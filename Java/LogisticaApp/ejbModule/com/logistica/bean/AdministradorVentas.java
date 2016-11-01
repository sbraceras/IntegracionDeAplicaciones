package com.logistica.bean;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.logistica.dtos.ItemVentaDTO;
import com.logistica.dtos.VentaDTO;
import com.logistica.ejb.StatelessAdministradorVentasLocal;
import com.logistica.ejb.StatelessAdministradorVentasRemote;
import com.logistica.entities.Articulo;
import com.logistica.entities.Cliente;
import com.logistica.entities.Estandar;
import com.logistica.entities.Modulo;
import com.logistica.entities.Venta;
import com.logistica.enums.EstadoVenta;
import com.logistica.enums.TipoModulo;


@Stateless
@LocalBean
public class AdministradorVentas implements StatelessAdministradorVentasRemote, StatelessAdministradorVentasLocal {

	@PersistenceContext(unitName="MyPersistenceUnit")
	private EntityManager em;
	
		
// fecha de venta,cliente, hora, monto, descripcion/identificacion del portal,lista de itemsventa
// Cliente: dni, nombre, apellido, latidud, longitud.
// ItemVenta: 		
			
	//El WEBService se ocupa de recepcionar el JSON y parsearlo en un VENTADTO
	public boolean recepcionDeVenta (VentaDTO ventaDTO){
		
		//El nombre del portal es univoco
		
		Estandar portalWeb = em.find(Estandar.class, ventaDTO.getModulo());
		if(portalWeb == null)
		{
			//Si el portal no existe, falla la recepcion de la venta
			return false;
		}
		
		//Si el portal existe podemos continuar el proceso
		
		Cliente cliente = em.find(Cliente.class, ventaDTO.getCliente().getDni());
		if(cliente == null){
			//El cliente no existe, debemos persistirlo
			
			//Llamamos a este metodo que se ocupa de convertir de DTO a object
			cliente = Cliente.fromDTO(ventaDTO.getCliente());
			em.persist(cliente);			
		}
		else
		{
			//El cliente existe, le actualizamos sus coordenadas
			cliente.getDireccion().getCoordenada().setLatitud(ventaDTO.getCliente().getDireccion().getCoordenada().getLatitud());
			cliente.getDireccion().getCoordenada().setLongitud(ventaDTO.getCliente().getDireccion().getCoordenada().getLongitud());
			em.merge(cliente);
		}
		
		for(ItemVentaDTO item: ventaDTO.getItemsVenta()){
			Articulo articulo = em.find(Articulo.class, item.getArticulo().getId());
			if(articulo == null)
			{
				//El articulo no existe, debemos persistirlo
				articulo = Articulo.fromDTO(item.getArticulo());
				articulo.setVentasAcumuladas(item.getCantidad());
				em.persist(articulo);
			}
			else
			{
				//Actualizamos la cantidad vendida por el item
				articulo.setVentasAcumuladas(articulo.getVentasAcumuladas() + item.getCantidad());
				em.merge(articulo);
				//Debemos sumarle la cantidad vendida a las ventas acumuladas
			}
		}
		
		Venta venta = new Venta();
		venta = Venta.fromDTO(ventaDTO, cliente);
		venta.setEstado(EstadoVenta.EnProceso);
		venta.setModulo((Modulo) portalWeb);

		//Se persiste la venta como en proceso para luego en otro caso de uso
		//Se hara la orden de despacho
		em.persist(venta);
		
		return true;
	}
	
	
	//Borrar!!
	
	public void agregarPortalWeb (){
		
		Estandar estandar = new Estandar();
		estandar.setNombre("Mercadolibre");
		estandar.setIp("192.168.1.0");
		estandar.setTipoModulo(TipoModulo.PortalWeb);
		em.persist(estandar);
		
		estandar = new Estandar();
		estandar.setNombre("Al Mundo");
		estandar.setIp("192.168.1.50");
		estandar.setTipoModulo(TipoModulo.PortalWeb);
		
		em.persist(estandar);
		
	}
	
}
