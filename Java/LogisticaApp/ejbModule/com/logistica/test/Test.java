package com.logistica.test;

import java.util.ArrayList;
import java.util.Calendar;

import com.logistica.bean.AdministradorVentas;
import com.logistica.dtos.ClienteDTO;
import com.logistica.dtos.CoordenadaDTO;
import com.logistica.dtos.DireccionDTO;
import com.logistica.dtos.ItemVentaDTO;
import com.logistica.dtos.VentaDTO;
import com.logistica.entities.Direccion;
import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

public class Test {

	public static void main(String[] args) {

		 AdministradorVentas admVentas;
		 VentaDTO venta = new VentaDTO();
		 ClienteDTO cliente = new ClienteDTO();
		 DireccionDTO direccion = new DireccionDTO();
		 CoordenadaDTO coordenada = new CoordenadaDTO();
		 ArrayList<ItemVentaDTO> itemsVenta = new ArrayList<ItemVentaDTO>();
		 
		 coordenada.setLatitud(-34.6166938);
		 coordenada.setLongitud(-58.3815892);
		 coordenada.setId(1);
		 direccion.setAltura(4444);
		 direccion.setCalle("Larralde");
		 direccion.setDpto("A");
		 direccion.setPiso(2);
		 direccion.setCoordenada(coordenada);
		 cliente.setApellido("Miani");
		 cliente.setNombre("Agustin");
		 cliente.setDni(37702257);
		 cliente.setId(1);
		 cliente.setMail("agustinmiani@gmail.com");
		 cliente.setDireccion(direccion);
		 
		 venta.setCliente(cliente);
		 venta.setFechaHora(Calendar.getInstance().getTime());
		 venta.setId(2);
		 venta.setIpModulo("192.168.1.2");
		 venta.setItemsVenta(itemsVenta);
		 venta.setModulo("PortalWeb1");
		 venta.setMontoTotal(2150);
		 
		 admVentas.recepcionDeVenta(venta);
	}

}
