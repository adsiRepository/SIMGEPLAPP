package com.adsi38_sena.simgeplapp.Controlador;


import android.app.Activity;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.os.Vibrator;
import android.widget.Toast;

import com.adsi38_sena.simgeplapp.Modelo.MenuActivity;
import com.adsi38_sena.simgeplapp.Modelo.SIMGEPLAPP;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

//DIALOGOS => http://developer.android.com/guide/topics/ui/dialogs.html

public class FragmentoLogin extends Fragment {

    public interface Llamados_deActivity {
        void onPreExecute();
        void onProgressUpdate(String... values);
        void onPostExecute();
        void onCancelled();
    }

    SIMGEPLAPP simgeplapp;

    private String dirIP_server = "192.168.0.14", user, pass;

    ComunicadorServidor server;

    public AsyncLogg logging;

    private Llamados_deActivity llamados;

    public FragmentoLogin(){}

    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);
        // Obtener la instancia del activity q utiliza este fragmento
        llamados = (Llamados_deActivity)activity;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Retener el fragmento creado
        setRetainInstance(true);

        simgeplapp = (SIMGEPLAPP)getActivity().getApplication();

        server = new ComunicadorServidor(dirIP_server);

        user = getArguments().getString("usuario_digitado");
        pass = getArguments().getString("passw_digitado");

        //Una vez creado el fragmento se inicia la tarea asíncrona
        logging = new AsyncLogg();
        logging.execute(user, pass);
    }

    @Override
    public void onDetach(){
        super.onDetach();
        llamados = null;
    }


    public class AsyncLogg extends AsyncTask<String, String, Boolean> {

        private String user_process, pass_onprocess;
        //private ProgressDialog progreso;

        @Override
        protected void onPreExecute() {
            /*progreso = new ProgressDialog(getActivity());
            progreso.setMessage("Comprobando Base de Datos");
            progreso.setIndeterminate(false);
            progreso.setCancelable(false);
            progreso.show();*/
            if (llamados != null) {
                llamados.onPreExecute();
            }
        }

        @Override
        protected Boolean doInBackground(String... params) {
            try {//obtenemos user_process y pass_onprocess
                user_process = params[0];
                pass_onprocess = params[1];
                SystemClock.sleep(2500);
                //enviamos, recibimos y analizamos los datos en segundo plano.
                switch (server.loggin(user_process, pass_onprocess)) {//ejecuto el metodo loggin del objeto server este instanciado de la clase "ComunicadorServidor"
                    case -1:
                        publishProgress(new String[]{"No se obtuvo respuesta del servidor"});
                        return false;
                    case 0:
                        publishProgress(new String[]{"El usuario no existe en la Base de Datos"});
                        return false;
                    case 1:
                        publishProgress(new String[]{"Autenticado"});
                        return true;
                    default:
                        publishProgress(new String[]{"Error desconocido"});
                        return false;
                }
            } catch (Exception e) {
                e.printStackTrace();
                publishProgress(new String[]{e.toString()});
                return false;
            }
        }

        @Override
        protected void onProgressUpdate(String... values){
            //Toast.makeText(getActivity().getApplicationContext(), values[0], Toast.LENGTH_LONG).show();
            if (llamados != null) {
                llamados.onProgressUpdate(values[0]);
            }
        }

        @Override
        protected void onPostExecute(Boolean result) {
            //progreso.dismiss();
            if (result == true) {
                // if(simgeplapp.sessionAlive == false) {
                simgeplapp.session = new SIMGEPLAPP.Session();//Inicializo el objeto de session de la aplicacion
                simgeplapp.session.user = user_process;
                //configura los detalles de sesion que se conservaran durante reinicios y demas hasta que el usuario decida abandonar
                /*SharedPreferences confUser = getSharedPreferences("mi_usuario", MODE_PRIVATE);
                SharedPreferences.Editor editor = confUser.edit();
                editor.putString("usuario", pass_digitado);
                editor.putString("onsesion", "ok");//guardo en las preferencias q la sesion esta iniciada para por si se apaga el cel por ejemplo, al volver no deba iniciar de nuevo
                editor.commit();*/
                simgeplapp.sessionAlive = true;
                getActivity().finish();
                startActivity(new Intent(getActivity().getApplicationContext(), MenuActivity.class));
                Toast.makeText(getActivity().getApplicationContext(), "Session Iniciada", Toast.LENGTH_LONG).show();
            } else {
                loggError();
                Toast.makeText(getActivity().getApplicationContext(), "No se pudo iniciar sesion", Toast.LENGTH_SHORT).show();
            }

            if (llamados != null) {
                llamados.onPostExecute();
            }
        }

        @Override
        protected void onCancelled() {
            if (llamados != null) {
                llamados.onCancelled();
            }
        }

    }

    protected void loggError(){
        try {
            Vibrator vibrator = (Vibrator)getActivity().getSystemService(Context.VIBRATOR_SERVICE);
            vibrator.vibrate(400);
            Thread.sleep(540);
            vibrator.vibrate(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
            Toast.makeText(getActivity().getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
        }
    }

}
