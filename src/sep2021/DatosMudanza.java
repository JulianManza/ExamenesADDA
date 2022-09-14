package sep2021;

import java.util.List;

import us.lsi.common.Files2;

public class DatosMudanza {
	
	private static List<Integer> lMuebles;
	private static List<Integer> lDias;

	public static Integer getN() {
		return lMuebles.size();
	}

	public static Integer getM() {
		return lDias.size();
	}
	
	public static Integer getTiempo(Integer i) {
		return lDias.get(i);
	}
	
	public static void iniData(String file) {
		
	}

}
