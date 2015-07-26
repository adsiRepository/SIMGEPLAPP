package com.adsi38_sena.simgeplapp.Vistas;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.adsi38_sena.simgeplapp.Controlador.ComunicacionServidor.AsyncUsers;
import com.adsi38_sena.simgeplapp.Controlador.SalvaTareas;
import com.adsi38_sena.simgeplapp.Modelo.SIMGEPLAPP;
import com.adsi38_sena.simgeplapp.Modelo.Usuario;
import com.adsi38_sena.simgeplapp.R;

import java.util.ArrayList;

public class ActivityUsuarios extends Activity implements View.OnClickListener {

    EditText txt_nombre, txt_apes, txt_id, txt_tel, txt_mail, txt_nick, txt_pass;
    Spinner select_tipo_id;
    RadioGroup opcs_rol;
    //RadioButton rol_admin, rol_apz;//de aprendiz
    Button btn_guardar, btn_modificar, btn_buscar, btn_eliminar;

    final String[] tipos_id = {"Tarjeta de Identidad", "Cedula de Ciudadania", "Pasaporte"};
    String opc_rol_elegida;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuarios);

        txt_nombre = (EditText) findViewById(R.id.edt_nombres);
        txt_apes = (EditText) findViewById(R.id.edt_apes);
        txt_id = (EditText) findViewById(R.id.edt_id);
        txt_tel = (EditText) findViewById(R.id.edt_tel);
        txt_mail = (EditText) findViewById(R.id.edt_mail);
        txt_nick = (EditText) findViewById(R.id.edt_nick);
        txt_pass = (EditText) findViewById(R.id.edt_contrasena);

        select_tipo_id = (Spinner) findViewById(R.id.spin_tipo_id);
        ArrayAdapter<String> adp = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, tipos_id);
        select_tipo_id.setAdapter(adp);

        opcs_rol = (RadioGroup) findViewById(R.id.opcs_users_rol);
        opcs_rol.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radio_admin:
                        opc_rol_elegida = "Administrador";
                        Toast.makeText(getApplicationContext(), "Administrador", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.radio_aprendiz:
                        opc_rol_elegida = "Aprendiz";
                        Toast.makeText(getApplicationContext(), "Aprendiz", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });

        /*rol_admin  = (RadioButton)findViewById(R.id.radio_admin);
        rol_apz  = (RadioButton)findViewById(R.id.radio_aprendiz);*/

        btn_guardar = (Button) findViewById(R.id.btn_users_registrar);
        btn_guardar.setOnClickListener(this);//esto es para que respondan al metodo onClick de mas abajo
        btn_buscar = (Button) findViewById(R.id.btn_users_buscar);
        btn_buscar.setOnClickListener(this);
        btn_modificar = (Button) findViewById(R.id.btn_users_modif);
        btn_modificar.setOnClickListener(this);
        btn_eliminar = (Button) findViewById(R.id.btn_users_eliminar);
        btn_eliminar.setOnClickListener(this);

        //linea que me permite recapturar el hilo donde se despliega el dialogo emergente; aqui se sostiene el hilo y se adjunta cosntantemente al nuevo activity al cambiar configuaciones
        SalvaTareas.obtenerInstancia().adjuntarProcesoUsuario(SIMGEPLAPP.CargaSegura.LLAVE_PROCESO_CARGA_USERS, this);
        //--

    }

    //Gestion de Clicks(o tap) en la pantalla, no solo de los botones.
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_users_registrar:
            try {
                //Toast.makeText(getApplicationContext(), "Estas Registrando un User", Toast.LENGTH_LONG).show();

                if ((txt_nombre.getText().toString().length() > 0) && (txt_apes.getText().toString().length() > 0) &&
                        (txt_id.getText().toString().length() > 0)) {
                    Usuario nuevo_usuario = new Usuario();
                    nuevo_usuario.setNom(txt_nombre.getText().toString());
                    nuevo_usuario.setApe(txt_apes.getText().toString());
                    nuevo_usuario.setIde(txt_id.getText().toString());
                    nuevo_usuario.setTipo_ide(select_tipo_id.getSelectedItem().toString());
                    nuevo_usuario.setTel(txt_tel.getText().toString());
                    nuevo_usuario.setEmail(txt_mail.getText().toString());
                    nuevo_usuario.setNick(txt_nick.getText().toString());
                    nuevo_usuario.setPass(txt_pass.getText().toString());
                    nuevo_usuario.setRol(opc_rol_elegida);

                    ArrayList<Object> ordenes = new ArrayList<Object>();

                    ordenes.add(0, 1);//uno = codigo que indica q la orden es guardar nuevo usuario
                    ordenes.add(1, nuevo_usuario);

                    AsyncUsers registro = new AsyncUsers();
                    SalvaTareas.obtenerInstancia().procesarUsuario(SIMGEPLAPP.CargaSegura.LLAVE_PROCESO_CARGA_USERS,
                            registro, ActivityUsuarios.this);
                    registro.execute(ordenes);

                    //throw new Exception("instancia no nula");

                } else {
                    SIMGEPLAPP.vibrateError(ActivityUsuarios.this);
                    Toast.makeText(getApplicationContext(), "nombre, apellido e Id requeridos", Toast.LENGTH_LONG).show();
                }
            }catch (Exception eh){
                Toast.makeText(getApplicationContext(), "btn_reg: "+eh.toString(), Toast.LENGTH_LONG).show();
            }
                break;
            case R.id.btn_users_buscar:
                try {
                    //Toast.makeText(getApplicationContext(), "", Toast.LENGTH_LONG).show();

                    if ((txt_id.getText().toString().length() > 0)) {

                        ArrayList<Object> ordenes = new ArrayList<Object>();
                        String id_busc = txt_id.getText().toString();

                        ordenes.add(0, 2);//dos sera el codigo de la orden de busqueda
                        ordenes.add(1, id_busc);

                        //ArrayList<NameValuePair> h = nuevo_usuario.obtenerPaquete_Atributos();

                        //Toast.makeText(getApplicationContext(), SIMGEPLAPP.CargaSegura.LLAVE_PROCESO_CARGA_USERS, Toast.LENGTH_LONG).show();

                        AsyncUsers busqueda = new AsyncUsers();
                        SalvaTareas.obtenerInstancia().procesarUsuario(SIMGEPLAPP.CargaSegura.LLAVE_PROCESO_CARGA_USERS,
                                busqueda, ActivityUsuarios.this);
                        busqueda.execute(ordenes);

                        //throw new Exception("instancia no nula");

                    } else {
                        SIMGEPLAPP.vibrateError(ActivityUsuarios.this);
                        Toast.makeText(getApplicationContext(), "nombre, apellido e Id requeridos", Toast.LENGTH_LONG).show();
                    }
                }catch (Exception eh){
                    Toast.makeText(getApplicationContext(), "btn_reg: "+eh.toString(), Toast.LENGTH_LONG).show();
                }
        }
    }

    public void limpiarPantalla(){
        txt_nombre.setText("");
        txt_apes.setText("");
        txt_id.setText("");
        txt_tel.setText("");
        txt_mail.setText("");
        txt_nick.setText("");
        txt_pass.setText("");
        SIMGEPLAPP.vibrateExito(this);
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        SalvaTareas.obtenerInstancia().desAdjuntarProcesoUsuario(SIMGEPLAPP.CargaSegura.LLAVE_PROCESO_CARGA_USERS);
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
