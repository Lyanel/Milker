package modele.toggle;

import modele.MilkRs;
import modele.carac.Agent;
import modele.intel.Intel;

import java.util.Vector;

import org.w3c.dom.Element;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Toggle extends Intel implements Cloneable {

	private static Vector<Toggle> toggles;
	public static final String noeud = "toggle";
	public String getNoeud() {return noeud;}

	
	public static ObservableList<Toggle> getListes() {
		if (toggles==null){
			toggles = new Vector<Toggle>();
			toggles.add(ToggleTool.getTool());
			toggles.add(ToggleIdol.getIdol());
			toggles.add(ToggleEvent.getEvent());
		}
		ObservableList<Toggle> clone = FXCollections.observableArrayList();
		if (toggles!=null){
			for (Toggle toggle:toggles){
				clone.add(toggle);
			}
		}
		return clone;
	}

	// field

	private Agent agent;
	private Vector<ToggleOption> toggleOptions = null;	

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
	@Override
	public void setNullValueFromNode(Element milkElement) {
		super.setNullValueFromNode(milkElement);
		this.setNullAgent(milkElement);
		this.setNullToggleOptions(milkElement);
	}
	
	public void setAgent(Element milkElement) {
		this.agent.setValueFromNode(milkElement);;
	}
	public void setNullAgent(Element milkElement) {
		this.agent.setValueFromNode(milkElement);
	}
	
	public void addOption(Element milkElement) {
		ToggleOption newOption = new ToggleOption(milkElement);
		newOption.setValueFromNode(milkElement);
		toggleOptions.add(newOption);
	}
	public void addNullOption(Element milkElement) {
		ToggleOption newOption = new ToggleOption();
		newOption.setNullValueFromNode(milkElement);
		toggleOptions.add(newOption);
	}
	public void setToggleOptions(Element milkElement) {
		toggleOptions.addAll(ToggleOption.getMilkVarList(milkElement));
	}
	@SuppressWarnings("unchecked")
	public void setNullToggleOptions(Element milkElement) {
		toggleOptions.addAll(ToggleOption.getNullMilkVarList(milkElement));
	}
	
	public void setScenes(Element milkElement) {
		this.setToggleOptionsScenes(milkElement);
	}
	public void setNullScenes(Element milkElement) {
		this.setNullToggleOptionsScenes(milkElement);
	}
	public void setToggleOptionsScenes(Element milkElement) {
		toggleOptions.addAll(ToggleOption.getMilkVarList(milkElement));
	}
	@SuppressWarnings("unchecked")
	public void setNullToggleOptionsScenes(Element milkElement) {
		toggleOptions.addAll(ToggleOption.getNullMilkVarList(milkElement));
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