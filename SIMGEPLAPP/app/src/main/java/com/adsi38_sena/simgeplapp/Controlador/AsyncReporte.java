package com.adsi38_sena.simgeplapp.Controlador;

import android.app.Activity;
import android.os.AsyncTask;
import android.widget.Toast;

import com.adsi38_sena.simgeplapp.Controlador.ComunicacionServidor.ComunicadorServidor;
import com.adsi38_sena.simgeplapp.Controlador.ComunicacionServidor.FragmentoCargaServidor;
import com.adsi38_sena.simgeplapp.Modelo.SIMGEPLAPP;
import com.adsi38_sena.simgeplapp.Vistas.ActivityReportes;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

//hilo que actualiza los TextView que muestran las variables. Consulta: AsyncTask
public class AsyncReporte extends AsyncTask<Double, String, Boolean> {

    //private Double[] valores;
    //private Double temporal;
    //private Random random = new Random();
    //int aux;

    private ActivityReportes myActy;
    private SIMGEPLAPP simgeplapp;
    private ComunicadorServidor server;
    private FragmentoCargaServidor fragLoad;

    public void setMyActy(Activity activity) {
        this.myActy = (ActivityReportes)activity;
        simgeplapp = (SIMGEPLAPP)myActy.getApplication();
    }

    public void soltarActivity(){
        this.myActy = null;
    }

    @Override
    protected void onPreExecute(){
        server = new ComunicadorServidor();
        fragLoad = new FragmentoCargaServidor();
        fragLoad.show(myActy.getFragmentManager(), SIMGEPLAPP.CargaSegura.TAG_PROGRESO_CARGA_REPORTE);
    }

    @Override
    protected Boolean doInBackground(Double... params) {
        boolean adv = false;
        try {

            ArrayList<NameValuePair> parms = new ArrayList<NameValuePair>();
            parms.add(new BasicNameValuePair("generar_reporte", "true"));

            JSONObject obj = server.obtenerObjetoJSON(SIMGEPLAPP.Comunicaciones.URL_SERVER_LECS +"reportes.php", parms);

            if(obj != null && obj.length() > 0){
                boolean ok = obj.getBoolean("hay_datos");
                if(ok == true){
                    JSONArray jarray_datos = obj.getJSONArray("datos");
                    //publishProgress(""+jarray_datos.length());
                    for(int i = 0; i < jarray_datos.length(); i++) {
                        JSONObject fila = jarray_datos.getJSONObject(i);
                        /*ESTE ES UN EJEMPLO DEL JSON QUE CAPTURA =>
                        {"hay_datos":true,
                         "datos":[
                         {"lec":114.45,"factor":"presion","fecha":"2015-08-10","hora":"09:10:14"},
                         {"lec":101.42,"factor":"nivel","fecha":"2015-08-10","hora":"09:10:15"},
                         {"lec":11.12,"factor":"presion","fecha":"2015-08-10","hora":"09:15:12"}
                        ]}
                        //--
                        las llaves {} son JSONObject
                        como ven, la respuesta en si es un Json object.
                        los corchetes [] son JSONArray
                        "datos" engloba un array, por los corchetes, que a su vez es un array de objects porque contiene varios de estos dentro
                        */
                        publishProgress("" + fila.getDouble("lec"), fila.getString("factor"), fila.getString("fecha"), fila.getString("hora"));
                    }
                    adv = true;
                }
            }

        }catch (Exception e){
            publishProgress(e.toString());
            adv = false;
        }
        return adv;
    }

    @Override
    protected void onProgressUpdate(String... values){//recibo los valores pasados en el "publishProgress"
        try {
            if(myActy != null){
                myActy.plasmarTabla(values);
                //Toast.makeText(myActy.getApplicationContext(), "ProgUpd AsyRep: " + values[0], Toast.LENGTH_LONG).show();
            }
        }catch (Exception eh){
            Toast.makeText(myActy.getApplicationContext(), "ProgUpd AsyRep: " + eh.toString(), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onPostExecute(Boolean result) {
        //super.onPostExecute(result);
        if(myActy != null) {
            try {
                if(result == true) {
                    Toast.makeText(myActy.getApplicationContext(), "datos obtenidos con exito", Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(myActy.getApplicationContext(), "No se obtuvieron Datos", Toast.LENGTH_LONG).show();
                }
                fragLoad.cerrarCarga(myActy.getFragmentManager());
            } catch (Exception eh) {
                Toast.makeText(myActy.getApplicationContext(), "hilo monitoreo postEx: " + eh.toString(), Toast.LENGTH_LONG).show();
            }
        }

        SalvaTareas.obtenerInstancia().removerProcesoReporte(SIMGEPLAPP.CargaSegura.LLAVE_PROCESO_REPORTE);
    }

    @Override
    protected void onCancelled(){
    }
}