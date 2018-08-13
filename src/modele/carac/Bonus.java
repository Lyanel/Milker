package modele.carac;

import org.w3c.dom.Element;

import modele.baseObject.MilkKind;

public class Bonus extends MilkKind {
	
	public static final String noeud = "bonus";
	public String getNoeud() {return noeud;}

	// Fields
	
	private MilkProd attrib;
		
	// Constructors
	
	public Bonus() {
		super();
		this.attrib = new MilkProd();
	}
	public Bonus(Float quant,Float qual) {
		super();
		this.attrib = new MilkProd();
	}
	public Bonus(Element milkElement) {
		super();
		this.attrib = new MilkProd();
		this.setValueFromNode(milkElement);
	}
	public Bonus(Bonus original) {
		super(original);
		this.attrib = new MilkProd(original.getAttrib());
	}
	
	// Set value from Element methods
	
	@Override
	public void setValueFromNode(Element milkElement) {
		Element thisElement = this.getThisOptionalChildFromParent(milkElement);
		super.setValueFromNode(thisElement);
		this.setAttrib(thisElement);
	}
	public void setAttrib(Element milkElement) {
		this.attrib.setValueFromNode(milkElement);
	}
	
	// field methods
	
	public MilkProd getAttrib() {
		return this.attrib;
	}
	public void setAttrib(MilkProd attrib) {
		this.attrib = attrib;
	}
	
	// toString & toXml methods
	
	@Override
	public String toStringAttrib() {
		String temp = super.toStringAttrib();
		if (this.attrib != null) temp = this.attrib.toStringAttrib();
		return temp;
	}
	@Override
	public String toXmlAttrib() {
		String temp = super.toXmlAttrib();
		if (this.attrib != null) temp = this.attrib.toXmlAttrib();
		return temp;
	}
	
	@Override
	public String toStringStatChild() {
		String temp = super.toXmlStatChild();
		if (this.attrib != null) temp = this.attrib.toXmlStatChild();
		return temp;
	}
	@Override
	public String toXmlStatChild() {
		String temp = super.toXmlStatChild();
		if (this.attrib != null) temp = this.attrib.toXmlStatChild();
		return temp;
	}
	
	// other object methods

	@Override
	public boolean allZero()  {
		boolean temp = super.allZero();
		if(this.attrib!=null && !this.attrib.allZero()) temp= false;
		return temp;
	}
	
}
