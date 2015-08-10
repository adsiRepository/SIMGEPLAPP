package com.adsi38_sena.simgeplapp.Vistas;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.adsi38_sena.simgeplapp.Controlador.ServicioMonitoreo;
import com.adsi38_sena.simgeplapp.Modelo.SIMGEPLAPP;
import com.adsi38_sena.simgeplapp.R;


public class ActivityMenu extends Activity implements View.OnClickListener  {

    SIMGEPLAPP simgeplapp;

    private TextView lbl_user;
    private Button btn_monitoreo, btn_gestion_usuarios, btn_reportes;
    private Switch swch_service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        simgeplapp = (SIMGEPLAPP) getApplication();

        btn_monitoreo = (Button) findViewById(R.id.btn_monitoreo);
        btn_monitoreo.setOnClickListener(this);
        btn_gestion_usuarios = (Button) findViewById(R.id.btn_usuarios);
        btn_gestion_usuarios.setOnClickListener(this);
        btn_reportes = (Button) findViewById(R.id.btn_revision);
        btn_reportes.setOnClickListener(this);

        swch_service = (Switch) findViewById(R.id.switch_monitorear);

        lbl_user = (TextView) findViewById(R.id.txv_user_session);
        lbl_user.setText("" + simgeplapp.session.user);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_monitoreo:
                startActivity(new Intent(ActivityMenu.this, ActivityMonitoreo.class));
            break;
            case R.id.btn_usuarios:
                startActivity(new Intent(ActivityMenu.this, ActivityUsuarios.class));
            break;
            case R.id.btn_revision:
                startActivity(new Intent(ActivityMenu.this, ActivityReportes.class));
            break;
        }
    }

    @Override
    protected void onStart(){
        super.onStart();
    }

    // Se llama cuando la actividad va a comenzar a interactuar con el usuario. Es un buen lugar para lanzar las animaciones y la m�sica.
    @Override
    protected void onResume(){
        super.onResume();

        if (simgeplapp.serviceOn == true) {
            swch_service.post(new Runnable() {
                @Override
                public void run() {
                    swch_service.setChecked(true);
                }
            });
        } else {
            swch_service.post(new Runnable() {
                @Override
                public void run() {
                    swch_service.setChecked(false);
                }
            });
        }

        swch_service.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    if (SIMGEPLAPP.hayConexionInternet(ActivityMenu.this)) {
                        startService(new Intent(ActivityMenu.this, ServicioMonitoreo.class));
                        swch_service.post(new Runnable() {
                            @Override
                            public void run() {
                                swch_service.setText("Monitoreando");
                            }
                        });
                    } else {
                        Toast.makeText(getBaseContext(), "No hay Conexion a Internet en este Momento", Toast.LENGTH_LONG).show();
                        swch_service.post(new Runnable() {
                            @Override
                            public void run() {
                                swch_service.setChecked(false);
                            }
                        });
                    }
                } else {
                    if (simgeplapp.serviceOn == true) {
                        stopService(new Intent(ActivityMenu.this, ServicioMonitoreo.class));
                    }
                    swch_service.post(new Runnable() {
                        @Override
                        public void run() {
                            swch_service.setText("Monitorear");
                        }
                    });
                }
            }
        });

    }

    @Override
    protected void onPause(){
        super.onPause();
    }

    //Cuando la actividad no es visible. El programador debe guardar el estado de la interfaz de usuario, preferencias, etc.
    @Override
    protected void onStop(){
        super.onStop();
    }

    //Indica que la actividad va a volver a ser representada despu�s de haber pasado por onStop().
    @Override
    protected void onRestart(){
        super.onRestart();
    }

    //fin del ciclo de vida del activity, Se llama antes de que la actividad sea totalmente destruida. Por ejemplo, cuando el usuario pulsa el bot�n de volver o cuando se llama al m�todo finish()
    @Override
    protected void onDestroy(){
        super.onDestroy();
    }

    //CIERRE DE SESSION ==>
    /*@Override
    public boolean onKeyDown(int keyCode, KeyEvent event)  {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_menu__simgeplapp, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        /*if (id == R.id.action_settings) {
            return true;
        }*/

        switch (id){
            case R.id.terminar_sesion:
                AlertDialog.Builder construct_msg = new AlertDialog.Builder(this);
                construct_msg.setMessage("Deseas Salir de la Aplicacion?")
                        .setTitle("Simgeplapp")
                        .setPositiveButton("Salir",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        simgeplapp.sessionAlive = false;
                                        simgeplapp.session.user = null;
                                        simgeplapp.session.id = null;
                                        simgeplapp.session.rol = null;
                                        simgeplapp.session = null;

                                        SharedPreferences confUser = getSharedPreferences("mi_usuario", MODE_PRIVATE);
                                        SharedPreferences.Editor editor = confUser.edit();
                                        editor.putString("id", null);
                                        editor.putString("usuario", null);
                                        editor.putString("rol", null);
                                        editor.putString("onsesion", null);
                                        editor.commit();

                                        ActivityMenu.this.finish();
                                        stopService(new Intent(ActivityMenu.this, ServicioMonitoreo.class));
                                        startActivity(new Intent(ActivityMenu.this, ActivityLogin.class));
                                    }
                                })
                        .setNegativeButton("Cancelar",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                    }
                                });

                AlertDialog msg_emerg = construct_msg.create();
                msg_emerg.show();

            break;
        }

        return super.onOptionsItemSelected(item);
    }

}
