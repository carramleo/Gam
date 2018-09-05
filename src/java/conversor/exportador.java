/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conversor;

import com.itextpdf.text.DocumentException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import conversorTXT.MyDomParser;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.xml.transform.TransformerException;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author carlo
 */
@ManagedBean
@SessionScoped
public class exportador implements Serializable {

    private UploadedFile fileExp;

    public UploadedFile getFileExp() {
        return fileExp;
    }

    public void setFileExp(UploadedFile file) {
        this.fileExp = file;
    }

    public void errorFileExp() {
        FacesContext context = FacesContext.getCurrentInstance();

        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al exportar", "No ha seleccionado un fichero"));
    }

    public void exportadorPDF() throws IOException, DocumentException, TransformerException {
        if (fileExp.getFileName() == null || fileExp.getFileName().isEmpty()) {
            errorFileExp();
        } else {
            
            XMLtoPDF pdf = new XMLtoPDF();
            
            InputStream fichero = fileExp.getInputstream();
            
            pdf.convertXMLtoPDF(fichero);
            
        }
    }
    
    public void exportadorTXT() throws IOException, DocumentException, TransformerException {
        if (fileExp.getFileName() == null || fileExp.getFileName().isEmpty()) {
            errorFileExp();
        } else {
            
            MyDomParser txt = new MyDomParser();
            
            InputStream fichero = fileExp.getInputstream();
            
            txt.conversorXMLtoTXT(fichero);
            
        }
    }
}
