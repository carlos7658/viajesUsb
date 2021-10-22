/**  
 * @Title:  TipoIdentificacionRepository.java   
 * @Package co.edu.usbcali.viajesUsb.repository   
 * @Description: description   
 * @author: Diego Pastrana     
 * @date:   6/09/2021 5:19:47 p.Â m.   
 * @version V1.0 
 * @Copyright: Universidad San de Buenaventura
 */
package co.edu.usbcali.viajesusb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.usbcali.viajesusb.domain.TipoIdentificacion;

/**
 * 
 * @ClassName:  TipoIdentificacionRepository   
  * @Description: TODO   
 * @author: Carlos Garaicoa     
 * @date:   7/09/2021 9:57:38 a. m.      
 * @Copyright:  USB
 */
public interface TipoIdentificacionRepository extends JpaRepository<TipoIdentificacion, Long> {

	public List<TipoIdentificacion> findByEstadoOrderByNombreAsc(String estado) throws Exception;
	
	public TipoIdentificacion findByCodigoAndEstado(String codigo, String estado) throws Exception;
}
