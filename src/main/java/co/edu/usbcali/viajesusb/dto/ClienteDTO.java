/**  
 * @Title:  ClienteDTO.java   
 * @Package co.edu.usbcali.viajesusb.dto   
 * @Description: description   
 * @author: Diego Pastrana     
 * @date:   6/09/2021 10:45:54 p.Â m.   
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
 * 
 * @ClassName:  ClienteDTO   
  * @Description: TODO   
 * @author: Carlos Garaicoa     
 * @date:   7/09/2021 9:50:23 a. m.      
 * @Copyright:  USB
 */

@Data
public class ClienteDTO implements Serializable{

	/**   
	   * @Fields serialVersionUID: TODO (what does this variable mean)   
	 */
	private static final long serialVersionUID = 8650084404524240764L;
	
	public long idClie;
	public String numeroIdentificacion;
	public String primerApellido;
	public String segundoApellido;
	public String nombre;
	public String telefono1;
	public String telefono2;
	public String correo;
	public String sexo;
	public Date fechaNacimiento;
	public Date fechaCreacion;
	public Date fechaModificacion;
	public String usuCredor;
	public String usuModificador;
	public String estado;
	public String codigoTipoIdentificacion;
	
	
	/**
	 * 
	 * @Title:  ClienteDTO  
	 * @Author: Carlos Garaicoa
	   * @Description:TODO 
	 * @param:  @param idClie
	 * @param:  @param numeroIdentificacion
	 * @param:  @param nombre
	 * @param:  @param primerApellido  
	 * @throws
	 */
	public ClienteDTO(long idClie, String numeroIdentificacion, String nombre, String primerApellido) {
		super();
		this.idClie = idClie;
		this.numeroIdentificacion = numeroIdentificacion;
		this.nombre = nombre;
		this.primerApellido = primerApellido;
	}

	/**
	 * 
	 * @Title:  ClienteDTO  
	 * @Author: Carlos Garaicoa
	   * @Description:TODO 
	 * @param:  @param idClie
	 * @param:  @param numeroIdentificacion
	 * @param:  @param nombre  
	 * @throws
	 */
	public ClienteDTO(long idClie, String numeroIdentificacion, String nombre) {
		super();
		this.idClie = idClie;
		this.numeroIdentificacion = numeroIdentificacion;
		this.nombre = nombre;
	}
	
	
	public ClienteDTO() {
		
		
	}
	
	
	
}
