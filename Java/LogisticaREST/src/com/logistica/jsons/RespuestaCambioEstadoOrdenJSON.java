package com.logistica.jsons;

public class RespuestaCambioEstadoOrdenJSON {

	private boolean resultado;	// true
	private String message;		// "Actualizacion de estado exitosa"

	public RespuestaCambioEstadoOrdenJSON() {
		
	}

	public boolean isResultado() {
		return resultado;
	}

	public void setResultado(boolean resultado) {
		this.resultado = resultado;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
