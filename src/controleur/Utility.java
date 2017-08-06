package controleur;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Vector;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Element;
import org.xml.sax.SAXException;

public class Utility{
	private static final String NAME_FILE_STRING = "NameList";
	private static final Vector<String> LAST_NAME_LIST_STRINGS = ParseMilkFile.getNamesLists(NAME_FILE_STRING);
	
	/**
	 * return a random Name.
	 * @throws IOException 
	 * @throws SAXException 
	 * @throws ParserConfigurationException 
	 * */
	public static String getRandomLastName() {
		int i = (int) Math.round(LAST_NAME_LIST_STRINGS.size()*Math.random()) ;
		if (i==LAST_NAME_LIST_STRINGS.size())i-=1;
		return LAST_NAME_LIST_STRINGS.elementAt(i);
	}
		
	/**
	 * return minValue+((maxValue-minValue)*Math.random()*mod)
	 * */
	public static double getRandomValue(double mod, double minValue, double maxValue) {
		return minValue+((maxValue-minValue)*Math.random()*mod);
	}
	
	/**
	 * return a string from a xml element.
	 * */
	public static String getStringFromElement(Element node) {
		DOMSource domSource = new DOMSource(node);
		StringWriter writer = new StringWriter();
		StreamResult result = new StreamResult(writer);
		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer transformer = null;
		try {
			transformer = tf.newTransformer();
			transformer.transform(domSource, result);
		} catch (Exception e) {e.printStackTrace();}
		return writer.toString();
	}
}