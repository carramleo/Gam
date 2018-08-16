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
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
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

/**
 *
 * @author carlo
 */
import org.w3c.dom.Document;
import org.w3c.dom.Element;

@ManagedBean
@SessionScoped
public class createXML extends ButtonView implements Serializable {

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

            for (PreguntaOpciones model : Preguntas) {
                Element pregunta = document.createElement("pregunta");
                pregunta.setAttribute("id", Integer.toString(model.getId()));
                pregunta.setAttribute("sol", model.getSolucion());
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

            for (PreguntaCampoTexto model : PreguntasCampoTexto) {
                Element pregunta = document.createElement("pregunta");
                pregunta.setAttribute("id", Integer.toString(model.getId()));

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
                enunciado.setAttribute("numLineas", Integer.toString(model.getNumLineas()));
                pregunta.appendChild(enunciado);

                //for (String resp : model.getRespuestas()) {
                //    num++;
                //   Element respuesta = document.createElement("respuesta");
                //   respuesta.appendChild(document.createTextNode(resp));
                //   respuesta.setAttribute("numLineas", "1");
                //   respuesta.setAttribute("num", Integer.toString(num));
                //   respuesta.setAttribute("numLetras", "5");
                //   pregunta.appendChild(respuesta);
                //}
                num = 0;
                Element pista = document.createElement("pista");
                pista.setAttribute("id", "1");
                pista.setAttribute("numLineas", "numLineas");

                for (String pistaJuego : model.getPistas()) {
                    pista.appendChild(document.createTextNode(pistaJuego));
                }
                pregunta.appendChild(pista);
                preguntas.appendChild(pregunta);
            }
        } else if (PreguntasCifras != null && !PreguntasCifras.isEmpty()) {

            for (PreguntaCifras model : PreguntasCifras) {
                Element pregunta = document.createElement("pregunta");
                pregunta.setAttribute("id", Integer.toString(model.getId()));
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
                num = 0;
                //Element pista = document.createElement("pista");
                //pista.setAttribute("id", "1");
                //pista.setAttribute("numLineas", "numLineas");
                //pregunta.appendChild(pista);
                preguntas.appendChild(pregunta);
            }
        } else if (PreguntasContarLetras != null && !PreguntasContarLetras.isEmpty()) {

            for (PreguntaContarLetras model : PreguntasContarLetras) {
                Element pregunta = document.createElement("pregunta");
                pregunta.setAttribute("id", Integer.toString(model.getId()));

                for (String letras : model.getNumLetras()) {
                    pregunta.setAttribute("sol", letras);
                }
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
        } else if (PreguntasPanelesLetras != null && !PreguntasPanelesLetras.isEmpty()) {

            for (PreguntaPanelesLetras model : PreguntasPanelesLetras) {
                Element pregunta = document.createElement("pregunta");
                pregunta.setAttribute("id", Integer.toString(model.getId()));
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
        } else if (PreguntasRelacionar != null && !PreguntasRelacionar.isEmpty()) {

            for (PreguntaRelacionar model : PreguntasRelacionar) {
                Element pregunta = document.createElement("pregunta");
                pregunta.setAttribute("id", Integer.toString(model.getId()));
                // pregunta.setAttribute("sol", model.ge));
                pregunta.setAttribute("comodin50", "");
                pregunta.setAttribute("EmpiezaPor", "");

                Element tema = document.createElement("tema");
                tema.appendChild(document.createTextNode(""));
                pregunta.appendChild(tema);
                Element enunciado = document.createElement("enunciado");
                enunciado.appendChild(document.createTextNode(model.getEnunciado()));
                //enunciado.setAttribute("numLineas", Integer.toString(model.get));
                pregunta.appendChild(enunciado);

                for (String resp : model.getColumnaRespuesta()) {
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
        } else if (PreguntasRespAbierta != null && !PreguntasRespAbierta.isEmpty()) {

            for (PreguntaRespAbierta model : PreguntasRespAbierta) {
                Element pregunta = document.createElement("pregunta");
                pregunta.setAttribute("id", Integer.toString(model.getId()));
                //pregunta.setAttribute("sol", model.ge);
                pregunta.setAttribute("comodin50", "");
                pregunta.setAttribute("EmpiezaPor", "");

                Element tema = document.createElement("tema");
                tema.appendChild(document.createTextNode(""));
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
                num = 0;
                Element pista = document.createElement("pista");
                pista.setAttribute("id", "1");
                pista.setAttribute("numLineas", "numLineas");
                pregunta.appendChild(pista);
                preguntas.appendChild(pregunta);
            }
        } else if (PreguntasSiNo != null && !PreguntasSiNo.isEmpty()) {

            for (PreguntaSiNo model : PreguntasSiNo) {
                Element pregunta = document.createElement("pregunta");
                pregunta.setAttribute("id", Integer.toString(model.getId()));

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
        }

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(document);

        StreamResult streamResult = new StreamResult(new File("C:\\Users\\carlo\\Desktop\\Ingeniería de Telecomunicaciones\\4º CUARTO\\TFG\\prueba\\prueba.xml"));

        transformer.transform(source, streamResult);
    }

}
