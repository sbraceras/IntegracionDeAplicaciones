package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.mail.Flags.Flag;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.logistica.businessDelegate.BusinessDelegate;
import com.logistica.dtos.DespachoDTO;
import com.logistica.dtos.OrdenDespachoDTO;
import com.logistica.dtos.VentaDTO;
import com.logistica.enums.EstadoOrdenDespacho;

/**
 * Servlet implementation class VentasSinDespacho
 */
@WebServlet("/EnviarVentasEmitidas")
public class EnviarVentasEmitidas extends HttpServlet {
	private static final long serialVersionUID = 1L;
	boolean flag = false;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EnviarVentasEmitidas() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		List<OrdenDespachoDTO> ordenes = null;
		
		//Esto hay que usarlo cuando ya funciona enviar las Ordenes a los Despachos
//		try {
//			ordenes = BusinessDelegate.getInstance().enviarOrdenesEmitidas();
//		} catch (Exception e1) {
//			e1.printStackTrace();
//		}
//		

		//Esto hay que borrarlo es un MOCK
		
		ordenes = new ArrayList<OrdenDespachoDTO>();
		OrdenDespachoDTO orden1 = new OrdenDespachoDTO();
		OrdenDespachoDTO orden2 = new OrdenDespachoDTO();
		OrdenDespachoDTO orden3 = new OrdenDespachoDTO();
		DespachoDTO despacho = new DespachoDTO();
		despacho.setNombre("G05");
		orden1.setVenta(14);
		orden2.setVenta(15);
		orden3.setVenta(16);
		orden1.setDespacho(despacho);
		orden2.setDespacho(despacho);
		orden3.setDespacho(despacho);
		orden1.setEstado(EstadoOrdenDespacho.Enviada);
		orden2.setEstado(EstadoOrdenDespacho.Rechazada);
		orden3.setEstado(EstadoOrdenDespacho.Enviada);
		
		orden1.setFecha(new Date());
		orden2.setFecha(new Date());
		orden3.setFecha(new Date());
		
		orden1.setIdExterna(123);
		orden2.setIdExterna(124);
		orden3.setIdExterna(125);
		
		ordenes.add(orden1);
		ordenes.add(orden2);
		ordenes.add(orden3);
		
		request.setAttribute("ordenes", ordenes);
		request.getRequestDispatcher("jsp/ordenesEnviadas.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		doGet(request, response);
	}

}
