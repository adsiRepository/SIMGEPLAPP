<?php

class DB {

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
    public function getConnex() {
        return $this->conex;
    }

}


