package org.primefaces.Pregunta;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;




public class PreguntaSiNo implements Serializable {
	
	
        private int numResp=8;
	private int id=0;
	private String enunciado;
	private String[] respuestas;
	private String[] pistas;
        int lineasEnunciado;
        private String[] solucion;
        
        
	
	public PreguntaSiNo(int numResp, int lineasEnunciado) {
           
            
            respuestas = new String[numResp];
            solucion = new String[numResp];
            for(int j=0;j<numResp;j++){
                respuestas[j] = "Respuesta "+(j+1);
            }
            this.lineasEnunciado=lineasEnunciado;
	}
	
	public String getEnunciado() {
		return enunciado;
	}
	public void setEnunciado(String enunciado) {
		this.enunciado = enunciado;
	}

    public int getNumResp() {
        return this.numResp;
    }

    public void setNumResp(int numResp) {
        this.numResp = numResp;
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

    public String[] getSolucion() {
        return solucion;
    }

    public void setSolucion(String[] solucion) {
        this.solucion = solucion;
    }

   
    





	
}
