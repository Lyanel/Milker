package modele;

import org.w3c.dom.Element;

public class MilkObj extends MilkId implements Cloneable {
	
	private MilkKind kind;

	// Constructors
	
	public MilkObj() {
		super();
		this.kind = new MilkKind();
	}
	public MilkObj(Element milkElement) {
		super();
		this.kind = new MilkKind();
		this.setValueFromNode(milkElement);
	}

	// Set value from Element methods
	
	@Override
	public void setValueFromNode(Element milkElement) {
		super.setValueFromNode(milkElement);
		this.setKind(milkElement);
	}
	@Override
	public void setNullValueFromNode(Element milkElement) {
		super.setNullValueFromNode(milkElement);
		this.setNullKind(milkElement);
	}
	
	// field methods
	
	public MilkKind getKind() {
		return this.kind;
	}
	public void setKind(Integer id) {
		if (this.kind == null) this.kind = new MilkKind();
		this.kind.setKind(id);
	}
	public void setKind(MilkKind kind) {
		this.kind = kind;
	}
	public void setKind(Element milkElement) {
		this.kind.setValueFromNode(milkElement);;
	}
	public void setNullKind(Element milkElement) {
		this.kind.setValueFromNode(milkElement);
	}
	
	// toString & toXml methods
	
	@Override
	public String toStringAttrib() {
		String temp = super.toStringAttrib();
		if (this.kind != null) temp += this.kind.toStringAttrib();
		return temp;
	}
	@Override
	public String toXmlAttrib() {
		String temp = super.toXmlAttrib();
		if (this.kind != null) temp += this.kind.toXmlAttrib();
		return temp;
	}
	
	// other object methods
	
	@Override
	public boolean allZero()  {
		boolean temp = super.allZero();
		if(this.kind!=null && !this.kind.allZero()) temp= false;
		return temp;
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		MilkObj clone = (MilkObj) super.clone();
		if (this.kind!=null) clone.setKind((MilkKind) this.kind.clone());
		return clone;
	}
}
