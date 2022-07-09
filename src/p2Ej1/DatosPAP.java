package p2Ej1;

import java.util.List;

import us.lsi.common.Files2;
import us.lsi.common.List2;

public class DatosPAP {

	public static List<Asignatura> asignaturas;
	public static List<Profesor> profesores;

	public record Asignatura(String nombre, Integer creditos, Integer franja) {
		private static Asignatura create(String s) {
			String[] tokens = s.split("; ");
			String nombre = tokens[0].trim();
			Integer creditos = Integer.valueOf(tokens[1].split("=")[1].trim());
			Integer franja = Integer.valueOf(tokens[2].split("=")[1].replace(";", "").trim());
			return new Asignatura(nombre, creditos, franja);
		}

		public String toString() {
			return String.format("%s: %s; %s;", nombre(), creditos(), franja());
		}
	}

	public record Profesor(String nombre, Integer creditos, List<Integer> preferencias) {
		private static Profesor create(String s) {
			String[] tokens = s.split(";");
			String nombre = tokens[0].trim();
			Integer creditos = Integer.valueOf(tokens[1].split("=")[1].trim());
			List<Integer> preferencias = List2.parse(tokens[2].split("=")[1].replace(";", "").trim().split(","),
					Integer::valueOf);
			return new Profesor(nombre, creditos, preferencias);
		}

		public String toString() {
			return String.format("%s (%sECTS)", nombre(), creditos(), preferencias());
		}
	}

	public static void iniDatos(String file) {

		profesores = Files2.streamFromFile(file).filter(l -> !l.startsWith("// ") && l.startsWith("PROF"))
				.map(p -> Profesor.create(p)).toList();
		asignaturas = Files2.streamFromFile(file).filter(l -> !l.startsWith("// ") && !l.isEmpty() && !l.startsWith("PROF"))
				.map(p -> Asignatura.create(p)).toList();
	}

	// Número de asignaturas a repartir.
	public static Integer getNumAsignaturas() {
		return asignaturas.size();
	}

	// Número de profesores en el reparto.
	public static Integer getNumProfesores() {
		return profesores.size();
	}

	// Número créditos a cubrir de la asignatura j-ésima.
	public static Integer getECTSAsignatura(Integer j) {
		return asignaturas.get(j).creditos();
	}

	// Número de créditos a impartir por el profesor i-ésimo..
	public static Integer getECTSProfesor(Integer i) {
		return profesores.get(i).creditos();
	}

	// Franja horaria en la que se imparte la asignatura j-ésima.
	public static Integer getFranjaHoraria(Integer j) {
		return asignaturas.get(j).franja();
	}

	// Preferencia del profesor iésimo para impartir la asignatura j-ésima.
	public static Integer getPreferencia(Integer i, Integer j) {
		return profesores.get(i).preferencias().get(j);
	}

	// Número de franjas horarias distintas.
	public static Integer getNumFranjasHorarias() {
		return (int) asignaturas.stream().map(a -> a.franja()).distinct().count();
	}
	
	// Profesor i-ésimo.
	public static Profesor getProfesor(Integer i) {
		return profesores.get(i);
	}
	
	// Asignatura j-ésima.
	public static Asignatura getAsignatura(Integer j) {
		return asignaturas.get(j);
	}
	
	public static void toConsole() {
		System.out.println("Profesores: \n");
		profesores.forEach(System.out::println);
		System.out.println("Asignaturas: \n");
		asignaturas.forEach(System.out::println);
	}

}
