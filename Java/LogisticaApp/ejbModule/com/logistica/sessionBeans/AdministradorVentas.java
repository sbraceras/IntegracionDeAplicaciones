package com.logistica.sessionBeans;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.logistica.dtos.ItemVentaDTO;
import com.logistica.dtos.VentaDTO;
import com.logistica.entityBeans.Articulo;
import com.logistica.entityBeans.Cliente;
import com.logistica.entityBeans.Estandar;
import com.logistica.entityBeans.IdArticulo;
import com.logistica.entityBeans.Modulo;
import com.logistica.entityBeans.Venta;
import com.logistica.enums.EstadoVenta;
import com.logistica.enums.TipoModulo;


@Stateless
@LocalBean
public class AdministradorVentas {

	@PersistenceContext(unitName="MyPersistenceUnit")
	private EntityManager em;
	
		
// fecha de venta,cliente, hora, monto, descripcion/identificacion del portal,lista de itemsventa
// Cliente: dni, nombre, apellido, latidud, longitud.
// ItemVenta: 		
			
	//El WEBService se ocupa de recepcionar el JSON y parsearlo en un VentaDTO
	public void recepcionDeVenta(VentaDTO ventaDTO) throws Exception{
		
		//El nombre del portal es univoco
		Estandar portalWeb = (Estandar) buscarModulo(ventaDTO.getNombrePortal());

		if (portalWeb == null)
			//Si el portal no existe, falla la recepcion de la venta
			throw new Exception("El portal no existe.");
		
		try {
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
			
			IdArticulo idArticulo;
			for(ItemVentaDTO item: ventaDTO.getItemsVenta()){
				idArticulo = new IdArticulo();
				idArticulo.setId(item.getArticulo().getIdProducto());
				idArticulo.setNombreDeposito(item.getArticulo().getNombreDeposito());
				Articulo articulo = em.find(Articulo.class, idArticulo);
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
			em.flush();
			em.persist(venta);

		} catch (Exception e) {
			throw new Exception("Error al recibir la venta: " + e.getMessage());
		}
	}
	
	
	// BORRAR !!
	public void agregarPortalWeb() {
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
	
	private Modulo buscarModulo (String nombreModulo){
		
		Modulo modulo = (Modulo) em.createQuery("Select modulo from Modulo modulo where modulo.nombre =:nombre").setParameter("nombre", nombreModulo).getSingleResult();
		return modulo;
		
	}
	
}
