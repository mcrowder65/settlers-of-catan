package shared.communication.request;

import com.sun.net.httpserver.HttpExchange;

import shared.communication.response.GetModelResponse;
import shared.definitions.MirrorResourceType;
import shared.definitions.ResourceType;

public class MaritimeTradeCommand extends MoveCommand {

	int ratio;
	MirrorResourceType inputResource;
	MirrorResourceType outputResource;
	
	public MaritimeTradeCommand(int playerIndex, int ratio, ResourceType input, ResourceType output)
			throws IllegalArgumentException {
		super(playerIndex);
		
		if(ratio < 2 || ratio > 4) throw new IllegalArgumentException("Ratio can't be a number other than 2,3, or 4");
		if(input == null) throw new IllegalArgumentException("Input Resource can't be NULL");
		if(output == null) throw new IllegalArgumentException("Output Resurce can't be NULL");
		if(input == ResourceType.NONE) throw new IllegalArgumentException("Input Resource can't be None");
		if(output == ResourceType.NONE) throw new IllegalArgumentException("Output Resurce can't be None");
		
		this.ratio = ratio;
		this.inputResource = MirrorResourceType.getResource(input);
		this.outputResource = MirrorResourceType.getResource(output);
		this.type = "maritimeTrade";
	}
	
	public MaritimeTradeCommand(HttpExchange exchange) {
		super(exchange);
		
	}

	@Override
	public GetModelResponse execute() {
		return null;
	}


	public ResourceType getInput() {
		return MirrorResourceType.getOriginal(inputResource);
	}
	public ResourceType getOutput() {
		return MirrorResourceType.getOriginal(outputResource);
	}
}
