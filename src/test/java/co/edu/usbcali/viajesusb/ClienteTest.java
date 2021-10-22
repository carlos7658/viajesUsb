/**  
 * @Title:  ClienteTest.java   
 * @Package co.edu.usbcali.viajesusb   
 * @Description: description   
 * @author: Diego Pastrana     
 * @date:   6/09/2021 5:52:38 p. m.   
 * @version V1.0 
 * @Copyright: Universidad San de Buenaventura
 */
package co.edu.usbcali.viajesusb;

import static org.junit.jupiter.api.Assertions.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Rollback;

import co.edu.usbcali.viajesusb.domain.Cliente;
import co.edu.usbcali.viajesusb.dto.ClienteDTO;
import co.edu.usbcali.viajesusb.dto.DestinoDTO;
import co.edu.usbcali.viajesusb.repository.ClienteRepository;
import co.edu.usbcali.viajesusb.service.ClienteService;
import co.edu.usbcali.viajesusb.utils.Constantes;

/**
 * 
 * @ClassName:  ClienteTest   
  * @Description: TODO   
 * @author: Carlos Garaicoa     
 * @date:   7/09/2021 9:59:04 a.�m.      
 * @Copyright:  USB
 */

@SpringBootTest
@Rollback(false)
class ClienteTest {

