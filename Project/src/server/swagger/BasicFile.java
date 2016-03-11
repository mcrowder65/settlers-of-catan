package server.swagger;

import java.io.IOException;

import com.sun.net.httpserver.HttpExchange;

public class BasicFile extends BaseFile {

	public BasicFile(String rootPath) { super(rootPath);}
	@Override
	public void handle(HttpExchange exchange) throws IOException {
		String filepath = this.rootPath + this.getRequestPath(exchange);
		this.sendFile(exchange, filepath);
	}

}
