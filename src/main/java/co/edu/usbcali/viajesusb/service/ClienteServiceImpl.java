/**  
 * @Title:  ClienteServiceImpl.java   
 * @Package co.edu.usbcali.viajesusb.service   
 * @Description: description   
 * @author: Carlos Garaicoa     
 * @date:   14/09/2021 4:29:06 p.�m.   
 * @version V1.0 
 * @Copyright: Universidad San de Buenaventura
 */
package co.edu.usbcali.viajesusb.service;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import co.edu.usbcali.viajesusb.domain.Cliente;
import co.edu.usbcali.viajesusb.domain.Destino;
import co.edu.usbcali.viajesusb.domain.TipoIdentificacion;
import co.edu.usbcali.viajesusb.dto.ClienteDTO;
import co.edu.usbcali.viajesusb.dto.DestinoDTO;
import co.edu.usbcali.viajesusb.repository.ClienteRepository;
import co.edu.usbcali.viajesusb.repository.TipoDestinoRepository;
import co.edu.usbcali.viajesusb.utils.Constantes;
import co.edu.usbcali.viajesusb.utils.Utilities;

/**   
 * @ClassName:  ClienteServiceImpl   
  * @Description: TODO   
 * @author: Carlos Garaicoa     
 * @date:   14/09/2021 4:29:06 p.�m.      
 * @Copyright:  USB
 */
@Scope("singleton")
@Service

public class ClienteServiceImpl implements ClienteService {

	
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private TipoIdentificacionService tipoIdentificacionService;
	
	
	@Override
	public Page<Cliente> findByEstadoOrderByNumeroIdentificacionAsc(String estado, Pageable pageable)throws SQLException {
			
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
			 throw new SQLException("El estado de tipo destino solo puede contener letras y sin espacios y mayusculas");
		 }
		 
			Page<Cliente> pageCliente=null;
			
		
			pageCliente= clienteRepository.findByEstadoOrderByNumeroIdentificacionAsc(estado, pageable);

