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

    public void XMLtoPDF(InputStream XML)
            throws IOException, DocumentException, TransformerException, TransformerConfigurationException, FileNotFoundException {

        TransformerFactory tFactory = TransformerFactory.newInstance();
        Transformer transformer = tFactory.newTransformer(new StreamSource("C:\\Users\\carlo\\Documents\\NetBeansProjects\\Gam\\convertToPDF.xsl"));
        transformer.transform(new StreamSource(XML), new StreamResult(new FileOutputStream("C:\\Users\\carlo\\Documents\\NetBeansProjects\\Gam\\convertToPDF.html")));
        String File_To_Convert = "C:\\Users\\carlo\\Documents\\NetBeansProjects\\Gam\\convertToPDF.html";
        String url = new File(File_To_Convert).toURI().toURL().toString();
        System.out.println("" + url);
        String HTML_TO_PDF = "C:\\Users\\carlo\\Documents\\NetBeansProjects\\Gam\\Juego.pdf";
        OutputStream os = new FileOutputStream(HTML_TO_PDF);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        Document document = new Document();
        try {

            PdfWriter.getInstance(document, os);
            document.open();
            HTMLWorker htmlWorker = new HTMLWorker(document);
            htmlWorker.parse(new FileReader(new File(File_To_Convert)));

            FacesContext context = FacesContext.getCurrentInstance();
            ExternalContext externalContext = context.getExternalContext();
            externalContext.responseReset();
            externalContext.setResponseContentType("application/pdf");
            externalContext.setResponseHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
            externalContext.setResponseHeader("Pragma", "public");
            externalContext.setResponseHeader("Content-disposition", "attachment;filename=" + "juego.pdf");
            OutputStream out = externalContext.getResponseOutputStream();

            byte[] arr = baos.toByteArray();
            
            os.write(arr); 
            baos.write(arr,0, arr.length);
            
            baos.writeTo(out);
            externalContext.responseFlushBuffer();

            document.close();
            os.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
