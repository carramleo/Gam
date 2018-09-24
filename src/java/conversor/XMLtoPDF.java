/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
import org.primefaces.showcase.view.button.ButtonView;

@ManagedBean
@SessionScoped
public class XMLtoPDF implements Serializable {

    public void convertXMLtoPDF(InputStream XML)
            throws IOException, DocumentException, TransformerException, TransformerConfigurationException, FileNotFoundException {

        TransformerFactory tFactory = TransformerFactory.newInstance();
        Transformer transformer = tFactory.newTransformer(new StreamSource(getClass().getResource("convertToPDF.xsl").getFile()));
        transformer.transform(new StreamSource(XML), new StreamResult(new FileOutputStream(getClass().getResource("/").getPath().concat("/convertToPDF.html"))));
        String File_To_Convert = getClass().getResource("/convertToPDF.html").getFile();

        Document document = new Document();
        try {

            FacesContext context = FacesContext.getCurrentInstance();
            ExternalContext externalContext = context.getExternalContext();
            externalContext.responseReset();
            externalContext.setResponseContentType("application/pdf; charset=UTF-8");
            
            externalContext.setResponseHeader("Content-disposition", "attachment;filename=" + "juego.pdf");
            OutputStream out = externalContext.getResponseOutputStream();
            context.responseComplete();
            PdfWriter.getInstance(document, out);
            document.open();
            HTMLWorker htmlWorker = new HTMLWorker(document);
            htmlWorker.parse(new FileReader(new File(File_To_Convert)));
            
            
            externalContext.responseFlushBuffer();

            document.close();
            out.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
