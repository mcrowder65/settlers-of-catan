package server.swagger;

import java.io.IOException;

import com.sun.net.httpserver.HttpExchange;

public class JSONAppender extends BaseFile {
	public JSONAppender(String rootPath) { super(rootPath); }

	@Override
	public void handle(HttpExchange exchange) throws IOException {
		this.sendFile(exchange, this.rootPath + this.getRequestPath(exchange) + ".json");
	}
	
}
