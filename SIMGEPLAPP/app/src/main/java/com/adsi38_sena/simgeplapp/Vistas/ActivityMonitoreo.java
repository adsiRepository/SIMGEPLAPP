package com.adsi38_sena.simgeplapp.Vistas;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.adsi38_sena.simgeplapp.Controlador.AsyncMonitor;
import com.adsi38_sena.simgeplapp.Controlador.ComunicacionServidor.SalvaTareas;
import com.adsi38_sena.simgeplapp.Modelo.SIMGEPLAPP;
import com.adsi38_sena.simgeplapp.R;

import java.text.DecimalFormat;
import java.util.Random;

public class ActivityMonitoreo extends Activity {

    SIMGEPLAPP simgeplapp;
    private final DecimalFormat decimalFormat = new DecimalFormat("#.#");
    private Double temporal;
    private Double[] valores;
    private Random random = new Random();
    int aux;

    //ServicioMonitoreo theService;
    //private Messenger mensajero;

    protected TextView txv_TEMP, txv_PRES, txv_NIV;
    protected Button btnPrueba;
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

        simgeplapp = (SIMGEPLAPP)getApplication();

        SalvaTareas.obtenerInstancia().atraparHilo(SIMGEPLAPP.LLAVE_PROCESO_MONITOREO, this);

        txv_TEMP = (TextView) findViewById(R.id.txv_lec_temp);
        txv_PRES = (TextView) findViewById(R.id.txv_lec_pres);
        txv_NIV = (TextView) findViewById(R.id.txv_lec_niv);
        btnPrueba = (Button) findViewById(R.id.btn_init);

        temporal = simgeplapp.TEMP;

        btnPrueba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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

                AsyncMonitor monitor = new AsyncMonitor();
                SalvaTareas.obtenerInstancia().iniciarMonitoreo(SIMGEPLAPP.LLAVE_PROCESO_MONITOREO, monitor, ActivityMonitoreo.this);
                monitor.execute();
            }
        });

    }

    @Override
    protected void onStart(){
        super.onStart();
    }

    @Override
    protected void onPause(){
        super.onPause();
    }

    @Override
    protected void onResume(){
        super.onResume();
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
        SalvaTareas.obtenerInstancia().soltarHilo(SIMGEPLAPP.LLAVE_PROCESO_MONITOREO);
    }

    public void publicarLectura(){

        try {
            /*if(temporal == simgeplapp.TEMP){
                valores[0] = (random.nextDouble() * (178 - 19)) + 19;
                valores[1] = (random.nextDouble() * (168 - 16)) + 16;
                valores[2] = (random.nextDouble() * (166 - 18)) + 18;
            }
            else {
                valores[0] = simgeplapp.TEMP;//obtengo los valores de las variables globales del monitoreo
                valores[1] = simgeplapp.PRES;//que se redefinen en el servicio
                valores[2] = simgeplapp.NIV;
                temporal = valores[0];
            }*/

            aux = random.nextInt(3);
            switch (aux){
                case 0:
                    txv_TEMP.post(new Runnable() {
                        @Override
                        public void run() {
                            //txv_TEMP.setText("" + decimalFormat.format(valores[0]));
                            txv_TEMP.setText("" + decimalFormat.format((random.nextDouble() * (178 - 19)) + 19));
                        }
                    });
                    break;
                case 1:
                    txv_PRES.post(new Runnable() {
                        @Override
                        public void run() {
                            //txv_PRES.setText("" + decimalFormat.format(valores[1]));
                            txv_PRES.setText("" + decimalFormat.format((random.nextDouble() * (168 - 16)) + 16));
                        }
                    });
                    break;
                case 2:
                    txv_NIV.post(new Runnable() {
                        @Override
                        public void run() {
                            //txv_NIV.setText("" + decimalFormat.format(valores[2]));
                            txv_NIV.setText("" + decimalFormat.format((random.nextDouble() * (166 - 18)) + 18));
                        }
                    });
                    break;
            }

        } catch (Exception eh) {
            Toast.makeText(this, "ActyMon-pubLec: "+eh.getLocalizedMessage(), Toast.LENGTH_LONG).show();
        }
    }


    //////--------      METODOS DE ENLACE AL SERVICE EN SEGUNDO PLANO
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


    //MENU
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it stream present.
        getMenuInflater().inflate(com.adsi38_sena.simgeplapp.R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
