/**  
 * @Title:  DestinoService.java   
 * @Package co.edu.usbcali.viajesusb.service   
 * @Description: description   
 * @author: Carlos Garaicoa     
 * @date:   7/09/2021 12:21:51 p.�m.   
 * @version V1.0 
 * @Copyright: Universidad San de Buenaventura
 */
package co.edu.usbcali.viajesusb.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.usbcali.viajesusb.domain.Destino;
import co.edu.usbcali.viajesusb.dto.DestinoDTO;
import co.edu.usbcali.viajesusb.repository.DestinoRepository;

/**   
 * @ClassName:  DestinoService   
  * @Description: TODO   
 * @author: Carlos Garaicoa     
 * @date:   7/09/2021 12:21:51 p.�m.      
 * @Copyright:  USB
 */
public interface DestinoService {

	
	
		public List<Destino> findByTipoDestino_Codigo(String codigoTipoDestino) throws Exception;
		public Page<Destino> findByEstado(String estado, Pageable pageable) throws SQLException;

		public Destino guardarDestino(DestinoDTO destinoDTO) throws Exception;
		public Destino actualizarDestino(DestinoDTO destinoDTO) throws Exception;
		public void eliminarDestino(Long id) throws Exception;
	
}
