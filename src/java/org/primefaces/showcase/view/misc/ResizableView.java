package org.primefaces.showcase.view.misc;
 
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import org.primefaces.event.ResizeEvent;
 
@ManagedBean
public class ResizableView {
     
    public void onResize(ResizeEvent event) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, 
                        "Image resized", "Width:" + event.getWidth() + ",Height:" + event.getHeight());
 
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
}