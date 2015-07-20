package com.adsi38_sena.simgeplapp.Modelo;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.Collection;

public class Usuario {

    public String nom, ape, ide, tipo_ide, tel, email, pass, rol;

    protected ArrayList<NameValuePair> atributos_deUsuario;

    public Usuario(){
        this.atributos_deUsuario = new ArrayList<NameValuePair>(8);
    }

    public ArrayList<NameValuePair> obtenerPaquete_Atributos() {
        if(atributos_deUsuario.isEmpty()){

            /*for (int i = 0; i < atributos_deUsuario.size(); i++){
                atributos_deUsuario.remove(i);
            }*/

            atributos_deUsuario.add(new BasicNameValuePair("nombre", nom));
            atributos_deUsuario.add(new BasicNameValuePair("apellido", ape));
            atributos_deUsuario.add(new BasicNameValuePair("id", ide));
            atributos_deUsuario.add(new BasicNameValuePair("tipo_id", tipo_ide));
            atributos_deUsuario.add(new BasicNameValuePair("telefono", tel));
            atributos_deUsuario.add(new BasicNameValuePair("email", email));
            atributos_deUsuario.add(new BasicNameValuePair("pass", pass));
            atributos_deUsuario.add(new BasicNameValuePair("rol", rol));
        }
        return atributos_deUsuario;
    }


    //setters y getters
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getApe() {
        return ape;
    }

    public void setApe(String ape) {
        this.ape = ape;
    }

    public String getIde() {
        return ide;
    }

    public void setIde(String ide) {
        this.ide = ide;
    }

    public String getTipo_ide() {
        return tipo_ide;
    }

    public void setTipo_ide(String tipo_ide) {
        this.tipo_ide = tipo_ide;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

}
