package co.edu.usbcali.viajesusb.service;

import java.sql.SQLException;
import java.util.List;

import co.edu.usbcali.viajesusb.domain.TipoDestino;
import co.edu.usbcali.viajesusb.dto.TipoDestinoDTO;

/**
 * 
 * @ClassName:  TipoDestinoService   
  * @Description: TODO   
 * @author: Carlos Garaicoa     
 * @date:   7/09/2021 11:51:09 a.�m.      
 * @Copyright:  USB
 */

public interface TipoDestinoService {

	
	/**
	 * 
	 * @Title: findByCodigo   
	   * @Description: Consultar un tipo de destino por codigo 
	 * @param: @param codigo
	 * @param: @return
	 * @param: @throws Exception      
	 * @return: TipoDestino      
	 * @throws
	 */
	public TipoDestino findByCodigo(String codigo) throws Exception;
	
	
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
	 * @Title: findByEstadoOrderByNombreDesc   
	   * @Description: 
	 * @param: @param estado
	 * @param: @return
	 * @param: @throws SQLException      
	 * @return: List<TipoDestino>      
	 * @throws
	 */
	public List<TipoDestino> findByEstadoOrderByNombreDesc(String estado) throws SQLException;
	
	
	
	public TipoDestino debeGuardarTipoDestino(TipoDestinoDTO tipoDestinoDTO) throws Exception;
	
	public TipoDestino debeActualizarTipoDestino(TipoDestinoDTO tipoDestinoDTO)throws Exception;
	
	public void eliminarTipoDestino(Long id) throws Exception;
	
	
}
