<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : convertToPDF.xsl
    Created on : 31 de agosto de 2018, 19:09
    Author     : carlo
    Description:
        Purpose of transformation follows.
-->

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

<xsl:output method="xml" encoding="UTF-8" indent="yes"/>
    <xsl:template match="/Gamificacion">
        <html>
            <head>
                <title>Gamificacion</title>
                <meta name="description" content="Nuevo Juego"/>
                <h1>Nuevo Juego</h1>
            </head>
            <body style='font-family:Tahoma;font-size:9pt;letter-spacing:0.5px'>
                <h2>Titulo: <xsl:value-of select="titulo" /></h2>
                <h2>Autor: <xsl:value-of select="autor" /></h2>
                <xsl:for-each select="/Gamificacion/preguntas/pregunta">
				
				
				<h3><xsl:value-of select="tema" /></h3>
				<h4><xsl:value-of select="position()" />: <xsl:value-of select="enunciado" /></h4>
					<p>Solución: <xsl:value-of select="@sol" /></p>
					 <xsl:for-each select="respuesta">
						
							<p><xsl:value-of select="." /></p>
						
					</xsl:for-each>
            
			</xsl:for-each>
            </body>
        </html>
    </xsl:template>
    
</xsl:stylesheet>
