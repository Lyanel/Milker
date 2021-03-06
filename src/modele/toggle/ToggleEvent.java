package modele.toggle;

import java.util.ArrayList;

import org.w3c.dom.Element;

import modele.XmlHelper;
import modele.baseObject.MilkFile;
import modele.baseObject.MilkImage;
import modele.baseObject.MilkInterface;

public class ToggleEvent extends Toggle {

	private static ArrayList<ToggleEvent> events;
	private static ToggleEvent event;
	public static final String file		= "ToggleEvent";
	
	private static ArrayList<ToggleEvent> setMilkVarFromFiles() {
		if (events==null) events = new ArrayList<ToggleEvent>();
		else events.clear();
		//Set stats
		ArrayList<Element> elementlist = new ArrayList<Element>();
		elementlist = MilkFile.getMilkElementsFromFiles(MilkFile.getXmlFilePath(file)+file, noeud);
		events = getMilkVarList(elementlist);
		//Set info
		ArrayList<Element> elementlInfos = new ArrayList<Element>();
		elementlInfos = MilkFile.getMilkElementsFromFiles(MilkInterface.getXmlLangPath()+file, noeud);
		setInfos(events, elementlInfos);
		//Set icon
		ArrayList<Element> elementlIcon = new ArrayList<Element>();
		elementlIcon = MilkFile.getMilkElementsFromFiles(MilkImage.getXmlIconsPath(file)+file, noeud);
		setIcons(events, elementlIcon);
		//Set scene
		ArrayList<Element> elementlScene = new ArrayList<Element>();
		elementlScene = MilkFile.getMilkElementsFromFiles(MilkImage.getXmlScenesPath(file)+file, noeud);
		setScenes(events, elementlScene);
		
		
		return events;
	}
	
	public static ArrayList<ToggleEvent> getMilkVarList(Element parent) {
		return getMilkVarList(XmlHelper.getChildrenListByTagName(parent,noeud));
	}
	
	public static ArrayList<ToggleEvent> getMilkVarList(ArrayList<Element> elementlist) {
		ArrayList<ToggleEvent> things = new ArrayList<ToggleEvent>();
		for (Element elementMilk: elementlist) {
			try {
				ToggleEvent thing = new ToggleEvent(elementMilk);
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
	
	public static ToggleEvent getToggle() {
		if(event==null){
			if(events==null) setMilkVarFromFiles();
			event=new ToggleEvent(events.get(0));
		}
		return event;
	}
	
	/*public static ObservableList<ToggleOption> getOptionListes() {
		if (event==null) ToggleEvent.getEvent();
		ObservableList<ToggleOption> clone = FXCollections.observableArrayList();
		ArrayList<ToggleOption> optionlist;
		optionlist = event.getToggleOptions();
		for (ToggleOption option:optionlist){
			clone.add(option);
		}
		return clone;
	}*/

	// Constructors
	
	public ToggleEvent() {
		super();
	}
	public ToggleEvent(Element milkElement) {
		super();
		this.setValueFromNode(milkElement);
	}
	public ToggleEvent(ToggleEvent original) {
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