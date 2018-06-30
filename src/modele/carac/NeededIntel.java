package modele.carac;

import java.util.ArrayList;

import org.w3c.dom.Element;

import modele.XmlHelper;
import modele.baseObject.MilkObj;

public class NeededIntel extends MilkObj implements Cloneable {
	
	public static final String noeud = "intel";
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
	public static ArrayList<? extends NeededIntel> getMilkVarList(Element parent) {
		return getMilkVarList(getElementListfromParent(parent));
	}
	
	public static ArrayList<? extends NeededIntel> getMilkVarList(ArrayList<Element> elementlist) {
		ArrayList<NeededIntel> neededIntels = new ArrayList<NeededIntel>();
		if(elementlist != null) for (Element elementMilk: elementlist) {
			try {
				NeededIntel neededIntel = new NeededIntel(elementMilk);
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
		Element thisElement = this.getThisOptionalChildFromParent(milkElement);
		super.setValueFromNode(thisElement);
	}
	
	// other object methods
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		NeededIntel clone = (NeededIntel) super.clone();
		return clone;
	}
}
