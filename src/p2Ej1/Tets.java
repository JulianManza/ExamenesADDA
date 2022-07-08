package p2Ej1;

import java.util.List;
import java.util.Locale;

import us.lsi.ag.agchromosomes.AlgoritmoAG;
import us.lsi.ag.agstopping.StoppingConditionFactory;

public class Tets {
	
	private static void test(String fichero) {		
		PAP_AG prob = PAP_AG.create("ficheros/"+fichero);
		AlgoritmoAG<List<Integer>, SolucionPAP> alg = AlgoritmoAG.of(prob);
		alg.ejecuta();
		System.out.println(alg.bestSolution());		
	}	

	public static void main(String[] args) {
		AlgoritmoAG.POPULATION_SIZE = 1000;
		StoppingConditionFactory.NUM_GENERATIONS = 500;
		AlgoritmoAG.CROSSOVER_RATE = 0.8;
		AlgoritmoAG.ELITISM_RATE = 0.2;
		AlgoritmoAG.MUTATION_RATE = 0.6;
		Locale.setDefault(new Locale("en", "US"));
		test("PAP.txt");
	}

}
