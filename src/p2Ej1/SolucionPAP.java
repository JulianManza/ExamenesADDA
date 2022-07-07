package p2Ej1;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import p2Ej1.DatosPAP.Asignatura;
import p2Ej1.DatosPAP.Profesor;

public class SolucionPAP {
	
	private Map<Asignatura, List<Profesor>> solve;

	public static SolucionPAP create(List<Integer> ls) {
		return new SolucionPAP(ls);
	}
	
	private SolucionPAP(List<Integer> ls) {
		int k = 0;
		Stream.iterate(0, n->n>ls.size()-1,n->n+1).forEach(s->System.out.println(s));
		//solve = ls.stream().map(i-> DatosPAP.getProfesor(k)).collect(Collectors.groupingBy(j->DatosPAP.getAsignatura(ls.get(k))));
	}

	@Override
	public String toString() {
		return solve.entrySet().stream().map(a-> a.getKey().nombre()+": "+a.getValue().toString()).toList().toString();
	}
}
