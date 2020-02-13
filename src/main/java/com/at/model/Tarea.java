package com.at.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tarea")
public class Tarea implements Serializable {

	private static final long serialVersionUID = 1404956137521821642L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idTarea;
	@Column(length = 150, nullable = false)
	private String nombre;
	
	private Date fechaDeCreacion;
	
	
	private String prioridad;
	
	private Date FechaDeUltimaModificacion;
	@Column(columnDefinition = "tinyint(4) default '1'")
	private boolean enabled;
	
	private int idSprint;

	public int getIdTarea() {
		return idTarea;
	}

	public void setIdTarea(int idTarea) {
		this.idTarea = idTarea;
	}


	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	public Date getFechaDeUltimaModificacion() {
		return FechaDeUltimaModificacion;
	}

	public void setFechaDeUltimaModificacion(Date fechaDeUltimaModificacion) {
		FechaDeUltimaModificacion = fechaDeUltimaModificacion;
	}


	public int getIdSprint() {
		return idSprint;
	}

	public void setIdSprint(int idSprint) {
		this.idSprint = idSprint;
	}

	public String getPrioridad() {
		return prioridad;
	}

	public void setPrioridad(String prioridad) {
		this.prioridad = prioridad;
	}



	@ManyToOne
	@JoinColumn(name = "id_lista")
	private Lista lista;

	public Lista getLista() {
		return lista;
	}

	public void setLista(Lista lista) {
		this.lista = lista;
	}

	public Date getFechaDeCreacion() {
		return fechaDeCreacion;
	}

	public void setFechaDeCreacion(Date fechaDeCreacion) {
		this.fechaDeCreacion = fechaDeCreacion;
	}

//			@JoinColumn(name = "id_tablero", referencedColumnName = "idTablero",
//					inverseJoinColumns = {
//						@JoinColumn(name = "id_columna", referencedColumnName = "idColumna") })
//	private Set<Columna> columnas;

}
