package clases;

import java.time.LocalDate;
import java.util.ArrayList;

public class Auxiliar extends Trabajador {

	private Cargo cargo;
	private ArrayList<String> listaIdiomas;
	
	public Auxiliar(String dni, String nombre, String apellidos, LocalDate fechNac, LocalDate fechAlta, Cargo cargo,ArrayList<String> listaIdiomas) 
	{
		super(dni, nombre, apellidos, fechNac, fechAlta);
		this.cargo = cargo;
		this.listaIdiomas = listaIdiomas;
	}

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	public ArrayList<String> getListaIdiomas() {
		return listaIdiomas;
	}

	public void setListaIdiomas(ArrayList<String> listaIdiomas) {
		this.listaIdiomas = listaIdiomas;
	}

	@Override
	public String toString() {
		return super.toString()+"Auxiliar [cargo=" + cargo + ", listaIdiomas=" + listaIdiomas + "]";
	}
}
