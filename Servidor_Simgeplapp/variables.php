<?php
include_once 'db_config.php';

//obtenemos las variables enviadas por post desde la aplicacion con el objeto HttpClient
if (isset($_REQUEST["user"]) && isset($_REQUEST["pass"])) {
    $user = $_REQUEST["user"];
    $pass = $_REQUEST["pass"];

    $db = new mysqli("localhost", "root", "", "simgeplap"); 
//loggeo

    $consulta = "select * from usuarios where name='$user' and pass='$pass'";

    $result = $db->query($consulta);

    if ($result->num_rows > 0) {
        //echo "<p id='display'>Usuario Existe</p>";
        $respuesta[] = array("logged" => "1");
    } else {
        //echo "<p id='display'>Usuario No Existe</p>";
        $respuesta[] = array("logged" => "0");
    }

    echo json_encode($respuesta); //envio de respuesta a la app
}

?>
<!-->
<!DOCTYPE html>
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

