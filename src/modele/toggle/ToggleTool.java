package modele.toggle;

import modele.MilkFile;
import modele.MilkImage;
import modele.MilkInterface;

import java.util.Vector;

import org.w3c.dom.Element;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ToggleTool extends Toggle implements Cloneable {

	private static Vector<ToggleTool> tools;
	private static ToggleTool tool;
	public static final String file		= "ToggleTool";
	
	private static Vector<ToggleTool> setMilkVarFromFiles() {
		if (tools==null) tools = new Vector<ToggleTool>();
		else tools.removeAllElements();
		//Set stats
		Vector<Element> elementlist = new Vector<Element>();
		elementlist = MilkFile.getMilkElementsFromFiles(MilkFile.getXmlFilePath(file)+file, noeud);
		tools = getMilkVarList(elementlist);
		//Set info
		Vector<Element> elementlInfos = new Vector<Element>();
		elementlInfos = MilkFile.getMilkElementsFromFiles(MilkInterface.getXmlLangPath()+file, noeud);
		setInfos(tools, elementlInfos);
		//Set icon
		Vector<Element> elementlIcon = new Vector<Element>();
		elementlIcon = MilkFile.getMilkElementsFromFiles(MilkImage.getXmlIconsPath(file)+file, noeud);
		setIcons(tools, elementlIcon);
		//Set scene
		Vector<Element> elementlScene = new Vector<Element>();
		elementlScene = MilkFile.getMilkElementsFromFiles(MilkImage.getXmlScenesPath(file)+file, noeud);
		setScenes(tools, elementlScene);
		
		
		return tools;
	}
	
	public static Vector<ToggleTool> getMilkVarList(Vector<Element> elementlist) {
		Vector<ToggleTool> things = new Vector<ToggleTool>();
		for (Element elementMilk: elementlist) {
			try {
				ToggleTool thing = new ToggleTool(elementMilk);
				things.add(thing);
			} catch (Exception e) {e.printStackTrace();}
		}
		return things;
	}
	@SuppressWarnings("rawtypes")
	public static Vector getMilkVarList(Element elementlist) {
		Vector<ToggleTool> things = new Vector<ToggleTool>();
		ToggleTool thing=new ToggleTool();
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
	
	public static ToggleTool getTool() {
		if(tool==null){
			if(tools==null) setMilkVarFromFiles();
			try {
				tool=(ToggleTool) tools.get(0).clone();
			} catch (CloneNotSupportedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return tool;
	}
	
	public static ObservableList<ToggleOption> getOptionListes() {
		if (tool==null) ToggleTool.getTool();
		ObservableList<ToggleOption> clone = FXCollections.observableArrayList();
		Vector<ToggleOption> optionlist;
		optionlist = tool.getToggleOptions();
		for (ToggleOption option:optionlist){
			clone.add(option);
		}
		return clone;
	}

	// Constructors
	
	public ToggleTool() {
		super();
	}
	public ToggleTool(Element milkElement) {
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
		ToggleTool clone = (ToggleTool) super.clone();
		return clone;
	}
}