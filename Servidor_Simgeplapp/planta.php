<?php
include_once 'db_config.php';

if (isset($_REQUEST["peticion_lecturas"])) {

    $json_resp = array();
    
    $azar = mt_rand(0, 100);
    
    if($azar > 80){
        $lecTemperatura = mt_rand(20, 149);
        $lecPresion = mt_rand(0, 50);
        $lecNivel = mt_rand(0, 50);
    }
    else {
        $lecTemperatura = mt_rand(46, 120);
        $lecPresion = mt_rand(20, 37);
        $lecNivel = mt_rand(16, 41);
    }
    
    $json_resp["lecturas"]["temperatura"] = $lecTemperatura;
    $json_resp["lecturas"]["presion"] = $lecPresion;
    $json_resp["lecturas"]["nivel"] = $lecNivel;

    header('Content-type: application/json; charset=utf-8');
    echo json_encode($json_resp); //envio de respuesta a la app
}
exit();
?>

<!--<!DOCTYPE html>
<html>
    <head></head>
    <body>
        <p id="display"></p>
        <br><br>
        <div id="pantalla"></div>
    </body>
</html>-->

