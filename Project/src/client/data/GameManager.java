package client.data;


import java.util.Observable;
import java.util.Observer;

import client.communication.IProxy;
import client.communication.Poller;
import shared.definitions.GameModel;

/**
 * Serves as an wrapper for the GameModel class
 * and integrates it with the Server API.
 */
public class GameManager implements Observer {

	/**
	 * The model of the current game.
	 */
	private GameModel model;
	/**
	 * The poller created by the GameManager
	 * that will be used to periodically
	 * check for the newest version
	 * as defined by the proxy.
	 */
	private Poller poller;
	/**
	 * The Proxy that will be periodically polled
	 * for the latest version of the model.
	 */
	private IProxy proxy;
	
	/**
	 * Creates a new instance of the GameManager class.
	 */
	public GameManager(IProxy proxy, int pollingInterval) throws IllegalArgumentException
	{
		setProxy(proxy);
		poller = new Poller(proxy, pollingInterval);
		poller.addObserver(this);
		
	}
	
	public GameModel getModel() { return model;}
	public IProxy getProxy() { return proxy;}
	public Poller getPoller() { return poller;}
	
	public void setProxy(IProxy proxy) throws IllegalArgumentException {
		if (proxy == null) throw new IllegalArgumentException("Proxy cannot be null.");
		this.proxy = proxy;
	
	}
	/**
	 * Gets the latest version of the GameModel and updates
	 * the local copy of the GameModel.
	 * @return
	 * Returns true if update was successful.
	 */
	public boolean updateModel()  {
		proxy.getModel();
		return false;
	}

	@Override
	public void update(Observable o, Object arg) {
		GameModel model = (GameModel)arg;
		this.model = model;
		//TODO: Update local model (SYNCHRONIZED)
		
		
	}
	
}
