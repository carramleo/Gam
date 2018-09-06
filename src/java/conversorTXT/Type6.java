package conversorTXT;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Type6 {

	private static int NumeroResp = 21;
	private static int NumLineasPistas=9;
	private boolean TipoPistas=false;
	private boolean Tipo2palabras=false;
	private boolean Tipo6=false;
	private int numRespRestantes=0;
	private String comillas="''";

	public Type6() {
	};

	public void imprimirTipo6(String id,Element preguntaTipo, Element pregunta, PrintStream ps) throws FileNotFoundException {

		NodeList soluciones = pregunta.getElementsByTagName("respuesta");
		NodeList enunciado = pregunta.getElementsByTagName("enunciado");
		NodeList pista = pregunta.getElementsByTagName("pista");
		String correcta = pregunta.getAttribute("sol");

		
		
		
		
		if (preguntaTipo.getAttribute("tipo").equals("TipoPistas")) {
			TipoPistas=true;
			numRespRestantes = NumLineasPistas - pista.getLength();
		}else if (preguntaTipo.getAttribute("tipo").equals("Tipo2palabras")) {
			Tipo2palabras=true;
			numRespRestantes = NumeroResp - soluciones.getLength();
		}else if (preguntaTipo.getAttribute("tipo").equals("Tipo6")) {
			Tipo6=true;
			numRespRestantes = NumeroResp - soluciones.getLength();
		}

		for (int l = 0; l < enunciado.getLength(); l++) {

			Node e = enunciado.item(l);

			if (e.getNodeType() == Node.ELEMENT_NODE ) {

				String PalabraEnunciado = e.getTextContent();
				int numeroLetras = PalabraEnunciado.length();

				String comillas = "''";
				
				if (Tipo2palabras || Tipo6) {
					ps.println("'" + PalabraEnunciado + "'" + "\n" + numeroLetras + "\n" + "'LETRAS DE LA PALABRA '" + id +"'");

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
			}else if (TipoPistas) {
				ps.println("'PALABRA " + id +"'");
				ps.println("'" + correcta + "'");
			}

			}
			
			if(!Tipo2palabras && (TipoPistas || Tipo6)) {
				
			//FileOutputStream os1 = new FileOutputStream("examen.txt", true);
			//ps = new PrintStream(os1);
			
			if (Tipo6)
				ps.println("'PISTAS DE LA PALABRA'");
			
				

				
			for (int g = 0; g < pista.getLength(); g++) {

				Node pis = pista.item(g);

				if (pis.getNodeType() == Node.ELEMENT_NODE) {
					//ps = new PrintStream(os1);
					ps.println("'" + pis.getTextContent() + "'"); // imprimimos la pista
				}
			}
			
			if (TipoPistas) {
				for (int h = 0; h < numRespRestantes; h++) {
					ps.println(comillas); // imprimimos las comillas restantes de las soluciones posibles
				}
			}
		}
		}

	}
}
