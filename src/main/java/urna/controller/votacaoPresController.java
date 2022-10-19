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
 * Servlet implementation class votacaoController
 */
public class votacaoPresController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public votacaoPresController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UrnaEletronica urnaEletronica = UrnaEletronica.GetInstance();
		ArrayList<Candidato> candidatos = urnaEletronica.buscarCandidatos("presidente");
		request.setAttribute("candidatos", candidatos);
		RequestDispatcher dispatcher = request.getRequestDispatcher("votarPres.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/hmtl; charset=UTF-8");
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String msg = null;
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("confirmaPres.jsp");
				
		String numero = request.getParameter("numCandidato");
		String confirma = request.getParameter("confimar");
		String branco = request.getParameter("branco");
		String mostrarCandidatos = request.getParameter("mostraCandidatos");
		UrnaEletronica urnaEletronica = UrnaEletronica.GetInstance();
		Candidato candidato = null;
		
		if (numero.equals("") && mostrarCandidatos == null && branco == null) {
			dispatcher = request.getRequestDispatcher("votarPres.jsp");
			dispatcher.forward(request, response);
		}
		if (numero != "") {
			try {
				Integer.valueOf(numero);
			} catch (NumberFormatException e) {
				msg = "*Apenas numeros";
				request.setAttribute("message", msg);
				dispatcher = request.getRequestDispatcher("votarPres.jsp");
				dispatcher.forward(request, response);
			}
		} 
		
		if (mostrarCandidatos != null)
			this.doGet(request, response);
		
		if (numero != "" && branco == null && confirma !=null)
			candidato = urnaEletronica.buscaCandidatoPeloNumero(Integer.valueOf(numero));
		
		if (confirma != null && numero != null && candidato == null) {
			System.out.println("Não existe este candidato");
		} else if (confirma != null && numero != null) {
			dispatcher = request.getRequestDispatcher("confirmaPres.jsp");
			request.setAttribute("candidatos", candidato);
			request.setAttribute("numero", candidato.getNumeroIdentificador());
			dispatcher.forward(request, response);
		} else if (branco != null) {
			candidato = urnaEletronica.buscaCandidatoPeloNumero(-1);
			request.setAttribute("numero", candidato.getNumeroIdentificador());
			dispatcher.forward(request, response);
		}

	}
	
}
