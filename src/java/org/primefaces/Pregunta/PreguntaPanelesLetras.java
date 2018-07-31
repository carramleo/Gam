package org.primefaces.Pregunta;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;




public class PreguntaPanelesLetras implements Serializable {
	
	
        private int numResp=0;
	private int id=0;
	private String enunciado;
	private String[] respuestas;
	private String[] pistas;
        private String[] solucion;
        private int numLineas;
        
        
	
	public PreguntaPanelesLetras(int numLineas, int numPistas) {
           
            
            //solucion = new String[numSoluciones];
            pistas = new String[numPistas];
            this.numLineas=numLineas;
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

   

    public int getNumLineas() {
        return numLineas;
    }

    public void setNumLineas(int numLineas) {
        this.numLineas = numLineas;
    }





	
}
