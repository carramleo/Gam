package conversorTXT;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class FichTipo11 {

	public FichTipo11() {
	};

	public void imprimirTipo11(String id, Element pregunta, PrintStream ps) throws FileNotFoundException {

		NodeList enunciado = pregunta.getElementsByTagName("enunciado");

		for (int l = 0; l < enunciado.getLength(); l++) {

			Node e = enunciado.item(l);

			if (e.getNodeType() == Node.ELEMENT_NODE) {

				for (int j = 0; j < enunciado.getLength(); j++) {

					Node enu = enunciado.item(j);

					if (enu.getNodeType() == Node.ELEMENT_NODE) {

						Element palabraEnunciado = (Element) enu; // Imprimimos las soluciones de la pregunta
						FileOutputStream os1 = new FileOutputStream("examen.txt", true);
						ps = new PrintStream(os1);
						ps.println("'" + palabraEnunciado.getTextContent() + "'");
					}
				}
			}

		}

	}
}
