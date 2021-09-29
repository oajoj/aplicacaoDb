package edu.ifpa.dsvmobile.aplicacaodb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class BancoDeDados extends SQLiteOpenHelper {

    private static final int VERSAO_BANCO_DADOS = 1;
    private static final String NOME_BANCO_DADOS = "gerenciaContatos";
    private static final String TABELA_CONTATOS = "contatos";
    private static final String CAMPO_ID = "id";
    private static final String CAMPO_NOME = "nome";
    private static final String CAMPO_CELULAR = "celular";

    public BancoDeDados(Context context){
        super(context, NOME_BANCO_DADOS, null, VERSAO_BANCO_DADOS);
    }

    public BancoDeDados(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String criaTabelaContatos = "CREATE TABLE " + TABELA_CONTATOS + " (" + CAMPO_ID + " INTEGER PRIMARY KEY, " + CAMPO_NOME + " TEXT, " + CAMPO_CELULAR + " TEXT" + ")";
        sqLiteDatabase.execSQL(criaTabelaContatos);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABELA_CONTATOS);
        onCreate(sqLiteDatabase);
    }

    public void deletar(SQLiteDatabase banco){
        banco.execSQL("DROP TABLE IF EXISTS " + TABELA_CONTATOS);
    }

    public void inserirContato(Contatos contatos){
        SQLiteDatabase banco = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(CAMPO_NOME, contatos.getNome());
        values.put(CAMPO_CELULAR, contatos.getNumeroCelular());
        banco.insert(TABELA_CONTATOS, null, values);
        banco.close();
    }

    public Contatos consultarContato(int id){
        SQLiteDatabase banco = this.getReadableDatabase();
        Cursor cursor = banco.query(TABELA_CONTATOS, new String[] {
                CAMPO_ID, CAMPO_NOME, CAMPO_CELULAR
        }, CAMPO_ID + "=?", new String[] {String.valueOf(id)}, null, null, null, null);

        if(cursor != null) {
            cursor.moveToFirst();
        }

        return new Contatos(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2));
    }

    public List<Contatos> listarTodosContatos(){
        List<Contatos> listaContatos = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABELA_CONTATOS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if(cursor.moveToFirst()){
            do {
                Contatos contatos = new Contatos();
                contatos.setId(Integer.parseInt(cursor.getString(0)));
                contatos.setNome(cursor.getString(1));
                contatos.setNumeroCelular(cursor.getString(2));
                listaContatos.add(contatos);
            } while (cursor.moveToNext());
        }
        return listaContatos;
    }

    public int atualizaContato(Contatos contatos){
        SQLiteDatabase banco = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(CAMPO_NOME, contatos.getNome());
        values.put(CAMPO_CELULAR, contatos.getNumeroCelular());
        return banco.update(TABELA_CONTATOS, values, CAMPO_ID + "=?", new String[]{String.valueOf(contatos.getId())});
    }

    public void deletarContato(Contatos contatos){
        SQLiteDatabase banco = this.getWritableDatabase();
        banco.delete(TABELA_CONTATOS, CAMPO_ID + "=?", new String[]{String.valueOf(contatos.getId())});
        banco.close();
    }

    public int consultaQuantidadeContatos(){
        String countQuery = "SELECT * FROM " + TABELA_CONTATOS;
        SQLiteDatabase banco = this.getReadableDatabase();
        Cursor cursor = banco.rawQuery(countQuery, null);
        cursor.close();
        return cursor.getCount();
    }
}
