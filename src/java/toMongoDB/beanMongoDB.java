/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package toMongoDB;

import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author carlo
 */
@ManagedBean
@SessionScoped
public class beanMongoDB implements Serializable {

    private UploadedFile fileMongo;

    public UploadedFile getFileMongo() {
        return fileMongo;
    }

    public void setFileMongo(UploadedFile fileMongo) {
        this.fileMongo = fileMongo;
    }

    public void errorFile() {
        FacesContext context = FacesContext.getCurrentInstance();

        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al enviar", "No ha seleccionado un fichero"));
    }

    public void enviarFichero() {

        if (fileMongo.getFileName() == null || fileMongo.getFileName().isEmpty()) {
            errorFile();
        } else {

           
            
        }
    }
    
    
    
}
