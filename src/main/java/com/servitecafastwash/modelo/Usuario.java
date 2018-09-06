package com.servitecafastwash.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name="usuarios")
public class Usuario implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
    @NotEmpty
	private String nombre;
	
	@NotEmpty
	private String apellido;
	
	@NotEmpty
	@Email
	@Column(unique=true)
	private String email;
	
	@NotEmpty
	private String contrasena;

	
	@ColumnDefault("1")
	public Boolean enabled; 
	
	
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name="usuario_id")
	private List<Perfil> perfil;

	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name="usuario_id")
	private List<Auditoria> auditorias;
	
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name="usuario_id")
	private List<Factura> facturas;
	
	public Usuario() {
		auditorias = new ArrayList<Auditoria>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}
		
	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public List<Perfil> getPerfil() {
		return perfil;
	}

	public void setPerfil(List<Perfil> perfil) {
		this.perfil = perfil;
	}

	public List<Auditoria> getAuditorias() {
		return auditorias;
	}

	public void setAuditorias(List<Auditoria> auditorias) {
		this.auditorias = auditorias;
	}

	public List<Factura> getFacturas() {
		return facturas;
	}

	public void setFacturas(List<Factura> facturas) {
		this.facturas = facturas;
	}

	public List<Auditoria> getAuditoria() {
		return auditorias;
	}

	public void setAuditoria(List<Auditoria> auditoria) {
		this.auditorias = auditoria;
	}

	public void addAuditoria(Auditoria auditoria) {
		auditorias.add(auditoria);
	}
	
	private static final long serialVersionUID = 1L;
}
