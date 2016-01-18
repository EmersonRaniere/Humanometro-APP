package br.edu.ifpb.tsi.pdm.emersonraniere.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.edu.ifpb.tsi.pdm.emersonraniere.boaacaododia.R;
import br.edu.ifpb.tsi.pdm.emersonraniere.Model.Evento;
import br.edu.ifpb.tsi.pdm.emersonraniere.Database.EventoDAO;

public class UltimasActivity extends AppCompatActivity {

    private EventoDAO dao;
    private ListView lv_ultimas;
    private ArrayAdapter<Evento> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ultimas);
        this.dao =  new EventoDAO(this);
        loadingComponets();
        loadingContentFromDataBase();
    }

    public void loadingComponets(){
        this.lv_ultimas = (ListView) findViewById(R.id.lv_ultimas);
    }

    public void loadingContentFromDataBase(){
        List<Evento> lista = new ArrayList<Evento>();
        lista = dao.get();
        Collections.sort(lista);
        adapter = new ArrayAdapter<Evento>(this,android.R.layout.simple_list_item_1,lista);
        this.lv_ultimas.setAdapter(adapter);
    }
}
