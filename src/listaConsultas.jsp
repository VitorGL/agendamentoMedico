<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.ArrayList" import="java.net.URLEncoder"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Consultas</title>
</head>
<body>
<h1>Consultas</h1>

    <table border="1">
    	<%
    	
    	ArrayList<ArrayList<String>> consultas = (ArrayList<ArrayList<String>>)request.getAttribute("consultas");
    	
    	if(consultas!= null && !consultas.isEmpty())
    	{
    	 for(ArrayList<String> linha : consultas) {

    		 	out.println("<tr><td></td><th>"+linha.get(0)+"</th></tr><tr><th>"+linha.get(1)+"</th><td align=\"center\">X</td></tr>");
			    //out.println("<tr><td><label>"+linha+"</label></td><td><input type=\"radio\" name=\"CRM\" size=\"20\" value=\""+linha.get(0)+"\" required/> </td></tr>");
		    }	
    	 }
    	else
    	{
    		String message = "Não existem consultas para este médico.";
        	response.sendRedirect("listaMedicos.jsp?message=" + URLEncoder.encode(message, "UTF-8"));
    	}
    	
    	%>
    
    </table>
    <p>${param.message}</p>


</body>
</html>