<?php

include 'control_users.php';

if (isset($_REQUEST["id"])) {

    $json_resp = array();

    $id = $_REQUEST["id"];

    $usuarios_mng = new ControlUsuario();

    $json_resp["find"] = NULL;

    $datos = $usuarios_mng->devolverInformacion($id);

    if ($datos != NULL) {
        if ($datos[0] === "ok") {
            $json_resp["find"] = TRUE;
            $json_resp["user"]["id"] = utf8_encode($datos[1]);
            $json_resp["user"]["nombre"] = utf8_encode($datos[2]);
            $json_resp["user"]["apes"] = utf8_encode($datos[3]);
            $json_resp["user"]["tipo_id"] = utf8_encode($datos[4]);
            $json_resp["user"]["telefono"] = utf8_encode($datos[5]);
            $json_resp["user"]["email"] = utf8_encode($datos[6]);
            $json_resp["user"]["pass"] = utf8_encode($datos[7]);
            $json_resp["user"]["rol"] = utf8_encode($datos[8]);
            $json_resp["user"]["nick"] = utf8_encode($datos[9]);
        } else {
            $json_resp["find"] = FALSE;
            $json_resp["msg"] = utf8_encode($datos[0]);
        }
    }

    header('Content-type: application/json; charset=utf-8');
    echo json_encode($json_resp, JSON_FORCE_OBJECT);

    //echo json_encode($json_resp);
}

exit();
?>
<!--<!DOCTYPE html>
<html>
    <head></head>
    <body>
        <p id="display"></p>
        <br><br>
        <form action="buscar.php" method="post">
            <input type="text" name="id"/>
            <input type="submit" value="Submit"/>
        </form>
    </body>
</html>-->