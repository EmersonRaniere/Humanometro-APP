package br.edu.ifpb.tsi.pdm.emersonraniere.Activities;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import br.edu.ifpb.tsi.pdm.emersonraniere.AlarmManager.AlarmReciever;
import br.edu.ifpb.tsi.pdm.emersonraniere.boaacaododia.R;

public class ConfiguracoesActivity extends AppCompatActivity {
    private Button btn_ativar_notificacao, btn_cancelar_notificacao;
    private PendingIntent pendintIntent;
    private AlarmManager alarmManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracoes);
        loadingComponets();
        loadingListeners();
        loadingBroadcast();
    }
    public void loadingComponets(){
        this.btn_ativar_notificacao = (Button) findViewById(R.id.btn_settings_notification);
        this.btn_cancelar_notificacao = (Button) findViewById(R.id.btn_settings_cancel_notification);
    }

    public void loadingListeners(){
        this.btn_ativar_notificacao.setOnClickListener(new onClickAtivarBotao());
        this.btn_cancelar_notificacao.setOnClickListener(new onClickCancelarBotao());
    }

    // Recupera a PendingIntent que executará o broadcast
    public void loadingBroadcast(){
        Intent it = new Intent(ConfiguracoesActivity.this, AlarmReciever.class);
        pendintIntent = PendingIntent.getBroadcast(ConfiguracoesActivity.this, 0 , it, 0);
    }

    private class onClickAtivarBotao implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            setAlarm(v);
        }
    }
    private class onClickCancelarBotao implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            cancelAlarm(v);
        }
    }
    public void setAlarm(View v){
        int intervalo = 8000;
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), intervalo, pendintIntent);
        Toast.makeText(this, "Notificação ativada!", Toast.LENGTH_SHORT).show();
    }

    public void cancelAlarm(View v){
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.cancel(pendintIntent);
        pendintIntent.cancel();
        Toast.makeText(this, "Notificação cancelada!", Toast.LENGTH_SHORT).show();
    }

}
