package modele.carac;

import java.util.Vector;

import org.w3c.dom.Element;

import modele.MilkObj;

public class NeededIntel extends MilkObj implements Cloneable {
	
	public static final String noeud = "intel";
	public String getNoeud() {return noeud;}

	@SuppressWarnings("rawtypes")
	public static Vector getMilkVarList(Element elementlist) {
		Vector<NeededIntel> neededIntels = new Vector<NeededIntel>();
		NeededIntel neededIntel=new NeededIntel();
		Element elements = neededIntel.getMilkElementList(elementlist);
		int size = (elements!=null)? elements.getChildNodes().getLength():0;
		for (int i=0;i<size;i++){ 
			Element tempE=null;
			tempE=neededIntel.getMilkElement(elements,i);
			if (tempE != null){
				neededIntel=new NeededIntel();
				neededIntel.setValueFromNode(tempE);
				neededIntels.add(neededIntel);
			}
		}
		return neededIntels;
	}
	@SuppressWarnings("rawtypes")
	public static Vector getNullMilkVarList(Element elementlist) {
		Vector<NeededIntel> neededIntels = new Vector<NeededIntel>();
		NeededIntel neededIntel=new NeededIntel();
		Element elements = neededIntel.getMilkElementList(elementlist);
		int size = (elements!=null)? elements.getChildNodes().getLength():0;
		for (int i=0;i<size;i++){ 
			Element tempE=null;
			tempE=neededIntel.getMilkElement(elements,i);
			if (tempE != null){
				neededIntel=new NeededIntel();
				neededIntel.setNullValueFromNode(tempE);
				neededIntels.add(neededIntel);
			}
		}
		return neededIntels;
	}
	@SuppressWarnings("rawtypes")
	public static Vector getMilkVarList(Vector<Element> elementlist) {
		Vector<NeededIntel> neededIntels = new Vector<NeededIntel>();
		for (Element elementMilk: elementlist) {
			try {
				NeededIntel neededIntel = new NeededIntel(elementMilk);
				neededIntels.add(neededIntel);
			} catch (Exception e) {e.printStackTrace();}
		}
		return neededIntels;
	}
	@SuppressWarnings("rawtypes")
	public static Vector getNullMilkVarList(Vector<Element> elementlist) {
		Vector<NeededIntel> neededIntels = new Vector<NeededIntel>();
		for (Element elementMilk: elementlist) {
			try {
				NeededIntel neededIntel = new NeededIntel();
				neededIntel.setNullValueFromNode(elementMilk);
				neededIntels.add(neededIntel);
			} catch (Exception e) {e.printStackTrace();}
		}
		return neededIntels;
	}

	// Constructors

	public NeededIntel() {
		super();
		this.getKind().setMod(2);
	}
	public NeededIntel(Element elementMilk) {
		super(elementMilk);
		this.setValueFromNode(elementMilk);
		this.getKind().setMod(2);
	}

	// Set value from Element methods
	
	@Override
	public void setValueFromNode(Element milkElement) {
		Element thisElement = this.getThisElementFromParent(milkElement);
		super.setValueFromNode(thisElement);
	}
	@Override
	public void setNullValueFromNode(Element milkElement) {
		Element thisElement = this.getThisElementFromParent(milkElement);
		super.setNullValueFromNode(thisElement);
	}
	
	// other object methods
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		NeededIntel clone = (NeededIntel) super.clone();
		return clone;
	}
}
