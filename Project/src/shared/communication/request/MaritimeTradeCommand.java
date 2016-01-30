package shared.communication.request;

import shared.definitions.ResourceType;

public class MaritimeTradeCommand extends MoveCommand {

	int ratio;
	ResourceType input;
	ResourceType output;
	
	public MaritimeTradeCommand(int playerIndex, int ratio, ResourceType input, ResourceType output)
			throws IllegalArgumentException {
		super(playerIndex);
		
		if(ratio < 2 || ratio > 4) throw new IllegalArgumentException("Ratio can't be a number other than 2,3, or 4");
		if(input == null) throw new IllegalArgumentException("Input Resource can't be NULL");
		if(output == null) throw new IllegalArgumentException("Output Resurce can't be NULL");
		if(input == ResourceType.NONE) throw new IllegalArgumentException("Input Resource can't be None");
		if(output == ResourceType.NONE) throw new IllegalArgumentException("Output Resurce can't be None");
		
		this.ratio = ratio;
		this.input = input;
		this.output = output;
		
	}

	@Override
	public String getMoveType() {
		return "maritimeTrade";
	}
	public ResourceType getInput() {
		return input;
	}
	public ResourceType getOutput() {
		return output;
	}
}
