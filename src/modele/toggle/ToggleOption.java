package modele.toggle;

import java.util.ArrayList;

import org.w3c.dom.Element;

import modele.MilkRs;
import modele.XmlHelper;

public class ToggleOption extends ToggleScene {
	
	public static final String noeud = "option";
	public String getNoeud() {return noeud;}
	
	public static ArrayList<Element> getElementListfromParent(Element parent) {
		ArrayList<Element> temp = null;
		try {
			temp = XmlHelper.getChildrenListByTagName(XmlHelper.getOptionalChild(parent, noeud+"s"),noeud);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return temp;
	}
	
	public static ArrayList<ToggleOption> getMilkVarList(Element parent) {
		return getMilkVarList(getElementListfromParent(parent));
	}
	public static ArrayList<ToggleOption> getMilkVarList(ArrayList<Element> elementlist) {
		ArrayList<ToggleOption> toggleOptions = new ArrayList<ToggleOption>();
		for (Element elementMilk: elementlist) {
			try {
				ToggleOption toggleOption = new ToggleOption(elementMilk);
				toggleOptions.add(toggleOption);
			} catch (Exception e) {e.printStackTrace();}
		}
		return toggleOptions;
	}
	
	public static void setOptionsInfos(ArrayList<ToggleOption> toggleOptions, Element parent) {
		for (Element elementlInfo: getElementListfromParent(parent)) {
			try {
				ToggleOption test = new ToggleOption(elementlInfo);
				test.setInfo(elementlInfo);
				for (ToggleOption toggleOption:toggleOptions){
					if (test.getId().intValue() == toggleOption.getId().intValue()){
						toggleOption.setInfo(test.getInfo());
						ToggleLevel.setLevelsInfos(toggleOption.getLevels(),elementlInfo);
						break;
					}
				}
			} catch (Exception e) {e.printStackTrace();}
		}
		/*		
		ToggleOption toggleOption=new ToggleOption();
		Element elements = toggleOption.getMilkElementList(parent);
		int size = (elements!=null)? elements.getChildNodes().getLength():0;
		for (int i=0;i<size;i++){ 
			Element tempE=null;
			tempE=toggleOption.getMilkElement(elements,i);
			if (tempE != null){
				toggleOption = new ToggleOption(tempE);
				if(toggleOptions.contains(toggleOption)){
					ToggleOption option = toggleOptions.get(toggleOptions.indexOf(toggleOption));
					option.setInfo(tempE);
					ToggleLevel.setLevelsInfos(option.getLevels(),tempE);
				}
			}
		}*/
	}
	
	public static void setOptionsIcons(ArrayList<ToggleOption> toggleOptions, Element parent) {
		for (Element elementlInfo: getElementListfromParent(parent)) {
			try {
				ToggleOption test = new ToggleOption(elementlInfo);
				test.setIcon(elementlInfo);
				for (ToggleOption toggleOption:toggleOptions){
					if (test.getId().intValue() == toggleOption.getId().intValue()){
						toggleOption.setIcon(test.getIcon());
						ToggleLevel.setLevelsIcons(toggleOption.getLevels(),elementlInfo);
						break;
					}
				}
			} catch (Exception e) {e.printStackTrace();}
		}
		/*		
		ToggleOption toggleOption=new ToggleOption();
		Element elements = toggleOption.getMilkElementList(parent);
		int size = (elements!=null)? elements.getChildNodes().getLength():0;
		for (int i=0;i<size;i++){ 
			Element tempE=null;
			tempE=toggleOption.getMilkElement(elements,i);
			if (tempE != null){
				toggleOption=new ToggleOption(tempE);
				if(toggleOptions.contains(toggleOption)){
					ToggleOption option = toggleOptions.get(toggleOptions.indexOf(toggleOption));
					option.setIcon(tempE);
					ToggleLevel.setLevelsIcons(option.getLevels(),tempE);
				}
			}
		}*/
	}
	
	public static void setOptionsScenes(ArrayList<ToggleOption> toggleOptions, Element parent) {
		for (Element elementlInfo: getElementListfromParent(parent)) {
			try {
				ToggleOption test = new ToggleOption(elementlInfo);
				test.setScene(elementlInfo);
				for (ToggleOption toggleOption:toggleOptions){
					if (test.getId().intValue() == toggleOption.getId().intValue()){
						toggleOption.setScene(test.getScene());
						ToggleLevel.setLevelsScenes(toggleOption.getLevels(),elementlInfo);
						break;
					}
				}
			} catch (Exception e) {e.printStackTrace();}
		}
		/*		
		ToggleOption toggleOption=new ToggleOption();
		Element elements = toggleOption.getMilkElementList(parent);
		int size = (elements!=null)? elements.getChildNodes().getLength():0;
		for (int i=0;i<size;i++){ 
			Element tempE=null;
			tempE=toggleOption.getMilkElement(elements,i);
			if (tempE != null){
				toggleOption=new ToggleOption(tempE);
				if(toggleOptions.contains(toggleOption)){
					ToggleOption option = toggleOptions.get(toggleOptions.indexOf(toggleOption));
					option.setScene(tempE);
					ToggleLevel.setLevelsScenes(option.getLevels(),tempE);
				}
			}
		}*/
	}

	// field

	private ArrayList<ToggleLevel> levels = null;
	private boolean selected;
	
	// Constructors

	public ToggleOption() {
		super();
		selected = false;
		this.levels = new ArrayList<ToggleLevel>();
	}
	public ToggleOption(Element milkElement) {
		super();
		selected = false;
		this.levels = new ArrayList<ToggleLevel>();
		this.setValueFromNode(milkElement);
	}
	public ToggleOption(ToggleOption original) {
		super(original);
		this.selected = original.isSelected();
		this.setDeeplevels(original.getLevels());
	}

	// Set value from Element methods

	@Override
	public void setValueFromNode(Element milkElement) {
		super.setValueFromNode(milkElement);
		this.setToggleLevels(milkElement);
	}
	public void setToggleLevels(Element milkElement) {
		levels.addAll(ToggleLevel.getMilkVarList(milkElement));
	}
	public void addLevel(Element milkElement) {
		ToggleLevel newlevel = new ToggleLevel(milkElement);
		newlevel.setValueFromNode(milkElement);
		levels.add(newlevel);
	}
	public void setToggleOptionsScenes(Element milkElement) {
		levels.addAll(ToggleLevel.getMilkVarList(milkElement));
	}
	
	// field methods
	
	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	
	public ArrayList<ToggleLevel> getLevels() {
		return levels;
	}
	public void setLevels(ArrayList<ToggleLevel> levels) {
		this.levels = levels;
	}
	public void setDeeplevels(ArrayList<ToggleLevel> original) {
		this.levels = new ArrayList<ToggleLevel>();
		for (ToggleLevel level:original) this.addToggleLevel( new ToggleLevel (level));
	}
	public void addToggleLevel(ToggleLevel toggleLevel) {
		this.levels.add(toggleLevel);
	}
	public ToggleLevel getLevel() {
		ToggleLevel level = levels.get(0);
		for (ToggleLevel test : levels){
			if(test.getLvl().intValue() == this.getLvl().intValue()){
				level = test;
				break;
			}
		}
		return level;
	}

	public void setLevelsInfo(ArrayList<ToggleLevel> toggleLevels) {
		if (this.levels!=null && toggleLevels!=null){
			for (ToggleLevel toggleLevel:this.levels) {
				if(toggleLevels.contains(toggleLevel))
					toggleLevel.setInfo(toggleLevels.get(toggleLevels.indexOf(toggleLevel)).getInfo());
			}
		}
	}
	public void setLevelsIcon(ArrayList<ToggleLevel> toggleLevels) {
		if (this.levels!=null && toggleLevels!=null){
			for (ToggleLevel toggleLevel:this.levels) {
				if(toggleLevels.contains(toggleLevel))
					toggleLevel.setIcon(toggleLevels.get(toggleLevels.indexOf(toggleLevel)).getIcon());
			}
		}
	}
	public void setLevelsScene(ArrayList<ToggleLevel> toggleLevels) {
		if (this.levels!=null && toggleLevels!=null){
			for (ToggleLevel toggleLevel:this.levels) {
				if(toggleLevels.contains(toggleLevel))
					toggleLevel.setScene(toggleLevels.get(toggleLevels.indexOf(toggleLevel)).getScene());
			}
		}
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

	@Override
	public boolean allZero()  {
		boolean temp = super.allZero();
		if(this.levels!=null && this.levels.size()!=0) temp= false;
		return temp;
	}
	
}