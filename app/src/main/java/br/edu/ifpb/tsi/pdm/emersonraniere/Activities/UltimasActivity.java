package br.edu.ifpb.tsi.pdm.emersonraniere.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

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
        Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.dao =  new EventoDAO(this);
        loadingComponets();
        loadingContentFromDataBase();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_menu_settings){
            Intent it = new Intent(this, ConfiguracoesActivity.class);
            startActivity(it);
        }
        if (id == R.id.action_menu_ultimas){
            Toast.makeText(this, "Você já esta nesta tela", Toast.LENGTH_SHORT).show();
        }
        if (id == R.id.action_menu_diario){
            Intent it = new Intent(this, DiarioActivity.class);
            startActivity(it);
        }
        if (id == R.id.action_menu_sobre){
            Intent it = new Intent(this, SobreActivity.class);
            startActivity(it);
        }
        if (id == R.id.action_menu_exit){
            Intent it = new Intent(Intent.ACTION_MAIN);
            it.addCategory(Intent.CATEGORY_HOME);
            it.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(it);
            Toast.makeText(this, "Obrigado", Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
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
