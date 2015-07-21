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

import com.adsi38_sena.simgeplapp.Controlador.ComunicacionServidor.GestionCargas;
import com.adsi38_sena.simgeplapp.Modelo.SIMGEPLAPP;
import com.adsi38_sena.simgeplapp.R;

import java.text.DecimalFormat;
import java.util.Random;

public class ActivityMonitoreo extends Activity {

    SIMGEPLAPP simgeplapp;

    protected AutoActualizacion autoAct;

    private final DecimalFormat decimalFormat = new DecimalFormat("#.#");

    //ServicioMonitoreo theService;

    protected TextView txv_TEMP, txv_PRES, txv_NIV;
    protected Button btnPrueba;

    private Messenger mensajero;

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

        autoAct = new AutoActualizacion();
        autoAct.execute();

        txv_TEMP = (TextView) findViewById(R.id.txv_lec_temp);
        txv_PRES = (TextView) findViewById(R.id.txv_lec_pres);
        txv_NIV = (TextView) findViewById(R.id.txv_lec_niv);
        btnPrueba = (Button) findViewById(R.id.btn_init);

        //enlace al servicio, metodo no usado. Consulta: servicios enlazados en android
        //bindService(new Intent(InicioSimgeplapp.this, ServicioMonitoreo.class), conexService, Context.BIND_AUTO_CREATE);//

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
}
        });

    }

    @Override
    protected void onStart(){
        super.onStart();
        //Intent intentServicio = new Intent(InicioSimgeplapp.this, ServicioMonitoreo.class);
        //startService(intentServicio);
    }

    //esta parte se ejecuta cuando el activity es opacado por otra aplicacion pero este aun no desaparece de la interfaz actual o
    //está a punto de ser lanzada a segundo plano, es el primer metodo que se ejecuta al abandonar el activity (ya sea con la flecha atras o abriendo otro activity)
    //Es el lugar adecuado para detener animaciones, música o almacenar los datos que estaban en edición.
    @Override
    protected void onPause(){
        super.onPause();
    }

    // Se llama cuando la actividad va a comenzar a interactuar con el usuario. Es un buen lugar para lanzar las animaciones y la música.
    @Override
    protected void onResume(){
        super.onResume();
    }

    //Cuando la actividad no es visible. El programador debe guardar el estado de la interfaz de usuario, preferencias, etc.
    @Override
    protected void onStop(){
        super.onStop();
    }

    //Indica que la actividad va a volver a ser representada después de haber pasado por onStop().
    @Override
    protected void onRestart(){
        super.onRestart();
    }

    //fin del ciclo de vida del activity, Se llama antes de que la actividad sea totalmente destruida. Por ejemplo, cuando el usuario pulsa el botón de volver o cuando se llama al método finish()
    @Override
    protected void onDestroy(){
        super.onDestroy();
    }


    //hilo que actualiza los TextView que muestran las variables. Consulta: AsyncTask
    private class AutoActualizacion extends AsyncTask<Double, Double, String> {

        private Double[] valores;
        private Double temporal = simgeplapp.TEMP;
        private Random random = new Random();
        /*valores_temporales[0] = simgeplapp.TEMP;
            valores_temporales[1] = simgeplapp.PRES;
            valores_temporales[2] = simgeplapp.NIV;*/
        private TextView[] txvs = {txv_TEMP, txv_PRES, txv_NIV};
        int aux;

        @Override
        protected void onPreExecute(){
            valores = new Double[3];
        }

        @Override
        protected String doInBackground(Double... params) {
            while (true){ // true = hara indefinido este ciclo simgeplapp.serviceOn
                try {
                    Thread.sleep(3000); // se pausara la ejecucion durante 3 segundos

                    if(temporal == simgeplapp.TEMP/* || valores_temporales[1] == simgeplapp.PRES ||
                            valores_temporales[2] == simgeplapp.NIV*/){

                        valores[0] = (random.nextDouble() * (178 - 19)) + 19;
                        valores[1] = (random.nextDouble() * (168 - 16)) + 16;
                        valores[2] = (random.nextDouble() * (166 - 18)) + 18;
                    }
                    else {
                        valores[0] = simgeplapp.TEMP;//obtengo los valores de las variables globales del monitoreo
                        valores[1] = simgeplapp.PRES;//que se redefinen en el servicio
                        valores[2] = simgeplapp.NIV;
                        temporal = valores[0];
                    }

                    //el siguiente metodo lo que hace es llamar al onProgressUpdate pasandole los valores con que operara
                    publishProgress(valores);

                } catch (Exception eh) {
                    return eh.toString();
                }
            }
        }

        @Override
        protected void onProgressUpdate(Double... values){//recibo los valores pasados en el "publishProgress"
            try {
                aux = random.nextInt(3);
                //txvs[2].setText("" + decimalFormat.format(values[2]));
                switch (aux){
                    case 0:
                        txv_TEMP.setText("" + decimalFormat.format(values[0]));
                        break;
                    case 1:
                        txv_PRES.setText("" + decimalFormat.format(values[1]));
                        break;
                    case 2:
                        txv_NIV.setText("" + decimalFormat.format(values[2]));
                        break;
                }
                /*txv_TEMP.setText("" + decimalFormat.format(values[0]));
                txv_PRES.setText("" + decimalFormat.format(values[1]));
                txv_NIV.setText("" + decimalFormat.format(values[2]));*/
            }catch (Exception eh){
                Toast.makeText(getBaseContext(), "monitoreo pubprog: "+eh.toString(), Toast.LENGTH_LONG).show();
            }
        }

        @Override
        protected void onPostExecute(String result) {
            //super.onPostExecute(result);
            try {
                Toast.makeText(getBaseContext(), result, Toast.LENGTH_LONG).show();
            }catch (Exception eh){
                Toast.makeText(getBaseContext(), "hilo monitoreo postEx: "+eh.toString(), Toast.LENGTH_LONG).show();
            }
        }

        @Override
        protected void onCancelled(){
        }
    }

    //////--------      METODOS DE ENLACE AL SERVICE EN SEGUNDO PLANO
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
