package sep2122;

import java.util.List;

import us.lsi.common.Files2;
import us.lsi.common.List2;
import us.lsi.common.String2;

public class DatosProductos {

	private record Producto(Integer id, Integer ganancia, List<Integer> categorias) {
		private static Producto create(String file) {
			String[] tokens = file.split(";");
			Integer id = Integer.valueOf(tokens[0]);
			Integer ganancia = Integer.valueOf(tokens[1]);
			List<Integer> categorias = List2.parse(tokens[2].replace("{", "").replace("}", "").split(","), i -> Integer.valueOf(i));
			return new Producto(id, ganancia, categorias);
		}
	}

	private static List<Producto> productos;

	public static Integer getNumProductos() {
		return productos.size();
	}

	public static Integer getGanancia(Integer i) {
		return productos.get(i).ganancia();
	}

	public static List<Integer> getCategorias(Integer i) {
		return productos.get(i).categorias();
	}
	
	public static List<Producto> getProductos(){
		return productos;
	}
	
	public static void toConsole() {
		String2.toConsole(productos, "Productos:");
	}
	
	public static void iniDatos(String fichero) {
		productos = Files2.streamFromFile(fichero).map(l -> Producto.create(l.toString())).toList();
	}
	

}
