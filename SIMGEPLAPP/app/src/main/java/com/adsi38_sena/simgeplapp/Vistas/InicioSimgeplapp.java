package com.adsi38_sena.simgeplapp.Vistas;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;

import com.adsi38_sena.simgeplapp.Modelo.SIMGEPLAPP;
import com.adsi38_sena.simgeplapp.R;

public class InicioSimgeplapp extends Activity {

	SIMGEPLAPP simgeplapp;//aqui definimos un objeto de la clase global, lo mismo para cada componente

    protected ImageButton btn_entrar;

    //constructor
    public InicioSimgeplapp(){

    }


    //MANTENER EL ESTADO AL ROTAR LA PANTALLA O AL REGRESAR
    //fuentes => https://sekthdroid.wordpress.com/2012/10/04/mantener-los-datos-cuando-rotemos-la-pantalla-en-android/
    @Override
    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
    }
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);
    }

    //----

    //orden de ejecucion signado con *n (n = paso)
	//CUERPO DEL ACTIVITY
    //*1
    //onCreate => se ejecuta cada vez que accedemos al activity, al girar la pantalla vuelve a ejecutarse porque esto es una transision que requiere volver a crear el activity; o al salir y regresar
	//el activity no tiene cosntructor convencional, este es el reemplazo
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Toast.makeText(getBaseContext(), "onCreate", Toast.LENGTH_LONG).show();//toast que me indica el orden del ciclo de vida

        simgeplapp = (SIMGEPLAPP) getApplication();//obtengo la instancia de la aplicacion

        btn_entrar = (ImageButton) findViewById(R.id.btnimg_inicio);
        btn_entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(InicioSimgeplapp.this, ActivityLogin.class));
            }
        });

    }

    //*2
    //onStart => codigo que se ejecuta al iniciarse el activity, este se ejecuta al primer momento de abrir el activity.
    @Override
    protected void onStart(){
        super.onStart();
        //Intent intentServicio = new Intent(InicioSimgeplapp.this, ServicioMonitoreo.class);
        //startService(intentServicio);
        //Toast.makeText(getBaseContext(), "onStart()", Toast.LENGTH_LONG).show();
    }

    //*3
    //onResume => Se llama cuando la actividad va a comenzar a interactuar con el usuario. Es un buen lugar para lanzar las animaciones y la m�sica.
    //ultimo metodo que se ejecuta antes de estar completamente listo.
    @Override
    protected void onResume(){
        super.onResume();
        //Toast.makeText(getBaseContext(), "onResume", Toast.LENGTH_LONG).show();
    }
    //*1 -> *2 -> *3 orden ejecutado al entrar al activity, ya sea iniciandolo o regresando. Incluso al girar pantalla este orden vuelve a ejecutarse.

    //---

    //Abandono del activity
    //los siguientes se ejecutan justo en el orden en que estan escritos. Lo hacen siempre al perder totalmente el foco, es decir al salir

    //onPause => esta parte se ejecuta cuando el activity es opacado por otra aplicacion pero este aun no desaparece de la interfaz actual o no ha perdido totalmente el foco, es aun visible.
    //es la primera accion antes de ser lanzada a segundo plano, siempre se ejecuta en cualquier caso.
    //Es el lugar adecuado para detener animaciones, m�sica o almacenar los datos que estaban en edici�n.
    @Override
    protected void onPause(){
        super.onPause();
        //Toast.makeText(getBaseContext(), "onPause", Toast.LENGTH_LONG).show();
    }

    //onStop => se ejecuta cuando la actividad ya no es visible. El programador debe guardar el estado de la interfaz de usuario, preferencias, etc.
    @Override
    protected void onStop(){
        super.onStop();
        //Toast.makeText(getBaseContext(), "onStop()", Toast.LENGTH_LONG).show();
    }

    //fin del ciclo de vida del activity, Se llama antes de que la actividad sea totalmente destruida. Por ejemplo, cuando el usuario pulsa el bot�n de volver o cuando se llama al m�todo finish()
    @Override
    protected void onDestroy(){
        super.onDestroy();
        //Toast.makeText(getBaseContext(), "onDestroy", Toast.LENGTH_LONG).show();
    }

    //Indica que la actividad va a volver a ser representada despu�s de haber pasado por onStop().
    @Override
    protected void onRestart(){
        super.onRestart();
        //Toast.makeText(getBaseContext(), "onRestart", Toast.LENGTH_LONG).show();
    }

    @Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(com.adsi38_sena.simgeplapp.R.menu.main, menu);
		return true;
	}


    //METODOS

}
