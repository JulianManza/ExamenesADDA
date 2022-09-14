package sep2021;

import us.lsi.graphs.virtual.SimpleEdgeAction;

public record MudanzaEdge(MudanzaVertex source, MudanzaVertex target, Integer action, Double weight)
		implements SimpleEdgeAction<MudanzaVertex, Integer> {
	public static MudanzaEdge of(MudanzaVertex source, MudanzaVertex target, Integer action) {
		return new MudanzaEdge(source,target, action, 0.0);
	}

}
