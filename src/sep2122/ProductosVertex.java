package sep2122;

import java.util.List;

import us.lsi.common.List2;
import us.lsi.graphs.virtual.VirtualVertex;

public record ProductosVertex(Integer index, Integer gananciasRes, List<Integer> frecuenciaCat) implements VirtualVertex<ProductosVertex, ProductosEdge, Integer> {

	@Override
	public List<Integer> actions() {
		List<Integer> lA = List2.empty();
		
		return lA;
	}

	@Override
	public ProductosVertex neighbor(Integer a) {
		
		return of(index+1, 0, null);
	}
	
	private ProductosVertex of(int index, Integer ganancia, List<Integer> frecuencias) {
		return new ProductosVertex(index, ganancia, frecuencias);
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
