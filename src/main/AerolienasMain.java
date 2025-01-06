package main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import clases.*;
import excepcion.OrigenInvalido;
import utilidades.Utilidades;

public class AerolienasMain {

	public static int mostrarMenu() {
		System.out.println("1. Alta trabajador a partir del DNI (Piloto/Auxiliar)");
		System.out.println("2. Listado trabajadores indicando si es piloto o mostrando su cargo");
		System.out.println("3. Añadir vuelo/s a un piloto");
		System.out.println("4. Añadir idioma/s a un auxiliar");
		System.out.println("5. Listado de los vuelos de un piloto concreto a partir de su DNI");
		System.out.println("6. Listado de los vuelos realizados entre dos fechas");
		System.out.println("7. Listado de los pilotos que han participado en los vuelos de un destino concreto");
		System.out.println("8. Listado de los auxiliares que se manejan en un idioma concreto");
		System.out.println("9. Listado de los trabajadores ordenados por antigüedad");
		System.out.println("10. Listado de los pilotos ordenados por horas de vuelo");
		System.out.println("11. Cálculo de duración media de vuelos y detalles de vuelos extremos");
		System.out.println("12. Listado agrupado por destinos y nº de vuelos en un mes y origen");
		System.out.println("13. Mostrar piloto con más antigüedad y auxiliar más joven");
		System.out.println("14. Listado de los pilotos con residencia en un lugar concreto");
		System.out.println("15. Cálculo de edad y antigüedad de un trabajador concreto");
		System.out.println("16. Modificar el cargo de un auxiliar concreto");
		System.out.println("17. Modificar un vuelo de un piloto");
		System.out.println("18. Listado de los auxiliares con un cargo concreto");
		System.out.println("19. Media de edad de los pilotos con una residencia concreta");
		System.out.println("20. Estadística de edad de los pilotos");
		System.out.println("21. Salir");
		return Utilidades.leerInt();
	}

	public static void altaTrabajador(HashMap<String, Trabajador> mapa) {
		String dni, nombre, apellidos;
		LocalDate fechNac, fechAlta;
		char tipoTrabajador;
		LocalDate fechLicencia;
		String residencia;
		Cargo cargo;
		ArrayList<String> listaIdiomas;
		ArrayList<Vuelo> listaVuelos;

		System.out.println("Introduce el DNI del trabajador:");
		dni = Utilidades.introducirCadena();

		if (mapa.containsKey(dni)) {
			System.out.println("Este dni Ya existe en la base de datos");
		} else {
			System.out.println("Introduce el nombre:");
			nombre = Utilidades.introducirCadena();

			System.out.println("Introduce los apellidos:");
			apellidos = Utilidades.introducirCadena();

			System.out.println("Introduce la fecha de nacimiento (dd/MM/yyyy):");
			fechNac = Utilidades.leerFechaDMA();

			System.out.println("Introduce la fecha de alta (dd/MM/yyyy):");
			fechAlta = Utilidades.leerFechaDMA();

			System.out.println("¿Es un Piloto o un Auxiliar? (P/A):");
			tipoTrabajador = Utilidades.leerChar('P', 'A');

			try {
				if (tipoTrabajador == 'P') {
					System.out.println("Introduce la fecha de licencia (dd/MM/yyyy):");
					fechLicencia = Utilidades.leerFechaDMA();

					System.out.println("Introduce la residencia oficial:");
					residencia = Utilidades.introducirCadena();

					listaVuelos = new ArrayList<>();

					Piloto piloto = new Piloto(dni, nombre, apellidos, fechNac, fechAlta, fechLicencia, residencia,
							listaVuelos);

					mapa.put(dni, piloto);

					System.out.println("Piloto añadido con éxito.");
				} else if (tipoTrabajador == 'A') {
					System.out.println("Introduce el cargo (ejemplo: SENIOR, JUNIOR, MANAGER):");
					cargo = Cargo.valueOf(Utilidades.introducirCadena().toUpperCase());

					System.out.println("Introduce los idiomas separados por comas:");
					listaIdiomas = new ArrayList<>();

					for (String idioma : Utilidades.introducirCadena().split(",")) {
						listaIdiomas.add(idioma.trim());
					}

					Auxiliar auxiliar = new Auxiliar(dni, nombre, apellidos, fechNac, fechAlta, cargo, listaIdiomas);
					mapa.put(dni, auxiliar);

					System.out.println("Auxiliar añadido con éxito.");
				}
			} catch (IllegalArgumentException e) {
				System.err.println("Error valor no valido");
			}
		}
	}

