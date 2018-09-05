package conversorTXT;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Type2 {

	private static String comillas = "''";
	private static int NumeroLineasTipo2 = 3;
	private static int NumeroLineasTipoLetritas = 3;
	private static int NumeroLetrasLetritas = 18;
	private static boolean Tipo2 = false;
	private static boolean Tipo18 = false;
	private static boolean TipoLetritas = false;


	public Type2() {
	};

	public void imprimirTipo2(String id, Element preguntaTipo, Element pregunta, PrintStream ps)
			throws FileNotFoundException {

		String correcta = pregunta.getAttribute("sol");
		NodeList soluciones = pregunta.getElementsByTagName("respuesta");
		NodeList enunciado = pregunta.getElementsByTagName("enunciado");
		NodeList pistas = pregunta.getElementsByTagName("pista");
		for (int l = 0; l < enunciado.getLength(); l++) {

			Node e = enunciado.item(l);
			String lineas = e.getAttributes().getNamedItem("numLineas").getTextContent();
			
			int numLineas = Integer.parseInt(lineas);

			if (preguntaTipo.getAttribute("tipo").equals("2")) {
				Tipo2 = true;

				if (e.getNodeType() == Node.ELEMENT_NODE) {
					ps.println("'PANEL " + id + "'" + "\n" + "'" + e.getTextContent() + "'" // imprimimos los enunciados
																							// de las preguntas
					);
					for (int h = 0; h < NumeroLineasTipo2 - numLineas; h++) {
						ps.println(comillas); // imprimimos las comillas restantes de laslineas del enunciado sin ocupar

					}

				}
			} else if (preguntaTipo.getAttribute("tipo").equals("18")) {
				Tipo18 = true;
				if (e.getNodeType() == Node.ELEMENT_NODE) {
					ps.println("'ENUNCIADO DE LA PREGUNTA'" + "\n" + "'" + e.getTextContent() + "'" // imprimimos los
																									// enunciados de las
																									// preguntas
					);
					for (int h = 0; h < NumeroLineasTipo2 - numLineas; h++) {
						ps.println(comillas); // imprimimos las comillas restantes de laslineas del enunciado sin ocupar

					}

				}
			} else if (preguntaTipo.getAttribute("tipo").equals("letritas")) {
				TipoLetritas=true;
				
				if (e.getNodeType() == Node.ELEMENT_NODE) {
					ps.println("'PISTAS'");
					
					for (int i = 0; i < pistas.getLength(); i++) {
						Node p=pistas.item(i);
						
						
						ps.println("'" + p.getTextContent() + "'");
						
					}
					for (int h = 0; h < NumeroLineasTipoLetritas - pistas.getLength(); h++) {
						ps.println(comillas); // imprimimos las comillas restantes de laslineas del enunciado sin ocupar

					}

				}
			}
			

			for (int j = 0; j < soluciones.getLength(); j++) {

				Node sol = soluciones.item(j);

				if (sol.getNodeType() == Node.ELEMENT_NODE && Tipo2 == true) {

					String[] respOk = correcta.split(",");
					Element solucion = (Element) sol; // Imprimimos las soluciones de la pregunta
					Boolean si = false;
					FileOutputStream os1 = new FileOutputStream("examen.txt", true);
					ps = new PrintStream(os1);
					ps.println("'" + solucion.getTextContent() + "'");
					for (int k = 0; k < respOk.length; k++) {
						if (respOk[k].toString().equals(solucion.getAttribute("tipo"))) {
							ps.println("'SI'");
							si = true;
							k = respOk.length;
						}

					}

					if (si == false) {
						ps.println("'NO'");
					}
				} else if (sol.getNodeType() == Node.ELEMENT_NODE && Tipo18 == true) {

					String[] respOk = correcta.split(",");
					Element solucion = (Element) sol; // Imprimimos las soluciones de la pregunta
					Boolean si = false;
					FileOutputStream os1 = new FileOutputStream("examen.txt", true);
					ps = new PrintStream(os1);
					ps.println("'OPCION " + solucion.getAttribute("num") + "'" + "\n" + "'" + solucion.getTextContent()
							+ "'");
					for (int k = 0; k < respOk.length; k++) {
						if (respOk[k].toString().equals(solucion.getAttribute("tipo"))) {
							ps.println("'SI'");
							si = true;
							k = respOk.length;
						}

					}

					if (si == false) {
						ps.println("'NO'");
					}

				}else if (sol.getNodeType() == Node.ELEMENT_NODE && TipoLetritas == true) {

					
					Element solucion = (Element) sol; // Imprimimos las soluciones de la pregunta
					String letrita = sol.getAttributes().getNamedItem("letritas").getTextContent();
					FileOutputStream os1 = new FileOutputStream("examen.txt", true);
					ps = new PrintStream(os1);
					ps.println("'" + solucion.getTextContent() + "'");
					ps.println(letrita);
										
				}
			}
			
			if (TipoLetritas) {
				
				for (int h = 0; h < NumeroLetrasLetritas - soluciones.getLength();h++ ) {
					ps.println(comillas); // imprimimos las comillas restantes de laslineas del enunciado sin ocupar
					ps.println("0");

				}
			}

		}
	}
}
