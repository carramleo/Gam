package org.primefaces.showcase.view.data.datatable;
 
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.List;

import org.primefaces.Pregunta.Pregunta;
import org.primefaces.event.RowEditEvent;
import org.primefaces.showcase.service.SolService;
 
@ManagedBean(name="dtAddRowView")
@ViewScoped
public class AddRowView implements Serializable {
 
	private List<Pregunta> Preguntas;
	private boolean mostrar = false;
 
	@ManagedProperty("#{solService}")
    private SolService service;
 
    @PostConstruct
    public void init() {
        Preguntas = service.createPreguntas(1);
        
    }
 
    public List<Pregunta> getPreguntas() {
        return Preguntas;
    }
 
 
    public List<String> getSoluciones() {
        return service.getSoluciones();
    }
 
    public void setService(SolService service) {
        this.service = service;
    }
 
    public void onRowEdit(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Pregunta editada");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
 
    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Pregunta no editada");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    

    public void onAddNew() {
        // Add one new question to the table:
        Pregunta pregAdd = new Pregunta(1);
        Preguntas.add(pregAdd);
        pregAdd.setId(pregAdd.getId() + 1);
        
    }
    
    public void mostrarOn() {
    	this.mostrar = true;
    }

	public boolean isMostrar() {
		return this.mostrar;
	}

	public void setMostrar(boolean mostrar) {
		this.mostrar = mostrar;
	}
    
 
}