/*
 * Clase que define el formato de pregunta del tipo campo de texto.
 */
package org.primefaces.Pregunta;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

public class PreguntaCampoTexto implements Serializable {

    private int numResp;
    private String id;
    private String enunciado;
    private String[] respuestas;
    private String[] pistas;
    private String[] solucion;
    private int numLineas;

    public PreguntaCampoTexto(int numLineas, int numSoluciones, int numPistas) {

        solucion = new String[numSoluciones];
        pistas = new String[numPistas];
        this.numLineas = numLineas;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
