package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.mail.Flags.Flag;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.logistica.businessDelegate.BusinessDelegate;
import com.logistica.dtos.VentaDTO;

/**
 * Servlet implementation class VentasSinDespacho
 */
@WebServlet("/VentasSinDespacho")
public class VentasSinDespacho extends HttpServlet {
	private static final long serialVersionUID = 1L;
	boolean flag = false;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VentasSinDespacho() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		List<VentaDTO> ventasSinDespacho = new ArrayList<VentaDTO>();
		try {
			ventasSinDespacho = BusinessDelegate.getInstance().listarVentasSinOrdenDespacho();
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("ventas", ventasSinDespacho);
		request.getRequestDispatcher("jsp/ventasSinDespacho.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		doGet(request, response);
	}

}
