package com.selle.appjogares;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class formularioActivity extends AppCompatActivity {

    private EditText etNome, etNumero;
    private Button btnSalvar;


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

    }

    private void salvar() {
        String nome = etNome.getText().toString();
        if (nome.isEmpty()) {
// TOAST = Mensagem temporária que aponta um erro no preenchimento
            Toast.makeText(this, "Necessario o preencimento do campo nome", Toast.LENGTH_SHORT).show();
        } else {
            Jogador j = new Jogador();
            j.nome = nome;
//na tela do app, mesmo que o usuário digite um numero, a exibição dele se dá através de String!!!
            String sNumero = etNumero.getText().toString();
            if (sNumero.isEmpty()) {
                j.numero = 0;
            } else {
                j.numero = Integer.valueOf(sNumero);
            }
            JogadorDAO.inserir(this, j);
            limparTela();
        }
    }

    private void limparTela() {
        etNome.setText("");
        etNumero.setText("");
    }
}