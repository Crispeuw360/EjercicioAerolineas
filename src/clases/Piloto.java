package clases;

import java.time.LocalDate;
import java.util.ArrayList;

public class Piloto extends Trabajador {

	private LocalDate fechLicencia;
	private String residenciaOficial;
	private ArrayList<Vuelo> listaVuelos;
	
	public Piloto(String dni, String nombre, String apellidos, LocalDate fechNac, LocalDate fechAlta,LocalDate fechLicencia, String residenciaOficial, ArrayList<Vuelo> listaVuelos) 
	{
		super(dni, nombre, apellidos, fechNac, fechAlta);
		this.fechLicencia = fechLicencia;
		this.residenciaOficial = residenciaOficial;
		this.listaVuelos = listaVuelos;
	}

	public LocalDate getFechLicencia() {
		return fechLicencia;
	}

	public void setFechLicencia(LocalDate fechLicencia) {
		this.fechLicencia = fechLicencia;
	}

	public String getResidenciaOficial() {
		return residenciaOficial;
	}

	public void setResidenciaOficial(String residenciaOficial) {
		this.residenciaOficial = residenciaOficial;
	}

	public ArrayList<Vuelo> getListaVuelos() {
		return listaVuelos;
	}

	public void setListaVuelos(ArrayList<Vuelo> listaVuelos) {
		this.listaVuelos = listaVuelos;
	}

	@Override
	public String toString() {
		return super.toString()+"Piloto [fechLicencia=" + fechLicencia + ", residenciaOficial=" + residenciaOficial + ", listaVuelos="
				+ listaVuelos + "]";
	}
}
