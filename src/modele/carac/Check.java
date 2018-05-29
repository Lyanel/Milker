package modele.carac;

import org.w3c.dom.Element;

import controleur.ParseMilkFile;

public class Check extends Prerequisites implements Cloneable {

	public static final String noeud = "check", xmlMod = "mod";
	public String getNoeud() {return noeud;}

	// Fields
	
	private Integer mod;
	
	// Constructors
	
	public Check() {
		this.mod = 1;
	}
	public Check(Element milkElement) {
		super();
		this.mod = 1;
		this.setValueFromNode(milkElement);
	}

	// Set value from Element methods
	
	@Override
	public void setValueFromNode(Element milkElement) {
		Element thisElement = this.getThisElementFromParent(milkElement);
		super.setValueFromNode(thisElement);
		this.setMod(thisElement);
	}
	public void setMod(Element milkElement) {
		Integer temp=null;
		temp=ParseMilkFile.getXmlIntAttribute(milkElement,xmlMod);
		if (temp != null) this.mod=temp;
	}
	
	// field methods

	public Integer getMod() {
		return this.mod;
	}
	public String getStringMod() {
		String temp = null;
		if (this.mod != null) temp = xmlMod+" : "+this.mod+". ";
		return temp;
	}
	public String getXmlMod() {
		String temp = null;
		if (this.mod != null) temp = " "+xmlMod+"=\""+mod+"\"";
		return temp;
	}
	public void setMod(Integer mod) {
		this.mod = mod;
	}
	
	
	// toString & toXml methods
	
	@Override
	public String toStringAttrib() {
		String temp = super.toStringAttrib();
		temp+=this.getStringMod();
		return temp;
	}
	@Override
	public String toXmlAttrib() {
		String temp = super.toXmlAttrib();
		temp+=this.getXmlMod();
		return temp;
	}
	
	@Override
	public String toStringStatChild() {
		String temp = super.toStringStatChild();
		return temp;
	}
	@Override
	public String toXmlStatChild() {
		String temp = super.toXmlStatChild();
		return temp;
	}

	// other object methods
	

	@Override
	public boolean allZero()  {
		boolean temp = super.allZero();
		return temp;
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		Check clone = (Check) super.clone();
		return clone;
	}
}
