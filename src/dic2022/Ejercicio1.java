package dic2022;

import java.util.List;
import java.util.Map;

import us.lsi.common.List2;
import us.lsi.common.Map2;

public class Ejercicio1 {

	/*
	 * public static Map<Integer, List<String>> exam(Integer a, Integer b) { return
	 * Stream.iterate(a, c -> c <= b, d -> d + a + d%b) .filter(c -> c%10 !=
	 * b%10).map(d -> "(" + d + ")") .collect(Collectors.groupingBy(c ->
	 * c.length()-2)); }
	 */

	public static Map<Integer, List<String>> iterativo(Integer a, Integer b) {

		Map<Integer, List<String>> res = Map2.empty();

		Integer c = a;
		while (c <= b) {
			if (c % 10 != b % 10) {
				String value = "(" + c + ")";
				Integer k = value.length() - 2;
				if (res.containsKey(k)) {
					res.get(k).add(value);
				} else {
					res.put(k, List2.of(value));
				}
			}
			c += a + c % b;
		}
		return res;
	}

	public static Map<Integer, List<String>> recursivoFinal(Integer a, Integer b) {
		return auxRecursivoFinal(a, b, a, Map2.empty());
	}

	private static Map<Integer, List<String>> auxRecursivoFinal(Integer a, Integer b, Integer c,
			Map<Integer, List<String>> res) {
		if (c <= b) {
			if (c % 10 != b % 10) {
				String value = "(" + c + ")";
				Integer k = value.length() - 2;
				if (res.containsKey(k)) {
					res.get(k).add(value);
				} else {
					res.put(k, List2.of(value));
				}
			}
			auxRecursivoFinal(a, b, c + a + c % b, res);
		}
		return res;
	}
}
