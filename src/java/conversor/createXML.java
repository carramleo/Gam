/*
 * Clase que genera un fichero XML en función del tipo seleccionado en la aplicación y del contenido del bean, el cual
 *  contiene toda la información de las preguntas, respuestas, soluciones, autor y título.
 */
package conversor;

import java.io.File;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
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
import org.primefaces.context.RequestContext;

import org.primefaces.showcase.view.AdministrarPreguntas.AdminPreguntas;
import org.primefaces.showcase.view.MapaTipos.MenuMapTiposOpciones;

/**
 *
 * @author carlo
 */
import org.w3c.dom.Document;
import org.w3c.dom.Element;

@ManagedBean
@SessionScoped
public class createXML extends AdminPreguntas implements Serializable {

    private String message;
    private boolean numRespOk;

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
    //Mensaje de error que sale al no corresponderse el número de respuestas actual con el tipo de juego elegido.
    public void errorGenerado(String tipo, int numResp) {
        FacesContext context = FacesContext.getCurrentInstance();

        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Fallo al generar", "El numero de respuestas del juego " +tipo+ " es incorrecto."
                + " Nº respuestas = " + numResp+" "));
    }
    
    public void errorSinPreguntas() {
        FacesContext context = FacesContext.getCurrentInstance();

        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Fallo al generar", "Para generar un fichero XML se necesita al menos una pregunta."));
    }
    
    
    //Función para crear el XML, recibeel bean con las preguntas.
    public void createXML(AdminPreguntas b) throws Exception {
        int num = 0;
        numRespOk=false;  //Comprueba q el numero de rspuestas sea el correcto en función del tipo elegido.
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
        String temaJuego = b.getTemaJuego();

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
        
        Element temaJug = document.createElement("tema");
        temaJug.appendChild(document.createTextNode(temaJuego));
        element.appendChild(temaJug);

        Element preguntas = document.createElement("preguntas");
        preguntas.setAttribute("tipo", TipoJuego);
        element.appendChild(preguntas);

        if (Preguntas != null && !Preguntas.isEmpty()) {
            char[] formatoOpciones = new char[]{'A', 'B', 'C', 'D', 'E', 'F'};

            for (PreguntaOpciones model : Preguntas) {
                Element pregunta = document.createElement("pregunta");
                pregunta.setAttribute("id", model.getId());
                
                if(MenuMapTiposOpciones.numOpciones.get(preguntas.getAttribute("tipo")) == model.getRespuestas().size()){
                if (MenuMapTiposOpciones.formatoOp.get(preguntas.getAttribute("tipo")) == 1) {
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
                enunciado.setAttribute("numLineas", Integer.toString(MenuMapTiposOpciones.lineasEnun.get(preguntas.getAttribute("tipo"))));
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
                else{
                    
                    errorGenerado(TipoJuego, MenuMapTiposOpciones.numOpciones.get(preguntas.getAttribute("tipo")));
                    numRespOk=true;
                    
                }
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
        }else{
            errorSinPreguntas();
            
        }
        
        if(!numRespOk){
        
         
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(document);

        //Pedimos al servlet que nos mande u fichero descargable de tipo XML para escribir contenido en él.
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        externalContext.responseReset();
        externalContext.setResponseContentType("text/xml");
        externalContext.setResponseHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
        externalContext.setResponseHeader("Pragma", "public");
        externalContext.setResponseHeader("Content-disposition", "attachment;filename=" + "juego_" +TipoJuego +".xml");
       
        OutputStream out = externalContext.getResponseOutputStream();
       
        transformer.transform(source, new StreamResult(out));
         //Pedimos al servlet que nos descargue el fichero en la carpeta de descargas de nuestro equipo.
        externalContext.responseFlushBuffer();

        out.close();
        //b.deleteAllLists();
        ficheroGenerado(TipoJuego);
        }
    }

}
