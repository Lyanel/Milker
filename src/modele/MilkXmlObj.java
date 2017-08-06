package modele;

import org.w3c.dom.Element;

import controleur.GameModele;
import modele.carac.Need;

public class MilkXmlObj extends MilkObj implements Cloneable {
	
	private MilkInfo info;
	private Need need;
	
	// Constructors
	
	public MilkXmlObj() {
		super();
		this.info = new MilkInfo();
		this.getKind().setMod(0);
		this.need = new Need();
	}
	public MilkXmlObj(Element milkElement) {
		super();
		this.info = new MilkInfo();
		this.setValueFromNode(milkElement);
		this.getKind().setMod(0);
		this.need = new Need();
	}

	// Set value from Element methods
	
	@Override
	public void setValueFromNode(Element milkElement) {
		super.setValueFromNode(milkElement);
		this.setNeed(milkElement);
	}
	@Override
	public void setNullValueFromNode(Element milkElement) {
		super.setNullValueFromNode(milkElement);
		this.setNullNeed(milkElement);
	}
	
	@Override
	public void setTextValueFromNode(Element milkElement) {
		super.setTextValueFromNode(milkElement);
		this.setInfo(milkElement);
	}
	
	
	// field methods
	
	public Need getNeed() {
		return this.need;
	}
	public void setNeed(Need need) {
		this.need = need;
	}
	public void setNeed(Element milkElement) {
		this.need.setValueFromNode(milkElement);;
	}
	public void setNullNeed(Element milkElement) {
		this.need.setValueFromNode(milkElement);
	}
	public void addIntel(Element milkElement) {
		this.need.addIntel(milkElement);
	}
	public void addNullIntel(Element milkElement) {
		this.need.addNullIntel(milkElement);
	}
	
	public MilkInfo getInfo() {
		return this.info;
	}
	public void setInfo(MilkInfo info) {
		this.info = info;
	}
	public void setInfo(Element milkElement) {
		this.info.setTextValueFromNode(milkElement);;
	}
	
	// toString & toXml methods

	@Override
	public String toStringStatChild() {
		String temp = super.toStringStatChild();
		if (this.need != null) temp = this.need.toStringStat();
		return temp;
	}
	@Override
	public String toXmlStatChild() {
		String temp = super.toXmlStatChild();
		if (this.need != null) temp = this.need.toXmlStat();
		return temp;
	}
	
	@Override
	public String toStringTextChild() {
		String temp = super.toStringTextChild();
		if (this.info != null) temp += this.info.toStringTextChild();
		return temp;
	}
	@Override
	public String toXmlTextChild() {
		String temp = super.toXmlTextChild();
		if (this.info != null) temp += this.info.toXmlTextChild();
		return temp;
	}
	
	/**
	 * @return an html String containing this object node, attibutes & childs, mostly not need to be overide.
	 */
	@Override
	public final String toString() {
		return this.getXmlId()+" : "+this.getInfo().getName();
	}
	
	// other object methods

	@Override
	public boolean allZero()  {
		boolean temp = super.allZero();
		if(this.need!=null && !this.need.allZero()) temp= false;
		return temp;
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		MilkXmlObj clone = (MilkXmlObj) super.clone();
		if (this.need!=null) clone.setNeed((Need) this.need.clone());
		if (this.info!=null) clone.setInfo((MilkInfo) this.info.clone());
		return clone;
	}
}
