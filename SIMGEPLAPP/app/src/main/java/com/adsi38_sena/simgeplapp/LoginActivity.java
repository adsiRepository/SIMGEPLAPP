package com.adsi38_sena.simgeplapp;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.os.Vibrator;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.adsi38_sena.simgeplapp.Controlador.ComunicacionServidor;
import com.adsi38_sena.simgeplapp.Modelo.MenuActivity;
import com.adsi38_sena.simgeplapp.Modelo.SIMGEPLAPP;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class LoginActivity extends Activity {

    private SIMGEPLAPP simgeplapp;//INSTANCIA DE LA APLICACION

    private EditText txt_user, txt_pass;
    private Button btn_login;
    private ProgressDialog pDialog;

    ComunicacionServidor loggeo;
    String IP_Server="192.168.0.14";//IP DE NUESTRO PC
    String urlServidor ="http://"+IP_Server+"/Servidor_Simgeplapp/login.php";


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
        loggeo = new ComunicacionServidor();
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

    protected void loggError(){
        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(400);
    }


    public boolean loggearse(String username, String password) {
        int logstatus = -1;
        //boolean logged = false;

        ArrayList<NameValuePair> valoresDigitados= new ArrayList<NameValuePair>();
        valoresDigitados.add(new BasicNameValuePair("user", username));
        valoresDigitados.add(new BasicNameValuePair("pass", password));

        JSONArray jdata = loggeo.obtenerDatosServidor(valoresDigitados, urlServidor);

        //SystemClock.sleep(950);

        //si lo que obtuvimos no es null
        if (jdata!=null && jdata.length() > 0){
            JSONObject json_data; //creamos un objeto JSON
            try {
                json_data = jdata.getJSONObject(0); //leemos el primer segmento en nuestro caso el unico
                logstatus = json_data.getInt("logged");//accedemos al valor
                //logged = json_data.getBoolean("logged");
            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
            }
            //validamos el valor obtenido
            if (logstatus == 1){// [{"logstatus":"1"}]
                return true;
            }
            else{// [{"logstatus":"0"}]
                return false;
            }
        }else{	//json obtenido invalido verificar parte WEB.
            Log.e("JSON  ", "ERROR");
            return false;
        }
    }

/*		CLASE ASYNCTASK
 *
 * usaremos esta para poder mostrar el dialogo de progreso mientras enviamos y obtenemos los datos
 * podria hacerse lo mismo sin usar esto pero si el tiempo de respuesta es demasiado lo que podria ocurrir
 * si la conexion es lenta o el servidor tarda en responder la aplicacion sera inestable.
 * ademas observariamos el mensaje de que la app no responde.
 */

    protected class AsyncLogg extends AsyncTask<String, String, String> {

        String user,pass;

        protected void onPreExecute() {
            //para el progress dialog
            pDialog = new ProgressDialog(LoginActivity.this);
            pDialog.setMessage("Autenticando....");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }

        protected String doInBackground(String... params) {
            //obtnemos usr y pass
            user = params[0];
            pass = params[1];
            //enviamos y recibimos y analizamos los datos en segundo plano.
            if (loggearse(user, pass) == true){
                return "ok"; //login valido
            }else{
                return "err"; //login invalido
            }

        }
        /*protected Thread loggeo = new Thread(new Runnable() {
            @Override
            public void run() {

            }
        });*/

        /*Una vez terminado doInBackground segun lo que halla ocurrido
        pasamos a la sig. activity
        o mostramos error*/
        protected void onPostExecute(String result) {
            pDialog.dismiss();//ocultamos progess dialog.
            if (result.equals("ok")){
                Intent i=new Intent(LoginActivity.this, MenuActivity.class);
                i.putExtra("user", user);
                startActivity(i);
            }else{
                Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                vibrator.vibrate(400);
                Toast.makeText(getApplicationContext(), "no haz podido iniciar sesion", Toast.LENGTH_SHORT).show();
            }

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
