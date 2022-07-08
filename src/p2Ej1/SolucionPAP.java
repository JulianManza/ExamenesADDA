package p2Ej1;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import p2Ej1.DatosPAP.Asignatura;
import p2Ej1.DatosPAP.Profesor;
import us.lsi.common.List2;
import us.lsi.common.Map2;

public class SolucionPAP {

	private static Map<Asignatura, List<Profesor>> solve;
	public List<Integer> creditosRestantes;
	public Double objetivo;
	
	
	public static SolucionPAP create(List<Integer> ls) {
		return new SolucionPAP(ls);
	}

	private SolucionPAP(List<Integer> ls) {
		solve = Map2.empty();
		this.objetivo=0.;
		this.creditosRestantes = IntStream.range(0, DatosPAP.getNumProfesores()).boxed().map(p->DatosPAP.getECTSProfesor(p)).toList();
		IntStream.range(0, ls.size()).boxed().forEach(i -> mapeo(ls.get(i), i));
	}

	private Integer mapeo(Integer value, Integer index) {
		Integer i = index % DatosPAP.getNumProfesores();
		Integer j = index / DatosPAP.getNumProfesores();
		Profesor prof = DatosPAP.getProfesor(i);
		Asignatura asig = DatosPAP.getAsignatura(j);
		List<Integer> copy = List2.copy(this.creditosRestantes);
		if (value == 1) {
			if (solve.isEmpty() || !solve.containsKey(DatosPAP.getAsignatura(j))) {
				List<Profesor> profesores = List2.empty();
				profesores.add(prof);
				solve.put(asig, profesores);
			} else {
				solve.get(asig).add(prof);
			}
			copy.set(i, copy.get(i)-DatosPAP.getECTSAsignatura(j));
			this.objetivo += DatosPAP.getPreferencia(i, j); 
		}
		
		return index++;
	}

	public  Map<Asignatura, List<Profesor>> getSolucion() {
		return solve;
	}

	@Override
	public String toString() {
		return "\nSolucion: \n"
				+ solve.entrySet().stream().map(a -> "\t " + a.getKey().nombre() + ":" + a.getValue().toString())
						.toList().stream().collect(Collectors.joining("\n"));
	}
}
