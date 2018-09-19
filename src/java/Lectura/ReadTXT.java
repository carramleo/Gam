/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lectura;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
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
public class ReadTXT {

    private boolean compatible = false;

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

    public void readFile(UploadedFile fichero, ButtonView b, SelectOneMenuView tipos, SelectOneMenuTipos opcionesTipos) throws SAXException, IOException, ParserConfigurationException {

        if (fichero.getFileName() == null || fichero.getFileName().isEmpty()) {
            errorFile();
        } else {

            InputStream ficheroImp = fichero.getInputstream();

            List<PreguntaOpciones> Preguntas = b.getPreguntas();
            List<PreguntaCifras> PreguntasCifras = b.getPreguntasCifras();
            List<PreguntaCampoTexto> PreguntasCampoTexto = b.getPreguntasCampoTexto();
            List<PreguntaSiNo> PreguntasSiNo = b.getPreguntasSiNo();
            List<PreguntaPanelesLetras> PreguntasPanelesLetras = b.getPreguntasPanelesLetras();
            List<PreguntaRelacionar> PreguntasRelacionar = b.getPreguntasRelacionar();
            List<PreguntaRespAbierta> PreguntasRespAbierta = b.getPreguntasRespAbierta();
            List<PreguntaContarLetras> PreguntasContarLetras = b.getPreguntasContarLetras();
            String autor = b.getAutor();
            String titulo = b.getTitulo();
            String TipoJuego = b.getTipo();
            List<String> respuestas = new ArrayList<String>();

            String tipoSeleccionado = tipos.getTipo();  //tipo  seleccionado en el menu de la izq

            Map<String, String> mapaTiposCompatibles = tipos.getData().get(tipoSeleccionado);   //obtengo el mapa de los tipos compatibles que acepta

            //BufferedReader in = new BufferedReader(new InputStreamReader(ficheroImp));
            BufferedReader in = new BufferedReader(new InputStreamReader(ficheroImp, "UTF-8"));
            /*          
            String tipo = document.getElementsByTagName("preguntas").item(0).getAttributes().getNamedItem("tipo").getNodeValue();

            for (String key : mapaTiposCompatibles.keySet()) {
                if (mapaTiposCompatibles.get(key).equals(tipo)) {             //recorro el mapa buscando si el tipo del fichero importado aparece en ese mapa
                    compatible = true;
                }
            }
             */
            // if (compatible) {
            String line2;
            int numPreguntas = 0;
            int contadorLineasEnunciado = 0;
            int numLinea = 0;
            char[] formatoOpciones = new char[]{'A', 'B', 'C', 'D', 'E', 'F'};
            while ((line2 = in.readLine()) != null) {

                if (numLinea == 0) {
                    String[] aut = line2.split(":");
                    autor = aut[1].substring(0, aut[1].length() - 1);
                    b.setAutor(autor);
                } else if (numLinea == 1) {
                    String[] tit = line2.split(":");
                    titulo = tit[1].substring(0, tit[1].length() - 1);
                    b.setTitulo(titulo);
                } else {
                    int sigPreg = line2.indexOf("PREGUNTA");
                    if (sigPreg != -1) {
                        numPreguntas++;
                        contadorLineasEnunciado = 0;
                        respuestas.removeAll(respuestas);
                        PreguntaOpciones pregAdd = new PreguntaOpciones(SelectOneMenuTipos.numOpciones.get("Tipo1"), SelectOneMenuTipos.formatoOp.get("Tipo1"), SelectOneMenuTipos.lineasEnun.get("Tipo1"));

                        pregAdd.setId("preg_" + b.getNumber() + 1);
                        b.setNumber(b.getNumber() + 1);
                        Preguntas.add(pregAdd);
                    } else {
                        PreguntaOpciones pregCreada = Preguntas.get(Preguntas.size() - 1);
                        String enun = line2.substring(1, line2.length() - 1);
                        String resp = line2.substring(1, line2.length() - 1);
                        if (contadorLineasEnunciado >= 0 && contadorLineasEnunciado < 3) {

                            if (pregCreada.getEnunciado() != null) {
                                pregCreada.setEnunciado(pregCreada.getEnunciado() + enun);
                            } else {
                                pregCreada.setEnunciado(enun);
                            }

                        } else if (contadorLineasEnunciado >= 3 && contadorLineasEnunciado < 7) {

                            respuestas.add(resp);
                        } else if (contadorLineasEnunciado == 7) {

                            pregCreada.setRespuestas(respuestas);

                            int numSol = 0;
                            String sol = line2.substring(1, line2.length() - 1);
                            for (int j = 0; j < formatoOpciones.length; j++) {
                                if (String.valueOf(formatoOpciones[j]).equals(sol)) {
                                    numSol = j + 1;
                                }
                            }
                            pregCreada.setSolucion(String.valueOf(numSol));
                            respuestas=new ArrayList<String>();
                        }
                        contadorLineasEnunciado++;

                    }
                }
                numLinea++;
            }

            in.close();

            // } else {
            //}
        }
    }

    public boolean isCompatible() {
        return compatible;
    }

    public void setCompatible(boolean compatible) {
        this.compatible = compatible;
    }

}
