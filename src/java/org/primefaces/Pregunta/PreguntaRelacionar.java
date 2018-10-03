/*
 * Clase que define el formato de pregunta del tipo Relacionar columnas.
 */
package org.primefaces.Pregunta;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

public class PreguntaRelacionar implements Serializable {

    private int numCol;
    private String id;
    private String enunciado;
    private String[] columnaRespuesta;
    private String[] columnaSolucion;
    private int filasCol;

    public PreguntaRelacionar(int filasCol, int numFilaSoluciones) {

        columnaRespuesta = new String[numFilaSoluciones];
        columnaSolucion = new String[numFilaSoluciones];
        for (int j = 0; j < numFilaSoluciones; j++) {
            columnaRespuesta[j] = "Respuesta columna " + (j + 1);
            columnaSolucion[j] = "Solucion columna " + (j + 1);
        }
        this.filasCol = filasCol;
    }

    public int getNumCol() {
        return numCol;
    }

    public void setNumCol(int numCol) {
        this.numCol = numCol;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEnunciado() {
        return enunciado;
    }

    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
    }

    public String[] getColumnaRespuesta() {
        return columnaRespuesta;
    }

    public void setColumnaRespuesta(String[] columnaRespuesta) {
        this.columnaRespuesta = columnaRespuesta;
    }

    public String[] getColumnaSolucion() {
        return columnaSolucion;
    }

    public void setColumnaSolucion(String[] columnaSolucion) {
        this.columnaSolucion = columnaSolucion;
    }

    public int getFilasCol() {
        return filasCol;
    }

    public void setFilasCol(int filasCol) {
        this.filasCol = filasCol;
    }

}
