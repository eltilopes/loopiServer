<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml"
	xmlns:ui="http://java.sun.com/jsf/facelets"
	xmlns:h="http://java.sun.com/jsf/html"
	xmlns:f="http://java.sun.com/jsf/core">
<head>
	<link rel="icon" href="resources/img/favicon.png" type="image/png" />
	<title>APISME</title>
	  <link href="css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<p><b>APISME ROUTES SERVICE:</b></p>
<p><b>HOST:</b> http://aio.fortaleza.ce.gov.br/aio</p>
<table border="1" style="width: 90%">
 <tr style="text-align: center;">
    <td>URL</td>
     <td>METHOD</td>
    <td>PARAMETER</td>
    <td>RESPONSE</td>
    <td>DESCRIBE</td> 
  </tr>
  <tr style="text-align: center;">
    <td>/documento/buscar/{id}</td>
     <td>GET</td>
    <td style="text-align: left;">
	    <ul>
		  <li>String id</li>
		</ul>
	</td>
    <td>byte[]</td>
    <td>Busca o arquivo anexo no chamado pelo id do documento. </td> 
  </tr>
  
   <tr style="text-align: center;">
    <td>/mobile/version</td>
     <td>GET</td>
    <td style="text-align: center;">
	   -
	</td>
    <td>{versionCode:String,
    	 versionName:String,
    	 mandatory: boolean}</td>
    <td>Busca o arquivo anexo no chamado pelo id do documento. </td> 
  </tr>
</table>
</body>
</html>