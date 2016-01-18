package br.edu.ifpb.tsi.pdm.emersonraniere.Activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.ListView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import br.edu.ifpb.tsi.pdm.emersonraniere.boaacaododia.R;
import br.edu.ifpb.tsi.pdm.emersonraniere.Model.Evento;
import br.edu.ifpb.tsi.pdm.emersonraniere.Database.EventoDAO;

public class DiarioActivity extends AppCompatActivity {
    private CalendarView calendar;
    private EventoDAO dao;
    private ListView lv_diario;
    private ArrayAdapter<Evento> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diario);
        Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        this.dao = new EventoDAO(this);

        loadingComponets();
        loadingListeners();
        initCalendar();
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
            Toast.makeText(this, "Você já esta nesta tela", Toast.LENGTH_SHORT).show();
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
        this.lv_diario = (ListView) findViewById(R.id.lv_diario);

    }

    public void loadingListeners(){
        this.lv_diario.setOnItemLongClickListener(new onLongClickItemListView());
        this.lv_diario.setOnItemClickListener(new onClickItemListView());
    }

    public void initCalendar() {
        this.calendar = (CalendarView) findViewById(R.id.cv_diario);

        this.calendar.setShowWeekNumber(false);
        this.calendar.setFirstDayOfWeek(1);
        this.calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                List<Evento> lista = new ArrayList<Evento>();
                Calendar myCalendar = Calendar.getInstance();
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, month);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                String query;
                DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
                query = sdf.format(myCalendar.getTime());
                lista = dao.getData(query);

                adapter = new ArrayAdapter<Evento>(DiarioActivity.this,
                        android.R.layout.simple_list_item_1,lista);
                lv_diario.setAdapter(adapter);
            }
        });
    }
    private class onLongClickItemListView implements AdapterView.OnItemLongClickListener {

        @Override
        public boolean onItemLongClick(AdapterView<?> parent, final View view, final int position, final long id) {
            List<Evento> lista = new ArrayList<Evento>();
            lista = dao.get();
            String item = adapter.getItem(position).getAcao();
            for (final Evento e: lista ) {
                if(e.getAcao().equals(item) )  {

                    AlertDialog.Builder alert = new AlertDialog.Builder(DiarioActivity.this);
                    alert.setTitle("Deseja excluir essa ação?");
                    alert.setMessage(item);
                    alert.setPositiveButton("Sim", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dao.deleteById(e.getId());
                            adapter.remove(adapter.getItem(position));
                            adapter.notifyDataSetChanged();
                            Toast.makeText(DiarioActivity.this, "Ação removida com sucesso",
                                    Toast.LENGTH_SHORT).show();
                        }
                    });
                    alert.setNegativeButton("Não", null);
                    alert.create().show();

                }
            }
            return true;
        }
    }

    private class onClickItemListView implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            String item = adapter.getItem(position).toStringActionSend();

            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, item);
            sendIntent.setType("text/plain");
            startActivity(sendIntent);
        }
    }
}
