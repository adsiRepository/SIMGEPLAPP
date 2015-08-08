package com.adsi38_sena.simgeplapp.Controlador;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import com.adsi38_sena.simgeplapp.Controlador.ComunicacionServidor.ComunicadorServidor;
import com.adsi38_sena.simgeplapp.Modelo.SIMGEPLAPP;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;

//http://joomla.probando-cosas.com.ar/index.php/item/180-android-servicios-parte-1

public class ServicioMonitoreo extends Service {
	private static final String TAG = "ServicioMonitoreo";

    private SIMGEPLAPP simgeplapp;
    protected Notificador notif;

	public int ID_PETICION_SERVICIO = 3, ID_RESPUESTA_PETICION = 5;
    //public int val1, val2;

    private ComunicadorServidor server;

    private double[] lecs_to_activity;

    private ArrayList<NameValuePair> peticion;// este es el post que el php en el servidor verificara para ejecutar su accion

    int contador = 0;

    /*private static final List<NameValuePair> peticion = Collections.unmodifiableList(
            new ArrayList<NameValuePair>() {{
                add(new BasicNameValuePair("peticion_lecturas", "ok"));
            }});*/

    //ciclo de vida de un service y conexion a este (bound) -> http://www.androidcurso.com/index.php/tutoriales-android/38-unidad-8-servicios-notificaciones-y-receptores-de-anuncios/289-ciclo-de-vida-de-un-servicio
	@Override
	public void onCreate(){
        super.onCreate();
        simgeplapp = (SIMGEPLAPP)getApplication();
        //SystemClock.sleep(1100);
		Toast.makeText(getBaseContext(), "Monitoreo Simgeplapp en marcha", Toast.LENGTH_LONG).show();
        simgeplapp.serviceOn = true;
        notif = new Notificador();
        lecs_to_activity = new double[]{0.0, 0.0, 0.0};
        server = new ComunicadorServidor();
        peticion = new ArrayList<NameValuePair>();
        peticion.add(new BasicNameValuePair("peticion_lecturas", "ok"));
    }


	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		//super.onStartCommand(intent, flags, startId);
        try {
            if (!proceso_delServicio.isAlive()) {
                proceso_delServicio.start();
            }
            //this.stopSelf();

            //return START_NOT_STICKY;
            //return super.onStartCommand(intent, flags, startId);
        }catch (Exception e){
            Toast.makeText(getBaseContext(), "hilServ: " + e.toString(), Toast.LENGTH_LONG).show();
        }
        return START_STICKY;
	}

	private Thread proceso_delServicio = new Thread(new Runnable() {
    //private class proceso_delServicio extends Thread {


        JSONObject jsob_lecs;
        JSONObject lecturas;

        @Override
        public void run() {

            while (simgeplapp.serviceOn == true) {
                try {

                    if (SIMGEPLAPP.hayConexionInternet(ServicioMonitoreo.this) == true) {

                        jsob_lecs = server.obtenerObjetoJSON(SIMGEPLAPP.Comunicaciones.URL_SERVER + "planta.php", peticion);

                        if (jsob_lecs != null && jsob_lecs.length() > 0) {

                            lecturas = jsob_lecs.getJSONObject("lecturas");

                            SIMGEPLAPP.TEMP = lecturas.getDouble("temperatura");
                            SIMGEPLAPP.PRES = lecturas.getDouble("presion");
                            SIMGEPLAPP.NIV = lecturas.getDouble("nivel");

                            lecs_to_activity[0] = SIMGEPLAPP.TEMP;
                            lecs_to_activity[1] = SIMGEPLAPP.PRES;
                            lecs_to_activity[2] = SIMGEPLAPP.NIV;

                            if (SIMGEPLAPP.TEMP > 120 || SIMGEPLAPP.PRES > 37 || SIMGEPLAPP.NIV > 41) {
                                notif.notificarAlertaPlanta(ServicioMonitoreo.this, lecs_to_activity);
                            }
                        }

                        Thread.sleep(/*90000*/3000);

                    } else {
                        Thread.sleep(3000);
                        contador++;
                        if(contador > 400) {
                            ServicioMonitoreo.this.stopSelf();
                            notif.notificarPerdida_deConexion(ServicioMonitoreo.this);
                        }
                    }
                } catch (Exception e) {
                    //Log.d("ExceptHiloServMonit: ", e.toString());
                }
            }
        }
    });


    @Override
	public void onDestroy() {
		super.onDestroy();
        try {
            simgeplapp.serviceOn = false;
            //proceso_delServicio.interrupt();
            Toast.makeText(getBaseContext(), "Monitoreo Simgeplapp finalizado", Toast.LENGTH_LONG).show();
        }catch (Exception eh){
            Toast.makeText(getBaseContext(), eh.toString(), Toast.LENGTH_LONG).show();
        }
	}



    //INTERCAMBIO DE DATOS ENTRE ANTIVITIES Y SERVICIOS (metodo messenger)
	//IPC con Messenger
	//http://www.survivingwithandroid.com/2014/01/android-bound-service-ipc-with-messenger.html
	/*protected class ServiceHandler extends Handler{
		@Override
		public void handleMessage(Message msg){//este msg es el que se crea en el activity
			//super.handleMessage(msg);
			try {
				int idMsg = msg.what;
				if(idMsg == ID_PETICION_SERVICIO) {
					//variable a regresar como respuesta
					//String miMsg = "var of service";
					//String datoRecibido = msg.getData().getString("datoTransferido");// mensaje recibido enviado en el bundle
					//Mensaje de respuesta
					//este es el que obtendre en el metodo handleMessage del Handler de la Activity
					Message msgBack = Message.obtain(null, ID_RESPUESTA_PETICION);//ID_RESPUESTA_PETICION es un entero (variable creada), es el id del mensaje intercambiado, puede ser cualquier entero
					Bundle paquete = new Bundle();
					//paquete.putString("msgBack", miMsg);
					paquete.putDoubleArray("lecturas", lecs_to_activity);
					msgBack.setData(paquete);
					msg.replyTo.send(msgBack);//este metodo(replyTo) se definio en el activity y lo que hace es ejecutar el Handler definido en el mismo
				}
			} catch (RemoteException e) {
				Toast.makeText(getBaseContext(), e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
				e.printStackTrace();
			}
		}
	}
    private Messenger contactoActivity = new Messenger(new ServiceHandler());
    //public IBinder binder = new Enlace();
    */@Override
    public IBinder onBind(Intent i) {
        return null;
        //return binder;
        //return contactoActivity.getBinder();
    }
    //public class Enlace extends Binder{
    //	public ServicioMonitoreo getService(){
    //		return ServicioMonitoreo.this;
    //	}
    //}

}
