/**  
 * @Title:  ClienteRestController.java   
 * @Package co.edu.usbcali.viajesusb.controller   
 * @Description: description   
 * @author: Carlos Garaicoa     
 * @date:   18/10/2021 3:45:54 p. m.   
 * @version V1.0 
 * @Copyright: Universidad San de Buenaventura
 */
package co.edu.usbcali.viajesusb.controller;


import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

import co.edu.usbcali.viajesusb.domain.Cliente;
import co.edu.usbcali.viajesusb.domain.TipoDestino;
import co.edu.usbcali.viajesusb.dto.ClienteDTO;
import co.edu.usbcali.viajesusb.dto.TipoDestinoDTO;
import co.edu.usbcali.viajesusb.mapper.ClienteMapper;
import co.edu.usbcali.viajesusb.service.ClienteService;
import co.edu.usbcali.viajesusb.service.TipoDestinoService;

/**   
 * @ClassName:  ClienteRestController   
  * @Description: TODO   
 * @author: Carlos Garaicoa     
 * @date:   18/10/2021 3:45:54 p. m.      
 * @Copyright:  USB
 */
@RestController
@RequestMapping("/api/cliente")
public class ClienteRestController {
	
	@Autowired
	public ClienteService clienteService;
	
	@Autowired
	public ClienteMapper clienteMapper;
	
	
	/*
	@GetMapping("/getEstadoAscendente")
	public ResponseEntity<?> buscarEstadoAscendente(@RequestParam("estado")String estado,@RequestParam("page") Pageable page){
		
		try {
		
			Page<Cliente> pageCliente=clienteService.findByEstadoOrderByNumeroIdentificacionAsc(estado, page);
			
			return ResponseEntity.ok().body(clienteMapper.pageClienteToPageClienteDTO(pageCliente));
			
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		
		
	}
	*/
	
	@GetMapping("/getPorCorreo")
	public ResponseEntity<?> buscarClientePorCorreo(@RequestParam("correo")String correo){
		
		try {
		
			Cliente Cliente=clienteService.findByCorreoIgnoreCase(correo);
			System.out.println(correo);
			return ResponseEntity.ok().body(clienteMapper.clienteToClienteDTO(Cliente));
			
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
		
	}
	
	
	//numero identificacion
	
	@GetMapping("/getClientePorIdentificacion")
	public ResponseEntity<?> consultarClientePorIdentificacion(@RequestParam("identificacion")String identificacion){
	
		try {
			
			Cliente cliente=clienteService.findByNumeroIdentificacionLike(identificacion);
			
			return ResponseEntity.ok().body(clienteMapper.clienteToClienteDTO(cliente));
			
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
		
	}


	//getClientePorNombre
	@GetMapping("/getNombreCliente")
	public ResponseEntity<?> buscarClientePorNombre(@RequestParam("nombre")String nombre){
		
		try {
		
			List<Cliente> lstCliente=clienteService.findByNombreIgnoreCaseLike(nombre);
			return ResponseEntity.ok().body(clienteMapper.listClienteToListClienteDTO(lstCliente));
			
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
		
	}
	
	
	//getClientePorApellidos
	@GetMapping("/getClientePorApellidos")
	public ResponseEntity<?> buscarClientePorApellidos(@RequestParam("primerApellido")String primerApellido,@RequestParam("segundoApellido") String segundoApellido){
		
		try {
		
			List<Cliente> lstCliente=clienteService.findByPrimerApellidoOrSegundoApellido(primerApellido,segundoApellido);
			return ResponseEntity.ok().body(clienteMapper.listClienteToListClienteDTO(lstCliente));
			
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
		
	}
	
	
	
	//consultar por estado
	@GetMapping("/getClientePorEstado")
	public ResponseEntity<?> consultarClientePorEstado(@RequestParam("estado")String estado){
	
		try {
			
			List<ClienteDTO> lstcliente=clienteService.ConsultarClientesPorEstado(estado);
			
			return ResponseEntity.ok().body(clienteMapper.listClienteDTOToListCliente(lstcliente));
			
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
		
	}

	
	
	
	//Guardaaaaaaaaaaaar
	@PostMapping("/guardarCliente")
	public ResponseEntity<?> guardarCliente(@RequestBody ClienteDTO clienteDTO){
		
		try {
			
			Cliente cliente=clienteService.debeGuardarCliente(clienteDTO);
			
			return ResponseEntity.ok(clienteMapper.clienteToClienteDTO(cliente));
			
		} catch (Exception e) {
			
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
			 
		}
	}
	
	
	//actualizaar
	@PutMapping("/actualizarCliente")
	public ResponseEntity<?> actualizarCliente(@RequestBody ClienteDTO clienteDTO){
		
		try {
			
			Cliente cliente =clienteService.debeActualizarCliente(clienteDTO);
			
			return ResponseEntity.ok(clienteMapper.clienteToClienteDTO(cliente));
			
		} catch (Exception e) {
			
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
		
	}
	
	//borraaar
	@DeleteMapping("/eliminarCliente/{idClie}")
	public ResponseEntity<?> eliminarCliente(@PathVariable("idClie")Long idClie){
		
		try {
			
			clienteService.eliminarCliente(idClie);
			
			return ResponseEntity.ok("Se elimino satisfcatoriamente");
			
			} catch (Exception e) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
		
		
	}

	@GetMapping("/countClientePorEstado")
	public ResponseEntity<?> getClienteCountByEstado(@RequestParam("estado")String estado){
		
		try {
			
			int total=clienteService.countByEstado(estado);
			return ResponseEntity.ok(total);
			
		} catch (Exception e) {
		
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
			
		}
		
		
	}
	
	@GetMapping("/getClienteByFechaNacimientoBetween")
	public ResponseEntity<?> findByFechaNacimientoBetween (@RequestParam("fechaInicial") Date fechaInicial, @RequestParam("fechaFinal") Date fechaFinal ){
		
		try {
			List<Cliente> clientes= clienteService.findByFechaNacimientoBetween(fechaInicial, fechaFinal);
			return ResponseEntity.ok(clienteMapper.listClienteToListClienteDTO(clientes));
		}
		catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
		
	}
	
	

}
