package jun2122;

import java.util.List;

import data.DatosPAP;
import data.SolucionPAP;
import us.lsi.ag.BinaryData;

public class PAP_AG implements BinaryData<SolucionPAP> {

	public PAP_AG(String file) {
		DatosPAP.iniDatos(file);
		DatosPAP.toConsole();
	}

	public static PAP_AG create(String file) {
		return new PAP_AG(file);
	}

	@Override
	public Integer size() {
		return DatosPAP.getNumProfesores() * DatosPAP.getNumAsignaturas();
	}

	@Override
	public Double fitnessFunction(List<Integer> cr) {
		SolucionPAP s = SolucionPAP.create(cr);
		Double penalizacion = 3. * cr.size();

		return s.objetivo - penalizacion * (s.errorCTS + s.errorPreferencia + s.errorFranja);
	}

	@Override
	public SolucionPAP solucion(List<Integer> value) {
		return SolucionPAP.create(value);
	}

}
