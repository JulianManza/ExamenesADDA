package jun2122;

import us.lsi.graphs.virtual.SimpleEdgeAction;

public record CineEdge(CineVertex source, CineVertex target, Integer action, Double weight) implements SimpleEdgeAction<CineVertex, Integer> {
	public static CineEdge of(CineVertex source, CineVertex target, Integer action) {
		return new CineEdge(source, target, action, CineVertex.getEdgeWeight(source, target, action));
	}

	
}
