package co.edu.usbcali.viajesusb.domain;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


import lombok.Data;

@Data
@Entity
@Table(name="tipo_destino")

public class TipoDestino {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_tide")
	public long idTide;
	
	@Column(name="codigo",unique=true,nullable=false,length=5)
	public String codigo;
	
	@Column(name="nombre",unique=true,nullable=false,length=100)
	public String nombre;
	
	@Column(name="descripcion",nullable=false,length=300)
	public String descripcion;
	
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
	
	@OneToMany(fetch=FetchType.LAZY,mappedBy="tipoDestino")
	public List<Destino>destino=new ArrayList<>();
	
}
