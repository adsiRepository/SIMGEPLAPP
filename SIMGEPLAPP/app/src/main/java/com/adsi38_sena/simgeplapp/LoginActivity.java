package com.adsi38_sena.simgeplapp;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.adsi38_sena.simgeplapp.Controlador.FragmentoLogin;
import com.adsi38_sena.simgeplapp.Modelo.SIMGEPLAPP;


public class LoginActivity extends Activity implements FragmentoLogin.Llamados_deActivity{

    private SIMGEPLAPP simgeplapp;//INSTANCIA DE LA APLICACION

    private EditText txt_user, txt_pass;
    private Button btn_login;
    private TextView txv_prog_loggin;

    //private ProgressDialog pDialog;

    public ProgressBar spinner_logg;

    FragmentoLogin fragmento_loggeo;
    String TAG_FRAGMENTO_LOGGEO = "Fragmento_Loggeo";

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

        txt_user = (EditText)findViewById(R.id.edt_user);
        txt_pass = (EditText)findViewById(R.id.edt_pass);
        btn_login = (Button)findViewById(R.id.btn_login);

        //PERSONALIZAR PROGRESSBAR => http://www.101apps.co.za/articles/android-s-progress-bars.html
        spinner_logg = (ProgressBar)findViewById(R.id.progressLogin);
        //spinner_logg.setVisibility(View.INVISIBLE);
        txv_prog_loggin = (TextView)findViewById(R.id.txv_prog_loggin);

        fragmento_loggeo = (FragmentoLogin)getFragmentManager().findFragmentByTag(TAG_FRAGMENTO_LOGGEO);

        if(fragmento_loggeo != null){
            if(fragmento_loggeo.logging.getStatus() == AsyncTask.Status.RUNNING){
                spinner_logg.setVisibility(View.VISIBLE);
                txv_prog_loggin.setVisibility(View.VISIBLE);
                btn_login.setEnabled(false);
            }
        }


        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String user = txt_user.getText().toString();
                String pass = txt_pass.getText().toString();

                if ((user.length() * pass.length()) > 0) {//verificamos si estan en blanco
                    //si pasamos esa validacion ejecutamos el asynctask pasando el usuario y clave como parametros
                    //  new AsyncLogg().execute(user, pass);

                    FragmentManager manager_de_fragmentos = getFragmentManager();
                    fragmento_loggeo = new FragmentoLogin();
                    Bundle bund = new Bundle();
                    bund.putString("usuario_digitado", user);
                    bund.putString("passw_digitado", pass);
                    fragmento_loggeo.setArguments(bund);
                    FragmentTransaction transaccion = manager_de_fragmentos.beginTransaction();
                    transaccion.add(fragmento_loggeo, TAG_FRAGMENTO_LOGGEO);
                    transaccion.commit();

                } else {
                    loggError();
                    Toast.makeText(getApplicationContext(), "digita completamente los campos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onResume(){
        super.onResume();
    }
    @Override
    protected void onPause(){
        super.onPause();
    }
    @Override
    protected void onStop(){
        super.onStop();

    }
    @Override
    protected void onDestroy(){
        super.onDestroy();

    }
    @Override
    protected void onRestart(){
        super.onRestart();

    }

    /*@Override
    public void onConfigurationChanged(Configuration newConf){
        super.onConfigurationChanged(newConf);
        setContentView(R.layout.activity_login);
    }*/

    //implementamos los metodos accedidos mediante el interface del fragmento (...implements Frag..gin.Llamados..vity,
    // esto nos permite acceder directamente a esos metodos(ejecutados en el fragmento).
    //es lo que hace el interface: me permite ejecutar esos metodos desde acá, es hacer el proceso aya y recibir resultados aca
    // (como una superposicion)
    @Override
    public void onPreExecute() {
        //para el progress dialog
        //pDialog = new ProgressDialog(LoginActivity.this);
        //pDialog.setMessage("Comprobando Base de Datos");
        txv_prog_loggin.setVisibility(View.VISIBLE);
        spinner_logg.setIndeterminate(true);
        //spinner_logg.setCancelable(false);
        spinner_logg.setVisibility(View.VISIBLE);
        btn_login.setEnabled(false);
    }

    @Override
    public void onProgressUpdate(String... values) {
        Toast.makeText(getApplicationContext(), values[0], Toast.LENGTH_LONG).show();
    }

    @Override
    public void onPostExecute() {
        //pDialog.dismiss();//ocultamos progess dialog.
        spinner_logg.setVisibility(View.INVISIBLE);
        txv_prog_loggin.setVisibility(View.INVISIBLE);
        btn_login.setEnabled(true);
        /*if (result == true){
            finish();
            startActivity(new Intent(LoginActivity.this, MenuActivity.class));
            Toast.makeText(getApplicationContext(), "Session Iniciada", Toast.LENGTH_LONG).show();
            // }
        } else {
            loggError();
            Toast.makeText(getApplicationContext(), "No se pudo iniciar sesion", Toast.LENGTH_SHORT).show();
        }*/
    }

    @Override
    public void onCancelled() {
        spinner_logg.setVisibility(View.INVISIBLE);
        txv_prog_loggin.setVisibility(View.INVISIBLE);
        btn_login.setEnabled(true);
        loggError();
        Toast.makeText(getApplicationContext(), "Tiempo de Espera Excedido", Toast.LENGTH_SHORT).show();
    }

    protected void loggError(){
        try {
            Vibrator vibrator = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
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
