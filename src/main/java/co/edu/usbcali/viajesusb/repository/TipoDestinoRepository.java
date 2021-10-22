package co.edu.usbcali.viajesusb.repository;

import java.sql.SQLException;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.usbcali.viajesusb.domain.TipoDestino;

/**
 * 
 * @ClassName:  TipoDestinoRepository   
  * @Description: TODO   
 * @author: Carlos Garaicoa     
 * @date:   7/09/2021 9:56:42 a. m.      
 * @Copyright:  USB
 */

public interface TipoDestinoRepository extends JpaRepository <TipoDestino, Long> {

	/**
	 * 
	 * @Title: findByCodigo   
	 * @Description: TODO 
	 * @param: @param codigo
	 * @param: @return      
	 * @return: TipoDestino      
	 * @throws
	 */
	public TipoDestino findByCodigo(String codigo) throws SQLException;
	
	/**
	 * 
	 * @Title: findByCodigoAndEstado   
	   * @Description: TODO 
	 * @param: @param codigo
	 * @param: @param estado
	 * @param: @return
	 * @param: @throws SQLException      
	 * @return: TipoDestino      
	 * @throws
	 */
	public TipoDestino findByCodigoAndEstado(String codigo, String estado) throws SQLException;
	
	/**
	 * 
	 * @Title: findByEstado   
	   * @Description: TODO 
	 * @param: @param estado
	 * @param: @return
	 * @param: @throws SQLException      
	 * @return: List<TipoDestino>      
	 * @throws
	 */
	public List<TipoDestino> findByEstadoOrderByNombreDesc(String estado) throws SQLException;
	
}
