package modele;

import java.util.Vector;

import org.w3c.dom.Element;

import controleur.ParseMilkFile;

public class MilkFile extends MilkId implements Cloneable {

	public static final String xmlBasePath = "Xml/";
	public static final String xmlFiles = "XmlFileListe";
	public static final String noeud = "file";
	public String getNoeud() {return noeud;}
	public static final String xmlName = "name", xmlPath = "path";
	private static Vector<MilkFile> milkFiles = null;
	
	// Static method playing with files.Xml.

	/**
	 * set the milkFiles from the xml found in the file XmlFileListe.
	 */
	private static void setMilkFilesFromFiles() {
		if (milkFiles==null) milkFiles = new Vector<MilkFile>();
		else milkFiles.removeAllElements();
		Vector<Element> elementlist = new Vector<Element>();
		elementlist = getMilkElementsFromFiles(xmlBasePath+xmlFiles);
		milkFiles = getMilkVarList(elementlist);
	}
	/**
	 * Return an element listing node found in the file xmlPathFile .
	 */
	public static Vector<Element> getMilkElementsFromFiles(String xmlPathFile,String node) {
		Vector<Element> elementlist = new Vector<Element>();
		try {
			elementlist = ParseMilkFile.getMilkElementLists(xmlPathFile,node);
		} catch (Exception e) {e.printStackTrace();}
		return elementlist;
	}
	/**
	 * Return an element listing node found in the file xmlPathFile .
	 */
	public static Vector<Element> getMilkElementsFromFiles(String xmlPathFile) {
		return getMilkElementsFromFiles(xmlPathFile,noeud);
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
	
	public static Vector<MilkFile> getMilkVarList(Vector<Element> elementlist) {
		Vector<MilkFile> milkFiles = new Vector<MilkFile>();
		for (Element elementMilk: elementlist) {
			try {
				MilkFile milkFile = new MilkFile(elementMilk);
				milkFiles.add(milkFile);
			} catch (Exception e) {e.printStackTrace();}
		}
		return milkFiles;
	}
	public static Vector<MilkFile> getNullMilkVarList(Vector<Element> elementlist) {
		Vector<MilkFile> milkFiles = new Vector<MilkFile>();
		for (Element elementMilk: elementlist) {
			try {
				MilkFile milkFile = new MilkFile();
				milkFile.setNullValueFromNode(elementMilk);
				milkFiles.add(milkFile);
			} catch (Exception e) {e.printStackTrace();}
		}
		return milkFiles;
	}
	
	private String path;
	private String name;

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

	// Set value from Element methods
	
	@Override
	public void setValueFromNode(Element milkElement) {
		super.setValueFromNode(milkElement);
		this.setName(milkElement);
		this.setPath(milkElement);
	}
	@Override
	public void setNullValueFromNode(Element milkElement) {
		super.setNullValueFromNode(milkElement);
		this.setNullName(milkElement);
		this.setNullPath(milkElement);
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
	public void setName(Element milkElement) {
		this.name = ParseMilkFile.getXmlStringValue(milkElement,xmlName);
	}
	public void setNullName(Element milkElement) {
		this.name = ParseMilkFile.getXmlStringValue(milkElement,xmlName);
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
	public void setPath(Element milkElement) {
		this.path = ParseMilkFile.getXmlStringValue(milkElement,xmlPath);
	}
	public void setNullPath(Element milkElement) {
		this.path = ParseMilkFile.getXmlStringValue(milkElement,xmlPath);
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
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		MilkFile clone = (MilkFile) super.clone();
		return clone;
	}
}
