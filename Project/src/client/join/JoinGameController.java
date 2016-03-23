package client.join;

import shared.communication.response.GetModelResponse;
import shared.definitions.CatanColor;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import client.base.*;
import client.communication.HTTPProxy;
import client.controller.Facade;
import client.data.*;
import client.misc.*;
import client.utils.DataUtils;


/**
 * Implementation for the join game controller
 */
public class JoinGameController extends Controller implements IJoinGameController {

	private INewGameView newGameView;
	private ISelectColorView selectColorView;
	private IMessageView messageView;
	private IAction joinAction;
	private Facade facade;
	private PlayerInfo player;
	private int currentSelectedGameId = -1;
	private Object listGameLock = new Object();
	private Timer timer;
	private PollingTask pollingTask;
	private int interval;
	private GameInfo[] currentGames; //holds all the games
	private boolean isFinished = false;
	/**
	 * JoinGameController constructor
	 * 
	 * @param view Join game view
	 * @param newGameView New game view
	 * @param selectColorView Select color view
	 * @param messageView Message view (used to display error messages that occur while the user is joining a game)
	 */
	public JoinGameController(IJoinGameView view, INewGameView newGameView, 
								ISelectColorView selectColorView, IMessageView messageView,
								Facade facade) {

		super(view);

		setNewGameView(newGameView);
		setSelectColorView(selectColorView);
		setMessageView(messageView);
		
		this.facade = facade;
		interval = facade.getPollingInterval();
		player = new PlayerInfo();
	}
	/**
	 * uses the poller to update the game info on the join screen
	 */
	class PollingTask extends TimerTask {
		@Override
		public void run() {
			synchronized(listGameLock) {
				GameInfo[] info = facade.listGames();
				updateGames(info);
			}
		}
	}
	
	
	public IJoinGameView getJoinGameView() {
		
		return (IJoinGameView)super.getView();
	}
	
	/**
	 * Returns the action to be executed when the user joins a game
	 * @return The action to be executed when the user joins a game
	 */
	public IAction getJoinAction() {
		
		return joinAction;
	}

	/**
	 * Sets the action to be executed when the user joins a game
	 * @param value The action to be executed when the user joins a game
	 */
	public void setJoinAction(IAction value) {	
		
		joinAction = value;
	}
	
	public INewGameView getNewGameView() {
		
		return newGameView;
	}

	public void setNewGameView(INewGameView newGameView) {
		
		this.newGameView = newGameView;
	}
	
	public ISelectColorView getSelectColorView() {
		
		return selectColorView;
	}
	/**
	 * brings up a view to select the colors
	 * @param selectColorView
	 */
	public void setSelectColorView(ISelectColorView selectColorView) {
		
		this.selectColorView = selectColorView;
	}
	
	public IMessageView getMessageView() {
		
		return messageView;
	}
	public void setMessageView(IMessageView messageView) {
		
		this.messageView = messageView;
	}

	/**
	 * starts the joining a game process
	 */
	@Override
	public void start() {
		isFinished = false;
		player.setId(facade.getPlayerId());
		GameInfo[] info = facade.listGames(); //gets the list of games from the server object
		currentGames = info;
		getJoinGameView().setGames(currentGames,player); //updates the view with the games that it recieved
		getJoinGameView().showModal();
		
		//starts up the poller
		timer = new Timer();
		pollingTask = new PollingTask();
		timer.schedule(pollingTask, 0, interval * 1000);
	}

	/**
	 * places the games that were retrieved from the server on the view
	 * @param info
	 */
	private void updateGames(GameInfo[] info) {
		if (isGamesDifferent(info) && !isFinished && !this.getSelectColorView().isModalShowing() && !this.getNewGameView().isModalShowing()) {
			currentGames = info;
			if (getJoinGameView().isModalShowing())
				getJoinGameView().closeModal();
			getJoinGameView().setGames(currentGames,player);
			
			
			getJoinGameView().showModal();
		}
	}
	@Override
	public void startCreateNewGame() {
		getNewGameView().showModal();
	}

	@Override
	public void cancelCreateNewGame() {
		getNewGameView().setTitle("");
		getNewGameView().closeModal();
	}

	@Override
	public void createNewGame() {
		synchronized(listGameLock) {
			if(getNewGameView().getTitle().equals("")){
				showCreateGameFail("Game title cannot be empty");
				return;
			}
				
	       int gameId =	facade.createGame(
				getNewGameView().getTitle(), 
				getNewGameView().getRandomlyPlaceHexes(), 
				getNewGameView().getRandomlyPlaceNumbers(), 
				getNewGameView().getUseRandomPorts());
	       
			
	       if (gameId > -1) {
				getNewGameView().closeModal();
				
				//The color doesn't matter because we're going to re-join and pick a new one anyway
				facade.joinGame(gameId, CatanColor.red);
				
				
					GameInfo[] info = facade.listGames();
					updateGames(info);
				
			}
			else
			   showCreateGameFail("Create game failed.");
	       getNewGameView().setTitle("");
       }
	}
	@Override
	public void startJoinGame(GameInfo game) {
		currentSelectedGameId = game.getId();
		
		
		PlayerInfo[] players = facade.getPlayers(currentSelectedGameId);
		getSelectColorView().resetAll();
		//Disable colors that are already in game (unless you're rejoining)
		if(players == null){
			return;
		}
		//if player already joined as another color, make sure they still have that option.
		for (PlayerInfo p : players) {
			if (p.getId() != player.getId())
				getSelectColorView().setColorEnabled(p.getColor(), false);
		}
		
		getSelectColorView().showModal();
	}

	@Override
	public void cancelJoinGame() {
		getJoinGameView().closeModal();
	}

	@Override
	public void joinGame(CatanColor color) {
		if(!facade.canJoinGame(currentSelectedGameId, color, player.getId())){
			//showJoinGameFail("That color was likely already chosen.");
			return;
		}
		synchronized(listGameLock) {	
	       boolean success = facade.joinGame(currentSelectedGameId, color);
	  
		// If join succeeded
		  if (success) {
			getSelectColorView().closeModal();
			getJoinGameView().closeModal();
			isFinished = true;
			timer.cancel();
			facade.startPoller();
			joinAction.execute();
		  } else 
			  showJoinGameFail("Join game failed.");
	  }
	
	}
	private void showCreateGameFail(String error) {
		messageView.setTitle("Error!");
		messageView.setMessage(error);
		messageView.showModal();
	}
	private void showJoinGameFail(String error) {
		getJoinGameView().closeModal();
		messageView.setTitle("Error!");
		messageView.setMessage(error);
		messageView.showModal();
	}
	private boolean isGamesDifferent(GameInfo[] newGames) {
		if (newGames.length != currentGames.length)
			return true;
		for (int n = 0; n < currentGames.length; n++) {
			if (newGames[n].getPlayers().size() != currentGames[n].getPlayers().size())
				return true;
			for (int m = 0; m < currentGames[n].getPlayers().size(); m++) {
				if (newGames[n].getPlayers().get(m).getId() != currentGames[n].getPlayers().get(m).getId())
					return true;
			}
			
		}
		
		return false;
	}

	
}

