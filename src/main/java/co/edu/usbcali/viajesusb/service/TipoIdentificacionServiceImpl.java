/**  
 * @Title:  TipoIdentificacionServiceImpl.java   
 * @Package co.edu.usbcali.viajesusb.service   
 * @Description: description   
 * @author: Carlos Garaicoa     
 * @date:   14/09/2021 5:12:26 p.�m.   
 * @version V1.0 
 * @Copyright: Universidad San de Buenaventura
 */
package co.edu.usbcali.viajesusb.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import co.edu.usbcali.viajesusb.domain.Destino;
import co.edu.usbcali.viajesusb.domain.TipoDestino;
import co.edu.usbcali.viajesusb.domain.TipoIdentificacion;
import co.edu.usbcali.viajesusb.dto.ClienteDTO;
import co.edu.usbcali.viajesusb.dto.DestinoDTO;
import co.edu.usbcali.viajesusb.dto.TipoDestinoDTO;
import co.edu.usbcali.viajesusb.dto.TipoIdentificacionDTO;
import co.edu.usbcali.viajesusb.repository.TipoIdentificacionRepository;
import co.edu.usbcali.viajesusb.utils.Utilities;


@Scope("singleton")
@Service

public class TipoIdentificacionServiceImpl implements TipoIdentificacionService{

	
	@Autowired
	private TipoIdentificacionRepository tipoIdentificacionRepository;
	
	@Autowired
	private ClienteService clienteService;
	
	@Override
	public List<TipoIdentificacion> findByEstadoOrderByNombreAsc(String estado) throws Exception {
		
		if (Utilities.isGreater(estado, 1)) {
			 throw new SQLException("Solo puede ingresar una letra"); 
		 }
		 
		 if(estado==null || Utilities.isEmpty(estado)) {
			 throw new SQLException("El estado de tipo destino es obligatorio");
			 
		 }
		 if(!Utilities.isOnlyletras(estado)) {
			 throw new SQLException("El estado solo puede contener letras y sin espacios y mayusculas");
		 }
		
		List<TipoIdentificacion> lstTipoIdentificacion;
		
		lstTipoIdentificacion=tipoIdentificacionRepository.findByEstadoOrderByNombreAsc(estado);
		
		return lstTipoIdentificacion;
	}

	@Override
	public TipoIdentificacion findByCodigoAndEstado(String codigo, String estado) throws Exception {
		
		
		if (Utilities.isGreater(estado, 1)) {
			 throw new SQLException("Solo puede ingresar una letra"); 
		 }
		
		 if (Utilities.isGreater(codigo, 15)) {
			 throw new SQLException("Solo puede contener 15 caracteres"); 
		 }
		 
		 if(estado==null || Utilities.isEmpty(estado)) {
			 throw new SQLException("El estado de tipo destino es obligatorio");
			 
		 }
		 if(!Utilities.isOnlyletras(estado)) {
			 throw new SQLException("El estado solo puede contener letras y sin espacios y mayusculas");
		 }
		 
		 
		 if(codigo==null || Utilities.isEmpty(codigo)) {
			 throw new SQLException("El codigo es obligatorio");
			 
		 }
		 
		 if(!Utilities.isOnlyletras(codigo)) {
			 throw new SQLException("El codigo solo puede contener letras, sin espacios, y sin caracteres especiales");
		 }
		 	
		TipoIdentificacion tipoIdentificacion;
		
		tipoIdentificacion=tipoIdentificacionRepository.findByCodigoAndEstado(codigo, estado);
		
		return tipoIdentificacion;
	}
	

	
	
