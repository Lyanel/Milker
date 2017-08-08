package controleur;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Collections;
import java.util.HashSet;
import java.util.Vector;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import modele.MilkRs;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ParseMilkFile{
	/**
	 * m�thode renvoyant le document "path/"+fichier+".xml".
	 * @throws ParserConfigurationException 
	 * @throws IOException 
	 * @throws SAXException 
	 * */
	private static Document getXmlDocument(String fichier) throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory usine = DocumentBuilderFactory.newInstance();
		DocumentBuilder machine = usine.newDocumentBuilder();
		return machine.parse(fichier+".xml");
	}

	/**
	 * m�thode parsant le fichier demander a la recherche du node cible.
	 * @throws ParserConfigurationException 
	 * @throws IOException 
	 * @throws SAXException 
	 * */
	private static NodeList getXmlList(String fichier, String node) throws ParserConfigurationException, SAXException, IOException {
		Document ciblexml = getXmlDocument(fichier);
		return ciblexml.getElementsByTagName(node);
	}
	
	/**
	 * m�thode renvoyant un Vector de milkFile sous forme d'element depuis une NodeList.
	 * @throws IOException 
	 * @throws SAXException 
	 * @throws ParserConfigurationException 
	 * */
	public static Vector<Element> getElementLists(NodeList milkObjetlist) throws ParserConfigurationException, SAXException, IOException {
		Vector<Element> milkObjet = new Vector<Element>();
		for (int i=0;i<milkObjetlist.getLength();i++) {
			milkObjet.add((Element)milkObjetlist.item(i));
		}
		return milkObjet;
	}
	
	/**
	 * m�thode renvoyant un Vector de milkFile sous forme d'element apr�s lecture du fichier.
	 * @throws IOException 
	 * @throws SAXException 
	 * @throws ParserConfigurationException 
	 * */
	public static Vector<Element> getMilkElementLists(String fichier,String node) {
		Vector<Element> temp=null;
		try {
			temp = getElementLists(getXmlList(fichier,node));
		} catch (ParserConfigurationException | SAXException | IOException e) {e.printStackTrace();}
		return temp;
	}
	
	/**
	 * m�thode renvoyant un �l�ment "name" � l'index "range" dans l'�l�ment "element", ou null...
	 * @param element
	 * @param nodeName
	 * @return
	 */
	public static Element getMilkElement(Element element,String name, int range) {
		return (element != null) ?(Element)element.getElementsByTagName(name).item(range):null;
	}
	
	/**
	 * m�thode renvoyant un Float trouv� dans un attribut d'un �l�ment xml.
	 * Si l'attribut est vide ou qu'il n'existe pas, la m�thode renvoie null.
	 * */
	public static Float getXmlFloatAttribute(Element milknode, String attribute) {
		Float ftemp = (float)0;
		String stemp = "";
		stemp += (milknode != null) ? milknode.getAttribute(attribute):"";
		try{
			ftemp = (stemp != null && !stemp.equals("")) ? Float.parseFloat(stemp) : null ;
		} catch (NumberFormatException e){}
		return  ftemp ;
	}
	
	/**
	 * m�thode renvoyant un Float trouv� dans un �l�ment xml.
	 * Si l'�l�ment est vide ou qu'il n'existe pas, la m�thode renvoie null.
	 * */
	public static Float getXmlFloatValue(Element milknode) {
		String temp = "";
		temp += (milknode != null) ? milknode.getTextContent():"";
		return  (temp != null && !temp.equals("")) ? Float.parseFloat(temp) : null ;
	}
	
	/**
	 * m�thode renvoyant un Integer trouv� dans un attribut d'un �l�ment xml.
	 * Si l'attribut est vide ou qu'il n'existe pas, la m�thode renvoie null.
	 * */
	public static Integer getXmlIntAttribute(Element milknode, String attribute) {
		String temp = "";
		temp += (milknode != null) ? milknode.getAttribute(attribute):"";
		return  (temp != null && !temp.equals("")) ? Integer.parseInt(temp) : null ;
	}
	
	/**
	 * m�thode renvoyant un Integer trouv� dans un �l�ment xml.
	 * Si l'�l�ment est vide ou qu'il n'existe pas, la m�thode renvoie null.
	 * */
	public static Integer getXmlIntValue(Element milknode) {
		Integer itemp = 0;
		String stemp = "";
		stemp += (milknode != null) ? milknode.getTextContent():"";
		try{
			itemp = (stemp != null && !stemp.equals("")) ? Integer.parseInt(stemp) : null ;
		} catch (NumberFormatException e){}
		return  itemp ;
	}
	
	/**
	 * m�thode renvoyant une String trouv� dans un �l�ment xml
	 * Si l'�l�ment est vide ou qu'il n'existe pas, la m�thode renvoie une chaine vide ("").
	 * */
	public static String getXmlStringValue(Element milknode, String nodename) {
		String temp="";
		if (milknode != null){
			if (milknode.getElementsByTagName(nodename).item(0) != null)temp = milknode.getElementsByTagName(nodename).item(0).getTextContent();
			else temp = milknode.getTextContent();
			
		}
		return  (temp != null) ? temp : "" ;
	}
	
	/**
	 * m�thode renvoyant une liste de nom depuis le fichier demander.
	 * */
	public static Vector<String> getNamesLists(String file) {
		Vector<String> namelist = new Vector<String>();
		String chaine = "";
		try {
			File fileDir = new File("Data/Xml/"+file+".txt");
			BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(fileDir), "UTF-8"));
			String str;
			while ((str = in.readLine()) != null) {
				chaine+=str;
			}
			in.close();
		} catch (Exception e) {e.printStackTrace();}
		
		String[] subChaine = chaine.split(",");
		for(String chaineI : subChaine) {
			namelist.add(chaineI);
		}
	//	whriteNamesLists(file,namelist);
		return namelist;
	}
	
	/**
	 * m�thode �crivant une liste de nom dans le fichier demander.
	 * */
	public static void whriteNamesLists(String file,Vector<String> namelist) {
		HashSet<String> set = new HashSet<String>(namelist);
		namelist.clear();
		namelist.addAll(set);
		Collections.sort(namelist);
		try {
			File fileDir = new File("WebContent/Test/"+file+".txt");
			if (!fileDir.exists())fileDir.createNewFile();
			BufferedWriter in = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileDir), "UTF-8"));
			String start = "";
			for(String chaine : namelist) {
				if (chaine.length()>0){
					if(!chaine.startsWith(start)){
						in.write(MilkRs.LIGNE_BREAK);
					}
					in.write(chaine+",");
					start=""+chaine.charAt(0);
				}
			}
			in.close();
		} catch (Exception e) {e.printStackTrace();}
	}

	public static void saveMilkElementLists(String fichier, String xmlString) {
		String path = fichier+".xml";
		BufferedWriter writer = null;
		try{
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path),"UTF-8"));
			writer.write(xmlString);
		}catch ( IOException e){
		}finally{
			try{
				if ( writer != null)writer.close( );
			} catch ( IOException e) { }
		}
	}
}