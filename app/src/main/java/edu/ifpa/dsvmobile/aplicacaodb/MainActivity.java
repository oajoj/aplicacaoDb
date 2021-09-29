package edu.ifpa.dsvmobile.aplicacaodb;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView mostraConteudo;
    private String text = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mostraConteudo = (TextView) findViewById(R.id.mostraConteudo);

        BancoDeDados banco = new BancoDeDados(this);

        banco.inserirContato(new Contatos("Steve Formoso", "9321-4430"));
        banco.inserirContato(new Contatos("Osmar Mota", "(8267-7025"));
        banco.inserirContato(new Contatos("Rolando Caio da Rocha", "8745-1123"));
        banco.inserirContato( new Contatos("Omar Telo", "8766-4411"));

        List<Contatos> contatos = banco.listarTodosContatos();

        for(Contatos c : contatos){
            String log = "ID: " + c.getId() + ", NOME: " + c.getNome() + ", CELULAR: " + c.getNumeroCelular() + "\n";
            text = text + log;
        }

        mostraConteudo.setText(text);

    }
}