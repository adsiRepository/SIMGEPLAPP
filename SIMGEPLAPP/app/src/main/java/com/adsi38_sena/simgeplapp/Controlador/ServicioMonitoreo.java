package com.adsi38_sena.simgeplapp.Controlador;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import android.widget.Toast;

import com.adsi38_sena.simgeplapp.Modelo.SIMGEPLAPP;

import java.util.Random;

//http://joomla.probando-cosas.com.ar/index.php/item/180-android-servicios-parte-1

public class ServicioMonitoreo extends Service {
	private static final String TAG = "ServicioMonitoreo";

	public int ID_PETICION_SERVICIO = 3, ID_RESPUESTA_PETICION = 5;

	public int val1, val2;

	private SIMGEPLAPP simgeplapp;

	private Messenger contactoActivity = new Messenger(new ServiceHandler());
	public IBinder binder = new Enlace();
	@Override
	public IBinder onBind(Intent i) {
		// TODO Auto-generated method stub
		//return null;
		//return binder;
		return contactoActivity.getBinder();
	}
	public class Enlace extends Binder{
		public Enlace(){}
		public ServicioMonitoreo getService(){
			return ServicioMonitoreo.this;
		}
	}
	public String variablePrueba1 = "Good";


    //ciclo de vida de un service y conexion a este (bound) -> http://www.androidcurso.com/index.php/tutoriales-android/38-unidad-8-servicios-notificaciones-y-receptores-de-anuncios/289-ciclo-de-vida-de-un-servicio
	@Override
	public void onCreate(){
		Toast.makeText(getBaseContext(), "Monitoreo Simgeplapp en marcha", Toast.LENGTH_LONG).show();
		simgeplapp = (SIMGEPLAPP)getApplication();
        simgeplapp.serviceOn = true;
	}


	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		super.onStartCommand(intent, flags, startId);

		if(!proceso_delServicio.isAlive()) {
			proceso_delServicio.start();
		}
		//this.stopSelf();
		return START_STICKY;
	}
	private Thread proceso_delServicio = new Thread(new Runnable() {
		Random random = new Random();
		@Override
		public void run() {//por ahora estamos generando las variables con Random. Aqui hay que implementar la comunicacion al servidor
			while(true) {
				try {
					Thread.sleep(1000);
					//se supone que debe enviar notificacion al obtener lecturas extrañas:
					/*NotificationManager mngNotif = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
					NotificationCompat.Builder constructorNotificacion = new NotificationCompat.Builder(getBaseContext())
							.setSmallIcon(R.drawable.img_notif).setContentTitle("Notificacion")
							.setContentText("El servicio ha terminado correctamente")
							.setWhen(System.currentTimeMillis());
					mngNotif.notify(1, constructorNotificacion.build());*/
					simgeplapp.TEMP = random.nextDouble();
					simgeplapp.PRES = random.nextDouble();
                    simgeplapp.NIV = random.nextDouble();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	});


	@Override
	public void onDestroy() {
		super.onDestroy();
        simgeplapp.serviceOn = false;
        Toast.makeText(getBaseContext(), "Monitoreo Simgeplapp finalizado", Toast.LENGTH_LONG).show();
	}


	//IPC con Messenger
	//http://www.survivingwithandroid.com/2014/01/android-bound-service-ipc-with-messenger.html
	protected class ServiceHandler extends Handler{
		@Override
		public void handleMessage(Message msg){//este msg es el que se crea en el activity
			//super.handleMessage(msg);
			try {
				int idMsg = msg.what;
				if(idMsg == ID_PETICION_SERVICIO) {
					//variable a regresar como respuesta
					String miMsg = "var of service";
					//String datoRecibido = msg.getData().getString("datoTransferido");// mensaje recibido enviado en el bundle
					//Mensaje de respuesta
					//este es el que obtendre en el metodo handleMessage del Handler de la Activity
					Message msgBack = Message.obtain(null, ID_RESPUESTA_PETICION);//ID_RESPUESTA_PETICION es un entero (variable creada), es el id del mensaje intercambiado, puede ser cualquier entero
					Bundle paquete = new Bundle();
					//paquete.putString("msgBack", miMsg);
					paquete.putIntArray("values", new int[]{val1, val2});//a�ado un arreglo de enteros en el paquete de envio
					msgBack.setData(paquete);
					msg.replyTo.send(msgBack);//este metodo(replyTo) se definio en el activity y lo que hace es ejecutar el Handler definido en el mismo
				}
			} catch (RemoteException e) {
				Toast.makeText(getBaseContext(), e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
				e.printStackTrace();
			}
		}
	}

	public String getVariablePrueba1() {
		return variablePrueba1;
	}

	public void setVariablePrueba1(String variablePrueba1) {
		this.variablePrueba1 = variablePrueba1;
	}

}
