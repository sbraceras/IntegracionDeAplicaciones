package com.logistica.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.logistica.dto.ItemVentaDTO;
import com.logistica.dto.VentaDTO;

@Entity
@Table(name="Ventas")
public class Venta {
	
	@Id
	@Column(name="idVenta")
	private int id;
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="idVenta")
	private ArrayList<ItemVenta> itemsVenta;
	private Date fechaHora;
	private float montoTotal;
	
	@OneToOne(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinColumn(name="idVenta") //Debatir grupo
	private OrdenDespacho ordenDespacho;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="idModulo")
	private Modulo modulo;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="idCliente")
	private Cliente cliente;
	private EstadoVenta estado;
	
	public Venta() {
		
	}
	
	public Venta(int id, ArrayList<ItemVenta> itemsVenta, Date fechaHora, float montoTotal, OrdenDespacho ordenDespacho,
			Modulo modulo, Cliente cliente, EstadoVenta estado) {
		this.id = id;
		this.itemsVenta = itemsVenta;
		this.fechaHora = fechaHora;
		this.montoTotal = montoTotal;
		this.ordenDespacho = ordenDespacho;
		this.modulo = modulo;
		this.cliente = cliente;
		this.estado = estado;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public ArrayList<ItemVenta> getItemsVenta() {
		return itemsVenta;
	}
	public void setItemsVenta(ArrayList<ItemVenta> itemsVenta) {
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
	public OrdenDespacho getOrdenDespacho() {
		return ordenDespacho;
	}
	public void setOrdenDespacho(OrdenDespacho ordenDespacho) {
		this.ordenDespacho = ordenDespacho;
	}
	public Modulo getModulo() {
		return modulo;
	}
	public void setModulo(Modulo modulo) {
		this.modulo = modulo;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public EstadoVenta getEstado() {
		return estado;
	}
	public void setEstado(EstadoVenta estado) {
		this.estado = estado;
	}
	
	public VentaDTO toDTO(){
		VentaDTO ventaDTO = new VentaDTO();
		ventaDTO.setCliente(cliente.toDTO());
		ventaDTO.setEstado(this.estado);
		ventaDTO.setFechaHora(this.fechaHora);
		ventaDTO.setId(this.id);
		String ip = "" + this.modulo.getIp();
		ventaDTO.setIpModulo(ip);
		ItemVentaDTO itemVentaDTO;
		List<ItemVentaDTO> itemsVentaDTO = new ArrayList<ItemVentaDTO>();
		for(ItemVenta item: this.itemsVenta){
			itemVentaDTO = new ItemVentaDTO();
			itemVentaDTO = item.toDTO();
			itemsVentaDTO.add(itemVentaDTO);
		}
		ventaDTO.setItemsVenta(itemsVentaDTO);
		ventaDTO.setModulo(this.getModulo().getNombre());
		ventaDTO.setMontoTotal(this.montoTotal);
		ventaDTO.setOrdenDespacho(ordenDespacho.toDTO());
		return ventaDTO;
	}
}
