/**  
 * @Title:  TipoDestinoImpl.java   
 * @Package co.edu.usbcali.viajesusb.service   
 * @Description: description   
 * @author: Carlos Garaicoa     
 * @date:   7/09/2021 11:53:14 a.�m.   
 * @version V1.0 
 * @Copyright: Universidad San de Buenaventura
 */
package co.edu.usbcali.viajesusb.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import co.edu.usbcali.viajesusb.domain.Destino;
import co.edu.usbcali.viajesusb.domain.TipoDestino;
import co.edu.usbcali.viajesusb.dto.TipoDestinoDTO;
import co.edu.usbcali.viajesusb.repository.TipoDestinoRepository;
import co.edu.usbcali.viajesusb.utils.Utilities;

/**   
 * @ClassName:  TipoDestinoImpl   
  * @Description: TODO   
 * @author: Carlos Garaicoa     
 * @date:   7/09/2021 11:53:14 a.�m.      
 * @Copyright:  USB
 */

@Scope("singleton")
@Service
public class TipoDestinoServiceImpl implements TipoDestinoService{

	@Autowired
	private TipoDestinoRepository tipoDestinoRepository;
	
	
	@Autowired
	private DestinoService destinoService;
	/**   
	 * <p>Title: findByCodigo</p>   
	 * <p>Description: </p>   
	 * @param codigo
	 * @return
	 * @throws Exception   
	 * @see co.edu.usbcali.viajesusb.service.TipoDestinoService#findByCodigo(java.lang.String)   
	 */
	

	@Override
	public TipoDestino findByCodigo(String codigo) throws Exception {
	
			
			TipoDestino tipoDestino=null;
			
			if(codigo==null || Utilities.isEmpty(codigo)) {
				 throw new Exception("El codigo de tipo destino es obligatorio");
				 
			 }
			 if(!Utilities.isOnlyletras(codigo)) {
				 throw new Exception("El codigo de tipo destino solo puede contener letras y sin espacios y mayusculas");
			 }
			 
				
			tipoDestino=tipoDestinoRepository.findByCodigo(codigo);
				
		return tipoDestino;
	}

	
	
		@Override
		public TipoDestino findByCodigoAndEstado(String codigo, String estado) throws SQLException{
		
			 if (Utilities.isGreater(estado, 1)) {
				 throw new SQLException("Solo puede ingresar una letra"); 
			 }
			
			
			if(codigo==null || Utilities.isEmpty(codigo)) {
				 throw new SQLException("El codigo de tipo destino es obligatorio");
				 
			 }
			
			 if(!Utilities.isOnlyletras(codigo)) {
				 throw new SQLException("El codigo de tipo destino solo puede contener letras y sin espacios y mayusculas");
			 }
			
			 
			 if(estado==null || Utilities.isEmpty(estado)) {
				 throw new SQLException("El estado de tipo destino es obligatorio");
				 
			 }
			 
			 if(!Utilities.isOnlyletras(estado)) {
				 throw new SQLException("El estado de tipo destino solo puede contener letras y sin espacios y mayusculas");
			 }
			 
			
			TipoDestino tipoDestino=null;

			tipoDestino=tipoDestinoRepository.findByCodigoAndEstado(codigo,estado);
			
		
			return tipoDestino;
	}
	
		@Override
		public List<TipoDestino> findByEstadoOrderByNombreDesc(String estado) throws SQLException{
			
		
			List<TipoDestino> lstTipoDestino=null;
			
			
			 if (Utilities.isGreater(estado, 1)) {
				 throw new SQLException("Solo puede ingresar una letra"); 
			 }
			 
			if(estado==null || Utilities.isEmpty(estado)) {
				 throw new SQLException("El estado de tipo destino es obligatorio");
				 
			 }
			 
			 if(!Utilities.isOnlyletras(estado)) {
				 throw new SQLException("El estado de tipo destino solo puede contener letras y sin espacios y mayusculas");
			 }
			 
			 
			
		
			
			lstTipoDestino=tipoDestinoRepository.findByEstadoOrderByNombreDesc(estado);
			
			return lstTipoDestino;
			
		}
		