			return pageCliente;
	}

	
	@Override
	public Cliente findByCorreoIgnoreCase(String correo) throws SQLException {
		
		if (Utilities.isGreater(correo, 100)) {
			 throw new SQLException("Solo puede ingresar maximo 100 caracteres"); 
		 }
		
		if(correo==null || Utilities.isEmpty(correo)) {
			 throw new SQLException("El correo es obligatorio");
			 
		 }
		
		if(!Utilities.isValidEmail(correo)){
			throw new SQLException("El correo no tiene un formato adecuado");
		}
		
		 
		Cliente cliente=null;
		
		cliente= clienteRepository.findByCorreoIgnoreCase(correo);
		
		return cliente;
	}

	
	
	@Override
	public Cliente findByNumeroIdentificacionLike(String identificacion) throws SQLException {
	
		Cliente cliente=null;
		
		if(identificacion==null || Utilities.isEmpty(identificacion)) {
			 throw new SQLException("La idenficacion es obligatorio");
			 
		 }
		 if(!Utilities.isNumeric(identificacion)) {
			 throw new SQLException("La identificacion solo puede contener numeros y sin espacios ni caracteres especiales");
		 }
		 
		 if (Utilities.isGreater(identificacion, 15)) {
			 throw new SQLException("Solo puede contener 15 caracteres "); 
		 }
		
		cliente=clienteRepository.findByNumeroIdentificacionLike(identificacion);
		
		
		return cliente;
	}

	
	@Override
	public List<Cliente> findByNombreIgnoreCaseLike(String nombre) throws SQLException {
		
		
		 if (Utilities.isGreater(nombre, 100)) {
			 throw new SQLException("Solo puede contener 100 caracteres"); 
		 }
		
		 if(nombre==null || Utilities.isEmpty(nombre)) {
			 throw new SQLException("El nombre es obligatorio");
			 
		 }
		 if(!Utilities.isOnlyletras(nombre)) {
			 throw new SQLException("El nombre solo puede contener letras y sin espacios y mayusculas");
		 }
		 
		List<Cliente> lstClientes;
		lstClientes=clienteRepository.findByNombreIgnoreCaseLike(nombre);
		
		return lstClientes;
	}

	
	@Override
	public List<Cliente> findByFechaNacimientoBetween(Date start, Date finish) throws Exception {

		// NO PUEDE INGRESAR VALORES VACIOS O NULOS
		if (start == null) {
			throw new Exception("La fecha inicial es obligatoria");
		}
		if (finish == null) {
			throw new Exception("La fecha final es obligatoria");
		}

		List<Cliente> lstClientes;
		lstClientes = clienteRepository.findByFechaNacimientoBetween(start, finish);
		return lstClientes;
	}


	
	@Override
	public Integer countByEstado(String estado) throws SQLException {
		
		
		if (Utilities.isGreater(estado, 1)) {
			 throw new SQLException("Solo puede ingresar una letra"); 
		 }
		
		if(estado==null || Utilities.isEmpty(estado)) {
			 throw new SQLException("El estado del cliente es obligatorio");
			 
		 }
		 
		 if(!Utilities.isOnlyletras(estado)) {
			 throw new SQLException("El estado del cliente solo puede ser una letra y en mayuscula");
		 }
		 
		 
		Integer cantidad ;
		cantidad=clienteRepository.countByEstado(estado);
		
		return cantidad;
		
	}

	
	@Override
	public Page<Cliente> findByTipoIdentificacion_Codigo(String codigoTipoIdentificacion, Pageable pageable)throws Exception {
		
		
		 if (Utilities.isGreater(codigoTipoIdentificacion, 5)) {
			 throw new SQLException("Solo puede ingresar cinco caracteres "); 
		 }
		  
		
		 if(pageable==null) {
			 throw new SQLException("El pageable no puede ser nulo");
			 
		 }
		 
		 if(codigoTipoIdentificacion==null || Utilities.isEmpty(codigoTipoIdentificacion)) {
			 throw new SQLException("El tipo de identificacion es obligatorio");
			 
		 }
		 
		 if(!Utilities.isOnlyLetters(codigoTipoIdentificacion)) {
			 throw new SQLException("La identificacion letra y en mayuscula");
		 }
		 
		
		Page<Cliente> pageCliente = null;
		
		pageCliente = clienteRepository.findByTipoIdentificacion_Codigo(codigoTipoIdentificacion,pageable);
		
		return pageCliente;
	}

	
	@Override
	public List<Cliente> findByPrimerApellidoOrSegundoApellido(String primerApellido, String segundoApellido)throws Exception {
		
		if (Utilities.isGreater(primerApellido, 100)) {
			 throw new SQLException("Solo puede contener 100 caracteres en el primer apellido"); 
		 }
		 if (Utilities.isGreater(segundoApellido, 100)) {
			 throw new SQLException("Solo puede contener 100 caracteres en el segundo appelido"); 
		 }
		
		
		 if(primerApellido==null || Utilities.isEmpty(primerApellido)) {
			 throw new SQLException("El primer apellido es obligatorio");
			 
		 }
		 if(!Utilities.isOnlyletras(primerApellido)) {
			 throw new SQLException("El Primer apellido solo puede contener letras y sin espacios y mayusculas");
		 }
		 
		 if(segundoApellido==null || Utilities.isEmpty(segundoApellido)) {
			 throw new SQLException("El segundo apellido es obligatorio");
			 
		 }
		 
		 if(!Utilities.isOnlyletras(segundoApellido)) {
			 throw new SQLException("El Segundo apellido solo puede contener letras y sin espacios y mayusculas");
		 }
		 
		List<Cliente> ltsCliente;
		
		ltsCliente = clienteRepository.findByPrimerApellidoOrSegundoApellido(primerApellido,segundoApellido);
		
		return ltsCliente;
	}

	
	@Override
	public List<ClienteDTO> ConsultarClientesPorEstado(String estado) throws SQLException {
		
		 if (Utilities.isGreater(estado, 1)) {
			 throw new SQLException("Solo puede ingresar una letra"); 
		 }
		
		if(estado==null || Utilities.isEmpty(estado)) {
			 throw new SQLException("El estado del cliente es obligatorio");
			 
		 }
		 
		 if(!Utilities.isOnlyletras(estado)) {
			 throw new SQLException("El estado del cliente solo puede ser una letra y en mayuscula");
		 }
		 
		
		
		
		List<ClienteDTO> ltsClienteDTO;
		
		ltsClienteDTO=clienteRepository.ConsultarClientesPorEstado(estado);
		
		
		return ltsClienteDTO;
	}

	
	@Override
	public ClienteDTO ConsultarClientesPorNumeroIdentificacion(String numeroIdentificacion) throws SQLException {
		
		
		if(numeroIdentificacion==null || Utilities.isEmpty(numeroIdentificacion)) {
			 throw new SQLException("El tipo de identificacion es obligatorio");
			 
		 }
		 
		 if(Utilities.isNumeric(numeroIdentificacion)) {
			 throw new SQLException("La identificacion solo puede contener numeros, sin espacios, y sin caracteres especiales");
		 }
		 
		 if (Utilities.isGreater(numeroIdentificacion, 15)) {
			 throw new SQLException("Solo puede contener 15 caracteres"); 
		 }
		 
	
		
		ClienteDTO clienteDTO;
		
		clienteDTO=clienteRepository.ConsultarClientesPorNumeroIdentificacion(numeroIdentificacion);
		
		return clienteDTO;
	}

	
	@Override
	public List<ClienteDTO> ConsultarClientesPorTipoIdentificacion(String tipoIdentificacion) throws SQLException {
		
		if (Utilities.isGreater(tipoIdentificacion, 5)) {
			 throw new SQLException("Solo puede ingresar cinco caracteres "); 
		 }
		
		if(tipoIdentificacion==null || Utilities.isEmpty(tipoIdentificacion)) {
			 throw new SQLException("El tipo de identificacion es obligatorio");
			 
		 }
		 
		 if(!Utilities.isOnlyletras(tipoIdentificacion)) {
			 throw new SQLException("La identificacion letra y en mayuscula");
		 }
		 
		 
		
		
		List<ClienteDTO> ltsClienteDTO;
		
		ltsClienteDTO=clienteRepository.ConsultarClientesPorTipoIdentificacion(tipoIdentificacion);
		
		return ltsClienteDTO;
	}

	
	@Override
	public List<ClienteDTO> ConsultarClientesPorNombreEnOrdenAscendente(String nombre) throws SQLException {
		
		if (Utilities.isGreater(nombre, 100)) {
			 throw new SQLException("Solo puede contener en el nombre 100 caracteres"); 
		 }
		
		 if(nombre==null || Utilities.isEmpty(nombre)) {
			 throw new SQLException("El primer apellido es obligatorio");
			 
		 }
		 if(!Utilities.isOnlyletras(nombre)) {
			 throw new SQLException("El nombre de tipo destino solo puede contener letras y sin espacios y mayusculas");
		 }
		 
		
		List<ClienteDTO> ltsClienteDTO;
		
		ltsClienteDTO=clienteRepository.ConsultarClientesPorNombreEnOrdenAscendente(nombre);
		
		return ltsClienteDTO;
	}

	
	public Cliente debeGuardarCliente(ClienteDTO clienteDTO)throws Exception{
		
		
			Cliente cliente =new Cliente();
			TipoIdentificacion tipoIdentificacion=null;
			
			// VALIDACION ESTADO
			if (clienteDTO.getEstado() == null || Utilities.isEmpty(clienteDTO.getEstado())) {
				throw new Exception("El estado es obligatorio");
			}
			if (Utilities.isGreater(clienteDTO.getEstado(), 1)) {
				throw new Exception("El estado solo puede contener 1 caracter");
			}
			if (!Utilities.isOnlyletras(clienteDTO.getEstado())) {
				throw new Exception("El estado solo puede contener letras");
			}
			cliente.setEstado(clienteDTO.getEstado());

			// VALIDACION FECHA CREACION
			if (clienteDTO.getFechaCreacion() == null) {
				throw new Exception("La fecha creacion es obligatoria");
			}
			cliente.setFechaCreacion(clienteDTO.getFechaCreacion());

			// VALIDACION FECHA NACIMIENTO
			if (clienteDTO.getFechaNacimiento() == null) {
				throw new Exception("La fecha nacimiento es obligatoria");
			}
			cliente.setFechaNacimiento(clienteDTO.getFechaNacimiento());

			// VALIDACION NOMBRE
			if (clienteDTO.getNombre() == null || Utilities.isEmpty(clienteDTO.getNombre())) {
				throw new Exception("El nombre es obligatorio");
			}
			if (Utilities.isGreater(clienteDTO.getNombre(), 100)) {
				throw new Exception("El nombre solo puede contener 100 caracteres");
			}
			if (!Utilities.isOnlyletras(clienteDTO.getNombre())) {
				throw new Exception("El nombre solo puede contener letras");
			}
			cliente.setNombre(clienteDTO.getNombre());

			// VALIDACION NUMERO IDENTIFICACION
			if (clienteDTO.getNumeroIdentificacion() == null || Utilities.isEmpty(clienteDTO.getNumeroIdentificacion())) {
				throw new Exception("El numero de identificacion es obligatorio");
			}
			if (Utilities.isGreater(clienteDTO.getNumeroIdentificacion(), 15)) {
				throw new Exception("El numero de identificacion solo puede contener 15 caracteres");
			}
			if (!Utilities.isNumeric(clienteDTO.getNumeroIdentificacion())) {
				throw new Exception("El numero de identificacion solo puede contener numeros");
			}
			cliente.setNumeroIdentificacion(clienteDTO.getNumeroIdentificacion());

			// VALIDACION PRIMER APELLIDO
			if (clienteDTO.getPrimerApellido() == null || Utilities.isEmpty(clienteDTO.getPrimerApellido())) {
				throw new Exception("El primer apellido es obligatorio");
			}
			if (Utilities.isGreater(clienteDTO.getPrimerApellido(), 100)) {
				throw new Exception("El primer apellido solo puede contener 100 caracteres");
			}
			if (!Utilities.isOnlyletras(clienteDTO.getPrimerApellido())) {
				throw new Exception("El primer apellido solo puede contener letras");
			}
			cliente.setPrimerApellido(clienteDTO.getPrimerApellido());

			// VALIDACION SEGUNDO APELLIDO
			if (clienteDTO.getSegundoApellido() == null || Utilities.isEmpty(clienteDTO.getSegundoApellido())) {
				throw new Exception("El segundo apellido es obligatorio");
			}
			if (Utilities.isGreater(clienteDTO.getSegundoApellido(), 100)) {
				throw new Exception("El segundo apellido solo puede contener 100 caracteres");
			}
			if (!Utilities.isOnlyletras(clienteDTO.getSegundoApellido())) {
				throw new Exception("El segundo apellido solo puede contener letras");
			}
			cliente.setSegundoApellido(clienteDTO.getSegundoApellido());

			// VALIDACION SEXO
			if (clienteDTO.getSexo() == null || Utilities.isEmpty(clienteDTO.getSexo())) {
				throw new Exception("El sexo es obligatorio");
			}
			if (Utilities.isGreater(clienteDTO.getSexo(), 1)) {
				throw new Exception("El sexo solo puede contener 1 caracter");
			}
			if (!(clienteDTO.getSexo().equals(Constantes.MASCULINO) || clienteDTO.getSexo().equals(Constantes.FEMENINO))) {
				throw new Exception("El sexo solo puede ser Masculino o Femenino");
			}
			cliente.setSexo(clienteDTO.getSexo());

			// VALIDACION TELEFONO1
			if(clienteDTO.getTelefono1()!=null && !Utilities.isEmpty(clienteDTO.getTelefono1())) {
				if (Utilities.isGreater(clienteDTO.getTelefono1(), 15)) {
					throw new Exception("El telefono 1 solo puede contener 15 caracteres");
				}
				if (!Utilities.isNumeric(clienteDTO.getTelefono1())) {
					throw new Exception("El telefono 1 solo puede aceptar numeros");
				}
			}
			cliente.setTelefono1(clienteDTO.getTelefono1());

			// VALIDACION TELEFONO2
			if(clienteDTO.getTelefono2()!=null && !Utilities.isEmpty(clienteDTO.getTelefono2())) {
				if (Utilities.isGreater(clienteDTO.getTelefono2(), 15)) {
					throw new Exception("El telefono 2 solo puede contener 15 caracteres");
				}
				if (!Utilities.isNumeric(clienteDTO.getTelefono2())&& clienteDTO.getTelefono2()!=null) {
					throw new Exception("El telefono 2 solo puede aceptar numeros");
				}
			}
			cliente.setTelefono2(clienteDTO.getTelefono2());

			// VALIDACION USU CREADOR
			if(clienteDTO.getUsuCredor()!=null && !Utilities.isEmpty(clienteDTO.getUsuCredor())) {
				if (Utilities.isGreater(clienteDTO.getUsuCredor(), 10)) {
					throw new Exception("El usuario creador solo puede contener 10 caracter");
				}
				if (!Utilities.isOnlyletras(clienteDTO.getUsuCredor())&& clienteDTO.getUsuCredor()!=null) {
					throw new Exception("El usuario creador solo puede contener letras");
				}
			}
			cliente.setUsuCredor(clienteDTO.getUsuCredor());

			tipoIdentificacion = tipoIdentificacionService.findByCodigoAndEstado(clienteDTO.getCodigoTipoIdentificacion(),
					Constantes.ACTIVO);

			// Validamos que el tipo destino exista y este activo
			if (tipoIdentificacion == null) {
				throw new Exception("El tipo Identificacion no existe o no esta activo");
			}
			cliente.setTipoIdentificacion(tipoIdentificacion);
			clienteRepository.save(cliente);
			return cliente;
	}
	
	
	
	public Cliente findById(Long idClie) throws Exception{
		
		
		//Validamos el idDest venga con info
		if(idClie == null) {
			throw new Exception("Debe ingresar un id de cliente");
		}
		
		if(!clienteRepository.findById(idClie).isPresent()) {
			throw new Exception("El Cliente con id: "+idClie + " no existe");
		}
		
		return clienteRepository.findById(idClie).get();
		
	}
	
	
	public Cliente debeActualizarCliente(ClienteDTO clienteDTO)throws Exception{
		
		
			Cliente cliente =new Cliente();
			TipoIdentificacion tipoIdentificacion=null;
			
			
			cliente=findById(clienteDTO.getIdClie());
			
			// VALIDACION ESTADO
			if (clienteDTO.getEstado() == null || Utilities.isEmpty(clienteDTO.getEstado())) {
				throw new Exception("El estado es obligatorio");
			}
			if (Utilities.isGreater(clienteDTO.getEstado(), 1)) {
				throw new Exception("El estado solo puede contener 1 caracter");
			}
			if (!Utilities.isOnlyletras(clienteDTO.getEstado())) {
				throw new Exception("El estado solo puede contener letras");
			}
			cliente.setEstado(clienteDTO.getEstado());

			// VALIDACION FECHA CREACION
			if (clienteDTO.getFechaCreacion() == null) {
				throw new Exception("La fecha creacion es obligatoria");
			}
			cliente.setFechaCreacion(clienteDTO.getFechaCreacion());

			// VALIDACION FECHA NACIMIENTO
			if (clienteDTO.getFechaNacimiento() == null) {
				throw new Exception("La fecha nacimiento es obligatoria");
			}
			cliente.setFechaNacimiento(clienteDTO.getFechaNacimiento());

			// VALIDACION NOMBRE
			if (clienteDTO.getNombre() == null || Utilities.isEmpty(clienteDTO.getNombre())) {
				throw new Exception("El nombre es obligatorio");
			}
			if (Utilities.isGreater(clienteDTO.getNombre(), 100)) {
				throw new Exception("El nombre solo puede contener 100 caracteres");
			}
			if (!Utilities.isOnlyletras(clienteDTO.getNombre())) {
				throw new Exception("El nombre solo puede contener letras");
			}
			cliente.setNombre(clienteDTO.getNombre());

			// VALIDACION NUMERO IDENTIFICACION
			if (clienteDTO.getNumeroIdentificacion() == null || Utilities.isEmpty(clienteDTO.getNumeroIdentificacion())) {
				throw new Exception("El numero de identificacion es obligatorio");
			}
			if (Utilities.isGreater(clienteDTO.getNumeroIdentificacion(), 15)) {
				throw new Exception("El numero de identificacion solo puede contener 15 caracteres");
			}
			if (!Utilities.isNumeric(clienteDTO.getNumeroIdentificacion())) {
				throw new Exception("El numero de identificacion solo puede contener numeros");
			}
			cliente.setNumeroIdentificacion(clienteDTO.getNumeroIdentificacion());

			// VALIDACION PRIMER APELLIDO
			if (clienteDTO.getPrimerApellido() == null || Utilities.isEmpty(clienteDTO.getPrimerApellido())) {
				throw new Exception("El primer apellido es obligatorio");
			}
			if (Utilities.isGreater(clienteDTO.getPrimerApellido(), 100)) {
				throw new Exception("El primer apellido solo puede contener 100 caracteres");
			}
			if (!Utilities.isOnlyletras(clienteDTO.getPrimerApellido())) {
				throw new Exception("El primer apellido solo puede contener letras");
			}
			cliente.setPrimerApellido(clienteDTO.getPrimerApellido());

			// VALIDACION SEGUNDO APELLIDO
			if (clienteDTO.getSegundoApellido() == null || Utilities.isEmpty(clienteDTO.getSegundoApellido())) {
				throw new Exception("El segundo apellido es obligatorio");
			}
			if (Utilities.isGreater(clienteDTO.getSegundoApellido(), 100)) {
				throw new Exception("El segundo apellido solo puede contener 100 caracteres");
			}
			if (!Utilities.isOnlyletras(clienteDTO.getSegundoApellido())) {
				throw new Exception("El segundo apellido solo puede contener letras");
			}
			cliente.setSegundoApellido(clienteDTO.getSegundoApellido());

			// VALIDACION SEXO
			if (clienteDTO.getSexo() == null || Utilities.isEmpty(clienteDTO.getSexo())) {
				throw new Exception("El sexo es obligatorio");
			}
			if (Utilities.isGreater(clienteDTO.getSexo(), 1)) {
				throw new Exception("El sexo solo puede contener 1 caracter");
			}
			if (!(clienteDTO.getSexo().equals(Constantes.MASCULINO) || clienteDTO.getSexo().equals(Constantes.FEMENINO))) {
				throw new Exception("El sexo solo puede ser Masculino o Femenino");
			}
			cliente.setSexo(clienteDTO.getSexo());

			// VALIDACION TELEFONO1
			if(clienteDTO.getTelefono1()!=null && !Utilities.isEmpty(clienteDTO.getTelefono1())) {
				if (Utilities.isGreater(clienteDTO.getTelefono1(), 15)) {
					throw new Exception("El telefono 1 solo puede contener 15 caracteres");
				}
				if (!Utilities.isNumeric(clienteDTO.getTelefono1())) {
					throw new Exception("El telefono 1 solo puede aceptar numeros");
				}
			}
			cliente.setTelefono1(clienteDTO.getTelefono1());

			// VALIDACION TELEFONO2
			if(clienteDTO.getTelefono2()!=null && !Utilities.isEmpty(clienteDTO.getTelefono2())) {
				if (Utilities.isGreater(clienteDTO.getTelefono2(), 15)) {
					throw new Exception("El telefono 2 solo puede contener 15 caracteres");
				}
				if (!Utilities.isNumeric(clienteDTO.getTelefono2())&& clienteDTO.getTelefono2()!=null) {
					throw new Exception("El telefono 2 solo puede aceptar numeros");
				}
			}
			cliente.setTelefono2(clienteDTO.getTelefono2());

			// VALIDACION USU CREADOR
			if(clienteDTO.getUsuCredor()!=null && !Utilities.isEmpty(clienteDTO.getUsuCredor())) {
				if (Utilities.isGreater(clienteDTO.getUsuCredor(), 10)) {
					throw new Exception("El usuario creador solo puede contener 10 caracter");
				}
				if (!Utilities.isOnlyletras(clienteDTO.getUsuCredor())&& clienteDTO.getUsuCredor()!=null) {
					throw new Exception("El usuario creador solo puede contener letras");
				}
			}
			cliente.setUsuCredor(clienteDTO.getUsuCredor());

			tipoIdentificacion = tipoIdentificacionService.findByCodigoAndEstado(clienteDTO.getCodigoTipoIdentificacion(),
					Constantes.ACTIVO);

			// Validamos que el tipo destino exista y este activo
			if (tipoIdentificacion == null) {
				throw new Exception("El tipo Identificacion no existe o no esta activo");
			}
			cliente.setTipoIdentificacion(tipoIdentificacion);
			clienteRepository.save(cliente);
			return cliente;
		
	}
	
	
	public void eliminarCliente(Long idClie) throws Exception{
		
		
		if(idClie==null) {
			throw new Exception("El id del cliente es obligatorio");
			
		}
		Optional<Cliente>destinoBD=clienteRepository.findById(idClie);
		
		if(destinoBD.isPresent()) {
			clienteRepository.delete(destinoBD.get());
			
		}else {
			throw new Exception("El Cliente no se encontro");
		}
	}
	
	
}
