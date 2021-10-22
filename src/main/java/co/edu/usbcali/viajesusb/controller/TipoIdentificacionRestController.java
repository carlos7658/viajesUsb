/**  
 * @Title:  TipoIdentificacionRestController.java   
 * @Package co.edu.usbcali.viajesusb.controller   
 * @Description: description   
 * @author: Carlos Garaicoa     
 * @date:   18/10/2021 6:48:08 p. m.   
 * @version V1.0 
 * @Copyright: Universidad San de Buenaventura
 */
package co.edu.usbcali.viajesusb.controller;

import java.util.List;

import org.mapstruct.Mapper;
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

import co.edu.usbcali.viajesusb.domain.TipoDestino;
import co.edu.usbcali.viajesusb.domain.TipoIdentificacion;
import co.edu.usbcali.viajesusb.dto.TipoDestinoDTO;
import co.edu.usbcali.viajesusb.dto.TipoIdentificacionDTO;
import co.edu.usbcali.viajesusb.mapper.ClienteMapper;
import co.edu.usbcali.viajesusb.mapper.TipoIdentificacionMapper;
import co.edu.usbcali.viajesusb.service.ClienteService;
import co.edu.usbcali.viajesusb.service.TipoIdentificacionService;

/**   
 * @ClassName:  TipoIdentificacionRestController   
  * @Description: TODO   
 * @author: Carlos Garaicoa     
 * @date:   18/10/2021 6:48:08 p. m.      
 * @Copyright:  USB
 */
@RestController
@RequestMapping("/api/tipoIdentificacion")
public class TipoIdentificacionRestController {

	
	@Autowired
	public TipoIdentificacionMapper tipoIdentificacionMapper;
	
	@Autowired
	public TipoIdentificacionService tipoIdentificacionService;
	
	
	@GetMapping("/getEstado")
	public ResponseEntity<?> consultarEstadoTipoIdentificacion(@RequestParam("estado")String estado){
	
		try {
			
			List<TipoIdentificacion> ltstipoDestino=tipoIdentificacionService.findByEstadoOrderByNombreAsc(estado);
			
			return ResponseEntity.ok().body(tipoIdentificacionMapper.listTipoIdentificacionToListTipoIdentificacionDTO(ltstipoDestino));
			
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
		
	}
	
	
	
	@GetMapping("/getCodigoAndEstado")
	public ResponseEntity<?> consultarCodigoyEstado(@RequestParam("codigo")String codigo,@RequestParam("estado")String estado){
		
		try {
			
			TipoIdentificacion tipoIdentificacion=tipoIdentificacionService.findByCodigoAndEstado(codigo, estado);
			return ResponseEntity.ok().body(tipoIdentificacionMapper.tipoIdentificacionToTipoIdentificacionDTO(tipoIdentificacion));
			
		} catch (Exception e) {
			
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
			
		}
		
	}

	
	
	//guardar
	@PostMapping("/guardarTipoIdentificacion")
	public ResponseEntity<?> guardarTipoDestino(@RequestBody TipoIdentificacionDTO tipoIdentificacioon){
		
		try {
			
			TipoIdentificacion tipoIdentificacion =tipoIdentificacionService.debeGuardarTipoIdentificacion(tipoIdentificacioon);
			
			return ResponseEntity.ok(tipoIdentificacionMapper.tipoIdentificacionToTipoIdentificacionDTO(tipoIdentificacion));
			
		} catch (Exception e) {
			
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
			 
		}
		
	}
	
	
	//actualizar
		@PutMapping("/actualizarTipoIdentificacion")
		public ResponseEntity<?> actializarTipoIdentificacion(@RequestBody TipoIdentificacionDTO tipoIdentificacioon){
			
			try {
				
				TipoIdentificacion tipoIdentificacion =tipoIdentificacionService.actualizarTipoIdentificacion(tipoIdentificacioon);
				
				return ResponseEntity.ok(tipoIdentificacionMapper.tipoIdentificacionToTipoIdentificacionDTO(tipoIdentificacion));
				
			} catch (Exception e) {
				
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
				 
			}
			
		}
		
		//borraaarrr
		@DeleteMapping("/eliminarTipoIdentificacion/{idTiId}")
		public ResponseEntity<?> eliminarTipoDestino(@PathVariable("idTiId")Long id){
			
			try {
				
				tipoIdentificacionService.eliminarTipoIdentificacion(id);
				
				return ResponseEntity.ok("Se eliminó satisfcatoriamente");
				
				} catch (Exception e) {
					//return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
					return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
			
			}
			
			
		}
		
	
	
}
