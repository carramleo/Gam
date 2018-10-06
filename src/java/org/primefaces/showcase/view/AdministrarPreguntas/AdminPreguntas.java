/*
 * Clase que Contiene todas las listas de preguntas que se generan en el juego,
 *  las funciones de borrado y añadido de preguntas y respuestas.
 */

package org.primefaces.showcase.view.AdministrarPreguntas;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.Pregunta.PreguntaCampoTexto;
import org.primefaces.Pregunta.PreguntaCifras;
import org.primefaces.Pregunta.PreguntaContarLetras;
import org.primefaces.Pregunta.PreguntaOpciones;
import org.primefaces.Pregunta.PreguntaPanelesLetras;
import org.primefaces.Pregunta.PreguntaRelacionar;
import org.primefaces.Pregunta.PreguntaRespAbierta;
import org.primefaces.Pregunta.PreguntaSiNo;

import org.primefaces.showcase.view.MapaTipos.MenuMapTiposOpciones;

@ManagedBean
@SessionScoped
public class AdminPreguntas implements Serializable {
    //Creamos todas las listas de preguntas de todos los tipos de juegos.
    private List<PreguntaOpciones> Preguntas = new ArrayList<PreguntaOpciones>();
    private List<PreguntaCifras> PreguntasCifras = new ArrayList<PreguntaCifras>();
    private List<PreguntaCampoTexto> PreguntasCampoTexto = new ArrayList<PreguntaCampoTexto>();
    private List<PreguntaSiNo> PreguntasSiNo = new ArrayList<PreguntaSiNo>();
    private List<PreguntaPanelesLetras> PreguntasPanelesLetras = new ArrayList<PreguntaPanelesLetras>();
    private List<PreguntaRelacionar> PreguntasRelacionar = new ArrayList<PreguntaRelacionar>();
    private List<PreguntaRespAbierta> PreguntasRespAbierta = new ArrayList<PreguntaRespAbierta>();
    private List<PreguntaContarLetras> PreguntasContarLetras = new ArrayList<PreguntaContarLetras>();
    private String autor;
    private String titulo;
    private String Tipo;
    private String TemaJuego;

