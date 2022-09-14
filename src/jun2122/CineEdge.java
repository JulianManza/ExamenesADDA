package jun2122;

import us.lsi.graphs.virtual.SimpleEdgeAction;

public record CineEdge(CineVertex source, CineVertex target, Integer action, Double weight) implements SimpleEdgeAction<CineVertex, Integer> {
	public static CineEdge of(CineVertex source, CineVertex target, Integer action) {
		return new CineEdge(source, target, action, getEdgeWeight(source, target, action));
	}

	private static Double getEdgeWeight(CineVertex source2, CineVertex target2, Integer action2) {
		return null;
	}
}