		public TipoDestino debeGuardarTipoDestino(TipoDestinoDTO tipoDestinoDTO) throws Exception{
			
			TipoDestino tipoDestino= new TipoDestino();
			
			//VALIDACIONES DEL CÓDIGO
			//NO PUEDE INGRESAR UN CODIGO VACIO
			if(tipoDestinoDTO.getCodigo() == null || Utilities.isEmpty(tipoDestinoDTO.getCodigo())) {
				throw new Exception("El codigo destino es obligatorio");
			}
			//EL CODIGO SOLAMENTE ACEPTA LETRAS, SIN NÚMEROS NI CARÁCTERES ESPECIALES
			if(!Utilities.isOnlyLetters(tipoDestinoDTO.getCodigo() )){
				throw new Exception("El codigo solo puede contener letras mayusculas. No puede contener números, ni carácteres especiales");
			}
			//EL TIPO DE IDENTIFICACION SOLO PUEDE CONTENER 5 CARÁCTERES
			if(Utilities.isGreater(tipoDestinoDTO.getCodigo() , 5)) {
				throw new Exception("El codigo solo puede contener 5 carácteres");
			}
			
			tipoDestino.setCodigo(tipoDestinoDTO.getCodigo());
			
			//VALIDACIONES DE LA DESCRIPCION
			//NO PUEDE INGRESAR UNA DESCRIPCION VACIA
			if(tipoDestinoDTO.getDescripcion() == null || Utilities.isEmpty(tipoDestinoDTO.getDescripcion())) {
				throw new Exception("La descripcion es obligatoria");
			}
			//UNICAMENTE ACEPTA LETRAS, SIN NÚMEROS NI CARÁCTERES ESPECIALES
			if(!Utilities.isOnlyLetters(tipoDestinoDTO.getDescripcion())){
				throw new Exception("La descripcion solo puede contener letras mayusculas. No puede contener números, ni carácteres especiales");
			}
			//LA DESCRIPCION SOLO PUEDE CONTENER 300	 CARÁCTERES
			if(Utilities.isGreater(tipoDestinoDTO.getDescripcion() , 300)) {
				throw new Exception("La descripcion solo puede contener 300 carácteres");
			}
			
			tipoDestino.setDescripcion(tipoDestinoDTO.getDescripcion());
			
			//VALIDACIONES DEL ESTADO
			//EL ESTADO SOLO PUEDE CONTENER UN CARÁCTER
			if(Utilities.isGreater(tipoDestinoDTO.getEstado(), 1)) {
				throw new Exception("El estado solo puede contener 1 carácter");
			}
			//NO PUEDE INGRESAR UN ESTADO VACIO
			if(tipoDestinoDTO.getEstado() == null || Utilities.isEmpty(tipoDestinoDTO.getEstado())) {
				throw new Exception("El estado de tipo destino es obligatorio");
			}
			
			//EL ESTADO SOLAMENTE ACEPTA LETRAS, SIN NÚMEROS NI CARÁCTERES ESPECIALES
			if(!Utilities.isOnlyLetters(tipoDestinoDTO.getEstado())){
				throw new Exception("El estado solo puede contener letras mayusculas. No puede contener números, ni carácteres especiales");
			}
			
			tipoDestino.setEstado(tipoDestinoDTO.getEstado());
			
			//VALIDACIONES DE LA FECHA DE CREACION
			//NO PUEDE INGRESAR VALORES VACIOS O NULOS
			if(tipoDestinoDTO.getFechaCreacion() == null) {
				throw new Exception("La fecha de creacion es obligatoria");
			}
			
			tipoDestino.setFechaCreacion(tipoDestinoDTO.getFechaCreacion());
			
			//VALIDACIONES DEL NOMBRE
			//EL NOMBRE SOLO PUEDE CONTENER 100 CARÁCTERES
			if(Utilities.isGreater(tipoDestinoDTO.getNombre(), 100)) {
				throw new Exception("El nombre solo puede contener 100 carácteres");
			}
			//NO PUEDE INGRESAR VALORES VACIOS O NULOS
			if(tipoDestinoDTO.getNombre() == null || Utilities.isEmpty(tipoDestinoDTO.getNombre())) {
				throw new Exception("El nombre es obligatorio");
			}
			//SOLAMENTE ACEPTA LETRAS, SIN NÚMEROS NI CARÁCTERES ESPECIALES
			if(!Utilities.isOnlyLetters(tipoDestinoDTO.getNombre().trim())){
				throw new Exception("El nombre solo puede contener letras.No puede contener números, ni carácteres especiales");
			}
			tipoDestino.setNombre(tipoDestinoDTO.getNombre());
			
			//VALIDACIONES DEL USU CREADOR
			//EL USU CRADOR SOLO PUEDE CONTENER 10 CARÁCTERES
			if(Utilities.isGreater(tipoDestinoDTO.getUsuCreator(), 10)) {
				throw new Exception("El usu creador solo puede contener 10 carácteres");
			}
			//NO PUEDE INGRESAR VALORES VACIOS O NULOS
			if(tipoDestinoDTO.getUsuCreator() == null || Utilities.isEmpty(tipoDestinoDTO.getUsuCreator())) {
				throw new Exception("El usuario creador es obligatorio");
			}
			//SOLAMENTE ACEPTA LETRAS, SIN NÚMEROS NI CARÁCTERES ESPECIALES
			if(!Utilities.isOnlyLetters(tipoDestinoDTO.getUsuCreator())){
				throw new Exception("El usuario creador solo puede contener letras.No puede contener números, ni carácteres especiales");
			}
			tipoDestino.setUsuCreator(tipoDestinoDTO.getUsuCreator());
			
			tipoDestinoRepository.save(tipoDestino);
			
			return tipoDestino;
		}

		
		
