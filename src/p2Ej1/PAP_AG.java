package p2Ej1;

import java.util.List;

import us.lsi.ag.BinaryData;

public class PAP_AG implements BinaryData<SolucionPAP> {

	//private Double obj = DatosPAP.getNumProfesores()*3.0;

	public PAP_AG(String file) {
		DatosPAP.iniDatos(file);
		DatosPAP.toConsole();
	}
	
	public static PAP_AG create(String file) {
		return new PAP_AG(file);
	}

	@Override
	public Integer size() {
		return DatosPAP.getNumProfesores()*DatosPAP.getNumAsignaturas();
	}

	@Override
	public Double fitnessFunction(List<Integer> value) {
		return 5.0;
	}

	@Override
	public SolucionPAP solucion(List<Integer> value) {
		return SolucionPAP.create(value);
	}

}
