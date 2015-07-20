package com.adsi38_sena.simgeplapp.Controlador.ComunicacionServidor;


import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;

import com.adsi38_sena.simgeplapp.Modelo.Usuario;


public class AsyncGestionUsuarios extends AsyncTask<Usuario, String, Boolean>{

    private Activity activity_raiz;
    private FragmentoGestionUsuarios frag_progress_usuarios;
    public static String TAG_FRAG_PROGRESO_CARGA_USUARIOS = "cargando_usuarios";


    @Override
    protected void onPreExecute(){
        frag_progress_usuarios = new FragmentoGestionUsuarios();
        frag_progress_usuarios.show(activity_raiz.getFragmentManager(), TAG_FRAG_PROGRESO_CARGA_USUARIOS);
    }

    @Override
    protected Boolean doInBackground(Usuario... params) {
        try {
            Thread.sleep(7000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(String... values){

    }

    @Override
    protected void onPostExecute(Boolean result){
        if(activity_raiz != null){
            frag_progress_usuarios.cerrarDialogoCarga(activity_raiz.getFragmentManager());
        }
    }


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

    public void setActivity_raiz(Activity activity_raiz) {
        this.activity_raiz = activity_raiz;
    }

}
