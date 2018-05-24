package modele.toggle;

import java.util.Vector;

import org.w3c.dom.Element;

import modele.MilkRs;

public class ToggleOption extends ToggleScene implements Cloneable {
	
	public static final String noeud = "option";
	public String getNoeud() {return noeud;}
	
	public static Vector<ToggleOption> getMilkVarList(Element elementlist) {
		Vector<ToggleOption> toggleOptions = new Vector<ToggleOption>();
		ToggleOption toggleOption=new ToggleOption();
		Element elements = toggleOption.getMilkElementList(elementlist);
		int size = (elements!=null)? elements.getChildNodes().getLength():0;
		for (int i=0;i<size;i++){ 
			Element tempE=null;
			tempE=toggleOption.getMilkElement(elements,i);
			if (tempE != null){
				toggleOption=new ToggleOption();
				toggleOption.setValueFromNode(tempE);
				toggleOptions.add(toggleOption);
			}
		}
		return toggleOptions;
	}
	
	public static Vector<ToggleOption> setOptionsInfos(Vector<ToggleOption> toggleOptions, Element elementlist) {
		ToggleOption toggleOption=new ToggleOption();
		Element elements = toggleOption.getMilkElementList(elementlist);
		int size = (elements!=null)? elements.getChildNodes().getLength():0;
		for (int i=0;i<size;i++){ 
			Element tempE=null;
			tempE=toggleOption.getMilkElement(elements,i);
			if (tempE != null){
				toggleOption=new ToggleOption(tempE);
				if(toggleOptions.contains(toggleOption)){
					toggleOptions.get(toggleOptions.indexOf(toggleOption)).setInfo(tempE);
				}
			}
		}
		return toggleOptions;
	}
	
	public static Vector<ToggleOption> setOptionsIcons(Vector<ToggleOption> toggleOptions, Element elementlist) {
		ToggleOption toggleOption=new ToggleOption();
		Element elements = toggleOption.getMilkElementList(elementlist);
		int size = (elements!=null)? elements.getChildNodes().getLength():0;
		for (int i=0;i<size;i++){ 
			Element tempE=null;
			tempE=toggleOption.getMilkElement(elements,i);
			if (tempE != null){
				toggleOption=new ToggleOption(tempE);
				if(toggleOptions.contains(toggleOption)){
					toggleOptions.get(toggleOptions.indexOf(toggleOption)).setIcon(tempE);
				}
			}
		}
		return toggleOptions;
	}
	
	public static Vector<ToggleOption> setOptionsScenes(Vector<ToggleOption> toggleOptions, Element elementlist) {
		ToggleOption toggleOption=new ToggleOption();
		Element elements = toggleOption.getMilkElementList(elementlist);
		int size = (elements!=null)? elements.getChildNodes().getLength():0;
		for (int i=0;i<size;i++){ 
			Element tempE=null;
			tempE=toggleOption.getMilkElement(elements,i);
			if (tempE != null){
				toggleOption=new ToggleOption(tempE);
				if(toggleOptions.contains(toggleOption)){
					toggleOptions.get(toggleOptions.indexOf(toggleOption)).setScene(tempE);
				}
			}
		}
		return toggleOptions;
	}
	
	@SuppressWarnings("rawtypes")
	public static Vector getNullMilkVarList(Element elementlist) {
		Vector<ToggleOption> toggleOptions = new Vector<ToggleOption>();
		ToggleOption toggleOption=new ToggleOption();
		Element elements = toggleOption.getMilkElementList(elementlist);
		int size = (elements!=null)? elements.getChildNodes().getLength():0;
		for (int i=0;i<size;i++){ 
			Element tempE=null;
			tempE=toggleOption.getMilkElement(elements,i);
			if (tempE != null){
				toggleOption=new ToggleOption();
				toggleOption.setNullValueFromNode(tempE);
				toggleOptions.add(toggleOption);
			}
		}
		return toggleOptions;
	}
	@SuppressWarnings("rawtypes")
	public static Vector getMilkVarList(Vector<Element> elementlist) {
		Vector<ToggleOption> toggleOptions = new Vector<ToggleOption>();
		for (Element elementMilk: elementlist) {
			try {
				ToggleOption toggleOption = new ToggleOption(elementMilk);
				toggleOptions.add(toggleOption);
			} catch (Exception e) {e.printStackTrace();}
		}
		return toggleOptions;
	}
	@SuppressWarnings("rawtypes")
	public static Vector getNullMilkVarList(Vector<Element> elementlist) {
		Vector<ToggleOption> toggleOptions = new Vector<ToggleOption>();
		for (Element elementMilk: elementlist) {
			try {
				ToggleOption toggleOption = new ToggleOption();
				toggleOption.setNullValueFromNode(elementMilk);
				toggleOptions.add(toggleOption);
			} catch (Exception e) {e.printStackTrace();}
		}
		return toggleOptions;
	}

	// field

	private Vector<ToggleLevel> levels = null;	
	
	// Constructors
	
	public ToggleOption() {
		super();
		this.levels = new Vector<ToggleLevel>();
	}
	public ToggleOption(Element milkElement) {
		super();
		this.levels = new Vector<ToggleLevel>();
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

	public void addLevel(Element milkElement) {
		ToggleLevel newlevel = new ToggleLevel(milkElement);
		newlevel.setValueFromNode(milkElement);
		levels.add(newlevel);
	}
	public void addNullLevel(Element milkElement) {
		ToggleLevel newlevel = new ToggleLevel();
		newlevel.setNullValueFromNode(milkElement);
		levels.add(newlevel);
	}
	
	@SuppressWarnings("unchecked")
	public void setLevels(Element milkElement) {
		this.levels.addAll(ToggleLevel.getMilkVarList(milkElement));
	}
	@SuppressWarnings("unchecked")
	public void setNullLevels(Element milkElement) {
		this.levels.addAll(ToggleLevel.getNullMilkVarList(milkElement));
	}
	
	// field methods
	
	public Vector<ToggleLevel> getLevels() {
		return levels;
	}
	public void setLevels(Vector<ToggleLevel> levels) {
		this.levels = levels;
	}
	
	// toString & toXml methods

	@Override
	public String toStringStatChild() {
		String temp = super.toStringStatChild();
		if(levels.size()>0){
			temp += MilkRs.LIGNE_TAB+ToggleLevel.noeud+"s : "+MilkRs.LIGNE_BREAK;
			for (ToggleLevel level : levels) {
				temp += MilkRs.LIGNE_TAB+level.toStringStat();
			}
		}
		return temp;
	}
	@Override
	public String toXmlStatChild() {
		String temp = super.toXmlStatChild();
		if(levels.size()>0){
			temp += "<"+ToggleLevel.noeud+"s>"+MilkRs.LIGNE_BREAK;
			for (ToggleLevel level : levels) {
				temp += MilkRs.LIGNE_TAB+level.toXmlStat();
			}
		temp += "</"+ToggleLevel.noeud+"s>"+MilkRs.LIGNE_BREAK;
		}
		return temp;
	}
	
	// other object methods
	
	public Vector<ToggleLevel> getCloneLevels() throws CloneNotSupportedException {
		Vector<ToggleLevel> clone = new Vector<ToggleLevel>();
		if (this.levels!=null) for (ToggleLevel level:this.levels) clone.add((ToggleLevel) level.clone());
		return clone;
	}

	@Override
	public boolean allZero()  {
		boolean temp = super.allZero();
		if(this.levels!=null && this.levels.size()!=0) temp= false;
		return temp;
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		ToggleOption clone = (ToggleOption) super.clone();
		clone.setLevels(getCloneLevels());
		return clone;
	}
}