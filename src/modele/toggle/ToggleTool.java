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
	
	public static Vector<ToggleTool> getNullMilkVarList(Vector<Element> elementlist) {
		Vector<ToggleTool> things = new Vector<ToggleTool>();
		for (Element elementMilk: elementlist) {
			try {
				ToggleTool thing = new ToggleTool();
				thing.setNullValueFromNode(elementMilk);
				things.add(thing);
			} catch (Exception e) {e.printStackTrace();}
		}
		return things;
	}

	private static void setInfos(Vector<ToggleTool> toggleTools, Vector<Element> elementInfos) {
		for (Element elementInfo: elementInfos) {
			try {
				ToggleTool toggleToolInfo = new ToggleTool(elementInfo);
				toggleToolInfo.setInfo(elementInfo);
				for (ToggleTool toggleTool:toggleTools){
					if (toggleToolInfo.equals(toggleTool)){
						toggleTool.setInfo(toggleToolInfo.getInfo());
						toggleTool.setToggleOptionsInfo(toggleToolInfo.getToggleOptions());
						ToggleOption.setOptionsInfos(toggleTool.getToggleOptions(),elementInfo);
						break;
					}
				}
			} catch (Exception e) {e.printStackTrace();}
		}
	}

	private static void setIcons(Vector<ToggleTool> toggleTools, Vector<Element> elementIcons) {
		for (Element elementIcon: elementIcons) {
			try {
				ToggleTool toggleToolIcon = new ToggleTool(elementIcon);
				toggleToolIcon.setIcon(elementIcon);
				for (ToggleTool toggleTool:toggleTools){
					if (toggleToolIcon.equals(toggleTool)){
						toggleTool.setIcon(toggleToolIcon.getIcon());
						toggleTool.setToggleOptionsIcon(toggleToolIcon.getToggleOptions());
						ToggleOption.setOptionsIcons(toggleTool.getToggleOptions(),elementIcon);
						break;
					}
				}
			} catch (Exception e) {e.printStackTrace();}
		}
	}

	private static void setScenes(Vector<ToggleTool> toggleTools, Vector<Element> elementScenes) {
		for (Element elementScene: elementScenes) {
			try {
				ToggleTool toggleToolScene = new ToggleTool(elementScene);
				toggleToolScene.setScenes(elementScene);
				for (ToggleTool toggleTool:toggleTools){
					if (toggleToolScene.equals(toggleTool)){
						toggleTool.setToggleOptionsScene(toggleToolScene.getToggleOptions());
						ToggleOption.setOptionsScenes(toggleTool.getToggleOptions(),elementScene);
						break;
					}
				}
			} catch (Exception e) {e.printStackTrace();}
		}
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
	@SuppressWarnings("rawtypes")
	public static Vector getNullMilkVarList(Element elementlist) {
		Vector<ToggleTool> things = new Vector<ToggleTool>();
		ToggleTool thing=new ToggleTool();
		Element elements = thing.getMilkElementList(elementlist);
		int size = (elements!=null)? elements.getChildNodes().getLength():0;
		for (int i=0;i<size;i++){ 
			Element tempE=null;
			tempE=thing.getMilkElement(elements,i);
			if (tempE != null){
				thing=new ToggleTool();
				thing.setNullValueFromNode(tempE);
				things.add(thing);
			}
		}
		return things;
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
		ToggleTool clone = (ToggleTool) super.clone();
		return clone;
	}
}