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
	private List<Integer> ectsAcubrir, ectsRestantes;
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
		
		this.ectsAcubrir = IntStream.range(0, DatosPAP.getNumAsignaturas()).boxed()
				.map(p -> DatosPAP.getECTSAsignatura(p)).collect(Collectors.toList());
		
		IntStream.range(0, ls.size()).boxed().forEach(i -> mapeo(ls.get(i), i));
		
		System.out.println(ectsAcubrir);
		//this.ectsAcubrir.set(j, ectsAcubrir.get(j) - DatosPAP.getECTSProfesor(i)/mulProf.get(i ); => en stream para cada prof seleccionado
		
		
		List<Profesor> profSelec = solve.entrySet().stream().flatMap(f->f.getValue().stream().map(m-> m)).collect(Collectors.toList());
		List<Integer> mulProf = DatosPAP.profesores.stream().map(m->Collections.frequency(profSelec, m)).toList();
		//
		this.errorCTS = (double) ectsAcubrir.stream().mapToInt(p-> Math.abs(p)).sum() + mulProf.stream().filter(f->f==0).count(); ;
		
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
			
			this.ectsAcubrir.set(j, ectsAcubrir.get(j) - DatosPAP.getECTSProfesor(i));
			this.errorPreferencia += DatosPAP.getPreferencia(i, j) < 3 ? DatosPAP.getPreferencia(i, j) == 0?6.0:-DatosPAP.getPreferencia(i, j)+3.0 : 0.0;
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
