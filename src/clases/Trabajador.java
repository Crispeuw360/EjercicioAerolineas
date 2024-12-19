package clases;

import java.time.LocalDate;

public class Trabajador {

	protected String dni;
	protected String nombre;
	protected String apellidos;
	protected LocalDate fechNac;
	protected LocalDate fechAlta;
	
	public Trabajador(String dni, String nombre, String apellidos, LocalDate fechNac, LocalDate fechAlta) 
	{
		this.dni = dni;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.fechNac = fechNac;
		this.fechAlta = fechAlta;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public LocalDate getFechNac() {
		return fechNac;
	}

	public void setFechNac(LocalDate fechNac) {
		this.fechNac = fechNac;
	}

	public LocalDate getFechAlta() {
		return fechAlta;
	}

	public void setFechAlta(LocalDate fechAlta) {
		this.fechAlta = fechAlta;
	}

	@Override
	public String toString() {
		return "Trabajador [dni=" + dni + ", nombre=" + nombre + ", apellidos=" + apellidos + ", fechNac=" + fechNac
				+ ", fechAlta=" + fechAlta + "]";
	}
	
	
}
