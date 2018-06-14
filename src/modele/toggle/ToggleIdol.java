package modele.toggle;

import modele.MilkFile;
import modele.MilkImage;
import modele.MilkInterface;

import java.util.Vector;

import org.w3c.dom.Element;

public class ToggleIdol extends Toggle implements Cloneable {

	public static final String file	= "ToggleIdol";
	private static Vector<ToggleIdol> idols;
	private static ToggleIdol idol;
	
	private static Vector<ToggleIdol> setMilkVarFromFiles() {
		if (idols==null) idols = new Vector<ToggleIdol>();
		else idols.removeAllElements();
		//Set stats
		Vector<Element> elementlist = new Vector<Element>();
		elementlist = MilkFile.getMilkElementsFromFiles(MilkFile.getXmlFilePath(file)+file, noeud);
		idols = getMilkVarList(elementlist);
		//Set info
		Vector<Element> elementlInfos = new Vector<Element>();
		elementlInfos = MilkFile.getMilkElementsFromFiles(MilkInterface.getXmlLangPath()+file, noeud);
		setInfos(idols, elementlInfos);
		//Set icon
		Vector<Element> elementlIcon = new Vector<Element>();
		elementlIcon = MilkFile.getMilkElementsFromFiles(MilkImage.getXmlIconsPath(file)+file, noeud);
		setIcons(idols, elementlIcon);
		//Set scene
		Vector<Element> elementlScene = new Vector<Element>();
		elementlScene = MilkFile.getMilkElementsFromFiles(MilkImage.getXmlScenesPath(file)+file, noeud);
		setScenes(idols, elementlScene);
		
		
		return idols;
	}
	
	public static Vector<ToggleIdol> getMilkVarList(Vector<Element> elementlist) {
		Vector<ToggleIdol> things = new Vector<ToggleIdol>();
		for (Element elementMilk: elementlist) {
			try {
				ToggleIdol thing = new ToggleIdol(elementMilk);
				things.add(thing);
			} catch (Exception e) {e.printStackTrace();}
		}
		return things;
	}
	
	public static Vector<? extends Toggle> getMilkVarList(Element elementlist) {
		Vector<ToggleIdol> things = new Vector<ToggleIdol>();
		ToggleIdol thing=new ToggleIdol();
		Element elements = thing.getMilkElementList(elementlist);
		int size = (elements!=null)? elements.getChildNodes().getLength():0;
		for (int i=0;i<size;i++){ 
			Element tempE=null;
			tempE=thing.getMilkElement(elements,i);
			if (tempE != null){
				thing.setValueFromNode(tempE);
				things.add(thing);
			}
		}
		return things;
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