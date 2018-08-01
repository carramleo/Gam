/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pruebaxml;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author rolmo
 */
@XmlRootElement
public class Exportacion {

    
    private List<Juego> juegos;
    
    
    
    public List<Juego> getJuegos() {
        return juegos;
    }

    @XmlElements({
            @XmlElement(name="pregunta",type=Pregunta.class),
            @XmlElement(name="multitest",type=Multitest.class),
        })
    public void setJuegos(List<Juego> juegos) {
        this.juegos = juegos;
    }

}
