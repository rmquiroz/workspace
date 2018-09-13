<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Movimiento Entre Almacenes</title>
</head>
<body>
		<form action="TeaServlets" target="_blank">
		<input type="hidden" name="reportID" value="1" >
			<h2 align="center">MOVIMIENTO ENTRE ALMACENES  PRUEBA DE PAOLA</h2>
				<fieldset class="collapseClosed">
					<legend>Ingresar traspaso</legend>
						<table>
							<tr>
			    				<td align="right">Nombre del documento: </td>
			    				<td style="width: 341px;">
			    				<input type="text" id="tea" name="FOLIO_REM" style="width: 335px; ">
			    				</td>
		        				<td style="width: 92px; ">
		        				<input type="submit" value="Imprimir"  style="width: 86px;" name="ingresar" id="ingresar">
		        				</td>
							</tr>	
						</table>
				</fieldset>
	</form>
	
	
</body>
</html>
