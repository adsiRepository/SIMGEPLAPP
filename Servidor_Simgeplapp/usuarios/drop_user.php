<?php

include_once 'control_users.php';

if (isset($_REQUEST["id_ref"])) {

    $json_resp = array();
    $usuarios_mng = new ControlUsuario();
    $json_resp["erase"] = NULL;
    $id_ref = $_REQUEST["id_ref"];

    $res = $usuarios_mng->eliminarUsuario($id_ref);

    if ($res != NULL) {
        if ($res === TRUE) {
            $json_resp["erase"] = $res;
        }
        else {
            $json_resp["erase"] = FALSE;
            $json_resp["msg"] = $res;
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
        <form action="drop_user.php" method="post">
            <input type="text" name="id_ref"/>
            <input type="submit" value="Submit"/>
        </form>
    </body>
</html>-->
