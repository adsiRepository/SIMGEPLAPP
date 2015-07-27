<?php
include 'control_users.php';

if (isset($_REQUEST["id"])) {
    
    $json_resp = array();

    $id = $_REQUEST["id"];
    
    $usuarios_mng = new ControlUsuario();

    $json_resp["find"] = NULL;
    
    $datos = $usuarios_mng->devolverInformacion($id);

    if ($datos != NULL) {
        $json_resp["find"] = TRUE;
        $json_resp["user"]["id"] = $datos[0];
        $json_resp["user"]["nombre"] = $datos[1];
        $json_resp["user"]["apes"] = $datos[2];
        $json_resp["user"]["tipo_id"] = $datos[3];
        $json_resp["user"]["telefono"] = $datos[4];
        $json_resp["user"]["email"] = $datos[5];
        $json_resp["user"]["pass"] = $datos[6];
        $json_resp["user"]["rol"] = $datos[7];
        $json_resp["user"]["nick"] = $datos[8];
    } else {
        $json_resp["find"] = FALSE;
        $json_resp["user"] = NULL;
    }
    
    header('Content-type: application/json; charset=utf-8');
    echo json_encode($json_resp, JSON_FORCE_OBJECT);    

    //echo json_encode($json_resp);
}

exit();
