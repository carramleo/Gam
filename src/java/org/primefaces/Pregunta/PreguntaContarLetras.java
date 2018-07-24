package org.primefaces.Pregunta;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;




public class PreguntaContarLetras implements Serializable {
	
	
	private int id=0;
	private String palabra;
	private String[] letras;
	
        private String[] numLetras;
        
        
	
	public PreguntaContarLetras(int numLetras) {
           
            
            letras= new String[numLetras];
            this.numLetras= new String[numLetras];
	}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPalabra() {
        return palabra;
    }

    public void setPalabra(String palabra) {
        this.palabra = palabra;
    }

    public String[] getLetras() {
        return letras;
    }

    public void setLetras(String[] letras) {
        this.letras = letras;
    }

    public String[] getNumLetras() {
        return numLetras;
    }

    public void setNumLetras(String[] numLetras) {
        this.numLetras = numLetras;
    }
	
	


	
}
