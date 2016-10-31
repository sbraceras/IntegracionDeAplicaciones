package com.logistica.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.logistica.dtos.LogDTO;
import com.logistica.enums.TipoLog;

@Entity
@Table(name="Logs")
public class Log {
	
	@Id
	@Column(name="idLog")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private Date fecha;
	private TipoLog tipo;
	private String descripcion;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="idModulo")
	private Modulo modulo;
	
	public Log() {
		
	}

	public Log(int id, Date fecha, TipoLog tipo, String descripcion, Modulo modulo) {
		this.id = id;
		this.fecha = fecha;
		this.tipo = tipo;
		this.descripcion = descripcion;
		this.modulo = modulo;
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
	public TipoLog getTipo() {
		return tipo;
	}
	public void setTipo(TipoLog tipo) {
		this.tipo = tipo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Modulo getModulo() {
		return modulo;
	}
	public void setModulo(Modulo modulo) {
		this.modulo = modulo;
	}
	
	public LogDTO toDTO(){
		LogDTO logDTO = new LogDTO();
		logDTO.setDescripcion(this.descripcion);
		logDTO.setFecha(this.fecha);
		logDTO.setId(this.id);
		logDTO.setIp(this.modulo.getIp());
		logDTO.setNombreModulo(this.modulo.getNombre());
		logDTO.setTipo(this.tipo);
		return logDTO;
	}
	
	
}
