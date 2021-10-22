/**  
 * @Title:  TipoDestinoRestController.java   
 * @Package co.edu.usbcali.viajesusb.controller   
 * @Description: description   
 * @author: Carlos Garaicoa     
 * @date:   12/10/2021 10:34:47 a.�m.   
 * @version V1.0 
 * @Copyright: Universidad San de Buenaventura
 */
package co.edu.usbcali.viajesusb.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.edu.usbcali.viajesusb.domain.Destino;
import co.edu.usbcali.viajesusb.domain.TipoDestino;
import co.edu.usbcali.viajesusb.dto.DestinoDTO;
import co.edu.usbcali.viajesusb.dto.TipoDestinoDTO;
import co.edu.usbcali.viajesusb.mapper.TipoDestinoMapper;
import co.edu.usbcali.viajesusb.service.TipoDestinoService;

/**   
 * @ClassName:  TipoDestinoRestController   
  * @Description: TODO   
 * @author: Carlos Garaicoa     
 * @date:   12/10/2021 10:34:47 a.�m.      
 * @Copyright:  USB
 */
@RestController
@RequestMapping("/api/tipoDestino")
public class TipoDestinoRestController {

	@Autowired
	private TipoDestinoService tipoDestinoService;
	
	@Autowired
	private TipoDestinoMapper tipoDestinoMapper;
	
	
	@GetMapping("/getTipoDestinoPorCodigo")
	public ResponseEntity<?> buscarTipoDestinoPorCodigo(@RequestParam("codigo")String codigo){
		
		try {
		
			TipoDestino tipoDestino=tipoDestinoService.findByCodigo(codigo);
			
			return ResponseEntity.ok().body(tipoDestinoMapper.tipoDestinoToTipoDestinoDTO(tipoDestino));
			
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		
		
	}
	
	
	@GetMapping("/getCodigoAndEstado")
	public ResponseEntity<?> consultarCodigoyEstado(@RequestParam("codigo")String codigo,@RequestParam("estado")String estado){
		
		try {
			
			TipoDestino tipoDestino=tipoDestinoService.findByCodigoAndEstado(codigo, estado);
			return ResponseEntity.ok().body(tipoDestinoMapper.tipoDestinoToTipoDestinoDTO(tipoDestino));
			
		} catch (Exception e) {
			
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
			
		}
		
	}
	
	
	@GetMapping("/getTiposDestino")
	public ResponseEntity<?> consultarTiposDestino(@RequestParam("estado")String estado){
	
		try {
			
			List<TipoDestino> ltstipoDestino=tipoDestinoService.findByEstadoOrderByNombreDesc(estado);
			
			return ResponseEntity.ok().body(tipoDestinoMapper.listTipoDestinoToListTipoDestinoDTO(ltstipoDestino));
			
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		
	}
	
	
	
	@PostMapping("/guardarTipoDestino")
	public ResponseEntity<?> guardarTipoDestino(@RequestBody TipoDestinoDTO tipoDestinoDTO){
		
		try {
			
			TipoDestino tipoDestino =tipoDestinoService.debeGuardarTipoDestino(tipoDestinoDTO);
			
			return ResponseEntity.ok(tipoDestinoMapper.tipoDestinoToTipoDestinoDTO(tipoDestino));
			
		} catch (Exception e) {
			
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
			 
		}
		
	}
	
	@PutMapping("/actualizarTipoDestino")
	public ResponseEntity<?> actualizarTipoDestino(@RequestBody TipoDestinoDTO tipoDestinoDTO){
		
		try {
			
			TipoDestino tipoDestino =tipoDestinoService.debeActualizarTipoDestino(tipoDestinoDTO);
			
			return ResponseEntity.ok(tipoDestinoMapper.tipoDestinoToTipoDestinoDTO(tipoDestino));
			
		} catch (Exception e) {
			
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
			 
		}
		
	}
	
	@DeleteMapping("/eliminarTipoDestino/{idTide}")
	public ResponseEntity<?> eliminarTipoDestino(@PathVariable("idTide")Long id){
		
		try {
			
			tipoDestinoService.eliminarTipoDestino(id);
			
			return ResponseEntity.ok("Se eliminó satisfcatoriamente");
			
			} catch (Exception e) {
				//return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		
		}
		
		
	}
	
	
	
	
}

