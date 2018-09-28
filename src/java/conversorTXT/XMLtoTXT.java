package conversorTXT;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.Serializable;
import conversorTXT.FichTipo1;
import conversorTXT.FichTipo11;
import conversorTXT.FichTipo2;
import conversorTXT.FichTipo3;
import conversorTXT.FichTipo4;
import conversorTXT.FichTipo6;
import conversorTXT.FichTipo7;
import conversorTXT.FichTipo8;
import conversorTXT.FichTipoPalabras;
import conversorTXT.FichTipoRelacionar;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

@ManagedBean
@SessionScoped
public class XMLtoTXT implements Serializable {

    public static PrintStream ps;

    public void conversorXMLtoTXT(InputStream XML) {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(XML);
            FacesContext context = FacesContext.getCurrentInstance();
            ExternalContext externalContext = context.getExternalContext();
            externalContext.responseReset();
            externalContext.setResponseContentType("text/plain; charset=UTF-8");
            externalContext.setResponseHeader("Content-disposition", "attachment;filename=" + "juego.txt");
            OutputStream out = externalContext.getResponseOutputStream();

            context.responseComplete();

            NodeList Autor = doc.getElementsByTagName("autor");  //Obtenemos el nombre del autor del XML
            Node nombre = Autor.item(0);
            NodeList Titulo = doc.getElementsByTagName("titulo");
            Node tema = Titulo.item(0);
            int numId = 0;
            if (nombre.getNodeType() == Node.ELEMENT_NODE && tema.getNodeType() == Node.ELEMENT_NODE) {   //Imprimimos el nombre del autor en el txt

                NodeList Preguntas = doc.getElementsByTagName("preguntas");

                Node preg = Preguntas.item(0);

                NodeList Pregunta = doc.getElementsByTagName("pregunta");   //creamos la lista de nodos de pregunta
                NodeList palabras = doc.getElementsByTagName("palabra");

                if (preg.getAttributes().getNamedItem("tipo").getTextContent().equals("TipoSabios")
                        || preg.getAttributes().getNamedItem("tipo").getTextContent().equals("Tipo14")
                        || preg.getAttributes().getNamedItem("tipo").getTextContent().equals("Tipo123")
                        || preg.getAttributes().getNamedItem("tipo").getTextContent().equals("TipoPocasPalabras")
                        || preg.getAttributes().getNamedItem("tipo").getTextContent().equals("TipoPistas")
                        || preg.getAttributes().getNamedItem("tipo").getTextContent().equals("TipoLetra")
                        || preg.getAttributes().getNamedItem("tipo").getTextContent().equals("TipoAntesyDespues")
                        || preg.getAttributes().getNamedItem("tipo").getTextContent().equals("TipoPartextodo")
                        || preg.getAttributes().getNamedItem("tipo").getTextContent().equals("TipoLlamada")) {

                    //FileOutputStream os = new FileOutputStream("examen.txt", true);
                    ps = new PrintStream(out, false, "UTF-8");
                } else if (preg.getAttributes().getNamedItem("tipo").getTextContent().equals("identity")) {
                    //FileOutputStream os = new FileOutputStream("examen.txt", true);
                    ps = new PrintStream(out, false, "UTF-8");
                    ps.println("'TEMA: " + tema.getTextContent() + "'");
                } else if (preg.getAttributes().getNamedItem("tipo").getTextContent().equals("palabraImposible")) {
                    //FileOutputStream os = new FileOutputStream("examen.txt", true);
                    ps = new PrintStream(out, false, "UTF-8");
                    ps.println("60");
                    ps.println("'Compatibilidad " + preg.getAttributes().getNamedItem("tipo").getTextContent() + "'");
                    ps.println("'AUTOR/A: " + nombre.getTextContent() + "'");
                } else {

                    //FileOutputStream os = new FileOutputStream("examen.txt", true);
                    ps = new PrintStream(out, false, "UTF-8");
                    ps.println("'AUTOR/A: " + nombre.getTextContent() + "'");
                    ps.println("'TEMA: " + tema.getTextContent() + "'");
                }

                for (int i = 0; i < Pregunta.getLength(); i++) {

                    Node p = Pregunta.item(i);

                    if (preg.getNodeType() == Node.ELEMENT_NODE && p.getNodeType() == Node.ELEMENT_NODE) {

                        Element preguntaTipo = (Element) preg;
                        Element pregunta = (Element) p;
                        String id = Integer.toString(numId++);

                        if (preguntaTipo.getAttribute("tipo").equals("Tipo1")
                                || preguntaTipo.getAttribute("tipo").equals("Tipo5")
                                || preguntaTipo.getAttribute("tipo").equals("Tipo12")
                                || preguntaTipo.getAttribute("tipo").equals("Tipo13")
                                || preguntaTipo.getAttribute("tipo").equals("Tipo14")
                                || preguntaTipo.getAttribute("tipo").equals("Tipo15")
                                || preguntaTipo.getAttribute("tipo").equals("Tipo17")
                                || preguntaTipo.getAttribute("tipo").equals("Tipo19")
                                || preguntaTipo.getAttribute("tipo").equals("Tipo50x50")
                                || preguntaTipo.getAttribute("tipo").equals("Tipoduelo")
                                || preguntaTipo.getAttribute("tipo").equals("TipoBombaDorada")
                                || preguntaTipo.getAttribute("tipo").equals("TipoBombaPlateada")
                                || preguntaTipo.getAttribute("tipo").equals("TipoArcoiris")
                                || preguntaTipo.getAttribute("tipo").equals("TipoSabios")
                                || preguntaTipo.getAttribute("tipo").equals("TipoVoF")
                                || preguntaTipo.getAttribute("tipo").equals("TipoJurado")
                                || preguntaTipo.getAttribute("tipo").equals("Tipo123")
                                || preguntaTipo.getAttribute("tipo").equals("TipoTrampa")
                                || preguntaTipo.getAttribute("tipo").equals("TipoLetra")) {

                            FichTipo1 tipo1 = new FichTipo1();
                            tipo1.imprimirTipo1(id, preguntaTipo, pregunta, ps);

                        } else if (preguntaTipo.getAttribute("tipo").equals("Tipo2")
                                || preguntaTipo.getAttribute("tipo").equals("Tipo18")
                                || preguntaTipo.getAttribute("tipo").equals("TipoLetritas")) {

                            FichTipo2 tipo2 = new FichTipo2();
                            tipo2.imprimirTipo2(id, preguntaTipo, pregunta, ps);

                        } else if (preguntaTipo.getAttribute("tipo").equals("Tipo3")
                                || preguntaTipo.getAttribute("tipo").equals("Tipo9")
                                || preguntaTipo.getAttribute("tipo").equals("TipoPatata")) {

                            FichTipo3 tipo3 = new FichTipo3();
                            tipo3.imprimirTipo3(id, preguntaTipo, pregunta, ps);

                        } else if (preguntaTipo.getAttribute("tipo").equals("Tipo4")
                                || preguntaTipo.getAttribute("tipo").equals("TipoSabio")) {

                            FichTipo4 tipo4 = new FichTipo4();
                            tipo4.imprimirTipo4(id, preguntaTipo, pregunta, ps);

                        } else if (preguntaTipo.getAttribute("tipo").equals("Tipo6")
                                || preguntaTipo.getAttribute("tipo").equals("Yipo2palabras")
                                || preguntaTipo.getAttribute("tipo").equals("TipoPistas")) {

                            FichTipo6 tipo6 = new FichTipo6();
                            tipo6.imprimirTipo6(id, preguntaTipo, pregunta, ps);

                        } else if (preguntaTipo.getAttribute("tipo").equals("Tipo7")) {

                            FichTipo7 tipo7 = new FichTipo7();
                            tipo7.imprimirTipo7(id, pregunta, ps);

                        } else if (preguntaTipo.getAttribute("tipo").equals("Tipo8")
                                || preguntaTipo.getAttribute("tipo").equals("Tipo10")) {

                            FichTipo8 tipo8 = new FichTipo8();
                            tipo8.imprimirTipo8(preguntaTipo, nombre.getTextContent(), tema.getTextContent(), id, pregunta, ps);

                        } else if (preguntaTipo.getAttribute("tipo").equals("Tipo11")
                                || preguntaTipo.getAttribute("tipo").equals("Tipo16")) {

                            FichTipo11 tipo11 = new FichTipo11();
                            tipo11.imprimirTipo11(id, pregunta, ps);

                        } else if (preguntaTipo.getAttribute("tipo").equals("Tipocoincidencias")
                                || preguntaTipo.getAttribute("tipo").equals("TipoDueloPalabras")
                                || preguntaTipo.getAttribute("tipo").equals("TipoPocasPalabras")
                                || preguntaTipo.getAttribute("tipo").equals("TipoLingo")
                                || preguntaTipo.getAttribute("tipo").equals("TipoCuantasLetras")) {

                            FichTipoPalabras tipoPalabras = new FichTipoPalabras();
                            tipoPalabras.imprimirTipoPalabras(id, palabras, preguntaTipo, pregunta, ps);

                        } else if (preguntaTipo.getAttribute("tipo").equals("TipoAntesyDespues")
                                || preguntaTipo.getAttribute("tipo").equals("TipoIdentity")
                                || preguntaTipo.getAttribute("tipo").equals("TipoPartextodo")
                                || preguntaTipo.getAttribute("tipo").equals("TipoLlamada")
                                || preguntaTipo.getAttribute("tipo").equals("TipoOrdenaFrase")
                                || preguntaTipo.getAttribute("tipo").equals("TipoImposible")) {

                            FichTipoRelacionar tipoRelacionar = new FichTipoRelacionar();
                            tipoRelacionar.imprimirTipoRelacionar(id, preguntaTipo, pregunta, ps);

                        }

                    }

                }
                if (preg.getAttributes().getNamedItem("tipo").getTextContent().equals("TipoSabios")
                        || preg.getAttributes().getNamedItem("tipo").getTextContent().equals("Tipo14")
                        || preg.getAttributes().getNamedItem("tipo").getTextContent().equals("Tipo123")
                        || preg.getAttributes().getNamedItem("tipo").getTextContent().equals("TipoPocasPalabras")
                        || preg.getAttributes().getNamedItem("tipo").getTextContent().equals("TipoPistas")
                        || preg.getAttributes().getNamedItem("tipo").getTextContent().equals("TipoLetra")
                        || preg.getAttributes().getNamedItem("tipo").getTextContent().equals("TipoAntesyDespues")
                        || preg.getAttributes().getNamedItem("tipo").getTextContent().equals("TipoPartextodo")
                        || preg.getAttributes().getNamedItem("tipo").getTextContent().equals("TipoLlamada")) {

                    ps.println("'AUTOR/A: " + nombre.getTextContent() + "'");
                    ps.println("'" + tema.getTextContent() + "'");
                } else if (preg.getAttributes().getNamedItem("tipo").getTextContent().equals("TipoIdentity")) {

                    ps.println("'AUTOR/A: " + nombre.getTextContent() + "'");
                } else if (preg.getAttributes().getNamedItem("tipo").getTextContent().equals("TipoImposible")) {

                    ps.println("'TEMA: " + tema.getTextContent() + "'");
                }
                //FileOutputStream os = new FileOutputStream("examen.txt", true);
                //ps = new PrintStream(out, false, "UTF-8");
                //ps.println("'Compatibilidad " + preg.getAttributes().getNamedItem("tipo").getTextContent() + "'");
                
                
                
                externalContext.responseFlushBuffer();

                out.close();

            }
        } catch (ParserConfigurationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SAXException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
