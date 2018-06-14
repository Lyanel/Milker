package modele.toggle;

import modele.MilkRs;
import modele.carac.Agent;
import modele.intel.Intel;

import java.util.Vector;

import org.w3c.dom.Element;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Toggle extends Intel implements Cloneable {

	public static final String noeud = "toggle";
	public String getNoeud() {return noeud;}

	private static Vector<Toggle> toggles;
	private static ObservableList<Toggle> modeltoggles;
	
	public static void setInfos(Vector<? extends Toggle> toggles, Vector<Element> elements) {
		for (Element element: elements) {
			try {
				Toggle test = new Toggle(element);
				test.setInfo(element);
				for (Toggle thing:toggles){
					if (test.getId().intValue() == thing.getId().intValue()){
						thing.setInfo(test.getInfo());
				//		thing.setToggleOptionsInfo(test.getToggleOptions());
						ToggleOption.setOptionsInfos(thing.getToggleOptions(),element);
						break;
					}
				}
			} catch (Exception e) {e.printStackTrace();}
		}
	}

	public static void setIcons(Vector<? extends Toggle> toggles, Vector<Element> elements) {
		for (Element element: elements) {
			try {
				Toggle test = new Toggle(element);
				test.setIcon(element);
				for (Toggle thing:toggles){
					if (test.getId().intValue() == thing.getId().intValue()){
						thing.setIcon(test.getIcon());
				//		thing.setToggleOptionsIcon(test.getToggleOptions());
						ToggleOption.setOptionsIcons(thing.getToggleOptions(),element);
						break;
					}
				}
			} catch (Exception e) {e.printStackTrace();}
		}
	}

	public static void setScenes(Vector<? extends Toggle> toggles, Vector<Element> elements) {
		for (Element element: elements) {
			try {
				Toggle test = new Toggle(element);
				test.setScenes(element);
				for (Toggle thing:toggles){
					if (test.getId().intValue() == thing.getId().intValue()){
				//		thing.setToggleOptionsScene(test.getToggleOptions());
						ToggleOption.setOptionsScenes(thing.getToggleOptions(),element);
						break;
					}
				}
			} catch (Exception e) {e.printStackTrace();}
		}
	}
	
	public static ObservableList<Toggle> getToggleListe() {
		if (modeltoggles==null){
			if (toggles==null){
				toggles = new Vector<Toggle>();
				toggles.add(ToggleTool.getTool());
				toggles.add(ToggleIdol.getIdol());
				toggles.add(ToggleEvent.getEvent());
			}
			modeltoggles = FXCollections.observableArrayList();
			if (toggles!=null){
				for (Toggle toggle:toggles){
					modeltoggles.add(toggle);
				}
			}
		}
		return modeltoggles;
	}

	// field

	private Agent agent;
	private Vector<ToggleOption> toggleOptions = null;	
	private ObservableList<ToggleOption> modelOptions = null;	

	// Constructors
	
	public Toggle() {
		super();
		this.agent = new Agent();
		this.toggleOptions = new Vector<ToggleOption>();
	}
	public Toggle(Element milkElement) {
		super();
		this.agent = new Agent();
		this.toggleOptions = new Vector<ToggleOption>();
		this.setValueFromNode(milkElement);
	}
	
	// Set values from Element methods
	
	@Override
	public void setValueFromNode(Element milkElement) {
		super.setValueFromNode(milkElement);
		this.setAgent(milkElement);
		this.setToggleOptions(milkElement);
	}
	public void setAgent(Element milkElement) {
		this.agent.setValueFromNode(milkElement);;
	}
	public void setToggleOptions(Element milkElement) {
		toggleOptions.addAll(ToggleOption.getMilkVarList(milkElement));
	}
	public void addOption(Element milkElement) {
		ToggleOption newOption = new ToggleOption(milkElement);
		newOption.setValueFromNode(milkElement);
		toggleOptions.add(newOption);
	}
	public void setScenes(Element milkElement) {
		this.setToggleOptionsScenes(milkElement);
	}
	public void setToggleOptionsScenes(Element milkElement) {
		toggleOptions.addAll(ToggleOption.getMilkVarList(milkElement));
	}
	
	// field methods

	@Override
	public Float getPriceValue() {
		return super.getPriceValue();
	}
	public Agent getAgent() {
		return this.agent;
	}
	public void setAgent(Agent agent) {
		this.agent = agent;
	}
	
	public Vector<ToggleOption> getToggleOptions() {
		return toggleOptions;
	}
	public void setToggleOptions(Vector<ToggleOption> toggleOptions) {
		this.toggleOptions = toggleOptions;
	}
	public void setToggleOptionsInfo(Vector<ToggleOption> toggleOptions) {
		if (this.toggleOptions!=null && toggleOptions!=null){
			for (ToggleOption toggleOption:this.toggleOptions) {
				if(toggleOptions.contains(toggleOption))
					toggleOption.setInfo(toggleOptions.get(toggleOptions.indexOf(toggleOption)).getInfo());
			}
		}
	}
	public void setToggleOptionsIcon(Vector<ToggleOption> toggleOptions) {
		if (this.toggleOptions!=null && toggleOptions!=null){
			for (ToggleOption toggleOption:this.toggleOptions) {
				if(toggleOptions.contains(toggleOption))
					toggleOption.setIcon(toggleOptions.get(toggleOptions.indexOf(toggleOption)).getIcon());
			}
		}
	}
	public void setToggleOptionsScene(Vector<ToggleOption> toggleOptions) {
		if (this.toggleOptions!=null && toggleOptions!=null){
			for (ToggleOption toggleOption:this.toggleOptions) {
				if(toggleOptions.contains(toggleOption))
					toggleOption.setScene(toggleOptions.get(toggleOptions.indexOf(toggleOption)).getScene());
			}
		}
	}
	
	public ObservableList<ToggleOption> getObservableOptions() {
		if (modelOptions==null){
			modelOptions = FXCollections.observableArrayList();
			for (ToggleOption option:toggleOptions){
				modelOptions.add(option);
			}
		}
		return modelOptions;
	}
	
	// toString & toXml methods
	
	@Override
	public String toStringStatChild() {
		String temp = super.toStringStatChild();
		if(toggleOptions.size()>0){
			temp += " "+ToggleOption.noeud+"s : "+MilkRs.LIGNE_BREAK;
			for (ToggleOption toggleOption : toggleOptions) {
				temp += MilkRs.LIGNE_TAB+toggleOption.toStringStat();
			}
		}
		return temp;
	}
	@Override
	public String toXmlStatChild() {
		String temp = super.toXmlStatChild();
		if(toggleOptions.size()>0){
			temp += "<"+ToggleOption.noeud+"s>"+MilkRs.LIGNE_BREAK;
			for (ToggleOption toggleOption : toggleOptions) {
				temp += MilkRs.LIGNE_TAB+toggleOption.toXmlStat();
			}
		temp += "</"+ToggleOption.noeud+"s>"+MilkRs.LIGNE_BREAK;
		}
		return temp;
	}
	
	// other object methods

	public Vector<ToggleOption> getCloneToggleOptions() throws CloneNotSupportedException {
		Vector<ToggleOption> clone = new Vector<ToggleOption>();
		if (this.toggleOptions!=null) for (ToggleOption toggleOption:this.toggleOptions) clone.add((ToggleOption) toggleOption.clone());
		return clone;
	}

	@Override
	public boolean allZero()  {
		boolean temp = super.allZero();
		if(this.agent!=null && !this.agent.allZero()) temp= false;
		if(this.toggleOptions!=null && this.toggleOptions.size()!=0) temp= false;
		return temp;
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		Toggle clone = (Toggle) super.clone();
		if (this.agent!=null) clone.setAgent((Agent) this.agent.clone());
		clone.setToggleOptions(getCloneToggleOptions());
		return clone;
	}
}