package org.primefaces.Pregunta;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;




public class PreguntaOpciones implements Serializable {
	
	
        
	private String id;
	private String enunciado;
	//private String[] respuestas;
        private List<String> respuestas= new ArrayList<String>();
	private String[] pistas;
        int lineasEnunciado;
        private String solucion;
        private List<Integer> solucionList=new ArrayList<Integer>();
        char[] formatoOpciones= new char[] {'A','B','C','D','E','F'};
        
        
        
	
	public PreguntaOpciones(int numResp,int formatoOpciones, int lineasEnunciado) {
           //respuestas = new String[numResp];
            for (int i=0; i<numResp;i++){
                
                respuestas.add("");
            }
            
            
            for (int i=0; i<numResp;i++){
                
                solucionList.add(i+1);
            }
            

            
            this.lineasEnunciado=lineasEnunciado;
            //this.id=UUID.randomUUID().toString();
            
	}
	
	public String getEnunciado() {
		return enunciado;
	}
	public void setEnunciado(String enunciado) {
		this.enunciado = enunciado;
	}

    
	
	public String[] getPistas() {
		return pistas;
	}
	public void setPistas(String[] pistas) {
		this.pistas = pistas;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

    public int getLineasEnunciado() {
        return lineasEnunciado;
    }

    public void setLineasEnunciado(int lineasEnunciado) {
        this.lineasEnunciado = lineasEnunciado;
    }

    /*public String[] getRespuestas() {
        return respuestas;
    }

    public void setRespuestas(String[] respuestas) {
        this.respuestas = respuestas;
    }
*/

    public List<String> getRespuestas() {
        return respuestas;
    }

    public void setRespuestas(List<String> respuestas) {
        this.respuestas = respuestas;
    }
    
    
    public String getSolucion() {
        return solucion;
    }

    public void setSolucion(String solucion) {
        this.solucion = solucion;
    }

    public char[] getFormatoOpciones() {
        return formatoOpciones;
    }

    public void setFormatoOpciones(char[] formatoOpciones) {
        this.formatoOpciones = formatoOpciones;
    }

    public List<Integer> getSolucionList() {
        return solucionList;
    }

    public void setSolucionList(List<Integer> solucionList) {
        this.solucionList = solucionList;
    }





	
}
