import dtos.DespachoDTO;

public class Despacho {
	
	private int id;
	private Direccion direccion;
	private String descripcion;
	private boolean estado;
	
	
	public Despacho(int id, Direccion direccion, String descripcion,
			boolean estado) {
		this.id = id;
		this.direccion = direccion;
		this.descripcion = descripcion;
		this.estado = estado;
	}


	public Despacho() {
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public Direccion getDireccion() {
		return direccion;
	}


	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}


	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	public boolean isEstado() {
		return estado;
	}


	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	
	public DespachoDTO toDTO(){
		DespachoDTO dto = new DespachoDTO();
		
	}
	
}
