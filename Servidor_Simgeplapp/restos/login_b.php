<?php

//carga y se conecta a la base de datos
require("config_db.php");

if (isset($_REQUEST["user"]) && isset($_REQUEST["pass"])) {
    //obteneos los usuarios respecto a la usuario que llega por parametro
    $query = "select * from usuarios where nick = :nick and pass = :pass";
    
    $query_params = array(
        ':nick' => $_POST['user'],
        ':pass' => $_POST['pass']
    );
    
    try {
        $stmt   = $db->prepare($query);
        $result = $stmt->execute($query_params);
    }
    catch (PDOException $ex) {
        //para testear pueden utilizar lo de abajo
        //die("la consulta murio " . $ex->getMessage());
        
        $response["logged"] = FALSE;
        $response["msg"] = "Problema con la base de datos, vuelve a intetarlo";
        die(json_encode($response));
        
    }
    
    //la variable a continuación nos permitirará determinar 
    //si es o no la información correcta
    //la inicializamos en "false"
    //$validated_info = false;
    
    //bamos a buscar a todas las filas
    $row = $stmt->fetch();
    if ($row) {
        $response["logged"] = TRUE;
        $response["msg"] = "Logged!";
        die(json_encode($response));
    } else {
        $response["logged"] = FALSE;
        $response["msg"] = "No Existe el Usuario";
        die(json_encode($response));
    }
}