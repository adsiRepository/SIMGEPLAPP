package com.adsi38_sena.simgeplapp.Modelo;


import android.app.Application;
import android.os.Parcel;
import android.os.Parcelable;

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

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(user);
            dest.writeString(password);
        }

        public static final Parcelable.Creator<Session> SESSION_CREATOR = new Parcelable.ClassLoaderCreator<Session>(){
            @Override
            public Session createFromParcel(Parcel source) {
                return null;
            }
            @Override
            public Session[] newArray(int size) {
                return new Session[0];
            }
            @Override
            public Session createFromParcel(Parcel source, ClassLoader loader) {
                return null;
            }
        };

        @Override
        public int describeContents() {
            return 0;
        }



    }

}
