/**  
 * @Title:  DestinoServiceImpl.java   
 * @Package co.edu.usbcali.viajesusb.service   
 * @Description: description   
 * @author: Carlos Garaicoa     
 * @date:   7/09/2021 12:22:07 p.�m.   
 * @version V1.0 
 * @Copyright: Universidad San de Buenaventura
 */
package co.edu.usbcali.viajesusb.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import co.edu.usbcali.viajesusb.domain.Destino;
import co.edu.usbcali.viajesusb.domain.TipoDestino;
import co.edu.usbcali.viajesusb.dto.DestinoDTO;
import co.edu.usbcali.viajesusb.repository.DestinoRepository;
import co.edu.usbcali.viajesusb.utils.Constantes;
import co.edu.usbcali.viajesusb.utils.Utilities;


/**   
 * @ClassName:  DestinoServiceImpl   
  * @Description: TODO   
 * @author: Carlos Garaicoa     
 * @date:   7/09/2021 12:22:07 p.�m.      
 * @Copyright:  USB
 */

@Scope("singleton")
@Service
public class DestinoServiceImpl implements DestinoService {

		@Autowired
		private DestinoRepository destinoRepository;
		
		@Autowired
		private TipoDestinoService tipoDestinoService;
	
		@Override
		public List<Destino> findByTipoDestino_Codigo(String codigoTipoDestino) throws Exception {
		 
		 List<Destino> ltsDestino=null;
		 
		 if (Utilities.isGreater(codigoTipoDestino, 5)) {
			 throw new SQLException("Solo puede contener cinco caracteres "); 
		 }
		 
		 if(codigoTipoDestino==null || Utilities.isEmpty(codigoTipoDestino)) {
			 throw new Exception("El codigo de tipo destino es obligatorio");
			 
		 }
		 
		 if(!Utilities.isOnlyletras(codigoTipoDestino.trim())) {
			 throw new Exception("El codigo de tipo destino solo puede contener numeros");
		 }
		 
		 
		 ltsDestino=destinoRepository.findByTipoDestino_Codigo(codigoTipoDestino);
		 return ltsDestino;
		
		}
		
		
		@Override
		public Page<Destino> findByEstado(String estado, Pageable pageable) throws SQLException {
			Page<Destino> pageDestino=destinoRepository.findByEstado(estado, pageable);
			

			 if (Utilities.isGreater(estado, 1)) {
				 throw new SQLException("Solo puede ingresar una letra"); 
			 }
			 
			
			
			 if(estado==null || Utilities.isEmpty(estado)) {
				 throw new SQLException("El estado de tipo destino es obligatorio");
				 
			 }
			 
			 if(pageable==null) {
				 throw new SQLException("El estado de tipo destino es obligatorio");
				 
			 }
			 
			 if(!Utilities.isOnlyletras(estado)) {
				 throw new SQLException("El estado solo puede contener letras y sin espacios y mayusculas");
			 }
			 
			
			 
			return pageDestino;
			
		}
		
		
		/**
		 * 
		 * @Title: guardarDestino   
		   * @Description: Metodo que guarda un destino  
		 * @param: @param destino
		 * @param: @throws Exception      
		 * @return: void      
		 * @throws
		 */
		@Override
		public Destino guardarDestino(DestinoDTO destinoDTO)throws Exception {
			
			Destino destino =null;
			TipoDestino tipoDestino =null;
			
			
			destino=new Destino();
			
			// Validaciones de aire
			if (Utilities.isGreater(destinoDTO.getAire(), 1)) {
				throw new SQLException("Solo puede contener 1 caracteres");
			}
			if (destinoDTO.getAire() == null || Utilities.isEmpty(destinoDTO.getAire())) {
				throw new Exception("El campo del aire es obligatorio");
			}
			if (!(destinoDTO.getAire().equals(Constantes.SI) || destinoDTO.getAire().equals(Constantes.NO))) {
				throw new Exception("El campo del aire solo acepta Si o No");
			}
			destino.setAire(destinoDTO.getAire());

			// Validacion de codigo
			if (Utilities.isGreater(destinoDTO.getCodigo(), 5)) {
				throw new SQLException("Solo puede ingresar 5 letras");
			}

			if (destinoDTO.getCodigo() == null || Utilities.isEmpty(destinoDTO.getCodigo())) {
				throw new SQLException("El codigo del cliente es obligatorio");

			}

			if (!Utilities.isOnlyletras(destinoDTO.getCodigo())) {
				throw new SQLException("El Destinooo solo puede ser 5 letras y en mayuscula");
			}
			destino.setCodigo(destinoDTO.getCodigo());

			// Validaciones de descripcion
			if (Utilities.isGreater(destinoDTO.getDescripcion(), 300)) {
				throw new SQLException("Solo puede ingresar 5 letras");
			}

			if (destinoDTO.getDescripcion() == null || Utilities.isEmpty(destinoDTO.getDescripcion())) {
				throw new SQLException("El codigo del cliente es obligatorio");

			}

			if (!Utilities.isOnlyletras2(destinoDTO.getDescripcion())) {
				throw new SQLException("La descripcion solo puede ser 5 letras y en mayuscula");
			}
			destino.setDescripcion(destinoDTO.getDescripcion());

			// Validacion de estado
			if (Utilities.isGreater(destinoDTO.getEstado(), 1)) {
				throw new SQLException("Solo puede ingresar una letra");
			}

			if (destinoDTO.getEstado() == null || Utilities.isEmpty(destinoDTO.getEstado())) {
				throw new SQLException("El estado del destino es obligatorio");

			}

			if (!Utilities.isOnlyletras(destinoDTO.getEstado())) {
				throw new SQLException("El estado del cliente solo puede ser una letra y en mayuscula");
			}
			destino.setEstado(destinoDTO.getEstado());

			// Validaciones FechaCreacion
			if (destinoDTO.getFechaCreacion() == null) {
				throw new SQLException("la fecha de creacion no puede ser nula");
			}
			destino.setFechaCreacion(destinoDTO.getFechaCreacion());

			// Validaciones de mar
			if (Utilities.isGreater(destinoDTO.getMar(), 1)) {
				throw new SQLException("Solo puede contener 1 caracteres");
			}
			if (destinoDTO.getMar() == null || Utilities.isEmpty(destinoDTO.getMar())) {
				throw new Exception("El campo del mar es obligatorio");
			}
			if (!(destinoDTO.getMar().equals(Constantes.SI) || destinoDTO.getMar().equals(Constantes.NO))) {
				throw new Exception("El campo del mar solo acepta Si o No");
			}
			destino.setMar(destinoDTO.getMar());

			// Validaciones de nombre
			if (Utilities.isGreater(destinoDTO.getNombre(), 100)) {
				throw new SQLException("Solo puede contener 100 caracteres");
			}

			if (destinoDTO.getNombre() == null || Utilities.isEmpty(destinoDTO.getNombre())) {
				throw new SQLException("El nombre es obligatorio");

			}
			if (!Utilities.isOnlyletras2(destinoDTO.getNombre())) {
				throw new SQLException("El nombre solo puede contener letras y sin espacios y mayusculas");
			}
			destino.setNombre(destinoDTO.getNombre());

			// Validaciones de Tierra
			if (Utilities.isGreater(destinoDTO.getTierra(), 1)) {
				throw new SQLException("Solo puede contener 1 caracteres");
			}
			if (destinoDTO.getTierra() == null || Utilities.isEmpty(destinoDTO.getTierra())) {
				throw new Exception("El campo de tierra es obligatorio");
			}
			if (!(destinoDTO.getTierra().equals(Constantes.SI) || destinoDTO.getTierra().equals(Constantes.NO))) {
				throw new Exception("El campo de tierra solo acepta Si o No");
			}
			destino.setTierra(destinoDTO.getTierra());

			// Validaciones usuCreador
			if (Utilities.isGreater(destinoDTO.getUsuCreator(), 10)) {
				throw new SQLException("Solo puede contener 10 caracteres");
			}

			if (destinoDTO.getNombre() == null || Utilities.isEmpty(destinoDTO.getUsuCreator())) {
				throw new SQLException("El UsuCreator es obligatorio");

			}
			if (!Utilities.isOnlyletras(destinoDTO.getUsuCreator())) {
				throw new SQLException("El UsuCreator solo puede contener letras y sin espacios y mayusculas");
			}
			destino.setUsuCreator(destinoDTO.getUsuCreator());

			// se consulta el tipo destino dado su id

			tipoDestino = tipoDestinoService.findByCodigoAndEstado(destinoDTO.getCodigoTipoDestino(), Constantes.ACTIVO);

			// validamos que el tipo destino exista y este activo

			if (tipoDestino == null) {
				throw new Exception("El tipo destino " + destinoDTO.getCodigoTipoDestino() + " No existe ");
			}

			destino.setTipoDestino(tipoDestino);

			destinoRepository.save(destino);

			return destino;
			
		}
	
		
		@Override
		public Destino actualizarDestino(DestinoDTO destinoDTO) throws Exception{
			
			Destino destino = null;
			TipoDestino tipoDestino = null;
			
			// Validaciones de aire
			if (Utilities.isGreater(destinoDTO.getAire(), 1)) {
				throw new SQLException("Solo puede contener 1 caracteres");
			}
			if (destinoDTO.getAire() == null || Utilities.isEmpty(destinoDTO.getAire())) {
				throw new Exception("El campo del aire es obligatorio");
			}
			if (!(destinoDTO.getAire().equals(Constantes.SI) || destinoDTO.getAire().equals(Constantes.NO))) {
				throw new Exception("El campo del aire solo acepta Si o No");
			}
			destino.setAire(destinoDTO.getAire());

			// Validacion de codigo
			if (Utilities.isGreater(destinoDTO.getCodigo(), 5)) {
				throw new SQLException("Solo puede ingresar 5 letras");
			}

			if (destinoDTO.getCodigo() == null || Utilities.isEmpty(destinoDTO.getCodigo())) {
				throw new SQLException("El codigo del cliente es obligatorio");

			}

			if (!Utilities.isOnlyletras(destinoDTO.getCodigo())) {
				throw new SQLException("El Destinooo solo puede ser 5 letras y en mayuscula");
			}
			destino.setCodigo(destinoDTO.getCodigo());

			// Validaciones de descripcion
			if (Utilities.isGreater(destinoDTO.getDescripcion(), 300)) {
				throw new SQLException("Solo puede ingresar 5 letras");
			}

			if (destinoDTO.getDescripcion() == null || Utilities.isEmpty(destinoDTO.getDescripcion())) {
				throw new SQLException("El codigo del cliente es obligatorio");

			}

			if (!Utilities.isOnlyletras2(destinoDTO.getDescripcion())) {
				throw new SQLException("La descripcion solo puede ser 5 letras y en mayuscula");
			}
			destino.setDescripcion(destinoDTO.getDescripcion());

			// Validacion de estado
			if (Utilities.isGreater(destinoDTO.getEstado(), 1)) {
				throw new SQLException("Solo puede ingresar una letra");
			}

			if (destinoDTO.getEstado() == null || Utilities.isEmpty(destinoDTO.getEstado())) {
				throw new SQLException("El estado del destino es obligatorio");

			}

			if (!Utilities.isOnlyletras(destinoDTO.getEstado())) {
				throw new SQLException("El estado del cliente solo puede ser una letra y en mayuscula");
			}
			destino.setEstado(destinoDTO.getEstado());

			// Validaciones FechaCreacion
			if (destinoDTO.getFechaCreacion() == null) {
				throw new SQLException("la fecha de creacion no puede ser nula");
			}
			destino.setFechaCreacion(destinoDTO.getFechaCreacion());

			// Validaciones de mar
			if (Utilities.isGreater(destinoDTO.getMar(), 1)) {
				throw new SQLException("Solo puede contener 1 caracteres");
			}
			if (destinoDTO.getMar() == null || Utilities.isEmpty(destinoDTO.getMar())) {
				throw new Exception("El campo del mar es obligatorio");
			}
			if (!(destinoDTO.getMar().equals(Constantes.SI) || destinoDTO.getMar().equals(Constantes.NO))) {
				throw new Exception("El campo del mar solo acepta Si o No");
			}
			destino.setMar(destinoDTO.getMar());

			// Validaciones de nombre
			if (Utilities.isGreater(destinoDTO.getNombre(), 100)) {
				throw new SQLException("Solo puede contener 100 caracteres");
			}

			if (destinoDTO.getNombre() == null || Utilities.isEmpty(destinoDTO.getNombre())) {
				throw new SQLException("El nombre es obligatorio");

			}
			if (!Utilities.isOnlyletras2(destinoDTO.getNombre())) {
				throw new SQLException("El nombre solo puede contener letras y sin espacios y mayusculas");
			}
			destino.setNombre(destinoDTO.getNombre());

			// Validaciones de Tierra
			if (Utilities.isGreater(destinoDTO.getTierra(), 1)) {
				throw new SQLException("Solo puede contener 1 caracteres");
			}
			if (destinoDTO.getTierra() == null || Utilities.isEmpty(destinoDTO.getTierra())) {
				throw new Exception("El campo de tierra es obligatorio");
			}
			if (!(destinoDTO.getTierra().equals(Constantes.SI) || destinoDTO.getTierra().equals(Constantes.NO))) {
				throw new Exception("El campo de tierra solo acepta Si o No");
			}
			destino.setTierra(destinoDTO.getTierra());

			// Validaciones usuCreador
			if (Utilities.isGreater(destinoDTO.getUsuCreator(), 10)) {
				throw new SQLException("Solo puede contener 10 caracteres");
			}

			if (destinoDTO.getNombre() == null || Utilities.isEmpty(destinoDTO.getUsuCreator())) {
				throw new SQLException("El UsuCreator es obligatorio");

			}
			if (!Utilities.isOnlyletras(destinoDTO.getUsuCreator())) {
				throw new SQLException("El UsuCreator solo puede contener letras y sin espacios y mayusculas");
			}
			destino.setUsuCreator(destinoDTO.getUsuCreator());

			// se consulta el tipo destino dado su id

			tipoDestino = tipoDestinoService.findByCodigoAndEstado(destinoDTO.getCodigoTipoDestino(), Constantes.ACTIVO);

			// validamos que el tipo destino exista y este activo

			if (tipoDestino == null) {
				throw new Exception("El tipo destino " + destinoDTO.getCodigoTipoDestino() + " No existe ");
			}

			destino.setTipoDestino(tipoDestino);

			destinoRepository.save(destino);

			return destino;
			
		}
		
		
		public void eliminarDestino(Long idDest) throws Exception{
			
			
			if(idDest==null) {
				throw new Exception("El id destino es oblugatorio");
				
			}
			Optional<Destino>destinoBD=destinoRepository.findById(idDest);
			
			if(destinoBD.isPresent()) {
				destinoRepository.delete(destinoBD.get());
				
			}else {
				throw new Exception("El destino no se encontr�");
			}
		}
	
		
	
		public Destino findById(Long idDest) throws Exception{
		
			
			//Validamos el idDest venga con info
			if(idDest == null) {
				throw new Exception("Debe ingresar un id destino");
			}
			
			if(!destinoRepository.findById(idDest).isPresent()) {
				throw new Exception("El destino con id: "+idDest + " no existe");
			}
			
			return destinoRepository.findById(idDest).get();
			
		}
		
		
		

	}
	
	

