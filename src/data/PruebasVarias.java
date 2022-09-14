package data;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class PruebasVarias {

	public static List<Integer> frequencyMap(List<Integer> ls) {
		return ls.stream().collect(Collectors.groupingBy(x -> x, Collectors.counting())).values().stream()
				.map(x -> x.intValue()).toList();
	}

	public static void main(String[] args) {
		List<Integer> ls = List.of(1, 2, 3, 4, 5, 6, 1, 2, 3, 5, 5, 5);
		ls.stream().sorted();

		List<Integer> lf = ls.stream().map(m -> Collections.frequency(ls, m)).collect(Collectors.toList());
		System.out.println(ls);
		System.out.println(lf);
		System.out.println(frequencyMap(ls));
	}

}
