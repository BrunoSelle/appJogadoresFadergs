package com.selle.appjogares;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

//DAO = DATA ACTION OBJECT
public class JogadorDAO {

// Inserir jogador do DB
    public static void inserir(Context contexto, Jogador jogador) {
        Banco conn = new Banco(contexto);
        SQLiteDatabase db = conn.getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put("nome", jogador.nome);
        valores.put("numero", jogador.numero);

        db.insert(Banco.TBL_JOGADORES, null, valores);
    }

// Editar jogador já cadastrado no DB
    public static void editar(Context contexto, Jogador jogador){
            Banco conn = new Banco(contexto);
            SQLiteDatabase db = conn.getWritableDatabase();

            ContentValues valores = new ContentValues();
            valores.put("nome", jogador.nome);
            valores.put("numero", jogador.numero);

            db.update(Banco.TBL_JOGADORES, valores, " id = " + jogador.id, null);
        }

// Excluir jogador do DB
        public static void excluir (Context contexto,int idJogador){
                Banco conn = new Banco(contexto);
                SQLiteDatabase db = conn.getWritableDatabase();

                db.delete(Banco.TBL_JOGADORES, " id = " + idJogador, null);
            }


// Exibir lista de jogadores
            public static List<Jogador> getJogadores (Context contexto){
                List<Jogador> lista = new ArrayList<>();
                Banco conn = new Banco(contexto);
                SQLiteDatabase db = conn.getReadableDatabase();
                String sql = "SELECT id, nome, numero FROM " + Banco.TBL_JOGADORES + " ORDER BY nome";
                Cursor cursor = db.rawQuery(sql, null);

                if (cursor.getCount() > 0) {
                    cursor.moveToFirst();

                    do {
                        Jogador j = new Jogador();
                        j.id = cursor.getInt(0);
                        j.nome = cursor.getString(1);
                        j.numero = cursor.getInt(2);
                        lista.add(j);
                    } while (cursor.moveToNext());
                }
                return lista;

            }
// Buscar um jogador por ID

    public static Jogador getJogadorById(Context contexto, int idJogador) {
        Banco conn = new Banco (contexto);
        SQLiteDatabase db = conn.getReadableDatabase();
        String sql = "SELECT id, nome, numero FROM " + Banco.TBL_JOGADORES + " WHERE id = " + idJogador;
        Cursor cursor = db.rawQuery(sql, null);

        if( cursor.getCount() > 0 ){
            cursor.moveToFirst();

                Jogador j = new Jogador();
                j.id = cursor.getInt(0);
                j.nome = cursor.getString(1);
                j.numero = cursor.getInt(2);
                return j;
        } else {
            return null;
        }

    }

        }