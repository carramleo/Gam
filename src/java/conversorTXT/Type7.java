package conversorTXT;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Type7 {

	private static int NumeroLineasTipo3 = 3;
	private static int NumeroResp = 2;

	public Type7() {
	};

	public void imprimirTipo7(String id, Element pregunta, PrintStream ps) throws FileNotFoundException {

		String correcta = pregunta.getAttribute("sol");
		String comodin = pregunta.getAttribute("comodin50");
		String[] comodin50 = comodin.split(",");
		NodeList soluciones = pregunta.getElementsByTagName("respuesta");
		NodeList enunciado = pregunta.getElementsByTagName("enunciado");

		for (int l = 0; l < enunciado.getLength(); l++) {

			Node e = enunciado.item(l);

			if (e.getNodeType() == Node.ELEMENT_NODE) {

				String lineas = e.getAttributes().getNamedItem("numLineas").getTextContent();

				int numLineas = Integer.parseInt(lineas);

				String comillas = "''";

				ps.println("'PREGUNTA " + id + "'" + "\n" + "'" + e.getTextContent() + "'" // imprimimos los enunciados
																							// de las preguntas
				);

				for (int h = 0; h < NumeroLineasTipo3 - numLineas; h++) {
					ps.println(comillas); // imprimimos las comillas restantes de laslineas del enunciado sin ocupar

				}

				for (int j = 0; j < soluciones.getLength(); j++) {

					Node sol = soluciones.item(j);

					if (sol.getNodeType() == Node.ELEMENT_NODE) {

						Element solucion = (Element) sol; // Imprimimos las soluciones de la pregunta
						//FileOutputStream os1 = new FileOutputStream("examen.txt", true);
						//ps = new PrintStream(os1);
						ps.println("'" + solucion.getTextContent() + "'");

						String lineasSol = e.getAttributes().getNamedItem("numLineas").getTextContent();
						int numLineasSol = Integer.parseInt(lineasSol);
						int numRespRestantes = NumeroResp - numLineasSol;
						for (int h = 0; h < numRespRestantes; h++) {
							ps.println(comillas); // imprimimos las comillas restantes de las soluciones posibles
						}
					}
				}
			}
			//FileOutputStream os1 = new FileOutputStream("examen.txt", true);
			//ps = new PrintStream(os1);
			ps.println("'" + correcta + "'");
			for (int k = 0; k < comodin50.length; k++) {
				ps.println("'" + comodin50[k] + "'");
			}

		}

	}
}