    int number = 0;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
    //Función para resetear todas lass listas, utilizado cuando se pulsa el botón de "borrar preguntas" o cuando se cambia
    //de tipo de juego de tipo opciones.
    public void deleteAllLists() {
        Preguntas.removeAll(Preguntas);
        PreguntasCampoTexto.removeAll(PreguntasCampoTexto);
        PreguntasCifras.removeAll(PreguntasCifras);
        PreguntasContarLetras.removeAll(PreguntasContarLetras);
        PreguntasPanelesLetras.removeAll(PreguntasPanelesLetras);
        PreguntasRelacionar.removeAll(PreguntasRelacionar);
        PreguntasRespAbierta.removeAll(PreguntasRespAbierta);
        PreguntasSiNo.removeAll(PreguntasSiNo);
    }
    //Funcion para añadir pregunta a la lista  de tipo test
    public void addPregunta() {

        PreguntaOpciones pregAdd = new PreguntaOpciones(MenuMapTiposOpciones.numOpciones.get(Tipo), MenuMapTiposOpciones.formatoOp.get(Tipo), MenuMapTiposOpciones.lineasEnun.get(Tipo));
        //this.number = Preguntas.size();
        pregAdd.setId("preg_" + number++);
        if(Tipo.equals("Tipo12")){
            pregAdd.setTema("");
        }
        Preguntas.add(pregAdd);
    }
    //Funcion para añadir respuesta a la lista de respuestas  de tipo test
    public void addRespPregunta(PreguntaOpciones pregActual) {

        pregActual.getRespuestas().add("");
        pregActual.getSolucionList().add(pregActual.getSolucionList().get(pregActual.getSolucionList().size()-1) +1);
    }
    //Funcion para borrar respuesta de la lista de respuestas  de tipo test
    public void deleteRespPregunta(PreguntaOpciones pregActual, int resp) {

        //pregActual.getRespuestas().remove(resp);

        
            pregActual.getRespuestas().remove(resp);
            pregActual.getSolucionList().remove(pregActual.getSolucionList().size()-1) ;
 
    }
    //Funcion para borrar pregunta de la lista  de tipo test
    public void deletePregunta(String id) {

        for (int i = 0; i < Preguntas.size(); i++) {

            if (Preguntas.get(i).getId().equals(id)) {

                Preguntas.remove(i);

            }

        }

    }
    //Funcion para añadir pregunta a la lista  de tipo cifras
    public void addPreguntaCifras() {

        PreguntaCifras pregAdd = new PreguntaCifras(MenuMapTiposOpciones.lineasEnun.get(Tipo));
        //this.number = PreguntasCifras.size();
        pregAdd.setId("preg_" + number++);
        PreguntasCifras.add(pregAdd);
    }
    //Funcion para borrar pregunta de la lista  de tipo cifras
    public void deletePreguntaCifras(String id) {
        for (int i = 0; i < PreguntasCifras.size(); i++) {

            if (PreguntasCifras.get(i).getId().equals(id)) {

                PreguntasCifras.remove(i);

            }

        }

    }
    //Funcion para añadir pregunta a la lista  de tipo campo texto
    public void addPreguntaCampoTexto() {
        PreguntaCampoTexto pregAdd3 = new PreguntaCampoTexto(MenuMapTiposOpciones.lineasEnun.get(Tipo), MenuMapTiposOpciones.posiblesSol.get(Tipo), 1);
        // this.number = PreguntasCampoTexto.size();
        pregAdd3.setId("preg_" + number++);
        PreguntasCampoTexto.add(pregAdd3);

    }
    //Funcion para borrar pregunta de la lista  de tipo campo texto
    public void deletePreguntaCampoTexto(String id) {
        for (int i = 0; i < PreguntasCampoTexto.size(); i++) {

            if (PreguntasCampoTexto.get(i).getId().equals(id)) {

                PreguntasCampoTexto.remove(i);

            }

        }

    }
    //Funcion para añadir pregunta a la lista  de tipo respuesta si o no
    public void addPreguntaSiNo() {

        PreguntaSiNo pregAdd = new PreguntaSiNo(MenuMapTiposOpciones.elementosPanel.get(Tipo), MenuMapTiposOpciones.lineasEnun.get(Tipo));
        //this.number = PreguntasSiNo.size();
        pregAdd.setId("preg_" + number++);
        PreguntasSiNo.add(pregAdd);
    }
    //Funcion para borrar pregunta de la lista  de tipo respuesta si o no
    public void deletePreguntaSiNo(String id) {
        for (int i = 0; i < PreguntasSiNo.size(); i++) {

            if (PreguntasSiNo.get(i).getId().equals(id)) {

                PreguntasSiNo.remove(i);

            }

        }

    }
    //Funcion para añadir pregunta a la lista  de tipo paneles con letras
    public void addPreguntaPanelesLetras() {

        PreguntaPanelesLetras pregAdd = new PreguntaPanelesLetras(MenuMapTiposOpciones.letrasPanel.get(Tipo), MenuMapTiposOpciones.Pistas.get(Tipo));
        //this.number = PreguntasPanelesLetras.size();
        pregAdd.setId("preg_" + number++);
        PreguntasPanelesLetras.add(pregAdd);
    }
    //Funcion para borrar pregunta de la lista  de tipo paneles con letras
    public void deletePreguntaPanelesLetras(String id) {
        for (int i = 0; i < PreguntasPanelesLetras.size(); i++) {

            if (PreguntasPanelesLetras.get(i).getId().equals(id)) {

                PreguntasPanelesLetras.remove(i);

            }

        }

    }
    //Funcion para añadir pregunta a la lista  de tipo relacionar columnas
    public void addPreguntaRelacionar() {

        PreguntaRelacionar pregAdd = new PreguntaRelacionar(MenuMapTiposOpciones.filasColumna.get(Tipo), MenuMapTiposOpciones.filasColumna.get(Tipo));
        //this.number = PreguntasRelacionar.size();
        pregAdd.setId("preg_" + number++);
        PreguntasRelacionar.add(pregAdd);
    }
    //Funcion para borrar pregunta de la lista  de tipo relacionar columnas
    public void deletePreguntaRelacionar(String id) {
        for (int i = 0; i < PreguntasRelacionar.size(); i++) {

            if (PreguntasRelacionar.get(i).getId().equals(id)) {

                PreguntasRelacionar.remove(i);

            }

        }

    }
    //Funcion para añadir pregunta a la lista  de tipo respuesta abierta
    public void addPreguntaRespAbierta() {

        PreguntaRespAbierta pregAdd = new PreguntaRespAbierta(MenuMapTiposOpciones.lineasEnun.get(Tipo));
        //this.number = PreguntasRespAbierta.size();
        pregAdd.setId("preg_" + number++);
        PreguntasRespAbierta.add(pregAdd);
    }
    //Funcion para borrar pregunta de la lista  de tipo respuesta abierta
    public void deletePreguntaRespAbierta(String id) {
        for (int i = 0; i < PreguntasRespAbierta.size(); i++) {

            if (PreguntasRespAbierta.get(i).getId().equals(id)) {

                PreguntasRespAbierta.remove(i);

            }

        }

    }
    //Funcion para añadir pregunta a la lista  de tipo contar letras
    public void addPreguntaContarLetras() {

        PreguntaContarLetras pregAdd = new PreguntaContarLetras(18);

        pregAdd.setId("preg_" + number++);
        PreguntasContarLetras.add(pregAdd);
    }
    //Funcion para borrar pregunta de la lista  de tipo contar letras
    public void deletePreguntaContarLetras(String id) {
        for (int i = 0; i < PreguntasContarLetras.size(); i++) {

            if (PreguntasContarLetras.get(i).getId().equals(id)) {

                PreguntasContarLetras.remove(i);

            }

        }

    }

