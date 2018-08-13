package modele.carac;

import org.w3c.dom.Element;


public class Agent extends NeededThing {
	
	public static final String noeud = "agent";
	public String getNoeud() {return noeud;}

	// Constructors
	
	public Agent() {
		super();
	}
	public Agent(Element milkElement) {
		super();
		this.setValueFromNode(milkElement);
	}
	public Agent(Agent original) {
		super(original);
	}
	
	// Set value from Element methods
	
	@Override
	public void setValueFromNode(Element milkElement) {
		Element thisElement = this.getThisOptionalChildFromParent(milkElement);
		if(milkElement != thisElement) super.setValueFromNode(thisElement);
	}
	
	// other object methods
	
}
