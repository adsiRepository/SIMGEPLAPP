package com.adsi38_sena.simgeplapp.Controlador;

import android.app.NotificationManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

import com.adsi38_sena.simgeplapp.Modelo.SIMGEPLAPP;
import com.adsi38_sena.simgeplapp.R;

public class Notificador {

    private SIMGEPLAPP simgeplapp;

    public Notificador(SIMGEPLAPP app){
        this.simgeplapp = app;
    }

    public void notificarAlertaPlanta() {
        try {
            //se supone que debe enviar notificacion al obtener lecturas extrañas:
            Bitmap icon = BitmapFactory.decodeResource(simgeplapp.getResources(), R.drawable.homero);
            Uri sound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);//sonido
            NotificationManager mngNotif = (NotificationManager) simgeplapp.getSystemService(simgeplapp.NOTIFICATION_SERVICE);
            NotificationCompat.Builder constructorNotificacion = new NotificationCompat.Builder(simgeplapp.getBaseContext())
                    .setTicker("Alerta Simgeplap")
                    .setSmallIcon(R.drawable.img_notif).setContentTitle("simgeplapp")
                    .setContentText("Ha habido un sobresalto en la planta")
                    .setWhen(System.currentTimeMillis())
                    .setSound(sound)
                    .setVibrate(new long[]{300, 800, 1000, 2300})
                    .setLargeIcon(icon);
            mngNotif.notify(1, constructorNotificacion.build());
        } catch (Exception eh) {
            Toast.makeText(simgeplapp.getApplicationContext(), eh.toString(), Toast.LENGTH_LONG).show();
        }
    }
}


