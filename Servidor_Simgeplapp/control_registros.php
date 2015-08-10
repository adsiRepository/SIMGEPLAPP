<?php
include_once 'db_config.php';

class Report{
    
    private static $conex;
    
    function __construct(){
        $mng = new DB();
        self::$conex = $mng->getConnex();
    }
    
    public function errorMysql() {
        return self::$conex->error;
    }
    
    public static function guardarReporte($lectura, $factor){
        try{
            /*$instante = getdate();//funcion getdate(), devuelve un arreglo.
            $fecha = $instante['year']."-".$instante['mon']."-".$instante['mday'];
            $hora = $instante['hours'] .":". $instante['minutes'] .":". $instante['seconds'];*/
            
            //funcion time() -> devuelve un entero que es la cantidad de segundos transcurridos contados a partir de las 0 horas del primer dia del año 1970
            //la funcion time() devolvera un valor diferente respecto a la zona horaria del servidor en donde este alojado este archivo
            $time = time();
            $fecha = date("Y-m-d", $time);
            $hora = date("H:i:s", $time);
            
            self::$conex = DB::connect();
            $done = self::$conex->query("insert into incidencias (lectura, factor, fecha, hora) values ($lectura, '$factor', '$fecha', '$hora')");
            
            if($done){
                return "ok";
            }
            else {
                //return $this->conex->error;
                return self::$conex->error;
            }

        } catch (Exception $ex) {
            return $ex->getTraceAsString();
        }
    }
    
    public static function obtenerLecturas() {
        try {
            $lecturas[] = NULL;
            self::$conex = DB::connect();
            $result = self::$conex->query("select * from lecturas where 1");
            if ($result->num_rows > 0) {
                $lecturas[0] = "ok";
                while ($row = $result->fetch_array()) {
                    $lecturas[1] = doubleval($row[0]);
                    $lecturas[2] = doubleval($row[1]);
                    $lecturas[3] = doubleval($row[2]);
                }
            } else {
                $lecturas[0] = $this->conex->error;
            }
            return $lecturas;
        } catch (Exception $ex) {
            return $ex->getTraceAsString();
        }
    }
    
}


