package dtos;
public class VentaDTO {
	private int id;
	private Collection<ItemVentaDTO> itemsVenta;
	private Date fechaHora;
	private float montoTotal;
	private OrdenDespachoDTO ordenDespacho;
	private Modulo portal;
	private ClienteDTO cliente;
	private EstadoVenta estado;
}
