package urna.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import urna.model.Candidato;
import urna.model.UrnaEletronica;

import java.io.IOException;

/**
 * Servlet implementation class confirmaDepController
 */
public class confirmaDepController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public confirmaDepController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	RequestDispatcher dispatcher = request.getRequestDispatcher("votarDep.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/hmtl; charset=UTF-8");
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("votarPres.jsp");
				
		String numero = request.getParameter("numCandidato");
		String confirma = request.getParameter("confimar");
		String corrige = request.getParameter("corrige");
		String branco = request.getParameter("branco");
		UrnaEletronica urnaEletronica = UrnaEletronica.GetInstance();
		Candidato candidato = null;
		
		if (numero != "" && confirma !=null) {
			if (Integer.valueOf(numero) == 0)
				candidato = urnaEletronica.buscaCandidatoPeloNumero(-1);
			else 
				candidato = urnaEletronica.buscaCandidatoPeloNumero(Integer.valueOf(numero));
		}
		
		if (confirma != null) {
			urnaEletronica.atualizarVotos(candidato.getVotos()+1, candidato.getNumeroIdentificador());
			System.out.println(candidato);
			request.setAttribute("confirmado", 1);
			dispatcher = request.getRequestDispatcher("confirmaDep.jsp");
			dispatcher.forward(request, response);
		} else if (corrige != null) {
			dispatcher = request.getRequestDispatcher("votarDep.jsp");
			dispatcher.forward(request, response);
		} else if (branco != null) {
			dispatcher = request.getRequestDispatcher("votarDep.jsp");
			dispatcher.forward(request, response);
		}

	}
	
}