package parcial2122.P2;

import java.util.List;

public class DatosAsignaturas {
	private static List<Integer> asignaturas;
	private static List<Integer> tiemposAsisgnaturas;
	private static List<Integer> limitesDias;
	
	public static Integer getNumAsignaturas() {
		return asignaturas.size();
	}
	
	public static List<Integer> tiemposAsignaturas(){
		return tiemposAsisgnaturas;
	}
	
	public static List<Integer> limitesDias(){
		return limitesDias;
	}

}