	public static void listarTrabajadores(HashMap<String, Trabajador> mapa) {
		Trabajador trabajador;
		Piloto piloto;
		Auxiliar auxiliar;
		boolean hayTrabajadores = !mapa.isEmpty();

		System.out.println("Listado de trabajadores:");

		if (!hayTrabajadores) {
			System.out.println("No hay trabajadores registrados en el sistema.");
		} else {
			for (String key : mapa.keySet()) {
				trabajador = mapa.get(key);

				if (trabajador instanceof Piloto) {
					piloto = (Piloto) trabajador;

					System.out.println("Piloto: " + piloto.getNombre() + " " + piloto.getApellidos() + " (DNI: "
							+ piloto.getDni() + ", Residencia: " + piloto.getResidenciaOficial() + ")");

				} else if (trabajador instanceof Auxiliar) {
					auxiliar = (Auxiliar) trabajador;

					System.out.println("Auxiliar: " + auxiliar.getNombre() + " " + auxiliar.getApellidos() + " (DNI: "
							+ auxiliar.getDni() + ", Cargo: " + auxiliar.getCargo() + ")");

				}
			}
		}
	}

	public static void añadirVuelos(HashMap<String, Trabajador> mapa) {
		System.out.println("Introduce el DNI del piloto:");
		String dni = Utilidades.introducirCadena();

		Trabajador trabajador = mapa.get(dni);
		if (!(trabajador instanceof Piloto)) {
			System.out.println("No existe un piloto con este DNI.");
			return;
		}

		Piloto piloto = (Piloto) trabajador;

		boolean seguir;
		do {
			System.out.println("Introduce el origen del vuelo:");
			String origen = Utilidades.introducirCadena();

			System.out.println("Introduce el destino del vuelo:");
			String destino = Utilidades.introducirCadena();

			System.out.println("Introduce la fecha de inicio del vuelo (dd/MM/yyyy):");
			LocalDate fechaInicio = Utilidades.leerFechaDMA();

			System.out.println("Introduce la fecha de fin del vuelo (dd/MM/yyyy):");
			LocalDate fechaFin = Utilidades.leerFechaDMA();

			System.out.println("Introduce el tipo de avión:");
			String tipoAvion = Utilidades.introducirCadena();

			try {
				Vuelo vuelo = new Vuelo(null, destino, origen, fechaInicio, fechaFin, tipoAvion);
				piloto.getListaVuelos().add(vuelo);
				System.out.println("Vuelo añadido: " + vuelo);
			} catch (OrigenInvalido e) {
				System.out.println("Error: " + e.getMessage());
			}

			System.out.println("¿Deseas añadir otro vuelo? (S/N):");
			char respuesta = Utilidades.leerChar('S', 'N');
			seguir = (respuesta == 'S');
		} while (seguir);
	}

	public static void añadirIdiomas(HashMap<String, Trabajador> trabajadores) {
		boolean seguir;

		System.out.println("Introduce el DNI del auxiliar:");
		String dni = Utilidades.introducirCadena();

		Trabajador trabajador = trabajadores.get(dni);
		if (!(trabajador instanceof Auxiliar)) {
			System.out.println("No existe un auxiliar con este DNI.");
			return;
		}

		Auxiliar auxiliar = (Auxiliar) trabajador;

		do {
			System.out.println("Introduce un idioma:");
			String idioma = Utilidades.introducirCadena();

			auxiliar.getListaIdiomas().add(idioma);
			System.out.println("Idioma añadido.");

			System.out.println("¿Deseas añadir otro idioma? (S/N):");
			char respuesta = Utilidades.leerChar('S', 'N');
			seguir = (respuesta == 'S');
		} while (seguir);
	}

