package com.logistica.entityBeans;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.logistica.dtos.OrdenDespachoDTO;
import com.logistica.enums.EstadoOrdenDespacho;
@Entity
@Table(name="OrdenesDespacho")
public class OrdenDespacho {
	
	//No vamos a hacer que sea generated, ya que la va a generar el modulo
	//De despacho al que le mandemos y recibimos la pk compuesta que ellos
	//Digan, analizar si esto va a ser una pk compuesta de nuestro lado.
	@Id
	@Column(name="idOrdenDespacho")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	//Esta ID la genera el modulo de despacho
	private int idExterna;
	private Date fecha;
	private EstadoOrdenDespacho estado;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="idModulo")
	private Despacho despacho;
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "idVenta") 
	private Venta venta;
	
	public OrdenDespacho() {
		this.idExterna = -1;
	}
	
	public OrdenDespacho(int id, Date fecha, EstadoOrdenDespacho estado, Despacho despacho, Venta venta) {
		this.id = id;
		this.fecha = fecha;
		this.estado = estado;
		this.despacho = despacho;
		this.venta = venta;
		this.idExterna = -1;
	}
	
	
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
	public Despacho getDespacho() {
		return despacho;
	}
	public void setDespacho(Despacho despacho) {
		this.despacho = despacho;
	}
	public Venta getVenta() {
		return venta;
	}
	public void setVenta(Venta venta) {
		this.venta = venta;
	}
	
	
	public int getIdExterna() {
		return idExterna;
	}

	public void setIdExterna(int idExterna) {
		this.idExterna = idExterna;
	}

	public OrdenDespachoDTO toDTO(){
		OrdenDespachoDTO ordenDespachoDTO = new OrdenDespachoDTO();
		ordenDespachoDTO.setDespacho(this.despacho.toDTO());
		ordenDespachoDTO.setEstado(this.estado);
		ordenDespachoDTO.setFecha(this.fecha);
		ordenDespachoDTO.setId(this.id);
		ordenDespachoDTO.setVenta(this.venta.getId());
		ordenDespachoDTO.setIdExterna(this.idExterna);
		return ordenDespachoDTO;
	}
	
}
