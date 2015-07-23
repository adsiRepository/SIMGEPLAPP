package com.adsi38_sena.simgeplapp.Controlador;

import android.app.Activity;
import android.os.AsyncTask;
import android.widget.TextView;
import android.widget.Toast;

import com.adsi38_sena.simgeplapp.Controlador.ComunicacionServidor.SalvaTareas;
import com.adsi38_sena.simgeplapp.Modelo.SIMGEPLAPP;
import com.adsi38_sena.simgeplapp.Vistas.ActivityMonitoreo;

import java.util.Random;

//hilo que actualiza los TextView que muestran las variables. Consulta: AsyncTask
public class AsyncMonitor extends AsyncTask<Double, Double, String> {

    //private Double[] valores;
    //private Double temporal;
    //private Random random = new Random();
    //int aux;

    private ActivityMonitoreo myActy;
    private SIMGEPLAPP simgeplapp;

    public void setMyActy(Activity activity) {
        this.myActy = (ActivityMonitoreo)activity;
        simgeplapp = (SIMGEPLAPP)myActy.getApplication();
    }

    public void soltarActivity(){
        this.myActy = null;
    }

    @Override
    protected void onPreExecute(){
        //valores = new Double[3];
    }

    @Override
    protected String doInBackground(Double... params) {
        //temporal= simgeplapp.TEMP;
        while (simgeplapp.serviceOn == true){ // true = hara indefinido este ciclo simgeplapp.serviceOn
            try {
                Thread.sleep(1200);
                publishProgress();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return "Monitoreo Detenido";
    }

    @Override
    protected void onProgressUpdate(Double... values){//recibo los valores pasados en el "publishProgress"
        try {
            if(myActy != null){
                myActy.publicarLectura();
            }
        }catch (Exception eh){
            Toast.makeText(myActy.getApplicationContext(), "monitoreo pubprog: " + eh.toString(), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onPostExecute(String result) {
        //super.onPostExecute(result);
        if(myActy != null) {
            try {
                Toast.makeText(myActy.getApplicationContext(), result, Toast.LENGTH_LONG).show();
            } catch (Exception eh) {
                Toast.makeText(myActy.getApplicationContext(), "hilo monitoreo postEx: " + eh.toString(), Toast.LENGTH_LONG).show();
            }
        }
        SalvaTareas.obtenerInstancia().removerHilo(SIMGEPLAPP.LLAVE_PROCESO_MONITOREO);
    }

    @Override
    protected void onCancelled(){
    }
}