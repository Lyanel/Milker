package modele.carac;

import org.w3c.dom.Element;

public class Target extends Agent {

	public static final String noeud = "target";
	public String getNoeud() {return noeud;}

	// Constructors
	
	public Target() {
		super();
	}
	public Target(Element milkElement) {
		super();
		this.setValueFromNode(milkElement);
	}
	public Target(Target original) {
		super(original);
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
		Target clone = (Target) super.clone();
		return clone;
	}
	
}
