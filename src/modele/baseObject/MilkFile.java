package modele.baseObject;

import java.util.ArrayList;

import org.w3c.dom.Element;

import modele.MilkRs;
import modele.ParseMilkFile;
import modele.XmlHelper;

public class MilkFile extends MilkId {

	public static final String xmlBasePath = "Xml/";
	public static final String xmlFiles = "XmlFileListe";
	public static final String noeud = "file";
	public String getXmlFiles() {return xmlFiles;}
	public String getNoeud() {return noeud;}
	public static final String xmlName = "name", xmlPath = "path";
	private static ArrayList<MilkFile> milkFiles = null;
	
	// Static method playing with files.Xml.

	/**
	 * Return an element listing node found in the file xmlPathFile .
	 */
	public static ArrayList<Element> getMilkElementsFromFiles(String xmlPathFile,String node) {
		ArrayList<Element> elementlist = new ArrayList<Element>();
		try {
			Element racine = ParseMilkFile.getXmlRacine(xmlPathFile,node+"s");
			elementlist = XmlHelper.getChildrenListByTagName(racine, node);
		} catch (Exception e) {e.printStackTrace();}
		return elementlist;
	}

	/**
	 * Return an element listing node found in the file xmlPathFile .
	 */
	public static ArrayList<Element> getMilkElementsFromFiles(String xmlPathFile) {
		return getMilkElementsFromFiles(xmlPathFile,noeud);
	}
	
	/**
	 * set the milkFiles from the xml found in the file XmlFileListe.
	 */
	private static void setMilkFilesFromFiles() {
		if (milkFiles==null) milkFiles = new ArrayList<MilkFile>();
		else milkFiles.clear();
		ArrayList<Element> elementlist = new ArrayList<Element>();
		elementlist = getMilkElementsFromFiles(xmlBasePath+xmlFiles);
		milkFiles = getMilkVarList(elementlist);
	}
	
	/**
	 * Return the file path after searching the file name in XmlFileListe.
	 */
	public static String getXmlFilePath(String fileName) {
		if (milkFiles==null) setMilkFilesFromFiles();
		String link = "";
		for (MilkFile gamefile: milkFiles) {
			if (fileName.equals(gamefile.getName())) link = xmlBasePath+gamefile.getPath();
		}
		return link;
	}
	
	public static ArrayList<MilkFile> getMilkVarList(ArrayList<Element> elementlist) {
		ArrayList<MilkFile> milkFiles = new ArrayList<MilkFile>();
		for (Element elementMilk: elementlist) {
			try {
				MilkFile milkFile = new MilkFile(elementMilk);
				milkFiles.add(milkFile);
			} catch (Exception e) {e.printStackTrace();}
		}
		return milkFiles;
	}
	
	// Fields
	
	private String name,path;

	// Constructors
	
	public MilkFile() {
		this("","");
	}
	public MilkFile(String path,String name) {
		super();
		this.setName(name);
		this.setPath(path);
	}
	public MilkFile(Element milkElement) {
		super();
		this.setValueFromNode(milkElement);
	}
	public MilkFile(MilkFile original) {
		super(original);
		this.name = new String(original.getName());
		this.path = new String(original.getPath());
	}

	// Set value from Element methods
	
	@Override
	public void setValueFromNode(Element milkElement) {
		super.setValueFromNode(milkElement);
		this.setName(milkElement);
		this.setPath(milkElement);
	}
	public void setName(Element milkElement) {
		this.name = ParseMilkFile.getXmlStringValue(milkElement,xmlName);
	}
	public void setPath(Element milkElement) {
		this.path = ParseMilkFile.getXmlStringValue(milkElement,xmlPath);
	}
	
	// field methods
	
	public String getName() {
		return name;
	}
	public String getStringName() {
		String temp = null;
		if (this.name != null) temp = xmlName+" : "+this.name+". ";
		return temp;
	}
	public String getXmlName() {
		String temp = null;
		if (this.name != null) temp = "<"+xmlName+">"+this.name+"</"+xmlName+">"+MilkRs.LIGNE_BREAK;
		return temp;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getPath() {
		return this.path;
	}
	public String getStringPath() {
		String temp = null;
		if (this.path != null) temp = xmlPath+" : "+this.path+". ";
		return temp;
	}
	public String getXmlPath() {
		String temp = null;
		if (this.path != null) temp = "<"+xmlPath+">"+this.path+"</"+xmlPath+">"+MilkRs.LIGNE_BREAK;
		return temp;
	}
	public void setPath(String path) {
		this.path = path;
	}
	
	// toString & toXml methods
	
	@Override
	public String toStringTextChild() {
		String temp = super.toStringTextChild();
		temp+=this.getStringName();
		temp+=this.getStringPath();
		return temp;
	}
	@Override
	public String toXmlTextChild() {
		String temp = super.toXmlTextChild();
		temp+=this.getXmlName();
		temp+=this.getXmlPath();
		return temp;
	}
	
	// other object methods
	
}
