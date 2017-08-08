package modele;

import java.util.Vector;

import org.w3c.dom.Element;

import controleur.ParseMilkFile;

public class MilkLanguage extends MilkFile {

	public static final String noeud = "language", xmlIso= "iso";
	public String getNoeud() {return noeud;}
	
	public static Vector<MilkLanguage> getMilkLanguageList(Vector<Element> elementlist) {
		Vector<MilkLanguage> milkFiles = new Vector<MilkLanguage>();
		for (Element elementMilk: elementlist) {
			try {
				MilkLanguage milkFile = new MilkLanguage(elementMilk);
				milkFiles.add(milkFile);
			} catch (Exception e) {e.printStackTrace();}
		}
		return milkFiles;
	}
	
	private String iso;
	
	// Constructors
	
	public MilkLanguage() {
		this("");
	}
	public MilkLanguage(String iso) {
		super();
		this.setIso(iso);
	}
	public MilkLanguage(Element milkElement) {
		super();
		this.setValueFromNode(milkElement);
	}

	// Set value from Element methods
	
	@Override
	public void setValueFromNode(Element milkElement) {
		super.setValueFromNode(milkElement);
		this.setIso(milkElement);
	}
	@Override
	public void setNullValueFromNode(Element milkElement) {
		super.setNullValueFromNode(milkElement);
		this.setNullIso(milkElement);
	}
	
	// field methods
	
	public String getIso() {
		return iso;
	}
	public String getStringIso() {
		String temp = null;
		if (this.iso != null) temp = xmlIso+" : "+this.iso+". ";
		return temp;
	}
	public String getXmlIso() {
		String temp = null;
		if (this.iso != null) temp = "<"+xmlIso+">"+this.iso+"</"+xmlIso+">"+MilkRs.LIGNE_BREAK;
		return temp;
	}
	public void setIso(String iso) {
		this.iso = iso;
	}
	public void setIso(Element milkElement) {
		this.iso = ParseMilkFile.getXmlStringValue(milkElement,xmlIso);
	}
	public void setNullIso(Element milkElement) {
		this.iso = ParseMilkFile.getXmlStringValue(milkElement,xmlIso);
	}
		
	// toString & toXml methods
	
	@Override
	public String toStringTextChild() {
		String temp = super.toStringTextChild();
		temp+=this.getStringIso();
		return temp;
	}
	@Override
	public String toXmlTextChild() {
		String temp = super.toXmlTextChild();
		temp+=this.getXmlIso();
		return temp;
	}
	
	// other object methods
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		MilkLanguage clone = (MilkLanguage) super.clone();
		return clone;
	}
} 