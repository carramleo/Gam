/*
 * Clase que convierte un fichero XML que recibe como parámetro en la función convertXMLtoPDF
 *  y posteriormente se convierte a HTML, en el cual, a través de un fichero XSL, se le da formato a la página PDF que posteriormente se va a generar.
 *  Para ello se utiliza la librería ITEXT.
 */
package conversor;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.html.simpleparser.HTMLWorker;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;

import java.io.*;
import java.net.URL;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.xml.transform.*;
import javax.xml.transform.stream.*;
import org.primefaces.showcase.view.AdministrarPreguntas.AdminPreguntas;

@ManagedBean
@SessionScoped
public class XMLtoPDF implements Serializable {

    public void convertXMLtoPDF(InputStream XML)
            throws IOException, DocumentException, TransformerException, TransformerConfigurationException, FileNotFoundException {
        
        //Una instancia de TransformerFactory se puede utilizar para crear objetos Transformery Templates.
        TransformerFactory tFactory = TransformerFactory.newInstance();
        Transformer transformer = tFactory.newTransformer(new StreamSource(getClass().getResource("convertToPDF.xsl").getFile()));
        //El html generado se genera en el servidor.
        transformer.transform(new StreamSource(XML), new StreamResult(new FileOutputStream(getClass().getResource("/").getPath().concat("/convertToPDF.html"))));
        String File_To_Convert = getClass().getResource("/convertToPDF.html").getFile();

        Document document = new Document();
        try {
            //Pedimos al servlet que nos mande u fichero descargable de tipo PDF para escribir contenido en él.
            FacesContext context = FacesContext.getCurrentInstance();
            ExternalContext externalContext = context.getExternalContext();
            externalContext.responseReset();
            externalContext.setResponseContentType("application/pdf; charset=UTF-8");
            
            externalContext.setResponseHeader("Content-disposition", "attachment;filename=" + "juego.pdf");
            OutputStream out = externalContext.getResponseOutputStream();
            context.responseComplete();
            
            //Grabamos el contenido que teníamos en el XML en el fichero PDF.
            PdfWriter.getInstance(document, out);
            document.open();
            HTMLWorker htmlWorker = new HTMLWorker(document);
            htmlWorker.parse(new FileReader(new File(File_To_Convert)));
            
            //Pedimos al servlet que nos descargue el fichero en la carpeta de descargas de nuestro equipo.
            externalContext.responseFlushBuffer();

            document.close();
            out.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
