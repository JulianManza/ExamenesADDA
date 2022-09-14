package jun2122;

import java.util.List;

import us.lsi.common.Files2;
import us.lsi.common.List2;
import us.lsi.common.String2;

public class DatosCine {
	
	private record Pelicula(Integer id, Double precio, List<Double> taquillaEst, List<Double> recaudacionEst) {
		private static Pelicula create(String file) {
			String[] tokens = file.split(",");
			Integer id = Integer.valueOf(tokens[0]);
			Double precio = Double.valueOf(tokens[1]);
			List<Double> taquilla = List2.parse(tokens[2].split("{")[0].replace("}", "").split(","), Double::valueOf);
			List<Double> recaudacion = List2.parse(tokens[3].split("{")[0].replace("}", "").split(","), Double::valueOf);
			return new Pelicula(id,precio, taquilla, recaudacion);
		}
	}
	
	private static Double presupuesto;
	private static Integer n;
	private static List<Pelicula> peliculas;

	
	
	// Para saber el presupuesto del que se dispone:
	static Double getMax() {
		return presupuesto;
	}
	// Para saber el número de pelí­culas a reemplazar:
	static Integer getM() {
		return peliculas.size();
	}
	// Para saber el número total de películas ofertadas/propuestas:
	static Integer getN() {
		return n;
	}
	// Para saber la recaudación estimada de la película i durante j semanas
	static Double getTaquilla(Integer i, Integer j) {
		// Recaudación/Taquilla !=  Beneficio; Beneficio = Taquilla_Estimada - Precio
		return peliculas.get(i).taquillaEst.get(j);
	}
	// Para saber el precio de la película i durante j semanas
	static Double getPrecio(Integer i, Integer j) {
		return peliculas.get(i).recaudacionEst.get(j);
	}
	
	public static void iniDatos(String fichero) {
		peliculas = Files2.streamFromFile(fichero).map(e-> Pelicula.create(e.toString())).toList();
	}
	
	public static void toConsole() {
		String2.toConsole(peliculas, "Peliculas: ");
	}
	

}
