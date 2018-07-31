package org.primefaces.Pregunta;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;




public class PreguntaOpciones implements Serializable {
	
	
        
	private int id=0;
	private String enunciado;
	private String[] respuestas;
	private String[] pistas;
        int lineasEnunciado;
        private String solucion;
        char[] formatoOpciones= new char[] {'A','B','C','D','E','F'};
        
        
        
	
	public PreguntaOpciones(int numResp,int formatoOpciones, int lineasEnunciado) {
           respuestas = new String[numResp];
            
           if (formatoOpciones == 1){
               for(int j=0;j<numResp;j++){
                respuestas[j] = "Respuesta "+this.formatoOpciones[j];
            } 
            }
           else if (formatoOpciones == 2){
           for(int j=0;j<numResp;j++){
                respuestas[j] = "Respuesta "+(j+1);
            }
           }
            
            this.lineasEnunciado=lineasEnunciado;
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

    public int getLineasEnunciado() {
        return lineasEnunciado;
    }

    public void setLineasEnunciado(int lineasEnunciado) {
        this.lineasEnunciado = lineasEnunciado;
    }

    public String[] getRespuestas() {
        return respuestas;
    }

    public void setRespuestas(String[] respuestas) {
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





	
}
