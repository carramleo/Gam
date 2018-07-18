package org.primefaces.Pregunta;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;




public class Pregunta implements Serializable {
	
	
        private int numResp=4;
	private int id=0;
	private String enunciado;
	private String[] respuestasTipo1;
	private String[] pistas;
        
        
	
	public Pregunta(int n) {
           
            
            respuestasTipo1 = new String[numResp];
            for(int j=0;j<numResp;j++){
                respuestasTipo1[j] = "Respuesta "+(j+1);
            }
	}
	
	public String getEnunciado() {
		return enunciado;
	}
	public void setEnunciado(String enunciado) {
		this.enunciado = enunciado;
	}

    public int getNumResp() {
        return numResp;
    }

    public void setNumResp(int numResp) {
        this.numResp = numResp;
    }

    public String[] getRespuestasTipo1() {
        return respuestasTipo1;
    }

    public void setRespuestasTipo1(String[] respuestasTipo1) {
        this.respuestasTipo1 = respuestasTipo1;
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





	
}
