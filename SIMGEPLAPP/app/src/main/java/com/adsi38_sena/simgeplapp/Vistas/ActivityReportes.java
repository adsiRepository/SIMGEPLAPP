package com.adsi38_sena.simgeplapp.Vistas;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.adsi38_sena.simgeplapp.Controlador.AsyncReporte;
import com.adsi38_sena.simgeplapp.Controlador.ComunicacionServidor.ComunicadorServidor;
import com.adsi38_sena.simgeplapp.Controlador.SalvaTareas;
import com.adsi38_sena.simgeplapp.Modelo.SIMGEPLAPP;
import com.adsi38_sena.simgeplapp.R;


public class ActivityReportes extends Activity {

    private SIMGEPLAPP simgeplapp;
    private static TableLayout tablaDespliegue;
    private TableLayout tabla;

    //control de estado de la pantalla
    /*@Override
    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        estado_variables = new CharSequence[]{txv_TEMP.getText(), txv_PRES.getText(), txv_NIV.getText()};
        outState.putCharSequenceArray("variables", estado_variables);
        outState.putBundle();
    }
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);
        estado_variables = savedInstanceState.getCharSequenceArray("variables");
        txv_TEMP.setText(estado_variables[0]);
        txv_PRES.setText(estado_variables[1]);
        txv_NIV.setText(estado_variables[2]);
    }*/
    //-------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reportes);

        simgeplapp = (SIMGEPLAPP)getApplication();

        tablaDespliegue = (TableLayout)findViewById(R.id.tabla_reportes);
    }

    @Override
    protected void onResume(){
        super.onResume();
        SalvaTareas.obtenerInstancia().adjuntarProcesoReporte(SIMGEPLAPP.CargaSegura.LLAVE_PROCESO_REPORTE, this);
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        simgeplapp = null;
        SalvaTareas.obtenerInstancia().desadjuntarProcesoReporte(SIMGEPLAPP.CargaSegura.LLAVE_PROCESO_REPORTE);
    }

    public void plasmarTabla(String[] fila_reporte) {
        //int i = 1;
        //String[] k = {"abc","dfg","hij","klm","nop","qrs","tuv","wx","y","z","$"};
        //while (i <= 10) {

        TableRow fila = (TableRow) LayoutInflater.from(this).inflate(R.layout.fila_tabla_reportes, null);
        ((TextView) fila.findViewById(R.id.txv_report_lec)).setText(fila_reporte[0]);
        if(fila_reporte[1].compareTo("temperatura") == 0){
            fila.setBackgroundColor(Color.rgb(255, 218, 38));
        }
        if(fila_reporte[1].compareTo("presion") == 0){
            fila.setBackgroundColor(Color.argb(186, 255, 126, 72));
        }
        if(fila_reporte[1].compareTo("nivel") == 0){
            fila.setBackgroundColor(Color.rgb(61, 197, 255));
        }
        ((TextView) fila.findViewById(R.id.txv_report_factor)).setText(fila_reporte[1]);
        ((TextView) fila.findViewById(R.id.txv_report_fecha)).setText(fila_reporte[2]);
        ((TextView) fila.findViewById(R.id.txv_report_hora)).setText(fila_reporte[3]);
        tablaDespliegue.addView(fila);
        //   i++;
        //}
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_reportes, menu);
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
            case R.id.mnu_report_desplegar_info:
                try {
                    AsyncReporte reporte = new AsyncReporte();
                    SalvaTareas.obtenerInstancia().generarReporte(SIMGEPLAPP.CargaSegura.LLAVE_PROCESO_REPORTE, reporte, ActivityReportes.this);
                    reporte.execute();

                }catch (Exception e){
                    Toast.makeText(getApplicationContext(), "MnuItm Slc: " + e.toString(), Toast.LENGTH_LONG).show();
                }
            break;
        }

        return super.onOptionsItemSelected(item);
    }

}
