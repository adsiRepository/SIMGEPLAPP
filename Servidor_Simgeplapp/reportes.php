<?php
include_once 'db_config.php';

if (isset($_REQUEST["generar_reporte"])) {

    $json_resp = array();
    //$json_resp["hay_datos"] = null;

    try {
        $conn = DB::connect();
        $result = $conn->query("select * from incidencias");
        if ($result->num_rows > 0) {
            $json_resp["hay_datos"] = true;
            $i = 0;
            while ($fila = $result->fetch_array()) {
                $json_resp["datos"][$i]["lec"] = doubleval($fila[1]);
                $json_resp["datos"][$i]["factor"] = "$fila[2]";
                $json_resp["datos"][$i]["fecha"] = "$fila[3]";
                $json_resp["datos"][$i]["hora"] = "$fila[4]";
                $i++;
            }
        } else {
            $json_resp["hay_datos"] = false;
            $json_resp["error"] = $conn->error;
            echo $conn->error;
        }
        //return $datos;
        //return $result;
        $result->free();
        $conn->close();
        echo json_encode($json_resp);
        
    } catch (Exception $e) {
        echo $e->getTraceAsString();
        $json_resp["error"] = $e->getTraceAsString();
    }

}    

//exit();

?>

<!--<!DOCTYPE html>
<html>
    <head></head>
    <body>
        <p id="display"></p>
        <br><br>
        <div id="pantalla">
<php
//hora-fecha php -> http://www.aprenderaprogramar.com/index.php?option=com_content&view=article&id=855:funciones-php-de-fecha-hora-tiempo-time-date-formato-de-fecha-mktime-gmmkitme-ejemplo-cu00830b&catid=70:tutorial-basico-programador-web-php-desde-cero&Itemid=193
//$time = time();// - 25200;
//echo date("Y-m-d H:i:s", $time);//"", 
//echo '<br>';
//echo date("H:i:s", $time);
//funcion getdate() -> http://www.homeandlearn.co.uk/php/php11p3.html
//http://www.plurasin.com/2014/04/funciones-de-fecha-y-hora-en-php.html
//$hoy = getdate();
//print_r($hoy);
//echo $hoy['mday']."-".$hoy['mon']."-".$hoy['year']."<br>";
//echo $hoy['hours'] .":". $hoy['minutes'] .":". $hoy['seconds'];
?>
        </div>

        <form method="post" action="planta.php">
            
            <input type="submit" value="action"/>
            
        </form>

    </body>
</html>-->

