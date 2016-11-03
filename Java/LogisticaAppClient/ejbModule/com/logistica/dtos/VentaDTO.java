package com.logistica.dtos;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.logistica.enums.EstadoVenta;

public class VentaDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private int id;
	private List<ItemVentaDTO> itemsVenta;
	private Date fechaHora;
	private float montoTotal;
	private OrdenDespachoDTO ordenDespacho;
	private String ipModulo; //Debatir grupo
	private String modulo; //Debatir grupo
	private ClienteDTO cliente;
	private EstadoVenta estado;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public List<ItemVentaDTO> getItemsVenta() {
		return itemsVenta;
	}
	public void setItemsVenta(List<ItemVentaDTO> itemsVenta) {
		this.itemsVenta = itemsVenta;
	}
	public Date getFechaHora() {
		return fechaHora;
	}
	public void setFechaHora(Date fechaHora) {
		this.fechaHora = fechaHora;
	}
	public float getMontoTotal() {
		return montoTotal;
	}
	public void setMontoTotal(float montoTotal) {
		this.montoTotal = montoTotal;
	}
	public OrdenDespachoDTO getOrdenDespacho() {
		return ordenDespacho;
	}
	public void setOrdenDespacho(OrdenDespachoDTO ordenDespacho) {
		this.ordenDespacho = ordenDespacho;
	}
	public String getIpModulo() {
		return ipModulo;
	}
	public void setIpModulo(String ipModulo) {
		this.ipModulo = ipModulo;
	}
	public String getModulo() {
		return modulo;
	}
	public void setModulo(String modulo) {
		this.modulo = modulo;
	}
	public ClienteDTO getCliente() {
		return cliente;
	}
	public void setCliente(ClienteDTO cliente) {
		this.cliente = cliente;
	}
	public EstadoVenta getEstado() {
		return estado;
	}
	public void setEstado(EstadoVenta estado) {
		this.estado = estado;
	}
	
}
