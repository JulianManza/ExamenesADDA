package jun2122;

import java.util.stream.IntStream;

public class CineHeuristic {
	
	public static Double heuristic(CineVertex v) {
		return IntStream.range(v.index(), DatosCine.getN())
		 .mapToDouble(i -> maximo(i, v.preRes())).sorted()
		 .skip(DatosCine.getN() - v.index() - v.numRes()).sum();
		}
		private static Double maximo(int i, Double presRest) {
		int weeks = IntStream.range(1, 7)
		.filter(j -> DatosCine.getPrecio(i, j)<=presRest).max().orElse(0);
		return DatosCine.getTaquilla(i, weeks);
		}

}
