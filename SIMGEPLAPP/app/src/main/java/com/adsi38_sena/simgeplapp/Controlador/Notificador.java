package com.adsi38_sena.simgeplapp.Controlador;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

import com.adsi38_sena.simgeplapp.Modelo.SIMGEPLAPP;
import com.adsi38_sena.simgeplapp.R;
import com.adsi38_sena.simgeplapp.Vistas.ActivityMonitoreo;

public class Notificador {

    //private SIMGEPLAPP simgeplapp;

    public Notificador(/*SIMGEPLAPP app*/){
      //  this.simgeplapp = app;
    }

    public void notificarAlertaPlanta(Service source) {
        //fuentes => http://androcode.es/2012/09/notificaciones-metodo-tradicional-notification-builder-y-jelly-bean/
        try {
            //se supone que debe enviar notificacion al obtener lecturas extrañas:
            Bitmap icon = BitmapFactory.decodeResource(source.getResources(), R.drawable.img_notif);
            Uri sound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);//sonido

            PendingIntent acty_pendiente = PendingIntent.getActivity(source, /*codigoPeticion*/0,
                    /*intent*/new Intent(source, ActivityMonitoreo.class), /*flags*/ 0);

            NotificationManager mngNotif = (NotificationManager) source.getSystemService(source.NOTIFICATION_SERVICE);

            NotificationCompat.Builder constructorNotificacion = new NotificationCompat.Builder(source)
                    .setContentIntent(acty_pendiente)
                    .setTicker("Alerta Simgeplap")
                    .setSmallIcon(R.drawable.icon_notif)
                    .setContentTitle("Notificacion Simgeplapp")
                    .setContentText("Ha habido un sobresalto en la planta")
                    .setWhen(System.currentTimeMillis())
                    .setSound(sound)
                    .setVibrate(new long[]{800, 500, 900, 150, 1100})//tiempos entre vibracion y descanso
                    .setLargeIcon(icon)
                    .setLights(Color.RED, 1, 0);

            mngNotif.notify(SIMGEPLAPP.NOTIFICACIONES.ID_NOTIFICACION_ALERTA, constructorNotificacion.build());

        } catch (Exception eh) {
            Toast.makeText(source.getApplicationContext(), eh.toString(), Toast.LENGTH_LONG).show();
        }
    }
}


