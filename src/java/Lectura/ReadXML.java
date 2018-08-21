/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lectura;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.primefaces.Pregunta.PreguntaCampoTexto;
import org.primefaces.Pregunta.PreguntaCifras;
import org.primefaces.Pregunta.PreguntaContarLetras;
import org.primefaces.Pregunta.PreguntaOpciones;
import org.primefaces.Pregunta.PreguntaPanelesLetras;
import org.primefaces.Pregunta.PreguntaRelacionar;
import org.primefaces.Pregunta.PreguntaRespAbierta;
import org.primefaces.Pregunta.PreguntaSiNo;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import org.primefaces.showcase.view.button.ButtonView;
import org.primefaces.showcase.view.input.SelectOneMenuTipos;
import org.primefaces.showcase.view.input.SelectOneMenuView;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author cramos
 */
@ManagedBean
@SessionScoped
public class ReadXML {

    private boolean compatible = false;

    private UploadedFile file;

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }
    
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    public void saveMessage() {
        FacesContext context = FacesContext.getCurrentInstance();
         
        context.addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al importar","Tipo de juego incompatible"));
    }

    public void readFile(ButtonView b, SelectOneMenuView tipos, SelectOneMenuTipos opcionesTipos) throws SAXException, IOException, ParserConfigurationException {

        InputStream fichero = file.getInputstream();

        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.parse(fichero);

        String titulo = document.getElementsByTagName("titulo").item(0).getTextContent();
        String autor = document.getElementsByTagName("autor").item(0).getTextContent();

        String tipo = document.getElementsByTagName("preguntas").item(0).getAttributes().getNamedItem("tipo").getNodeValue();

        List<PreguntaOpciones> Preguntas = b.getPreguntas();
        List<PreguntaCifras> PreguntasCifras = b.getPreguntasCifras();
        List<PreguntaCampoTexto> PreguntasCampoTexto = b.getPreguntasCampoTexto();
        List<PreguntaSiNo> PreguntasSiNo = b.getPreguntasSiNo();
        List<PreguntaPanelesLetras> PreguntasPanelesLetras = b.getPreguntasPanelesLetras();
        List<PreguntaRelacionar> PreguntasRelacionar = b.getPreguntasRelacionar();
        List<PreguntaRespAbierta> PreguntasRespAbierta = b.getPreguntasRespAbierta();
        List<PreguntaContarLetras> PreguntasContarLetras = b.getPreguntasContarLetras();
        autor = b.getAutor();
        titulo = b.getTitulo();
        String TipoJuego = b.getTipo();
        
        
        String tipoSeleccionado = tipos.getTipo();  //tipo  seleccionado en el menu de la izq
        
        Map<String, String> mapaTiposCompatibles= tipos.getData().get(tipoSeleccionado);   //obtengo el mapa de los tipos compatibles que acepta
       
        
        for (String key : mapaTiposCompatibles.keySet()){
            if(mapaTiposCompatibles.get(key).equals(tipo)){             //recorro el mapa buscando si el tipo del fichero importado aparece en ese mapa
              compatible = true;  
            }
        }

        if (compatible) {

            int preguntas = document.getElementsByTagName("pregunta").getLength();
            NodeList preguntasElement = document.getElementsByTagName("pregunta");

            if (tipoSeleccionado.equals("TipoOpciones")) {

                for (int i = 0; i < preguntasElement.getLength(); i++) {

                    Preguntas.add(getPreguntaOpciones(preguntasElement.item(i), tipo,b));
                }
            }

        }else{
            saveMessage();
        }
    }

    public boolean isCompatible() {
        return compatible;
    }

    public void setCompatible(boolean compatible) {
        this.compatible = compatible;
    }

    private static PreguntaOpciones getPreguntaOpciones(Node node, String tipo, ButtonView bb) {
        //XMLReaderDOM domReader = new XMLReaderDOM();
        PreguntaOpciones preg = new PreguntaOpciones(SelectOneMenuTipos.numOpciones.get(tipo), SelectOneMenuTipos.formatoOp.get(tipo), SelectOneMenuTipos.lineasEnun.get(tipo));

        if (node.getNodeType() == Node.ELEMENT_NODE) {
            Element element = (Element) node;

            preg.setEnunciado(getTagValuePreguntaOpciones("enunciado", element));
            preg.setId("preg_" + bb.getNumber()+1 );
            bb.setNumber(bb.getNumber()+1);
            
            String[] respuestasXML = new String[element.getElementsByTagName("respuesta").getLength()];
            for (int i = 0; i < element.getElementsByTagName("respuesta").getLength(); i++) {
       
                    
                   NodeList nodeListResp   = element.getElementsByTagName("respuesta").item(i).getChildNodes();
                   Node nodeResp = (Node) nodeListResp.item(0);
                     respuestasXML[i]=nodeResp.getNodeValue();

            }
            preg.setRespuestas(respuestasXML);
            preg.setSolucion(element.getAttributeNode("sol").getValue());

        }

        return preg;
    }

    private static String getTagValuePreguntaOpciones(String tag, Element element) {
        NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node node = (Node) nodeList.item(0);
        return node.getNodeValue();
    }

}
