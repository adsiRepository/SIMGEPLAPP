<?php
include 'control_users.php';

if (isset($_REQUEST["user"]) && isset($_REQUEST["pass"])) {
    
    $json_resp = array();
    
    $user = $_REQUEST["user"];
    $pass = $_REQUEST["pass"];

    $control_usuarios = new ControlUsuario();
    
    $json_resp["logged"] = NULL;
    
    $resp = $control_usuarios->loggin($user, $pass);
    
    if($resp != NULL){
        if ($resp[0] === "logged") {
            $json_resp["logged"] = TRUE;
            $json_resp["data_sesion"]["id"] = $resp[1];
            $json_resp["data_sesion"]["nombre"] = $resp[2];
            $json_resp["data_sesion"]["rol"] = $resp[3];

        } else {
            $json_resp["logged"] = FALSE;
            $json_resp["msg"] = $resp[0];
        }
    }
    
    //header('Content-type: application/json; charset=utf-8');
    echo json_encode($json_resp);
}

?>
<!--<!DOCTYPE html>
<html>
    <head></head>
    <body>
        <p id="display"></p>
        <br><br>
        <form action="login.php" method="post">
            <input type="text" name="user"/>
            <input type="text" name="pass"/>
            <input type="submit" value="Submit"/>
        </form>
    </body>
</html>-->
