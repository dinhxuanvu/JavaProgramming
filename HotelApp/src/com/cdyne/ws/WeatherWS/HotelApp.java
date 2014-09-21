/**
 * HotelApp.java
 * 
 * File:
 * 		$Id: HotelApp.java,v 1.2 2013/12/11 05:32:49 vxd9797 Exp $
 * 
 * Revisions:
 * 		$Log: HotelApp.java,v $
 * 		Revision 1.2  2013/12/11 05:32:49  vxd9797
 * 		Fixed the HTML tags. Leave as it is.
 * 		Final version.
 *
 * 		Revision 1.1  2013/12/11 02:18:47  vxd9797
 * 		Final Version.
 *
 * 
 */

package com.cdyne.ws.WeatherWS;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.xpath.*;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.LinkedList;

/**
 * A simple program is using Google Maps, Expedia and Weather web services to perform some tasks.
 * The problem is able to find a hotel near the current location.
 * Then, find the direction between the current location and hotel's location
 * Find the current weather at hotel location.
 * 
 * @author Vu Dinh (vxd9797@rit.edu)
 *
 */

public class HotelApp {

	static Document docXML;
	
	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException, RemoteException  {
		
		// Variables field
		String address = "";
		String city = ""; 
		String state = "";
		String zipCode = "";
		
		// List of NodeList to parse XML
		LinkedList<NodeList> nodeList = new LinkedList<NodeList>();
		
		// http://api.ean.com/ean-services/rs/hotel/v3/list?_type=xml&locale=en_US&currencyCode=USD&numberOfResults=15&apiKey=utf2pd8jsm9mr6mmfxyjqeq9&stateProvinceCode=NY&city=Rochester; 
		  
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance(); 
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder(); 
        
        // Get XML file containing the hotel list from Expedia
        Document doc = dBuilder.parse("http://api.ean.com/ean-services/rs/hotel/v3/list?_type=xml&locale=en_US"
        		+ "&currencyCode=USD&numberOfResults=1&apiKey=utf2pd8jsm9mr6mmfxyjqeq9&stateProvinceCode=NY&city=Rochester"); 
        doc.getDocumentElement().normalize(); 
  
        // Get the NodeList with tag HotelSumary
        NodeList nList = doc.getElementsByTagName("HotelSummary"); 
  
        Node nNode = nList.item(0); 
        
        System.out.println("Your current address is 102 Lomb Memorial Drive, Rochester NY.");
        System.out.print("We found the following hotel: "); 
        
        if (nNode.getNodeType() == Node.ELEMENT_NODE) { 

            NodeList n = nNode.getChildNodes(); 
            // Get the name of the hotel
            System.out.println(n.item(1).getTextContent()); 
            System.out.print("At this address: ");
            // Address of the hotel
            address = n.item(2).getTextContent();
            // Get the city
            city = n.item(3).getTextContent();
            // Get the state
            state = n.item(4).getTextContent();
            // Get zipcode for the hotel's location
            zipCode = n.item(5).getTextContent();
            // Display the full address of the hotel
            System.out.println(address + "," + city + "," + state + "\n");
        } 


		//http://maps.googleapis.com/maps/api/geocode/xml?address=102+Lomb+Memorial+Drive,+Rochester,+NY&sensor=true

        // Use XPath to parse XML for the Google direction
		XPathFactory factory = XPathFactory.newInstance();

		XPath xpath = factory.newXPath();
		
		try {
			
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			
			// Generate the correct formatted address
			String newAddress = address.replaceAll(" ", "+");
			String destination = newAddress+",+"+city+",+"+state;
			String origin = "102+Lomb+Memorial+Drive,+Rochester,+NY";

			// The correct formatted URL for Google direction web services
			String directionURL = "http://maps.googleapis.com/maps/api/directions/xml?origin="+origin+"&destination="+destination+"&sensor=true";

			// Get the XML from Google Map web services
			docXML = db.parse(new URL(directionURL).openStream());

			// Because the evaluator may return multiple entries, we specify that the expression
			// return a NODESET and place the result in a NodeList.
			// Get the list of steps
			NodeList steps = (NodeList) xpath.evaluate("/DirectionsResponse/route/leg/step", docXML, XPathConstants.NODESET);
						
			for (int i = 0; i < steps.getLength(); i++) {
				nodeList.add((NodeList) steps.item(i));
			}
			
			System.out.println("The routing direction:");
			
			// We can then iterate over the NodeList and extract the content via getTextContent().
			// NOTE: this will only return text for element nodes at the returned context.
			// Get the step-by-step direction from current location to the hotel
			for (int i = 0; i < steps.getLength(); i++) {
				String inst = nodeList.get(i).item(11).getTextContent();
				// Display the direction
				System.out.println(inst);
			}
			
		} catch (XPathExpressionException ex) {
			System.err.println("XPath Error!");
		}
		
		// Use the Weather web services
		WeatherSoapProxy wc = new WeatherSoapProxy(); 
		System.out.print("\nThe current temperature of the hotel area is "); 
		// Get the current weather temperature
		System.out.println(wc.getCityWeatherByZIP(zipCode).getTemperature()); 
	}
}
