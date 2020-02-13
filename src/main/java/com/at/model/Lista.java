package com.at.model;

import java.io.Serializable;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "lista")
public class Lista implements Serializable {

	private static final long serialVersionUID = -1467671316784641476L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(length = 150, nullable = false)
	private String Nombre;
	@Column(nullable = false)
	@Size(min = 0, max = 4)
	private int posicion;
	

//	@Column(length = 150, nullable = false)
//	private int idTablero;

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
	
//	@OneToMany(cascade = CascadeType.ALL, mappedBy = "columna")
//    private List<Tarea> tareas;

	@ManyToOne
    @JoinColumn(name = "id_tablero")
    private Tablero tablero;

	public Tablero getTablero() {
		return tablero;
	}

	public void setTablero(Tablero tablero) {
		this.tablero = tablero;
	}

	public int getPosicion() {
		return posicion;
	}

	public void setPosicion(int posicion) {
		this.posicion = posicion;
	}


	
	
	
	
	
	
	
	//	@OneToOne(mappedBy = "columna")
//    private Tarea tarea;
//	
//	
//	@OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "id_columna", referencedColumnName = "idColumna")
//    private Tablero tablero;
	
	
	
	
//	@OneToMany(fetch = FetchType.EAGER)
//	@JoinTable(name = "columna_tablero", joinColumns = {
//			@JoinColumn(name = "id_columna", referencedColumnName = "idColumna") }, inverseJoinColumns = {
//					@JoinColumn(name = "id_tablero", referencedColumnName = "idTablero") })
//	private Set<Tablero> tableros;
	
	
	

}
