/*
 * Clase que simula el contenido de base de datos donde se almacena
 * toda la información de todos los tipos de juegos para la importación txt.
 */
package org.primefaces.showcase.view.MapaTipos;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author carlos
 */
@ManagedBean
@SessionScoped
/**
 *
 * @author cramos
 */
public class MapaTiposTXT {

    public static Map<String, String[]> filtroTXT = new HashMap<String, String[]>();
    String[] opciones = new String[5];
    Properties prop = new Properties();
    InputStream input;

    public void iniciarMapa() {

        try {

            input = getClass().getResource("Tipos.property").openStream();
            prop.load(input);
            Set<String> propertyNames = prop.stringPropertyNames();
            
            for (String Property : propertyNames) {
                String aux=prop.getProperty(Property);
                aux=aux.substring(1, aux.length()-1);
                opciones = aux.split(",");
                filtroTXT.put(Property, opciones);

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
