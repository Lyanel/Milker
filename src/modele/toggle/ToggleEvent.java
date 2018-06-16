package modele.toggle;

import modele.MilkFile;
import modele.MilkImage;
import modele.MilkInterface;
import modele.XmlHelper;

import java.util.ArrayList;

import org.w3c.dom.Element;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ToggleEvent extends Toggle implements Cloneable {

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
	
	public static ToggleEvent getEvent() {
		if(event==null){
			if(events==null) setMilkVarFromFiles();
			try {
				event=(ToggleEvent) events.get(0).clone();
			} catch (CloneNotSupportedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return event;
	}
	
	public static ObservableList<ToggleOption> getOptionListes() {
		if (event==null) ToggleEvent.getEvent();
		ObservableList<ToggleOption> clone = FXCollections.observableArrayList();
		ArrayList<ToggleOption> optionlist;
		optionlist = event.getToggleOptions();
		for (ToggleOption option:optionlist){
			clone.add(option);
		}
		return clone;
	}

	// Constructors
	
	public ToggleEvent() {
		super();
	}
	public ToggleEvent(Element milkElement) {
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
		ToggleEvent clone = (ToggleEvent) super.clone();
		return clone;
	}
}