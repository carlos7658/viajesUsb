/**  
 * @Title:  TipoIdentificacionTest.java   
 * @Package co.edu.usbcali.viajesusb   
 * @Description: description   
 * @author: Diego Pastrana     
 * @date:   6/09/2021 9:36:16 p. m.   
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
import org.springframework.test.annotation.Rollback;

import co.edu.usbcali.viajesusb.domain.TipoIdentificacion;
import co.edu.usbcali.viajesusb.dto.TipoDestinoDTO;
import co.edu.usbcali.viajesusb.dto.TipoIdentificacionDTO;
import co.edu.usbcali.viajesusb.repository.TipoIdentificacionRepository;
import co.edu.usbcali.viajesusb.service.TipoIdentificacionService;
import co.edu.usbcali.viajesusb.utils.Constantes;

/**
 * 
 * @ClassName:  TipoIdentificacionTest   
  * @Description: TODO   
 * @author: Carlos Garaicoa     
 * @date:   7/09/2021 10:02:58 a.�m.      
 * @Copyright:  USB
 */


@SpringBootTest
@Rollback(false)
class TipoIdentificacionTest {

	@Autowired
	private TipoIdentificacionService tipoIdentificacionService;
	
    
	@Test
	@Transactional
	void debeConsultarTipoIdentificacionPorEstadoOrdenadoAlfabeticamente() {
		
		List<TipoIdentificacion> lstTipoIdentificacion;
		
		try {
			lstTipoIdentificacion=tipoIdentificacionService.findByEstadoOrderByNombreAsc("A");
			for(TipoIdentificacion tipoIdentificacion:lstTipoIdentificacion) {
				System.out.println(tipoIdentificacion.getCodigo());
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
    
	
    

	@Test
	@Transactional
	void debeConsultarTipoIdentificacionPorCodigoYEstado() {
		
		TipoIdentificacion tipoIdentificacion;
		
		try {
			tipoIdentificacion=tipoIdentificacionService.findByCodigoAndEstado("CC", "A");
			System.out.println(tipoIdentificacion.getCodigo());

		}
		catch(NullPointerException e) {
			System.out.println("No hay datos en la base de datos");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	@Test
	@Transactional
	void debeGuardarTipoIdentificacion() {
		
		try {
		TipoIdentificacionDTO tipoIdentificacion=new TipoIdentificacionDTO();
		
		tipoIdentificacion.setCodigo("PC");
		tipoIdentificacion.setEstado(Constantes.ACTIVO);
		tipoIdentificacion.setFechaCreacion(new Date());
		tipoIdentificacion.setNombre("CEDULA PERRUNA");
		tipoIdentificacion.setUsuCreador("Carlos");
		
		tipoIdentificacionService.debeGuardarTipoIdentificacion(tipoIdentificacion);
		
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	@Transactional
	void debeActualizarTipoIdentificacion() {
		
		try {
		TipoIdentificacionDTO tipoIdentificacion=new TipoIdentificacionDTO();
		
		tipoIdentificacion.setIdTiId(6);
		tipoIdentificacion.setCodigo("PC");
		tipoIdentificacion.setEstado(Constantes.ACTIVO);
		tipoIdentificacion.setFechaCreacion(new Date());
		tipoIdentificacion.setNombre("CEDULA PERRUNAAAA");
		tipoIdentificacion.setUsuCreador("CARLOS");
		
		tipoIdentificacionService.actualizarTipoIdentificacion(tipoIdentificacion);
		
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	@Test
	@Transactional
	public void debeEliminarElTipoIdentificacion(){
	
			
			try {
			
				tipoIdentificacionService.eliminarTipoIdentificacion(5L);
			
			} catch(Exception e) {
				e.printStackTrace();
			}
		}


}
