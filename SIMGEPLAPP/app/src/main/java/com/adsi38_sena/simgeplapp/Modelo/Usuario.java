package com.adsi38_sena.simgeplapp.Modelo;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;

public class Usuario {

    public String nom;
    public String ape;
    public String ide;
    public String ide_base;
    public String tipo_ide;
    public String tipo_ide_bd;
    public String tel;
    public String email;
    public String nick;
    public String pass;
    public String rol;

    public ArrayList<NameValuePair> atributos_deUsuario;

    public Usuario(){
        this.atributos_deUsuario = new ArrayList<NameValuePair>();
        this.ide_base = "0";
    }

    public ArrayList<NameValuePair> obtenerPaquete_Atributos() {
        if(atributos_deUsuario.isEmpty()){

            atributos_deUsuario.add(new BasicNameValuePair("nom", nom));
            atributos_deUsuario.add(new BasicNameValuePair("ape", ape));
            atributos_deUsuario.add(new BasicNameValuePair("id", ide));
            atributos_deUsuario.add(new BasicNameValuePair("tipo_id", getTipo_ide_bd()));
            atributos_deUsuario.add(new BasicNameValuePair("tel", tel));
            atributos_deUsuario.add(new BasicNameValuePair("email", email));
            atributos_deUsuario.add(new BasicNameValuePair("nick", nick));
            atributos_deUsuario.add(new BasicNameValuePair("pass", pass));
            atributos_deUsuario.add(new BasicNameValuePair("rol", rol));
            atributos_deUsuario.add(new BasicNameValuePair("id_base", ide_base));
        }
        else {
            atributos_deUsuario.set(0, new BasicNameValuePair("nom", nom));
            atributos_deUsuario.set(1, new BasicNameValuePair("ape", ape));
            atributos_deUsuario.set(2, new BasicNameValuePair("id", ide));
            atributos_deUsuario.set(3, new BasicNameValuePair("tipo_id", getTipo_ide_bd()));
            atributos_deUsuario.set(4, new BasicNameValuePair("tel", tel));
            atributos_deUsuario.set(5, new BasicNameValuePair("email", email));
            atributos_deUsuario.set(6, new BasicNameValuePair("nick", nick));
            atributos_deUsuario.set(7, new BasicNameValuePair("pass", pass));
            atributos_deUsuario.set(8, new BasicNameValuePair("rol", rol));
            atributos_deUsuario.set(9, new BasicNameValuePair("id_base", ide_base));
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

    public int getTipo_ide() {
        int t = 1;
        if(tipo_ide == "Tarjeta de Identidad") {
            t = 0;
        }
        if(tipo_ide == "Cedula de Ciudadania"){
            t = 1;
        }
        if(tipo_ide == "Pasaporte"){
            t = 2;
        }
        return t;
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

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getIde_base() {
        return ide_base;
    }

    public void setIde_base(String ide_base) {
        this.ide_base = ide_base;
    }

    public String getTipo_ide_bd() {
        if(tipo_ide == "Cedula de Ciudadania"){
            tipo_ide_bd = "CC";
        }
        if(tipo_ide == "Tarjeta de Identidad"){
            tipo_ide_bd = "TI";
        }
        if(tipo_ide == "Pasaporte"){
            tipo_ide_bd = "Pasaporte";
        }
        return tipo_ide_bd;
    }

    public void setTipo_ide_bd(String tipo_ide_bd) {
        this.tipo_ide_bd = tipo_ide_bd;
    }



}
