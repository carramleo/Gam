/*
 * Clase que identifica el fichero subido y en función de su tipo llama a la clase correspondiente
*   para su lectura.
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
import org.primefaces.showcase.view.AdministrarPreguntas.AdminPreguntas;
import org.primefaces.showcase.view.MapaTipos.MapaTiposTXT;
import org.primefaces.showcase.view.MapaTipos.MenuMapTiposOpciones;
import org.primefaces.showcase.view.MapaTipos.MenuMapTipos;
import org.xml.sax.SAXException;

/**
 *
 * @author carlo
 */
@ManagedBean
@SessionScoped
public class Importador implements Serializable{
    
    // Variable donde se guarda el fichero que se sube
    private UploadedFile file;

    
    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }
    //Mensaje de error cuando no se seleccione ningún fichero e intento importar.
    public void errorFile() {
        FacesContext context = FacesContext.getCurrentInstance();

        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al importar", "No ha seleccionado un fichero"));
    }
    
   //Mensaje de error cuando no se seleccione ningún tipo  e intento importar.
    public void errorFileSinTipo() {
        FacesContext context = FacesContext.getCurrentInstance();

        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al importar", "No ha seleccionado ningún tipo"));
    }
     public void errorFileExpFormato() {
        FacesContext context = FacesContext.getCurrentInstance();

        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al exportar", "No ha seleccionado un fichero válido (sólo xml y txt)"));
    }
    
    public void importador(AdminPreguntas b, MenuMapTipos tipos, MenuMapTiposOpciones opcionesTipos) throws SAXException, IOException, ParserConfigurationException{
        
        //compruebo si se ha subido el fichero o si está vacío y también si se ha seleccionao.
        if (file.getFileName() == null || file.getFileName().isEmpty() ) {
            errorFile();
        }else if (b.getTipo()==null || b.getTipo().isEmpty())
            errorFileSinTipo();
        else {
            
            
            //Comprobamos de qué tipo es el fichero subido.
            if(file.getContentType().equals("text/xml")){
                ReadXML xml = new ReadXML();
                
                xml.readFile(file,b, tipos, opcionesTipos);
                
            }else if(file.getContentType().equals("text/plain")){
                
                //Iniciamos el mapa de TXT
                
                MapaTiposTXT mapa = new MapaTiposTXT();
                mapa.iniciarMapa();
                
                ReadTXT txt = new ReadTXT();
                
                txt.readFile(file, b, tipos, opcionesTipos);
            }else{
                errorFileExpFormato();
            }
        }
    }
}
