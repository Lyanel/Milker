package modele.toggle;

import java.util.ArrayList;

import org.w3c.dom.Element;

import modele.XmlHelper;
import modele.carac.Bonus;

public class ToggleLevel extends ToggleScene {
	
	public static final String noeud = "level";
	public String getNoeud() {return noeud;}

	
	public static ArrayList<Element> getElementListfromParent(Element parent) {
		ArrayList<Element> temp = null;
		try {
			temp = XmlHelper.getChildrenListByTagName(XmlHelper.getOptionalChild(parent, noeud+"s"),noeud);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return (temp !=null)?temp:new ArrayList<Element>();
	}
	public static ArrayList<ToggleLevel> getMilkVarList(Element parent) {
		return getMilkVarList(getElementListfromParent(parent));
	}
	public static  ArrayList<ToggleLevel> getMilkVarList(ArrayList<Element> elementlist) {
		ArrayList<ToggleLevel> toggleLevels = new ArrayList<ToggleLevel>();
		if(elementlist != null) for (Element elementMilk: elementlist) {
			try {
				ToggleLevel toggleLevel = new ToggleLevel(elementMilk);
				toggleLevels.add(toggleLevel);
			} catch (Exception e) {e.printStackTrace();}
		}
		return toggleLevels;
	}
	
	public static void setLevelsInfos(ArrayList<ToggleLevel> toggleLevels, Element parent) {
		if(parent != null) for (Element elementlInfo: getElementListfromParent(parent)) {
			try {
				ToggleLevel test = new ToggleLevel(elementlInfo);
				test.setInfo(elementlInfo);
				for (ToggleLevel toggleLevel:toggleLevels){
					if (test.getId().intValue() == toggleLevel.getId().intValue()){
						toggleLevel.setInfo(test.getInfo());
						break;
					}
				}
			} catch (Exception e) {e.printStackTrace();}
		}/*
		ToggleLevel toggleLevel=new ToggleLevel();
		Element elements = toggleLevel.getMilkElementList(parent);
		int size = (elements!=null)? elements.getChildNodes().getLength():0;
		for (int i=0;i<size;i++){ 
			Element tempE=null;
			tempE=toggleLevel.getMilkElement(elements,i);
			if (tempE != null){
				toggleLevel=new ToggleLevel(tempE);
				if(toggleLevels.contains(toggleLevel)){
					toggleLevels.get(toggleLevels.indexOf(toggleLevel)).setInfo(tempE);
				}
			}
		}*/
	}
	
	public static void setLevelsIcons(ArrayList<ToggleLevel> toggleLevels, Element parent) {
		if(parent != null) for (Element elementlInfo: getElementListfromParent(parent)) {
				try {
					ToggleLevel test = new ToggleLevel(elementlInfo);
					test.setIcon(elementlInfo);
					for (ToggleLevel toggleLevel:toggleLevels){
						if (test.getId().intValue() == toggleLevel.getId().intValue()){
							toggleLevel.setIcon(test.getIcon());
							break;
						}
					}
				} catch (Exception e) {e.printStackTrace();}
			}
		/*
		ToggleLevel toggleLevel=new ToggleLevel();
		Element elements = toggleLevel.getMilkElementList(parent);
		int size = (elements!=null)? elements.getChildNodes().getLength():0;
		for (int i=0;i<size;i++){ 
			Element tempE=null;
			tempE=toggleLevel.getMilkElement(elements,i);
			if (tempE != null){
				toggleLevel=new ToggleLevel(tempE);
				if(toggleLevels.contains(toggleLevel)){
					toggleLevels.get(toggleLevels.indexOf(toggleLevel)).setIcon(tempE);
				}
			}
		}*/
	}
	
	public static void setLevelsScenes(ArrayList<ToggleLevel> toggleLevels, Element parent) {
		ArrayList<Element> temp = getElementListfromParent(parent);
		if(parent != null) for (Element elementlInfo: temp) {
			try {
				ToggleLevel test = new ToggleLevel(elementlInfo);
				test.setScene(elementlInfo);
				for (ToggleLevel toggleLevel:toggleLevels){
					if (test.getId().intValue() == toggleLevel.getId().intValue()){
						toggleLevel.setScene(test.getScene());
						break;
					}
				}
			} catch (Exception e) {e.printStackTrace();}
		}
		/*
		ToggleLevel toggleLevel=new ToggleLevel();
		Element elements = toggleLevel.getMilkElementList(parent);
		int size = (elements!=null)? elements.getChildNodes().getLength():0;
		for (int i=0;i<size;i++){ 
			Element tempE=null;
			tempE=toggleLevel.getMilkElement(elements,i);
			if (tempE != null){
				toggleLevel=new ToggleLevel(tempE);
				if(toggleLevels.contains(toggleLevel)){
					toggleLevels.get(toggleLevels.indexOf(toggleLevel)).setScene(tempE);
				}
			}
		}*/
	}

	// field
	
	private Bonus bonus;

	// Constructors
	
	public ToggleLevel() {
		super();
		this.bonus = new Bonus();
	}
	public ToggleLevel(Element milkElement) {
		super();
		this.bonus = new Bonus();
		this.setValueFromNode(milkElement);
	}
	public ToggleLevel(ToggleLevel original) {
		super(original);
		this.bonus = new Bonus(original.getBonus());
	}
	
	// Set value from Element methods
	
	@Override
	public void setValueFromNode(Element milkElement) {
		super.setValueFromNode(milkElement);
		this.setBonus(milkElement);
	}
	public void setBonus(Element milkElement) {
		this.bonus.setValueFromNode(milkElement);;
	}
	
	// field methods
	
	public Bonus getBonus() {
		return this.bonus;
	}
	public void setBonus(Bonus bonus) {
		this.bonus = bonus;
	}
	
	// toString & toXml methods
	
	@Override
	public String toStringStatChild() {
		String temp = super.toStringStatChild();
		if (this.bonus != null) temp += this.bonus.toStringStat();
		return temp;
	}
	@Override
	public String toXmlStatChild() {
		String temp = super.toXmlStatChild();
		if (this.bonus != null) temp += this.bonus.toXmlStat();
		return temp;
	}
	
	// other object methods

	@Override
	public boolean allZero()  {
		boolean temp = super.allZero();
		if(this.bonus!=null && !this.bonus.allZero()) temp= false;
		return temp;
	}
	
}