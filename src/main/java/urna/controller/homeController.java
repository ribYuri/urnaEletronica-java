package urna.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import urna.model.UrnaEletronica;

import java.io.IOException;

/**
 * Servlet implementation class homeController
 */
public class homeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public homeController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/hmtl; charset=UTF-8");
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String encerrar = request.getParameter("encerrar");
		String votar = request.getParameter("votar");
		UrnaEletronica urnaEletronica = UrnaEletronica.GetInstance();
		
		if (encerrar != null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("resultados.jsp");
			dispatcher.forward(request, response);
		} else if (votar != null) {
			urnaEletronica.setVotacaoIniciada(true);
			RequestDispatcher dispatcher = request.getRequestDispatcher("identificar.jsp");
			dispatcher.forward(request, response);		
		}
	}

}
