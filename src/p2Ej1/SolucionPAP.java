package p2Ej1;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import p2Ej1.DatosPAP.Asignatura;
import p2Ej1.DatosPAP.Profesor;
import us.lsi.ag.AuxiliaryAg;
import us.lsi.common.List2;
import us.lsi.common.Map2;

public class SolucionPAP {

	private static Map<Asignatura, List<Profesor>> solve;
	private List<Integer> ctsRestantes;
	public Double objetivo, errorCTS, errorFranja, errorPreferencia;

	public static SolucionPAP create(List<Integer> ls) {
		return new SolucionPAP(ls);
	}

	private SolucionPAP(List<Integer> ls) {
		solve = Map2.empty();
		this.objetivo = 0.;
		this.errorCTS = 0.;
		this.errorFranja = 0.;
		this.errorPreferencia = 0.;
		Double error = (int)ls.stream().filter(f->f==1).count()<DatosPAP.getNumProfesores()?10.0:0.0;
		this.ctsRestantes = IntStream.range(0, DatosPAP.getNumAsignaturas()).boxed()
				.map(p -> DatosPAP.getECTSAsignatura(p)).collect(Collectors.toList());
		IntStream.range(0, ls.size()).boxed().forEach(i -> mapeo(ls.get(i), i));
		this.errorCTS = error + AuxiliaryAg.distanceToEqZero((double)ctsRestantes.stream().mapToInt(e -> e).filter(f->f!=0).count()) + AuxiliaryAg.distanceToEqZero((double)ctsRestantes.stream().mapToInt(e -> e).filter(f->f!=0).sum());
		Map<Object, List<Object>> mp = solve.entrySet().stream().collect(Collectors.groupingBy(a -> a.getKey().franja(),
				Collectors.flatMapping(e -> e.getValue().stream(), Collectors.toList())));
		errorFranja =(double) mp.values().stream().mapToInt(p-> (int) p.stream().filter(f->Collections.frequency(p, f)>1).count()).sum();
	}

	private Integer mapeo(Integer value, Integer index) {
		Integer i = index % DatosPAP.getNumProfesores();
		Integer j = index / DatosPAP.getNumProfesores();
		Profesor prof = DatosPAP.getProfesor(i);
		Asignatura asig = DatosPAP.getAsignatura(j);
		if (value == 1) {
			if (solve.containsKey(DatosPAP.getAsignatura(j))) {
				solve.get(asig).add(prof);
			} else {
				List<Profesor> profesores = List2.empty();
				profesores.add(prof);
				solve.put(asig, profesores);
			}
			this.ctsRestantes.set(j, ctsRestantes.get(j) - DatosPAP.getECTSProfesor(i));
			this.errorPreferencia += DatosPAP.getPreferencia(i, j) == 0 ? 1.0 : 0.0;
			this.objetivo += DatosPAP.getPreferencia(i, j)*DatosPAP.getECTSProfesor(i);
		}
		return index++;
	}

	public Map<Asignatura, List<Profesor>> getSolucion() {
		return solve;
	}

	@Override
	public String toString() {
		return "\nSolucion: \n"
				+ solve.entrySet().stream().map(a -> "\t " + a.getKey().nombre() + ":" + a.getValue().toString())
						.toList().stream().collect(Collectors.joining("\n"));
	}
}
