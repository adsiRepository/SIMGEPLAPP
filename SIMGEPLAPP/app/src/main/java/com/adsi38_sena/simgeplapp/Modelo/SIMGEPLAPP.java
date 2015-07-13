package com.adsi38_sena.simgeplapp.Modelo;


import android.app.Application;
import android.os.Parcel;
import android.os.Parcelable;

//esta clase como ven se extiende de la clase Application. La funcion de esta clase es sostener las variables globales de toda la aplicacion.
//Al ser extendida de la clase Application se puede instanciar la aplicacion en si como un objeto de esta clase.
//fuentes hay muchas, la busqueda google seria: variables globales en android.
//En cada elemento de la aplicacion (activitys, services, etc.) se instanciara un objeto de esta clase:
//SIMGEPLAPP simgeplapp = (SIMGEPLAPP)getApplication(); lo que obtendra la instancia actual de esta clase. La instancia de esta clase (SIMGEPLAPP)
//se define y se inicia al ejecutar por primera vez la aplicacion, lo que hacemos en cada componente es recoger esa instancia y obtener asi las variables globales.
public class SIMGEPLAPP extends Application{

    public static Double TEMP, PRES, NIV; // temperatura, presion y nivel.
                                            //variables de la aplicacion
    public static Session session;

    public static boolean serviceOn;//se define en el servicio, en el metodo que se ejecuta al crearlo

    public static boolean sessionAlive = false;


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
