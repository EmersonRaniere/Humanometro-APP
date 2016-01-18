package br.edu.ifpb.tsi.pdm.emersonraniere.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import br.edu.ifpb.tsi.pdm.emersonraniere.boaacaododia.R;

public class MainActivity extends AppCompatActivity {
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.fab = (FloatingActionButton) findViewById(R.id.fab);
        this.fab.setOnClickListener(new AddBotao());

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

    private class AddBotao implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            if(v.equals(fab)){
                Intent it = new Intent(MainActivity.this, AddActivity.class);
                startActivity(it);
            }
        }
    }


}
