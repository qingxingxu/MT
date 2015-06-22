package edu.buaa.nlp.xqx;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.Node;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class XML {

	 static void readXML(String url,List<String> chsList,List<String> krsList){
		 
			String getUrl = null;
	        DocumentBuilder db = null;
	        Document doc = null ;
	 		try {
					db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
					doc = db.parse(url);
				}catch (ParserConfigurationException e) {
					e.printStackTrace();
				}catch (SAXException e){
					e.printStackTrace();
				}catch (IOException e){
					e.printStackTrace();
				}
	            NodeList nodeList = doc.getElementsByTagName("para");
	            for (int i = 0; i < nodeList.getLength(); i++) {
	                 String Chinese = doc.getElementsByTagName("Chinese").item(i).getFirstChild().getNodeValue();
	                 String Korean = doc.getElementsByTagName("Korean").item(i).getFirstChild().getNodeValue();
	                 chsList.add(Chinese);
	                 krsList.add(Korean);
	            }
			    
	}
	
	public static void main(String[] args) {
		
	}
}
