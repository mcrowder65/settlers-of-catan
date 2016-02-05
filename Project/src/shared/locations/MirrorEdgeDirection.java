package shared.locations;

public enum MirrorEdgeDirection {
	NW, N, NE, SE, S, SW;
	
	public static MirrorEdgeDirection getMirrorEdge(EdgeDirection edge) {
		switch (edge) {
		case NorthWest:
			return NW;
		case North:
			return N;
		case NorthEast:
			return NE;
		case SouthEast:
			return SE;
		case South:
			return S;
		case SouthWest:
			return SW;
		default:
			return SW;
		}
	}
	
	public static EdgeDirection getOriginal(MirrorEdgeDirection edge) {
		switch (edge) {
		case NW:
			return EdgeDirection.NorthWest;
		case N:
			return EdgeDirection.North;
		case NE:
			return EdgeDirection.NorthEast;
		case SE:
			return EdgeDirection.SouthEast;
		case S:
			return EdgeDirection.South;
		case SW:
			return EdgeDirection.SouthWest;
		default:
			return EdgeDirection.SouthWest;
		}
	}
}
