package conversorTXT;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Type4 {

	private static int NumeroLineasTipo4 = 3;
	private static int NumeroLineasTipoSabio = 5;
	private static int NumeroResp = 3;
	private int numLineasRestantes=0;
	private String comillas = "''";
	private Boolean tipo4=false;
	private Boolean tipoSabio=false;
	
	
	public Type4() {
	};

	public void imprimirTipo4(String id,Element preguntaTipo, Element pregunta, PrintStream ps) throws FileNotFoundException {

		NodeList soluciones = pregunta.getElementsByTagName("respuesta");
		NodeList enunciado = pregunta.getElementsByTagName("enunciado");
		NodeList pista = pregunta.getElementsByTagName("pista");

		int numRespRestantes = NumeroResp - soluciones.getLength();

		for (int l = 0; l < enunciado.getLength(); l++) {

			Node e = enunciado.item(l);

			if (e.getNodeType() == Node.ELEMENT_NODE) {

				String lineas = e.getAttributes().getNamedItem("numLineas").getTextContent();

				int numLineas = Integer.parseInt(lineas);
				
				if (preguntaTipo.getAttribute("tipo").equals("Tipo4")){
					
				tipo4=true;	
				 numLineasRestantes = NumeroLineasTipo4 - numLineas;

				

				ps.println("'DEFINICION " + id + "'" + "\n" + "'" + e.getTextContent() + "'" // imprimimos los
																								// enunciados de las
																								// preguntas
				);
				}else if (preguntaTipo.getAttribute("tipo").equals("TipoSabio")){
				
				tipoSabio=true;
				 numLineasRestantes = NumeroLineasTipoSabio - numLineas;

				

				ps.println("'PREGUNTA " + id + "'" + "\n" + "'" + e.getTextContent() + "'" // imprimimos los
																								// enunciados de las
																								// preguntas
				);
				}
				for (int h = 0; h < numLineasRestantes; h++) {
					ps.println(comillas); // imprimimos las comillas restantes de laslineas del enunciado sin ocupar

				}
				

				for (int j = 0; j < soluciones.getLength(); j++) {

					Node sol = soluciones.item(j);

					if (sol.getNodeType() == Node.ELEMENT_NODE) {

						Element solucion = (Element) sol; // Imprimimos las soluciones de la pregunta
						//FileOutputStream os1 = new FileOutputStream("examen.txt", true);
						//ps = new PrintStream(os1);
						ps.println("'" + solucion.getTextContent() + "'");
					}
				}
				for (int h = 0; h < numRespRestantes; h++) {
					ps.println(comillas); // imprimimos las comillas restantes de las soluciones posibles
				}

			}

			if (tipo4 ) {
			Node pis = pista.item(0);

			//FileOutputStream os1 = new FileOutputStream("examen.txt", true);
			if (pis.getNodeType() == Node.ELEMENT_NODE) {
				//ps = new PrintStream(os1);
				ps.println("'" + pis.getTextContent() + "'"); // imprimimos la pista
			}
		}
		}

	}

}
