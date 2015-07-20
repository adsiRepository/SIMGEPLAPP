package com.adsi38_sena.simgeplapp.Vistas;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;

import com.adsi38_sena.simgeplapp.R;

public class ActivityUsuarios extends Activity {

    EditText txt_nombre, txt_apes, txt_id, txt_tel, txt_mail, txt_pass;
    Spinner select_tipo_id;
    RadioButton rol_admin, rol_apz;//de aprendiz
    Button btn_guardar, btn_modificar, btn_buscar, btn_eliminar;
    final String[] tipos_id = {"Tarjeta de Identidad", "Cedula de Ciudadania", "Pasaporte"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuarios);

        txt_nombre = (EditText)findViewById(R.id.edt_nombres);
        txt_apes = (EditText)findViewById(R.id.edt_apes);
        txt_id = (EditText)findViewById(R.id.edt_id);
        txt_tel = (EditText)findViewById(R.id.edt_tel);
        txt_mail = (EditText)findViewById(R.id.edt_mail);
        txt_pass = (EditText)findViewById(R.id.edt_contrasena);

        select_tipo_id = (Spinner)findViewById(R.id.spin_tipo_id);
        ArrayAdapter<String> adp = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, tipos_id);
        select_tipo_id.setAdapter(adp);

        rol_admin  = (RadioButton)findViewById(R.id.radio_admin);
        rol_apz  = (RadioButton)findViewById(R.id.radio_aprendiz);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_gestion_usuarios, menu);
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
