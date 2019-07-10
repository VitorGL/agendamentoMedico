<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.ArrayList" import="java.net.URLEncoder"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

<title>Escolher especialidade</title>
</head>
<body>
<h1>Selecione a especialidade médica</h1>
<form action="Inicio" method="POST">
    <table>
    	<%
    	
    	ArrayList<ArrayList<String>> especialidades = (ArrayList<ArrayList<String>>)request.getAttribute("especialidades");
    	
    	if(especialidades!= null && !especialidades.isEmpty())
    	{
    	 for(ArrayList<String> linha : especialidades) {

			    out.println("<tr><td><label>"+linha+"</label></td><td><input type=\"radio\" name=\"cod_especialidade\" size=\"20\" value=\""+linha.get(0)+"\" required/> </td></tr>");
		    }	
    	 }
    	else
    	{
    		String message = "Não existem especialidades.";
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