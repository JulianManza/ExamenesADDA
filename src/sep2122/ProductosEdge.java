package sep2122;

import us.lsi.graphs.virtual.SimpleEdgeAction;

public record ProductosEdge(ProductosVertex source, ProductosVertex target, Integer action, Double weight) implements SimpleEdgeAction<ProductosVertex, Integer> {
	public static ProductosEdge of(ProductosVertex c1, ProductosVertex c2, Integer action) {
		return new ProductosEdge(c1,c2,action, (double) c1.ganancias());
	}
}
