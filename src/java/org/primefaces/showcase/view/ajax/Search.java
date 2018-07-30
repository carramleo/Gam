package org.primefaces.showcase.view.ajax;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class Search implements Serializable {

    private String etapa;
    private Map<String, String> etapas = new HashMap<String, String>();
    private String asignatura;
    private String tema;
    private String nombreFich;
    private String autor;
    private String idioma;
    private Map<String, String> idiomas = new HashMap<String, String>();
    private String tipo;
    private Map<String, String> tipos = new HashMap<String, String>();
    private String individual;
    private Map<String, String> individuales = new HashMap<String, String>();
    private String intervalo;
    private Map<String, String> intervalos = new HashMap<String, String>();
    private String palabra;

    @PostConstruct
    public void init() {
        etapas.put("Infantil", "infantil");
        etapas.put("Educación Permanente", "permanente");
        etapas.put("ESO", "eso");
        etapas.put("Bachillerato", "bachiller");
        etapas.put("CFGM", "cfgm");
        etapas.put("CFGS", "cfgs");
        etapas.put("General", "general");
        etapas.put("PCPI", "pcpi");
        etapas.put("Primaria", "primaria");
        etapas.put("Régimen especial Música", "musica");
        etapas.put("Universidad", "universidad");
        

        idiomas.put("Español", "esp");
        idiomas.put("Inglés", "ingl");
        idiomas.put("Francés", "fra");
        idiomas.put("Alemán", "alm");
        idiomas.put("Griego", "grie");
        idiomas.put("Latín", "lat");
        idiomas.put("Italiano", "ita");
        idiomas.put("Catalán", "cat");

        tipos.put("Tipo 1 (270/ilim/3/4/A-D)", "Tipo1");
        tipos.put("Tipo 2 (3/8/3/8/Si-No)", "Tipo2");
        tipos.put("Tipo 3 (3/ilim/5/0/cifra)", "Tipo3");
        tipos.put("Tipo 4 (2/ilim/3-y-pistas/0-3/palabra)", "Tipo4");
        tipos.put("Tipo 5 (2/ilim/6/3/1-3)", "Tipo5");
        tipos.put("Tipo 6 (2/1/21 letras-8 pistas/0/palabra)", "Tipo6");
        tipos.put("Tipo 7 (3/ilim/3/4-comodines/A-D)", "Tipo7");
        tipos.put("Tipo 8 (2/1/63 letras-pistas/0/frase)", "Tipo8");
        tipos.put("Tipo 9 (4/ilim/3/0/cifra)", "Tipo9");
        tipos.put("Tipo 10 (2/1/63 letras-9 pistas/0/frase)", "Tipo10");        
        tipos.put("Tipo 12 (4/ilim/3/2-4/A-B-D)", "Tipo12");
        tipos.put("Tipo 13 (7/ilim/3/6/A-F)", "Tipo13");
        tipos.put("Tipo 14 (2/ilim/4/2/A-B)", "Tipo14");
        tipos.put("Tipo 15 (3/15/3/2-4/A-B-D)", "Tipo15");
        tipos.put("Tipo 16 (2/1/2 columnas/8/relacionar)", "Tipo16");
        tipos.put("Tipo 17 (2/5/3/2-6/A-B-F)", "Tipo17");
        tipos.put("Tipo 18 (2/1/3/15/Si-No)", "Tipo18");
        tipos.put("Tipo 19 (2/8/3/4-5/A-D-E)", "Tipo19");
        
        
        individuales.put("Tipo 50x50 (1/ilim/3/2/A-B)", "Tipo50x50");
        individuales.put("Tipo Arco iris (1/24/3/4/A-D)", "TipoArcoIris");
        individuales.put("Tipo Bomba dorada (1/1/3/10/1-10)", "TipoBombaDorada");
        individuales.put("Tipo BombaPlateada (1/ilim/3/7/1-7)", "TipoBombaPlateada");
        individuales.put("Tipo El Duelo (1/2/3/7/1-2)", "TipoDuelo");
        individuales.put("Tipo La trampa (1/ilim/3/9/1-9)", "TipoTrampa");
        individuales.put("Tipo Los Sabios (1/15/4/2/A-B)", "TipoSabios");
        individuales.put("Tipo La patata caliente (1/ilim/7/0/cifras)", "TipoPatata");
        individuales.put("Tipo Cada sabio con su tema (1/40/5/0/palabra)", "TipoSabio");
        individuales.put("Tipo El pulsador (1/ilim/6/0/palabra)", "TipoSalvador");
        individuales.put("Tipo Empieza por (1/ilim/3-pistas/0/palabra)", "TipoEmpieza");
        individuales.put("Tipo La escalera (1/ilim/6/0/palabras)", "TipoEscalera");
        individuales.put("Tipo Letra a letra (1/25/6/0/palabras)", "TipoLetra");
        individuales.put("Tipo Pasapalabra (1/25/6/0/palabras)", "TipoPasapalabra");
        individuales.put("Tipo Palabra Imposible (1/8/5/8/1-8)", "TipoImposible");
        individuales.put("Tipo La última llamada (1/6/4/6/1-6)", "TipoLlamada");
        individuales.put("Tipo Identity (1/9/6/9/1-9)", "TipoIdentity");
        individuales.put("Tipo Parejas Ocultas (1/1/2 columnas/10/relacionar)", "TipoParejas");
        individuales.put("Tipo De par en par (1/9/1/9/1-9)", "TipoPar");
        individuales.put("Tipo Del tirón (1/10/1/0/palabra)", "TipoDelTiron");
        individuales.put("Tipo Jurado (1/12/4/0/abierta)", "TipoJurado");
        individuales.put("Tipo Cuantas Letras (1/1/1/1-18/cifras)", "TipoCuantasLetras");
        individuales.put("Tipo Letritas que faltan (1/1/18 letras y 3 pistas/0/letras)", "TipoLetritas");
        
        
        
        intervalos.put("02-04", "02-04");
        intervalos.put("05-09", "05-09");
        intervalos.put("10-14", "10-14");
        intervalos.put("15-19", "15-19");
        intervalos.put("20-24", "20-24");
        intervalos.put("25-29", "25-29");
        intervalos.put("30-35", "30-35");
        intervalos.put("35-40", "35-40");
        intervalos.put("40-49", "40-49");
        intervalos.put("50-59", "50-59");
        intervalos.put("60-69", "60-69");
        intervalos.put("70-79", "70-79");
        intervalos.put("80-89", "80-89");
        intervalos.put("90-99", "90-99");
        intervalos.put(">=100", ">=100");
        
    }

    public String getEtapa() {
        return etapa;
    }

    public void setEtapa(String etapa) {
        this.etapa = etapa;
    }

    public Map<String, String> getEtapas() {
        return etapas;
    }

    public void setEtapas(Map<String, String> etapas) {
        this.etapas = etapas;
    }

    public String getAsignatura() {
        return asignatura;
    }

    public void setAsignatura(String asignatura) {
        this.asignatura = asignatura;
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public String getNombreFich() {
        return nombreFich;
    }

    public void setNombreFich(String nombreFich) {
        this.nombreFich = nombreFich;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public Map<String, String> getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(Map<String, String> idiomas) {
        this.idiomas = idiomas;
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

    public String getIndividual() {
        return individual;
    }

    public void setIndividual(String individual) {
        this.individual = individual;
    }

    public Map<String, String> getIndividuales() {
        return individuales;
    }

    public void setIndividuales(Map<String, String> individuales) {
        this.individuales = individuales;
    }

    public String getIntervalo() {
        return intervalo;
    }

    public void setIntervalo(String intervalo) {
        this.intervalo = intervalo;
    }

    public Map<String, String> getIntervalos() {
        return intervalos;
    }

    public void setIntervalos(Map<String, String> intervalos) {
        this.intervalos = intervalos;
    }

    public String getPalabra() {
        return palabra;
    }

    public void setPalabra(String palabra) {
        this.palabra = palabra;
    }
    
    

}
