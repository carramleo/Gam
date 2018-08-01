/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pruebaxml;

import com.thoughtworks.xstream.XStream;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.Element;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;


/**
 *
 * @author rolmo
 */
public class Principal {
    
    private static final String FILE_NAME = "juegos.xml";
    
    public static void main(String[] args) {
        Pregunta p = new Pregunta();
        p.setId("PREGUNTA1");
        p.setEnunciado("ENUNCIADO");
        
        Multitest m = new Multitest();
        m.setId("MULTITEST1");
        
        List<Juego> juegos = new ArrayList<Juego>();
        
        
        juegos.add(p);
        juegos.add(m);
        
        Exportacion nuevoXml = new Exportacion();
        nuevoXml.setJuegos(juegos);
        
        jaxbObjectToXML(nuevoXml);
        XStream xstream = new XStream();
        xstream.alias("exportacion", Exportacion.class);
        xstream.alias("pregunta", Pregunta.class);
        xstream.alias("juegos", Multitest.class);
        xstream.addImplicitCollection(Juego.class, "list"); 
        
        String xml = xstream.toXML(nuevoXml);
        
        
        
    }
    
    private static void jaxbObjectToXML(Exportacion exp) {

        try {
            JAXBContext context = JAXBContext.newInstance(Exportacion.class);
            Marshaller m = context.createMarshaller();
            //for pretty-print XML in JAXB
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            // Write to System.out for debugging
            // m.marshal(emp, System.out);

            // Write to File
            m.marshal(exp, new File(FILE_NAME));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
