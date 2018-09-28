package org.primefaces.showcase.view.button;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.component.UIOutput;
import javax.faces.component.html.HtmlInputText;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import org.primefaces.Pregunta.PreguntaCampoTexto;
import org.primefaces.Pregunta.PreguntaCifras;
import org.primefaces.Pregunta.PreguntaContarLetras;
import org.primefaces.Pregunta.PreguntaOpciones;
import org.primefaces.Pregunta.PreguntaPanelesLetras;
import org.primefaces.Pregunta.PreguntaRelacionar;
import org.primefaces.Pregunta.PreguntaRespAbierta;
import org.primefaces.Pregunta.PreguntaSiNo;

import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.inputtextarea.InputTextarea;
import org.primefaces.component.panel.Panel;
import org.primefaces.component.selectoneradio.SelectOneRadio;
import org.primefaces.event.CloseEvent;
import org.primefaces.showcase.view.input.MenuMapTiposOpciones;
import org.primefaces.showcase.view.input.MenuMapTipos;

@ManagedBean
@SessionScoped
public class AdminPreguntas implements Serializable {

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

    int number = 0;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

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

    public void addPregunta() {

        PreguntaOpciones pregAdd = new PreguntaOpciones(MenuMapTiposOpciones.numOpciones.get(Tipo), MenuMapTiposOpciones.formatoOp.get(Tipo), MenuMapTiposOpciones.lineasEnun.get(Tipo));
        //this.number = Preguntas.size();
        pregAdd.setId("preg_" + number++);
        Preguntas.add(pregAdd);
    }

    public void addRespPregunta(PreguntaOpciones pregActual) {

        pregActual.getRespuestas().add("");
        pregActual.getSolucionList().add(pregActual.getSolucionList().get(pregActual.getSolucionList().size()-1) +1);
    }

    public void deleteRespPregunta(PreguntaOpciones pregActual, String resp) {

        //pregActual.getRespuestas().remove(resp);

        Iterator<String> i = pregActual.getRespuestas().iterator();
        while (i.hasNext()) {
            String s = i.next(); // must be called before you can call i.remove()
            
            if(s.equals(resp)){
                i.remove();
                pregActual.getSolucionList().remove(pregActual.getSolucionList().size()-1) ;
            }
            
        }

    }

    public void deletePregunta(String id) {

        for (int i = 0; i < Preguntas.size(); i++) {

            if (Preguntas.get(i).getId().equals(id)) {

                Preguntas.remove(i);

            }

        }

    }

    public void addPreguntaCifras() {

        PreguntaCifras pregAdd = new PreguntaCifras(MenuMapTiposOpciones.lineasEnun.get(Tipo));
        //this.number = PreguntasCifras.size();
        pregAdd.setId("preg_" + number++);
        PreguntasCifras.add(pregAdd);
    }

    public void deletePreguntaCifras(CloseEvent event) {
        for (int i = 0; i < PreguntasCifras.size(); i++) {

            if (PreguntasCifras.get(i).getId().equals(event.getComponent().getId())) {

                PreguntasCifras.remove(i);

            }

        }

    }

    public void addPreguntaCampoTexto() {
        PreguntaCampoTexto pregAdd3 = new PreguntaCampoTexto(MenuMapTiposOpciones.lineasEnun.get(Tipo), MenuMapTiposOpciones.posiblesSol.get(Tipo), 1);
        // this.number = PreguntasCampoTexto.size();
        pregAdd3.setId("preg_" + number++);
        PreguntasCampoTexto.add(pregAdd3);

    }

    public void deletePreguntaCampoTexto(CloseEvent event) {
        for (int i = 0; i < PreguntasCampoTexto.size(); i++) {

            if (PreguntasCampoTexto.get(i).getId().equals(event.getComponent().getId())) {

                PreguntasCampoTexto.remove(i);

            }

        }

    }

    public void addPreguntaSiNo() {

        PreguntaSiNo pregAdd = new PreguntaSiNo(MenuMapTiposOpciones.elementosPanel.get(Tipo), MenuMapTiposOpciones.lineasEnun.get(Tipo));
        //this.number = PreguntasSiNo.size();
        pregAdd.setId("preg_" + number++);
        PreguntasSiNo.add(pregAdd);
    }

    public void deletePreguntaSiNo(CloseEvent event) {
        for (int i = 0; i < PreguntasSiNo.size(); i++) {

            if (PreguntasSiNo.get(i).getId().equals(event.getComponent().getId())) {

                PreguntasSiNo.remove(i);

            }

        }

    }

    public void addPreguntaPanelesLetras() {

        PreguntaPanelesLetras pregAdd = new PreguntaPanelesLetras(MenuMapTiposOpciones.letrasPanel.get(Tipo), MenuMapTiposOpciones.Pistas.get(Tipo));
        //this.number = PreguntasPanelesLetras.size();
        pregAdd.setId("preg_" + number++);
        PreguntasPanelesLetras.add(pregAdd);
    }

    public void deletePreguntaPanelesLetras(CloseEvent event) {
        for (int i = 0; i < PreguntasPanelesLetras.size(); i++) {

            if (PreguntasPanelesLetras.get(i).getId().equals(event.getComponent().getId())) {

                PreguntasPanelesLetras.remove(i);

            }

        }

    }

    public void addPreguntaRelacionar() {

        PreguntaRelacionar pregAdd = new PreguntaRelacionar(MenuMapTiposOpciones.filasColumna.get(Tipo), MenuMapTiposOpciones.filasColumna.get(Tipo));
        //this.number = PreguntasRelacionar.size();
        pregAdd.setId("preg_" + number++);
        PreguntasRelacionar.add(pregAdd);
    }

    public void deletePreguntaRelacionar(CloseEvent event) {
        for (int i = 0; i < PreguntasRelacionar.size(); i++) {

            if (PreguntasRelacionar.get(i).getId().equals(event.getComponent().getId())) {

                PreguntasRelacionar.remove(i);

            }

        }

    }

    public void addPreguntaRespAbierta() {

        PreguntaRespAbierta pregAdd = new PreguntaRespAbierta(MenuMapTiposOpciones.lineasEnun.get(Tipo));
        //this.number = PreguntasRespAbierta.size();
        pregAdd.setId("preg_" + number++);
        PreguntasRespAbierta.add(pregAdd);
    }

    public void deletePreguntaRespAbierta(CloseEvent event) {
        for (int i = 0; i < PreguntasRespAbierta.size(); i++) {

            if (PreguntasRespAbierta.get(i).getId().equals(event.getComponent().getId())) {

                PreguntasRespAbierta.remove(i);

            }

        }

    }

    public void addPreguntaContarLetras() {

        PreguntaContarLetras pregAdd = new PreguntaContarLetras(18);

        pregAdd.setId("preg_" + number++);
        PreguntasContarLetras.add(pregAdd);
    }

    public void deletePreguntaContarLetras(CloseEvent event) {
        for (int i = 0; i < PreguntasContarLetras.size(); i++) {

            if (PreguntasContarLetras.get(i).getId().equals(event.getComponent().getId())) {

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

}
