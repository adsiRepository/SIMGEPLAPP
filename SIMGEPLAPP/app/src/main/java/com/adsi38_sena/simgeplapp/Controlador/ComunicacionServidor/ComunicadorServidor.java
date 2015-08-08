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
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ComunicadorServidor {


//METODO DE LOGGEO
    public String[] intentoLoggeo(String user, String pass) throws Exception {

        String url = SIMGEPLAPP.Comunicaciones.URL_SERVER + "usuarios/login.php";

        String[] resp = new String[4];

        ArrayList<NameValuePair> valoresEnviados = new ArrayList<NameValuePair>();
        valoresEnviados.add(new BasicNameValuePair("user", user));
        valoresEnviados.add(new BasicNameValuePair("pass", pass));

        //JSONObject jarray = //metodo que obtiene la respuesta del servidor
        JSONObject jobj = obtenerObjetoJSON(url, valoresEnviados);
        if (jobj != null && jobj.length() > 0) {
            boolean log = jobj.getBoolean("logged");
            if (log == true) {
                resp[0] = "logged";
                JSONObject data = jobj.getJSONObject("data_sesion");
                if(data != null && data.length() > 0){
                    resp[1] = data.getString("id");
                    resp[2] = data.getString("nombre");
                    resp[3] = data.getString("rol");
                }
                else{
                    throw new Exception("No llegaron los Datos");
                }
            } else {
                resp[0] = jobj.getString("msg");//"El usuario no Existe en la Base de Datos";
            }
        } else {
            resp[0] = "No hubo respuesta del Servidor";
        }
        return resp;
    }


//METODO DE REGISTRO DE USUARIOS

    //private String[] ordenes_usuario = {"registrar", "modificar", "borrar", "buscar"};

    public String[] registrarNuevoUsuario(Usuario nuevoUsuario) throws Exception/*IOException, JSONException*/ {

        String url = SIMGEPLAPP.Comunicaciones.URL_SERVER + "usuarios/add_user.php";
        String[] results = new String[3];
        ArrayList<NameValuePair> datos_a_registrar = nuevoUsuario.obtenerPaquete_Atributos();

        if(datos_a_registrar == null){
            throw new Exception("traduccion name-values Usuario.class");
        }
        else {

            JSONObject jsonObj = obtenerObjetoJSON(url, datos_a_registrar);

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

    public Object[] buscarUsuario(String llave_busqueda) throws Exception/*IOException, JSONException*/ {

        String url = SIMGEPLAPP.Comunicaciones.URL_SERVER + "usuarios/buscar.php";
        Object[] results = new Object[2];
        ArrayList<NameValuePair> parametroBusqueda = new ArrayList<NameValuePair>();

        parametroBusqueda.add(new BasicNameValuePair("id", llave_busqueda));

        JSONObject jsonObj = obtenerObjetoJSON(url, parametroBusqueda);

        if (jsonObj != null && jsonObj.length() > 0) {
            boolean finded = jsonObj.getBoolean("find");
            //int added = jsonObj.getInt("added");
            if (finded == true) {
                results[0] = "finded";
                JSONObject datos = jsonObj.getJSONObject("user");
                if (datos != null && datos.length() > 0) {
                    Usuario usuarioHallado = new Usuario();
                    usuarioHallado.setIde(datos.getString("id"));
                    usuarioHallado.setNom(datos.getString("nombre"));
                    usuarioHallado.setApe(datos.getString("apes"));
                    usuarioHallado.setTipo_ide(datos.getString("tipo_id"));
                    usuarioHallado.setTel(datos.getString("telefono"));
                    usuarioHallado.setEmail(datos.getString("email"));
                    usuarioHallado.setPass(datos.getString("pass"));
                    usuarioHallado.setRol(datos.getString("rol"));
                    usuarioHallado.setNick(datos.getString("nick"));
                    results[1] = usuarioHallado;
                }
            } else {
                if(jsonObj.getString("msg") != null && jsonObj.getString("msg").length() > 0){
                    results[0] = jsonObj.getString("msg");
                }
                else {
                    results[0] = "No existe el Usuario en la Base de Datos";
                }
            }
        } else {
            results[0] = "No se obtuvo Respuesta del Servidor";
            //return results;
        }
        return results;
    }



    public String modificarUsuario(Usuario nuevosDatos, String llave) throws Exception {

        String url = SIMGEPLAPP.Comunicaciones.URL_SERVER + "usuarios/modif_user.php";

        ArrayList<NameValuePair> datos_a_cambiar = nuevosDatos.obtenerPaquete_Atributos();

        if(datos_a_cambiar == null){
            throw new Exception("traduccion name-values Usuario.class");
        }
        else {

            datos_a_cambiar.set(9, new BasicNameValuePair("id_base", llave));

            JSONObject jsonObj = obtenerObjetoJSON(url, datos_a_cambiar);

            if (jsonObj != null && jsonObj.length() > 0) {
                boolean modif = jsonObj.getBoolean("modif");
                if (modif == true) {
                    return "Modificado";
                } else {
                    String error = jsonObj.getString("error");
                    return error;
                }
            }
            else {
                return "No se obtuvo respuesta del Servidor";
            }
        }
    }


    public Object[] eliminarUsuario(String parametro) throws Exception {
        String url = SIMGEPLAPP.Comunicaciones.URL_SERVER + "usuarios/drop_user.php";

        ArrayList<NameValuePair> ref_eliminar = new ArrayList<NameValuePair>();
        ref_eliminar.add(new BasicNameValuePair("id_ref", parametro));

        Object[] valores_retornados = new Object[2];

        JSONObject jobj = obtenerObjetoJSON(url, ref_eliminar);
        if(jobj != null && jobj.length() > 0){
            boolean erase = jobj.getBoolean("erase");
            if(erase == true){
                valores_retornados[0] = true;
            }
            else {
                valores_retornados[0] = false;
                valores_retornados[1] = jobj.getString("msg");
            }
        }
        else {
            valores_retornados[0] = false;
            valores_retornados[1] = "No hubo Respuesta del Servidor";
        }
        return valores_retornados;
    }


    //OBTENCION DE VARIABLES DEL SERVIDOR->
   /* public void obtenerVariablesServidor(Context context){
        try {
            if (SIMGEPLAPP.hayConexionInternet(context) == true) {
                List<NameValuePair> peticion = Collections.unmodifiableList(
                        new ArrayList<NameValuePair>() {{
                            add(new BasicNameValuePair("peticion_lecturas", "ok"));
                        }});

                    JSONObject jsob_lecs = obtenerObjetoJSON(SIMGEPLAPP.Comunicaciones.URL_SERVER + "planta.php", (ArrayList<NameValuePair>) peticion);
                    if (jsob_lecs != null && jsob_lecs.length() > 0) {

                        JSONObject lecturas = jsob_lecs.getJSONObject("lecturas");

                        SIMGEPLAPP.TEMP = lecturas.getDouble("temperatura");
                        SIMGEPLAPP.PRES = lecturas.getDouble("presion");
                        SIMGEPLAPP.NIV = lecturas.getDouble("nivel");
                    }

            }
            else {
                Toast.makeText(context.getApplicationContext(), "No Existe Conexion en este Momento", Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(context.getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
        }
    }*/


//METODO DE LA COMUNICACION CON EL SERVIDOR (metodos generales encargados de tal cosa)
    //peticion HTTP
    public JSONObject obtenerObjetoJSON(String url_servidor, ArrayList<NameValuePair> parametros) throws IOException, JSONException {

        InputStream corriente_datos_entrantes;
        String result;

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

        BufferedReader reader = new BufferedReader(new InputStreamReader(corriente_datos_entrantes, "iso-8859-1"), 8);
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line + "\n");
        }

        corriente_datos_entrantes.close();
        result = sb.toString();
        entidad_respuesta.consumeContent();

        JSONObject jsonObj = new JSONObject(result);

        return jsonObj;
    }
    //FIN MOTOR DE CONEXION AL SERVIDOR


}
