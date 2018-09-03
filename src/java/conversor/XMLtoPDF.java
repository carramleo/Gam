/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conversor;

import java.io.*;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Document;
import org.xhtmlrenderer.pdf.ITextRenderer;
import javax.xml.transform.*;
import javax.xml.transform.stream.*;


public class XMLtoPDF {

    public static void main(String[] args)
            throws IOException, DocumentException, TransformerException, TransformerConfigurationException, FileNotFoundException {
        TransformerFactory tFactory = TransformerFactory.newInstance();
        Transformer transformer = tFactory.newTransformer(new StreamSource("convertToPDF.xsl"));
        transformer.transform(new StreamSource("prueba.xml"), new StreamResult(new FileOutputStream("convertToPDF.html")));

        String File_To_Convert = "convertToPDF.html";
        String url = new File(File_To_Convert).toURI().toURL().toString();
        System.out.println("" + url);
        String HTML_TO_PDF = "ConvertedFile.pdf";

        OutputStream os = new FileOutputStream(HTML_TO_PDF);
        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocument(url);
        renderer.layout();
        renderer.createPDF(os);
        os.close();

        
        HtmlConverter.convertToPdf( new ByteArrayInputStream(html), new FileOutputStream(dest), properties);
    }

}
}
