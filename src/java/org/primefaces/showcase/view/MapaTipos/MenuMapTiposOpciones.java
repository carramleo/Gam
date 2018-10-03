/*
 * Clase que simula el contenido de base de datos donde se almacenan
 * todas las opciones de todos los tipos de juegos.
 */
package org.primefaces.showcase.view.MapaTipos;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author carlos
 */
@ManagedBean
@SessionScoped
public class MenuMapTiposOpciones implements Serializable {

    public static Map<String, Integer> numOpciones = new HashMap<String, Integer>();
    public static Map<String, Integer> formatoOp = new HashMap<String, Integer>();
    public static Map<String, Integer> lineasEnun = new HashMap<String, Integer>();
    public static Map<String, Integer> posiblesSol = new HashMap<String, Integer>();
    public static Map<String, Integer> elementosPanel = new HashMap<String, Integer>();
    public static Map<String, Integer> letrasPanel = new HashMap<String, Integer>();
    public static Map<String, Integer> filasColumna = new HashMap<String, Integer>();
    public static Map<String, Integer> Pistas = new HashMap<String, Integer>();
    public static Map<String, Integer> Temas = new HashMap<String, Integer>();

    static {
        numOpciones.put("Tipo1", 4);
        numOpciones.put("Tipo5", 3);
        numOpciones.put("Tipo7", 4);
        numOpciones.put("Tipo12", 4);
        numOpciones.put("Tipo13", 6);
        numOpciones.put("Tipo14", 2);
        numOpciones.put("Tipo15", 4);
        numOpciones.put("Tipo17", 6);
        numOpciones.put("Tipo19", 5);
        numOpciones.put("Tipo50x50", 2);
        numOpciones.put("TipoArcoiris", 4);
        numOpciones.put("TipoBombaDorada", 10);
        numOpciones.put("TipoBombaPlateada", 7);
        numOpciones.put("TipoDuelo", 3);
        numOpciones.put("TipoTrampa", 9);
        numOpciones.put("TipoSabios", 2);

        lineasEnun.put("Tipo1", 3);
        lineasEnun.put("Tipo5", 6);
        lineasEnun.put("Tipo7", 3);
        lineasEnun.put("Tipo12", 3);
        lineasEnun.put("Tipo13", 3);
        lineasEnun.put("Tipo14", 4);
        lineasEnun.put("Tipo15", 3);
        lineasEnun.put("Tipo17", 3);
        lineasEnun.put("Tipo19", 3);
        lineasEnun.put("Tipo50x50", 3);
        lineasEnun.put("TipoArcoiris", 3);
        lineasEnun.put("TipoBombaDorada", 3);
        lineasEnun.put("TipoBombaPlateada", 3);
        lineasEnun.put("TipoDuelo", 6);
        lineasEnun.put("TipoTrampa", 3);
        lineasEnun.put("TipoSabios", 4);

        lineasEnun.put("Tipo9", 3);
        lineasEnun.put("Tipo3", 5);
        lineasEnun.put("TipoPatataCaliente", 7);

        lineasEnun.put("Tipo4", 3);
        lineasEnun.put("TipoSabio", 5);
        lineasEnun.put("TipoPulsador", 6);
        lineasEnun.put("TipoEmpiezaPor", 3);
        lineasEnun.put("TipoEscalera", 6);
        lineasEnun.put("TipoLetra", 6);
        lineasEnun.put("TipoPasapalabra", 6);
        lineasEnun.put("TipoImposible", 5);
        lineasEnun.put("TipoLlamada", 4);
        lineasEnun.put("TipoIdentity", 6);

        lineasEnun.put("Tipo2", 3);
        lineasEnun.put("Tipo18", 3);

        lineasEnun.put("TipoJurado", 5);
        lineasEnun.put("Tipo123", 6);

        formatoOp.put("Tipo1", 1);
        formatoOp.put("Tipo5", 2);
        formatoOp.put("Tipo7", 1);
        formatoOp.put("Tipo12", 1);
        formatoOp.put("Tipo13", 1);
        formatoOp.put("Tipo14", 1);
        formatoOp.put("Tipo15", 1);
        formatoOp.put("Tipo17", 1);
        formatoOp.put("Tipo19", 2);
        formatoOp.put("Tipo50x50", 1);
        formatoOp.put("TipoArcoiris", 1);
        formatoOp.put("TipoBombaDorada", 2);
        formatoOp.put("TipoBombaPlateada", 2);
        formatoOp.put("TipoDuelo", 2);
        formatoOp.put("TipoTrampa", 2);
        formatoOp.put("TipoSabios", 1);

        posiblesSol.put("Tipo4", 3);
        posiblesSol.put("TipoSabio", 3);
        posiblesSol.put("TipoPulsador", 1);
        posiblesSol.put("TipoEmpiezaPor", 3);
        posiblesSol.put("TipoEscalera", 1);
        posiblesSol.put("TipoLetra", 1);
        posiblesSol.put("TipoPasapalabra", 3);
        posiblesSol.put("TipoImposible", 3);
        posiblesSol.put("TipoLlamada", 1);
        posiblesSol.put("TipoIdentity", 1);

        elementosPanel.put("Tipo2", 8);
        elementosPanel.put("Tipo18", 16);

        letrasPanel.put("Tipo6", 21);
        letrasPanel.put("Tipo8", 63);
        letrasPanel.put("Tipo10", 63);
        
        Pistas.put("Tipo6", 8);
        Pistas.put("Tipo8", 3);
        Pistas.put("Tipo10", 9);
        Pistas.put("Tipo4", 1);
        Pistas.put("TipoEmpiezaPor", 4);
        Pistas.put("TipoEscalera", 1);
        Pistas.put("TipoPasapalabra", 1);
        Pistas.put("TipoIdentity", 6);

        filasColumna.put("Tipo16", 8);
        filasColumna.put("TipoParejas", 10);
        filasColumna.put("TipoPar", 9);
        filasColumna.put("TipoDelTiron", 10);
        
        
        Temas.put("Tipo17", 1);
        Temas.put("TipoArcoIris", 1);
        Temas.put("TipoDuelo", 1);
        Temas.put("TipoSabios", 1);
        Temas.put("TipoSabio", 1);

    }

    public Map<String, Integer> getNumOpciones() {
        return numOpciones;
    }

    public void setNumOpciones(Map<String, Integer> numOpciones) {
        this.numOpciones = numOpciones;
    }

    public Map<String, Integer> getFormatoOp() {
        return formatoOp;
    }

    public void setFormatoOp(Map<String, Integer> formatoOp) {
        this.formatoOp = formatoOp;
    }

    public Map<String, Integer> getLineasEnun() {
        return lineasEnun;
    }

    public void setLineasEnun(Map<String, Integer> lineasEnun) {
        this.lineasEnun = lineasEnun;
    }

    public Map<String, Integer> getPosiblesSol() {
        return posiblesSol;
    }

    public void setPosiblesSol(Map<String, Integer> posiblesSol) {
        this.posiblesSol = posiblesSol;
    }

    public Map<String, Integer> getElementosPanel() {
        return elementosPanel;
    }

    public void setElementosPanel(Map<String, Integer> elementosPanel) {
        this.elementosPanel = elementosPanel;
    }

    public Map<String, Integer> getLetrasPanel() {
        return letrasPanel;
    }

    public void setLetrasPanel(Map<String, Integer> letrasPanel) {
        this.letrasPanel = letrasPanel;
    }

    public Map<String, Integer> getFilasColumna() {
        return filasColumna;
    }

    public void setFilasColumna(Map<String, Integer> filasColumna) {
        this.filasColumna = filasColumna;
    }

}
