package server.swagger;


import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

import com.sun.istack.internal.logging.Logger;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public abstract class BaseFile implements HttpHandler {
	
	public BaseFile(String rootPath) { this.rootPath = rootPath; }
	protected String rootPath;
	protected String getRequestPath(HttpExchange exchange) {
		return exchange.getRequestURI().getPath().substring(1);
	}
	protected void sendFile(HttpExchange exchange, String filepath) throws IOException{
		try {
			byte[] response = FileUtils.readFile(rootPath + filepath);
			ArrayList<String> mimetypes = new ArrayList<String>();
			
			mimetypes.add(FileUtils.getMimeType(filepath));
			exchange.getResponseHeaders().put("Content-type", mimetypes);
			exchange.sendResponseHeaders(200, response.length);
			OutputStream os = exchange.getResponseBody();
			os.write(response);
			os.close();
		} catch (IOException ioe) { 
			exchange.sendResponseHeaders(404, -1);
			OutputStream os = exchange.getResponseBody();
			os.close();
		}

	}
}
