package com.selle.appjogares;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Banco extends SQLiteOpenHelper {

    private static String NOME_DB = "bancoJogadores";
    private static int VERSAO_DB = 1;
    public static String TBL_JOGADORES = "jogadores";

    public Banco(@Nullable Context context) {
        super(context, NOME_DB, null, VERSAO_DB);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TBL_JOGADORES + "(" +
                   " id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                   "nome TEXT NOT NULL," +
                   "numero INTEGER ); "
                  );


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
