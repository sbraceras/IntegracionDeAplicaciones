package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.logistica.businessDelegate.BusinessDelegate;
import com.logistica.dtos.DespachoDTO;
import com.logistica.dtos.VentaDTO;

/**
 * Servlet implementation class AsignacionDespacho
 */
@WebServlet("/AsignacionDespacho")
public class AsignacionDespacho extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AsignacionDespacho() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		request.getRequestDispatcher("jsp/asignacionDespacho.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		VentaDTO ventaSeleccionada = new VentaDTO();
		List<DespachoDTO> comboDespachos = new ArrayList<DespachoDTO>();
		DespachoDTO despachoCercano=null;
		try {
			List<VentaDTO> ventasSinDespacho = BusinessDelegate.getInstance().listarVentasSinOrdenDespacho();
			for(VentaDTO v : ventasSinDespacho){
				if(v.getId() == Integer.valueOf(request.getParameter("idVenta"))){
					ventaSeleccionada = v;
					break;
				}
			}
			List<DespachoDTO> despachos = BusinessDelegate.getInstance().obtenerDespachosActivos();
			
//			despachoCercano = BusinessDelegate.getInstance().obtenerDespachoCercanoCliente(ventaSeleccionada);
			
			//Esto esta hecho asi porque el proxy nos bloquea la señal
			despachoCercano = despachos.get(0);
			comboDespachos.add(despachoCercano);
			
			for(DespachoDTO despacho: despachos){
				if(despachoCercano.getId()!= despacho.getId())
					comboDespachos.add(despacho);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("despachoCercano", despachoCercano);
		request.setAttribute("comboDespachos", comboDespachos);
		request.setAttribute("ventaSeleccionada", ventaSeleccionada);
		request.getRequestDispatcher("jsp/asignacionDespacho.jsp").forward(request, response);
	}

}
