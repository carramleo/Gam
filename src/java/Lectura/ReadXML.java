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

        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al importar", "Tipo de juego incompatible"));
    }
    
    public void errorFile() {
        FacesContext context = FacesContext.getCurrentInstance();

        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al importar", "No ha seleccionado un fichero"));
    }

    public void readFile(ButtonView b, SelectOneMenuView tipos, SelectOneMenuTipos opcionesTipos) throws SAXException, IOException, ParserConfigurationException {

        if (file.getFileName() == null || file.getFileName().isEmpty() ) {
            errorFile();
        } else {

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

            Map<String, String> mapaTiposCompatibles = tipos.getData().get(tipoSeleccionado);   //obtengo el mapa de los tipos compatibles que acepta

            for (String key : mapaTiposCompatibles.keySet()) {
                if (mapaTiposCompatibles.get(key).equals(tipo)) {             //recorro el mapa buscando si el tipo del fichero importado aparece en ese mapa
                    compatible = true;
                }
            }

            if (compatible) {

                NodeList preguntasElement = document.getElementsByTagName("pregunta");

                if (tipoSeleccionado.equals("TipoOpciones")) {

                    for (int i = 0; i < preguntasElement.getLength(); i++) {

                        Preguntas.add(getPreguntaOpciones(preguntasElement.item(i), tipo, b));
                    }
                } else if (tipoSeleccionado.equals("TipoCampoTexto")) {

                    for (int i = 0; i < preguntasElement.getLength(); i++) {

                        PreguntasCampoTexto.add(getPreguntaCampoTexto(preguntasElement.item(i), tipo, b));
                    }
                } else if (tipoSeleccionado.equals("TipoCifras")) {

                    for (int i = 0; i < preguntasElement.getLength(); i++) {

                        PreguntasCifras.add(getPreguntaCifras(preguntasElement.item(i), tipo, b));
                    }
                } else if (tipoSeleccionado.equals("TipoSiNo")) {

                    for (int i = 0; i < preguntasElement.getLength(); i++) {

                        PreguntasSiNo.add(getPreguntaSiNo(preguntasElement.item(i), tipo, b));
                    }
                } else if (tipoSeleccionado.equals("TipoPanelesLetras")) {

                    for (int i = 0; i < preguntasElement.getLength(); i++) {

                        PreguntasPanelesLetras.add(getPreguntaPanelesLetras(preguntasElement.item(i), tipo, b));
                    }
                } else if (tipoSeleccionado.equals("TipoRelacionar")) {

                    for (int i = 0; i < preguntasElement.getLength(); i++) {

                        PreguntasRelacionar.add(getPreguntaRelacionar(preguntasElement.item(i), tipo, b));
                    }
                } else if (tipoSeleccionado.equals("TipoRespAbierta")) {

                    for (int i = 0; i < preguntasElement.getLength(); i++) {

                        PreguntasRespAbierta.add(getPreguntaRespAbierta(preguntasElement.item(i), tipo, b));
                    }
                } else if (tipoSeleccionado.equals("TipoContarLetras")) {

                    for (int i = 0; i < preguntasElement.getLength(); i++) {

                        PreguntasContarLetras.add(getPreguntaContarLetras(preguntasElement.item(i), tipo, b));
                    }
                }

            } else {
                saveMessage();
            }
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
         char[] formatoOpciones = new char[]{'A', 'B', 'C', 'D', 'E', 'F'};
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            Element element = (Element) node;

            preg.setEnunciado(getTagValuePregunta("enunciado", element));

            preg.setId("preg_" + bb.getNumber() + 1);
            bb.setNumber(bb.getNumber() + 1);

            String[] respuestasXML = new String[element.getElementsByTagName("respuesta").getLength()];
            for (int i = 0; i < element.getElementsByTagName("respuesta").getLength(); i++) {

                NodeList nodeListResp = element.getElementsByTagName("respuesta").item(i).getChildNodes();
                Node nodeResp = (Node) nodeListResp.item(0);
                respuestasXML[i] = nodeResp.getNodeValue();

            }
            preg.setRespuestas(respuestasXML);
            int numSol=0;
            for(int j =0;j<formatoOpciones.length;j++){
                if(String.valueOf(formatoOpciones[j]).equals(element.getAttributeNode("sol").getValue())){
                    numSol=j+1;
                }
            }
            preg.setSolucion(String.valueOf(numSol));

        }

        return preg;
    }

    private static PreguntaCampoTexto getPreguntaCampoTexto(Node node, String tipo, ButtonView bb) {
        //XMLReaderDOM domReader = new XMLReaderDOM();
        PreguntaCampoTexto preg = new PreguntaCampoTexto(SelectOneMenuTipos.lineasEnun.get(tipo), SelectOneMenuTipos.posiblesSol.get(tipo), SelectOneMenuTipos.Pistas.get(tipo));

        if (node.getNodeType() == Node.ELEMENT_NODE) {
            Element element = (Element) node;

            preg.setEnunciado(getTagValuePregunta("enunciado", element));
            preg.setId("preg_" + bb.getNumber() + 1);
            bb.setNumber(bb.getNumber() + 1);

            /*String[] respuestasXML = new String[element.getElementsByTagName("respuesta").getLength()];
            for (int i = 0; i < element.getElementsByTagName("respuesta").getLength(); i++) {
       
                    
                   NodeList nodeListResp   = element.getElementsByTagName("respuesta").item(i).getChildNodes();
                   Node nodeResp = (Node) nodeListResp.item(0);
                     respuestasXML[i]=nodeResp.getNodeValue();

            }
            preg.setRespuestas(respuestasXML);*/
            String soluciones[] = new String[SelectOneMenuTipos.posiblesSol.get(tipo)];

            String solSplit = element.getAttribute("sol");

            String solpart[] = solSplit.split(",");
            int h = soluciones.length - 1;
            for (int j = 0; j < soluciones.length; j++) {

                soluciones[h] = solpart[j];
                h--;
            }
            h = 0;
            preg.setSolucion(soluciones);

            String[] pistasXML = new String[element.getElementsByTagName("pista").getLength()];
            for (int i = 0; i < element.getElementsByTagName("pista").getLength(); i++) {
                NodeList nodeListPista = element.getElementsByTagName("pista").item(i).getChildNodes();

                Node nodeResp = (Node) nodeListPista.item(0);
                pistasXML[i] = nodeResp.getNodeValue();
            }
            preg.setPistas(pistasXML);

        }

        return preg;
    }

    private static PreguntaCifras getPreguntaCifras(Node node, String tipo, ButtonView bb) {
        //XMLReaderDOM domReader = new XMLReaderDOM();
        PreguntaCifras preg = new PreguntaCifras(SelectOneMenuTipos.lineasEnun.get(tipo));

        if (node.getNodeType() == Node.ELEMENT_NODE) {
            Element element = (Element) node;

            preg.setEnunciado(getTagValuePregunta("enunciado", element));
            preg.setId("preg_" + bb.getNumber() + 1);
            bb.setNumber(bb.getNumber() + 1);

            /*String[] respuestasXML = new String[element.getElementsByTagName("respuesta").getLength()];
            for (int i = 0; i < element.getElementsByTagName("respuesta").getLength(); i++) {
       
                    
                   NodeList nodeListResp   = element.getElementsByTagName("respuesta").item(i).getChildNodes();
                   Node nodeResp = (Node) nodeListResp.item(0);
                     respuestasXML[i]=nodeResp.getNodeValue();

            }
            preg.setRespuestas(respuestasXML);*/
            preg.setSolucion(element.getAttribute("sol"));
            /*
            String[] pistasXML = new String[element.getElementsByTagName("pista").getLength()];
            for (int i = 0; i < element.getElementsByTagName("pista").getLength(); i++) {
                NodeList nodeListPista   = element.getElementsByTagName("pista").item(i).getChildNodes();
               
                Node nodeResp = (Node) nodeListPista.item(0);
                     pistasXML[i]=nodeResp.getNodeValue();
            }
            preg.setPistas(pistasXML);
             */

        }

        return preg;
    }

    private static PreguntaSiNo getPreguntaSiNo(Node node, String tipo, ButtonView bb) {
        //XMLReaderDOM domReader = new XMLReaderDOM();
        PreguntaSiNo preg = new PreguntaSiNo(SelectOneMenuTipos.elementosPanel.get(tipo), SelectOneMenuTipos.lineasEnun.get(tipo));

        if (node.getNodeType() == Node.ELEMENT_NODE) {
            Element element = (Element) node;

            preg.setEnunciado(getTagValuePregunta("enunciado", element));
            preg.setId("preg_" + bb.getNumber() + 1);
            bb.setNumber(bb.getNumber() + 1);

            String[] respuestasXML = new String[element.getElementsByTagName("respuesta").getLength()];
            String[] solucionesXML = new String[element.getElementsByTagName("respuesta").getLength()];;

            for (int i = 0; i < element.getElementsByTagName("respuesta").getLength(); i++) {

                NodeList nodeListResp = element.getElementsByTagName("respuesta").item(i).getChildNodes();
                Node nodeResp = (Node) nodeListResp.item(0);
                respuestasXML[i] = nodeResp.getNodeValue();
                Node aux = element.getElementsByTagName("respuesta").item(i).getAttributes().getNamedItem("sol");
                solucionesXML[i] = aux.getNodeValue();

            }
            preg.setRespuestas(respuestasXML);
            preg.setSolucion(solucionesXML);

            /*
            String[] pistasXML = new String[element.getElementsByTagName("pista").getLength()];
            for (int i = 0; i < element.getElementsByTagName("pista").getLength(); i++) {
                NodeList nodeListPista   = element.getElementsByTagName("pista").item(i).getChildNodes();
               
                Node nodeResp = (Node) nodeListPista.item(0);
                     pistasXML[i]=nodeResp.getNodeValue();
            }
            preg.setPistas(pistasXML);
             */
        }

        return preg;
    }

    private static PreguntaPanelesLetras getPreguntaPanelesLetras(Node node, String tipo, ButtonView bb) {
        //XMLReaderDOM domReader = new XMLReaderDOM();
        PreguntaPanelesLetras preg = new PreguntaPanelesLetras(SelectOneMenuTipos.letrasPanel.get(tipo), SelectOneMenuTipos.Pistas.get(tipo));

        if (node.getNodeType() == Node.ELEMENT_NODE) {
            Element element = (Element) node;

            preg.setEnunciado(getTagValuePregunta("enunciado", element));
            preg.setId("preg_" + bb.getNumber() + 1);
            bb.setNumber(bb.getNumber() + 1);

            String[] pistasXML = new String[element.getElementsByTagName("pista").getLength()];
            for (int i = 0; i < element.getElementsByTagName("pista").getLength(); i++) {

                NodeList nodeListResp = element.getElementsByTagName("pista").item(i).getChildNodes();
                Node nodeResp = (Node) nodeListResp.item(0);
                pistasXML[i] = nodeResp.getNodeValue();

            }
            preg.setPistas(pistasXML);
            /*
            String[] pistasXML = new String[element.getElementsByTagName("pista").getLength()];
            for (int i = 0; i < element.getElementsByTagName("pista").getLength(); i++) {
                NodeList nodeListPista   = element.getElementsByTagName("pista").item(i).getChildNodes();
               
                Node nodeResp = (Node) nodeListPista.item(0);
                     pistasXML[i]=nodeResp.getNodeValue();
            }
            preg.setPistas(pistasXML);
             */

        }

        return preg;
    }

    private static PreguntaRelacionar getPreguntaRelacionar(Node node, String tipo, ButtonView bb) {
        //XMLReaderDOM domReader = new XMLReaderDOM();
        PreguntaRelacionar preg = new PreguntaRelacionar(SelectOneMenuTipos.filasColumna.get(tipo), SelectOneMenuTipos.filasColumna.get(tipo));

        if (node.getNodeType() == Node.ELEMENT_NODE) {
            Element element = (Element) node;

            //preg.setEnunciado(getTagValuePregunta("enunciado", element));
            preg.setId("preg_" + bb.getNumber() + 1);
            bb.setNumber(bb.getNumber() + 1);

            String[] respuestasXML = new String[element.getElementsByTagName("respuesta").getLength() / 2]; //la mitad de ellas son respuestas
            String[] solucionesXML = new String[element.getElementsByTagName("respuesta").getLength() / 2]; //la otra mitad son soluciones
            int h = 0;
            for (int i = 0; i < element.getElementsByTagName("respuesta").getLength(); i += 2) {

                NodeList nodeListResp = element.getElementsByTagName("respuesta").item(i).getChildNodes();
                Node nodeResp = (Node) nodeListResp.item(0);

                respuestasXML[h] = nodeResp.getNodeValue();
                h++;
            }
            preg.setColumnaRespuesta(respuestasXML);
            h = 0;
            for (int i = 1; i < element.getElementsByTagName("respuesta").getLength(); i += 2) {

                NodeList nodeListSol = element.getElementsByTagName("respuesta").item(i).getChildNodes();
                Node nodeSol = (Node) nodeListSol.item(0);

                solucionesXML[h] = nodeSol.getNodeValue();
                h++;
            }
            preg.setColumnaSolucion(solucionesXML);

            h = 0;

            /*
            String[] pistasXML = new String[element.getElementsByTagName("pista").getLength()];
            for (int i = 0; i < element.getElementsByTagName("pista").getLength(); i++) {
                NodeList nodeListPista   = element.getElementsByTagName("pista").item(i).getChildNodes();
               
                Node nodeResp = (Node) nodeListPista.item(0);
                     pistasXML[i]=nodeResp.getNodeValue();
            }
            preg.setPistas(pistasXML);
             */
        }

        return preg;
    }

    private static PreguntaRespAbierta getPreguntaRespAbierta(Node node, String tipo, ButtonView bb) {
        //XMLReaderDOM domReader = new XMLReaderDOM();
        PreguntaRespAbierta preg = new PreguntaRespAbierta(SelectOneMenuTipos.lineasEnun.get(tipo));

        if (node.getNodeType() == Node.ELEMENT_NODE) {
            Element element = (Element) node;

            preg.setEnunciado(getTagValuePregunta("enunciado", element));
            preg.setId("preg_" + bb.getNumber() + 1);
            bb.setNumber(bb.getNumber() + 1);

            preg.setTema(getTagValuePregunta("tema", element));

        }

        return preg;
    }

    private static PreguntaContarLetras getPreguntaContarLetras(Node node, String tipo, ButtonView bb) {
        //XMLReaderDOM domReader = new XMLReaderDOM();
        PreguntaContarLetras preg = new PreguntaContarLetras(18);

        if (node.getNodeType() == Node.ELEMENT_NODE) {
            Element element = (Element) node;

            preg.setPalabra(getTagValuePregunta("enunciado", element));
            preg.setId("preg_" + bb.getNumber() + 1);
            bb.setNumber(bb.getNumber() + 1);

            String[] letrasXML = new String[element.getElementsByTagName("respuesta").getLength()];
            String[] numLetrasXML = new String[element.getElementsByTagName("respuesta").getLength()];
            for (int i = 0; i < element.getElementsByTagName("respuesta").getLength(); i++) {

                Element comprobacion = (Element) element.getElementsByTagName("respuesta").item(i);

                if (comprobacion.getChildNodes().getLength() > 0) { //comprobamos si las respuestas tienen texto o no
                    NodeList nodeListResp = element.getElementsByTagName("respuesta").item(i).getChildNodes();
                    Node nodeResp = (Node) nodeListResp.item(0);

                    letrasXML[i] = nodeResp.getNodeValue();

                    Node aux = element.getElementsByTagName("respuesta").item(i).getAttributes().getNamedItem("numLetras");
                    numLetrasXML[i] = aux.getNodeValue();
                } else {
                    i = element.getElementsByTagName("respuesta").getLength();
                }

            }
            preg.setLetras(letrasXML);
            preg.setNumLetras(numLetrasXML);

        }

        return preg;
    }

    private static String getTagValuePregunta(String tag, Element element) {
        NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node node = (Node) nodeList.item(0);
        return node.getNodeValue();
    }

}
