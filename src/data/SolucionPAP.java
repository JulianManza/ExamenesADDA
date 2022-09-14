package data;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import data.DatosPAP.Asignatura;
import data.DatosPAP.Profesor;
import us.lsi.common.List2;
import us.lsi.common.Map2;
import us.lsi.common.String2;

public class SolucionPAP {

	private static Map<Asignatura, List<Profesor>> solve;
	private List<Integer> ectsAcubrir, mulProf;
	public Double objetivo, errorCTS, errorFranja, errorPreferencia;
  public static List<Integer> ls = List2.empty();
	public static SolucionPAP create(List<Integer> ls) {
		return new SolucionPAP(ls);
	}

	private SolucionPAP(List<Integer> ls) {
		solve = Map2.empty();
		this.objetivo = 0.;
		this.errorCTS = 0.;
		this.errorFranja = 0.;
		this.errorPreferencia = 0.;

		IntStream.range(0, ls.size()).boxed().forEach(i -> mapeo(ls.get(i), i));
		List<Profesor> profSelec = profesoresSeleccionados();
		multiplicidadProfesoresSeleccionados(profSelec);
		errorECTS();
		errorFranja();

	}

	private void multiplicidadProfesoresSeleccionados(List<Profesor> profSelec) {
		this.mulProf = DatosPAP.profesores.stream().map(m -> Collections.frequency(profSelec, m)).toList();
	}

	private List<Profesor> profesoresSeleccionados() {
		List<Profesor> profSelec = solve.entrySet().stream().flatMap(f -> f.getValue().stream().map(m -> m))
				.collect(Collectors.toList());
		return profSelec;
	}

	private void errorFranja() {
		Map<Object, List<Object>> mapFranjas = solve.entrySet().stream().collect(Collectors.groupingBy(
				a -> a.getKey().franja(), Collectors.flatMapping(e -> e.getValue().stream(), Collectors.toList())));

		errorFranja = (double) mapFranjas.values().stream()
				.mapToInt(p -> (int) p.stream().filter(f -> Collections.frequency(p, f) > 1).count()).sum();
	}

	private void errorECTS() {
		this.ectsAcubrir = solve.entrySet().stream()
				.map(a -> a.getKey().creditos() - a.getValue().stream()
						.mapToInt(p -> p.creditos() / mulProf.get(Integer.valueOf(p.nombre().split("_")[1]))).sum())
				.toList();

		this.errorCTS = (double) ectsAcubrir.stream().mapToInt(p -> Math.abs(p)).sum()
				+ mulProf.stream().filter(f -> f == 0).count();
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
			errorPreferencia(i, j);
			goal(i, j);
		}
		return index++;
	}

	private void goal(Integer i, Integer j) {
		this.objetivo += DatosPAP.getPreferencia(i, j) * DatosPAP.getECTSProfesor(i);
	}

	private void errorPreferencia(Integer i, Integer j) {
		this.errorPreferencia += DatosPAP.getPreferencia(i, j) < 3
				? DatosPAP.getPreferencia(i, j) == 0 ? 6.0 : -DatosPAP.getPreferencia(i, j) + 3.0
				: 0.0;
	}

	public Map<Asignatura, List<Profesor>> getSolucion() {
		return solve;
	}

	@Override
	public String toString() {
		List<String> solucion = solve.entrySet().stream().sorted(Comparator.comparing(k -> k.toString()))
				.map(a -> a.getKey().nombre() + ": "
						+ a.getValue().stream().map(p -> p.nombre() + "("
								+ (p.creditos() / mulProf.get(Integer.valueOf(p.nombre().split("_")[1])) + "ECTS)"))
								.toList())
				.toList();
		String2.toConsole(solucion, "Solucion");
		return "";
	}
}
