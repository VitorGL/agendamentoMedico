<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.ArrayList" import="java.net.URLEncoder"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Escolher especialidade</title>
</head>
<body>
<h1>Selecione o médico</h1>
<form action="Medico" method="POST">
    <table>
    	<%
    	
    	ArrayList<ArrayList<String>> medicos = (ArrayList<ArrayList<String>>)request.getAttribute("medicos");
    	
    	if(medicos!= null && !medicos.isEmpty())
    	{
    	 for(ArrayList<String> linha : medicos) {

			    out.println("<tr><td><label>"+linha+"</label></td><td><input type=\"radio\" name=\"CRM\" size=\"20\" value=\""+linha.get(0)+"\" required/> </td></tr>");
		    }	
    	 }
    	else
    	{
    		String message = "Não existem medicos para esta especialidade.";
        	response.sendRedirect("inicial.jsp?message=" + URLEncoder.encode(message, "UTF-8"));
    	}
    	
    	%>
        <tr>
            <td colspan="2">
                <input type="submit" value="Pronto"/>
            </td>
        </tr>
    </table>
    <p>${param.message}</p>
</form>

</body>
</html>