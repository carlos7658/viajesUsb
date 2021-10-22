/**  
 * @Title:  DestinoTest.java   
 * @Package co.edu.usbcali.viajesUsb   
 * @Description: description   
 * @author: Diego Pastrana     
 * @date:   31/08/2021 11:41:01 a. m.   
 * @version V1.0 
 * @Copyright: Universidad San de Buenaventura
 */
package co.edu.usbcali.viajesusb;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;
import java.util.List;


import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Rollback;

import co.edu.usbcali.viajesusb.domain.Destino;
import co.edu.usbcali.viajesusb.domain.TipoDestino;
import co.edu.usbcali.viajesusb.dto.DestinoDTO;
import co.edu.usbcali.viajesusb.repository.DestinoRepository;
import co.edu.usbcali.viajesusb.service.DestinoService;
import co.edu.usbcali.viajesusb.utils.Constantes;

/**
 * 
 * @ClassName:  DestinoTest   
  * @Description: TODO   
 * @author: Carlos Garaicoa     
 * @date:   7/09/2021 10:01:06 a.�m.      
 * @Copyright:  USB
 */
@SpringBootTest
@Rollback(false)
class DestinoTest {
	
	@Autowired
	//private DestinoRepository destinoRepository;
	private DestinoService destinoService;

	@Test
	@Transactional
	void debeConsutarDestinosPorTipoDestinos() {
		
		List<Destino> lstDestino=null;
		
		try {
			
			lstDestino= destinoService.findByTipoDestino_Codigo("PLAYA");
			
			for(Destino destino : lstDestino) {
				System.out.println(destino.getCodigo()+" - "+destino.getNombre());
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	@Transactional
	void debeConsutarDestinosPorEstadoPaginado() {
		
		Page<Destino> pageDestino=null;
		
		try {
			//El primer numero es el numero de la pagina actual empezando desde 0
			//El segundo numero es la cantidad de item por pagina
			
			Pageable pageable = PageRequest.of(0,3);
			pageDestino= destinoService.findByEstado("A", pageable);
			
			for(Destino destino : pageDestino.getContent()) {
				System.out.println(destino.getCodigo()+" - "+destino.getNombre());
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Test
	@Transactional
	void debeGuardarElDestinoSanAndres() {
		
		try {
			
		
		DestinoDTO destinoDTO = new DestinoDTO();
		
		destinoDTO.setAire(Constantes.SI);
		destinoDTO.setMar(Constantes.SI);
		destinoDTO.setTierra(Constantes.SI);
		
		destinoDTO.setNombre("San andres");
		destinoDTO.setCodigo("SANI");
		destinoDTO.setDescripcion("SAN ANDRES ISLAS");
		destinoDTO.setEstado(Constantes.ACTIVO);
		destinoDTO.setFechaCreacion(new Date());;
		destinoDTO.setUsuCreator("Mario");

		destinoDTO.setCodigoTipoDestino("PLAYA");
		destinoDTO.setNombreTipoDestino("PLAYA Y MAR");
		
		
		destinoService.guardarDestino(destinoDTO);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		
	}
	
	@Test
	@Transactional
	public void actualizarDestino(DestinoDTO destinoDTO2) throws Exception{
		
		try {
			
			
			DestinoDTO destinoDTO = new DestinoDTO();
			
			destinoDTO.setIdDest(26L);
			destinoDTO.setAire(Constantes.SI);
			destinoDTO.setMar(Constantes.SI);
			destinoDTO.setTierra(Constantes.NO);
			
			destinoDTO.setNombre("San andr�s");
			destinoDTO.setCodigo("SAND");
			destinoDTO.setDescripcion("SAN ANDRES ISLAS");
			destinoDTO.setEstado(Constantes.ACTIVO);
			destinoDTO.setFechaCreacion(new Date());;
			destinoDTO.setUsuCreator("Mario");

			destinoDTO.setCodigoTipoDestino("PLAYA");
			destinoDTO.setNombreTipoDestino("PLAYA Y MAR");
			
			
			destinoService.actualizarDestino(destinoDTO2);
			}catch(Exception e) {
				e.printStackTrace();
			}
		
		
		
	}
	
	
	@Test
	@Transactional
	public void debeEliminarEldestinoSanAndres(){
		
		try {
			
			destinoService.eliminarDestino(26L);
			}catch(Exception e) {
				System.out.println(e.getMessage());
			}
	}
	
	
	
	
	
	
	
	
	

}
