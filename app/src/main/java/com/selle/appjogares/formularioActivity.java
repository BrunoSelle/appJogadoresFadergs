package com.selle.appjogares;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class formularioActivity extends AppCompatActivity {

    private EditText etNome, etNumero;
    private Button btnSalvar;
    private String acao;
    private Jogador jogador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);
        etNome = findViewById(R.id.etNome);
        etNumero = findViewById(R.id.etNumero);
        btnSalvar = findViewById(R.id.btnSalvar);
        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                salvar();
            }
        });

        acao = getIntent().getStringExtra("acao");

        if (acao.equals("editar")) {
            carregarFormulario();
        }

    }

    private void carregarFormulario() {
        int id = getIntent().getIntExtra("idJogador", 0);
        jogador = JogadorDAO.getJogadorById(this, id);
        etNome.setText(jogador.nome);
        etNumero.setText(String.valueOf(jogador.numero));
    }

    private void salvar() {
        String nome = etNome.getText().toString();
        if (nome.isEmpty()) {
// TOAST = Mensagem temporária que aponta um erro no preenchimento
            Toast.makeText(this, "Necessario o preencimento do campo nome", Toast.LENGTH_SHORT).show();
        } else {
            if (acao.equals("novo")) {
                jogador = new Jogador();
            }
            jogador.nome = nome;
//na tela do app, mesmo que o usuário digite um numero, a exibição dele se dá através de String!!!
            String sNumero = etNumero.getText().toString();
            if (sNumero.isEmpty()) {
                jogador.numero = 0;
            } else {
                jogador.numero = Integer.valueOf(sNumero);
            }
            if (acao.equals("novo")) {
                JogadorDAO.inserir(this, jogador);
                limparTela();
            } else {
                JogadorDAO.editar(this, jogador);
                finish();
            }
        }
    }

    private void limparTela() {
        etNome.setText("");
        etNumero.setText("");
    }

// Utilização alerta para preenchimento de campo
    @Override
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
        menu.add("Nome...");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.toString().equals("Nome...")){
            AlertDialog.Builder alerta = new AlertDialog.Builder(formularioActivity.this);
            alerta.setIcon(android.R.drawable.ic_input_add);
            alerta.setTitle("Nome do Jogador...");
            EditText etAlertNome = new EditText(formularioActivity.this);
//É possivel usar os atributos de montagem de tela via código
            etAlertNome.setHint("Digite o nome");
            alerta.setView(etAlertNome);

            alerta.setNeutralButton("Cancelar", null);
            alerta.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    etNome.setText( etAlertNome.getText().toString());
                }
            });

        }


        return super.onOptionsItemSelected(item);
    }
}