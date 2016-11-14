package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.logistica.businessDelegate.BusinessDelegate;
import com.logistica.dtos.DespachoDTO;
import com.logistica.dtos.OrdenDespachoDTO;
import com.logistica.dtos.VentaDTO;

/**
 * Servlet implementation class ConfirmarAsignacionDespacho
 */
@WebServlet("/ConfirmarAsignacionDespacho")
public class ConfirmarAsignacionDespacho extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConfirmarAsignacionDespacho() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		//Metodod de BD para asignarle el despacho a la venta con los parametros idVenta y idDespacho
		System.out.println(request.getParameter("idVenta"));
		System.out.println(request.getParameter("combo"));
		VentaDTO venta = new VentaDTO();
		OrdenDespachoDTO ordenDespacho = new OrdenDespachoDTO();
		DespachoDTO despachoDTO = new DespachoDTO();
		
		
		venta.setId((Integer.parseInt(request.getParameter("idVenta"))));
		venta.setNombrePortal(request.getParameter("nombrePortal"));
		despachoDTO.setNombre(request.getParameter("combo"));
		ordenDespacho.setDespacho(despachoDTO);
		venta.setOrdenDespacho(ordenDespacho);
		BusinessDelegate.getInstance().emitirOrdenDespacho(venta);
		
		List<VentaDTO> ventasSinDespacho = BusinessDelegate.getInstance().listarVentasSinOrdenDespacho();
		
		request.setAttribute("ventas", ventasSinDespacho);
		request.getRequestDispatcher("jsp/ventasSinDespacho.jsp").forward(request, response);
	}

}
