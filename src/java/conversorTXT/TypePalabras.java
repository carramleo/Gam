package conversorTXT;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class TypePalabras {

	public TypePalabras() {
	};
	
	private static int NumeroLineasTipoCoincidencias = 2;
	private static int NumeroLineasTipoDueloPalabras = 3;
	private boolean TipoCoincidencias=false;
	private boolean TipoDueloPalabras=false;
	private boolean TipoPocasPalabras=false;
	private boolean TipoLingo=false;
	private boolean TipoCuantasLetras=false;

	
	public void imprimirTipoPalabras( String id,NodeList palabras,Element preguntaTipo, Element pregunta, PrintStream ps) throws FileNotFoundException {

		NodeList soluciones = pregunta.getElementsByTagName("respuesta");
		NodeList enunciado = pregunta.getElementsByTagName("enunciado");
		
		
		if(preguntaTipo.getAttribute("tipo").equals("DueloPalabras") ){
			TipoDueloPalabras=true;
			
			if (id.equals("1")) {    //solo me interesa entrar aqui una vez
				
			ps.println("'PALABRAS'" );
		for (int i = 0; i < palabras.getLength(); i++) {
			
			Node palabra = palabras.item(i);
			
			if (palabra.getNodeType() == Node.ELEMENT_NODE) {
				ps.println("'"+palabra.getTextContent() +"'");
				
			}
			
			
		}
		ps.println("'PREGUNTAS'" );

		
		}
		
		
		
		}else if (preguntaTipo.getAttribute("tipo").equals("coincidencias")){
			TipoCoincidencias=true;
		}else if (preguntaTipo.getAttribute("tipo").equals("pocasPalabras")){
			TipoPocasPalabras=true;
			
			if (id.equals("1")) {    //solo me interesa entrar aqui una vez
				
			for (int i = 0; i < palabras.getLength(); i++) {
				
				Node palabra = palabras.item(i);
				
				if (palabra.getNodeType() == Node.ELEMENT_NODE) {
					ps.println("'"+palabra.getTextContent() +"'");
					
				}
				
				
			}
		
			}
		}else if (preguntaTipo.getAttribute("tipo").equals("lingo")){
			TipoLingo=true;
		}else if (preguntaTipo.getAttribute("tipo").equals("cuantasLetras")){
			TipoCuantasLetras=true;
			
			if (id.equals("1")) {    //solo me interesa entrar aqui una vez
				
				ps.println("'PALABRA'" );
			}
			
		}
		
		if (!TipoPocasPalabras) {
			
		for (int l = 0; l < enunciado.getLength(); l++) {

			Node e = enunciado.item(l);
			String lineas = e.getAttributes().getNamedItem("numLineas").getTextContent();
			int numLineas = Integer.parseInt(lineas);
			
			
			
			if (e.getNodeType() == Node.ELEMENT_NODE) {

				

				String comillas = "''";
				
				
				if(TipoCoincidencias) {
				ps.println("'"+e.getTextContent() +"'");
				
				
					
					for (int h = 0; h < NumeroLineasTipoCoincidencias - numLineas; h++) {
						
						ps.println(comillas); // imprimimos las comillas restantes de laslineas del enunciado sin ocupar
	
					}
				}else if(TipoDueloPalabras) {
					
					ps.println("'PREGUNTA " + id + "'" );
					ps.println("'"+e.getTextContent() +"'");
					for (int h = 0; h < NumeroLineasTipoDueloPalabras - numLineas; h++) {
						
						ps.println(comillas); // imprimimos las comillas restantes de laslineas del enunciado sin ocupar
	
					}
				}else if(TipoLingo) {
				ps.println("'"+e.getTextContent() +"'");
				
				}else if(TipoCuantasLetras) {
					ps.println("'"+e.getTextContent() +"'");
					
				
					}
		
				
				
				if(!TipoCuantasLetras) {
				for (int j = 0; j < soluciones.getLength(); j++) {

					Node sol = soluciones.item(j);

					if (sol.getNodeType() == Node.ELEMENT_NODE) {

						Element solucion = (Element) sol; // Imprimimos las soluciones de la pregunta
						FileOutputStream os1 = new FileOutputStream("examen.txt", true);
						ps = new PrintStream(os1);
						ps.println("'" + solucion.getTextContent() + "'");
					}
				}
			
			}else {
				
				
				String[] letrasPalabra = e.getTextContent().split("");
				
				for (int k = 0; k < letrasPalabra.length; k++) {
					Node sol = soluciones.item(k);
					ps.println("'"+letrasPalabra[k] +"'");
					ps.println(sol.getAttributes().getNamedItem("numLetras").getTextContent());

				}
			}

			}

		}
		
	}
		
		

	}
	

}
