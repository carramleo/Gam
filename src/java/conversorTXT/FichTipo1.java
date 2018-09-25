package conversorTXT;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class FichTipo1 {

    private static int NumeroLineasTipo1 = 3;      //Numero de lineas de enunciado de cada tipo
    private static int NumeroLineasTipo5 = 6;
    private static int NumeroLineasTipo14 = 4;
    private static int numOpcionesSolucion1 = 4;   //Numero de opciones de respuesta de cada tipo
    private static int numOpcionesSolucion5 = 3;
    private static int numOpcionesSolucion13 = 6;
    private static int numOpcionesSolucion14 = 2;
    private static int numOpcionesSolucion19 = 5;
    private static int numOpcionesSoluciontrampa = 9;
    private static int numLineasSol50x50 = 2;
    private static String comillas = "''";
    private static int numCaracteres = 54; //numero de caracteres alfanumericos que caben en una linea del generador de juegos tipo1
    private static int IdRonda = 1;
    private static int IdPanel = 0;
    private boolean Tipo1 = false;
    private boolean Tipo5 = false;
    private boolean Tipo14 = false;
    private boolean TipoJurado = false;
    private boolean Tipo123 = false;
    private boolean TipoTrampa = false;
    private boolean TipoletraAletra = false;

    public FichTipo1() {
    }

    ;

	public void imprimirTipo1(String id, Element preguntaTipo, Element pregunta, PrintStream ps)
            throws FileNotFoundException {

        String correcta = pregunta.getAttribute("sol");

        NodeList soluciones = pregunta.getElementsByTagName("respuesta");
        NodeList enunciado = pregunta.getElementsByTagName("enunciado");
        NodeList tema = pregunta.getElementsByTagName("tema");
        int idPreg=Integer.parseInt(id)+1;

        for (int l = 0; l < enunciado.getLength(); l++) {

            Node e = enunciado.item(l);
            //String lineas = e.getAttributes().getNamedItem("numLineas").getTextContent();
            String enunciadoStr = e.getTextContent();

            Node t = tema.item(l);
            //int numLineas = Integer.parseInt(lineas);

            
            

            List<String> stringsEnunciado = new ArrayList<String>();
            int index = 0;
            while (index < enunciadoStr.length()) {
                stringsEnunciado.add(enunciadoStr.substring(index, Math.min(index + 44, enunciadoStr.length())));
                index += 44;
            }
            
            int numLineas = stringsEnunciado.size();

            int temaArcoirirs = (idPreg - 1) % 4; //Variable para que en el tipo arcoiris imprima tema cada 4 preguntas
            int temaLosSabios = (idPreg - 1) % 3;
            int temaJurado = (idPreg - 1) % 1;
            int ronda123 = (idPreg - 1) % 6;
            int panelesLetraAletra = (idPreg - 1) % 5;

            if (e.getNodeType() == Node.ELEMENT_NODE) {

                if (preguntaTipo.getAttribute("tipo").equals("TipoDuelo")) {

                    ps.println("'" + t.getTextContent() + "'" + "\n" + "'" + enunciadoStr + "'");

                } else {
                    if (preguntaTipo.getAttribute("tipo").equals("TipoArcoiris") && (temaArcoirirs == 0 || idPreg == 0)) {  //imprimimos el tema de arcoiris cada 4 preguntas
                        ps.println("'" + t.getTextContent() + "'");
                    } else if (preguntaTipo.getAttribute("tipo").equals("TipoSabios") && (temaLosSabios == 0 || idPreg == 0)) {
                        ps.println("'" + t.getTextContent() + "'");

                    } else if (preguntaTipo.getAttribute("tipo").equals("Tipo123") && (ronda123 == 0 || idPreg == 0)) {
                        ps.println("'RONDA " + IdRonda + "'");
                        IdRonda++;

                    } else if (preguntaTipo.getAttribute("tipo").equals("numLineasLetra") && (panelesLetraAletra == 0 || idPreg == 0)) {

                        IdPanel++;  //para incrementar el numero del panel cada 5 preguntas

                    }

                    if (!preguntaTipo.getAttribute("tipo").equals("TipoLetra")) {
                        ps.println("'PREGUNTA " + (idPreg++) + "'"); // imprimimos los enunciados de las preguntas																							
                    } else {

                        ps.println("'PANEL " + IdPanel + ". PALABRA " + idPreg + "'");
                    }

                    if (preguntaTipo.getAttribute("tipo").equals("TipoJurado") && (temaJurado == 0 || idPreg == 0)) {
                        TipoJurado = true;
                        ps.println("'" + t.getTextContent() + "'");
                    }
                    

                }
                if (preguntaTipo.getAttribute("tipo").equals("Tipo1") || preguntaTipo.getAttribute("tipo").equals("Tipo12")
                        || preguntaTipo.getAttribute("tipo").equals("Tipo13")
                        || preguntaTipo.getAttribute("tipo").equals("Tipo15")
                        || preguntaTipo.getAttribute("tipo").equals("Tipo17")
                        || preguntaTipo.getAttribute("tipo").equals("Tipo19")
                        || preguntaTipo.getAttribute("tipo").equals("Tipo50x50")
                        || preguntaTipo.getAttribute("tipo").equals("TipoBombaDorada")
                        || preguntaTipo.getAttribute("tipo").equals("TipoBombaPlateada")
                        || preguntaTipo.getAttribute("tipo").equals("TipoArcoiris")
                        || preguntaTipo.getAttribute("tipo").equals("TipoVoF")) {

                    Tipo1 = true;
                    int restriccionLineas=0;
                    for (String textoEnunciado : stringsEnunciado) {
                        
                        if(restriccionLineas<=NumeroLineasTipo1){
                        ps.println("'" + textoEnunciado + "'");
                        restriccionLineas++;
                        }
                        else
                            break;
                    }
                    
                    for (int h = 0; h < NumeroLineasTipo1 - numLineas; h++) {

                        ps.println(comillas); // imprimimos las comillas restantes de laslineas del enunciado sin ocupar

                    }

                } else if (preguntaTipo.getAttribute("tipo").equals("Tipo5")
                        || preguntaTipo.getAttribute("tipo").equals("TipoDuelo")
                        || preguntaTipo.getAttribute("tipo").equals("Tipo123")) {
                    
                    int restriccionLineas=0;
                    for (String textoEnunciado : stringsEnunciado) {
                        
                        if(restriccionLineas<=NumeroLineasTipo5){
                        ps.println("'" + textoEnunciado + "'");
                        restriccionLineas++;
                        }
                        else
                            break;
                    }
                    for (int h = 0; h < NumeroLineasTipo5 - numLineas; h++) {
                        if (preguntaTipo.getAttribute("tipo").equals("Tipo123")) {
                            Tipo123 = true;
                        }
                        Tipo5 = true;
                        ps.println(comillas); // imprimimos las comillas restantes de laslineas del enunciado sin ocupar

                    }

                } else if (preguntaTipo.getAttribute("tipo").equals("Tipo14")
                        || preguntaTipo.getAttribute("tipo").equals("TipoSabios")
                        || preguntaTipo.getAttribute("tipo").equals("TipoJurado")) {
                    
                    int restriccionLineas=0;
                    for (String textoEnunciado : stringsEnunciado) {
                        
                        if(restriccionLineas<=NumeroLineasTipo14){
                        ps.println("'" + textoEnunciado + "'");
                        restriccionLineas++;
                        }
                        else
                            break;
                    }
                    for (int h = 0; h < NumeroLineasTipo14 - numLineas; h++) {
                        Tipo14 = true;
                        ps.println(comillas); // imprimimos las comillas restantes de laslineas del enunciado sin ocupar

                    }
                } else if (preguntaTipo.getAttribute("tipo").equals("TipoTrampa")) {
                    
                    int restriccionLineas=0;
                    for (String textoEnunciado : stringsEnunciado) {
                        
                        if(restriccionLineas<=NumeroLineasTipo1){
                        ps.println("'" + textoEnunciado + "'");
                        restriccionLineas++;
                        }
                        else
                            break;
                    }
                    for (int h = 0; h < NumeroLineasTipo1 - numLineas; h++) {
                        TipoTrampa = true;
                        ps.println(comillas); // imprimimos las comillas restantes de laslineas del enunciado sin ocupar

                    }
                } else if (preguntaTipo.getAttribute("tipo").equals("TipoLetra")) {
                    
                    int restriccionLineas=0;
                    for (String textoEnunciado : stringsEnunciado) {
                        
                        if(restriccionLineas<=NumeroLineasTipo5){
                        ps.println("'" + textoEnunciado + "'");
                        restriccionLineas++;
                        }
                        else
                            break;
                    }
                    for (int h = 0; h < NumeroLineasTipo5 - numLineas; h++) {
                        TipoletraAletra = true;
                        ps.println(comillas); // imprimimos las comillas restantes de laslineas del enunciado sin ocupar

                    }
                }
            }
            if (!preguntaTipo.getAttribute("tipo").equals("TipoJurado") && !preguntaTipo.getAttribute("tipo").equals("Tipo123")) {

                if (!preguntaTipo.getAttribute("tipo").equals("TipoLetra")) {

                    for (int j = 0; j < soluciones.getLength(); j++) {

                        Node sol = soluciones.item(j);

                        if (sol.getNodeType() == Node.ELEMENT_NODE) {
                            String lineasSol = e.getAttributes().getNamedItem("numLineas").getTextContent();

                            int numLineasSol = Integer.parseInt(lineasSol);

                            Element solucion = (Element) sol; // Imprimimos las soluciones de la pregunta

                            ps.println("'" + solucion.getTextContent() + "'");
                            if (preguntaTipo.getAttribute("tipo").equals("Tipo50x50")) {
                                for (int h = 0; h < numLineasSol50x50 - numLineasSol; h++) {
                                    ps.println(comillas);

                                }
                            }

                        }
                    }

                }

                if (preguntaTipo.getAttribute("tipo").equals("Tipo1") || preguntaTipo.getAttribute("tipo").equals("Tipo12")
                        || preguntaTipo.getAttribute("tipo").equals("Tipo15")) {
                    for (int h = 0; h < numOpcionesSolucion1 - soluciones.getLength(); h++) {
                        ps.println(comillas); // imprimimos las comillas restantes de las soluciones posibles
                    }
                } else if (preguntaTipo.getAttribute("tipo").equals("Tipo5")) {
                    for (int h = 0; h < numOpcionesSolucion5 - soluciones.getLength(); h++) {
                        ps.println(comillas); // imprimimos las comillas restantes de las soluciones posibles
                    }

                } else if (preguntaTipo.getAttribute("tipo").equals("Tipo13")
                        || preguntaTipo.getAttribute("tipo").equals("Tipo17")) {
                    for (int h = 0; h < numOpcionesSolucion13 - soluciones.getLength(); h++) {
                        ps.println(comillas); // imprimimos las comillas restantes de las soluciones posibles
                    }

                } else if (preguntaTipo.getAttribute("tipo").equals("Tipo14")) {
                    for (int h = 0; h < numOpcionesSolucion14 - soluciones.getLength(); h++) {
                        ps.println(comillas); // imprimimos las comillas restantes de las soluciones posibles
                    }

                } else if (preguntaTipo.getAttribute("tipo").equals("Tipo19")) {
                    Tipo1 = false;
                    Tipo5 = true;
                    for (int h = 0; h < numOpcionesSolucion19 - soluciones.getLength(); h++) {
                        ps.println(comillas); // imprimimos las comillas restantes de las soluciones posibles
                    }

                } else if (preguntaTipo.getAttribute("tipo").equals("TipoTrampa")) {

                    for (int h = 0; h < numOpcionesSoluciontrampa - soluciones.getLength(); h++) {
                        ps.println(comillas); // imprimimos las comillas restantes de las soluciones posibles
                    }

                }

                if (!TipoJurado && !Tipo123 && (Tipo1 || Tipo14 || TipoletraAletra)) {

                    if (preguntaTipo.getAttribute("tipo").equals("TipoBombaDorada")
                            || preguntaTipo.getAttribute("tipo").equals("TipoBombaPlateada")) {
                        ps.println(correcta);
                    } else {
                        ps.println("'" + correcta + "'");
                    }

                } else if (!Tipo123 && (Tipo5 || TipoTrampa)) {

                    ps.println(correcta);
                }
            }

        }

    }
}