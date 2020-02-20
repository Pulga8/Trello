package com.at.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "lista")
public class Lista implements Serializable {

	private static final long serialVersionUID = -1467671316784641476L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@OneToOne
	@JoinColumn(name = "tipoLista_id",referencedColumnName ="id")
	private TipoLista tipoLista;
	
	
	
	@Column(nullable = false)
	private int posicion;
	

//	@Column(length = 150, nullable = false)
//	private int idTablero;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	

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

	public TipoLista getTipoLista() {
		return tipoLista;
	}

	public void setTipoLista(TipoLista tipoLista) {
		this.tipoLista = tipoLista;
	}


	
	

	

}
