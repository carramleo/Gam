package org.primefaces.showcase.view.button;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.component.UIOutput;
import javax.faces.component.html.HtmlInputText;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import org.primefaces.Pregunta.PreguntaCifras;
import org.primefaces.Pregunta.PreguntaOpciones;

import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.inputtextarea.InputTextarea;
import org.primefaces.component.panel.Panel;
import org.primefaces.component.selectoneradio.SelectOneRadio;

@ManagedBean
@ViewScoped
public class ButtonView implements Serializable {

    private List<PreguntaOpciones> Preguntas = new ArrayList<PreguntaOpciones>();
    private List<PreguntaCifras> PreguntasCifras = new ArrayList<PreguntaCifras>();
    int number = 0;
    boolean hidden = false;

    public void hideOrShow() {

        if (hidden == false) {
            hidden = true;
        }
    }

    public boolean isHidden() {
        return hidden;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @PostConstruct

    public void addPregunta() {
        
        
        PreguntaOpciones pregAdd = new PreguntaOpciones(6,3);
        this.number = Preguntas.size();
        pregAdd.setId(number);
        Preguntas.add(pregAdd);
    }
    
    public void addPreguntaCifras() {
        
        
        PreguntaCifras pregAdd = new PreguntaCifras(3);
        this.number = PreguntasCifras.size();
        pregAdd.setId(number);
        PreguntasCifras.add(pregAdd);
    }

    public List<PreguntaOpciones> getPreguntas() {
        return Preguntas;
    }

    public List<PreguntaCifras> getPreguntasCifras() {
        return PreguntasCifras;
    }


    
    

}