	public TipoIdentificacion debeGuardarTipoIdentificacion(TipoIdentificacionDTO tipoIdentificacionDTO) throws Exception{
		
		TipoIdentificacion tipoIdentificacion=new TipoIdentificacion();
		
		// VALIDACION ESTADO
		if (tipoIdentificacionDTO.getEstado() == null || Utilities.isEmpty(tipoIdentificacionDTO.getEstado())) {
			throw new Exception("El estado es obligatorio");
		}
		if (Utilities.isGreater(tipoIdentificacionDTO.getEstado(), 1)) {
			throw new Exception("El estado solo puede contener 1 caracter");
		}
		if (!Utilities.isOnlyLetters(tipoIdentificacionDTO.getEstado())) {
			throw new Exception("El estado solo puede contener letras");
		}
		tipoIdentificacion.setEstado(tipoIdentificacionDTO.getEstado());
		
		
		// VALIDACION CODIGO
		if (tipoIdentificacionDTO.getCodigo() == null || Utilities.isEmpty(tipoIdentificacionDTO.getCodigo())) {
			throw new Exception("El codigo es obligatorio");
		}
		if (!Utilities.isOnlyLetters(tipoIdentificacionDTO.getCodigo())) {
			throw new Exception("El codigo solo puede contener letras");
		}
		if (Utilities.isGreater(tipoIdentificacionDTO.getCodigo(), 5)) {
			throw new Exception("El codigo solo puede contener 5 caracteres");
		}
		tipoIdentificacion.setCodigo(tipoIdentificacionDTO.getCodigo());
		
		
		//Validaciones FechaCreacion
		if(tipoIdentificacionDTO.getFechaCreacion()==null) {
			throw new SQLException("la fecha de creacion no puede ser nula");
		}
		tipoIdentificacion.setFechaCreacion(tipoIdentificacionDTO.getFechaCreacion());
		
		// VALIDACION NOMBRE
		if (tipoIdentificacionDTO.getNombre() == null || Utilities.isEmpty(tipoIdentificacionDTO.getNombre())) {
			throw new Exception("El nombre es obligatorio");
		}
		if (Utilities.isGreater(tipoIdentificacionDTO.getNombre(), 100)) {
			throw new Exception("El nombre solo puede contener 100 caracteres");
		}
		if (!Utilities.isOnlyLetters(tipoIdentificacionDTO.getNombre())) {
			throw new Exception("El nombre solo puede contener letras");
		}
		tipoIdentificacion.setNombre(tipoIdentificacionDTO.getNombre());

		//Validaciones usuCreador
		if (Utilities.isGreater(tipoIdentificacionDTO.getUsuCreador(), 10)) {
			 throw new SQLException("Solo puede contener 10 caracteres"); 
		 }
		
		 if(tipoIdentificacionDTO.getNombre()==null || Utilities.isEmpty(tipoIdentificacionDTO.getUsuCreador())) {
			 throw new SQLException("El UsuCreator es obligatorio");
			 
		 }
		 if(!Utilities.isOnlyletras(tipoIdentificacionDTO.getUsuCreador())) {
			 
			 throw new SQLException("El UsuCreator solo puede contener letras y sin espacios y mayusculas");
		 }
		 tipoIdentificacion.setUsuCreador(tipoIdentificacionDTO.getUsuCreador());
		
		tipoIdentificacionRepository.save(tipoIdentificacion);
		
		return tipoIdentificacion;
	}

	
	
