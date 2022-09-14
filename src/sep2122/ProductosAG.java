package sep2122;

import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

import us.lsi.ag.AuxiliaryAg;
import us.lsi.ag.BinaryData;

public class ProductosAG implements BinaryData<SolucionProductos> {

	public ProductosAG(String file) {
		DatosProductos.iniDatos("ficheros/productos.txt");
		DatosProductos.toConsole();
	}

	@Override
	public Integer size() {
		return DatosProductos.getNumProductos();
	}

	@Override
	public Double fitnessFunction(List<Integer> ls) {
		List<Integer> productosSelect = IntStream.range(0, ls.size()).boxed().filter(p->ls.get(p).equals(1)).toList();
		Double goal = productosSelect.stream().mapToDouble(p-> DatosProductos.getGanancia(p)).sum();
		Double ko1 = AuxiliaryAg.distanceToGeZero(50-goal);
		Double ko2 = AuxiliaryAg.distanceToGeZero(productosSelect.size()-4.0);
		Double ko3 = penalizaXCategoria(productosSelect);
		Double ko= ko1+ko2+ko3;
		return goal - ko * 789654;
	}

	private Double penalizaXCategoria(List<Integer> ls) {
		List<Integer> lcategorias =  ls.stream().flatMap(p->DatosProductos.getCategorias(p).stream().map(x->x)).toList();
		Integer numCatMax =  ls.stream().mapToInt(p-> DatosProductos.getCategorias(p).size()).max().orElse(0);
		return IntStream.range(0, numCatMax).boxed().mapToDouble(l->Collections.frequency(lcategorias, l)).filter(e->e>2).sum();
	}

	@Override
	public SolucionProductos solucion(List<Integer> value) {
		return SolucionProductos.create(value);
	}

	public static ProductosAG create(String file) {
		return new ProductosAG(file);
	}

}
