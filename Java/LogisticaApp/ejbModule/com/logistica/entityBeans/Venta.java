package com.logistica.entityBeans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.logistica.dtos.ItemVentaDTO;
import com.logistica.dtos.VentaDTO;
import com.logistica.enums.EstadoVenta;

@Entity
@Table(name = "Ventas")
public class Venta {

	@EmbeddedId
	private IDVenta id;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumns({ @JoinColumn(name = "idVenta"), @JoinColumn(name = "idModulo") })
	private List<ItemVenta> itemsVenta;

	private Date fechaHora;
	private float montoTotal;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "venta")
	private OrdenDespacho ordenDespacho;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idCliente")
	private Cliente cliente;

	private EstadoVenta estado;

	public Venta() {

	}

	public Venta(IDVenta id, List<ItemVenta> itemsVenta, Date fechaHora, float montoTotal, OrdenDespacho ordenDespacho,
			Cliente cliente, EstadoVenta estado) {
		super();
		this.id = id;
		this.itemsVenta = itemsVenta;
		this.fechaHora = fechaHora;
		this.montoTotal = montoTotal;
		this.ordenDespacho = ordenDespacho;
		this.cliente = cliente;
		this.estado = estado;
	}

	public IDVenta getId() {
		return id;
	}

	public void setId(IDVenta id) {
		this.id = id;
	}

	public List<ItemVenta> getItemsVenta() {
		return itemsVenta;
	}

	public void setItemsVenta(List<ItemVenta> itemsVenta) {
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

	public VentaDTO toDTO() {
		VentaDTO ventaDTO = new VentaDTO();

		ventaDTO.setId(this.id.getId());
		ventaDTO.setCliente(cliente.toDTO());
		ventaDTO.setEstado(this.estado);
		ventaDTO.setFechaHoraVenta(this.fechaHora); 
		ventaDTO.setIpModulo(this.id.getModulo().getIp());

		ItemVentaDTO itemVentaDTO;
		List<ItemVentaDTO> itemsVentaDTO = new ArrayList<ItemVentaDTO>();
		for (ItemVenta item : this.itemsVenta) {
			itemVentaDTO = new ItemVentaDTO();
			itemVentaDTO = item.toDTO();
			itemsVentaDTO.add(itemVentaDTO);
		}
		ventaDTO.setItemsVenta(itemsVentaDTO);
		ventaDTO.setNombrePortal(this.id.getModulo().getNombre());
		ventaDTO.setMonto(this.montoTotal);
		if(this.ordenDespacho!=null)
			ventaDTO.setOrdenDespacho(ordenDespacho.toDTO());
		return ventaDTO;
	}

	public static Venta fromDTO(VentaDTO dto, Cliente cliente) {
		Venta venta = new Venta();

		venta.setCliente(cliente);
		venta.setFechaHora(dto.getFechaHoraVenta());
		venta.setMontoTotal(dto.getMonto());

		IDVenta id = new IDVenta();
		id.setId(dto.getId());
		id.setModulo(null);
		venta.setId(id);

		List<ItemVenta> items = new ArrayList<ItemVenta>();
		ItemVenta item;

		for (ItemVentaDTO itemDTO : dto.getItemsVenta()) {
			item = new ItemVenta();
			item.setArticulo(Articulo.fromDTO(itemDTO.getArticulo()));
			item.setCantidad(itemDTO.getCantidad());
			item.setId(itemDTO.getId());
			items.add(item);
		}
		venta.setItemsVenta(items);

		return venta;
	}
}
