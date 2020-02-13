package com.at.model;

import java.io.Serializable;
import java.util.ArrayList;
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
	private String Nombre;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return Nombre;
	}

	public void setNombre(String nombre) {
		Nombre = nombre;
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
