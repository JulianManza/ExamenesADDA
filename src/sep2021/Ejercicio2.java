package sep2021;

import java.util.List;
import java.util.Map;

import us.lsi.common.List2;
import us.lsi.common.Map2;
import us.lsi.tiposrecursivos.Tree;

public class Ejercicio2 {

	public static Map<Integer, List<Integer>> agrupaMultiplos(Tree<Integer> t, List<Integer> l) {
		Map<Integer, List<Integer>> ac = Map2.empty();
		agrupaMultiplos(t, l, ac);
		return null;
	}

	private static void agrupaMultiplos(Tree<Integer> t, List<Integer> l, Map<Integer, List<Integer>> ac) {
		if (!t.isEmpty()) {
			// si no es vacio 
			for (Integer n : l) {
				if (t.getLabel() % n == 0) {
					if (!ac.containsKey(n)) {
						ac.put(n, List2.empty());
					}
					ac.get(n).add(t.getLabel());
				}
			}
		}
		if (t.isNary()) {
			for (Tree<Integer> child : t.getChildren()) {
				agrupaMultiplos(child, l, ac);
			}
		}
	}

}
