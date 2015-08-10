
// Project: SIMGEPLAP

package Controlador;

import simgeplap.*;
import java.io.File;
import java.io.FileOutputStream;
import javax.swing.JFileChooser;
import java.io.FileNotFoundException;
import javax.swing.JOptionPane;
import java.awt.HeadlessException;
import java.io.IOException;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;


public class PDF {
    
    private static String stringDireccionArchivo;

    public static void crear_yGuardarPDF() {

        try {
            //La clase JFileChooser de la api de java permite que el usuario elija un archivo
            JFileChooser selecUbicacion = new JFileChooser();//Aqui llamo al constructor de la clase que tiene el mismo nombre anyimage creo el objeto selecUbicacion
            int option = selecUbicacion.showSaveDialog(null);//Metodo showSaveDialog me seleciona el objeto selecUbicacion anyimage lo guarda en option
            if (option == JFileChooser.APPROVE_OPTION) {//Si es identico la selecion del archivo con el archivo que elige el usuario apruebelo anyimage retornelo
                File f = selecUbicacion.getSelectedFile();//Este metodo getSelectFile()Devuelve los archivos selecionados
                stringDireccionArchivo = f.toString();//aqui se convierte la direccion de la ubicacion en una cadena o string
                Dibujando_elPDF(stringDireccionArchivo);
            }
        } catch (BadElementException | IOException ex) {
            JOptionPane.showMessageDialog(null, ex.getLocalizedMessage(), "PDF, seleccionar unbicacion y guardar", 0);
        }

    }
    
   
    public static void Dibujando_elPDF(String rutaDestino) throws BadElementException, IOException {

        try {
            FileOutputStream archivo = new FileOutputStream(rutaDestino + ".pdf");
            
            Document doc = new Document(PageSize.LETTER, 40, 30, 20, 30);
            
            PdfWriter transcriptor = PdfWriter.getInstance(doc, archivo);
            
            DiseñoPagina paint = new DiseñoPagina();
            transcriptor.setPageEvent(paint);
            
            Paragraph parrafo1 = new Paragraph(); 
            Image img2 = Image.getInstance(Grafos.chartToPNG(Grafos.GenerarSeriesTiempo()));
            
            parrafo1.setFont(FontFactory.getFont("cursive", 14, Font.BOLD, BaseColor.BLACK));
            parrafo1.setAlignment(Paragraph.ALIGN_JUSTIFIED);
            parrafo1.add("\n\n\nReporte Generado por: "+SIMGEPLAP.user_session);
            
            doc.addTitle("Reporte planta Simgeplap");
            doc.addAuthor("ADSI-38");
            
            // Aqui comienza el diseño del PDF --<> 
            doc.open();
            
            doc.add(parrafo1);
            
            //doc.newPage();
            
            doc.add(Chunk.NEWLINE);
            doc.add(Chunk.NEWLINE);
            doc.add(img2);
                
            doc.close();
            // Aqui termina la creacion del PDF
            
            JOptionPane.showMessageDialog(null, "Documento creado con exito");

        } catch (FileNotFoundException | DocumentException | HeadlessException e) {
            JOptionPane.showMessageDialog(null, "ERROR >\n" + e.getLocalizedMessage(), "PDF, dibujando el PDF", 0);
        }
    }
    
      /*lINK de Ayuda para entender mejor=== https://www.youtube.com/watch?v=iYNJg_l6jLo*/
            /*https://www.youtube.com/watch?v=zzEAIEEcf9k*/
    
    
    
    private static class DiseñoPagina extends PdfPageEventHelper{
        
        Phrase bloqueCabecera;
        Phrase imgCabza;
        
        @Override
        public void onStartPage(PdfWriter writer, Document document) {

            //try {
                //Image anyimage = Image.getInstance("");
                //Chunk logoPDF = new Chunk(anyimage, 20, -50);
                //imgCabza = new Phrase(logoPDF);
                bloqueCabecera = new Phrase(); //logoPDF
                bloqueCabecera.add("Simgeplap  ADSI-38\n");
            //} catch (BadElementException | IOException ex) {
             //   JOptionPane.showMessageDialog(null, ex.getLocalizedMessage(), "Class DiseñoPagina", 0);
           // }

            
            PdfContentByte pfdBytes = writer.getDirectContent();
            
            ColumnText.showTextAligned(
                    /*PdfContentByte  */ pfdBytes, 
                    /*alineacion*/ Element.ALIGN_TOP, 
                    /*objeto Phrase*/bloqueCabecera, 
                    /* posicion en X*/(document.right() - document.left()) / 2 + document.leftMargin(), //(document.right() / 3) - document.rightMargin()
                    /* posicion en Y*/document.top()-15, 
                    /*rotacion*/ 0); 
            /*
            ColumnText.showTextAligned(
                     pfdBytes, 
                     Element.ALIGN_RIGHT, 
                    imgCabza, 
                    (document.right() - document.left()) / 2 + document.leftMargin(), 
                    document.top()-20, 
                    0); 
            */
        }
        
    }
    
}
