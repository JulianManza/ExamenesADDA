package sep2122;

import java.util.List;

import us.lsi.common.List2;
import us.lsi.graphs.virtual.VirtualVertex;

public record ProductosVertex(Integer index, List<Integer> productosSeleccionados, Integer ganancias, List<Integer> frecuenciaCat) implements VirtualVertex<ProductosVertex, ProductosEdge, Integer> {

	@Override
	public List<Integer> actions() {
		List<Integer> lA = List2.empty();
		Boolean numProducto = productosSeleccionados.size()>=4;
		Boolean frecuencia = frecuenciaCat.stream().allMatch(e->e<=2);
		Boolean condGanancias = ganancias <=50;		
		
		if(numProducto && frecuencia && condGanancias) {
			lA.add(1);
		}
		
		return lA;
	}

	@Override
	public ProductosVertex neighbor(Integer a) {
		List<Integer> prodSelect = List2.copy(productosSeleccionados);
		List<Integer> frecuencias = List2.copy(frecuenciaCat);
		Integer ganancia = ganancias;
		prodSelect.set(a, prodSelect.get(a));
		
		return of(index+1, null, 0,null);
	}
	
	private ProductosVertex of(int index, List<Integer> prodSelect, Integer ganancia, List<Integer> frecuencias) {
		return new ProductosVertex(ganancia, prodSelect, ganancia, frecuencias);
	}
	@Override
	public ProductosEdge edge(Integer a) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

}