	public static void listarVuelosPiloto(HashMap<String, Trabajador> trabajadores) {
		System.out.println("Introduce el DNI del piloto:");
		String dni = Utilidades.introducirCadena();

		Trabajador trabajador = trabajadores.get(dni);
		if (trabajador == null || !(trabajador instanceof Piloto)) {
			System.out.println("No se encontró un piloto con ese DNI.");
		} else {
			Piloto piloto = (Piloto) trabajador;
		}
	}

	public static void listarVuelosEntreFechas(HashMap<String, Trabajador> trabajadores) 
	{
		System.out.println("Listado de vuelos entre dos fechas (función no implementada aún).");
		// Lógica para listar vuelos entre fechas
	}

	public static void pilotosPorDestino(HashMap<String, Trabajador> trabajadores) 
	{
		System.out.println("Listado de pilotos por destino (función no implementada aún).");
		// Lógica para listar pilotos por destino
	}

	public static void auxiliaresPorIdioma(HashMap<String, Trabajador> trabajadores) 
	{
		System.out.println("Listado de auxiliares por idioma (función no implementada aún).");
		// Lógica para listar auxiliares por idioma
	}

	public static void listarTrabajadoresOrdenados(HashMap<String, Trabajador> trabajadores) 
	{
		System.out.println("Listado de trabajadores ordenados (función no implementada aún).");
		// Lógica para ordenar trabajadores por antigüedad
	}

	public static void pilotosPorHorasVuelo(HashMap<String, Trabajador> trabajadores) 
	{
		System.out.println("Listado de pilotos por horas de vuelo (función no implementada aún).");
		// Lógica para listar pilotos ordenados por horas de vuelo
	}

	public static void calcularDuracionMedia(HashMap<String, Trabajador> trabajadores) 
	{
		System.out.println("Cálculo de duración media (función no implementada aún).");
		// Lógica para calcular duración media de vuelos
	}

	public static void agruparPorDestinos(HashMap<String, Trabajador> trabajadores) 
	{
		System.out.println("Agrupación por destinos (función no implementada aún).");
		// Lógica para agrupar vuelos por destinos
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		HashMap<String, Trabajador> mapa = new HashMap<>();
		int opcion;

		do {
			opcion = mostrarMenu();
			switch (opcion) {
			case 1:
				altaTrabajador(mapa);
				break;
			case 2:
				listarTrabajadores(mapa);
				break;
			case 3:
				añadirVuelos(mapa);
				break;
			case 4:
				añadirIdiomas(mapa);
				break;
			case 5:
				listarVuelosPiloto(mapa);
				break;
			case 6:
				listarVuelosEntreFechas(mapa);
				break;
			case 7:
				pilotosPorDestino(mapa);
				break;
			case 8:
				auxiliaresPorIdioma(mapa);
				break;
			case 9:
				listarTrabajadoresOrdenados(mapa);
				break;
			case 10:
				pilotosPorHorasVuelo(mapa);
				break;
			case 11:
				calcularDuracionMedia(mapa);
				break;
			case 12:
				agruparPorDestinos(mapa);
				break;
			case 13:
				// Lógica para mostrar detalles
				break;
			case 14:
				// Lógica para pilotos por residencia
				break;
			case 15:
				// Lógica para cálculo de edad y antigüedad
				break;
			case 16:
				// Lógica para modificar cargo
				break;
			case 17:
				// Lógica para modificar vuelo
				break;
			case 18:
				// Lógica para auxiliares por cargo
				break;
			case 19:
				// Lógica para calcular media de edad
				break;
			case 20:
				// Lógica para estadística de edades
				break;
			case 21:
				// Finaliza el programa
				break;
			default:
				System.out.println("Opción no válida, por favor, elige una opción del 1 al 21.");
				break;
			}
		} while (opcion != 21);
	}

}
