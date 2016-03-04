package client.communication;

import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;

import client.data.GameManager;
import client.utils.DataUtils;
import shared.communication.response.GetModelResponse;
/**
 * The Poller class periodically checks the proxy
 * for a more recent model,
 * updating the given model if it is behind.
 */
public class Poller extends Observable {


	/**
	 * The Game Manager that is used for
	 * calling the checkServerVersion method. 
	 */
	private IProxy proxy;
	/**
	 * The internal timer for the poller.
	 */
	private Timer timer;
	private boolean polling;
	
	private PollingTask pollingTask;
	/**
	 * The time (in seconds) between invoking
	 * the timer's TimerTask run method
	 */
	private int interval;
	
	/**
	 * The latest version as received by the proxy.
	 */
	private int localVersion = -1;
	
	/**
	 * A wrapper for the TimerTask class
	 */
	class PollingTask extends TimerTask {
		/**
		 * The method that will be called every x seconds,
		 * where x is the interval from the constructor.
		 * Calls the proxy's getModel method.
		 */
		@Override
		public void run() {
		
			synchronized(DataUtils.modelLock) {
				GetModelResponse response = proxy.getModel();
				if (response.isSuccess() && response.isUpdated()) {
					localVersion = response.getModel().getVersion();
				    setChanged();
					notifyObservers(response.getModel());
				}
			}
			
		}
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
	public Poller(IProxy proxy, int interval) throws IllegalArgumentException {
		setProxy(proxy);
		setInterval(interval);
		polling = false;
		
	}
	
	
	public IProxy getProxy() {
		return proxy;
	}


	public void setProxy(IProxy proxy) throws IllegalArgumentException {
		if (proxy == null)
			throw new IllegalArgumentException("Proxy cannot be null.");
		this.proxy = proxy;
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
			
			if (polling) 
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
	

	/**
	 * Begins the poller.
	 */
	public void startPolling() {
		if (polling) return;
		polling = true;
		timer = new Timer();
		pollingTask = new PollingTask();
		timer.schedule(pollingTask, 0, interval * 1000);
		
		
	}
	/**
	 * Stops the poller.
	 */
	public void stopPolling() {
		if (!polling) return;
		polling = false;
		timer.cancel();
	}
	
	public boolean isPolling() { return polling;}
	
	
}
