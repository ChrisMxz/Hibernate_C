package com.david.hibernate.entidades;

import java.util.Objects;

public class Pais {
	//variables
	private String c_Pais;
	private String descripcion;
	
	//Constructores
	public Pais() {
	}
	
	
	
	public Pais(String c_Pais, String descripcion) {
		this.c_Pais = c_Pais;
		this.descripcion = descripcion;
	}



	//setters and getters
	public String getC_Pais() {
		return c_Pais;
	}
	
	public void setC_Pais(String c_Pais) {
		this.c_Pais = c_Pais;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public String toString() {
		return "Pais [c_Pais=" + c_Pais + ", descripcion=" + descripcion + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(c_Pais, descripcion);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pais other = (Pais) obj;
		return Objects.equals(c_Pais, other.c_Pais) && Objects.equals(descripcion, other.descripcion);
	}
	
	
	
	
	

}
