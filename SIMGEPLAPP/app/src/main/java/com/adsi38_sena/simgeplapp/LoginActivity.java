package com.adsi38_sena.simgeplapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.adsi38_sena.simgeplapp.Controlador.ComunicacionServidor.AsyncLoggin;
import com.adsi38_sena.simgeplapp.Controlador.ComunicacionServidor.GestionCargas;
import com.adsi38_sena.simgeplapp.Modelo.ActivityMenu;
import com.adsi38_sena.simgeplapp.Modelo.SIMGEPLAPP;


public class LoginActivity extends Activity /*implements AsyncLoggin.Llamados_deActivity*/{

    private SIMGEPLAPP simgeplapp;//INSTANCIA DE LA APLICACION

    private EditText txt_user, txt_pass;
    private ImageButton btn_login;

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
        btn_login = (ImageButton)findViewById(R.id.btn_loggin);

        //PERSONALIZAR PROGRESSBAR => http://www.101apps.co.za/articles/android-s-progress-bars.html

        GestionCargas.obtenerInstancia().adjuntarProcesoLoggin(simgeplapp.LLAVE_PROCESO_LOGIN, this);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String user = txt_user.getText().toString();
                String pass = txt_pass.getText().toString();

                if ((user.length() * pass.length()) > 0) {

                    AsyncLoggin loggin = new AsyncLoggin();
                    GestionCargas.obtenerInstancia().ejecutarLoggeo(simgeplapp.LLAVE_PROCESO_LOGIN, loggin, LoginActivity.this);
                    loggin.execute(user, pass);

                } else {
                    loggError();
                    Toast.makeText(getApplicationContext(), "digita completamente los campos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void onLogged(){
        finish();
        startActivity(new Intent(getApplicationContext(), ActivityMenu.class));
        Toast.makeText(getApplicationContext(), "Session Iniciada", Toast.LENGTH_LONG).show();
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
        GestionCargas.obtenerInstancia().desAdjuntarProcesoLoggin(simgeplapp.LLAVE_PROCESO_LOGIN);
    }
    @Override
    protected void onRestart(){
        super.onRestart();

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
