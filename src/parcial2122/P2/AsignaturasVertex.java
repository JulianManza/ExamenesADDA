package parcial2122.P2;

import java.util.List;

import us.lsi.common.List2;
import us.lsi.graphs.virtual.VirtualVertex;

public record AsignaturasVertex(Integer index, List<Integer> espacioRestante)
		implements VirtualVertex<AsignaturasVertex, AsignaturasEdge, Integer> {

	@Override
	public List<Integer> actions() {
		List<Integer> alternativas = List2.empty();
		if(index < DatosAsignaturas.getNumAsignaturas()) {
			for (Integer i : espacioRestante) {
				if(espacioRestante.get(i)>= DatosAsignaturas.tiemposAsignaturas().get(index)) {
					alternativas.add(i);
				}
			}
		}
		return alternativas;
	}

	@Override
	public AsignaturasVertex neighbor(Integer a) {
		List<Integer> ls = List2.copy(espacioRestante);
		ls.set(a, ls.get(a) - DatosAsignaturas.tiemposAsignaturas().get(index));
		return of(index + 1, ls);
	}

	private AsignaturasVertex of(int index, List<Integer> espacioRestante) {
		return new AsignaturasVertex(index, espacioRestante);
	}

	public static Boolean goal(AsignaturasVertex v) {
		return v.index() == DatosAsignaturas.getNumAsignaturas();
	}

	@Override
	public AsignaturasEdge edge(Integer a) {
		return AsignaturasEdge.of(this, this.neighbor(a), a);
	}
	
	public static Double getEdgeWeight(AsignaturasVertex v1, AsignaturasVertex v2, Integer a) {
		return v1.espacioRestante().get(a) == DatosAsignaturas.limitesDias().get(a)?1.0:0.0;
	}

}
