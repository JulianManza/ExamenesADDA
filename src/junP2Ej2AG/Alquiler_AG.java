package junP2Ej2AG;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import data.DatosAlquiler;
import data.SolucionAlquiler;
import us.lsi.ag.AuxiliaryAg;
import us.lsi.ag.ValuesInRangeData;
import us.lsi.ag.agchromosomes.ChromosomeFactory.ChromosomeType;

public class Alquiler_AG implements ValuesInRangeData<Integer, SolucionAlquiler> {

	public Alquiler_AG(String file) {
		DatosAlquiler.iniDatos(file);
		DatosAlquiler.toConsole();
	}

	@Override
	public Integer size() {
		return DatosAlquiler.getNumOfertas();
	}

	@Override
	public ChromosomeType type() {
		return ChromosomeType.Range;
	}

	@Override
	public Double fitnessFunction(List<Integer> ls) {
		Double ko1 = penalizaXpresupuesto(ls);
		Double ko2 = penalizaXnecesidades(ls);
		Double ko = ko1 + ko2;
		Double objetivo = objetivo(ls);
		return objetivo - ko * objetivo;
	}

	public static Double costeTotal(List<Integer> ls) {
		return (double) IntStream.range(0, ls.size())
				.mapToLong(c -> (ls.get(c) * DatosAlquiler.getPrecio(c)) + DatosAlquiler.getDescuento(c, ls.get(c)))
				.sum();
	}

	public static Double objetivo(List<Integer> ls) {
		return (double) IntStream.range(0, ls.size()).mapToLong(c -> ls.get(c) * DatosAlquiler.getRentabilidad(c))
				.sum();

	}

	public Double penalizaXpresupuesto(List<Integer> ls) {
		return ((double) costeTotal(ls) - DatosAlquiler.getPresupuesto())<=0?0:Math.pow(costeTotal(ls), 2);
	}

	public Double penalizaXnecesidades(List<Integer> ls) {
		Map<String, Integer> necesidades = necesidades(ls);
		Map<String, Integer> necesidadesAcubrir = DatosAlquiler.getNecesidades();
		return necesidades.keySet().stream()
				.mapToDouble(
						c -> AuxiliaryAg.distanceToEqZero((double) (necesidades.get(c) - necesidadesAcubrir.get(c))))
				.sum();
	}

	private Map<String, Integer> necesidades(List<Integer> ls) {
		return IntStream.range(0, ls.size()).boxed()
				.collect(Collectors.groupingBy(DatosAlquiler::getCategoria, Collectors.summingInt(ls::get)));
	}

	@Override
	public SolucionAlquiler solucion(List<Integer> ls) {
		return SolucionAlquiler.create(ls);
	}

	@Override
	public Integer max(Integer i) {
		return DatosAlquiler.getUnidadesDisponibles(i);
	}

	@Override
	public Integer min(Integer i) {
		return 0;
	}

	public static Alquiler_AG create(String file) {
		return new Alquiler_AG(file);
	}
}
