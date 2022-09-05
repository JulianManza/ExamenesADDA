package junP2Ej2AG;

import java.util.List;
import java.util.Locale;

import data.SolucionAlquiler;
import us.lsi.ag.agchromosomes.AlgoritmoAG;
import us.lsi.ag.agstopping.StoppingConditionFactory;

public class Test {

	private static void test_AG(String fichero) {		
		Alquiler_AG prob = Alquiler_AG.create("ficheros/"+fichero);
		AlgoritmoAG<List<Integer>, SolucionAlquiler> alg = AlgoritmoAG.of(prob);
		alg.ejecuta();
		System.out.println(alg.bestSolution());		
	}	
	
	public static void main(String[] args) {
		AlgoritmoAG.POPULATION_SIZE = 2500;
		StoppingConditionFactory.NUM_GENERATIONS = 1500;
		AlgoritmoAG.CROSSOVER_RATE = 0.8;
		AlgoritmoAG.ELITISM_RATE = 0.2;
		AlgoritmoAG.MUTATION_RATE = 0.7;
		Locale.setDefault(new Locale("en", "US"));
		test_AG("alquiler.txt");
	}

}
