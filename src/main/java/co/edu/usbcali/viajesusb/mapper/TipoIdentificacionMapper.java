/**  
 * @Title:  TipoIdentificacionMapper.java   
 * @Package co.edu.usbcali.viajesusb.mapper   
 * @Description: description   
 * @author: Carlos Garaicoa     
 * @date:   18/10/2021 6:48:28 p. m.   
 * @version V1.0 
 * @Copyright: Universidad San de Buenaventura
 */
package co.edu.usbcali.viajesusb.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import co.edu.usbcali.viajesusb.domain.TipoDestino;
import co.edu.usbcali.viajesusb.domain.TipoIdentificacion;
import co.edu.usbcali.viajesusb.dto.TipoDestinoDTO;
import co.edu.usbcali.viajesusb.dto.TipoIdentificacionDTO;

/**   
 * @ClassName:  TipoIdentificacionMapper   
  * @Description: TODO   
 * @author: Carlos Garaicoa     
 * @date:   18/10/2021 6:48:28 p. m.      
 * @Copyright:  USB
 */
@Mapper(componentModel="spring")
public interface TipoIdentificacionMapper {

	
	
	
	public TipoIdentificacionDTO tipoIdentificacionToTipoIdentificacionDTO(TipoIdentificacion tipoDestino);
	public TipoIdentificacion tipoIdentificacionDTOToTipoIdentificacion(TipoIdentificacionDTO tipoDestinoDTO);
	
	public List<TipoIdentificacionDTO> listTipoIdentificacionToListTipoIdentificacionDTO(List<TipoIdentificacion> lsttipoDestino);
	public List<TipoIdentificacionDTO> listTipoIdentificacionDTOToListTipoIdentificacion(List<TipoIdentificacion> lsttipoDestino);
	
}
