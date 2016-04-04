package server.plugin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class ConfigurationTool {

	
	public static List<PluginDefinition> readConfigFile(String configLocation) throws SAXException, IOException, ParserConfigurationException {
		List<PluginDefinition> definitions = new ArrayList<PluginDefinition>();
		DocumentBuilderFactory dbFactory =  DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		File parseFile = new File(configLocation);
		Document doc = dBuilder.parse(parseFile);
		doc.getDocumentElement().normalize();
		
		Element root = doc.getDocumentElement();
		NodeList pluginNodes = root.getElementsByTagName("plugin");
		for (int n = 0; n < pluginNodes.getLength(); n++) {
			PluginDefinition def = new PluginDefinition();
			ArrayList<Element> children = getChildElements(pluginNodes.item(n));
			
			for (Element element : children) {
		    	switch (element.getTagName()) {
		    	case "name":
		    		def.name = element.getFirstChild().getNodeValue(); 
		    		break;
		    	case "path":
		    		def.path = element.getFirstChild().getNodeValue();
		    		break;
			
		    	}
			}
			
			definitions.add(def);
		}
		return definitions;
	}
	
	public static ArrayList<Element> getChildElements(Node node) {
		ArrayList<Element> result = new ArrayList<Element>();
		
		NodeList children = node.getChildNodes();
		for (int i = 0; i < children.getLength(); i++) {
			Node child = children.item(i);
			if (child.getNodeType() == Node.ELEMENT_NODE) {
				result.add((Element)child);
			}
		}
		return result;
	}

}
