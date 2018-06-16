package modele.carac;

import org.w3c.dom.Element;

import modele.MilkKind;

public class Population extends MilkKind implements Cloneable {
	
	public static final String noeud = "pop";
	public String getNoeud() {return noeud;}
	private ThingAttrib attrib;

	// Constructors
	
	public Population() {
		super();
		this.attrib = new ThingAttrib();
	}
	public Population(Element milkElement) {
		super();
		this.attrib = new ThingAttrib();
		this.setValueFromNode(milkElement);
	}

	// Set value from Element methods
	
	@Override
	public void setValueFromNode(Element milkElement) {
		Element thisElement = this.getThisOptionalChildFromParent(milkElement);
		super.setValueFromNode(thisElement);
		this.setAttrib(thisElement);
		if(this.getAttrib().getQuant()>0)this.setMod(1);		
	}
	public void setAttrib(Element milkElement) {
		this.attrib.setValueFromNode(milkElement);
	}
	/*
	@Override
	public void setNullValueFromNode(Element milkElement) {
		Element thisElement = this.getThisElementFromParentNull(milkElement);
		super.setNullValueFromNode(thisElement);
		this.setNullAttrib(thisElement);
	}
	public void setNullAttrib(Element milkElement) {
		this.attrib.setNullValueFromNode(milkElement);
	}*/
	
	// field methods
	
	public ThingAttrib getAttrib() {
		return this.attrib;
	}
	public void setAttrib(ThingAttrib attrib) {
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
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		Population clone = (Population) super.clone();
		if (this.attrib!=null) clone.setAttrib((ThingAttrib) this.attrib.clone());
		return clone;
	}
}
