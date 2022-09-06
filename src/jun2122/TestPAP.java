package jun2122;

import java.util.List;
import java.util.Locale;

import data.SolucionPAP;
import us.lsi.ag.agchromosomes.AlgoritmoAG;
import us.lsi.ag.agstopping.StoppingConditionFactory;

public class TestPAP {
	
	private static void test_AG(String fichero) {		
		PAP_AG prob = PAP_AG.create("ficheros/"+fichero);
		AlgoritmoAG<List<Integer>, SolucionPAP> alg = AlgoritmoAG.of(prob);
		alg.ejecuta();
		System.out.println(alg.bestSolution());		
	}	

	public static void main(String[] args) {
		AlgoritmoAG.POPULATION_SIZE = 500;
		StoppingConditionFactory.NUM_GENERATIONS = 1000;
		AlgoritmoAG.CROSSOVER_RATE = 0.8;
		AlgoritmoAG.ELITISM_RATE = 0.2;
		AlgoritmoAG.MUTATION_RATE = 0.6;
		Locale.setDefault(new Locale("en", "US"));
		test_AG("PAP.txt");
	}

}
