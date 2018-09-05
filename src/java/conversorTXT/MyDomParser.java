package conversorTXT;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.Serializable;
import conversorTXT.Type1;
import conversorTXT.Type11;
import conversorTXT.Type2;
import conversorTXT.Type3;
import conversorTXT.Type4;
import conversorTXT.Type6;
import conversorTXT.Type7;
import conversorTXT.Type8;
import conversorTXT.TypePalabras;
import conversorTXT.TypeRelacionar;
import java.io.OutputStream;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

@ManagedBean
@SessionScoped
public class MyDomParser implements Serializable {

    public static PrintStream ps;

    public void conversorXMLtoTXT(InputStream XML) {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        try {
            DocumentBuilder builder = factory.newDocumentBuilder();

            Document doc = builder.parse(XML);

            FacesContext context = FacesContext.getCurrentInstance();
            ExternalContext externalContext = context.getExternalContext();
            externalContext.responseReset();
            externalContext.setResponseContentType("application/txt");
            externalContext.setResponseHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
            externalContext.setResponseHeader("Pragma", "public");
            externalContext.setResponseHeader("Content-disposition", "attachment;filename=" + "juego.txt");
            OutputStream out = externalContext.getResponseOutputStream();
            
            

            NodeList Autor = doc.getElementsByTagName("autor");  //Obtenemos el nombre del autor del XML
            Node nombre = Autor.item(0);
            NodeList Titulo = doc.getElementsByTagName("titulo");
            Node tema = Titulo.item(0);
            int numId=0;
            if (nombre.getNodeType() == Node.ELEMENT_NODE && tema.getNodeType() == Node.ELEMENT_NODE) {   //Imprimimos el nombre del autor en el txt

                NodeList Preguntas = doc.getElementsByTagName("preguntas");

                Node preg = Preguntas.item(0);

                NodeList Pregunta = doc.getElementsByTagName("pregunta");   //creamos la lista de nodos de pregunta
                NodeList palabras = doc.getElementsByTagName("palabra");

                if (preg.getAttributes().getNamedItem("tipo").getTextContent().equals("lossabios")
                        || preg.getAttributes().getNamedItem("tipo").getTextContent().equals("14")
                        || preg.getAttributes().getNamedItem("tipo").getTextContent().equals("123")
                        || preg.getAttributes().getNamedItem("tipo").getTextContent().equals("pocasPalabras")
                        || preg.getAttributes().getNamedItem("tipo").getTextContent().equals("pistas")
                        || preg.getAttributes().getNamedItem("tipo").getTextContent().equals("letraAletra")
                        || preg.getAttributes().getNamedItem("tipo").getTextContent().equals("AntesyDespues")
                        || preg.getAttributes().getNamedItem("tipo").getTextContent().equals("partextodo")
                        || preg.getAttributes().getNamedItem("tipo").getTextContent().equals("ultimallamada")) {

                    //FileOutputStream os = new FileOutputStream("examen.txt", true);
                    ps = new PrintStream(out);
                } else if (preg.getAttributes().getNamedItem("tipo").getTextContent().equals("identity")) {
                    //FileOutputStream os = new FileOutputStream("examen.txt", true);
                    ps = new PrintStream(out);
                    ps.println("'TEMA: " + tema.getTextContent() + "'");
                } else if (preg.getAttributes().getNamedItem("tipo").getTextContent().equals("palabraImposible")) {
                    //FileOutputStream os = new FileOutputStream("examen.txt", true);
                    ps = new PrintStream(out);
                    ps.println("60");
                    ps.println("'Compatibilidad " + preg.getAttributes().getNamedItem("tipo").getTextContent() + "'");
                    ps.println("'AUTOR/A: " + nombre.getTextContent() + "'");
                } else {

                    //FileOutputStream os = new FileOutputStream("examen.txt", true);
                    ps = new PrintStream(out);
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

                            Type1 tipo1 = new Type1();
                            tipo1.imprimirTipo1(id, preguntaTipo, pregunta, ps);

                        } else if (preguntaTipo.getAttribute("tipo").equals("Tipo2")
                                || preguntaTipo.getAttribute("tipo").equals("Tipo18")
                                || preguntaTipo.getAttribute("tipo").equals("TipoLetritas")) {

                            Type2 tipo2 = new Type2();
                            tipo2.imprimirTipo2(id, preguntaTipo, pregunta, ps);

                        } else if (preguntaTipo.getAttribute("tipo").equals("Tipo3")
                                || preguntaTipo.getAttribute("tipo").equals("Tipo9")
                                || preguntaTipo.getAttribute("tipo").equals("TipoPatata")) {

                            Type3 tipo3 = new Type3();
                            tipo3.imprimirTipo3(id, preguntaTipo, pregunta, ps);

                        } else if (preguntaTipo.getAttribute("tipo").equals("Tipo4")
                                || preguntaTipo.getAttribute("tipo").equals("TipoSabio")) {

                            Type4 tipo4 = new Type4();
                            tipo4.imprimirTipo4(id, preguntaTipo, pregunta, ps);

                        } else if (preguntaTipo.getAttribute("tipo").equals("Tipo6")
                                || preguntaTipo.getAttribute("tipo").equals("Yipo2palabras")
                                || preguntaTipo.getAttribute("tipo").equals("TipoPistas")) {

                            Type6 tipo6 = new Type6();
                            tipo6.imprimirTipo6(id, preguntaTipo, pregunta, ps);

                        } else if (preguntaTipo.getAttribute("tipo").equals("Tipo7")) {

                            Type7 tipo7 = new Type7();
                            tipo7.imprimirTipo7(id, pregunta, ps);

                        } else if (preguntaTipo.getAttribute("tipo").equals("Tipo8")
                                || preguntaTipo.getAttribute("tipo").equals("Tipo10")) {

                            Type8 tipo8 = new Type8();
                            tipo8.imprimirTipo8(preguntaTipo, nombre.getTextContent(), tema.getTextContent(), id, pregunta, ps);

                        } else if (preguntaTipo.getAttribute("tipo").equals("Tipo11")
                                || preguntaTipo.getAttribute("tipo").equals("Tipo16")) {

                            Type11 tipo11 = new Type11();
                            tipo11.imprimirTipo11(id, pregunta, ps);

                        } else if (preguntaTipo.getAttribute("tipo").equals("Tipocoincidencias")
                                || preguntaTipo.getAttribute("tipo").equals("TipoDueloPalabras")
                                || preguntaTipo.getAttribute("tipo").equals("TipoPocasPalabras")
                                || preguntaTipo.getAttribute("tipo").equals("TipoLingo")
                                || preguntaTipo.getAttribute("tipo").equals("TipoCuantasLetras")) {

                            TypePalabras tipoPalabras = new TypePalabras();
                            tipoPalabras.imprimirTipoPalabras(id, palabras, preguntaTipo, pregunta, ps);

                        } else if (preguntaTipo.getAttribute("tipo").equals("TipoAntesyDespues")
                                || preguntaTipo.getAttribute("tipo").equals("TipoIdentity")
                                || preguntaTipo.getAttribute("tipo").equals("TipoPartextodo")
                                || preguntaTipo.getAttribute("tipo").equals("TipoLlamada")
                                || preguntaTipo.getAttribute("tipo").equals("TipoOrdenaFrase")
                                || preguntaTipo.getAttribute("tipo").equals("TipoImposible")) {

                            TypeRelacionar tipoRelacionar = new TypeRelacionar();
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
                ps = new PrintStream(out);
                ps.println("'Compatibilidad " + preg.getAttributes().getNamedItem("tipo").getTextContent() + "'");
                
                
                externalContext.responseFlushBuffer();
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
