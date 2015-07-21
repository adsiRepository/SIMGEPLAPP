package com.adsi38_sena.simgeplapp.Controlador.ComunicacionServidor;


import com.adsi38_sena.simgeplapp.Modelo.SIMGEPLAPP;
import com.adsi38_sena.simgeplapp.Modelo.Usuario;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ComunicadorServidor {

    //Atributos
    //private String IPv4;//se consigue la direccion mediante el comando "ipconfig" en cmd, el IPv4 de la conexion LAN

    private InputStream corriente_datos_entrantes = null;
    private String result = "";

    //Metodos

    //Construtor
    /*public ComunicadorServidor(String dirIP) {//constructor de la clase comunicacion al servidor
        this.IPv4 = dirIP;//supongo que es buen punto para definir la ip //esta se cambia cuando el servidor cambia de terminal
    }*/


//METODO DE LOGGEO
    public int intentoLoggeo(String user, String pass) throws JSONException, IOException {

        String url = SIMGEPLAPP.Comunicaciones.URL_SERVER + "login.php";

        int isInDB;//se puede loggear?, existe en base de datos?

        ArrayList<NameValuePair> valoresEnviados = new ArrayList<NameValuePair>();
        valoresEnviados.add(new BasicNameValuePair("user", user));
        valoresEnviados.add(new BasicNameValuePair("pass", pass));

        JSONArray jarray = capturarRespuestaServidor(url, valoresEnviados);//metodo que obtiene la respuesta del servidor
        if (jarray != null && jarray.length() > 0) {
            JSONObject jobj = jarray.getJSONObject(0);//viene con un array codificado en json que contiene en este caso un solo indice (un solo valor)
            isInDB = jobj.getInt("logged");//el json viene desde el php con un entero entre 0 y 1
            if (isInDB == 1) {//por convencion 1 es verdadero, si existe en bd me envia 1
                return 1;
            } else {
                return 0;//0 es que no existe en la base de datos
            }
        } else {
            return -1;//-1 significa que no obtuvo respuesta del servidor
        }

    }


//METODO DE REGISTRO DE USUARIOS

    //private String[] ordenes_usuario = {"registrar", "modificar", "borrar", "buscar"};

    public String[] registrarNuevoUsuario(Usuario nuevoUsuario) throws Exception/*IOException, JSONException*/ {

        String url = SIMGEPLAPP.Comunicaciones.URL_SERVER + "add_user.php";
        String[] results = new String[3];
        ArrayList<NameValuePair> datos_a_registrar = nuevoUsuario.obtenerPaquete_Atributos();

        if(datos_a_registrar == null){
            throw new Exception("traduccion name-values Usuario.class");
        }
        else {

            DefaultHttpClient cliente_web = new DefaultHttpClient();//HttpClient
            HttpParams params_cliente = cliente_web.getParams();
            HttpConnectionParams.setConnectionTimeout(params_cliente, 15000);
            HttpConnectionParams.getSoTimeout(params_cliente);
            HttpPost peticion_post = new HttpPost(url);
            peticion_post.setEntity(new UrlEncodedFormEntity(datos_a_registrar));//se codifica en forma de form
            HttpResponse respuesta_servidor = cliente_web.execute(peticion_post);
            HttpEntity entidad_respuesta = respuesta_servidor.getEntity();

            InputStream input = entidad_respuesta.getContent();

            BufferedReader reader = new BufferedReader(new InputStreamReader(input, "iso-8859-1"), 8);
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }

            input.close();
            String traduccion = sb.toString();

            JSONObject jsonObj = new JSONObject(traduccion);

            //JSONArray jarray = new JSONArray(traduccion);

            if (jsonObj != null && jsonObj.length() > 0) {
                boolean added = jsonObj.getBoolean("added");
                //int added = jsonObj.getInt("added");
                if (added == /*1*/ true) {
                    results[0] = "added";
                    JSONObject extras = jsonObj.getJSONObject("extras");
                    if (extras != null && extras.length() > 0) {
                        results[1] = extras.getString("r_pass");
                        results[2] = extras.getString("r_nick");
                    }
                    return results;
                } else {
                    results[0] = "no_added";
                    return results;
                }
            } else {
                results[0] = "failed_conex";
                return results;
            }

        }
    }



//FUENTE DE LA COMUNICACION CON EL SERVIDOR (metodos generales encargados de tal cosa)
    //peticion HTTP
    private void pedirRespuestaServidor(String url_servidor, ArrayList<NameValuePair> parametros) throws IOException {

        //instanciamos un objeto que se comportara como cliente para el servidor (el navegador chrome es un cliente por ejemplo)
        HttpClient cliente_web = new DefaultHttpClient();
        //añadimos parametros a la conexion
        HttpParams params_cliente = cliente_web.getParams();//parametros de la conexion
        HttpConnectionParams.setConnectionTimeout(params_cliente, 15000);//establezco que el tiempo de espera en la conexion sea de maximo 7 segundos
        HttpConnectionParams.getSoTimeout(params_cliente);
        //enviamos la solicitud de respuesta por un objeto que implementa el metodo post
        HttpPost peticion_post = new HttpPost(url_servidor);
        peticion_post.setEntity(new UrlEncodedFormEntity(parametros));//se codifica en forma de form
        //envio el objeto codificado en post
        HttpResponse respuesta_servidor = cliente_web.execute(peticion_post);
        HttpEntity entidad_respuesta = respuesta_servidor.getEntity();
        corriente_datos_entrantes = entidad_respuesta.getContent();
    }


    //metodo que obtiene en un JSonArray el arreglo confeccionado y enviado por el fichero php en el servidor
    private JSONArray capturarRespuestaServidor(String url_servidor, ArrayList<NameValuePair> parametros) throws IOException {
        pedirRespuestaServidor(url_servidor, parametros);//definira la variable "corriente_datos_entrantes"
        if (corriente_datos_entrantes != null) {//s� obtuvo una respuesta
            desglosarRespuesta();//basicamente convierte la corriente de informacion entrante en una lista despuesta en un String donde cada reglon es una pareja nombre-valor
            return obtenerArrayJSON();//se decodifica esta lista en un objeto arreglo JSon
        } else {
            return null;
        }
    }

    private JSONArray obtenerArrayJSON() {
        //parse json data
        try {
            JSONArray jArray = new JSONArray(result);
            return jArray;
        } catch (JSONException e) {
            return null;
        }
    }

    private void desglosarRespuesta() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(corriente_datos_entrantes, "iso-8859-1"), 8);
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line + "\n");
        }
        corriente_datos_entrantes.close();
        result = sb.toString();
    }

//FIN MOTOR DE CONEXION AL SERVIDOR


}
