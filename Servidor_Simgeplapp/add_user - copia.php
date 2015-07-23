<?php
include 'control_users.php';

//$db = new mysqli("localhost", "root", "", "simgeplapp");
//obtenemos las variables enviadas por post desde la aplicacion con el objeto HttpClient

$json_resp = array();

if (isset($_REQUEST["nom"]) && isset($_REQUEST["ape"]) && isset($_REQUEST["id"])) {

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

    $json_resp["added"] = NULL;
    
    if ($new_pass == NULL || !(strlen($new_pass) > 0)) {
        $new_pass = "0000";
        //$json_resp["extras"]["r_pass"] = "Tu Contrasena -> 0000";
    }

    if ($new_nick == NULL || !(strlen($new_nick) > 0)) {
        $new_nick = $new_id;
        //$json_resp["extras"]["r_nick"] = "Tu Nick -> tu Identificacion";
    }

    $resp_bd = $usuarios_mng->registrarUsuario($new_id, $new_nom, $new_ape, $new_tipo_id, $new_tel, $new_mail, $new_pass, $new_rol, $new_nick);

    if ($resp_bd == TRUE) {
        /*echo "<script type='text/javascript'>
                        alert('Guardado Correctamente');
                        </script>";*/
        $json_resp["added"] = 1;
        //$respuesta[] = array("added" => "1");
        //echo "<p id='display1'>".$resp_bd."</p>";
    } else {
        /*echo "<script type='text/javascript'>
                        alert('No se agrego');
                        </script>";*/
        $json_resp["added"] = 0;
        //$respuesta[] = array("added" => "0");
        //echo "<p id='display1'>".$resp_bd."</p>";
    }

    //echo json_encode($respuesta); //envio de respuesta a la app
    echo json_encode($respuesta);/*"<p id='display2'>" . *//* . "</p>"*//*$json_resp*/
}
?>
<!-->
<!DOCTYPE html>
<html>
    <head></head>
    <body>
        <p id="display1"></p>
        <br>
        <p id="display2"></p>
        <br><br>
        <form action="add_user.php" method="post">
            <label>Nombre</label>
            <input type="text" name="nom"/><br>
            <label>Apellido</label>
            <input type="text" name="ape"/><br>
            <label>Id</label>
            <input type="text" name="id"/><br>
            <label>Tipo Id</label>
            <input type="text" name="tipo_id"/><br>
            <label>Telefono</label>
            <input type="text" name="tel"/><br>
            <label>Email</label>
            <input type="text" name="email"/><br>
            <label>Nick</label>
            <input type="text" name="nick"/><br>
            <label>Password</label>
            <input type="text" name="pass"/><br>
            <label>Rol</label>
            <input type="text" name="rol"/><br>

            <br>
            <input type="submit" value="Submit"/>
        </form>
    </body>
</html>-->

