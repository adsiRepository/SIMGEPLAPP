
// Project: SIMGEPLAP

package Controlador;

/*/
 * creation : 10/03/2015
 * author   : Miguel
/*/

import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;

public class text {

    public static void savePassws(String user, String pass){
        String ruta = "claves.txt";
        File file = new File(ruta);
        BufferedWriter escritor;
        try {
            if (file.exists()) {

                escritor = new BufferedWriter(new FileWriter(file));
                escritor.write(user + "   " + pass + "\n");
                escritor.close();

            }
            else{
                escritor = new BufferedWriter(new FileWriter(file));
                escritor.write(user + "   " + pass + "\n");
                escritor.close();
            }
        } catch (IOException ex) {
            System.out.print(ex.getLocalizedMessage());
        }
    }
    
    public static void main(String[] args){
        
        savePassws("miguel", "0000");
        
    }
    
}
