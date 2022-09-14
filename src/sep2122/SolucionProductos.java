package sep2122;

import java.util.List;
import java.util.stream.IntStream;

import us.lsi.common.List2;

public class SolucionProductos {
	private static List<String> solve;
	private static Integer gananciasTotales;

	public SolucionProductos(List<Integer> value) {
		solucion(value);
	}

	public void solucion(List<Integer> ls) {
		solve = List2.empty();
		gananciasTotales =0;
		List<Integer> listaProdSel =  IntStream.range(0, ls.size()).boxed().filter(p->ls.get(p).equals(1)).toList();
		gananciasTotales = listaProdSel.stream().mapToInt(p->DatosProductos.getGanancia(p)).sum();
		solve = listaProdSel.stream().map(p-> "Producto: " + p).toList();		
	}

	public static SolucionProductos create(List<Integer> value) {
		return new SolucionProductos(value);
	}

	@Override
	public String toString() {
		return "Productos seleccionados: \n + " + solve.toString() + "Ganancias Totales: "+gananciasTotales;
	}
	
	

}
