package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.logistica.businessDelegate.BusinessDelegate;
import com.logistica.dtos.ResumenPortalDTO;

/**
 * Servlet implementation class Test
 */
@WebServlet("/VentasPortales")
public class VentasPortales extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public VentasPortales() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    	public void init() {
		
		try {
			super.init();
//			bd = BusinessDelegate.getInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

		List<ResumenPortalDTO> resumenPortal = BusinessDelegate.getInstance().obtenerVentasPortal();
		
		request.setAttribute("resumenPortal", resumenPortal);
		
		request.getRequestDispatcher("jsp/ventasPortales.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		//Llamar metodo BD para enviar el ranking a los portales
		RequestDispatcher dispatcher=request.getRequestDispatcher("index.html");
		dispatcher.forward(request, response);
	}

}
