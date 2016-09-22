package dtos;

public class DespachoDTO {
	private int id;
	private DireccionDTO direccion;
	private String descripcion;
	private boolean estado;
	
	public DespachoDTO() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public DireccionDTO getDireccion() {
		return direccion;
	}

	public void setDireccion(DireccionDTO direccion) {
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
	
}
