<?php

class ControlUsuario {

    public $conex;
    public $conected = FALSE;
    public $error;

    function __construct() {
        $this->conex = new mysqli("localhost", "root", "", "simgeplapp"); //local
        //$this->conex = new mysqli("localhost", "u855993248_ads38", "simgeplap", "u855993248_simge"); //hostinger
        if ($this->conex->connect_errno) {
            $error = $this->conex->error;
        } else {
            $conected = TRUE;
        }
    }

    //funcion publica que devuelve el objeto instanciado de la conexion a la base de datos (creado)
    public function getConnect() {
        return $this->conex;
    }

    public function loggin($nick, $pass){
        //loggeo
        $consulta = "select * from usuarios where nick='$nick' and pass='$pass'";
        $result = $this->conex->query($consulta);
        if ($result->num_rows > 0) {
            return TRUE;
        }
        else {
            return FALSE;
        }
    }

        public function registrarUsuario($id, $name, $ape, $tipo_id, $tel, $email, $pass, $rol, $nick) {

        $consulta = "insert into usuarios values ('$id','$name','$ape','$tipo_id','$tel','$email',"
                . "'$pass','$rol','$nick')";

        $result = $this->conex->query($consulta);
        if ($result == TRUE) {
            return TRUE;
        } else {
            return FALSE;
        }
        //return $consulta;
    }

    public function comprobarExistencia($query) {
        $results = $this->cnxDB->query($query);
        if ($results->num_rows > 0) {
            return TRUE;
        } else {
            return FALSE;
        }
    }

    public function desconectarDB() {
        $this->conex->close();
    }

}
/*
function getConexDB() {
    $con = new mysqli(HOST, USER, PASS, DATABASE);
    if ($con->connect_errno) {
        echo $con->error;
        return NULL;
    } else {
        return $con;
    }
}
*/