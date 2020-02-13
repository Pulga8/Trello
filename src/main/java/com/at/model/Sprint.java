package com.at.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "sprint")
public class Sprint {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idSprint;
	@Column(length = 150, nullable = false)
	private String Nombre;
	@Column(length = 250)
	private String Descipcion;

	public int getIdSprint() {
		return idSprint;
	}

	public void setIdSprint(int idSprint) {
		this.idSprint = idSprint;
	}

	public String getNombre() {
		return Nombre;
	}

	public void setNombre(String nombre) {
		Nombre = nombre;
	}

	public String getDescipcion() {
		return Descipcion;
	}

	public void setDescipcion(String descipcion) {
		Descipcion = descipcion;
	}

	@ManyToOne
	@JoinColumn(name = "id_tarea")
	private Tarea tarea;
	
	
	public Tarea getTarea() {
		return tarea;
	}

	public void setTarea(Tarea tarea) {
		this.tarea = tarea;
	}
	
}