package shared.communication.request;

public class SaveGameRequest extends Request {

	int id;
	String name;
	public SaveGameRequest(int id, String name) {
		this.id = id;
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
}
