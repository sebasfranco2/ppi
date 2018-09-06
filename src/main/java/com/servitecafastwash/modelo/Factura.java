package com.servitecafastwash.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;


@Entity
@Table(name="facturas")
public class Factura implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="create_at")
	private Date createAt; 

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="hora_salida")
	private Date horaSalida;
	
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	private Vehiculo vehiculo;
	
	@NotNull
	@OneToMany(fetch =FetchType.LAZY, cascade =CascadeType.ALL)
	@JoinColumn(name="factura_id")
	private List<ItemFactura> itemsFacturas;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="usuario_id")
	private Usuario usuario;
	
	private int activo;
	
	public Factura() {
		itemsFacturas = new ArrayList<ItemFactura>();
	}

	@PrePersist
	private void createAt() {
		createAt = new Date();
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public Date getHoraSalida() {
		return horaSalida;
	}

	public void setHoraSalida(Date horaSalida) {
		this.horaSalida = horaSalida;
	}

	public Vehiculo getVehiculo() {
		return vehiculo;
	}

	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}

	public List<ItemFactura> getItemsFacturas() {
		return itemsFacturas;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public void setItemsFacturas(List<ItemFactura> itemsFacturas) {
		this.itemsFacturas = itemsFacturas;
	}

	public int getActivo() {
		return activo;
	}

	public void setActivo(int activo) {
		this.activo = activo;
	}

	public void addItemFactura(ItemFactura itemFactura) {
		itemsFacturas.add(itemFactura);
	}

	public Double getTotal() {
		Double total = 0.0;
		int size = itemsFacturas.size();
		
		for(int i=0; i < size; i++) {
			total += itemsFacturas.get(i).calcularImporte();
		}
		return total;
	}
	
	public void horaSalida() {
		horaSalida = new Date();
	}

	
	private static final long serialVersionUID = 1L;
}
