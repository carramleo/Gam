package org.primefaces.showcase.view.button;

import java.io.Serializable;
import java.util.ArrayList;
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
import org.primefaces.showcase.view.input.SelectOneMenuTipos;
import org.primefaces.showcase.view.input.SelectOneMenuView;

@ManagedBean
@SessionScoped
public class ButtonView implements Serializable {

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

        PreguntaOpciones pregAdd = new PreguntaOpciones(SelectOneMenuTipos.numOpciones.get(Tipo), SelectOneMenuTipos.formatoOp.get(Tipo), SelectOneMenuTipos.lineasEnun.get(Tipo));
        //this.number = Preguntas.size();
        pregAdd.setId("preg_" + number++);
        Preguntas.add(pregAdd);
    }
    
    public void addRespPregunta(PreguntaOpciones pregActual){
        
        pregActual.getRespuestas().add("");
    }
    
    public void deleteRespPregunta(PreguntaOpciones pregActual, int number){
        
         pregActual.getRespuestas().remove(number);
        
    }

    public void deletePregunta(CloseEvent event) {

        for (int i = 0; i < Preguntas.size(); i++) {

            if (Preguntas.get(i).getId().equals(event.getComponent().getId())) {

                Preguntas.remove(i);

            }

        }

    }

    public void addPreguntaCifras() {

        PreguntaCifras pregAdd = new PreguntaCifras(SelectOneMenuTipos.lineasEnun.get(Tipo));
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
        PreguntaCampoTexto pregAdd3 = new PreguntaCampoTexto(SelectOneMenuTipos.lineasEnun.get(Tipo), SelectOneMenuTipos.posiblesSol.get(Tipo), 1);
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

        PreguntaSiNo pregAdd = new PreguntaSiNo(SelectOneMenuTipos.elementosPanel.get(Tipo), SelectOneMenuTipos.lineasEnun.get(Tipo));
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

        PreguntaPanelesLetras pregAdd = new PreguntaPanelesLetras(SelectOneMenuTipos.letrasPanel.get(Tipo), SelectOneMenuTipos.Pistas.get(Tipo));
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

        PreguntaRelacionar pregAdd = new PreguntaRelacionar(SelectOneMenuTipos.filasColumna.get(Tipo), SelectOneMenuTipos.filasColumna.get(Tipo));
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

        PreguntaRespAbierta pregAdd = new PreguntaRespAbierta(SelectOneMenuTipos.lineasEnun.get(Tipo));
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
