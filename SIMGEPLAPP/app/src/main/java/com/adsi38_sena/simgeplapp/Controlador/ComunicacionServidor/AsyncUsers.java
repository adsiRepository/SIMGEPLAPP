package com.adsi38_sena.simgeplapp.Controlador.ComunicacionServidor;


import android.app.Activity;
import android.os.AsyncTask;
import android.widget.Toast;

import com.adsi38_sena.simgeplapp.Modelo.SIMGEPLAPP;
import com.adsi38_sena.simgeplapp.Modelo.Usuario;

import org.apache.http.NameValuePair;

import java.util.ArrayList;


public class AsyncUsers extends AsyncTask<Usuario, String, Boolean>{

    SIMGEPLAPP simgeplapp;

    Activity activity_raiz;

    FragmentoCargaServidor frag_progress_usuarios;

    ComunicadorServidor server;

    Usuario usuario_en_proceso = null;
    ArrayList<NameValuePair> atributos_usuario = null;

    @Override
    protected void onPreExecute(){
        super.onPreExecute();
        try {
            simgeplapp = (SIMGEPLAPP) activity_raiz.getApplication();
            server = new ComunicadorServidor();
            frag_progress_usuarios = new FragmentoCargaServidor();
            frag_progress_usuarios.show(activity_raiz.getFragmentManager(), SIMGEPLAPP.CargaSegura.TAG_PROGRESO_CARGA_USUARIOS);
        }catch (Exception eh){
            Toast.makeText(simgeplapp.getApplicationContext(), "pre exe: "+eh.toString(), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected Boolean doInBackground(Usuario... params) {
        try {
            usuario_en_proceso = params[0];
            if(usuario_en_proceso == null){
                throw new Exception("recuperacion obj user nulo");
            }
            atributos_usuario = usuario_en_proceso.obtenerPaquete_Atributos();
            if(atributos_usuario == null){
                throw new Exception("recuperacion attr nula");
            }
            for(int i = 0; i < atributos_usuario.size(); i++){
                Thread.sleep(2000);
                publishProgress(atributos_usuario.get(i).getValue());
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(activity_raiz.getApplicationContext(), "dobackg AsyUsrs: "+e.toString(), Toast.LENGTH_LONG).show();
            return false;
        }
    }

    //##
    @Override
    protected void onProgressUpdate(String... values){
        try {
            if(values[0] != null) {
                Toast.makeText(simgeplapp.getApplicationContext(), values[0], Toast.LENGTH_SHORT).show();
            }
            else {
                throw new Exception("publishProg = null");
            }
        }catch (Exception eh){
            Toast.makeText(activity_raiz.getApplicationContext(), "progUpdt: "+eh.toString(), Toast.LENGTH_LONG).show();
        }
    }
    //##

    @Override
    protected void onPostExecute(Boolean result){
        if(activity_raiz != null) {
            try {
                if (result == true) {
                    Toast.makeText(activity_raiz.getApplicationContext(), "fin, se ha conseguido!", Toast.LENGTH_LONG).show();
                } else {
                    SIMGEPLAPP.vibrateError(activity_raiz);
                }
                frag_progress_usuarios.cerrarCarga(activity_raiz.getFragmentManager());

                GestionCargas.obtenerInstancia().removerProcesoUsuario(SIMGEPLAPP.CargaSegura.LLAVE_PROCESO_CARGA_USERS);
            }
            catch(Exception eh){
                Toast.makeText(activity_raiz.getApplicationContext(), eh.toString(), Toast.LENGTH_LONG).show();
            }
        }
        else {
            SIMGEPLAPP.vibrateError(simgeplapp.getApplicationContext());
        }
    }

    public void setActivity_raiz(Activity activity_raiz) {
        this.activity_raiz = activity_raiz;
    }

    public void desAdjuntar() {
        this.activity_raiz = null;
    }

   /*

//CLASE ENCARGADA DE SOSTENER EL FRAGMENTO QUE MUESTRA EL CUADRO EMERGENTE DE PROGRESO
    public static class FragmentoGestionUsuarios extends DialogFragment {

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
        }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            super.onCreateDialog(savedInstanceState);
            ProgressDialog dialogo_progreso = new ProgressDialog(getActivity(), ProgressDialog.STYLE_SPINNER);
            dialogo_progreso.setTitle("Comunicandose al Servidor");
            dialogo_progreso.setMessage("Espera...");
            dialogo_progreso.setIndeterminate(true);
            dialogo_progreso.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            setCancelable(false);
            return dialogo_progreso;
        }

        @Override
        public void show(FragmentManager manager, String tag){
            super.show(manager, tag);
        }

        public void cerrarDialogoCarga(FragmentManager fragmentManager){
            FragmentoGestionUsuarios dialogo = (FragmentoGestionUsuarios)fragmentManager.findFragmentByTag(TAG_FRAG_PROGRESO_CARGA_USUARIOS);
            if(dialogo != null){
                dialogo.dismiss();
            }
        }
    }
*/


}
