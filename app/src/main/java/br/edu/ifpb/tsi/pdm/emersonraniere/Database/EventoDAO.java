package br.edu.ifpb.tsi.pdm.emersonraniere.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import br.edu.ifpb.tsi.pdm.emersonraniere.Model.Evento;

/**
 * Created by emersonraniere on 15/01/16.
 */
public class EventoDAO {
    private SQLiteDatabase banco;

    public EventoDAO (Context context){
        this.banco = new EventoHelper(context).getWritableDatabase();
    }

    public void inserir(Evento novo){
        ContentValues cv = new ContentValues();
        cv.put("acao", novo.getAcao());
        cv.put("data", novo.getData().getTimeInMillis());

        this.banco.insert(EventoHelper.TABELA, null, cv);
    }

    public List<Evento> get(){
        String colunas[] = {"id", "data", "acao"};
        List<Evento> lista = new ArrayList<Evento>();
        Cursor c = this.banco.query(false, EventoHelper.TABELA, colunas, null, null, null, null, "id", null);

        if(c.getCount() > 0){
            c.moveToFirst();
            do{
                Evento e = new Evento();
                e.setId(c.getInt(c.getColumnIndex("id")));
                e.setData(c.getLong(c.getColumnIndex("data")));
                e.setAcao(c.getString(c.getColumnIndex("acao")));
                lista.add(e);
            }while (c.moveToNext());
        }
        return lista;
    }
    public List<Evento> getData(String data){
        String colunas[] = {"id", "data", "acao"};
        List<Evento> lista = new ArrayList<Evento>();
        Cursor c = this.banco.query(false, EventoHelper.TABELA, colunas, null, null, null, null, null, null);

        if(c.getCount() > 0){
            c.moveToFirst();
            do{
                Evento e = new Evento();

                e.setData(c.getLong(c.getColumnIndex("data")));
                e.setAcao(c.getString(c.getColumnIndex("acao")));

                DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.US);

                if (data.equals(sdf.format(e.getData().getTime()))){
                    lista.add(e);
                }

            }while (c.moveToNext());
        }
        return lista;
    }

    public boolean deleteById(int id){
        return banco.delete(EventoHelper.TABELA, "id" + "=" + id, null) > 0;
    }
}
