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
import com.logistica.dtos.RecepcionBestSellerDTO;

/**
 * Servlet implementation class Test
 */
@WebServlet("/EnviarBestSellers")
public class EnviarBestSellers extends HttpServlet {
	private static final long serialVersionUID = 1L;
//	private static BusinessDelegate bd;  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EnviarBestSellers() {
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

		
		BusinessDelegate bd= BusinessDelegate.getInstance();
		List<RecepcionBestSellerDTO> resultadoBestSeller;
		try {
//			resultadoBestSeller = bd.enviarReporteBestSeller();
			
			//Borrar esto es solo para simular comportamiento
			
			
			resultadoBestSeller = new ArrayList<RecepcionBestSellerDTO>();
			RecepcionBestSellerDTO resumen1 = new RecepcionBestSellerDTO();
			RecepcionBestSellerDTO resumen2 = new RecepcionBestSellerDTO();
			RecepcionBestSellerDTO resumen3 = new RecepcionBestSellerDTO();
			RecepcionBestSellerDTO resumen4 = new RecepcionBestSellerDTO();
			
			resumen1.setEstado("OK");
			resumen1.setMensaje("Actualizacion Exitosa");
			resumen1.setNombrePortal("G03");
			resumen2.setEstado("OK");
			resumen2.setMensaje("Actualizacion Exitosa");
			resumen2.setNombrePortal("G07");
			resumen3.setEstado("OK");
			resumen3.setNombrePortal("G10");
			resumen3.setMensaje("Actualizacion Exitosa");
			resumen4.setEstado("ERROR");
			resumen4.setMensaje("Se rompio todo :)");
			resumen4.setNombrePortal("G13");
			
			resultadoBestSeller.add(resumen1);
			resultadoBestSeller.add(resumen2);
			resultadoBestSeller.add(resumen3);
			resultadoBestSeller.add(resumen4);
			request.setAttribute("resultadoBestSeller", resultadoBestSeller);
			
			request.getRequestDispatcher("jsp/ResumenEnviobestSellers.jsp").forward(request, response);
			
			
			
		} catch (Exception e) {
			//Deberiamos Mostrar un mensaje de error
			e.printStackTrace();
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher dispatcher=request.getRequestDispatcher("index.html");
		dispatcher.forward(request, response);
	}

}
