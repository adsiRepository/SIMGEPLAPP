<?php
include 'control_users.php';

if (isset($_REQUEST["ref"])) {

    $json_resp = array();
    
    $ref = $_REQUEST["ref"];
    
    $new_nom = $_REQUEST["nom"];
    $new_ape = $_REQUEST["ape"];
    $new_id = $_REQUEST["id"];
    $new_tipo_id = $_REQUEST["tipo_id"];
    $new_tel = $_REQUEST["tel"];
    $new_mail = $_REQUEST["email"];
    $new_nick = $_REQUEST["nick"];
    $new_pass = $_REQUEST["pass"];
    $new_rol = $_REQUEST["rol"];

    $usuarios_mng = new ControlUsuario();

    $json_resp["modif"] = NULL;
    
    $buildQuery = "";
    
    if ($new_pass != NULL && (strlen($new_pass) > 0)) {
        $buildQuery = $buildQuery . ", set pass='$new_pass'";
    }
    
    if ($new_nick != NULL && (strlen($new_nick) > 0)) {
        $buildQuery = $buildQuery . ", set nick='$new_nick'";
    }
    
    $resp_bd = $usuarios_mng->modificarUsuario($ref, $new_id, $new_nom, $new_ape, $new_tipo_id, $new_tel, 
            $new_mail, $new_rol, $buildQuery);

    if ($resp_bd === "modificado") {
        $json_resp["modif"] = TRUE;
        $json_resp["error"] = NULL;
    } 
    elseif ($resp_bd === "fail"){
        $json_resp["modif"] = FALSE;
        $json_resp["error"] = $usuarios_mng->obtenerDetalleError();
    }
    else {
        $json_resp["modif"] = FALSE;
        $json_resp["error"] = $resp_bd;
    }

    echo json_encode($json_resp);
}
