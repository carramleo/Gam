/*
 * Clase que en función del botón pulsado para exportar en la aplicación web
 *  llama a la clase correspondiente para exportarlo a PDF o TXT
 */
package conversor;

import com.itextpdf.text.DocumentException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import conversorTXT.XMLtoTXT;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.xml.transform.TransformerException;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author carlos
 */
@ManagedBean
@SessionScoped
public class exportador implements Serializable {
    //Fichero que se sube en la aplicación.
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
            
            XMLtoTXT txt = new XMLtoTXT();
            
            InputStream fichero = fileExp.getInputstream();
            
            txt.conversorXMLtoTXT(fichero);
            
        }
    }
}
