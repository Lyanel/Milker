package modele.toggle;

import modele.MilkRs;
import modele.baseObject.MilkPricedObj;
import modele.carac.Agent;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Element;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.util.Callback;

public class Toggle extends MilkPricedObj {

	public static final String noeud = "toggle";
	public String getNoeud() {return noeud;}

	private static ArrayList<Toggle> toggles;
	private static ObservableList<Toggle> modeltoggles;
	
	public static void setInfos(List<? extends Toggle> toggles, ArrayList<Element> elements) {
		for (Element element: elements) {
			try {
				Toggle test = new Toggle(element);
				test.setInfo(element);
				for (Toggle thing:toggles){
					if (test.getId().intValue() == thing.getId().intValue()){
						thing.setInfo(test.getInfo());
						ToggleOption.setOptionsInfos(thing.getToggleOptions(),element);
						break;
					}
				}
			} catch (Exception e) {e.printStackTrace();}
		}
	}

	public static void setIcons(ArrayList<? extends Toggle> toggles, ArrayList<Element> elements) {
		for (Element element: elements) {
			try {
				Toggle test = new Toggle(element);
				test.setIcon(element);
				for (Toggle thing:toggles){
					if (test.getId().intValue() == thing.getId().intValue()){
						thing.setIcon(test.getIcon());
						ToggleOption.setOptionsIcons(thing.getToggleOptions(),element);
						break;
					}
				}
			} catch (Exception e) {e.printStackTrace();}
		}
	}

	public static void setScenes(ArrayList<? extends Toggle> toggles, ArrayList<Element> elements) {
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

	public static Callback<Toggle, Observable[]> extractor() {
        return (Toggle p) -> new Observable[]{p.getInfo().getObrservableName()};
	}
	public static Callback<ToggleOption, Observable[]> extractorO() {
        return (ToggleOption p) -> new Observable[]{p.getInfo().getObrservableName()};
	}
	
	
	public static ObservableList<Toggle> getToggleListe() {
		if (modeltoggles==null)setObservableListe();
		return modeltoggles;
	}
	
	public static void setObservableListe() {
		modeltoggles = FXCollections.observableArrayList(extractor());
		if (toggles==null)setToggleListe();
		for (Toggle toggle:toggles){
			modeltoggles.add(new Toggle(toggle));
		}
		for (Toggle toggle:modeltoggles){
			toggle.setOptionSelected(toggle.getStart());
		}
	}
	
	private static void setToggleListe() {
		toggles = new ArrayList<Toggle>();
		toggles.add(ToggleTool.getToggle());
		toggles.add(ToggleIdol.getToggle());
		toggles.add(ToggleEvent.getToggle());
	}

	// field

	private Agent agent;
	private ArrayList<ToggleOption> toggleOptions = null;	
	private ObservableList<ToggleOption> modelOptions = null;	

	// Constructors
	
	public Toggle() {
		super();
		this.agent = new Agent();
		this.toggleOptions = new ArrayList<ToggleOption>();
	}
	public Toggle(Element milkElement) {
		super();
		this.agent = new Agent();
		this.toggleOptions = new ArrayList<ToggleOption>();
		this.setValueFromNode(milkElement);
	}
	public Toggle(Toggle original) {
		super(original);
		this.agent = new Agent(original.getAgent());
		this.setDeepOptions(original.getToggleOptions());
		this.setDeepObservableOptions(original.getObservableOptions());
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
		setOptionSelected(this.getStart().intValue());
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
	public Double getPriceValue() {
		return super.getPriceValue();
	}
	public Agent getAgent() {
		return this.agent;
	}
	public void setAgent(Agent agent) {
		this.agent = agent;
	}
	
	public ArrayList<ToggleOption> getToggleOptions() {
		return toggleOptions;
	}
	public void setToggleOptions(ArrayList<ToggleOption> toggleOptions) {
		this.toggleOptions = toggleOptions;
	}
	public void setDeepOptions(ArrayList<ToggleOption> original) {
		this.toggleOptions = new ArrayList<ToggleOption>();
		for (ToggleOption toggleOption:original) this.addToggleOption( new ToggleOption (toggleOption));
	}
	public void addToggleOption(ToggleOption toggleOption) {
		this.toggleOptions.add(toggleOption);
	}
	public void setToggleOptionsInfo(ArrayList<ToggleOption> toggleOptions) {
		if (this.toggleOptions!=null && toggleOptions!=null){
			for (ToggleOption toggleOption:this.toggleOptions) {
				if(toggleOptions.contains(toggleOption))
					toggleOption.setInfo(toggleOptions.get(toggleOptions.indexOf(toggleOption)).getInfo());
			}
		}
	}
	public void setToggleOptionsIcon(ArrayList<ToggleOption> toggleOptions) {
		if (this.toggleOptions!=null && toggleOptions!=null){
			for (ToggleOption toggleOption:this.toggleOptions) {
				if(toggleOptions.contains(toggleOption))
					toggleOption.setIcon(toggleOptions.get(toggleOptions.indexOf(toggleOption)).getIcon());
			}
		}
	}
	public void setToggleOptionsScene(ArrayList<ToggleOption> toggleOptions) {
		if (this.toggleOptions!=null && toggleOptions!=null){
			for (ToggleOption toggleOption:this.toggleOptions) {
				if(toggleOptions.contains(toggleOption))
					toggleOption.setScene(toggleOptions.get(toggleOptions.indexOf(toggleOption)).getScene());
			}
		}
	}
	
	public void setOptionSelected(ToggleOption value) {
		setOptionSelected(value.getId().intValue());
	}
	public void setOptionSelected(int optionId) {
		this.setStart(optionId);
		for (ToggleOption option : toggleOptions){
			if(option.getId().intValue()==optionId)option.setSelected(true);
			else option.setSelected(false);
		}
	}
	public ToggleOption getselectedOption() {
		ToggleOption value = new ToggleOption();
		for (ToggleOption option : toggleOptions){
			if(option.getId().intValue()==getStart().intValue()){
				value = option;
				break;
			}
		}
		return value;
	}
	
	public void setDeepObservableOptions(ObservableList<ToggleOption> observableList) {
		modelOptions = FXCollections.observableArrayList(extractorO());
		for (ToggleOption toggleOption:observableList) modelOptions.add(new ToggleOption (toggleOption));
	}
	public ObservableList<ToggleOption> getObservableOptions() {
		if (modelOptions==null){
			modelOptions = FXCollections.observableArrayList(extractorO());
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

	@Override
	public boolean allZero()  {
		boolean temp = super.allZero();
		if(this.agent!=null && !this.agent.allZero()) temp= false;
		if(this.toggleOptions!=null && this.toggleOptions.size()!=0) temp= false;
		return temp;
	}
	
}