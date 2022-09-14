package jun2122;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.IntStream;

import us.lsi.common.List2;
import us.lsi.graphs.virtual.VirtualVertex;

public record CineVertex(Integer index, Double preRes, Integer numRes)
		implements VirtualVertex<CineVertex, CineEdge, Integer> {

	@Override
	public List<Integer> actions() {
		int i = numRes == DatosCine.getN() - index ? 1 : 0;
		return index == DatosCine.getN() ? List2.empty()
				: numRes == 0 ? List.of(0)
						: IntStream.range(i, 7).filter(j -> DatosCine.getPrecio(index, j) <= preRes).boxed().toList();
	}
	
	public static CineVertex initial() {
		return new CineVertex(0, DatosCine.getMax(), DatosCine.getM());
	}

	public static Predicate<CineVertex> goal() {
		return v -> v.index() == DatosCine.getM();
	}

	@Override
	public CineVertex neighbor(Integer a) {
		int n = a>0?1:0;
		
		return of(index+1, preRes - DatosCine.getPrecio(index, a), numRes-n);
	}

	private CineVertex of(int i, double d, int j) {
		return new CineVertex(i, d, j);
	}

	@Override
	public CineEdge edge(Integer a) {
		return CineEdge.of(this, this.neighbor(a), a);
	}

	public static Double getEdgeWeight(CineVertex source, CineVertex target, Integer a) {
		return DatosCine.getPrecio(source.index, a);
	}

}
