/**  
 * @Title:  ClienteService.java   
 * @Package co.edu.usbcali.viajesusb.service   
 * @Description: description   
 * @author: Carlos Garaicoa     
 * @date:   14/09/2021 4:28:49 p.�m.   
 * @version V1.0 
 * @Copyright: Universidad San de Buenaventura
 */
package co.edu.usbcali.viajesusb.service;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import co.edu.usbcali.viajesusb.domain.Cliente;
import co.edu.usbcali.viajesusb.dto.ClienteDTO;

/**   
 * @ClassName:  ClienteService   
  * @Description: TODO   
 * @author: Carlos Garaicoa     
 * @date:   14/09/2021 4:28:49 p.�m.      
 * @Copyright:  USB
 */
public interface ClienteService {

	
	public Page<Cliente> findByEstadoOrderByNumeroIdentificacionAsc(String estado, Pageable pageable) throws SQLException;
	
	
	public Cliente findByCorreoIgnoreCase(String correo) throws SQLException;
	
	
	public Cliente findByNumeroIdentificacionLike(String identificacion) throws SQLException;
	
	
	public List<Cliente> findByNombreIgnoreCaseLike(String nombre) throws SQLException; 
	

	public List<Cliente> findByFechaNacimientoBetween(Date start , Date finish)throws Exception;

	
	public Integer countByEstado(String estado) throws SQLException;
	

	public Page<Cliente> findByTipoIdentificacion_Codigo(String codigoTipoIdentificacion,Pageable pageable) throws Exception;
	

	public List<Cliente> findByPrimerApellidoOrSegundoApellido(String primerApellido, String segundoApellido) throws Exception;
	
	
	public List<ClienteDTO> ConsultarClientesPorEstado(@Param("pEstado")String estado) throws SQLException;
	
	
	public ClienteDTO ConsultarClientesPorNumeroIdentificacion(@Param("pIdentificacion")String numeroIdentificacion) throws SQLException;
	
	
	public List<ClienteDTO> ConsultarClientesPorTipoIdentificacion(@Param("pTipoIdentificacion")String tipoIdentificacion) throws SQLException;
	
	
	public List<ClienteDTO> ConsultarClientesPorNombreEnOrdenAscendente(@Param("pNombre")String nombre) throws SQLException;
	
	public Cliente debeGuardarCliente(ClienteDTO clienteDTO)throws Exception;
	
	public Cliente debeActualizarCliente(ClienteDTO clienteDTO)throws Exception;
	
	public void eliminarCliente(Long idClie) throws Exception;
	
}
