/**  
 * @Title:  TipoDestinoDTO.java   
 * @Package co.edu.usbcali.viajesusb.dto   
 * @Description: description   
 * @author: Carlos Garaicoa     
 * @date:   28/09/2021 11:19:48 a. m.   
 * @version V1.0 
 * @Copyright: Universidad San de Buenaventura
 */
package co.edu.usbcali.viajesusb.dto;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

/**   
 * @ClassName:  TipoDestinoDTO   
  * @Description: TODO   
 * @author: Carlos Garaicoa     
 * @date:   28/09/2021 11:19:48 a. m.      
 * @Copyright:  USB
 */
@Data
public class TipoDestinoDTO implements Serializable{

	private static final long serialVersionUID = -2196740837122529314L;

	
	private long idTide;
	private String codigo;
	private String nombre;
	private String descripcion;
	private Date fechaCreacion;
	private Date fechaModificacion;
	private String usuCreator;
	private String usuModificador;
	private String estado;
	
	
	
}
