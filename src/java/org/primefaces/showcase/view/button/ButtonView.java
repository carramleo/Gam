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
    
    
    public void guardarPreguntas() {
        Preguntas.size();
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage("Preguntas grabadas"));
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
        pregAdd.setId("preg_"+number++);
        Preguntas.add(pregAdd);
    }

    public void deletePregunta(CloseEvent event) {
        
        for(int i=0; i<Preguntas.size();i++){
            
            if(Preguntas.get(i).getId().equals(event.getComponent().getId())){
                
                Preguntas.remove(i);
                
                
            }
            
        }

    }

    public void addPreguntaCifras() {

        PreguntaCifras pregAdd = new PreguntaCifras(SelectOneMenuTipos.lineasEnun.get(Tipo));
        //this.number = PreguntasCifras.size();
        pregAdd.setId("preg_"+number++);
        PreguntasCifras.add(pregAdd);
    }

    public void deletePreguntaCifras(String id) {
        int index=0;
        for (PreguntaCifras pregDel : PreguntasCifras) {

             if(pregDel.getId().equals(id)){
                Preguntas.remove(index);
            }
            index++;
        }

    }

    public void addPreguntaCampoTexto() {
        PreguntaCampoTexto pregAdd3 = new PreguntaCampoTexto(SelectOneMenuTipos.lineasEnun.get(Tipo), SelectOneMenuTipos.posiblesSol.get(Tipo), 1);
        // this.number = PreguntasCampoTexto.size();
        pregAdd3.setId("preg_"+number++);
        PreguntasCampoTexto.add(pregAdd3);

    }

    public void deletePreguntaCampoTexto(String id) {
        int index=0;
        for (PreguntaCampoTexto pregDel : PreguntasCampoTexto) {

             if(pregDel.getId().equals(id)){
                Preguntas.remove(index);
            }
            index++;
        }

    }

    public void addPreguntaSiNo() {

        PreguntaSiNo pregAdd = new PreguntaSiNo(SelectOneMenuTipos.numOpciones.get(Tipo), SelectOneMenuTipos.lineasEnun.get(Tipo));
        //this.number = PreguntasSiNo.size();
        pregAdd.setId("preg_"+number++);
        PreguntasSiNo.add(pregAdd);
    }

    public void deletePreguntaSiNo(String id) {
        int index=0;
        for (PreguntaSiNo pregDel : PreguntasSiNo) {

             if(pregDel.getId().equals(id)){
                Preguntas.remove(index);
            }
            index++;
        }

    }

    public void addPreguntaPanelesLetras() {

        PreguntaPanelesLetras pregAdd = new PreguntaPanelesLetras(SelectOneMenuTipos.letrasPanel.get(Tipo), SelectOneMenuTipos.PistasLetrasPanel.get(Tipo));
        //this.number = PreguntasPanelesLetras.size();
        pregAdd.setId("preg_"+number++);
        PreguntasPanelesLetras.add(pregAdd);
    }

    public void deletePreguntaPanelesLetras(String id) {
        int index=0;
        for (PreguntaPanelesLetras pregDel : PreguntasPanelesLetras) {

             if(pregDel.getId().equals(id)){
                Preguntas.remove(index);
            }
            index++;
        }

    }

    public void addPreguntaRelacionar() {

        PreguntaRelacionar pregAdd = new PreguntaRelacionar(SelectOneMenuTipos.filasColumna.get(Tipo), SelectOneMenuTipos.filasColumna.get(Tipo));
        //this.number = PreguntasRelacionar.size();
        pregAdd.setId("preg_"+number++);
        PreguntasRelacionar.add(pregAdd);
    }

    public void deletePreguntaRelacionar(String id) {
        int index=0;
        for (PreguntaRelacionar pregDel : PreguntasRelacionar) {

             if(pregDel.getId().equals(id)){
                Preguntas.remove(index);
            }
            index++;
        }

    }

    public void addPreguntaRespAbierta() {

        PreguntaRespAbierta pregAdd = new PreguntaRespAbierta(SelectOneMenuTipos.lineasEnun.get(Tipo));
        //this.number = PreguntasRespAbierta.size();
        pregAdd.setId("preg_"+number++);
        PreguntasRespAbierta.add(pregAdd);
    }

    public void deletePreguntaRespAbierta(String id) {
        int index=0;
        for (PreguntaRespAbierta pregDel : PreguntasRespAbierta) {

             if(pregDel.getId().equals(id)){
                Preguntas.remove(index);
            }
            index++;
        }

    }

    public void addPreguntaContarLetras() {

        PreguntaContarLetras pregAdd = new PreguntaContarLetras(18);
        
        pregAdd.setId("preg_"+number++);
        PreguntasContarLetras.add(pregAdd);
    }

    public void deletePreguntaContarLetras(String id) {
        int index=0;
        for (PreguntaContarLetras pregDel : PreguntasContarLetras) {

             if(pregDel.getId().equals(id)){
                Preguntas.remove(index);
            }
            index++;
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
