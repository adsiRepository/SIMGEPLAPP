package com.adsi38_sena.simgeplapp.Modelo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import com.adsi38_sena.simgeplapp.Controlador.Monitoreo;
import com.adsi38_sena.simgeplapp.Controlador.ServicioMonitoreo;
import com.adsi38_sena.simgeplapp.R;


public class MenuActivity extends Activity {

    SIMGEPLAPP simgeplapp;

    private TextView lbl_user, lbl_sessState;
    private Button btn_monitoreo;
    private Switch swch_service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_simgeplapp);

        simgeplapp = (SIMGEPLAPP)getApplication();

        swch_service = (Switch)findViewById(R.id.switch_service);
        if(simgeplapp.serviceOn == true){
            swch_service.setChecked(true);
        }
        else {
            swch_service.setChecked(false);
        }

        lbl_user = (TextView)findViewById(R.id.lbl_user);
        lbl_sessState = (TextView)findViewById(R.id.lbl_session_state);

        lbl_user.setText(""+simgeplapp.session.user);

        btn_monitoreo = (Button)findViewById(R.id.btn_monitoreo);
        btn_monitoreo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MenuActivity.this, Monitoreo.class));
            }
        });
    }

    @Override
    protected void onStart(){
        super.onStart();
        startService(new Intent(MenuActivity.this, ServicioMonitoreo.class));
    }

    // Se llama cuando la actividad va a comenzar a interactuar con el usuario. Es un buen lugar para lanzar las animaciones y la música.
    @Override
    protected void onResume(){
        super.onResume();
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

    //Indica que la actividad va a volver a ser representada después de haber pasado por onStop().
    @Override
    protected void onRestart(){
        super.onRestart();
    }

    //fin del ciclo de vida del activity, Se llama antes de que la actividad sea totalmente destruida. Por ejemplo, cuando el usuario pulsa el botón de volver o cuando se llama al método finish()
    @Override
    protected void onDestroy(){
        super.onDestroy();
    }

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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
