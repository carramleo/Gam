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
import org.primefaces.Pregunta.Pregunta;

import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.inputtextarea.InputTextarea;
import org.primefaces.component.panel.Panel;
import org.primefaces.component.selectoneradio.SelectOneRadio;

@ManagedBean
@ViewScoped
public class ButtonView implements Serializable {

    private List<Pregunta> Preguntas = new ArrayList<Pregunta>();
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
        Pregunta pregAdd = new Pregunta(1);
        this.number = Preguntas.size();
        pregAdd.setId(number);
        Preguntas.add(pregAdd);
    }

    public List<Pregunta> getPreguntas() {
        return Preguntas;
    }

}
