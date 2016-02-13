package client.gamestate;

public abstract class GameState {

	public GameState identifyState(String state){
		
		GameState gameState = new IsNotTurnState();
		
		if(state.equals("Discarding")) gameState = new DiscardingState();
		else if(state.equals("First Round")) gameState = new FirstRoundState();
		else if(state.equals("Is Not Turn")) gameState = new IsNotTurnState();
		else if(state.equals("Playing")) gameState = new PlayingState();
		else if(state.equals("Robbing")) gameState = new RobbingState();
		else if(state.equals("Rolling")) gameState = new RollingState();
		else if(state.equals("Second Round")) gameState = new SecondRoundState();
		
		return gameState;
	}
	
}
