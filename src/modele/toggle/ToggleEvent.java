package modele.toggle;

import modele.MilkFile;
import modele.MilkImage;
import modele.MilkInterface;

import java.util.Vector;

import org.w3c.dom.Element;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ToggleEvent extends Toggle implements Cloneable {

	private static Vector<ToggleEvent> events;
	private static ToggleEvent event;
	public static final String file		= "ToggleEvent";
	
	private static Vector<ToggleEvent> setMilkVarFromFiles() {
		if (events==null) events = new Vector<ToggleEvent>();
		else events.removeAllElements();
		//Set stats
		Vector<Element> elementlist = new Vector<Element>();
		elementlist = MilkFile.getMilkElementsFromFiles(MilkFile.getXmlFilePath(file)+file, noeud);
		events = getMilkVarList(elementlist);
		//Set info
		Vector<Element> elementlInfos = new Vector<Element>();
		elementlInfos = MilkFile.getMilkElementsFromFiles(MilkInterface.getXmlLangPath()+file, noeud);
		setInfos(events, elementlInfos);
		//Set icon
		Vector<Element> elementlIcon = new Vector<Element>();
		elementlIcon = MilkFile.getMilkElementsFromFiles(MilkImage.getXmlIconsPath(file)+file, noeud);
		setIcons(events, elementlIcon);
		//Set scene
		Vector<Element> elementlScene = new Vector<Element>();
		elementlScene = MilkFile.getMilkElementsFromFiles(MilkImage.getXmlScenesPath(file)+file, noeud);
		setScenes(events, elementlScene);
		
		
		return events;
	}
	
	public static Vector<ToggleEvent> getMilkVarList(Vector<Element> elementlist) {
		Vector<ToggleEvent> things = new Vector<ToggleEvent>();
		for (Element elementMilk: elementlist) {
			try {
				ToggleEvent thing = new ToggleEvent(elementMilk);
				things.add(thing);
			} catch (Exception e) {e.printStackTrace();}
		}
		return things;
	}
	
	@SuppressWarnings("rawtypes")
	public static Vector getMilkVarList(Element elementlist) {
		Vector<ToggleEvent> things = new Vector<ToggleEvent>();
		ToggleEvent thing=new ToggleEvent();
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
	/*
	public static Vector<ToggleEvent> getNullMilkVarList(Vector<Element> elementlist) {
		Vector<ToggleEvent> things = new Vector<ToggleEvent>();
		for (Element elementMilk: elementlist) {
			try {
				ToggleEvent thing = new ToggleEvent();
				thing.setNullValueFromNode(elementMilk);
				things.add(thing);
			} catch (Exception e) {e.printStackTrace();}
		}
		return things;
	}
	@SuppressWarnings("rawtypes")
	public static Vector getNullMilkVarList(Element elementlist) {
		Vector<ToggleEvent> things = new Vector<ToggleEvent>();
		ToggleEvent thing=new ToggleEvent();
		Element elements = thing.getMilkElementList(elementlist);
		int size = (elements!=null)? elements.getChildNodes().getLength():0;
		for (int i=0;i<size;i++){ 
			Element tempE=null;
			tempE=thing.getMilkElement(elements,i);
			if (tempE != null){
				thing=new ToggleEvent();
				thing.setNullValueFromNode(tempE);
				things.add(thing);
			}
		}
		return things;
	}*/
	/*
	private static void setInfos(Vector<ToggleEvent> toggleEvents, Vector<Element> elementInfos) {
		for (Element elementInfo: elementInfos) {
			try {
				ToggleEvent toggleEventInfo = new ToggleEvent(elementInfo);
				toggleEventInfo.setInfo(elementInfo);
				for (ToggleEvent toggleEvent:toggleEvents){
					if (toggleEventInfo.equals(toggleEvent)){
						toggleEvent.setInfo(toggleEventInfo.getInfo());
						toggleEvent.setToggleOptionsInfo(toggleEventInfo.getToggleOptions());
						ToggleOption.setOptionsInfos(toggleEvent.getToggleOptions(),elementInfo);
						break;
					}
				}
			} catch (Exception e) {e.printStackTrace();}
		}
	}

	private static void setIcons(Vector<ToggleEvent> toggleEvents, Vector<Element> elementIcons) {
		for (Element elementIcon: elementIcons) {
			try {
				ToggleEvent toggleEventIcon = new ToggleEvent(elementIcon);
				toggleEventIcon.setIcon(elementIcon);
				for (ToggleEvent toggleEvent:toggleEvents){
					if (toggleEventIcon.equals(toggleEvent)){
						toggleEvent.setIcon(toggleEventIcon.getIcon());
						toggleEvent.setToggleOptionsIcon(toggleEventIcon.getToggleOptions());
						ToggleOption.setOptionsIcons(toggleEvent.getToggleOptions(),elementIcon);
						break;
					}
				}
			} catch (Exception e) {e.printStackTrace();}
		}
	}

	private static void setScenes(Vector<ToggleEvent> toggleEvents, Vector<Element> elementScenes) {
		for (Element elementScene: elementScenes) {
			try {
				ToggleEvent toggleEventScene = new ToggleEvent(elementScene);
				toggleEventScene.setScenes(elementScene);
				for (ToggleEvent toggleEvent:toggleEvents){
					if (toggleEventScene.equals(toggleEvent)){
						toggleEvent.setToggleOptionsScene(toggleEventScene.getToggleOptions());
						ToggleOption.setOptionsScenes(toggleEvent.getToggleOptions(),elementScene);
						break;
					}
				}
			} catch (Exception e) {e.printStackTrace();}
		}
	}*/
	
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
		Vector<ToggleOption> optionlist;
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
	}/*
	@Override
	public void setNullValueFromNode(Element milkElement) {
		super.setNullValueFromNode(milkElement);
	}*/
	
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