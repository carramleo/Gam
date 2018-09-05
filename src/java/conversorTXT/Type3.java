package conversorTXT;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.lang.Integer;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Type3 {

	private static int NumeroLineasTipo3 = 5;
	private static int NumeroLineasTipo9 = 3;
	private static int NumeroLineasTipoPatata = 7;

	public Type3() {
	};

	public void imprimirTipo3(String id, Element preguntaTipo, Element pregunta, PrintStream ps)
			throws FileNotFoundException {

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

				if (preguntaTipo.getAttribute("tipo").equals("3")) {

					for (int h = 0; h < NumeroLineasTipo3 - numLineas; h++) {
						ps.println(comillas); // imprimimos las comillas restantes de laslineas del enunciado sin ocupar

					}

				} else if (preguntaTipo.getAttribute("tipo").equals("9")) {
					for (int h = 0; h < NumeroLineasTipo9 - numLineas; h++) {
						ps.println(comillas); // imprimimos las comillas restantes de laslineas del enunciado sin ocupar

					}

				} else if (preguntaTipo.getAttribute("tipo").equals("patatacaliente")) {
					for (int h = 0; h < NumeroLineasTipoPatata - numLineas; h++) {
						ps.println(comillas); // imprimimos las comillas restantes de laslineas del enunciado sin ocupar

					}

				}

				for (int j = 0; j < soluciones.getLength(); j++) {

					Node sol = soluciones.item(j);

					if (sol.getNodeType() == Node.ELEMENT_NODE) {

						Element solucion = (Element) sol; // Imprimimos las soluciones de la pregunta
						FileOutputStream os1 = new FileOutputStream("examen.txt", true);
						ps = new PrintStream(os1);
						ps.println(solucion.getTextContent());
					}
				}
			}

		}

	}

}
