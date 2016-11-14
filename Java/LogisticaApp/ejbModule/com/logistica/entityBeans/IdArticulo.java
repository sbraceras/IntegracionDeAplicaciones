package com.logistica.entityBeans;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class IDArticulo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Column(nullable = false)
	private int id;
	
	@Column(nullable = false)
	private String nombreDeposito;
	
	public IDArticulo() {
	}

	public String getNombreDeposito() {
		return nombreDeposito;
	}

	public void setNombreDeposito(String nombreDeposito) {
		this.nombreDeposito = nombreDeposito;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result
				+ ((nombreDeposito == null) ? 0 : nombreDeposito.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		IDArticulo other = (IDArticulo) obj;
		if (id != other.id)
			return false;
		if (nombreDeposito == null) {
			if (other.nombreDeposito != null)
				return false;
		} else if (!nombreDeposito.equals(other.nombreDeposito))
			return false;
		return true;
	}

}