	@Autowired
	private ClienteService clienteService;
	
	
	@Test
	@Transactional
	void debeConsultarElClientePorEstadoYOrdenarloPorIdentificacionAscendente() {
		
		Page<Cliente> pageCliente=null;
		
		try {
			//El primer numero es el numero de la pagina actual empezando desde 0
			//El segundo numero es la cantidad de item por pagina
			
			Pageable pageable = PageRequest.of(0,2);
			pageCliente= clienteService.findByEstadoOrderByNumeroIdentificacionAsc("A", pageable);
			
			for(Cliente cliente : pageCliente.getContent()) {
				System.out.println(cliente.getNombre()+" - "+cliente.getEstado());
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
		
	@Test
	@Transactional
	void debeConsultarElClientePorCorreoIgnorandoMinusculasYMayusculas() {
			
		Cliente cliente=null;
			
		try {
				
			cliente= clienteService.findByCorreoIgnoreCase("garaicoa7658@gmail.com");
			System.out.println(cliente.getNombre()+" - "+cliente.getCorreo());
				
		}
		catch(NullPointerException e) {
			System.out.println("No hay datos en la base de datos");
		}
		catch(Exception e){
			e.printStackTrace();
		}		
			
	}
		
	@Test
	@Transactional
	void debeConsultarPorTipoDeIdentificacion() {
		
		Cliente cliente=null;
		
		try {
			
			cliente=clienteService.findByNumeroIdentificacionLike("1193127450*");
			System.out.println(cliente.getNombre()+" - "+cliente.getNumeroIdentificacion());
			
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
	void debeConsultarPorNombreIgnorandoMinusculas() {
		
		List<Cliente> lstClientes;
		
		try {
			lstClientes=clienteService.findByNombreIgnoreCaseLike("%Carlos%");
			for(Cliente cliente:lstClientes) {
				System.out.println(cliente.getNombre());
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	@Transactional
	void debeConsultarPorFechaDeNacimientoEntreDosFechas() {
		
		List<Cliente> lstClientes;
		
		try {
			Date fecha1 = new SimpleDateFormat("DD/MM/YYYY").parse("01/01/2000");
			Date fecha2 = new SimpleDateFormat("DD/MM/YYYY").parse("01/30/2002");
			
			lstClientes=clienteService.findByFechaNacimientoBetween(fecha1, fecha2);
			for(Cliente cliente:lstClientes) {
				System.out.println(cliente.getNombre());
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	@Transactional
	void debeConsultarLaCantidadDeClientesPorEstado() {
		
		Integer cantidad ;
		
		try {
			
			cantidad=clienteService.countByEstado("A");
			System.out.println(cantidad);
		}
		catch(NullPointerException e) {
			System.out.println("No hay datos en la base de datos");
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@Test
	@Transactional
	void debeConsultarClientePorTipoIdentificacion() {
		
		Page<Cliente> pageCliente = null;
		
		try {
		
			Pageable pageable = PageRequest.of(0, 3);
			pageCliente = clienteService.findByTipoIdentificacion_Codigo("CC",pageable);
			
			for (Cliente cliente : pageCliente) {
				System.out.println(cliente.getNombre());
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	@Transactional
	void debeConsultarClientePorApellido() {
		
		List<Cliente> ltsCliente;
		
		try {
			ltsCliente = clienteService.findByPrimerApellidoOrSegundoApellido("Ibarra","null");
			for(Cliente cliente:ltsCliente) {
				System.out.println(cliente.getNombre()+ " - " + cliente.getPrimerApellido());
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	@Transactional
	void debeConsultarClientesPorEstadoDeManeraNativa() {
		
		List<ClienteDTO> ltsClienteDTO;
		
		try {
			ltsClienteDTO=clienteService.ConsultarClientesPorEstado("A");
			for(ClienteDTO clienteDTO: ltsClienteDTO) {
				System.out.println(clienteDTO.getIdClie()+" - "+clienteDTO.getNumeroIdentificacion()+" - "+ clienteDTO.getNombre()+" - "+clienteDTO.getPrimerApellido());
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	@Test
	@Transactional
	void debeConsultarClientesPorIdentificacionDeManeraNativa() {
		
		ClienteDTO clienteDTO;
		
		try {
			clienteDTO=clienteService.ConsultarClientesPorNumeroIdentificacion("1193127450");
			System.out.println(clienteDTO.getIdClie()+" - "+clienteDTO.getNumeroIdentificacion()+" - "+ clienteDTO.getNombre());
		}
		catch(NullPointerException e) {
			System.out.println("No hay datos en la base de datos");
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@Test
	@Transactional
	void debeConsultarClientesPorTipoIdentificacion() {
		
		List<ClienteDTO> ltsClienteDTO;
		
		try {
			ltsClienteDTO=clienteService.ConsultarClientesPorTipoIdentificacion("CC");
			for(ClienteDTO clienteDTO: ltsClienteDTO) {
				System.out.println(clienteDTO.getIdClie()+" - "+clienteDTO.getNumeroIdentificacion()+" - "+ clienteDTO.getNombre());
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}

	@Test
	@Transactional
	void debeConsultarClientesPorNombreEnOrdenAscendente() {
		
		List<ClienteDTO> ltsClienteDTO;
		
		try {
			ltsClienteDTO=clienteService.ConsultarClientesPorNombreEnOrdenAscendente("Carlos");
			for(ClienteDTO clienteDTO: ltsClienteDTO) {
				System.out.println(clienteDTO.getIdClie()+" - "+clienteDTO.getNumeroIdentificacion()+" - "+ clienteDTO.getNombre());
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	
	@Test
	@Transactional
	void debeGuardarCliente() {
		
		
		try {
		ClienteDTO clienteDTO =new ClienteDTO();
		
		
		clienteDTO.setCorreo("Prueba22@hotmail.com");
		clienteDTO.setEstado(Constantes.ACTIVO);
		clienteDTO.setFechaCreacion(new Date());
		clienteDTO.setFechaNacimiento(new Date());
		clienteDTO.setNombre("MORITAAA");
		clienteDTO.setNumeroIdentificacion("11312939");
		clienteDTO.setPrimerApellido("CHANCLETAS");
		clienteDTO.setSegundoApellido("MIRANDAA");
		clienteDTO.setSexo(Constantes.MASCULINO);
		clienteDTO.setTelefono1("223122");
		clienteDTO.setTelefono2("32412");
		clienteDTO.setUsuCredor("Carlos");
		clienteDTO.setCodigoTipoIdentificacion("CC");
		
		
		clienteService.debeGuardarCliente(clienteDTO);
		
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	@Test
	@Transactional
	void debeActualizarCliente() {
		
		try {
		ClienteDTO clienteDTO =new ClienteDTO();
		
		
		clienteDTO.setIdClie(8L);
		clienteDTO.setCorreo("Prueba22@hotmail.com");
		clienteDTO.setEstado(Constantes.ACTIVO);
		clienteDTO.setFechaCreacion(new Date());
		clienteDTO.setFechaNacimiento(new Date());
		clienteDTO.setNombre("mororo");
		clienteDTO.setNumeroIdentificacion("1172814589");
		clienteDTO.setPrimerApellido("CHANCLETAS");
		clienteDTO.setSegundoApellido("MIRANDA");
		clienteDTO.setSexo(Constantes.MASCULINO);
		clienteDTO.setTelefono1("223722");
		clienteDTO.setTelefono2("321273");
		clienteDTO.setUsuCredor("Carlos");
		
		
		clienteService.debeActualizarCliente(clienteDTO);
		
		}catch(Exception e) {
			System.out.println(e.getStackTrace());
		}
	
	}
	
	@Test
	@Transactional
	public void debeEliminarElCliente(Long id){
		
		try {
		
			clienteService.eliminarCliente(id);
			}catch(Exception e) {
				System.out.println(e.getMessage());
			}
	}
	

	
}
	

