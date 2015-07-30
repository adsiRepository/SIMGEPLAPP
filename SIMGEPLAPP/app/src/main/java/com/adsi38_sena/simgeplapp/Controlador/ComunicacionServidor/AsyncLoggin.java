package com.adsi38_sena.simgeplapp.Controlador.ComunicacionServidor;


import android.app.Activity;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.widget.Toast;

import com.adsi38_sena.simgeplapp.Controlador.SalvaTareas;
import com.adsi38_sena.simgeplapp.Vistas.ActivityLogin;
import com.adsi38_sena.simgeplapp.Modelo.SIMGEPLAPP;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.conn.ConnectTimeoutException;

import java.io.IOException;
import java.io.UnsupportedEncodingException;


public class AsyncLoggin extends AsyncTask<String, String, Boolean> {

    SIMGEPLAPP simgeplapp;

    private FragmentoCargaServidor dialogo_carga;
    private ActivityLogin myActy;

    private ComunicadorServidor server;

    private static String[] resp_server = new String[4];

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        simgeplapp = (SIMGEPLAPP) myActy.getApplication();
        server = new ComunicadorServidor();
        dialogo_carga = new FragmentoCargaServidor();
        dialogo_carga.show(myActy.getFragmentManager(), SIMGEPLAPP.CargaSegura.TAG_PROGRESO_LOGGIN);
    }

    @Override
    protected Boolean doInBackground(String... params) {
        boolean pro;
        try {//obtenemos user_process y pass_onprocess
            resp_server = server.intentoLoggeo(params[0], params[1]);
            if(resp_server[0] == "logged") {
                publishProgress(new String[]{"Autenticado"});
                pro = true;
            }
            else {
                publishProgress(new String[]{resp_server[0]});
                pro = false;
            }
        } catch (ConnectTimeoutException e){
            publishProgress(new String[]{e.getMessage()+". Tiempo de espera terminado"});
            pro = false;
        } catch (ClientProtocolException e) {
            publishProgress(new String[]{e.getMessage()});
            pro = false;
        } catch (UnsupportedEncodingException e) {
            publishProgress(new String[]{e.toString()});
            pro = false;
        } catch (IOException e) {
            publishProgress(new String[]{e.toString()});
            pro = false;
        } catch (Exception e) {
            publishProgress(new String[]{e.toString()});
            pro = false;
        }
        return pro;
    }

    @Override
    protected void onProgressUpdate(String... values) {
        Toast.makeText(simgeplapp.getApplicationContext(), values[0], Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onPostExecute(Boolean result) {
        if(myActy != null) {
            if (result == true) {
                // if(simgeplapp.sessionAlive == false) {
                simgeplapp.sessionAlive = true;
                simgeplapp.session = new SIMGEPLAPP.Session();//Inicializo el objeto de session de la aplicacion
                simgeplapp.session.id = resp_server[1];
                simgeplapp.session.user = resp_server[2];
                simgeplapp.session.rol = resp_server[3];
                //configura los detalles de sesion que se conservaran durante reinicios y demas hasta que el usuario decida abandonar
                SharedPreferences confUser = myActy.getSharedPreferences("mi_usuario", myActy.MODE_PRIVATE);
                SharedPreferences.Editor editor = confUser.edit();
                editor.putString("id", simgeplapp.session.id);
                editor.putString("usuario", simgeplapp.session.user);
                editor.putString("rol", simgeplapp.session.rol);
                editor.putString("onsesion", "ok");//guardo en las preferencias q la sesion esta iniciada para por si se apaga el cel por ejemplo, al volver no deba iniciar de nuevo
                editor.commit();
                myActy.onLogged();
            } else {
                //loggError();
                Toast.makeText(myActy.getApplicationContext(), "No se pudo iniciar sesion", Toast.LENGTH_SHORT).show();
            }
            dialogo_carga.cerrarCarga(myActy.getFragmentManager());
        }

            SalvaTareas.obtenerInstancia().removerProcesoLoggin(SIMGEPLAPP.CargaSegura.LLAVE_PROCESO_LOGIN);
    }

    @Override
    protected void onCancelled() {

    }

    public void setMyActy(Activity myActy) {
        this.myActy = (ActivityLogin) myActy;
    }

    public void desAdjuntar() {
        this.myActy = null;
    }

}
