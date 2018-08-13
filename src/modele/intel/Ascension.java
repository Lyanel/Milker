package modele.intel;

import org.w3c.dom.Element;

import modele.baseObject.MilkKind;

public class Ascension extends Intel {
	
	public static final String noeud = "ascension";
	public String getNoeud() {return noeud;}

	// Constructors
	
	public Ascension() {
		super();
		this.setKind(MilkKind.Ascension);
	}
	public Ascension(Element milkElement) {
		super();
		this.setValueFromNode(milkElement);
		this.setKind(MilkKind.Ascension);
	}
	public Ascension(Ascension original) {
		super(original);
	}
	
	// other object methods
	
}
