package modele.toggle;

import modele.MilkFile;
import modele.MilkImage;
import modele.MilkInterface;

import java.util.Vector;

import org.w3c.dom.Element;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ToggleIdol extends Toggle implements Cloneable {

	private static Vector<ToggleIdol> idols;
	private static ToggleIdol idol;
	public static final String file		= "ToggleIdol";
	
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
	
	public static Vector<ToggleIdol> getNullMilkVarList(Vector<Element> elementlist) {
		Vector<ToggleIdol> things = new Vector<ToggleIdol>();
		for (Element elementMilk: elementlist) {
			try {
				ToggleIdol thing = new ToggleIdol();
				thing.setNullValueFromNode(elementMilk);
				things.add(thing);
			} catch (Exception e) {e.printStackTrace();}
		}
		return things;
	}

	private static void setInfos(Vector<ToggleIdol> toggleIdols, Vector<Element> elementInfos) {
		for (Element elementInfo: elementInfos) {
			try {
				ToggleIdol toggleIdolInfo = new ToggleIdol(elementInfo);
				toggleIdolInfo.setInfo(elementInfo);
				for (ToggleIdol toggleIdol:toggleIdols){
					if (toggleIdolInfo.equals(toggleIdol)){
						toggleIdol.setInfo(toggleIdolInfo.getInfo());
						toggleIdol.setToggleOptionsInfo(toggleIdolInfo.getToggleOptions());
						ToggleOption.setOptionsInfos(toggleIdol.getToggleOptions(),elementInfo);
						break;
					}
				}
			} catch (Exception e) {e.printStackTrace();}
		}
	}

	private static void setIcons(Vector<ToggleIdol> toggleIdols, Vector<Element> elementIcons) {
		for (Element elementIcon: elementIcons) {
			try {
				ToggleIdol toggleIdolIcon = new ToggleIdol(elementIcon);
				toggleIdolIcon.setIcon(elementIcon);
				for (ToggleIdol toggleIdol:toggleIdols){
					if (toggleIdolIcon.equals(toggleIdol)){
						toggleIdol.setIcon(toggleIdolIcon.getIcon());
						toggleIdol.setToggleOptionsIcon(toggleIdolIcon.getToggleOptions());
						ToggleOption.setOptionsIcons(toggleIdol.getToggleOptions(),elementIcon);
						break;
					}
				}
			} catch (Exception e) {e.printStackTrace();}
		}
	}

	private static void setScenes(Vector<ToggleIdol> toggleIdols, Vector<Element> elementScenes) {
		for (Element elementScene: elementScenes) {
			try {
				ToggleIdol toggleIdolScene = new ToggleIdol(elementScene);
				toggleIdolScene.setScenes(elementScene);
				for (ToggleIdol toggleIdol:toggleIdols){
					if (toggleIdolScene.equals(toggleIdol)){
						toggleIdol.setToggleOptionsScene(toggleIdolScene.getToggleOptions());
						ToggleOption.setOptionsScenes(toggleIdol.getToggleOptions(),elementScene);
						break;
					}
				}
			} catch (Exception e) {e.printStackTrace();}
		}
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
	
	public static ObservableList<ToggleOption> getOptionListes() {
		if (idol==null) ToggleIdol.getIdol();
		ObservableList<ToggleOption> clone = FXCollections.observableArrayList();
		Vector<ToggleOption> optionlist;
		optionlist = idol.getToggleOptions();
		for (ToggleOption option:optionlist){
			clone.add(option);
		}
		return clone;
	}
	
	@SuppressWarnings("rawtypes")
	public static Vector getMilkVarList(Element elementlist) {
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
	@SuppressWarnings("rawtypes")
	public static Vector getNullMilkVarList(Element elementlist) {
		Vector<ToggleIdol> things = new Vector<ToggleIdol>();
		ToggleIdol thing=new ToggleIdol();
		Element elements = thing.getMilkElementList(elementlist);
		int size = (elements!=null)? elements.getChildNodes().getLength():0;
		for (int i=0;i<size;i++){ 
			Element tempE=null;
			tempE=thing.getMilkElement(elements,i);
			if (tempE != null){
				thing=new ToggleIdol();
				thing.setNullValueFromNode(tempE);
				things.add(thing);
			}
		}
		return things;
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
	@Override
	public void setNullValueFromNode(Element milkElement) {
		super.setNullValueFromNode(milkElement);
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