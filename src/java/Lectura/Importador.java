/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lectura;

import java.io.IOException;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.xml.parsers.ParserConfigurationException;
import org.primefaces.model.UploadedFile;
import org.primefaces.showcase.view.button.ButtonView;
import org.primefaces.showcase.view.input.SelectOneMenuTipos;
import org.primefaces.showcase.view.input.SelectOneMenuView;
import org.xml.sax.SAXException;

/**
 *
 * @author carlo
 */
@ManagedBean
@SessionScoped
public class Importador implements Serializable{
    
    
    private UploadedFile file;

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }
    
    public void errorFile() {
        FacesContext context = FacesContext.getCurrentInstance();

        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al importar", "No ha seleccionado un fichero"));
    }
    
    public void importador(ButtonView b, SelectOneMenuView tipos, SelectOneMenuTipos opcionesTipos) throws SAXException, IOException, ParserConfigurationException{
        
        if (file.getFileName() == null || file.getFileName().isEmpty() ) {
            errorFile();
        } else {
            
            if(file.getContentType().equals("text/xml")){
                ReadXML xml = new ReadXML();
                
                xml.readFile(file,b, tipos, opcionesTipos);
                
            }else if(file.getContentType().equals("text/plain")){
                
                ReadTXT txt = new ReadTXT();
                
                txt.readFile(file, b, tipos, opcionesTipos);
            }
        }
    }
}