/**  
 * @Title:  ClienteRepository.java   
 * @Package co.edu.usbcali.viajesUsb.repository   
 * @Description: description   
 * @author: Diego Pastrana     
 * @date:   6/09/2021 5:19:28 p. m.   
 * @version V1.0 
 * @Copyright: Universidad San de Buenaventura
 */
package co.edu.usbcali.viajesusb.repository;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import co.edu.usbcali.viajesusb.domain.Cliente;
import co.edu.usbcali.viajesusb.dto.ClienteDTO;



public interface ClienteRepository extends JpaRepository <Cliente, Long> {

	/**
	 * 
	 * @Title: findByEstadoOrderByIdentificacionAsc   
	 * @Description: Consultar todos los clientes por estado. Esta consulta debe ser paginada y ordenada de forma ascendente por numero de identificación
	 * @param: @param estado
	 * @param: @param pageable
	 * @param: @return
	 * @param: @throws Exception      
	 * @return: Page<Cliente>      
	 * @throws
	 */
	public Page<Cliente> findByEstadoOrderByNumeroIdentificacionAsc(String estado, Pageable pageable) throws SQLException;
	
	/**
	 * 
	 * @Title: findByCorreoIgnoreCase   
	 * @Description: Consultar un cliente por correo electronico. Esta consulta debe ignorar las mayusculas y minúsculas.
	 * @param: @param correo
	 * @param: @return
	 * @param: @throws SQLException      
	 * @return: Cliente      
	 * @throws
	 */
	public Cliente findByCorreoIgnoreCase(String correo) throws SQLException;
	
	/**
	 * 
	 * @Title: findByNumeroIdentificacionLike   
	 * @Description: Consultar cliente por numero de identificacion, usando LIKE
	 * @param: @param identificacion
	 * @param: @return
	 * @param: @throws SQLException      
	 * @return: Cliente      
	 * @throws
	 */
	public Cliente findByNumeroIdentificacionLike(String identificacion) throws SQLException;
	
	/**
	 * 
	 * @Title: findByNombreLike   
	 * @Description: Consultar cliente por nombre, ignorando Mayusculas y minusculas, usando LIKE. 
	 * @param: @param nombre
	 * @param: @return
	 * @param: @throws SQLException      
	 * @return: List<Cliente>      
	 * @throws
	 */
	public List<Cliente> findByNombreIgnoreCaseLike(String nombre) throws SQLException; 
	
	/**
	 * 
	 * @Title: findByFechaNacimientoBetween   
	 * @Description: Consultar clientes por rango de fecha (Se debe pasar dos fechas y traer los clientes cuya fecha de nacimiento se encuentre entre el rango ingresado).
	 * @param: @param start
	 * @param: @param finish
	 * @param: @return      
	 * @return: List<Cliente>      
	 * @throws
	 */
	public List<Cliente> findByFechaNacimientoBetween(Date start , Date finish)throws SQLException;
	
	/**
	 * 
	 * @Title: countByEstado   
	 * @Description: Consultar total de clientes por estado.
	 * @param: @param estado
	 * @param: @return
	 * @param: @throws SQLException      
	 * @return: Integer      
	 * @throws
	 */
	public Integer countByEstado(String estado) throws SQLException;
	
	/**
	 * 
	 * @Title: findByTipoIdentificacion_Codigo   
	 * @Description: Consultar clientes por tipo de identificacion. Debe de ser paginado
	 * @param: @param codigoTipoIdentificacion
	 * @param: @param pageable
	 * @param: @return
	 * @param: @throws Exception      
	 * @return: Page<Cliente>      
	 * @throws
	 */
	public Page<Cliente> findByTipoIdentificacion_Codigo(String codigoTipoIdentificacion,Pageable pageable) throws Exception;
	
	/**
	 * 
	 * @Title: findByPrimerApellidoOrSegundoApellido   
	 * @Description: Consultar clientes por apellido (tener en cuenta los campos primer y segundo apellido) 
	 * @param: @param primerApellido
	 * @param: @param segundoApellido
	 * @param: @return
	 * @param: @throws Exception      
	 * @return: List<Cliente>      
	 * @throws
	 */
	public List<Cliente> findByPrimerApellidoOrSegundoApellido(String primerApellido, String segundoApellido) throws Exception;
	
	@Query(nativeQuery=true)
	public List<ClienteDTO> ConsultarClientesPorEstado(@Param("pEstado")String estado);
	
	@Query(nativeQuery=true)
	public ClienteDTO ConsultarClientesPorNumeroIdentificacion(@Param("pIdentificacion")String numeroIdentificacion);
	
	@Query(nativeQuery=true)
	public List<ClienteDTO> ConsultarClientesPorTipoIdentificacion(@Param("pTipoIdentificacion")String tipoIdentificacion);
	
	@Query(nativeQuery=true)
	public List<ClienteDTO> ConsultarClientesPorNombreEnOrdenAscendente(@Param("pNombre")String nombre);
}
