package com.adsi38_sena.simgeplapp.Controlador;


import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ComunicacionServidor {

    InputStream stream = null;
    String result = "";

    public JSONArray obtenerDatosServidor(ArrayList<NameValuePair> parameters, String urlwebserver){
        //conecta via http y envia un post.
        conexionHttp(parameters, urlwebserver);
        if (stream !=null){//si obtuvo una respuesta
            desglosarRespuesta();
            return getjsonarray();
        }else{
            return null;
        }
    }


    //peticion HTTP
    private void conexionHttp(ArrayList<NameValuePair> parametros, String urlwebserver){
        try{
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(urlwebserver);
            httppost.setEntity(new UrlEncodedFormEntity(parametros));
            //ejecuto peticion enviando datos por POST
            HttpResponse response = httpclient.execute(httppost);
            HttpEntity entity = response.getEntity();
            stream = entity.getContent();

        }catch(Exception e){
            Log.e("log_tag", "Error in http connection "+e.toString());
        }
    }


    public void desglosarRespuesta(){
        //Convierte respuesta a String
        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream,"iso-8859-1"),8);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            stream.close();
            result=sb.toString();
            Log.e("desglosarRespuesta"," result= "+sb.toString());
        }catch(Exception e){
            Log.e("log_tag", "Error converting result "+e.toString());
        }
    }

    public JSONArray getjsonarray(){
        //parse json data
        try{
            JSONArray jArray = new JSONArray(result);
            return jArray;
        }
        catch(JSONException e){
            Log.e("log_tag", "Error parsing data "+e.toString());
            return null;
        }
    }

}
