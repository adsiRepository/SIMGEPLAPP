<?php
include 'control_users.php';

if (isset($_REQUEST["user"]) && isset($_REQUEST["pass"])) {
    
    $user = $_REQUEST["user"];
    $pass = $_REQUEST["pass"];

    $control_usuarios = new ControlUsuario();
    
    $r = $control_usuarios->loggin($user, $pass);
    
    $json_resp = array();

    if ($r == TRUE) {
        $json_resp["logged"] = TRUE;
    } else {
        $json_resp["logged"] = FALSE;
    }

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
