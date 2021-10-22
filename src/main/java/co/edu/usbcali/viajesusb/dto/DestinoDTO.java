/**  
 * @Title:  DestinoDTO.java   
 * @Package co.edu.usbcali.viajesusb.dto   
 * @Description: description   
 * @author: Carlos Garaicoa     
 * @date:   28/09/2021 11:14:46 a. m.   
 * @version V1.0 
 * @Copyright: Universidad San de Buenaventura
 */
package co.edu.usbcali.viajesusb.dto;

import java.io.Serializable;
import java.util.Date;

import co.edu.usbcali.viajesusb.domain.TipoDestino;
import lombok.Data;

/**   
 * @ClassName:  DestinoDTO   
  * @Description: TODO   
 * @author: Carlos Garaicoa     
 * @date:   28/09/2021 11:14:46 a. m.      
 * @Copyright:  USB
 */
@Data
public class DestinoDTO implements Serializable{

	private static final long serialVersionUID = 1788451459550469775L;
	
	
	private long idDest;
	private String codigo;
	private String nombre;
	private String descripcion;
	private String tierra;
	private String aire;
	private String mar;
	private Date fechaCreacion;
	private Date fechaModificacion;
	private String usuCreator;
	private String usuModificador;
	private String estado;
	
	private Long idTide;
	private String codigoTipoDestino;
	private String nombreTipoDestino;
	
	
}
