package org.primefaces.Pregunta;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;




public class PreguntaRespAbierta implements Serializable {
	
	
	private int id=0;
	private String enunciado;
	
        private int numLineas;
        
        
	
	public PreguntaRespAbierta(int numLineas) {
           
            
            
            this.numLineas=numLineas;
	}
	
	public String getEnunciado() {
		return enunciado;
	}
	public void setEnunciado(String enunciado) {
		this.enunciado = enunciado;
	}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumLineas() {
        return numLineas;
    }

    public void setNumLineas(int numLineas) {
        this.numLineas = numLineas;
    }

   



	
}
