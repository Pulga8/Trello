package com.at.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tablero")
public class Tablero implements Serializable {

	private static final long serialVersionUID = 3414725599827013782L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(length = 150, nullable = false)
	private String nombre;

	@Column(nullable = false)
	private int posicion;

	@Column(length = 200, nullable = false)
	private String fondo;

	@Column(nullable = false)
	private boolean favorito = false;
	
	 private Date fechaCreacion = new Date();

	// Getters and Setters 
	//Encapsulacion de datos

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Set<Usuario> getMiembros() {
		return miembros;
	}

	public void setMiembros(Set<Usuario> miembros) {
		this.miembros = miembros;
	}

	public Usuario getAdministrador() {
		return administrador;
	}

	public void setAdministrador(Usuario administrador) {
		this.administrador = administrador;
	}

	public List<Lista> getListas() {
		return listas;
	}

	public void setListas(List<Lista> listas) {
		this.listas = listas;
	}

	public int getPosicion() {
		return posicion;
	}

	public void setPosicion(int posicion) {
		this.posicion = posicion;
	}

	public String getFondo() {
		return fondo;
	}

	public void setFondo(String fondo) {
		this.fondo = fondo;
	}

	public boolean isFavorito() {
		return favorito;
	}

	public void setFavorito(boolean favorito) {
		this.favorito = favorito;
	}
	
	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}


	// Relaciones de Tablas

	@ManyToMany
	@JoinTable(name = "miembros", joinColumns = {
			@JoinColumn(name = "id_tablero", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "id_usuario", referencedColumnName = "idUser") })
	private Set<Usuario> miembros = new HashSet<Usuario>();

	@ManyToOne
	@JoinColumn(name = "id_user")
	private Usuario administrador;

	@OneToMany(cascade = CascadeType.REMOVE, mappedBy = "tablero")
	private List<Lista> listas = new ArrayList<Lista>();



	
}
