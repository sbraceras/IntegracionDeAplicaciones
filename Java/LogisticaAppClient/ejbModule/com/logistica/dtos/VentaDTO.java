package com.logistica.dtos;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.logistica.enums.EstadoVenta;

public class VentaDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	/* ***** Atributos del JSON ***** */
	private int id;
	private ClienteDTO cliente;
	private double latitud;
	private double longitud;
	private Date fechaHoraVenta;
	private float monto;
	private String nombrePortal;
	private List<ItemVentaDTO> itemsVenta;
	/* ****************************** */

	private OrdenDespachoDTO ordenDespacho;
	private String ipModulo; // Debatir grupo
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

	public Date getFechaHoraVenta() {
		return fechaHoraVenta;
	}

	public void setFechaHoraVenta(Date fechaHoraVenta) {
		this.fechaHoraVenta = fechaHoraVenta;
	}

	public float getMonto() {
		return monto;
	}

	public void setMonto(float monto) {
		this.monto = monto;
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

	public String getNombrePortal() {
		return nombrePortal;
	}

	public void setNombrePortal(String nombrePortal) {
		this.nombrePortal = nombrePortal;
	}

	public ClienteDTO getCliente() {
		return cliente;
	}

	public void setCliente(ClienteDTO cliente) {
		this.cliente = cliente;
	}

	public double getLatitud() {
		return this.cliente.getDireccion().getCoordenada().getLatitud();
	}

	public void setLatitud(double latitud) {
		this.cliente.getDireccion().getCoordenada().setLatitud(latitud);
	}

	public double getLongitud() {
		return this.cliente.getDireccion().getCoordenada().getLongitud();
	}

	public void setLongitud(double longitud) {
		this.cliente.getDireccion().getCoordenada().setLongitud(longitud);
	}

	public EstadoVenta getEstado() {
		return estado;
	}

	public void setEstado(EstadoVenta estado) {
		this.estado = estado;
	}

}
