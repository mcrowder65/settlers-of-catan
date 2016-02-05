package shared.communication.request;

public class GetModelRequest extends Request {

	//Nullable because this is optional
	Integer version;
	public GetModelRequest() {
		
	}
	public GetModelRequest(int version) {
		this.version = version;
	}
}
