package urna.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import urna.model.Candidato;
import urna.model.UrnaEletronica;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Servlet implementation class resultadosController
 */
public class resultadosController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public resultadosController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("resultados.jsp");
		dispatcher.forward(request, response);
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/hmtl; charset=UTF-8");
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String resultDeps = request.getParameter("resultDeputado");
		String resultPres = request.getParameter("resultPresidente");
		String nuloBranco = request.getParameter("resultNuloBranco");
		String reiniciar = request.getParameter("resetar");
		UrnaEletronica urnaEletronica = UrnaEletronica.GetInstance();
		
		if (resultDeps != null) {
			ArrayList<Candidato> candidatos = urnaEletronica.buscarCandidatos("deputado");
			request.setAttribute("candidatos", candidatos);
			RequestDispatcher dispatcher = request.getRequestDispatcher("resultados.jsp");
			dispatcher.forward(request, response);
		} else if (resultPres != null) {
			ArrayList<Candidato> candidatos = urnaEletronica.buscarCandidatos("presidente");
			request.setAttribute("candidatos", candidatos);
			RequestDispatcher dispatcher = request.getRequestDispatcher("resultados.jsp");
			dispatcher.forward(request, response);
		} else if (nuloBranco != null) {
			ArrayList<Candidato> brancoNulo = urnaEletronica.buscarBrancoNulo();
			request.setAttribute("NuloBranco", brancoNulo);
			RequestDispatcher dispatcher = request.getRequestDispatcher("resultados.jsp");
			dispatcher.forward(request, response);
		} else if (reiniciar != null) {
			urnaEletronica.reiniciarVotacao();
			urnaEletronica.setVotacaoIniciada(false);
			RequestDispatcher dispatcher = request.getRequestDispatcher("identificar.jsp");
			dispatcher.forward(request, response);
		}
	}

}