    public List<PreguntaOpciones> getPreguntas() {
        return Preguntas;
    }

    public List<PreguntaCifras> getPreguntasCifras() {
        return PreguntasCifras;
    }

    public List<PreguntaCampoTexto> getPreguntasCampoTexto() {
        return PreguntasCampoTexto;
    }

    public void setPreguntasCampoTexto(List<PreguntaCampoTexto> PreguntasCampoTexto) {
        this.PreguntasCampoTexto = PreguntasCampoTexto;
    }

    public List<PreguntaSiNo> getPreguntasSiNo() {
        return PreguntasSiNo;
    }

    public void setPreguntasSiNo(List<PreguntaSiNo> PreguntasSiNo) {
        this.PreguntasSiNo = PreguntasSiNo;
    }

    public List<PreguntaPanelesLetras> getPreguntasPanelesLetras() {
        return PreguntasPanelesLetras;
    }

    public void setPreguntasPanelesLetras(List<PreguntaPanelesLetras> PreguntasPanelesLetras) {
        this.PreguntasPanelesLetras = PreguntasPanelesLetras;
    }

    public List<PreguntaRelacionar> getPreguntasRelacionar() {
        return PreguntasRelacionar;
    }

    public void setPreguntasRelacionar(List<PreguntaRelacionar> PreguntasRelacionar) {
        this.PreguntasRelacionar = PreguntasRelacionar;
    }

    public List<PreguntaRespAbierta> getPreguntasRespAbierta() {
        return PreguntasRespAbierta;
    }

    public void setPreguntasRespAbierta(List<PreguntaRespAbierta> PreguntasRespAbierta) {
        this.PreguntasRespAbierta = PreguntasRespAbierta;
    }

    public List<PreguntaContarLetras> getPreguntasContarLetras() {
        return PreguntasContarLetras;
    }

    public void setPreguntasContarLetras(List<PreguntaContarLetras> PreguntasContarLetras) {
        this.PreguntasContarLetras = PreguntasContarLetras;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTipo() {
        return Tipo;
    }

    public void setTipo(String Tipo) {
        this.Tipo = Tipo;
    }

    public String getTemaJuego() {
        return TemaJuego;
    }

    public void setTemaJuego(String TemaJuego) {
        this.TemaJuego = TemaJuego;
    }
    
    

}
