package com.adsi38_sena.simgeplapp.Controlador.ComunicacionServidor;


import android.app.Activity;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.widget.Toast;

import com.adsi38_sena.simgeplapp.Modelo.SIMGEPLAPP;
import com.adsi38_sena.simgeplapp.Modelo.Usuario;


public class AsyncUsers extends AsyncTask<Usuario, String, String>{

    SIMGEPLAPP simgeplapp;

    Activity activity_raiz;

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
    protected String doInBackground(Usuario... params) {
        try {
            usuario_en_proceso = params[0];
            String paso = null;
            //atributos_usuario = usuario_en_proceso.obtenerPaquete_Atributos();

            String[] datos_transaccion = server.registrarNuevoUsuario(usuario_en_proceso);

            if (datos_transaccion != null) {

                if (datos_transaccion[0] == "added") {
                    //publishProgress("Usuario Añadido");
                    if (datos_transaccion[1] != null && datos_transaccion[1].length() > 0) {
                        publishProgress(datos_transaccion[1]);
                        SystemClock.sleep(300);
                        publishProgress(datos_transaccion[2]);
                    }
                    paso = "ok";
                }
                if (datos_transaccion[0] == "no_added") {
                    publishProgress("Usuario No Añadido");
                    paso = "no";
                }
                if (datos_transaccion[0] == "failed_conex") {
                    publishProgress("Error de Conexion");
                    paso = "no";
                }
            }
            else {
                throw new Exception("No se pudo recibir el objeto");
            }

            //for(int i = 0; i < atributos_usuario.size(); i++){
                //Thread.sleep(6000);
              //  publishProgress(atributos_usuario.get(i).getValue());
            //}
            //return true;
            return paso;

        } catch (Exception e) {
            e.printStackTrace();
            //Toast.makeText(activity_raiz.getApplicationContext(), "dobackg AsyUsrs: "+e.toString(), Toast.LENGTH_LONG).show();
            return "dobackg AsyUsrs: "+e.toString();
        }
    }

    //##
    @Override
    protected void onProgressUpdate(String... values){
        try {
            if(values[0] != null) {
                Toast.makeText(activity_raiz.getApplicationContext(), values[0], Toast.LENGTH_LONG).show();
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
    protected void onPostExecute(String result){
        try {
            if (activity_raiz != null) {
                if (result == "ok") {
                    Toast.makeText(activity_raiz.getApplicationContext(), "Usuario Registrado con Exito", Toast.LENGTH_LONG).show();
                }else{
                    SIMGEPLAPP.vibrateError(activity_raiz);
                    Toast.makeText(activity_raiz.getApplicationContext(), result, Toast.LENGTH_LONG).show();
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
