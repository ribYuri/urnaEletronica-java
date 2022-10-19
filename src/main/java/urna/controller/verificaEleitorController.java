package urna.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import urna.model.Eleitor;
import urna.model.UrnaEletronica;

import java.io.IOException;

/**
 * Servlet implementation class verificaEleitorController
 */
public class verificaEleitorController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public verificaEleitorController() {
        super();
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
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("votarDep.jsp");
		
		String encerrar = request.getParameter("encerrar");
		
		if (encerrar != null) {
			dispatcher = request.getRequestDispatcher("resultados.jsp");
			dispatcher.forward(request, response);
		}
		
		String titulo = request.getParameter("tituloEleitor");
		String msg = null;
		
		if (titulo.equals("") && encerrar == null) {
			dispatcher = request.getRequestDispatcher("identificar.jsp");
			dispatcher.forward(request, response);
		}
		
		if (titulo != "") {
			try {
				Integer.valueOf(titulo);
			} catch (NumberFormatException e) {
				msg = "*Apenas numeros";
				request.setAttribute("message", msg);
				dispatcher = request.getRequestDispatcher("identificar.jsp");
				dispatcher.forward(request, response);
			} finally {
				if (Integer.valueOf(titulo) != 0) {
			
					UrnaEletronica urnaEletronica = UrnaEletronica.GetInstance();
					Eleitor eleitor = new Eleitor();
					eleitor = urnaEletronica.buscaEleitorPeloTitulo(Integer.valueOf(titulo));
					
					if (eleitor != null && eleitor.isIdadeMinima()) {
						if (eleitor != null && !eleitor.isVotou()) {
							System.out.println(eleitor);
							dispatcher.forward(request, response);
							urnaEletronica.atualizaVotoEleitor(eleitor.getTitulo());
						} else if (eleitor != null && eleitor.isVotou()) {
							msg = "*Eleitor já votou!";
							request.setAttribute("message", msg);
							dispatcher = request.getRequestDispatcher("identificar.jsp");
							dispatcher.forward(request, response);			
						}	 
					}  else if (eleitor == null) {
						msg = "*Eleitor inexistente!";
						request.setAttribute("message", msg);
						dispatcher = request.getRequestDispatcher("identificar.jsp");
						dispatcher.forward(request, response);
					} else {
						msg = "*Eleitor abaixo da idade minima!";
						request.setAttribute("message", msg);
						dispatcher = request.getRequestDispatcher("identificar.jsp");
						dispatcher.forward(request, response);
					}
					
				} else {
					msg = "*Eleitor inexistente!";
					request.setAttribute("message", msg);
					dispatcher = request.getRequestDispatcher("identificar.jsp");
					dispatcher.forward(request, response);
				}
					
			}
		}
	}
		

}
