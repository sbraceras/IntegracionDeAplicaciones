package com.logistica.dtos;

import java.util.Date;

import com.logistica.entities.EstadoOrdenDespacho;

public class OrdenDespachoDTO {
	
	private int id;
	private Date fecha;
	private EstadoOrdenDespacho estado;
	private DespachoDTO despacho;
	private int venta; //Debatir por ahora seria el id de la venta
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public EstadoOrdenDespacho getEstado() {
		return estado;
	}
	public void setEstado(EstadoOrdenDespacho estado) {
		this.estado = estado;
	}
	public DespachoDTO getDespacho() {
		return despacho;
	}
	public void setDespacho(DespachoDTO despacho) {
		this.despacho = despacho;
	}
	public int getVenta() {
		return venta;
	}
	public void setVenta(int venta) {
		this.venta = venta;
	}
	
	
}
