package com.adsi38_sena.simgeplapp.Controlador.ComunicacionServidor;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.os.Bundle;


//DIALOGOS => http://developer.android.com/guide/topics/ui/dialogs.html
public class FragmentoCargaServidor extends DialogFragment {

    String TAG_PROGRESS;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreateDialog(savedInstanceState);
        ProgressDialog dialogo_progreso = new ProgressDialog(getActivity(), ProgressDialog.STYLE_SPINNER);
        //dialogo_progreso.setTitle("Comunicandose al Servidor");
        dialogo_progreso.setMessage("Espera...");
        dialogo_progreso.setIndeterminate(true);
        dialogo_progreso.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        setCancelable(true);
        return dialogo_progreso;
    }

    @Override
    public void show(FragmentManager manager, String tag) {
        TAG_PROGRESS = tag;
        super.show(manager, tag);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


    public void cerrarCarga(FragmentManager frg_mng){
        FragmentoCargaServidor frag = (FragmentoCargaServidor)frg_mng.findFragmentByTag(TAG_PROGRESS);
        if(frag != null){
            frag.dismiss();
        }
    }

}
