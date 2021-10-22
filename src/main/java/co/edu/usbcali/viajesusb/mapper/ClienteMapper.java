/**  
 * @Title:  ClienteMapper.java   
 * @Package co.edu.usbcali.viajesusb.mapper   
 * @Description: description   
 * @author: Carlos Garaicoa     
 * @date:   18/10/2021 4:35:07 p. m.   
 * @version V1.0 
 * @Copyright: Universidad San de Buenaventura
 */
package co.edu.usbcali.viajesusb.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;

import co.edu.usbcali.viajesusb.domain.Cliente;
import co.edu.usbcali.viajesusb.dto.ClienteDTO;

@Mapper(componentModel="spring")
public interface ClienteMapper {
	
	
	@Mapping(source="tipoIdentificacion.codigo",target="codigoTipoIdentificacion")
	public ClienteDTO clienteToClienteDTO(Cliente cliente);
	
	@Mapping(source="codigoTipoIdentificacion",target="tipoIdentificacion.codigo")
	public Cliente clienteDTOTocliente(ClienteDTO clienteDTO);

	
	
	//public Page<ClienteDTO> pageClienteToPageClienteDTO(Page<Cliente> pageCliente);
	//public Page<ClienteDTO> pageClienteDTOToPageCliente(Page<Cliente> pageClienteDTO);
	
	public List<ClienteDTO> listClienteToListClienteDTO(List<Cliente> lstcliente);
	public List<ClienteDTO> listClienteDTOToListCliente(List<ClienteDTO> lstclienteDTO);

	
	
}
