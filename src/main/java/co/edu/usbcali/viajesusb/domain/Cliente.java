package co.edu.usbcali.viajesusb.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMappings;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;
import javax.persistence.ConstructorResult;

import co.edu.usbcali.viajesusb.dto.ClienteDTO;
import lombok.Data;

@NamedNativeQueries({
	
	@NamedNativeQuery(name="Cliente.ConsultarClientesPorEstado", query="", resultSetMapping="ConsultarClientesPorEstado"),	
	@NamedNativeQuery(name="Cliente.ConsultarClientesPorNumeroIdentificacion", query="", resultSetMapping="ConsultarClientesPorNumeroIdentificacion"),
	@NamedNativeQuery(name="Cliente.ConsultarClientesPorTipoIdentificacion", query="", resultSetMapping="ConsultarClientesPorTipoIdentificacion"),
	@NamedNativeQuery(name="Cliente.ConsultarClientesPorNombreEnOrdenAscendente", query="", resultSetMapping="ConsultarClientesPorNombreEnOrdenAscendente"),
})

@SqlResultSetMappings({
	
	
	
	@SqlResultSetMapping(
		name="ConsultarClientesPorEstado", 
		classes= {
			@ConstructorResult(
				targetClass=ClienteDTO.class,
				columns= {
						@ColumnResult(name="idClie", type= Long.class),
						@ColumnResult(name="numeroIdentificacion", type= String.class),
						@ColumnResult(name="nombre", type= String.class),
						@ColumnResult(name="primerApellido", type= String.class),
				}
			) 
		}
	),
	
	
	
	
	@SqlResultSetMapping(
			name="ConsultarClientesPorNumeroIdentificacion", 
			classes= {
				@ConstructorResult(
					targetClass=ClienteDTO.class,
					columns= {
							@ColumnResult(name="idClie", type= Long.class),
							@ColumnResult(name="numeroIdentificacion", type= String.class),
							@ColumnResult(name="nombre", type= String.class),
					}
				) 
			}
		),
	
	
	
	@SqlResultSetMapping(
			name="ConsultarClientesPorTipoIdentificacion", 
			classes= {
				@ConstructorResult(
					targetClass=ClienteDTO.class,
					columns= {
							@ColumnResult(name="idClie", type= Long.class),
							@ColumnResult(name="numeroIdentificacion", type= String.class),
							@ColumnResult(name="nombre", type= String.class),
					}
				) 
			}
		),
	
	
	
	@SqlResultSetMapping(
			name="ConsultarClientesPorNombreEnOrdenAscendente", 
			classes= {
				@ConstructorResult(
					targetClass=ClienteDTO.class,
					columns= {
							@ColumnResult(name="idClie", type= Long.class),
							@ColumnResult(name="numeroIdentificacion", type= String.class),
							@ColumnResult(name="nombre", type= String.class),
					}
				) 
			}
		),
	
})

@Data
@Entity
@Table(name="cliente")

public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_clie")
	public long idClie;
	
	@Column(name="numero_identificacion",unique=true,nullable=false,length=15)
	public String numeroIdentificacion;
	
	@Column(name="primer_apellido",nullable=false,length=100)
	public String primerApellido;
	
	@Column(name="segundo_apellido",nullable=true,length=100)
	public String segundoApellido;
	
	@Column(name="nombre",nullable=false,length=100)
	public String nombre;
	
	@Column(name="telefono1",nullable=true,length=15)
	public String telefono1;
	
	@Column(name="telefono2",nullable=true,length=15)
	public String telefono2;
	
	@Column(name="correo",nullable=true,length=100)
	public String correo;
	
	@Column(name="sexo",nullable=false,length=1)
	public String sexo;
	
	@Column(name="fecha_nacimiento",nullable=false)
	public Date fechaNacimiento;
	
	@Column(name="fecha_creacion",nullable=false)
	public Date fechaCreacion;
	
	@Column(name="fecha_modificacion",nullable=true)
	public Date fechaModificacion;
	
	@Column(name="usu_creador",nullable=false,length=10)
	public String usuCredor;
	
	@Column(name="usu_modificador",nullable=true,length=10)
	public String usuModificador;
	
	@Column(name="estado",nullable=false,length=1)
	public String estado;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_tiid",nullable=false)
	public TipoIdentificacion tipoIdentificacion;
	
}
