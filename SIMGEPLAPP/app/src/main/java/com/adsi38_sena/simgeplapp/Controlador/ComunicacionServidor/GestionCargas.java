package com.adsi38_sena.simgeplapp.Controlador.ComunicacionServidor;


import android.app.Activity;

import java.lang.ref.WeakReference;
import java.util.WeakHashMap;

public class GestionCargas {


    public static WeakHashMap<String, WeakReference<AsyncLoggin>> loggeo_en_proceso =
            new WeakHashMap<String, WeakReference<AsyncLoggin>>();

    private static GestionCargas cargas;

    public static GestionCargas obtenerInstancia(){
        if (cargas == null)
        {
            synchronized (GestionCargas.class)
            {
            	if (cargas == null)
            	{
                    cargas = new GestionCargas();
            	}
            }
        }
        return cargas;
    }


    public void ejecutarLoggeo(String llave_proceso, AsyncLoggin asyncLogg, Activity activity){
        desAdjuntarProcesoLoggin(llave_proceso);
        loggeo_en_proceso.put(llave_proceso, new WeakReference<AsyncLoggin>(asyncLogg));
        if(activity != null){
            adjuntarProcesoLoggin(llave_proceso, activity);
        }

    }

    public void adjuntarProcesoLoggin(String llave_proceso, Activity activity){

        AsyncLoggin logging = obtenerProceso(llave_proceso);
        if(logging != null){
            logging.setMyActy(activity);
        }
    }

    public void desAdjuntarProcesoLoggin(String llave_proceso){
        if (loggeo_en_proceso.containsKey(llave_proceso)
                && loggeo_en_proceso.get(llave_proceso) != null
                && loggeo_en_proceso.get(llave_proceso).get() != null)
        {
            loggeo_en_proceso.get(llave_proceso).get().desAdjuntar();
        }
    }

    public void removerProcesoLoggin(String llave_proceso){
        loggeo_en_proceso.remove(llave_proceso);
    }

    public AsyncLoggin obtenerProceso(String llave_proceso){
        if(loggeo_en_proceso.get(llave_proceso) == null){
            return null;
        }
        else {
            return loggeo_en_proceso.get(llave_proceso).get();
        }
    }

}
