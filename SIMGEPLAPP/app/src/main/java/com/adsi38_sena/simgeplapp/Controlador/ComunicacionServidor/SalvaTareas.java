package com.adsi38_sena.simgeplapp.Controlador.ComunicacionServidor;


import android.app.Activity;

import com.adsi38_sena.simgeplapp.Controlador.AsyncMonitor;

import java.lang.ref.WeakReference;
import java.util.WeakHashMap;

public class SalvaTareas {

    private WeakHashMap<String, WeakReference<AsyncUsers>> proceso_usuario =
            new WeakHashMap<String, WeakReference<AsyncUsers>>();

    private WeakHashMap<String, WeakReference<AsyncLoggin>> proceso_loggeo =
            new WeakHashMap<String, WeakReference<AsyncLoggin>>();


    private static SalvaTareas cargas;

    public static SalvaTareas obtenerInstancia(){
        if (cargas == null)
        {
            synchronized (SalvaTareas.class)
            {
            	if (cargas == null)
            	{
                    cargas = new SalvaTareas();
            	}
            }
        }
        return cargas;
    }


 //SECCION GESTION DE CARGA EN LOGGEO

    public void ejecutarLoggeo(String llave_proceso, AsyncLoggin asyncLogg, Activity activity){
        desAdjuntarProcesoLoggin(llave_proceso);
        proceso_loggeo.put(llave_proceso, new WeakReference<AsyncLoggin>(asyncLogg));
        if(activity != null){
            adjuntarProcesoLoggin(llave_proceso, activity);
        }

    }
    public AsyncLoggin obtenerProcsLoggeo(String llave_proceso){
        if(proceso_loggeo.get(llave_proceso) == null){
            return null;
        }
        else {
            return proceso_loggeo.get(llave_proceso).get();
        }
    }

    public void adjuntarProcesoLoggin(String llave_proceso, Activity activity){

        AsyncLoggin logging = obtenerProcsLoggeo(llave_proceso);
        if(logging != null){
            logging.setMyActy(activity);
        }
    }

    public void desAdjuntarProcesoLoggin(String llave_proceso){
        if (proceso_loggeo.containsKey(llave_proceso)
                && proceso_loggeo.get(llave_proceso) != null
                && proceso_loggeo.get(llave_proceso).get() != null)
        {
            proceso_loggeo.get(llave_proceso).get().desAdjuntar();
        }
    }

    public void removerProcesoLoggin(String llave_proceso){
        proceso_loggeo.remove(llave_proceso);
    }

 //--- FIN GESTION DE LOGGEO --- //



//  GESTION CARGAR PROCESOS USUARIOS
//

    public void procesarUsuario(String llave_proceso, AsyncUsers asynUser, Activity activity){
        desAdjuntarProcesoUsuario(llave_proceso);
        proceso_usuario.put(llave_proceso, new WeakReference<AsyncUsers>(asynUser));
        if(activity != null){
            adjuntarProcesoUsuario(llave_proceso, activity);
        }
    }
    public AsyncUsers obtenerProcsUsers(String llave_proceso){
        if(proceso_usuario.get(llave_proceso) == null){
            return null;
        }
        else {
            return proceso_usuario.get(llave_proceso).get();
        }
    }
    public void adjuntarProcesoUsuario(String llave_proceso, Activity activity){

        AsyncUsers carga_usuario = obtenerProcsUsers(llave_proceso);
        if(carga_usuario != null){
            carga_usuario.setActivity_raiz(activity);
        }
    }
    public void desAdjuntarProcesoUsuario(String llave_proceso){
        if (proceso_usuario.containsKey(llave_proceso)
                && proceso_usuario.get(llave_proceso) != null
                && proceso_usuario.get(llave_proceso).get() != null)
        {
            proceso_usuario.get(llave_proceso).get().desAdjuntar();
        }
    }
    public void removerProcesoUsuario(String llave_proceso){
        proceso_usuario.remove(llave_proceso);
    }


//MONITOREO
    WeakHashMap<String, WeakReference<AsyncMonitor>> sostenMonitor = new WeakHashMap<String, WeakReference<AsyncMonitor>>();

    public void iniciarMonitoreo(String llave_proceso, AsyncMonitor monitor, Activity activity){
        soltarHilo(llave_proceso);
        sostenMonitor.put(llave_proceso, new WeakReference<AsyncMonitor>(monitor));
        if(activity != null){
            atraparHilo(llave_proceso, activity);
        }
    }
    public AsyncMonitor obtenerInstHilo(String llave_proceso){
        if(sostenMonitor.get(llave_proceso) == null){
            return null;
        }
        else {
            return sostenMonitor.get(llave_proceso).get();
        }
    }
    public void atraparHilo(String llave_proceso, Activity activity){

        AsyncMonitor ub_monitor = obtenerInstHilo(llave_proceso);
        if (ub_monitor != null){
            ub_monitor.setMyActy(activity);
        }
    }
    public void soltarHilo(String llave_proceso){
        if (sostenMonitor.containsKey(llave_proceso)
                && sostenMonitor.get(llave_proceso) != null
                && sostenMonitor.get(llave_proceso).get() != null)
        {
            sostenMonitor.get(llave_proceso).get().soltarActivity();
        }
    }
    public void removerHilo(String llave_proceso){
        proceso_usuario.remove(llave_proceso);
    }

}
