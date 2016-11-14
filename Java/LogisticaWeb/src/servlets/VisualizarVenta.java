package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.logistica.businessDelegate.BusinessDelegate;
import com.logistica.dtos.BestSellerDTO;
import com.logistica.dtos.VentaDTO;

/**
 * Servlet implementation class Test
 */
@WebServlet("/VisualizarVenta")
public class VisualizarVenta extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public VisualizarVenta() {
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int idVenta = Integer.parseInt(request.getParameter("idVenta"));
		String nombrePortal = request.getParameter("nombrePortal");
		
		VentaDTO dto = new VentaDTO();
		dto.setId(idVenta);
		dto.setNombrePortal(nombrePortal);
		
		VentaDTO ventaReal = BusinessDelegate.getInstance().obtenerVenta(dto);
		
		
		request.setAttribute("ventaReal", ventaReal);
		
		request.getRequestDispatcher("jsp/visualizarVenta.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		//Llamar metodo BD para enviar el ranking a los portales
		RequestDispatcher dispatcher=request.getRequestDispatcher("index.html");
		dispatcher.forward(request, response);
	}

}
