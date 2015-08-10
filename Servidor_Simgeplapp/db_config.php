<?php

class DB {

    public static $conex;
    public $conn;
            
    function __construct() {
        $this->conn = new mysqli("localhost", "root", "", "simgeplapp"); //local
        if ($this->conn->connect_errno) {
        } else {
        }
    }
    
//funcion publica que devuelve el objeto instanciado de la conexion a la base de datos (creado)
    public function getConnex(){
        return $this->conn;
    }

    //staticos en php => http://objetivophp.com/?p=96
    public static function connect() {
        self::$conex = new mysqli("localhost", "root", "", "simgeplapp"); //local
        return self::$conex;
    }
}
