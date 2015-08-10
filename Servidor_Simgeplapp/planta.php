<?php
include_once 'control_registros.php';

//if (isset($_REQUEST["peticion_lecturas"])) {

    $lecs = Report::obtenerLecturas();
    
    if ($lecs != NULL) {
        if ($lecs[0] === "ok") {
            $lecTemperatura = $lecs[1]; 
            $lecPresion = $lecs[2]; 
            $lecNivel = $lecs[3];

            if ( ($lecTemperatura > 120 || $lecTemperatura < 40) || ($lecPresion > 80 || $lecPresion < 30) ||
                    ($lecNivel > 80 || $lecNivel < 25) ) {

                $json_resp["alarma"] = TRUE;
                //$json_resp["factor"] = NULL;

                /*if ($lecTemperatura > 120 || $lecTemperatura < 40) {
                    $resp = Report::guardarReporte($lecTemperatura, "temperatura");
                    $json_resp["factor"]["temp"] = "affected";
                    if ($resp != "ok") {
                        $json_resp["error"] = $resp;
                    }
                }

                if ($lecPresion > 90 || $lecPresion < 20) {
                    $resp = Report::guardarReporte($lecPresion, "presion");
                    $json_resp["factor"]["pres"] = "affected";
                    if ($resp != "ok") {
                        $json_resp["error"] = $resp;
                    }
                }

                if ($lecNivel > 41 || $lecNivel < 10) {
                    $resp = Report::guardarReporte($lecNivel, "nivel");
                    $json_resp["factor"]["niv"] = "affected";
                    if ($resp != "ok") {
                        $json_resp["error"] = $resp;
                    }
                }*/
            } else {
                $json_resp["alarma"] = FALSE;
            }
            
        }
        else{
            $json_resp["alarma"] = FALSE;
            $json_resp["error"] = $resp;
        }
    }

    $json_resp["lecturas"]["temperatura"] = $lecTemperatura;
    $json_resp["lecturas"]["presion"] = $lecPresion;
    $json_resp["lecturas"]["nivel"] = $lecNivel;

    //envio de respuesta a la app
    //header('Content-type: application/json; charset=utf-8');
    echo json_encode($json_resp);
//}

//exit();
//$json_resp = array();
//$report = new Report();
//$resp = $report->guardarReporte(3.4, "temperatura");

//$resp = Report::guardarReporte(8.3, "nivel");
//echo $resp;

//echo number_format(mt_rand(20, 149), 2);
//$min = 15; $max = 30;
//$randomFloat = mt_rand(($min)*100, ($max)*100) / 100;//de esta forma obtendre numeros decimales
//var_dump($randomFloat);
//echo $randomFloat;

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

