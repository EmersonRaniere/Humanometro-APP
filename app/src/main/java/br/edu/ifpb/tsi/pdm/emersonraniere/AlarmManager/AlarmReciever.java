package br.edu.ifpb.tsi.pdm.emersonraniere.AlarmManager;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.NotificationCompat;

import java.util.Random;

import br.edu.ifpb.tsi.pdm.emersonraniere.Activities.MainActivity;
import br.edu.ifpb.tsi.pdm.emersonraniere.boaacaododia.R;


/**
 * Created by Emerson on 2016-01-17.
 */
public class AlarmReciever extends BroadcastReceiver{
    private boolean readNotification = false;
    private PendingIntent pendintIntent;
    private int dummyuniqueInt = new Random().nextInt(543254);


    @Override
    public void onReceive(Context context, Intent intent) {

        String note = "Você já fez sua boa ação de hoje?";
        String titulo = "Humanômetro";
        String msgBarra = "Você recebeu uma mensagem";

            // Acvivity que será retornada qnd clicado na notificação
            Intent it = new Intent(context,MainActivity.class);

            // ação que executa a activity ao selecionar
            pendintIntent = PendingIntent.getActivity(context, dummyuniqueInt, it, PendingIntent.FLAG_ONE_SHOT);

            // Cria o build com as informações da notificação
            NotificationCompat.Builder notification = new NotificationCompat.Builder(context);
            notification.setSmallIcon(R.drawable.notification);
            notification.setContentTitle(titulo);
            notification.setContentText(note);
            notification.setContentIntent(pendintIntent);
            notification.setAutoCancel(true);
            notification.setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE);

           //Recupera o serviço de notificação e configura para com o builder.
            NotificationManager nmgr = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            nmgr.notify(R.string.app_name, notification.build());

    }


}
