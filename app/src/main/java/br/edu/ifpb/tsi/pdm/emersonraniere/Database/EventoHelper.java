package br.edu.ifpb.tsi.pdm.emersonraniere.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by emersonraniere on 15/01/16.
 */
public class EventoHelper extends SQLiteOpenHelper {
    private static final String BANCO = "boaacao.sqlite";
    private static final int VERSAO = 1;
    public static final String TABELA = "evento";

    public EventoHelper(Context context) {
        super(context, BANCO, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS \"evento\" (\"id\" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, \"data\" DATETIME, \"acao\" VARCHAR)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table evento");
        this.onCreate(db);
    }
}
