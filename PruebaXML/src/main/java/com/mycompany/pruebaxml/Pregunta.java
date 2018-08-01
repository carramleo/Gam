/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pruebaxml;

import java.util.Arrays;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author rolmo
 */

public class Pregunta extends Juego{
    
    private String tipo;
    private String id;
    
    private String enunciado;
    private List<String> respuestas = Arrays.asList(new String[4]);
    
    public String getEnunciado() {
        return enunciado;
    }
    
    
    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
    }
    
    
    public String getTipo() {
        return tipo;
    }
    
    
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    
    public String getId() {
        return id;
    }

    
    public void setId(String id) {
        this.id = id;
    }

    

    public Pregunta() {
        this.tipo = getClass().getSimpleName();
        respuestas.set(0, "Respuesta 1");
        respuestas.set(1, "Respuesta 2");
        respuestas.set(2, "Respuesta 3");
        respuestas.set(3, "Respuesta 4");
    }
    
    
    
    public List<String> getRespuestas() {
        return respuestas;
    }

    public void setRespuestas(List<String> respuestas) {
        this.respuestas = respuestas;
    }

    
    
    
    
    
    
}
