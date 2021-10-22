/**  
 * @Title:  TipoIdentificacionService.java   
 * @Package co.edu.usbcali.viajesusb.service   
 * @Description: description   
 * @author: Carlos Garaicoa     
 * @date:   14/09/2021 5:11:58 p.�m.   
 * @version V1.0 
 * @Copyright: Universidad San de Buenaventura
 */
package co.edu.usbcali.viajesusb.service;

import java.util.List;

import co.edu.usbcali.viajesusb.domain.TipoIdentificacion;
import co.edu.usbcali.viajesusb.dto.TipoIdentificacionDTO;

/**   
 * @ClassName:  TipoIdentificacionService   
  * @Description: TODO   
 * @author: Carlos Garaicoa     
 * @date:   14/09/2021 5:11:58 p.�m.      
 * @Copyright:  USB
 */
public interface TipoIdentificacionService {
	
	public List<TipoIdentificacion> findByEstadoOrderByNombreAsc(String estado) throws Exception;
	
	public TipoIdentificacion findByCodigoAndEstado(String codigo, String estado) throws Exception;

	public TipoIdentificacion debeGuardarTipoIdentificacion(TipoIdentificacionDTO tipoIdentificacionDTO)throws Exception;
	
	public TipoIdentificacion actualizarTipoIdentificacion(TipoIdentificacionDTO tipoIdentificacionDTO) throws Exception;
	
	public void eliminarTipoIdentificacion(Long idTiId) throws Exception;
	
}
