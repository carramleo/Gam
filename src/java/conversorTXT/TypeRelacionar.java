package conversorTXT;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class TypeRelacionar {


	public TypeRelacionar() {
	};
	
	private boolean TipoIdentity=false;
	private boolean TipoPartextodo=false;
	private boolean TipoUltimallamada=false;
	private boolean TipoOrdenafrase=false;
	private boolean TipoPalabraImposible=false;
	private int numLineasTipoIdentity=6;
	private int numLineasTipoPalabraImposible=5;
	private int numLineasTipoOrdenaFrase=3;
	private int numLineasTipoPartextodo=3;
	private int numLineasTipoUltimallamada=4;
	private int numLineasPistasTipoIdentity=3;
	private int numLineasPistasTipoPartextodo=1;

	
	public void imprimirTipoRelacionar( String id,Element preguntaTipo, Element pregunta, PrintStream ps) throws FileNotFoundException {
		
		String correcta = pregunta.getAttribute("sol");
		NodeList soluciones = pregunta.getElementsByTagName("respuesta");
		NodeList enunciado = pregunta.getElementsByTagName("enunciado");
		NodeList pista = pregunta.getElementsByTagName("pista");


		
		if (preguntaTipo.getAttribute("tipo").equals("TipoIdentity")) {
			TipoIdentity=true;
			
		}
		else if (preguntaTipo.getAttribute("tipo").equals("TipoPartextodo")) {
			TipoPartextodo=true;
			
		}
		else if (preguntaTipo.getAttribute("tipo").equals("TipoLlamada")) {
			TipoUltimallamada=true;
			
		}
		else if (preguntaTipo.getAttribute("tipo").equals("TipoOrdenaFrase")) {
			TipoOrdenafrase=true;
			
		}
		else if (preguntaTipo.getAttribute("tipo").equals("TipoImposible")) {
			TipoPalabraImposible=true;
			
		}
		
		for (int l = 0; l < enunciado.getLength(); l++) {

			Node e = enunciado.item(l);
			String lineas = e.getAttributes().getNamedItem("numLineas").getTextContent();
			int numLineas = Integer.parseInt(lineas);
			
			
			
			if (e.getNodeType() == Node.ELEMENT_NODE) {

				

				String comillas = "''";
				
				
				if(TipoIdentity || TipoOrdenafrase) {
					
				if (TipoIdentity) {	
					ps.println("'PREGUNTA " + id + "'" );
						
					ps.println("'"+e.getTextContent() +"'");
					
					
						
						for (int h = 0; h < numLineasTipoIdentity - numLineas; h++) {
							
							ps.println(comillas); // imprimimos las comillas restantes de laslineas del enunciado sin ocupar
		
						}
					
				}else {
						ps.println("'FRASE " + id + "'" );
						ps.println("'"+e.getTextContent() +"'");
						
							for (int h = 0; h < numLineasTipoOrdenaFrase - numLineas; h++) {
							
							ps.println(comillas); // imprimimos las comillas restantes de laslineas del enunciado sin ocupar
		
						}
						}
				}else if(TipoPartextodo) {
					
						ps.println("'"+e.getTextContent() +"'");
				
				
					
					for (int h = 0; h < numLineasTipoPartextodo - numLineas; h++) {
						
						ps.println(comillas); // imprimimos las comillas restantes de laslineas del enunciado sin ocupar
	
					}
					
					
				}else if(TipoUltimallamada) {
						ps.println("'PREGUNTA " + id + "'" );
						ps.println("'"+e.getTextContent() +"'");
				
				
					
					for (int h = 0; h < numLineasTipoUltimallamada - numLineas; h++) {
						
						ps.println(comillas); // imprimimos las comillas restantes de laslineas del enunciado sin ocupar
	
					}
					
					
				}else if(TipoPalabraImposible) {
					
					
					if(id.equals("1")) {
						for (int h = 0; h < 7; h++) {
							
							ps.println(comillas); // imprimimos las comillas restantes de laslineas del enunciado sin ocupar

						}
					}
					ps.println("PALABRA " + id  );
					ps.println("'"+e.getTextContent() +"'");
			
			
				
				for (int h = 0; h < numLineasTipoPalabraImposible - numLineas; h++) {
					
					ps.println(comillas); // imprimimos las comillas restantes de laslineas del enunciado sin ocupar

				}
				
				
			}
		
				
				if (TipoIdentity || TipoPartextodo) {
				for (int g = 0; g < pista.getLength(); g++) {

					Node pis = pista.item(g);

					if (pis.getNodeType() == Node.ELEMENT_NODE) {
						
						ps.println("'" + pis.getTextContent() + "'"); // imprimimos la pista
					}
				if(TipoIdentity) {
				for (int h = 1; h < numLineasPistasTipoIdentity; h++) {
					ps.println(comillas); // imprimimos las comillas restantes de las soluciones posibles
				}
				}else if (TipoPartextodo) {
					for (int h = 1; h < numLineasPistasTipoPartextodo; h++) {
						ps.println(comillas); // imprimimos las comillas restantes de las soluciones posibles
					}
					
				}
				}
				
			}
				
				if (TipoIdentity || TipoUltimallamada || TipoPalabraImposible)
				ps.println("'" + correcta + "'");
			
				
				if(!TipoUltimallamada && !TipoOrdenafrase && !TipoPalabraImposible) {
				for (int j = 0; j < soluciones.getLength(); j++) {

					Node sol = soluciones.item(j);

					if (sol.getNodeType() == Node.ELEMENT_NODE) {

						Element solucion = (Element) sol; // Imprimimos las soluciones de la pregunta
						
						ps.println("'" + solucion.getTextContent() + "'");
					}
				}
			}else if(TipoPalabraImposible) {
				
				for (int h = 0; h < 3; h++) {
					
					ps.println(comillas); // imprimimos las comillas restantes de laslineas del enunciado sin ocupar

				}
			}

			}

		}
			
			
		
}
	
}
