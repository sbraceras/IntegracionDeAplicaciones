package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.logistica.businessDelegate.BusinessDelegate;
import com.logistica.dtos.LogDTO;

/**
 * Servlet implementation class InformesAuditoria
 */
@WebServlet("/InformesAuditoria")
public class InformesAuditoria extends HttpServlet {

	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public InformesAuditoria() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
		PrintWriter out = response.getWriter();
		try {
			List<LogDTO> logs = BusinessDelegate.getInstance().buscarLogs();

			out.println("<table class=\"mdl-data-table mdl-js-data-table mdl-shadow--2dp\" style=\"margin: 0 auto; top: 40px\">");

			out.println("<thead>");

			out.println("<tr>");

			out.println("<th class=\"mdl-data-table__cell--non-numeric\">Fecha/Hora</th>");
			out.println("<th class=\"mdl-data-table__cell--non-numeric\">Tipo Modulo</th>");
			out.println("<th class=\"mdl-data-table__cell--non-numeric\">Nombre Modulo</th>");
			out.println("<th class=\"mdl-data-table__cell--non-numeric\">Descripcion</th>");

			out.println("</tr>");

			out.println("</thead>");

			out.println("<tbody>");

			for(LogDTO log: logs) {
				out.println("<tr>");

				out.println("<td class=\"mdl-data-table__cell--non-numeric\">" +  sdf.format(log.getFecha())	+ "</td>");
				out.println("<td class=\"mdl-data-table__cell--non-numeric\">" +  log.getTipoModulo().name()	+ "</td>");
				out.println("<td class=\"mdl-data-table__cell--non-numeric\">" +  log.getNombreModulo() 		+ "</td>");
				out.println("<td class=\"mdl-data-table__cell--non-numeric\">" +  log.getDescripcion() 			+ "</td>");

				out.println("</tr>");
			}

			out.println("</tbody>");

			out.println("</table>");

		} catch (Exception e) {
			e.printStackTrace();

			if (out != null) {
				out.println("<h2> Error al recuperar los Informes de Auditoria </h2>");
			}
		}
	}

}
