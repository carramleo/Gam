/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pruebaxml;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author rolmo
 */

@XmlSeeAlso({Pregunta.class,Multitest.class})
@XmlType
public abstract class Juego implements Serializable{
    public abstract String getId();
    public abstract void setId(String id);
    public abstract String getTipo();
    public abstract void setTipo(String tipo);
    
}
