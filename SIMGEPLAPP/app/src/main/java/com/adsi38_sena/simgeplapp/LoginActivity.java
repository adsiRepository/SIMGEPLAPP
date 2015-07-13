package com.adsi38_sena.simgeplapp;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.adsi38_sena.simgeplapp.Controlador.ComunicadorServidor;
import com.adsi38_sena.simgeplapp.Modelo.MenuActivity;
import com.adsi38_sena.simgeplapp.Modelo.SIMGEPLAPP;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;


public class LoginActivity extends Activity {

    private SIMGEPLAPP simgeplapp;//INSTANCIA DE LA APLICACION

    private EditText txt_user, txt_pass;
    private Button btn_login;
    private ProgressDialog pDialog;

    ComunicadorServidor server;

    private String dirIP_server = "192.168.0.14";

    //ESTADO
    @Override
    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putString("temp_user", txt_user.getText().toString());
        outState.putString("temp_pass", txt_pass.getText().toString());
    }
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);
        txt_user.setText(savedInstanceState.getString("temp_user"));
        txt_pass.setText(savedInstanceState.getString("temp_pass"));
    }

    //CICLO DE VIDA
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        simgeplapp = (SIMGEPLAPP)getApplication();
        server = new ComunicadorServidor(dirIP_server);//construyo el objeto comunicador con la direccion ip del pc en donde montemos el proyecto php
        txt_user = (EditText)findViewById(R.id.edt_user);
        txt_pass = (EditText)findViewById(R.id.edt_pass);
        btn_login = (Button)findViewById(R.id.btn_login);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = txt_user.getText().toString();
                String pass = txt_pass.getText().toString();
                if ((user.length() * pass.length()) > 0) {//verificamos si estan en blanco
                    //si pasamos esa validacion ejecutamos el asynctask pasando el usuario y clave como parametros
                    new AsyncLogg().execute(user, pass);
                } else {
                    loggError();
                    Toast.makeText(getApplicationContext(), "digita completamente los campos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

/*		CLASE ASYNCTASK
 * usaremos esta clase para poder mostrar el dialogo del progreso mientras enviamos y obtenemos los datos.
 * podria hacerse lo mismo sin usar esto, pero si el tiempo de respuesta es demasiado -lo que podria ocurrir
 * si la conexion es lenta o el servidor tarda en responder- la aplicacion seria inestable.
 * ademas observariamos el mensaje de que la app no responde.
 */

    protected class AsyncLogg extends AsyncTask<String, String, Boolean> {

        String user, pass;

        @Override
        protected void onPreExecute() {
            //para el progress dialog
            pDialog = new ProgressDialog(LoginActivity.this);
            pDialog.setMessage("Comprobando Base de Datos");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }

        @Override
        protected Boolean doInBackground(String... params) {
            try {//obtenemos user y pass
                user = params[0];
                pass = params[1];
                //enviamos, recibimos y analizamos los datos en segundo plano.
                switch (server.loggin(user, pass)){//ejecuto el metodo loggin del objeto server este instanciado de la clase "ComunicadorServidor"
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
            Toast.makeText(getApplicationContext(), values[0], Toast.LENGTH_SHORT).show();
        }

        /*Una vez terminado doInBackground segun lo que halla ocurrido
        pasamos a la sig. activity
        o mostramos error*/
        @Override
        protected void onPostExecute(Boolean result) {
            pDialog.dismiss();//ocultamos progess dialog.
            if (result == true){
            // if(simgeplapp.sessionAlive == false) {
                    simgeplapp.session = new SIMGEPLAPP.Session();//Inicializo el objeto de session de la aplicacion
                    simgeplapp.session.user = user;
                    simgeplapp.sessionAlive = true;
                    startActivity(new Intent(LoginActivity.this, MenuActivity.class));
                    Toast.makeText(getApplicationContext(), "Session Iniciada", Toast.LENGTH_LONG).show();
               // }
            } else {
                loggError();
                Toast.makeText(getApplicationContext(), "No se pudo iniciar sesion", Toast.LENGTH_SHORT).show();
            }
        }
    }

    protected void loggError(){
        try {
            Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            vibrator.vibrate(400);
            Thread.sleep(540);
            vibrator.vibrate(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    //MENU
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
