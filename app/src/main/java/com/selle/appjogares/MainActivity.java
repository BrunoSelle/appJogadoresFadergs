package com.selle.appjogares;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView lvJogadores;
    //private ArrayAdapter adapter;
    private List<Jogador> jogadores;
    private JogadorAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvJogadores = findViewById(R.id.lvJogadores);

// Excluir item com click longo
        lvJogadores.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                excluir(i);
                return true;
            }
        });

// Transmissão de dados para outra tela
        lvJogadores.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Jogador selecionado = jogadores.get(i);
// Criação de nova tela: INTENT
                Intent intent = new Intent(MainActivity.this, formularioActivity.class);
                intent.putExtra("acao", "editar");
                intent.putExtra("idJogador", selecionado.id);
                startActivity( intent );
            }
        });
    }

    private void excluir (int posicao){
        AlertDialog.Builder alerta = new AlertDialog.Builder(this);
        alerta.setIcon( android.R.drawable.ic_delete);
        alerta.setTitle("EXCLUSÃO");
        String nome = jogadores.get(posicao).nome;
        alerta.setMessage("Confirma a exclusão de " + nome + "?");
        alerta.setNegativeButton("Não", null);
        alerta.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                int id = jogadores.get(posicao).id;
                JogadorDAO.excluir(MainActivity.this, id);
                carregarJogadores();
            }
        });
        alerta.show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        carregarJogadores();
    }

    private void carregarJogadores(){

        jogadores = JogadorDAO.getJogadores(this);
        if(jogadores.size() == 0){
            Jogador fake = new Jogador(0, "Nenhum jogador cadastrado",0);
            jogadores.add (fake);
            lvJogadores.setEnabled(false);
        } else {
            lvJogadores.setEnabled(true);
        }
    //    adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, jogadores);
    //    lvJogadores.setAdapter(adapter);
        adapter = new JogadorAdapter(this, jogadores);

    }

// Criação Menu
    @Override
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {

        menu.add("Add new player...");

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.toString().equals("Add new player...")) {
            Intent intent = new Intent(MainActivity.this, formularioActivity.class);
            intent.putExtra("action", "new");
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}