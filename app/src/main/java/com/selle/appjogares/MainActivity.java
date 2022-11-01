package com.selle.appjogares;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView lvJogadores;
    private ArrayAdapter adapter;
    private List<Jogador> jogadores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvJogadores = findViewById(R.id.lvJogadores);
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
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, jogadores);
        lvJogadores.setAdapter(adapter);
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