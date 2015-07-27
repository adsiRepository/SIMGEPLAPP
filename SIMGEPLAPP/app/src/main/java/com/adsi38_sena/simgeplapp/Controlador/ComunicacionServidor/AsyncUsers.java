package com.adsi38_sena.simgeplapp.Controlador.ComunicacionServidor;


import android.app.Activity;
import android.os.AsyncTask;
import android.widget.Toast;

import com.adsi38_sena.simgeplapp.Controlador.SalvaTareas;
import com.adsi38_sena.simgeplapp.Modelo.SIMGEPLAPP;
import com.adsi38_sena.simgeplapp.Modelo.Usuario;
import com.adsi38_sena.simgeplapp.Vistas.ActivityUsuarios;

import java.util.ArrayList;


public class AsyncUsers extends AsyncTask<ArrayList<Object>, String, ArrayList<Object>>{

    SIMGEPLAPP simgeplapp;

    ActivityUsuarios activity_raiz;

    FragmentoCargaServidor frag_progress_usuarios;

    ComunicadorServidor server;

    Usuario usuario_en_proceso = null;
    //ArrayList<NameValuePair> atributos_usuario = null;

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
    protected ArrayList<Object> doInBackground(ArrayList<Object>... params) {

        ArrayList<Object> argumentos = params[0];
        int orden = (Integer)argumentos.get(0);
        ArrayList<Object> postExe = new ArrayList<Object>();
        postExe.add(0, null);
        postExe.add(1, null);
        postExe.add(2, null);

        try {
            switch (orden){
                case 1:
                    postExe.set(0, 1);
                    usuario_en_proceso = (Usuario)argumentos.get(1);
                    String[] datos_transaccion = server.registrarNuevoUsuario(usuario_en_proceso);
                    if (datos_transaccion != null) {
                        if (datos_transaccion[0] == "added") {
                            publishProgress("Usuario Añadido");
                            publishProgress(datos_transaccion[1]);//SystemClock.sleep(100);
                            publishProgress(datos_transaccion[2]);
                            postExe.set(1, "ok");
                        }
                        if (datos_transaccion[0] == "no_added") {
                            publishProgress("Usuario No Añadido");
                            postExe.set(1, "no");
                        }
                        if (datos_transaccion[0] == "failed_conex") {
                            publishProgress("Error de Conexion");
                            postExe.set(1, "no");
                        }
                    }
                    else {
                        throw new Exception("No se pudo recibir transaccion nuevo usuario");
                    }
                    break;
                case 2:
                    postExe.set(0, 2);
                    Object[] resultadoBusqueda = server.buscarUsuario((String)argumentos.get(1));
                    if(resultadoBusqueda != null){
                        String r = (String)resultadoBusqueda[0];
                        if(r == "finded"){
                            publishProgress("Usuario Encontrado");
                            Usuario user = (Usuario)resultadoBusqueda[1];
                            if(user != null) {
                                postExe.set(2, user);
                            }
                            else {
                                throw new Exception("No se recibieron los datos del usuario");
                            }
                        }
                        else {
                            postExe.set(1, r);
                        }
                    }
                    break;
            }

            return postExe;

        } catch (Exception e) {
            e.printStackTrace();
            postExe.set(1, "dobackg AsyUsrs: " + e.toString());
            return postExe;
        }
    }

    @Override
    protected void onPostExecute(ArrayList<Object> result){
        int decisionFinal;
        try {
            if (activity_raiz != null) {
                if (result != null) {
                    decisionFinal = (Integer)result.get(0);
                    switch (decisionFinal) {
                        case 1:
                            activity_raiz.limpiarPantalla();
                            Toast.makeText(activity_raiz.getApplicationContext(), "Usuario Registrado con Exito", Toast.LENGTH_LONG).show();
                            SIMGEPLAPP.vibrateExito(activity_raiz);
                            break;
                        case 2:
                            if (result.get(2) != null) {
                                activity_raiz.plasmarDatosEncotrados((Usuario) result.get(2));
                            }
                            else {
                                Toast.makeText(activity_raiz.getApplicationContext(), (String)result.get(1), Toast.LENGTH_LONG).show();
                            }
                    }
                }else{
                    SIMGEPLAPP.vibrateError(activity_raiz);
                    Toast.makeText(activity_raiz.getApplicationContext(), "postExe = null", Toast.LENGTH_LONG).show();
                }

                frag_progress_usuarios.cerrarCarga(activity_raiz.getFragmentManager());

                SalvaTareas.obtenerInstancia().removerProcesoUsuario(SIMGEPLAPP.CargaSegura.LLAVE_PROCESO_CARGA_USERS);

            } else {
                SIMGEPLAPP.vibrateError(activity_raiz.getApplicationContext());
                Toast.makeText(activity_raiz.getApplicationContext(), "AsyUsrs pstExe: Actividad perdida", Toast.LENGTH_LONG).show();
            }

        }catch(Exception eh){
            Toast.makeText(activity_raiz.getApplicationContext(), "pstExe"+eh.toString(), Toast.LENGTH_LONG).show();
        }
    }



    //##
    @Override
    protected void onProgressUpdate(String... values){
        try {
            if(values[0] != null) {
                if(activity_raiz != null) {
                    Toast.makeText(activity_raiz.getApplicationContext(), values[0], Toast.LENGTH_LONG).show();
                }
            }
            else {
                throw new Exception("publishProg = null");
            }

        }catch (Exception eh){
            if(activity_raiz != null) {
                Toast.makeText(activity_raiz.getApplicationContext(), "progUpdt: " + eh.toString(), Toast.LENGTH_LONG).show();
            }
        }
    }
    //##



    public void setActivity_raiz(Activity activity_raiz) {
        this.activity_raiz = (ActivityUsuarios)activity_raiz;
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
