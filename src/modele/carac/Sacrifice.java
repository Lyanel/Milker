package modele.carac;

import org.w3c.dom.Element;

public class Sacrifice extends NeededThing implements Cloneable {
	
	public static final String noeud = "sacrifice";
	public String getNoeud() {return noeud;}


	// Constructors
	
	public Sacrifice() {
		super();
	}
	public Sacrifice(Element milkElement) {
		super();
		this.setValueFromNode(milkElement);
	}
	
	// Set value from Element methods
	
	@Override
	public void setValueFromNode(Element milkElement) {
		Element thisElement = this.getThisOptionalChildFromParent(milkElement);
		if(thisElement != null) super.setValueFromNode(thisElement);
	}
	
	// other object methods
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		Sacrifice clone = (Sacrifice) super.clone();
		return clone;
	}
}
