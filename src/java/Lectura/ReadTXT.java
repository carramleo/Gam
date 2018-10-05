/*
* Clase que lee un fichero de tipo TXT con formato Juego Tipo 1 del proyecto AJDA
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
import org.apache.jasper.tagplugins.jstl.core.ForEach;
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
import org.primefaces.showcase.view.AdministrarPreguntas.AdminPreguntas;
import org.primefaces.showcase.view.MapaTipos.MapaTiposTXT;
import org.primefaces.showcase.view.MapaTipos.MenuMapTiposOpciones;
import org.primefaces.showcase.view.MapaTipos.MenuMapTipos;
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

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void saveMessage() {
        FacesContext context = FacesContext.getCurrentInstance();

        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al importar", "No has seleccionado tipo de juego"));
    }

    public void errorFile() {
        FacesContext context = FacesContext.getCurrentInstance();

        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al importar", "No ha seleccionado un fichero"));
    }

    public void tipoCompatible() {
        FacesContext context = FacesContext.getCurrentInstance();

        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al importar", "Tipo de juego incompatible"));
    }

    public void readFile(UploadedFile fichero, AdminPreguntas b, MenuMapTipos tipos, MenuMapTiposOpciones opcionesTipos) throws SAXException, IOException, ParserConfigurationException {
        boolean compatibleTipoOpciones = false;
        //Comprobamos que el fichero no es nulo  ni vacio.
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
            String tipoSeleccionado = tipos.getTipo();
            List<String> respuestas = new ArrayList<String>();
            BufferedReader in;
            //Si se ha seleccionado un tipo en la página web podemos seguir leyendo.
            if (TipoJuego != null && !TipoJuego.isEmpty() && tipoSeleccionado != null && !tipoSeleccionado.isEmpty()) {

                //Leemos el fichero línea a línea y en formato UTF-8.
                in = new BufferedReader(new InputStreamReader(ficheroImp, "UTF-8"));

                String line1 = null;
                ArrayList<String> leerOtraVez = new ArrayList<String>();

                int numPreguntas = 0;
                int contadorLineasEnunciado = 0;
                int numLinea = 0;
                //Variable para transformar las respuesta elegida en caracteres alfanuméricos.
                char[] formatoOpciones = new char[]{'A', 'B', 'C', 'D', 'E', 'F'};
                String aux = "";
                while ((line1 = in.readLine()) != null) {

                    aux = line1;
                    leerOtraVez.add(line1);

                }
                in.close();

                aux = aux.substring(1, aux.length() - 1);
                String comp = "Compatibilidad ";
                String tipoImportado = "";
                String lineasEnun = "";
                String numResp = "";
                String posAutor = "";
                String posTitulo = "";
                String posTema = "";
                for (Map.Entry<String, String[]> key : MapaTiposTXT.filtroTXT.entrySet()) {

                    if (aux.equals(comp + key.getKey())) {
                        compatibleTipoOpciones = true;

                        tipoImportado = "Tipo" + key.getKey();

                        lineasEnun = key.getValue()[0];
                        numResp = key.getValue()[1];
                        posAutor = key.getValue()[2];
                        posTitulo = key.getValue()[3];
                        posTema = key.getValue()[4];
                    }
                }

                if (compatibleTipoOpciones) {

                    in = new BufferedReader(new InputStreamReader(ficheroImp, "UTF-8"));

                    for (String line2 : leerOtraVez) {

                        if (numLinea == Integer.parseInt(posAutor)) { //Comprobamos si estamos en la linea del autor.
                            String[] aut = line2.split(":");
                            autor = aut[1].substring(0, aut[1].length() - 1);
                            b.setAutor(autor);
                        } else if (numLinea == Integer.parseInt(posTema)) {   //Comprobamos si estamos en la linea del titulo.
                            String tema = line2.substring(1, line2.length() - 1);
                            b.setTemaJuego(tema);

                        } else if (numLinea == Integer.parseInt(posTitulo)) { //Comprobamos si estamos en la linea del titulo.
                            String[] tit = line2.split(":");
                            titulo = tit[1].substring(0, tit[1].length() - 1);
                            b.setTitulo(titulo);
                        } else {
                            int sigPreg = line2.indexOf("PREGUNTA"); //Si la linea actual es PREGUNTA estamos ante una nueva pregunta.
                            if (sigPreg != -1) { //Si esxiste la  linea, creamos una nueva pregunta en el bean.
                                numPreguntas++;
                                contadorLineasEnunciado = 0;
                                respuestas.removeAll(respuestas);
                                PreguntaOpciones pregAdd = new PreguntaOpciones(MenuMapTiposOpciones.numOpciones.get(tipoImportado), MenuMapTiposOpciones.formatoOp.get(tipoImportado), MenuMapTiposOpciones.lineasEnun.get(tipoImportado));

                                pregAdd.setId("preg_" + b.getNumber() + 1);
                                b.setNumber(b.getNumber() + 1);
                                Preguntas.add(pregAdd);
                            } else { //Si no, estamos ante el enunciado o sus respuestas.
                                PreguntaOpciones pregCreada = Preguntas.get(Preguntas.size() - 1);

                                if (contadorLineasEnunciado >= 0 && contadorLineasEnunciado < Integer.parseInt(lineasEnun)) { //Comprobamos si estamos leyendo el enunciado
                                    String enun = line2.substring(1, line2.length() - 1);
                                    if (pregCreada.getEnunciado() != null) {
                                        pregCreada.setEnunciado(pregCreada.getEnunciado() + enun);
                                    } else {
                                        pregCreada.setEnunciado(enun);
                                    }

                                } else if (contadorLineasEnunciado >= Integer.parseInt(lineasEnun) && contadorLineasEnunciado < Integer.parseInt(lineasEnun) + Integer.parseInt(numResp)) { //Comprobamos si estamos leyendo las respuestas
                                    String resp = line2.substring(1, line2.length() - 1);
                                    respuestas.add(resp);
                                } else if (contadorLineasEnunciado == Integer.parseInt(lineasEnun) + Integer.parseInt(numResp)) { //Comprobamos si estamos leyendo la solución.

                                    pregCreada.setRespuestas(respuestas);

                                    int numSol = 0;
                                    if (line2.startsWith("'")) {
                                        String sol = line2.substring(1, line2.length() - 1);

                                        for (int j = 0; j < formatoOpciones.length; j++) {
                                            if (String.valueOf(formatoOpciones[j]).equals(sol)) {
                                                numSol = j + 1;
                                            }
                                        }
                                        pregCreada.setSolucion(String.valueOf(numSol));
                                    } else {
                                        pregCreada.setSolucion(line2);
                                    }
                                    respuestas = new ArrayList<String>();
                                }
                                contadorLineasEnunciado++;

                            }
                        }
                        numLinea++;

                    }
                } else {
                    tipoCompatible();
                }

                in.close();

            } else {
                saveMessage();
            }
        }
    }

}
