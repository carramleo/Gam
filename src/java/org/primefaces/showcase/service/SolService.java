package org.primefaces.showcase.service;
 
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import org.primefaces.Pregunta.Pregunta;

 
@ManagedBean(name = "solService")
@ApplicationScoped
public class SolService implements Serializable {
 
    private final static String[] soluciones;
 

 
    static {
        soluciones = new String[4];
        soluciones[0] = "A";
        soluciones[1] = "B";
        soluciones[2] = "C";
        soluciones[3] = "D";

    }

    public List<Pregunta> createPreguntas(int size) {
        List<Pregunta> list = new ArrayList<Pregunta>();
        for(int i = 0 ; i < size ; i++) {
            list.add(new Pregunta(1));
        }
 
        return list;
    }
   
    public List<String> getSoluciones() {
        return Arrays.asList(soluciones);
    }

}