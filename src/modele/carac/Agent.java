package modele.carac;

import java.util.ArrayList;

import org.w3c.dom.Element;

import modele.XmlHelper;

public class Agent extends NeededThing implements Cloneable {
	
	public static final String noeud = "agent";
	public String getNoeud() {return noeud;}

	public static ArrayList<Agent> getMilkVarList(Element parent) {
		return getMilkVarList(XmlHelper.getChildrenListByTagName(parent,noeud));
	}
	
	public static ArrayList<Agent> getMilkVarList(ArrayList<Element> elementlist) {
		ArrayList<Agent> agents = new ArrayList<Agent>();
		for (Element elementMilk: elementlist) {
			try {
				Agent agent = new Agent(elementMilk);
				agents.add(agent);
			} catch (Exception e) {e.printStackTrace();}
		}
		return agents;
	}

	// Constructors
	
	public Agent() {
		super();
	}
	public Agent(Element milkElement) {
		super();
		this.setValueFromNode(milkElement);
	}
	
	// Set value from Element methods
	
	@Override
	public void setValueFromNode(Element milkElement) {
		Element thisElement = this.getThisOptionalChildFromParent(milkElement);
		if(milkElement != thisElement) super.setValueFromNode(thisElement);
	}
	
	// other object methods
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		Agent clone = (Agent) super.clone();
		return clone;
	}
}
