package sep2021;

public class Ejercicio1 {
	/*
	 * public String enunciado(Integer limite) { UnaryOperator<Integer> avanza = x
	 * -> { return x+3; }; return Stream .iterate(1, x -> x <= limite, x ->
	 * avanza(x)) .filter(x -> x%2==0) .map(x -> x.toString())
	 * .collect(Collectors.joining("\n")); }
	 */
	private static Integer avanza(Integer x) {
		return x + 3;
	}

	public static String iterativo(Integer limite) {
		String res = "";
		Integer x = 0;
		while (x <= limite) {
			if (x % 2 == 0) {
				res += "\n" + x.toString();
			}
			x = avanza(x);
		}
		return res;
	}

	public static String recursivo(Integer limite) {
		String ac = "";
		Integer x = 0;
		return auxRecursivo(limite, x, ac);
	}

	private static String auxRecursivo(Integer limite, Integer x, String ac) {
		if (x <= limite) {
			if (x % 2 == 0) {
				ac += "\n" + x.toString();
			}
			ac = auxRecursivo(limite, avanza(x), ac);
		}
		return ac;
	}

	public static void main(String[] args) {
		System.out.println(iterativo(30));
		System.out.println(recursivo(30));
	}

}
