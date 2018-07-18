/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.primefaces.Juego;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author carlos
 */
public class Juego implements Serializable {
    
    private int numOpciones;
    private int lineasEnunciado;
    private String tema;
    private List<String> pistas = new ArrayList<String>();
    private String formatoOpciones;
    private String tipoJuego;
    private String comodin;
    private List<String> lineasExtra= new ArrayList<String>();
    private int posiblesSoluciones;
    
    
}
