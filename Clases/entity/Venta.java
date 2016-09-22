public class Venta {
	private int id;
	private Collection<ItemVentaDTO> itemsVenta;
	private Date fechaHora;
	private float montoTotal;
	private OrdenDespachoDTO ordenDespacho;
	private Modulo modulo;
	private ClienteDTO cliente;
	private EstadoVenta estado;
}
