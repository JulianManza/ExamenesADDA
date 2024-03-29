package parcial2122.P2;

import us.lsi.graphs.virtual.SimpleEdgeAction;

public record AsignaturasEdge(AsignaturasVertex source, AsignaturasVertex target, Integer action, Double weight) implements SimpleEdgeAction<AsignaturasVertex, Integer> {
	public static AsignaturasEdge of(AsignaturasVertex c1, AsignaturasVertex c2, Integer action) {
		return new AsignaturasEdge(c1,c2,action, (double) action*DatosAsignaturas.tiemposAsignaturas().get(action));
	}
}
