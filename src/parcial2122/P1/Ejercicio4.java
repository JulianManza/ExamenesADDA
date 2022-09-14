package parcial2122.P1;

import us.lsi.tiposrecursivos.BinaryTree;

public class Ejercicio4 {
	record Tupla(Boolean b, Integer s) {
		public static Tupla of(Boolean b, Integer s) {
			return new Tupla(b, s);
		}
	}

	public static Boolean EjercicioArbol(BinaryTree<Integer> tree) {
		return esEquiponderado(tree).b();
	}

	public static Tupla esEquiponderado(BinaryTree<Integer> tree) {
		Tupla res = Tupla.of(true, 0);
		switch (tree.getType()) {
		case Empty:
			res = Tupla.of(true, 0);
			break;
		case Leaf:
			res = Tupla.of(true, tree.getLabel());
			break;
		case Binary:
			Tupla tl = esEquiponderado(tree.getLeft());
			Tupla tr = esEquiponderado(tree.getRight());
			// b de la tupla tiene que cumplir que es true tanto por ambos lados y que la
			// etiqueta es la misma, la s sera la suma de todas las etiquetas
			res = Tupla.of(tl.b() && tr.b() && tl.s() == tr.s(), tl.s() + tr.s() + tree.getLabel());

		}
		return res;
	}
	public static void main(String[] args) {
		
	}
}
