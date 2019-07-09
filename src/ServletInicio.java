

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletInicio
 */
@WebServlet("/Inicio")
public class ServletInicio extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletInicio() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Banco banco = new Banco();
		
		request.setAttribute("especialidades", banco.especialidades());
		
		request.getRequestDispatcher("inicial.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Banco banco = new Banco();
		String cod_especialidade = request.getParameter("cod_especialidade");
		
		request.setAttribute("cod_especialidade", cod_especialidade);
		
		request.setAttribute("medicos", banco.medicos(cod_especialidade)); //passando todos os medicos com tal cod especialidade
		
		request.getRequestDispatcher("listaMedicos.jsp").forward(request, response);
	}

}
