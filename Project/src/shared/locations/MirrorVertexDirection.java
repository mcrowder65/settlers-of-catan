package shared.locations;

public enum MirrorVertexDirection {
	W, NW, NE, E, SE, SW;
	
	public static MirrorVertexDirection getMirrorEdge(VertexDirection vertex) {
		switch (vertex) {
		case NorthWest:
			return NW;
		case West:
			return W;
		case NorthEast:
			return NE;
		case SouthEast:
			return SE;
		case East:
			return E;
		case SouthWest:
			return SW;
		default:
			return SW;
		}
	}
	
	public static VertexDirection getOriginal(MirrorVertexDirection vertex) {
		switch (vertex) {
		case NW:
			return VertexDirection.NorthWest;
		case W:
			return VertexDirection.West;
		case NE:
			return VertexDirection.NorthEast;
		case SE:
			return VertexDirection.SouthEast;
		case E:
			return VertexDirection.East;
		case SW:
			return VertexDirection.SouthWest;
		default:
			return VertexDirection.SouthWest;
		}
	}
}
