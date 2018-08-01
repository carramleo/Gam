/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pruebaxml;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author rolmo
 */

public class Multitest extends Juego{
    
    private String tipo;
    private String id;

    
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
    
    public Multitest() {
        this.tipo = getClass().getSimpleName();
        int i = 1;
        String cadena = "hola";
        String nuevaCadena = cadena + i;
        
    }
    
}
