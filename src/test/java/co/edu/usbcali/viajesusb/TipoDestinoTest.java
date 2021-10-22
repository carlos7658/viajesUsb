/**  
 * @Title:  TipoDestinoTest.java   
 * @Package co.edu.usbcali.viajesUsb   
 * @Description: description   
 * @author: Diego Pastrana     
 * @date:   31/08/2021 2:32:13 p. m.   
 * @version V1.0 
 * @Copyright: Universidad San de Buenaventura
 */
package co.edu.usbcali.viajesusb;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import co.edu.usbcali.viajesusb.domain.TipoDestino;
import co.edu.usbcali.viajesusb.dto.DestinoDTO;
import co.edu.usbcali.viajesusb.dto.TipoDestinoDTO;
import co.edu.usbcali.viajesusb.repository.TipoDestinoRepository;
import co.edu.usbcali.viajesusb.service.TipoDestinoService;
import co.edu.usbcali.viajesusb.utils.Constantes;

/**
 * 
 * @ClassName:  TipoDestinoTest   
  * @Description: TODO   
 * @author: Carlos Garaicoa     
 * @date:   7/09/2021 10:02:06 a.�m.      
 * @Copyright:  USB
 */

@SpringBootTest
@Rollback(false)
public class TipoDestinoTest {
	
	@Autowired
	private TipoDestinoService tipoDestinoRepository=null;
	
	
	
	@Test
	@Transactional
	void debeBuscarTipoDestinoPorCodigo() {
		
		TipoDestino tipoDestino=null;
		
		try {
			
			tipoDestino=tipoDestinoRepository.findByCodigo("PLAYA");
			System.out.println(tipoDestino.getCodigo()+"-"+tipoDestino.getNombre());
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
	void debeBuscarPorCodigoYEstado() {
		
		TipoDestino tipoDestino=null;
		
		try {
			
			tipoDestino=tipoDestinoRepository.findByCodigoAndEstado("DESIE","A");
			System.out.println(tipoDestino.codigo+" - "+tipoDestino.nombre);
			
		}catch(NullPointerException e) {
			System.out.println("No hay datos en la base de datos");
		}
		catch(Exception e) {
			
			e.printStackTrace();
			
		}
		
	}
	
	@Test
	@Transactional
	void debeBuscarPorEstadoYOrdenarlo(){
		
		List<TipoDestino> lstTipoDestino=null;
		
		try {
			
			lstTipoDestino=tipoDestinoRepository.findByEstadoOrderByNombreDesc("A");
			for(TipoDestino tipoDestino: lstTipoDestino){
				System.out.println(tipoDestino.getNombre());
			}
		}
		
		catch(Exception e) {
			
			e.printStackTrace();
			
		}
		
	}
	
	
	@Test
	@Transactional
	void debeGuardarTipoDestino() {
		try {
			TipoDestinoDTO tipoDestinoDTO = new TipoDestinoDTO();
			tipoDestinoDTO.setCodigo("PARAM");
			tipoDestinoDTO.setDescripcion("Ambiente de p�ramo");
			tipoDestinoDTO.setEstado(Constantes.ACTIVO);
			tipoDestinoDTO.setFechaCreacion(new Date());
			tipoDestinoDTO.setNombre("paramo de miraflores");
			tipoDestinoDTO.setUsuCreator("Diego");
			
			tipoDestinoRepository.debeGuardarTipoDestino(tipoDestinoDTO);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	
	
	@Test
	@Transactional
	void debeActualizarTipoDestino() {
		
		try {
		TipoDestinoDTO tipoDestinoDTO=new TipoDestinoDTO();
		
		tipoDestinoDTO.setIdTide(1L);
		tipoDestinoDTO.setCodigo("PRIM");
		tipoDestinoDTO.setDescripcion("Ambiente de paramo");
		tipoDestinoDTO.setEstado(Constantes.ACTIVO);
		tipoDestinoDTO.setFechaCreacion(new Date());
		tipoDestinoDTO.setNombre("ParamoOOO de miraflores");
		tipoDestinoDTO.setUsuCreator("Carlos");
		
			
			tipoDestinoRepository.debeActualizarTipoDestino(tipoDestinoDTO);
		}catch(Exception e) {
			e.printStackTrace();;
		}
		
		
	}

	
	@Test
	@Transactional
	public void debeEliminarElTipoDestino(){
		
		try {
			
			
			tipoDestinoRepository.eliminarTipoDestino(1L);
			}catch(Exception e) {
				System.out.println(e.getMessage());
			}
		
	}
	
	

}
