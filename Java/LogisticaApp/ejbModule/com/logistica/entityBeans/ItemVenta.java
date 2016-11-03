package com.logistica.entityBeans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.logistica.dtos.ItemVentaDTO;

@Entity
@Table(name="ItemsVenta")
public class ItemVenta {
	
	@Id
	@Column(name="idItemVenta")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private double cantidad;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="idArticulo")
	private Articulo articulo;
	
	public ItemVenta() {
		
	}
	
	public ItemVenta(int id, double cantidad, Articulo articulo) {
		this.id = id;
		this.cantidad = cantidad;
		this.articulo = articulo;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getCantidad() {
		return cantidad;
	}
	public void setCantidad(double cantidad) {
		this.cantidad = cantidad;
	}
	public Articulo getArticulo() {
		return articulo;
	}
	public void setArticulo(Articulo articulo) {
		this.articulo = articulo;
	}
	
	public ItemVentaDTO toDTO(){
		ItemVentaDTO itemVentaDTO = new ItemVentaDTO();
		itemVentaDTO.setArticulo(this.articulo.toDTO());
		itemVentaDTO.setCantidad(this.cantidad);
		itemVentaDTO.setId(this.id);
		return itemVentaDTO;
	}
	
}
