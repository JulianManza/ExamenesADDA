package data;

import java.util.List;
import java.util.Map;
import java.util.Set;

import us.lsi.common.Files2;
import us.lsi.common.List2;
import us.lsi.common.Map2;
import us.lsi.common.String2;

public class DatosAlquiler {
	
	public static List<Coche> ofertas;
	private static Integer presupuesto;
	private static Map<String,Integer> necesidades;

	private record Coche(Integer id, String marca, String modelo, Integer unidades, Integer rentabilidad,
			String categoria, Integer precio, List<Integer> descuentos) {
		private static Coche create(String file) {
			String[] tokens = file.split(",");
			Integer id = Integer.valueOf(tokens[0]);
			String marca = tokens[1];
			String modelo = tokens[2];
			Integer unidades = Integer.valueOf(tokens[3]);
			Integer rentabilidad = Integer.valueOf(tokens[4]);
			String categoria = tokens[5];
			Integer precio = Integer.valueOf(tokens[6]);
			List<Integer> descuentos = List2.parse(tokens[7].replace("[", "").replace("]", "").split(";"),
					Integer::valueOf);
			return new Coche(id, marca, modelo, unidades, rentabilidad, categoria, precio, descuentos);
		}
	}

	public static Integer getPresupuesto() {
		return presupuesto;
	}

	public static Set<String> getCategorias() {
		return necesidades.keySet();
	}

	public static Map<String, Integer> getNecesidades() {
		return necesidades;
	}

	public static Integer getNumOfertas() {
		return ofertas.size();
	}

	public static String getMarca(Integer i) {
		return ofertas.get(i).marca;
	}

	public static String getCoche(Integer i) {
		return ofertas.get(i).modelo;
	}

	public static String getCategoria(Integer i) {
		return ofertas.get(i).categoria;
	}

	public static Integer getUnidadesDisponibles(Integer i) {
		return ofertas.get(i).unidades;
	}

	public static Integer getRentabilidad(Integer i) {
		return ofertas.get(i).rentabilidad;
	}

	public static Integer getPrecio(Integer i) {
		return ofertas.get(i).precio;
	}

	public static Integer getDescuento(Integer i, Integer j) {
		return j==0?0:ofertas.get(i).descuentos.get(j-1);
	}

	public static void iniDatos(String fichero) {
		List<String> lineas = Files2.linesFromFile(fichero);
		ofertas = lineas.stream().filter(l -> !l.contains("Presupuesto") && !l.isEmpty() && !l.startsWith("{"))
				.map(c -> Coche.create(c.trim())).toList();
		
		presupuesto = Integer.valueOf(lineas.stream().filter(l-> l.startsWith("P")).toList().toString().split("=")[1].replace("]", ""));
		String[] ln = lineas.stream().filter(l-> l.startsWith("{")).findFirst().get().replace("{", "").replace("}", "").replace(",", "=").split("=");
		necesidades = Map2.empty();
		for(int i =0; i<ln.length-1;i+=2) {
			necesidades.put(ln[i], Integer.valueOf(ln[i+1])); 
		}		
	}

	public static void toConsole() {
		String2.toConsole(ofertas, "Ofertas");
	}

}