	public TipoIdentificacion actualizarTipoIdentificacion(TipoIdentificacionDTO tipoIdentificacionDTO) throws Exception {
		
		TipoIdentificacion tipoIdentificacion=null;
		
		tipoIdentificacion=findById(tipoIdentificacionDTO.getIdTiId());
		
				// VALIDACION ESTADO
				if (tipoIdentificacionDTO.getEstado() == null || Utilities.isEmpty(tipoIdentificacionDTO.getEstado())) {
					throw new Exception("El estado es obligatorio");
				}
				if (Utilities.isGreater(tipoIdentificacionDTO.getEstado(), 1)) {
					throw new Exception("El estado solo puede contener 1 caracter");
				}
				if (!Utilities.isOnlyLetters(tipoIdentificacionDTO.getEstado())) {
					throw new Exception("El estado solo puede contener letras");
				}
				tipoIdentificacion.setEstado(tipoIdentificacionDTO.getEstado());
				
				
				// VALIDACION CODIGO
				if (tipoIdentificacionDTO.getCodigo() == null || Utilities.isEmpty(tipoIdentificacionDTO.getCodigo())) {
					throw new Exception("El codigo es obligatorio");
				}
				if (!Utilities.isOnlyLetters(tipoIdentificacionDTO.getCodigo())) {
					throw new Exception("El codigo solo puede contener letras");
				}
				if (Utilities.isGreater(tipoIdentificacionDTO.getCodigo(), 5)) {
					throw new Exception("El codigo solo puede contener 5 caracteres");
				}
				tipoIdentificacion.setCodigo(tipoIdentificacionDTO.getCodigo());
				
				
				//Validaciones FechaCreacion
				if(tipoIdentificacionDTO.getFechaCreacion()==null) {
					throw new SQLException("la fecha de creacion no puede ser nula");
				}
				tipoIdentificacion.setFechaCreacion(tipoIdentificacionDTO.getFechaCreacion());
				
				// VALIDACION NOMBRE
				if (tipoIdentificacionDTO.getNombre() == null || Utilities.isEmpty(tipoIdentificacionDTO.getNombre())) {
					throw new Exception("El nombre es obligatorio");
				}
				if (Utilities.isGreater(tipoIdentificacionDTO.getNombre(), 100)) {
					throw new Exception("El nombre solo puede contener 100 caracteres");
				}
				if (!Utilities.isOnlyLetters(tipoIdentificacionDTO.getNombre())) {
					throw new Exception("El nombre solo puede contener letras");
				}
				tipoIdentificacion.setNombre(tipoIdentificacionDTO.getNombre());

				//Validaciones usuCreador
				if (Utilities.isGreater(tipoIdentificacionDTO.getUsuCreador(), 10)) {
					 throw new SQLException("Solo puede contener 10 caracteres"); 
				 }
				
				 if(tipoIdentificacionDTO.getNombre()==null || Utilities.isEmpty(tipoIdentificacionDTO.getUsuCreador())) {
					 throw new SQLException("El UsuCreator es obligatorio");
					 
				 }
				 if(!Utilities.isOnlyletras(tipoIdentificacionDTO.getUsuCreador())) {
					 
					 throw new SQLException("El UsuCreator solo puede contener letras y sin espacios y mayusculas");
				 }
				 tipoIdentificacion.setUsuCreador(tipoIdentificacionDTO.getUsuCreador());
		
		tipoIdentificacionRepository.save(tipoIdentificacion);
		
		return tipoIdentificacion;
	}
	
	
	public TipoIdentificacion findById(Long idTiid) throws Exception{
		
		
		//Validamos el idDest venga con info
		if(idTiid == null) {
			throw new Exception("Debe ingresar un id destino");
		}
		
		if(!tipoIdentificacionRepository.findById(idTiid).isPresent()) {
			throw new Exception("El destino con id: "+idTiid + " no existe");
		}
		
		return tipoIdentificacionRepository.findById(idTiid).get();
		
	}
	
	
	public void eliminarTipoIdentificacion(Long idTiId) throws Exception{
		
		
		if(idTiId==null) {
			throw new Exception("El id tipo Identificacion es oblugatorio");
			
		}
		Optional<TipoIdentificacion>TipoIdentificacionBD=tipoIdentificacionRepository.findById(idTiId);
		List <ClienteDTO> listClientes = clienteService.ConsultarClientesPorTipoIdentificacion(findById(idTiId).getCodigo());
		
		
		if(TipoIdentificacionBD.isPresent()) {
			if(listClientes.isEmpty()) {
				tipoIdentificacionRepository.delete(TipoIdentificacionBD.get());
				
			}else {
				throw new Exception("No se puede eliminar un tipo de identificacion si hay un cliente");
			}
			
			
			
		}else {
			throw new Exception("El tipo de identificacion no se encontr�");
		}
	}
	
	
	
}
