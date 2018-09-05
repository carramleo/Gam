package conversorTXT;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Type8 {

	private static int NumeroResp = 63;
	private static int NumeroLineasPista8 = 3;
	private static int NumeroLineasPista10 = 9;
	private static String comillas = "''";

	public Type8() {
	};

	public void imprimirTipo8(Element preguntaTipo, String nombre, String tema, String id, Element pregunta,
			PrintStream ps) throws FileNotFoundException {

		NodeList soluciones = pregunta.getElementsByTagName("respuesta");
		NodeList enunciado = pregunta.getElementsByTagName("enunciado");
		NodeList pista = pregunta.getElementsByTagName("pista");

		int numRespRestantes = NumeroResp - soluciones.getLength();
		int numPistasRestantes8 = NumeroLineasPista8 - pista.getLength();
		int numPistasRestantes10 = NumeroLineasPista10 - pista.getLength();

		for (int l = 0; l < enunciado.getLength(); l++) {

			Node e = enunciado.item(l);

			if (e.getNodeType() == Node.ELEMENT_NODE) {

				FileOutputStream os1 = new FileOutputStream("examen.txt");
				ps = new PrintStream(os1);
				ps.println("'FRASE'");

				for (int j = 0; j < soluciones.getLength(); j++) {

					Node sol = soluciones.item(j);

					if (sol.getNodeType() == Node.ELEMENT_NODE) {

						Element solucion = (Element) sol; // Imprimimos las soluciones de la pregunta
						FileOutputStream os2 = new FileOutputStream("examen.txt", true);
						ps = new PrintStream(os2);
						ps.println("'" + solucion.getTextContent() + "'");
					}
				}
				for (int h = 0; h < numRespRestantes; h++) {
					ps.println(comillas); // imprimimos las comillas restantes de las soluciones posibles
				}

			}

			FileOutputStream os2 = new FileOutputStream("examen.txt", true);
			ps = new PrintStream(os2);
			ps.println("'AUTOR/A: " + nombre + "'");
			ps.println("'TEMA: " + tema + "'");
			ps.println("'PISTA'");
			for (int g = 0; g < pista.getLength(); g++) {

				Node pis = pista.item(g);

				if (pis.getNodeType() == Node.ELEMENT_NODE) {
					ps = new PrintStream(os2);
					ps.println("'" + pis.getTextContent() + "'"); // imprimimos la pista
				}

			}
		}
		if (preguntaTipo.getAttribute("tipo").equals("8")) {
			for (int h = 0; h < numPistasRestantes8; h++) {
				ps.println(comillas); // imprimimos las comillas restantes de las pistas posibles
			}
		} else if (preguntaTipo.getAttribute("tipo").equals("10")) {

			for (int h = 0; h < numPistasRestantes10; h++) {
				ps.println(comillas); // imprimimos las comillas restantes de las pistas posibles
			}
		}

	}
}
