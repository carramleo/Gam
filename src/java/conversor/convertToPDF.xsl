<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : convertToPDF.xsl
    Created on : 31 de agosto de 2018, 19:09
    Author     : carlo
    Description:
        Purpose of transformation follows.
-->

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

<xsl:output method="html" encoding="UTF-8" indent="yes"/>
    <xsl:template match="/Gamificacion">
        <html>
            <head>
               
                
            </head>
            <body style='font-family:Tahoma;font-size:9pt;letter-spacing:0.5px'>
                <h2 style="text-align:center;" >
                    <strong>Título: <xsl:value-of select="titulo" /></strong>
                </h2>
                <br></br>
                <h2>
                    <strong>Autor: <xsl:value-of select="autor" /></strong>
                </h2>
                <br></br>
                <h3>Preguntas:</h3>
                <br></br>
                <xsl:for-each select="/Gamificacion/preguntas/pregunta">
				
				
				<h3><xsl:value-of select="tema" /></h3>
                                <h4>
                                   <strong> <xsl:value-of  select="position()" />:
                                    <xsl:value-of select="enunciado" /></strong>
                                </h4>
					<p style="text-decoration: underline;" >Solucion: <xsl:value-of select="@sol" /></p>
					 <xsl:for-each select="respuesta">
						
							<p><xsl:value-of select="position()" />: <xsl:value-of select="." /></p>
						
					</xsl:for-each>
            
			</xsl:for-each>
            </body>
        </html>
    </xsl:template>
    
</xsl:stylesheet>
