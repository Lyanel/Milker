package modele.toggle;

import java.util.Vector;

import org.w3c.dom.Element;

import modele.carac.Bonus;

public class ToggleLevel extends ToggleScene implements Cloneable {
	
	public static final String noeud = "level";
	public String getNoeud() {return noeud;}

	@SuppressWarnings("rawtypes")
	public static Vector getMilkVarList(Element elementlist) {
		Vector<ToggleLevel> toggleLevels = new Vector<ToggleLevel>();
		ToggleLevel toggleLevel=new ToggleLevel();
		Element elements = toggleLevel.getMilkElementList(elementlist);
		int size = (elements!=null)? elements.getChildNodes().getLength():0;
		for (int i=0;i<size;i++){ 
			Element tempE=null;
			tempE=toggleLevel.getMilkElement(elements,i);
			if (tempE != null){
				toggleLevel=new ToggleLevel();
				toggleLevel.setValueFromNode(tempE);
				toggleLevels.add(toggleLevel);
			}
		}
		return toggleLevels;
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Vector getMilkVarList(Vector<Element> elementlist) {
		Vector<ToggleLevel> toggleLevels = new Vector<ToggleLevel>();
		for (Element elementMilk: elementlist) {
			try {
				ToggleLevel toggleLevel = new ToggleLevel(elementMilk);
				toggleLevels.add(toggleLevel);
			} catch (Exception e) {e.printStackTrace();}
		}
		return toggleLevels;
	}
	/*
	@SuppressWarnings("rawtypes")
	public static Vector getNullMilkVarList(Element elementlist) {
		Vector<ToggleLevel> toggleLevels = new Vector<ToggleLevel>();
		ToggleLevel toggleLevel=new ToggleLevel();
		Element elements = toggleLevel.getMilkElementList(elementlist);
		int size = (elements!=null)? elements.getChildNodes().getLength():0;
		for (int i=0;i<size;i++){ 
			Element tempE=null;
			tempE=toggleLevel.getMilkElement(elements,i);
			if (tempE != null){
				toggleLevel=new ToggleLevel();
				toggleLevel.setNullValueFromNode(tempE);
				toggleLevels.add(toggleLevel);
			}
		}
		return toggleLevels;
	}
	@SuppressWarnings("rawtypes")
	public static Vector getNullMilkVarList(Vector<Element> elementlist) {
		Vector<ToggleLevel> toggleLevels = new Vector<ToggleLevel>();
		for (Element elementMilk: elementlist) {
			try {
				ToggleLevel toggleLevel = new ToggleLevel();
				toggleLevel.setNullValueFromNode(elementMilk);
				toggleLevels.add(toggleLevel);
			} catch (Exception e) {e.printStackTrace();}
		}
		return toggleLevels;
	}*/

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
	
	// Set value from Element methods
	
	@Override
	public void setValueFromNode(Element milkElement) {
		super.setValueFromNode(milkElement);
		this.setBonus(milkElement);
	}
	public void setBonus(Element milkElement) {
		this.bonus.setValueFromNode(milkElement);;
	}
	/*
	@Override
	public void setNullValueFromNode(Element milkElement) {
		super.setNullValueFromNode(milkElement);
		this.setNullBonus(milkElement);
	}
	public void setNullBonus(Element milkElement) {
		this.bonus.setNullValueFromNode(milkElement);
	}*/
	
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
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		ToggleLevel clone = (ToggleLevel) super.clone();
		if (this.bonus!=null) clone.setBonus((Bonus) this.bonus.clone());
		return clone;
	}
}