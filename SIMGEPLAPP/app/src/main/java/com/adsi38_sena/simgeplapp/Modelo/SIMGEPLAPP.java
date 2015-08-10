package com.adsi38_sena.simgeplapp.Modelo;


import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemClock;
import android.os.Vibrator;
import android.widget.Toast;

//esta clase como ven se extiende de la clase Application. La funcion de esta clase es sostener las variables globales de toda la aplicacion.
//Al ser extendida de la clase Application se puede instanciar la aplicacion en si como un objeto de esta clase.
//fuentes hay muchas, la busqueda google seria: variables globales en android.
//En cada elemento de la aplicacion (activitys, services, etc.) se instanciara un objeto de esta clase:
//SIMGEPLAPP simgeplapp = (SIMGEPLAPP)getApplication(); lo que obtendra la instancia actual de esta clase. La instancia de esta clase (SIMGEPLAPP)
//se define y se inicia al ejecutar por primera vez la aplicacion, lo que hacemos en cada componente es recoger esa instancia y obtener asi las variables globales.
public class SIMGEPLAPP extends Application{

    //http://www.elandroidelibre.com/2015/07/errores-habituales-desarrollador-android.html

    //ATRIBUTOS DE LA APLICACION
    //variables globales accesibles a todos los elementos pertenecientes a la aplicacion: activities, services, clases, etc.

    public static double TEMP = 0.0, PRES = 0.0, NIV = 0.0; // temperatura, presion y nivel.
                                            //variables de la aplicacion
    public static Session session;

    public static boolean serviceOn;//se define en el servicio, en el metodo que se ejecuta al crearlo

    public static boolean sessionAlive;

    public static boolean llamada_mail_habilitados = false;

    public static boolean monitoreoAbierto = false;

    static ConnectivityManager manager_deConexiones;
    static NetworkInfo net_info;

    public static boolean hayConexionInternet(Context contexto){

        manager_deConexiones = (ConnectivityManager)contexto.getSystemService(Context.CONNECTIVITY_SERVICE);
        net_info = manager_deConexiones.getActiveNetworkInfo();
        /*//saber el estado del wifi -> http://stackoverflow.com/questions/3841317/how-to-see-if-wifi-is-connected-in-android
        WifiManager wifi_mng = (WifiManager)getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = wifi_mng.getConnectionInfo();
        SupplicantState suplicante = info.getSupplicantState();*/

        if (net_info != null && net_info.isConnected()){
            return true;
        }
        else {
            return false;

        }
        /*ConnectivityManager connectivity = (ConnectivityManager)getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo[] info = connectivity.getAllNetworkInfo();
        for (int i = 0; i < info.length; i++){
            if (info[i].getState() == NetworkInfo.State.CONNECTED);
        }*/


    }


    public static class Comunicaciones{
        //SERVIDOR LOCAL (mi pc)
        public static String dirIP_server = "192.168.0.14";
        //public static String URL_SERVER_USERS = "http://" + dirIP_server + "/REPOSITORIO__Simgeplapp/Servidor_Simgeplapp/";
        //SERVIDOR REMOTO (hostinger)
        public static String URL_SERVER_USERS = "http://adsi38.esy.es/Servidor_Simgeplapp/";

        public static String URL_SERVER_LECS = "http://" + dirIP_server + "/REPOSITORIO__Simgeplapp/Servidor_Simgeplapp/";

    }

    public static class CargaSegura {

        public static final String TAG_PROGRESO_LOGGIN = "fragmento_loggin";

        public static final String LLAVE_PROCESO_LOGIN = "proceso_loggeo";

        public static final String TAG_PROGRESO_CARGA_USUARIOS = "carga_usuarios";

        public static final String LLAVE_PROCESO_CARGA_USERS = "cargando_usuario";
    }

    public static class NOTIFICACIONES{
        public static final int ID_NOTIFICACION_ALERTA = 5;
        public static final int ID_NOTIFICACION_PERDIDA_CONEXION = 6;
    }

    public static final String LLAVE_PROCESO_MONITOREO = "monitoreando";

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
        String estado_sesion = preferencias.getString("onsesion", null);//null valor por defecto

        if(estado_sesion != null){
            sessionAlive = true;
            session = new Session();
            session.id = preferencias.getString("id", null);
            session.user = preferencias.getString("usuario", null);
            session.rol = preferencias.getString("rol", null);

            /*Intent h = new Intent(Intent.ACTION_MAIN, null);
            h.addCategory(Intent.CATEGORY_LAUNCHER);
            PackageManager pkm = getPackageManager();
            List<ResolveInfo> list_activitys = pkm.queryIntentActivities(h, 0);
            for (ResolveInfo r : list_activitys){
                String pkgName = r.activityInfo.packageName;
                String className = r.activityInfo.name;
                if(className == "com.adsi38_sena.simgeplapp.Vistas.ActivityMenu"){
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

    @Override
    public void onTerminate(){

    }


    public static void vibrateError(Context context) {
        long[] patron = {0, 400, 230, 400};
        Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(patron, -1);
    }
    public static void vibrateExito(Context context) {
        //long[] patron de vibracion -> retraso inicial, vibra, duerme, vibra, duerme, vibra, duerme, ....
        long[] patron = {0, 500, 200, 500, 200, 900};
        Vibrator vibrate = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        vibrate.vibrate(patron, -1);//indice(repeticion) = -1 -> solo se ejecutara una vez, 0 indefinido
    }

    //--

    //class de Sesion. Objeto que se crea al obtener un intentoLoggeo exitoso e inicializa las variables concernientes a la sesion del usuario
    public static class Session implements Parcelable{// refs -> http://androcode.es/2012/12/trabajando-con-parcelables/
        public static String user;
        public static String password;
        public static String id;
        public static String rol;

        public Session(){
            super();
            user = null;
            password = null;
            id = null;
            rol = null;
        }

        public Session(Parcel parcel){
            super();
            user = parcel.readString();
            password = parcel.readString();
            id = parcel.readString();
            rol = parcel.readString();
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
            dest.writeString(id);
            dest.writeString(rol);
        }

        @Override
        public int describeContents() {
            return 0;
        }

    }

}
