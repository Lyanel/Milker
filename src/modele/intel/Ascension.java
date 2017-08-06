package modele.intel;

import org.w3c.dom.Element;

import modele.MilkKind;

public class Ascension extends Intel implements Cloneable {
	
	public static final String noeud = "ascension";
	public String getNoeud() {return noeud;}

	// Constructors
	
	public Ascension() {
		super();
		this.setKind(MilkKind.kind_Ascension);
	}
	public Ascension(Element milkElement) {
		super();
		this.setValueFromNode(milkElement);
		this.setKind(MilkKind.kind_Ascension);
	}
	
	// other object methods
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		Ascension clone = (Ascension) super.clone();
		return clone;
	}
}
