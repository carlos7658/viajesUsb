package co.edu.usbcali.viajesusb.domain;





import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;



@Data
@Entity
@Table(name="destino")

public class Destino {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_dest")
	public long idDest;
	
	@Column(name="codigo",unique=true,nullable=false,length=5)
	public String codigo;
	
	@Column(name="nombre",unique=false,nullable=false,length=100)
	public String nombre;
	
	@Column(name="descripcion",nullable=false,length=300)
	public String descripcion;
	
	@Column(name="tierra",nullable=false,length=1)
	public String tierra;
	
	@Column(name="aire",nullable=false,length=1)
	public String aire;
	
	@Column(name="mar",nullable=false,length=1)
	public String mar;
	
	@Column(name="fecha_creacion",nullable=false)
	public Date fechaCreacion;
	
	@Column(name="fecha_modificacion")
	public Date fechaModificacion;
	
	@Column(name="usu_creador",nullable=false,length=10)
	public String usuCreator;
	
	@Column(name="usu_modificador",length=10)
	public String usuModificador;
	
	@Column(name="estado",nullable=false,length=1)
	public String estado;
	
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_tide",nullable=false)
	public TipoDestino tipoDestino;
	
	
}
