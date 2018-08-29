/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conversor;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;

import org.primefaces.Pregunta.PreguntaCampoTexto;
import org.primefaces.Pregunta.PreguntaCifras;
import org.primefaces.Pregunta.PreguntaContarLetras;
import org.primefaces.Pregunta.PreguntaOpciones;
import org.primefaces.Pregunta.PreguntaPanelesLetras;
import org.primefaces.Pregunta.PreguntaRelacionar;
import org.primefaces.Pregunta.PreguntaRespAbierta;
import org.primefaces.Pregunta.PreguntaSiNo;

import org.primefaces.showcase.view.button.ButtonView;
import org.primefaces.showcase.view.input.SelectOneMenuTipos;

/**
 *
 * @author carlo
 */
import org.w3c.dom.Document;
import org.w3c.dom.Element;

@ManagedBean
@SessionScoped
public class createXML extends ButtonView implements Serializable {
    
    
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    public void ficheroGenerado(String tipo) {
        FacesContext context = FacesContext.getCurrentInstance();

        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Generado con éxito", "El fichero se ha guardado en base de datos"));
    }
    

    public void createXML(ButtonView b) throws Exception {
        int num = 0;

        List<PreguntaOpciones> Preguntas = b.getPreguntas();
        List<PreguntaCifras> PreguntasCifras = b.getPreguntasCifras();
        List<PreguntaCampoTexto> PreguntasCampoTexto = b.getPreguntasCampoTexto();
        List<PreguntaSiNo> PreguntasSiNo = b.getPreguntasSiNo();
        List<PreguntaPanelesLetras> PreguntasPanelesLetras = b.getPreguntasPanelesLetras();
        List<PreguntaRelacionar> PreguntasRelacionar = b.getPreguntasRelacionar();
        List<PreguntaRespAbierta> PreguntasRespAbierta = b.getPreguntasRespAbierta();
        List<PreguntaContarLetras> PreguntasContarLetras = b.getPreguntasContarLetras();
        String autorJuego = b.getAutor();
        String tituloJuego = b.getTitulo();
        String TipoJuego = b.getTipo();

        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

        Document document = documentBuilder.newDocument();

        Element element = document.createElement("Gamificacion");
        document.appendChild(element);

        Attr attr = document.createAttribute("Id");
        attr.setValue("1");
        element.setAttributeNode(attr);

        Element titulo = document.createElement("titulo");
        titulo.appendChild(document.createTextNode(tituloJuego));
        element.appendChild(titulo);

        Element autor = document.createElement("autor");
        autor.appendChild(document.createTextNode(autorJuego));
        element.appendChild(autor);

        Element preguntas = document.createElement("preguntas");
        preguntas.setAttribute("tipo", TipoJuego);
        element.appendChild(preguntas);

        if (Preguntas != null && !Preguntas.isEmpty()) {
            char[] formatoOpciones = new char[]{'A', 'B', 'C', 'D', 'E', 'F'};

            for (PreguntaOpciones model : Preguntas) {
                Element pregunta = document.createElement("pregunta");
                pregunta.setAttribute("id", model.getId());

                if (SelectOneMenuTipos.formatoOp.get(preguntas.getAttribute("tipo")) == 1) {
                    pregunta.setAttribute("sol", String.valueOf(formatoOpciones[Integer.parseInt(model.getSolucion()) - 1]));
                } else {
                    pregunta.setAttribute("sol", model.getSolucion());
                }
                pregunta.setAttribute("comodin50", "");
                pregunta.setAttribute("EmpiezaPor", "");

                Element tema = document.createElement("tema");
                tema.appendChild(document.createTextNode(""));
                pregunta.appendChild(tema);
                Element enunciado = document.createElement("enunciado");
                enunciado.appendChild(document.createTextNode(model.getEnunciado()));
                enunciado.setAttribute("numLineas", Integer.toString(SelectOneMenuTipos.lineasEnun.get(preguntas.getAttribute("tipo"))));
                pregunta.appendChild(enunciado);

                for (String resp : model.getRespuestas()) {
                    num++;

                    Element respuesta = document.createElement("respuesta");
                    respuesta.appendChild(document.createTextNode(resp));
                    respuesta.setAttribute("numLineas", "1");
                    respuesta.setAttribute("num", Integer.toString(num));
                    respuesta.setAttribute("numLetras", "5");
                    pregunta.appendChild(respuesta);

                }
                num = 0;
                Element pista = document.createElement("pista");
                pista.setAttribute("id", "1");
                pista.setAttribute("numLineas", "numLineas");
                pregunta.appendChild(pista);
                preguntas.appendChild(pregunta);
            }
        } else if (PreguntasCampoTexto != null && !PreguntasCampoTexto.isEmpty()) {
            num = 0;
            for (PreguntaCampoTexto model : PreguntasCampoTexto) {
                Element pregunta = document.createElement("pregunta");
                pregunta.setAttribute("id", model.getId());

                for (String sol : model.getSolucion()) {
                    pregunta.setAttribute("sol", sol + "," + pregunta.getAttribute("sol"));
                }
                pregunta.setAttribute("comodin50", "");
                pregunta.setAttribute("EmpiezaPor", "");

                Element tema = document.createElement("tema");
                tema.appendChild(document.createTextNode(""));
                pregunta.appendChild(tema);
                Element enunciado = document.createElement("enunciado");
                enunciado.appendChild(document.createTextNode(model.getEnunciado()));
                enunciado.setAttribute("numLineas", Integer.toString(model.getNumLineas()));
                pregunta.appendChild(enunciado);

                Element pista = document.createElement("pista");

                for (String pistaJuego : model.getPistas()) {
                    pista.setAttribute("id", Integer.toString(num + 1));
                    pista.setAttribute("numLineas", "numLineas");
                    pista.appendChild(document.createTextNode(pistaJuego));

                    pregunta.appendChild(pista);
                    num++;
                }
                preguntas.appendChild(pregunta);
            }
        } else if (PreguntasCifras != null && !PreguntasCifras.isEmpty()) {

            for (PreguntaCifras model : PreguntasCifras) {
                Element pregunta = document.createElement("pregunta");
                pregunta.setAttribute("id", model.getId());
                pregunta.setAttribute("sol", model.getSolucion());
                pregunta.setAttribute("comodin50", "");
                pregunta.setAttribute("EmpiezaPor", "");

                Element tema = document.createElement("tema");
                tema.appendChild(document.createTextNode(""));
                pregunta.appendChild(tema);
                Element enunciado = document.createElement("enunciado");
                enunciado.appendChild(document.createTextNode(model.getEnunciado()));
                enunciado.setAttribute("numLineas", Integer.toString(model.getNumLineas()));
                pregunta.appendChild(enunciado);

                /*for (String resp : model.getRespuestas()) {
                    num++;

                    Element respuesta = document.createElement("respuesta");
                    respuesta.appendChild(document.createTextNode(resp));
                    respuesta.setAttribute("numLineas", "1");
                    respuesta.setAttribute("num", Integer.toString(num));
                    respuesta.setAttribute("numLetras", "5");
                    pregunta.appendChild(respuesta);

                }*/
                //num = 0;
                //Element pista = document.createElement("pista");
                //pista.setAttribute("id", "1");
                //pista.setAttribute("numLineas", "numLineas");
                //pregunta.appendChild(pista);
                preguntas.appendChild(pregunta);
            }
        } else if (PreguntasContarLetras != null && !PreguntasContarLetras.isEmpty()) {
            num = 0;
            for (PreguntaContarLetras model : PreguntasContarLetras) {
                Element pregunta = document.createElement("pregunta");
                pregunta.setAttribute("id", model.getId());

                pregunta.setAttribute("comodin50", "");
                pregunta.setAttribute("EmpiezaPor", "");

                Element tema = document.createElement("tema");
                tema.appendChild(document.createTextNode(""));
                pregunta.appendChild(tema);
                Element enunciado = document.createElement("enunciado");
                enunciado.appendChild(document.createTextNode(model.getPalabra()));
                //enunciado.setAttribute("numLineas", Integer.toString(model.ge));
                pregunta.appendChild(enunciado);

                for (String resp : model.getLetras()) {

                    Element respuesta = document.createElement("respuesta");

                    //respuesta.setAttribute("numLineas", "1");
                    // respuesta.setAttribute("num", Integer.toString(num));
                    if (model.getLetras()[num] != null) {
                        respuesta.appendChild(document.createTextNode(resp));
                        respuesta.setAttribute("numLetras", model.getNumLetras()[num]);
                    } else {
                        break;
                    }
                    num++;
                    pregunta.appendChild(respuesta);

                }

                Element pista = document.createElement("pista");
                pista.setAttribute("id", "1");
                pista.setAttribute("numLineas", "numLineas");
                pregunta.appendChild(pista);
                preguntas.appendChild(pregunta);
            }
        } else if (PreguntasPanelesLetras != null && !PreguntasPanelesLetras.isEmpty()) {

            for (PreguntaPanelesLetras model : PreguntasPanelesLetras) {
                Element pregunta = document.createElement("pregunta");
                pregunta.setAttribute("id", model.getId());
                //pregunta.setAttribute("sol", model.get);
                pregunta.setAttribute("comodin50", "");
                pregunta.setAttribute("EmpiezaPor", "");

                Element tema = document.createElement("tema");
                tema.appendChild(document.createTextNode(""));
                pregunta.appendChild(tema);
                Element enunciado = document.createElement("enunciado");
                enunciado.appendChild(document.createTextNode(model.getEnunciado()));
                enunciado.setAttribute("numLineas", Integer.toString(model.getNumLineas()));
                pregunta.appendChild(enunciado);

                /*for (String resp : model.getRespuestas()) {
                    num++;

                    Element respuesta = document.createElement("respuesta");
                    respuesta.appendChild(document.createTextNode(resp));
                    respuesta.setAttribute("numLineas", "1");
                    respuesta.setAttribute("num", Integer.toString(num));
                    respuesta.setAttribute("numLetras", "5");
                    pregunta.appendChild(respuesta);

                }*/
                num = 1;
                for (String pist : model.getPistas()) {

                    Element pista = document.createElement("pista");
                    pista.setAttribute("id", Integer.toString(num++));
                    pista.setAttribute("numLineas", "numLineas");
                    pista.appendChild(document.createTextNode(pist));
                    pregunta.appendChild(pista);
                    
                }

                preguntas.appendChild(pregunta);
            }
        } else if (PreguntasRelacionar != null && !PreguntasRelacionar.isEmpty()) {
            num = 0;
            for (PreguntaRelacionar model : PreguntasRelacionar) {
                Element pregunta = document.createElement("pregunta");
                pregunta.setAttribute("id", model.getId());
                // pregunta.setAttribute("sol", model.ge));
                pregunta.setAttribute("comodin50", "");
                pregunta.setAttribute("EmpiezaPor", "");

                Element tema = document.createElement("tema");
                tema.appendChild(document.createTextNode(""));
                pregunta.appendChild(tema);
                Element enunciado = document.createElement("enunciado");
                enunciado.appendChild(document.createTextNode("''"));
                //enunciado.setAttribute("numLineas", Integer.toString(model.get));
                pregunta.appendChild(enunciado);

                for (String resp : model.getColumnaRespuesta()) {
                    

                    Element respuesta = document.createElement("respuesta");
                    respuesta.appendChild(document.createTextNode(resp));
                    respuesta.setAttribute("numLineas", "''");
                    respuesta.setAttribute("num", Integer.toString(num));
                    //respuesta.setAttribute("numLetras", "''");
                   
                    
                    Element respuesta2 = document.createElement("respuesta");
                    respuesta2.appendChild(document.createTextNode(model.getColumnaSolucion()[num]));
                    respuesta2.setAttribute("numLineas", "''");
                    respuesta2.setAttribute("num", Integer.toString(num));
                    
                    
                    pregunta.appendChild(respuesta);
                    pregunta.appendChild(respuesta2);
                    num++;
                }
                num = 0;
                /*
                num = 0;
                Element pista = document.createElement("pista");
                pista.setAttribute("id", "1");
                pista.setAttribute("numLineas", "numLineas");
                pregunta.appendChild(pista);
                */
                preguntas.appendChild(pregunta);
            }
        } else if (PreguntasRespAbierta != null && !PreguntasRespAbierta.isEmpty()) {

            for (PreguntaRespAbierta model : PreguntasRespAbierta) {
                Element pregunta = document.createElement("pregunta");
                pregunta.setAttribute("id", model.getId());
                //pregunta.setAttribute("sol", model.ge);
                pregunta.setAttribute("comodin50", "");
                pregunta.setAttribute("EmpiezaPor", "");

                Element tema = document.createElement("tema");
                tema.appendChild(document.createTextNode(model.getTema()));
                pregunta.appendChild(tema);
                Element enunciado = document.createElement("enunciado");
                enunciado.appendChild(document.createTextNode(model.getEnunciado()));
                enunciado.setAttribute("numLineas", Integer.toString(model.getNumLineas()));
                pregunta.appendChild(enunciado);

                /*for (String resp : model.ge)) {
                    num++;

                    Element respuesta = document.createElement("respuesta");
                    respuesta.appendChild(document.createTextNode(resp));
                    respuesta.setAttribute("numLineas", "1");
                    respuesta.setAttribute("num", Integer.toString(num));
                    respuesta.setAttribute("numLetras", "5");
                    pregunta.appendChild(respuesta);

                }*/
                /*
                num = 0;
                Element pista = document.createElement("pista");
                pista.setAttribute("id", "1");
                pista.setAttribute("numLineas", "numLineas");
                pregunta.appendChild(pista);
                */
                preguntas.appendChild(pregunta);
            }
        } else if (PreguntasSiNo != null && !PreguntasSiNo.isEmpty()) {
            num = 0;
            for (PreguntaSiNo model : PreguntasSiNo) {
                Element pregunta = document.createElement("pregunta");
                pregunta.setAttribute("id", model.getId());

                for (String sol : model.getSolucion()) {
                    pregunta.setAttribute("sol", sol);
                }
                pregunta.setAttribute("comodin50", "");
                pregunta.setAttribute("EmpiezaPor", "");

                Element tema = document.createElement("tema");
                tema.appendChild(document.createTextNode(""));
                pregunta.appendChild(tema);
                Element enunciado = document.createElement("enunciado");
                enunciado.appendChild(document.createTextNode(model.getEnunciado()));
                enunciado.setAttribute("numLineas", Integer.toString(model.getLineasEnunciado()));
                pregunta.appendChild(enunciado);

                for (String resp : model.getRespuestas()) {
                    

                    Element respuesta = document.createElement("respuesta");
                    respuesta.appendChild(document.createTextNode(resp));
                    //respuesta.setAttribute("numLineas", "1");
                    respuesta.setAttribute("num", Integer.toString(num));
                    respuesta.setAttribute("sol", model.getSolucion()[num]);
                    //respuesta.setAttribute("numLetras", "5");
                    pregunta.appendChild(respuesta);
                    num++;
                }
                num = 0;
                Element pista = document.createElement("pista");
                pista.setAttribute("id", "1");
                pista.setAttribute("numLineas", "numLineas");
                pregunta.appendChild(pista);
                preguntas.appendChild(pregunta);
            }
        }

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(document);

        StreamResult streamResult = new StreamResult(new File("C:\\Users\\carlo\\Desktop\\Ingeniería de Telecomunicaciones\\4º CUARTO\\TFG\\prueba\\prueba.xml"));

        transformer.transform(source, streamResult);
        
        b.deleteAllLists();
        
        ficheroGenerado(TipoJuego);
    }
    
    

}
