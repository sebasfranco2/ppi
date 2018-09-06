package com.servitecafastwash.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="servicios")
public class Servicio implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private int precio;
	
	@NotEmpty
	private String nombre;
	
	@ManyToMany(fetch= FetchType.LAZY, cascade = { CascadeType.MERGE, CascadeType.PERSIST})
	@JoinColumn(name="tipo_vehiculo_id")
	private List<TipoVehiculo> tiposVehiculos;
	
	public Servicio() {
		tiposVehiculos = new ArrayList<TipoVehiculo>();		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getPrecio() {
		return precio;
	}

	public void setPrecio(int precio) {
		this.precio = precio;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<TipoVehiculo> getTiposVehiculos() {
		return tiposVehiculos;
	}

	public void setTiposVehiculos(List<TipoVehiculo> tiposVehiculos) {
		this.tiposVehiculos = tiposVehiculos;
	}
	private static final long serialVersionUID = 1L;
}
