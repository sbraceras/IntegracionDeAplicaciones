package com.logistica.enums;

public enum EstadoOrdenDespacho {
	//Fue emitida, es decir que solo se le asocio el Despacho que la envie, pero aun esta no se envio al despacho
	Emitida,
	//Enviada, significa que ya fue enviada y recibida correctamente por el despacho
	Enviada,
	//Se envio la orden al despacho elegido pero fallo por algun motivo
	Rechazada,
	//Ya se le entrego al cliente la venta
	Entregada;
}