		public TipoDestino debeActualizarTipoDestino(TipoDestinoDTO tipoDestinoDTO)throws Exception{
			
			
			TipoDestino tipoDestino=null;
			
			tipoDestino=findById(tipoDestinoDTO.getIdTide());
			
			
			//VALIDACIONES DEL CÓDIGO
			//NO PUEDE INGRESAR UN CODIGO VACIO
			if(tipoDestinoDTO.getCodigo() == null || Utilities.isEmpty(tipoDestinoDTO.getCodigo())) {
				throw new Exception("El codigo destino es obligatorio");
			}
			//EL CODIGO SOLAMENTE ACEPTA LETRAS, SIN NÚMEROS NI CARÁCTERES ESPECIALES
			if(!Utilities.isOnlyLetters(tipoDestinoDTO.getCodigo() )){
				throw new Exception("El codigo solo puede contener letras mayusculas. No puede contener números, ni carácteres especiales");
			}
			//EL TIPO DE IDENTIFICACION SOLO PUEDE CONTENER 5 CARÁCTERES
			if(Utilities.isGreater(tipoDestinoDTO.getCodigo() , 5)) {
				throw new Exception("El codigo solo puede contener 5 carácteres");
			}
			
			tipoDestino.setCodigo(tipoDestinoDTO.getCodigo());
			
			//VALIDACIONES DE LA DESCRIPCION
			//NO PUEDE INGRESAR UNA DESCRIPCION VACIA
			if(tipoDestinoDTO.getDescripcion() == null || Utilities.isEmpty(tipoDestinoDTO.getDescripcion())) {
				throw new Exception("La descripcion es obligatoria");
			}
			//UNICAMENTE ACEPTA LETRAS, SIN NÚMEROS NI CARÁCTERES ESPECIALES
			if(!Utilities.isOnlyLetters(tipoDestinoDTO.getDescripcion())){
				throw new Exception("La descripcion solo puede contener letras mayusculas. No puede contener números, ni carácteres especiales");
			}
			//LA DESCRIPCION SOLO PUEDE CONTENER 300	 CARÁCTERES
			if(Utilities.isGreater(tipoDestinoDTO.getDescripcion() , 300)) {
				throw new Exception("La descripcion solo puede contener 300 carácteres");
			}
			
			tipoDestino.setDescripcion(tipoDestinoDTO.getDescripcion());
			
			//VALIDACIONES DEL ESTADO
			//EL ESTADO SOLO PUEDE CONTENER UN CARÁCTER
			if(Utilities.isGreater(tipoDestinoDTO.getEstado(), 1)) {
				throw new Exception("El estado solo puede contener 1 carácter");
			}
			//NO PUEDE INGRESAR UN ESTADO VACIO
			if(tipoDestinoDTO.getEstado() == null || Utilities.isEmpty(tipoDestinoDTO.getEstado())) {
				throw new Exception("El estado de tipo destino es obligatorio");
			}
			
			//EL ESTADO SOLAMENTE ACEPTA LETRAS, SIN NÚMEROS NI CARÁCTERES ESPECIALES
			if(!Utilities.isOnlyLetters(tipoDestinoDTO.getEstado())){
				throw new Exception("El estado solo puede contener letras mayusculas. No puede contener números, ni carácteres especiales");
			}
			
			tipoDestino.setEstado(tipoDestinoDTO.getEstado());
			
			//VALIDACIONES DE LA FECHA DE CREACION
			//NO PUEDE INGRESAR VALORES VACIOS O NULOS
			if(tipoDestinoDTO.getFechaCreacion() == null) {
				throw new Exception("La fecha de creacion es obligatoria");
			}
			
			tipoDestino.setFechaCreacion(tipoDestinoDTO.getFechaCreacion());
			
			//VALIDACIONES DEL NOMBRE
			//EL NOMBRE SOLO PUEDE CONTENER 100 CARÁCTERES
			if(Utilities.isGreater(tipoDestinoDTO.getNombre(), 100)) {
				throw new Exception("El nombre solo puede contener 100 carácteres");
			}
			//NO PUEDE INGRESAR VALORES VACIOS O NULOS
			if(tipoDestinoDTO.getNombre() == null || Utilities.isEmpty(tipoDestinoDTO.getNombre())) {
				throw new Exception("El nombre es obligatorio");
			}
			//SOLAMENTE ACEPTA LETRAS, SIN NÚMEROS NI CARÁCTERES ESPECIALES
			if(!Utilities.isOnlyLetters(tipoDestinoDTO.getNombre().trim())){
				throw new Exception("El nombre solo puede contener letras.No puede contener números, ni carácteres especiales");
			}
			tipoDestino.setNombre(tipoDestinoDTO.getNombre());
			
			//VALIDACIONES DEL USU CREADOR
			//EL USU CRADOR SOLO PUEDE CONTENER 10 CARÁCTERES
			if(Utilities.isGreater(tipoDestinoDTO.getUsuCreator(), 10)) {
				throw new Exception("El usu creador solo puede contener 10 carácteres");
			}
			//NO PUEDE INGRESAR VALORES VACIOS O NULOS
			if(tipoDestinoDTO.getUsuCreator() == null || Utilities.isEmpty(tipoDestinoDTO.getUsuCreator())) {
				throw new Exception("El usuario creador es obligatorio");
			}
			//SOLAMENTE ACEPTA LETRAS, SIN NÚMEROS NI CARÁCTERES ESPECIALES
			if(!Utilities.isOnlyLetters(tipoDestinoDTO.getUsuCreator())){
				throw new Exception("El usuario creador solo puede contener letras.No puede contener números, ni carácteres especiales");
			}
			tipoDestino.setUsuCreator(tipoDestinoDTO.getUsuCreator());
			
			tipoDestinoRepository.save(tipoDestino);
			
			return tipoDestino;
			
		}
		
		
		public TipoDestino findById(Long idTide) throws Exception{
		
			
			//Validamos el idDest venga con info
			if(idTide == null) {
				throw new Exception("Debe ingresar un id destino");
			}
			
			if(!tipoDestinoRepository.findById(idTide).isPresent()) {
				throw new Exception("El Tipodestino con id: "+idTide + " no existe");
			}
			
			return tipoDestinoRepository.findById(idTide).get();
			
		}
		
		
		public void eliminarTipoDestino(Long idTide) throws Exception{
			
			
			if(idTide==null) {
				throw new Exception("El id destino es oblugatorio");
				
			}
			Optional<TipoDestino>TipodestinoBD=tipoDestinoRepository.findById(idTide);
			System.out.println(idTide);
			List<Destino>destinoBD=destinoService.findByTipoDestino_Codigo(findById(idTide).getCodigo());
			
			if(TipodestinoBD.isPresent()) {
				if(destinoBD.isEmpty()) {
					tipoDestinoRepository.delete(TipodestinoBD.get());
				}else {
					throw new Exception("No se puede borrar el destino");
				}
				
				
			}else {
				throw new Exception("El destino no se encontr�");
			}
		}
		
		
		
		
	

	
}
