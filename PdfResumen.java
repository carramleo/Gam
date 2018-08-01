/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.juntadeandalucia.ieca.util;

import com.lowagie.text.Cell;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Rectangle;
import com.lowagie.text.Row;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfTable;
import com.lowagie.text.pdf.PdfWriter;
import es.juntadeandalucia.ieca.beans.hibernate.IValidacion;
import es.juntadeandalucia.ieca.beans.hibernate.ValidacionDef;
import es.juntadeandalucia.ieca.beans.hibernate.ValidacionMat;
import es.juntadeandalucia.ieca.beans.hibernate.ValidacionPart;
import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.List;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author rolmo
 */
public class PdfResumen {

    private static IValidacion selectedValida;
    private static List<Resumen> lstResumen;
    private static final String ERRORES_DEFUNCIONES = "\tErrores de defunciones por lote y validación";
    private static final String ERRORES_MATRIMONIOS = "\tErrores de matrimonios por lote y validación";
    private static final String ERRORES_PARTOS = "\tErrores de partos por lote y validación";
    private static final String NOMBRE_FICHERO_DEFUNCIONES = "\tResumen Validación Defunciones";
    private static final String NOMBRE_FICHERO_MATRIMONIOS = "\tResumen Validación Matrimonios";
    private static final String NOMBRE_FICHERO_PARTOS = "\tResumen Validación Partos";
    private static final String CABECERA_FICHERO_LOTE = "\tLote";
    private static final String CABECERA_FICHERO_FECHA = "\tFValid";
    private static final String CABECERA_FICHERO_OBSER = "\tObservaciones";
    private static final String CABECERA_FICHERO_TIPO = "\tTipo";
    private static final String CABECERA_TABLA_TIPO = "\tTipo";
    private static final String CABECERA_TABLA_ERROR = "\tError";
    private static final String CABECERA_TABLA_DESC = "\tDescripción";
    private static final String CABECERA_TABLA_TOTAL = "\tTotal";
    
    
    public static void createPDF(IValidacion validacion, List<Resumen> lst) {
        try { //catch better your exceptions, this is just an example
            selectedValida = validacion;
            lstResumen = lst;
            FacesContext context = FacesContext.getCurrentInstance();
            Document document = new Document();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PdfWriter.getInstance(document, baos);

            if (!document.isOpen()) {
                document.open();
            }

            Paragraph preface = exportpreface();
            document.add(preface);

            PdfPTable pdfTable = exportPDFTable();
            document.add(pdfTable);

            //Keep modifying your pdf file (add pages and more)
            document.close();

            String fileName = "PDFFile";

            if (selectedValida instanceof ValidacionDef) {
                fileName = NOMBRE_FICHERO_DEFUNCIONES;
            } else if (selectedValida instanceof ValidacionMat) {
                fileName = NOMBRE_FICHERO_MATRIMONIOS;
            } else if (selectedValida instanceof ValidacionPart) {
                fileName = NOMBRE_FICHERO_PARTOS;
            }

            writePDFToResponse(context.getExternalContext(), baos, fileName);

            context.responseComplete();

        } catch (Exception e) {
            //e.printStackTrace();          
        }
    }

    private static PdfPTable exportPDFTable() {
        int numberOfColumns = 4;
        int[] relativeWidths = new int[]{15, 15, 55, 15};

        Resumen item = null;
        PdfPTable pdfTable = new PdfPTable(numberOfColumns);

        pdfTable.setWidthPercentage(100);
        BaseFont helvetica = null;

        Font font = new Font(helvetica, 10, Font.NORMAL);

        pdfTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
        pdfTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
        pdfTable.getDefaultCell().setBackgroundColor(Color.lightGray);

        pdfTable.addCell(new Paragraph(CABECERA_TABLA_TIPO, font));
        pdfTable.addCell(new Paragraph(CABECERA_TABLA_ERROR, font));
        pdfTable.addCell(new Paragraph(CABECERA_TABLA_DESC, font));
        pdfTable.addCell(new Paragraph(CABECERA_TABLA_TOTAL, font));

        pdfTable.getDefaultCell().setBackgroundColor(Color.white);

        for (int i = 0; i < lstResumen.size(); i++) { //lstPdfTable is the list from your datatable. A List of "itemOfList" type
            item = lstResumen.get(i);
            pdfTable.addCell(new Paragraph(item.getTipo().equals("Error") ? "E" : "A", font));
            pdfTable.addCell(new Paragraph(item.getError(), font));
            pdfTable.addCell(new Paragraph(item.getDescripcion(), font));
            pdfTable.addCell(new Paragraph(item.getTotal(), font));
        }
        try {
            pdfTable.setWidths(relativeWidths);
        } catch (Exception e) {
            //document exception
        }

        return pdfTable;
    }

