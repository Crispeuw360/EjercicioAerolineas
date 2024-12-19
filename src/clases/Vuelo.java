package clases;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import excepcion.*;

public class Vuelo {

	private String id;
	private String destino;
	private String origen;
	private LocalDate fechInicio;
	private LocalDate fechFin;
	private String tipoAvion;
	
	public Vuelo(String id, String destino, String origen, LocalDate fechInicio, LocalDate fechFin, String tipoAvion)throws OrigenInvalido
	{
		validarOrigen(origen);
		this.id = generarId(origen,destino);
		this.destino = destino;
		this.origen = origen;
		this.fechInicio = fechInicio;
		this.fechFin = fechFin;
		this.tipoAvion = tipoAvion;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
		this.id= generarId(this.origen,destino);
	}

	public String getOrigen() {
		return origen;
	}

	public void setOrigen(String origen)throws OrigenInvalido {
		validarOrigen(origen);
		this.origen = origen;
		this.id= generarId(origen,this.destino);
	}

	public LocalDate getFechInicio() {
		return fechInicio;
	}

	public void setFechInicio(LocalDate fechInicio) {
		this.fechInicio = fechInicio;
	}

	public LocalDate getFechFin() {
		return fechFin;
	}

	public void setFechFin(LocalDate fechFin) {
		this.fechFin = fechFin;
	}

	public String getTipoAvion() {
		return tipoAvion;
	}

	public void setTipoAvion(String tipoAvion) {
		this.tipoAvion = tipoAvion;
	}

	@Override
	public String toString() {
		return "Vuelo [id=" + id + ", destino=" + destino + ", origen=" + origen + ", fechInicio=" + fechInicio
				+ ", fechFin=" + fechFin + ", tipoAvion=" + tipoAvion + "]";
	}
	
	private String generarId(String origen, String destino) 
	{
        String origenId = origen.toUpperCase().substring(0, Math.min(origen.length(), 3));
        String destinoId = destino.toUpperCase().substring(0, Math.min(destino.length(), 3));
        return origenId + "/" + destinoId;
    }
	 private void validarOrigen(String origen) throws OrigenInvalido
	 {
        Pattern modelo = Pattern.compile("[a-zA-Z]{5,10}");
        Matcher mather = modelo.matcher(origen);
        if (!mather.matches()) 
        {
            throw new OrigenInvalido("El origen '" + origen + "' no es válido. Debe contener entre 5 y 10 letras.");
        }
    }
	
}
