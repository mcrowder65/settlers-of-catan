package server.dao;

import server.util.ServerGameModel;

public class GameParams {

	private int id;
	private ServerGameModel model;
	private String title;
	
	public GameParams(int id, ServerGameModel model, String title){
		this.id = id;
		this.model = model;
		this.title = title;
	}

	public int getId() {
		return id;
	}

	public ServerGameModel getModel() {
		return model;
	}

	public String getTitle() {
		return title;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setModel(ServerGameModel model) {
		this.model = model;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	
}
