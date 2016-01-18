package br.edu.ifpb.tsi.pdm.emersonraniere.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import br.edu.ifpb.tsi.pdm.emersonraniere.boaacaododia.R;

public class SobreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sobre);
        Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
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
            Intent it = new Intent(this, UltimasActivity.class);
            startActivity(it);
        }
        if (id == R.id.action_menu_diario){
            Intent it = new Intent(this, DiarioActivity.class);
            startActivity(it);
        }
        if (id == R.id.action_menu_sobre){
            Toast.makeText(this, "Você já esta nesta tela", Toast.LENGTH_SHORT).show();
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

}