    private static void writePDFToResponse(ExternalContext externalContext, ByteArrayOutputStream baos, String fileName) {
        try {
            externalContext.responseReset();
            externalContext.setResponseContentType("application/pdf");
            externalContext.setResponseHeader("Expires", "0");
            externalContext.setResponseHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
            externalContext.setResponseHeader("Pragma", "public");
            externalContext.setResponseHeader("Content-disposition", "attachment;filename=" + fileName + ".pdf");
            externalContext.setResponseContentLength(baos.size());
            OutputStream out = externalContext.getResponseOutputStream();
            baos.writeTo(out);
            externalContext.responseFlushBuffer();
        } catch (Exception e) {
            //e.printStackTrace();
        }
    }

    private static void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }

    private static Paragraph exportpreface() {

        BaseFont helvetica = null;

        Font font = new Font(helvetica, 13, Font.NORMAL);
        String linea = "";

        Paragraph preface = new Paragraph();
        addEmptyLine(preface, 1);

        if (selectedValida.getFechaValidacion() != null) {
            linea = linea + "\tFecha de Validación: " + selectedValida.getFechaValidacion().toString() + "      ";
        }

        if (selectedValida instanceof ValidacionDef) {
            linea = linea + ERRORES_DEFUNCIONES;
        } else if (selectedValida instanceof ValidacionMat) {
            linea = linea + ERRORES_MATRIMONIOS;
        } else if (selectedValida instanceof ValidacionPart) {
            linea = linea + ERRORES_PARTOS;
        }

        preface.add(new Paragraph(linea, font));
        addEmptyLine(preface, 1);

        PdfPTable cabecera = tableCabecera();
        preface.add(cabecera);
        addEmptyLine(preface, 1);

        if ((selectedValida.getAnoloteHasta() != null)
                && (selectedValida.getMesloteHasta() != null)
                && (selectedValida.getProvloteDesde() != null)) {

            linea = "   ";
            linea = linea + "Anolote Hasta: " + selectedValida.getAnoloteHasta().toString() + "   "
                    + "Meslote Hasta: " + selectedValida.getMesloteHasta() + "   "
                    + "Provlote Hasta: " + selectedValida.getProvloteDesde();

            addEmptyLine(preface, 1);
        }

        addEmptyLine(preface, 1);

        return preface;

    }

    private static PdfPTable tableCabecera() {
        int numberOfColumns = 4;

        PdfPTable pdfTable = new PdfPTable(numberOfColumns);

        pdfTable.setWidthPercentage(100);
        BaseFont helvetica = null;

        Font font = new Font(helvetica, 10, Font.BOLD);

        pdfTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
        pdfTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
        pdfTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
        pdfTable.getDefaultCell().setBackgroundColor(Color.lightGray);

        pdfTable.addCell(new Paragraph(CABECERA_FICHERO_LOTE, font));
        pdfTable.addCell(new Paragraph(CABECERA_FICHERO_FECHA, font));
        pdfTable.addCell(new Paragraph(CABECERA_FICHERO_OBSER, font));
        pdfTable.addCell(new Paragraph(CABECERA_FICHERO_TIPO, font));

        pdfTable.getDefaultCell().setBackgroundColor(Color.white);
        font = new Font(helvetica, 10, Font.NORMAL);
        pdfTable.addCell(new Paragraph(selectedValida.getAnoloteDesde() + "  " + selectedValida.getMesloteDesde() + "  "
                + selectedValida.getProvloteDesde(), font));
        pdfTable.addCell(new Paragraph(selectedValida.getFechaValidacion().toString(), font));
        pdfTable.addCell(new Paragraph(selectedValida.getObservaciones(), font));
        pdfTable.addCell(new Paragraph(selectedValida.getTipoValidacion(), font));

        //document exception
        return pdfTable;

        
    }

}
