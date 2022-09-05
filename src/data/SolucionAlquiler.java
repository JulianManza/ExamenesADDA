package data;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import junP2Ej2AG.Alquiler_AG;
import us.lsi.common.List2;
import us.lsi.common.Map2;
import us.lsi.common.String2;

public class SolucionAlquiler {

	Map<String, List<String>> solucion;
	Double coste;
	Double rentabilidad;
	List<Integer> crom;

	private SolucionAlquiler(List<Integer> ls) {
		solucion(ls);
	}

	public Map<String, List<String>> solucion(List<Integer> ls) {
		solucion = Map2.empty();
		DatosAlquiler.getCategorias().stream().forEach(c -> solucion.put(c, listaCoches(c, ls)));
		coste = Alquiler_AG.costeTotal(ls);
		rentabilidad = Alquiler_AG.objetivo(ls);
		crom= List2.copy(ls);
		return solucion;
	}

	private List<String> listaCoches(String c, List<Integer> ls) {
		List<String> ls2 = IntStream.range(0, ls.size()).boxed().filter(f -> DatosAlquiler.getCategoria(f).equals(c))
				.filter(f -> ls.get(f) > 0).flatMap(e -> repCoche(DatosAlquiler.getCoche(e), ls.get(e)).stream())
				.collect(Collectors.toList());
		List<String> ls3 = IntStream.range(0, ls2.size()).boxed().map(e -> ls2.get(e) + e).collect(Collectors.toList());

		return ls3;
	}

	private List<String> repCoche(String coche, Integer rep) {
		return IntStream.range(0, rep).boxed().map(j -> coche).collect(Collectors.toList());
	}

	public static SolucionAlquiler create(List<Integer> ls) {
		return new SolucionAlquiler(ls);
	}

	@Override
	public String toString() {
		List<String> solve = solucion.entrySet().stream()
				.map(k -> k.getKey().toString() + ": " + k.getValue().toString() ).toList();
		String2.toConsole(solve, "Solucion");
		System.out.println("\nCoste Total despues de descuento:" + coste + "\nRentabilidad Total:" + rentabilidad + crom);
		return "";
	}

}
