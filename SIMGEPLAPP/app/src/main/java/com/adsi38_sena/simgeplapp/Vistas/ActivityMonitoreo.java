package com.adsi38_sena.simgeplapp.Vistas;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.adsi38_sena.simgeplapp.Modelo.SIMGEPLAPP;
import com.adsi38_sena.simgeplapp.R;

import java.text.DecimalFormat;
import java.util.Random;

public class ActivityMonitoreo extends Activity {

    SIMGEPLAPP simgeplapp;
    private final DecimalFormat decimalFormat = new DecimalFormat("#.#");
    private Double temporal;
    private static Double[] lecs_actuales;
    private Random random = new Random();
    int aux;
    private static boolean openFromService, disponerMenu;
    private static boolean monitorear;

    //ServicioMonitoreo theService;
    //private Messenger mensajero;

    protected TextView txv_TEMP, txv_PRES, txv_NIV;
    CharSequence[] estado_variables;

    //control de estado de la pantalla
    @Override
    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        estado_variables = new CharSequence[]{txv_TEMP.getText(), txv_PRES.getText(), txv_NIV.getText()};
        outState.putCharSequenceArray("variables", estado_variables);
    }
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);
        estado_variables = savedInstanceState.getCharSequenceArray("variables");
        txv_TEMP.setText(estado_variables[0]);
        txv_PRES.setText(estado_variables[1]);
        txv_NIV.setText(estado_variables[2]);
    }
    //-------

    //CUERPO DEL ACTIVITY
    //onCreate => se ejecuta cada vez que accedemos al activity, al girar la pantalla vuelve a ejecutarse porque esto es una transision que requiere volver a crear el activity; o al salir y regresar
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.adsi38_sena.simgeplapp.R.layout.activity_monitoreo);
        try {
            simgeplapp = (SIMGEPLAPP) getApplication();

            txv_TEMP = (TextView) findViewById(R.id.txv_lec_temp);
            txv_PRES = (TextView) findViewById(R.id.txv_lec_pres);
            txv_NIV = (TextView) findViewById(R.id.txv_lec_niv);

            temporal = simgeplapp.TEMP;//variable q me permitira realizar una simulacion de lecturas mas frecuente de en lo q realmente son recibidas del servidor

        } catch (Exception e) {
            Toast.makeText(getBaseContext(), "onCreate: " + e.toString(), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onStart(){
        super.onStart();
    }

    @Override//se utiliza este metodo para sincronizar el intent y pueda ser actualizada la interfaz en base a lo enviado por la notificacion
    protected void onNewIntent(Intent newIntent){
        super.onNewIntent(newIntent);
        setIntent(newIntent);
    }

    @Override
    protected void onResume(){
        super.onResume();
        try {
            //SalvaTareas.obtenerInstancia().atraparHilo(SIMGEPLAPP.LLAVE_PROCESO_MONITOREO, this);
            if (getIntent().getExtras() != null) {
                openFromService = getIntent().getBooleanExtra("en_espera", false);//el false es por defecto, por si no se recibe nada
                if (openFromService == true) {

                    NotificationManager managerNotificaciones = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                    managerNotificaciones.cancel(SIMGEPLAPP.NOTIFICACIONES.ID_NOTIFICACION_ALERTA);

                    /*lecs_actuales[0] = getIntent().getDoubleExtra("temperatura",3);
                    lecs_actuales[1] = getIntent().getDoubleExtra("presion", 3);
                    lecs_actuales[2] = getIntent().getDoubleExtra("nivel", 3);*/

                    txv_TEMP.setText("" + decimalFormat.format(getIntent().getDoubleExtra("temperatura", /*simgeplapp.TEMP*/3)));
                    txv_PRES.setText("" + decimalFormat.format(getIntent().getDoubleExtra("presion", /*simgeplapp.PRES*/2)));
                    txv_NIV.setText("" + decimalFormat.format(getIntent().getDoubleExtra("nivel", /*simgeplapp.NIV*/1)));
                    //Toast.makeText(getBaseContext(), "en_espera = true", Toast.LENGTH_LONG).show();

                    if (simgeplapp.serviceOn == true) {
                        monitorear = false;
                    }

      //PERSONALIZACION DE UN TOAST => https://amatellanes.wordpress.com/2013/08/09/android-notificaciones-en-android-parte-1-toasts/

                    if(simgeplapp.usuario_notificado == false) {

                        LayoutInflater inflador_deLayouts = getLayoutInflater();
                        View toast_personal = inflador_deLayouts.inflate(R.layout.toast_personalizado, (ViewGroup) findViewById(R.id.custoast_monitor_ok_notif));
                /*TextView textToast = (TextView) layout.findViewById(R.id.text_toast);
                textToast.setText("any text");*/
                        Toast toast = new Toast(ActivityMonitoreo.this);
                        toast.setDuration(Toast.LENGTH_LONG);
                        toast.setView(toast_personal);
                        toast.setGravity(Gravity.TOP, -10, 60);
                        toast.show();
                    }

                    simgeplapp.usuario_notificado = true;
                    disponerMenu = true;
                    mnutem_mail.setEnabled(true);
                    mnutem_llamada.setEnabled(true);

                }
            } else {
                if (simgeplapp.serviceOn == true) {
                    if (!MotorMonitor.isAlive()) {
                        monitorear = simgeplapp.serviceOn;
                        MotorMonitor.start();
                    }
                }
                disponerMenu = false;
            }
        }catch (Exception e){
            Toast.makeText(getBaseContext(), "onResume: " + e.toString(), Toast.LENGTH_LONG).show();
        }
    }

    //MENU
    private MenuItem mnutem_mail, mnutem_llamada;

    @Override
    public void invalidateOptionsMenu(){
        super.invalidateOptionsMenu();
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        try {
            //super.invalidateOptionsMenu();
            //
            if (disponerMenu == true) {
                menu.getItem(0).setEnabled(true);
                menu.getItem(1).setEnabled(true);
                //menu.clear();

            } else {
                menu.getItem(0).setEnabled(false);
                menu.getItem(1).setEnabled(false);
            }
        }catch (Exception e){
            Toast.makeText(getBaseContext(), "onPrepMenu: " + e.toString(), Toast.LENGTH_LONG).show();
        }
        //return super.onPrepareOptionsMenu(menu);
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it stream present.
        //getMenuInflater().inflate(com.adsi38_sena.simgeplapp.R.menu.menu_monitoreo, menu);

        MenuInflater infladorMenues = getMenuInflater();
        infladorMenues.inflate(com.adsi38_sena.simgeplapp.R.menu.menu_monitoreo, menu);
        mnutem_mail = menu.getItem(1);
        mnutem_llamada = menu.getItem(0);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        /*if (id == R.id.action_settings) {
            return true;
        }*/

        switch (id){
            case R.id.mnutem_enviar_correo:

                String[] destinatario_s = {/*correo de la planta*/"miguelon_91@misena.edu.co"};
                String[] copiados = {/*direcciones email de quienes recibiran una copia de este correo*/};
                String asunto = "Notificacion de Sobresalto en la Planta";
                String mensaje = "Alerta! he recibido un aviso en mi movil de parte de la planta, existe una anomalía, por favor Revisar Inmediatemente.";

                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                emailIntent.setData(Uri.parse("mailto:"));
                emailIntent.putExtra(Intent.EXTRA_EMAIL, destinatario_s);
                emailIntent.putExtra(Intent.EXTRA_CC, copiados);
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, asunto);
                emailIntent.putExtra(Intent.EXTRA_TEXT, mensaje);
                emailIntent.setType("message/rfc822");
                startActivity(Intent.createChooser(emailIntent, "Email "));

            break;

            case R.id.mnutem_llamar_central:
                try {
                    startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:3173567440")));
                } catch (Exception e) {
                    Toast.makeText(ActivityMonitoreo.this, "LamarCentral: " + e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                }
                break;

        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onPause(){
        super.onPause();
        //SalvaTareas.obtenerInstancia().soltarHilo(SIMGEPLAPP.LLAVE_PROCESO_MONITOREO);
    }

    @Override
    protected void onStop(){
        super.onStop();
    }

    @Override
    protected void onRestart(){
        super.onRestart();
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();

    }



    private Thread MotorMonitor = new Thread(new Runnable() {
        @Override
        public void run() {
            while (monitorear == true) {
                try {
                    aux = random.nextInt(3);//intervalo desde 0 hasta 3 sin tomarlo, es decir realmente hasta 2.
                    switch (aux) {
                        case 0:
                            txv_TEMP.post(new Runnable() {
                                @Override
                                public void run() {
                                    //txv_TEMP.setText("" + decimalFormat.format(valores[0]));
                                    txv_TEMP.setText("" + decimalFormat.format((random.nextDouble() * (112 - 19)) + 19));
                                }
                            });
                            break;
                        case 1:
                            txv_PRES.post(new Runnable() {
                                @Override
                                public void run() {
                                    //txv_PRES.setText("" + decimalFormat.format(valores[1]));
                                    txv_PRES.setText("" + decimalFormat.format((random.nextDouble() * (98 - 16)) + 16));
                                }
                            });
                            break;
                        case 2:
                            txv_NIV.post(new Runnable() {
                                @Override
                                public void run() {
                                    //txv_NIV.setText("" + decimalFormat.format(valores[2]));
                                    txv_NIV.setText("" + decimalFormat.format((random.nextDouble() * (100 - 18)) + 18));
                                }
                            });
                            break;
                    }
                    Thread.sleep(900);
                } catch (Exception eh) {
                    Toast.makeText(ActivityMonitoreo.this, "ActyMon-pubLec: " + eh.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                }
            }
        }
    });



    //////--------      METODOS DE ENLACE AL SERVICE EN SEGUNDO PLANO

    //private void comunicarseConServidor_Bind() {
            //String valorEnviado = txt_valorEnviado.getText().toString();
				/*Message msg = Message.obtain(null, new ServicioMonitoreo().ID_PETICION_SERVICIO);//este metodo obtain es para identificar el mensaje dentro del canal de estos.
				msg.replyTo = new Messenger(new RecibidorRespuestasServicio());//este metodo se ejecutara en el servicio (msg.replyTo), aqui le damos una instancia de RecibidorRespuestasServicio, por ende ejecutara el codigo alli escrito
				//Bundle bund = new Bundle();
				//bund.putString("datoTransferido", valorEnviado);
				//msg.setData(bund);
				try {
					mensajero.send(msg);//mensajero es el campo que contiene el IBinder recibido del ServiceConnection. Este contiene el manejador (la clase Handler) que se encuentra en el servicio (su manejador de mensajes)
										//asi que al pasarle un mensaje (.send(mensaje)) se esta ejecutando el codigo del Handler del servicio
				} catch (RemoteException e) {
					Toast.makeText(getBaseContext(), e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
					e.printStackTrace();
				}*/

                /*AsyncMonitor monitor = new AsyncMonitor();
                SalvaTareas.obtenerInstancia().iniciarMonitoreo(SIMGEPLAPP.LLAVE_PROCESO_MONITOREO, monitor, ActivityMonitoreo.this);
                monitor.execute();*/
     //   }

    //enlace al servicio, metodo no usado. Consulta: servicios enlazados en android
    //bindService(new Intent(InicioSimgeplapp.this, ServicioMonitoreo.class), conexService, Context.BIND_AUTO_CREATE);//
    //metodos para enlazar servicios en segundo plano para intercambiar datos, actualmente no usados debido al uso de variables globales
    /*private Messenger mensajero;
    //definicion del Objeto que enlaza al servicio
    private ServiceConnection conexService = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder servicio) {//este servicio es la interfaz recibida una vez se ha conectado al servicio
            //Enlace enlace = (Enlace)servicio;
            //theService = enlace.getService();
            mensajero = new Messenger(servicio);//aqui recibe la interfaz retornada en el metodo onBind del servicio
            if(mensajero != null){
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Toast.makeText(getBaseContext(), "Conexion Establecida Correctamente", Toast.LENGTH_LONG).show();
            }
        }
        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            //theService = null;
        }
    };*/
    /*private class RecibidorRespuestasServicio extends Handler {
        @Override
        public void handleMessage(Message msg){
            int CualMsg = msg.what;
            if(CualMsg == new ServicioMonitoreo().ID_RESPUESTA_PETICION) {
                //String respuesta = msg.getData().getString("msgBack");
                int[] valoresRecibidos = msg.getData().getIntArray("values");
                txv_TEMP.setText(String.valueOf(valoresRecibidos[0]));
                txv_PRES.setText(String.valueOf(valoresRecibidos[1]));
            }
        }
    }
    }*/
    //////--------




}
