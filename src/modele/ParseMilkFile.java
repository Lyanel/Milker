package modele;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javafx.scene.image.Image;

public class ParseMilkFile{
	
	// Méthode lié au fichier
	
	/**
	 * méthode générant un tableau de byte depuis un document xml.
	 * @throws Exception 
	 * */
	public static byte[] getByteArrayFromXmlDocument(Document documentoXml) throws Exception {
	    Source source = new DOMSource( documentoXml );
	    ByteArrayOutputStream out = new ByteArrayOutputStream();
	    Result result = new StreamResult(out);
	    TransformerFactory factory = TransformerFactory.newInstance();
	    Transformer transformer = factory.newTransformer();
	    transformer.transform(source, result);
	    byte[] butesXml =  out.toByteArray();
	    return butesXml;
	}

	
	/**
	 * méthode générant un document xml depuis un tableau de byte.
	 * @throws Exception 
	 * */
	public static Document getXmlDocumentFromByteArray(byte[] documentoXml) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        return builder.parse(new ByteArrayInputStream(documentoXml));
    }
	
	/**
	 * méthode renvoyant le document "path/"+fichier+".xml".
	 * @throws ParserConfigurationException 
	 * @throws IOException 
	 * @throws SAXException 
	 * */
	private static Document getXmlDocument(String fichier) throws ParserConfigurationException, SAXException, IOException {
		Document documentoXml = null;
		byte[] result = null;
		result = Securite.ouvrirFichierCrypter(fichier);
		if (result == null) {
		//	Securite.crypterFichier(fichier); //unlock this method do crypte xml file.
			//old code to get clear xml file.
			DocumentBuilderFactory usine = DocumentBuilderFactory.newInstance();
			DocumentBuilder machine = usine.newDocumentBuilder();
			documentoXml = machine.parse(fichier+".xml");
		} else { 
			try {
				documentoXml = getXmlDocumentFromByteArray(result);
			} catch (Exception e) {e.printStackTrace();}
		}
		return documentoXml;
	}
		
	/**
	 * méthode renvoyant un ArrayList de milkFile sous forme d'element après lecture du fichier.
	 * @throws IOException 
	 * @throws SAXException 
	 * @throws ParserConfigurationException 
	 * */
	public static Element getXmlRacine(String fichier,String node) {
		Element temp=null;
		try {
			temp = getXmlDocument(fichier).getDocumentElement();
			if(!((Element) temp).getTagName().equals(node))throw new Exception("Expected racine tag name : " + node);
		} catch (Exception e) {e.printStackTrace();}
		return temp;
	}

	
	// Méthode lié a un attribut
	
	
	/**
	 * méthode renvoyant un Float trouvé dans un attribut d'un élément xml.
	 * Si l'attribut est vide ou qu'il n'existe pas, la méthode renvoie null.
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
	 * méthode renvoyant un Integer trouvé dans un attribut d'un élément xml.
	 * Si l'attribut est vide ou qu'il n'existe pas, la méthode renvoie null.
	 * */
	public static Integer getXmlIntAttribute(Element milknode, String attribute) {
		String temp = "";
		temp += (milknode != null) ? milknode.getAttribute(attribute):"";
		return  (temp != null && !temp.equals("")) ? Integer.parseInt(temp) : null ;
	}

	
	// Méthode lié a un contenu text d'un noeud
	
	
	/**
	 * méthode renvoyant un Float trouvé dans un élément xml.
	 * Si l'élément est vide ou qu'il n'existe pas, la méthode renvoie null.
	 * */
	public static Float getXmlFloatValue(Element milknode) {
		String temp = "";
		temp += (milknode != null) ? milknode.getTextContent():"";
		return  (temp != null && !temp.equals("")) ? Float.parseFloat(temp) : null ;
	}
	
	/**
	 * méthode renvoyant un Integer trouvé dans un élément xml.
	 * Si l'élément est vide ou qu'il n'existe pas, la méthode renvoie null.
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
	 * méthode renvoyant une String trouvé dans un élément xml
	 * Si l'élément est vide renvoie le text du parent.
	 * S'il n'existe pas, la méthode renvoie une chaine vide ("").
	 * */
	public static String getXmlStringValue(Element milknode, String nodename) {
		String temp="";
		if (milknode != null){
			Element child;
			try {
				child = XmlHelper.getOptionalChild(milknode,nodename);
				if (child != null)temp = milknode.getElementsByTagName(nodename).item(0).getTextContent();
				else temp = milknode.getTextContent();
			} catch (Exception e) {e.printStackTrace();}
			
		}
		return  (temp != null) ? temp : "" ;
	}
	
	/**
	 * méthode renvoyant une String trouvé dans un élément xml
	 * Si l'élément est vide ou qu'il n'existe pas, la méthode renvoie une chaine vide ("").
	 * */
	public static String getXmlChildStringValue(Element milknode, String nodename) {
		String temp="";
		if (milknode != null){
			if (milknode.getElementsByTagName(nodename).item(0) != null)temp = milknode.getElementsByTagName(nodename).item(0).getTextContent();
			
		}
		return  (temp != null) ? temp : "" ;
	}
	
	
	// Méthode de sauvegarde dans le xml

	
	/**
	 * méthode écrivant une liste de nom dans le fichier demander.
	 * */
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
			} catch ( IOException e) {e.printStackTrace();}
		}
	}
	
	
	// Méthode non lié a du xml

	/**
	 * méthode renvoyant l'image "path/"+fichier+"."+extension.
	 * @throws ParserConfigurationException 
	 * @throws IOException 
	 * @throws SAXException 
	 * */
	public static Image getMilkImage(String path, String extension) {
		Image image = null;
		if(path!=null && path.length()>0){
			byte[] raw = Securite.ouvrirImageCrypter(path);
			if (raw == null) {
			//	Securite.crypterImage(path,extension); //unlock this method do crypte xml file.
				
				//old code to get clear image file.
				path = "file:"+path+extension;
				image = new Image(path);
			} else {
				image = new Image(new ByteArrayInputStream(raw));
			}
		}
		return image;
	}
	
	
	/**
	 * méthode renvoyant une liste de nom depuis le fichier demander.
	 * */
	public static ArrayList<String> getNamesLists(String file) {
		ArrayList<String> namelist = new ArrayList<String>();
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
	 * méthode écrivant une liste de nom dans le fichier demander.
	 * */
	public static void whriteNamesLists(String file,ArrayList<String> namelist) {
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
}