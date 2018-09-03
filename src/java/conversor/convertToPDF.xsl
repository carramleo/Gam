<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : convertToPDF.xsl
    Created on : 31 de agosto de 2018, 19:09
    Author     : carlo
    Description:
        Purpose of transformation follows.
-->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method='html' version='1.0' encoding='UTF-8' indent='yes'/>

    <!-- TODO customize transformation rules 
         syntax recommendation http://www.w3.org/TR/xslt 
    -->
    <xsl:template match="/Gamificacion">
        <html>
            <head>
                <title>Gamificacion</title>
                <meta name="description" content="Nuevo Juego"></meta>
                <h1>Nuevo Juego</h1>
            </head>
            <body>
                <h2><xsl:value-of select="titulo" /></h2>
                <h2><xsl:value-of select="autor" /></h2>
                <xsl:apply-templates />
            </body>
        </html>
    </xsl:template>
    
    <xsl:template match="preguntas">
        <p ></p>
        
        <xsl:for-each select="Gamificacion/preguntas/pregunta">
            <p>Solución: <xsl:attribute name="sol"></p>
            <h3><xsl:value-of select="tema" /></h3>
            <h4><xsl:value-of select="enunciado" /></h4>
           
                <p> <xsl:value-of select="respuesta" /></p>
            
            
        </xsl:for-each>
        <h3><xsl:value-of select="enunciado" /></h3>
    </xsl:template>

</xsl:stylesheet>
