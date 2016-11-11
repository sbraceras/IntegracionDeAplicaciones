package com.logistica.jsons;

public class RespuestaRecepcionVentaJSON {

	private boolean resultado;	
	private String message;		// {"resultado":true,"message":"Recepcion de venta exitosa"}
								// {"resultado":false,"message":"Error en la recepcion"}

	public RespuestaRecepcionVentaJSON() {
		
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
