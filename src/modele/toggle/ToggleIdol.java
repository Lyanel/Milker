package modele.toggle;

import java.util.ArrayList;

import org.w3c.dom.Element;

import modele.XmlHelper;
import modele.baseObject.MilkFile;
import modele.baseObject.MilkImage;
import modele.baseObject.MilkInterface;

public class ToggleIdol extends Toggle implements Cloneable {

	public static final String file	= "ToggleIdol";
	private static ArrayList<ToggleIdol> idols;
	private static ToggleIdol idol;
	
	private static ArrayList<ToggleIdol> setMilkVarFromFiles() {
		if (idols==null) idols = new ArrayList<ToggleIdol>();
		else idols.clear();
		//Set stats
		ArrayList<Element> elementlist = new ArrayList<Element>();
		elementlist = MilkFile.getMilkElementsFromFiles(MilkFile.getXmlFilePath(file)+file, noeud);
		idols = getMilkVarList(elementlist);
		//Set info
		ArrayList<Element> elementlInfos = new ArrayList<Element>();
		elementlInfos = MilkFile.getMilkElementsFromFiles(MilkInterface.getXmlLangPath()+file, noeud);
		setInfos(idols, elementlInfos);
		//Set icon
		ArrayList<Element> elementlIcon = new ArrayList<Element>();
		elementlIcon = MilkFile.getMilkElementsFromFiles(MilkImage.getXmlIconsPath(file)+file, noeud);
		setIcons(idols, elementlIcon);
		//Set scene
		ArrayList<Element> elementlScene = new ArrayList<Element>();
		elementlScene = MilkFile.getMilkElementsFromFiles(MilkImage.getXmlScenesPath(file)+file, noeud);
		setScenes(idols, elementlScene);
		
		
		return idols;
	}
	
	public static ArrayList<ToggleIdol> getMilkVarList(Element parent) {
		return getMilkVarList(XmlHelper.getChildrenListByTagName(parent,noeud));
	}
	
	public static ArrayList<ToggleIdol> getMilkVarList(ArrayList<Element> elementlist) {
		ArrayList<ToggleIdol> things = new ArrayList<ToggleIdol>();
		for (Element elementMilk: elementlist) {
			try {
				ToggleIdol thing = new ToggleIdol(elementMilk);
				things.add(thing);
			} catch (Exception e) {e.printStackTrace();}
		}
		return things;
	}
	
	public static void updateInfoFromFiles() {
		ArrayList<Element> elementlInfos = new ArrayList<Element>();
		elementlInfos = MilkFile.getMilkElementsFromFiles(MilkInterface.getXmlLangPath()+file, noeud);
		setInfos(Toggle.getToggleListe(), elementlInfos);
	}
	
	public static ToggleIdol getIdol() {
		if(idol==null){
			if(idols==null) setMilkVarFromFiles();
			try {
				idol=(ToggleIdol) idols.get(0).clone();
			} catch (CloneNotSupportedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return idol;
	}

	// Constructors
	
	public ToggleIdol() {
		super();
	}
	public ToggleIdol(Element milkElement) {
		super();
		this.setValueFromNode(milkElement);
	}
	
	// Set value from Element methods
	
	@Override
	public void setValueFromNode(Element milkElement) {
		super.setValueFromNode(milkElement);
	}
	
	// field methods
	
	// toString & toXml methods
	
	@Override
	public String toStringStatChild() {
		String temp = super.toStringStatChild();
		return temp;
	}
	@Override
	public String toXmlStatChild() {
		String temp = super.toXmlStatChild();
		return temp;
	}
	
	// other object methods

	@Override
	public boolean allZero()  {
		boolean temp = super.allZero();
		return temp;
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		ToggleIdol clone = (ToggleIdol) super.clone();
		return clone;
	}
}