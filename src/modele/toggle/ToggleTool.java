package modele.toggle;

import java.util.ArrayList;

import org.w3c.dom.Element;

import modele.XmlHelper;
import modele.baseObject.MilkFile;
import modele.baseObject.MilkImage;
import modele.baseObject.MilkInterface;

public class ToggleTool extends Toggle {

	private static ArrayList<ToggleTool> tools;
	private static ToggleTool tool;
	public static final String file		= "ToggleTool";
	
	private static ArrayList<ToggleTool> setMilkVarFromFiles() {
		if (tools==null) tools = new ArrayList<ToggleTool>();
		else tools.clear();
		//Set stats
		ArrayList<Element> elementlist = new ArrayList<Element>();
		elementlist = MilkFile.getMilkElementsFromFiles(MilkFile.getXmlFilePath(file)+file, noeud);
		tools = getMilkVarList(elementlist);
		//Set info
		ArrayList<Element> elementlInfos = new ArrayList<Element>();
		elementlInfos = MilkFile.getMilkElementsFromFiles(MilkInterface.getXmlLangPath()+file, noeud);
		setInfos(tools, elementlInfos);
		//Set icon
		ArrayList<Element> elementlIcon = new ArrayList<Element>();
		elementlIcon = MilkFile.getMilkElementsFromFiles(MilkImage.getXmlIconsPath(file)+file, noeud);
		setIcons(tools, elementlIcon);
		//Set scene
		ArrayList<Element> elementlScene = new ArrayList<Element>();
		elementlScene = MilkFile.getMilkElementsFromFiles(MilkImage.getXmlScenesPath(file)+file, noeud);
		setScenes(tools, elementlScene);
		
		
		return tools;
	}

	public static ArrayList<ToggleTool> getMilkVarList(Element parent) {
		return getMilkVarList(XmlHelper.getChildrenListByTagName(parent,noeud));
	}
	public static ArrayList<ToggleTool> getMilkVarList(ArrayList<Element> elementlist) {
		ArrayList<ToggleTool> things = new ArrayList<ToggleTool>();
		for (Element elementMilk: elementlist) {
			try {
				ToggleTool thing = new ToggleTool(elementMilk);
				things.add(thing);
			} catch (Exception e) {e.printStackTrace();}
		}
		return things;
	}
	
	public static ToggleTool getToggle() {
		if(tool==null){
			if(tools==null) setMilkVarFromFiles();
			tool=new ToggleTool(tools.get(0));
		}
		return tool;
	}
	
	public static void updateInfoFromFiles() {
		ArrayList<Element> elementlInfos = new ArrayList<Element>();
		elementlInfos = MilkFile.getMilkElementsFromFiles(MilkInterface.getXmlLangPath()+file, noeud);
		setInfos(Toggle.getToggleListe(), elementlInfos);
	}
	
/*	public static ObservableList<ToggleOption> getOptionListes() {
		if (tool==null) ToggleTool.getTool();
		ObservableList<ToggleOption> clone = FXCollections.observableArrayList();
		ArrayList<ToggleOption> optionlist;
		optionlist = tool.getToggleOptions();
		for (ToggleOption option:optionlist){
			clone.add(option);
		}
		return clone;
	}*/

	// Constructors
	
	public ToggleTool() {
		super();
	}
	public ToggleTool(Element milkElement) {
		super();
		this.setValueFromNode(milkElement);
	}
	public ToggleTool(ToggleTool original) {
		super(original);
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
	
}