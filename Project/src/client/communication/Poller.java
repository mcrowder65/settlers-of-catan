package client.communication;

import java.util.Timer;
import java.util.TimerTask;

import client.data.GameManager;
/**
 * The Poller class periodically checks the proxy
 * for a more recent model,
 * updating the given model if it is behind.
 */
public class Poller {


	/**
	 * The Game Manager that is used for updating
	 * the model and calling the proxy's checkServerVersion method. 
	 */
	private GameManager game;
	/**
	 * The internal timer for the poller.
	 */
	private Timer timer;
	private boolean isPolling;
	
	private PollingTask pollingTask;
	/**
	 * The time (in seconds) between invoking
	 * the timer's TimerTask run method
	 */
	private int interval;
	
	/**
	 * A wrapper for the TimerTask class
	 */
	class PollingTask extends TimerTask {
		/**
		 * The method that will be called every x seconds,
		 * where x is TIMER_FREQUENCY.
		 * Calls the proxy's checkServerVersion method.
		 */
		@Override
		public void run() {
		
			int localVersion = game.getModel().getVersion();
			int remoteVersion = checkVersion();
			if (localVersion < remoteVersion) {
				game.updateModel();
				
			}
			
		}
	}
	
	/**
	 * Gets the current server's version as defined
	 * by the proxy.
	 * @return Returns the current server's version.
	 */
	private int checkVersion() {
		game.getProxy().checkVersion();
		return 0;
	}
	
	
	/**
	 * Creates a new Poller object.
	 * @param game
	 * The GameManager that will be used to 
	 * access the proxy and update the model
	 * @param interval
	 * The periodic interval (in seconds)
	 * that the poller will poll the proxy.
	 * @throws IllegalArgumentException
	 * Throws this exception if GameManager is null,
	 * or interval is invalid (i.e. <= 0)
	 */
	public Poller(GameManager game, int interval) throws IllegalArgumentException {
		setGame(game);
		setInterval(interval);
		isPolling = false;
		
	}
	
	
	public int getInterval() { return interval;}
	/**
	 * Sets the new timer interval.
	 * @param interval
	 * The new interval (in seconds) which to poll at.
	 * @throws IllegalArgumentException
	 * Throws this error if interval is an invalid value (i.e. <= 0)
	 */
	public void setInterval(int interval) throws IllegalArgumentException {
			if (interval <= 0) throw new IllegalArgumentException("Interval cannot be less than or equal to 0");
			
			if (isPolling) 
			{
				stopPolling();
				this.interval = interval;
				startPolling();
			} 
			else 
			{
				this.interval = interval;
			}
			
	
	}
	public GameManager getGame() {
		return game;
	}
	
	/**
	 * Sets the game manager.
	 * @param game
	 * @throws IllegalArgumentException
	 * Throws this error if GameManager is null.
	 */
	public void setGame(GameManager game) throws IllegalArgumentException {
			if (game == null) throw new IllegalArgumentException("Game cannot be null.");
			if (game.getProxy() == null) throw new IllegalArgumentException("Game's proxy cannot be null.");
			this.game = game;
	
	}
	/**
	 * Begins the poller.
	 */
	public void startPolling() {
		if (isPolling) return;
		isPolling = true;
		timer = new Timer();
		pollingTask = new PollingTask();
		timer.schedule(pollingTask, interval * 1000);
		
	}
	/**
	 * Stops the poller.
	 */
	public void stopPolling() {
		if (!isPolling) return;
		isPolling = false;
		timer.cancel();
	}
	
	
}
