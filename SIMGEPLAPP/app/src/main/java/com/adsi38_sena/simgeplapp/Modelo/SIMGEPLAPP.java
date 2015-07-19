package com.adsi38_sena.simgeplapp.Modelo;


import android.app.Application;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Configuration;
import android.os.Parcel;
import android.os.Parcelable;
import android.widget.Toast;

import com.adsi38_sena.simgeplapp.InicioSimgeplapp;

import java.util.List;

//esta clase como ven se extiende de la clase Application. La funcion de esta clase es sostener las variables globales de toda la aplicacion.
//Al ser extendida de la clase Application se puede instanciar la aplicacion en si como un objeto de esta clase.
//fuentes hay muchas, la busqueda google seria: variables globales en android.
//En cada elemento de la aplicacion (activitys, services, etc.) se instanciara un objeto de esta clase:
//SIMGEPLAPP simgeplapp = (SIMGEPLAPP)getApplication(); lo que obtendra la instancia actual de esta clase. La instancia de esta clase (SIMGEPLAPP)
//se define y se inicia al ejecutar por primera vez la aplicacion, lo que hacemos en cada componente es recoger esa instancia y obtener asi las variables globales.
public class SIMGEPLAPP extends Application{


    //ATRIBUTOS DE LA APLICACION
    //variables globales accesibles a todos los elementos pertenecientes a la aplicacion: activities, services, clases, etc.

    public static Double TEMP, PRES, NIV; // temperatura, presion y nivel.
                                            //variables de la aplicacion
    public static Session session;

    public static boolean serviceOn;//se define en el servicio, en el metodo que se ejecuta al crearlo

    public static boolean sessionAlive;

    //--fin declaracion de atributos--

    //METODOS DE LA APLICACION
    public SIMGEPLAPP(){
    }

    //PREFERENCIAS COMPARTIDAS: gestiona la memoria de la aplicacion para que guarde datos y configuraciones que podremos recuperar al reiniciar el dispositivo

    //ESTADOS DE LA APLICACION Y LAS ACTIVIDADES -> https://columna80.wordpress.com/2011/09/03/ciclo-de-vida-de-las-actividades-de-android-i/

    //Ciclo de Vida de la Aplicacion
    @Override
    public void onCreate(){
        super.onCreate();

        SharedPreferences preferencias = getSharedPreferences("mi_usuario", MODE_PRIVATE);
        String estado_sesion = preferencias.getString("onsesion", null);

        if(estado_sesion != null){
            sessionAlive = true;
            session = new Session();
            session.user = preferencias.getString("usuario", null);

            /*Intent h = new Intent(Intent.ACTION_MAIN, null);
            h.addCategory(Intent.CATEGORY_LAUNCHER);
            PackageManager pkm = getPackageManager();
            List<ResolveInfo> list_activitys = pkm.queryIntentActivities(h, 0);
            for (ResolveInfo r : list_activitys){
                String pkgName = r.activityInfo.packageName;
                String className = r.activityInfo.name;
                if(className == "com.adsi38_sena.simgeplapp.Modelo.MenuActivity"){
                    Intent primer_ejecucion = new Intent();
                    primer_ejecucion.setClassName(pkgName, className);
                    startActivity(primer_ejecucion);
                }
            }
            Toast.makeText(this, "tu madre simgeplapp", Toast.LENGTH_SHORT).show();
            //Intent primer_ejecucion = new Intent();
            //primer_ejecucion.setAction("com.adsi38_sena.simgeplapp.Modelo.MENU");
            //startActivity(primer_ejecucion);*/
        }
        else {
            sessionAlive = false;
        }

    }

    @Override
    public void onConfigurationChanged(Configuration newConf){
        super.onConfigurationChanged(newConf);
        //ejecutado si el sistema sufre cambios (como idioma) mientras la aplicacion se esta ejecutando
        //nota: el que no se este visualizando niguna pantalla de la aplicacion no quiere decir que esta se haya terminado
        //un service por ejemplo
    }

    @Override
    public void onLowMemory(){

    }

    public void onTerminate(){

    }

    //--

    //class de Sesion. Objeto que se crea al obtener un loggin exitoso e inicializa las variables concernientes a la sesion del usuario
    public static class Session implements Parcelable{// refs -> http://androcode.es/2012/12/trabajando-con-parcelables/
        public static String user;
        public static String password;

        public Session(){
            super();
            user = null;
            password = null;
        }

        public Session(Parcel parcel){
            super();
            user = parcel.readString();
            password = parcel.readString();
        }

        //auto implementada
        public static final Creator<Session> CREATOR = new Creator<Session>() {
            @Override
            public Session createFromParcel(Parcel in) {
                return new Session(in);
            }
            @Override
            public Session[] newArray(int size) {
                return new Session[size];
            }
        };

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(user);
            dest.writeString(password);
        }

        @Override
        public int describeContents() {
            return 0;
        }

    }

}
