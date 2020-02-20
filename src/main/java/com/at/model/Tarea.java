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
	

    @Column(length = 10000)
    private String descripcion;

	
	private Date fechaDeCreacion;
	
	
	private int prioridad;
	
	private Date FechaDeUltimaModificacion;
	@Column(columnDefinition = "tinyint(4) default '1'")
	private boolean enabled;
	
	private int idSprint;

	
	//GettersAndSetters
	
	
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

	

	public int getPrioridad() {
		return prioridad;
	}

	public void setPrioridad(int prioridad) {
		this.prioridad = prioridad;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}



	public Usuario getCreadaPor() {
		return creadaPor;
	}

	public void setCreadaPor(Usuario creadaPor) {
		this.creadaPor = creadaPor;
	}

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

	//Relaciones entre tablas
	
	@ManyToOne
	@JoinColumn(name = "id_lista")
	private Lista lista;
	
	
	@ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario creadaPor;


}
