package parcial2122.P1;

import java.util.Map;

import us.lsi.common.Map2;
import us.lsi.common.Pair;

public class Ejercicio1 {
	public static Integer iterativo(Integer n, Integer m) {
		Map<Pair<Integer, Integer>, Integer> ac = Map2.empty();
		Integer res = null;
		for (int i = 0; i <= n; i++) {
			for (int j = 0; j <= m; j++) {
				if (n < 4 && m < 2) {
					res = n + m * m;
				} else if (n < 4 || m < 2) {
					res = n * n + m;
				} else if (n % 2 == 0 && n >= 4 && m >= 2) {
					res = 3 * ac.get(Pair.of(n - 1, m - 1)) + 2;
				} else {
					res = ac.get(Pair.of(n - 1, m - 2)) + ac.get(Pair.of(n - 2, m - 2));
				}
				ac.put(Pair.of(n, m), res);
			}
		}
		return res;
	}

	public static Integer recursivoNoMem(Integer n, Integer m) {
		Integer res;
		if (n < 4 && m < 2) {
			res = n + m * m;
		} else if (n < 4 || m < 2) {
			res = n * n + m;
		} else if (n % 2 == 0 && n >= 4 && m >= 2) {
			res = 3 * recursivoNoMem(n - 1, m - 1) + 2;
		} else {
			res = recursivoNoMem(n - 1, m - 2) + recursivoNoMem(n - 2, m - 2);
		}
		return res;
	}

	public static Integer recursivoMem(Integer n, Integer m) {
		return auxRecursivoMem(n, m, Map2.empty());
	}

	private static Integer auxRecursivoMem(Integer n, Integer m, Map<Pair<Integer, Integer>, Integer> ac) {
		Integer res = ac.get(Pair.of(n, m));
		if (res == null) {
			if (n < 4 && m < 2) {
				res = n + m * m;
			} else if (n < 4 || m < 2) {
				res = n * n + m;
			} else if (n % 2 == 0 && n >= 4 && m >= 2) {
				res = 3 * auxRecursivoMem(n - 1, m - 1, ac) + 2;
			} else {
				res = auxRecursivoMem(n - 1, m - 2, ac) + auxRecursivoMem(n - 2, m - 2, ac);
			}
			ac.put(Pair.of(n, m), res);
		}
		return res;
	}

}
