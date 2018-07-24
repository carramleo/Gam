/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.primefaces.showcase.view.input;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author carlo
 */
@ManagedBean
@SessionScoped
public class SelectOneMenuView implements Serializable{

    private String tipo;
    private Map<String, String> tipos = new HashMap<String, String>();
    private String tipoSeleccionado;
    private Map<String, String> tiposSeleccionados = new HashMap<String, String>();
    private Map<String, Map<String, String>> data = new HashMap<String, Map<String, String>>();


    @PostConstruct
    public void init() {
        tipos.put("Respuestas con opciones", "TipoOpciones");
        tipos.put("Respuestas con cifras", "TipoCifras");
        tipos.put("Respuestas con campo de texto", "TipoCampoTexto");
        tipos.put("Paneles Si o No", "TipoSiNo");
        tipos.put("Paneles de letras", "TipoPanelesLetras");
        tipos.put("Columnas para relacionar", "TipoRelacionar");
        tipos.put("Respuesta abierta", "TipoRespAbierta");
        tipos.put("Contar letras", "TipoContarLetras");

        Map<String, String> map = new HashMap<String, String>();
        map.put("Tipo 1", "Tipo1");
        map.put("Tipo 5", "Tipo5");
        map.put("Tipo 7", "Tipo7");
        map.put("Tipo 12", "Tipo12");
        map.put("Tipo 13", "Tipo13");
        map.put("Tipo 14", "Tipo14");
        map.put("Tipo 15", "Tipo15");
        map.put("Tipo 17", "Tipo17");
        map.put("Tipo 19", "Tipo19");
        map.put("Tipo 50x50", "Tipo50x50");
        map.put("Tipo Arco iris", "TipoArcoIris");
        map.put("Tipo Bomba dorada", "TipoBombaDorada");
        map.put("Tipo BombaPlateada", "TipoBombaPlateada");
        map.put("Tipo El Duelo", "TipoDuelo");
        map.put("Tipo La trampa", "TipoTrampa");
        map.put("Tipo Los Sabios", "TipoSabios");
        data.put("TipoOpciones", map);

        map = new HashMap<String, String>();
        map.put("Tipo 3", "Tipo3");
        map.put("Tipo 9", "Tipo9");
        map.put("Tipo La patata caliente", "TipoPatata");
        data.put("TipoCifras", map);

        map = new HashMap<String, String>();
        map.put("Tipo 4", "Tipo4");
        map.put("Tipo Cada sabio con su tema", "TipoSabio");
        map.put("Tipo El pulsador", "TipoSalvador");
        map.put("Tipo Empieza por", "TipoEmpieza");
        map.put("Tipo La escalera", "TipoEscalera");
        map.put("Tipo Letra a letra", "TipoLetra");
        map.put("Tipo Pasapalabra", "TipoPasapalabra");
        map.put("Tipo Palabra Imposible", "TipoImposible");
        map.put("Tipo La última llamada", "TipoLlamada");
        map.put("Tipo Identity", "TipoIdentity");
        data.put("TipoCampoTexto", map);

        map = new HashMap<String, String>();
        map.put("Tipo 6", "Tipo6");
        map.put("Tipo 8", "Tipo8");
        map.put("Tipo 10", "Tipo10");
        data.put("TipoPanelesLetras", map);

        map = new HashMap<String, String>();
        map.put("Tipo 2", "Tipo2");
        map.put("Tipo 18", "Tipo18");
        data.put("TipoSiNo", map);

        map = new HashMap<String, String>();
        map.put("Tipo 16", "Tipo16");
        map.put("Tipo Parejas Ocultas", "TipoParejas");
        map.put("Tipo De par en par", "TipoPar");
        map.put("Tipo Del tirón", "TipoDelTiron");
        data.put("TipoRelacionar", map);

        map = new HashMap<String, String>();
        map.put("Tipo Jurado", "TipoJurado");
        map.put("Tipo 123", "Tipo123");
        data.put("TipoRespAbierta", map);

        map = new HashMap<String, String>();
        map.put("Tipo Cuantas Letras", "TipoCuantasLetras");
        map.put("Tipo Letritas que faltan", "TipoLetritas");
        data.put("TipoContarLetras", map);

    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Map<String, String> getTipos() {
        return tipos;
    }

    public void setTipos(Map<String, String> tipos) {
        this.tipos = tipos;
    }

    public void onTipoChange() {
        if (tipo != null && !tipo.equals("")) {
            tiposSeleccionados = data.get(tipo);
            
        } else {
            tiposSeleccionados = new HashMap<String, String>();
        }
    }

    public String getTipoSeleccionado() {
        return tipoSeleccionado;
    }

    public void setTipoSeleccionado(String tipoSeleccionado) {
        this.tipoSeleccionado = tipoSeleccionado;
    }

    public Map<String, String> getTiposSeleccionados() {
        return tiposSeleccionados;
    }

    public void setTiposSeleccionados(Map<String, String> tiposSeleccionados) {
        this.tiposSeleccionados = tiposSeleccionados;
    }

    public Map<String, Map<String, String>> getData() {
        return data;
    }

    public void setData(Map<String, Map<String, String>> data) {
        this.data = data;
    }

   
}
