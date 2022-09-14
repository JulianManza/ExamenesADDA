package sep2021;

import java.util.List;

import us.lsi.graphs.virtual.VirtualVertex;

public record MudanzaVertex() implements  VirtualVertex<MudanzaVertex, MudanzaEdge, Integer> {

	@Override
	public List<Integer> actions() {
		return null;
	}

	@Override
	public MudanzaVertex neighbor(Integer a) {
		return null;
	}

	@Override
	public MudanzaEdge edge(Integer a) {
		return null;
	}

}
