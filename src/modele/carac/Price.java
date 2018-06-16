package modele.carac;

import controleur.ParseMilkFile;

import org.w3c.dom.Element;

public class Price extends MilkCoin implements Cloneable {
	
	public static final String noeud = "price", xmlCoef = "coef", xmlSell = "sell";
	public String getNoeud() {return noeud;}
	private Float coef, sell;
		
	// Constructors
	
	public Price() {
		this((float) 0,(float) 0);
	}
	public Price(Float coef,Float sell) {
		super();
		this.setCoef(coef);
		this.setSell(sell);
	}
	public Price(Element milkElement) {
		super();
		this.setValueFromNode(milkElement);
	}
	
	// Set value from Element methods
	
	@Override
	public void setValueFromNode(Element milkElement) {
		Element thisElement = this.getThisOptionalChildFromParent(milkElement);
		super.setValueFromNode(thisElement);
		this.setCoef(thisElement);
		this.setSell(thisElement);
	}
	public void setCoef(Element milkElement) {
		Float temp=null;
		temp=ParseMilkFile.getXmlFloatAttribute(milkElement,xmlCoef);
		if (temp != null) this.coef=temp;
	}
	public void setSell(Element milkElement) {
		Float temp=null;
		temp=ParseMilkFile.getXmlFloatAttribute(milkElement,xmlSell);
		if (temp != null) this.sell=temp;
	}
	
	// field methods
	
	public Float getCoef() {
		return this.coef;
	}
	public String getStringCoef() {
		String temp = null;
		if (this.coef != null) temp = xmlCoef+" : "+this.coef+". ";
		return temp;
	}
	public String getXmlCoef() {
		String temp = null;
		if (this.coef != null) temp = " "+xmlCoef+"=\""+coef+"\"";
		return temp;
	}
	public void setCoef(Float coef) {
		this.coef = coef;
	}
	
	public Float getSell() {
		return this.sell;
	}
	public String getStringSell() {
		String temp = null;
		if (this.sell != null) temp = xmlSell+" : "+this.sell+". ";
		return temp;
	}
	public String getXmlSell() {
		String temp = null;
		if (this.sell != null) temp = " "+xmlSell+"=\""+sell+"\"";
		return temp;
	}
	public void setSell(Float sell) {
		this.sell = sell;
	}
	
	// toString & toXml methods
	
	@Override
	public String toStringAttrib() {
		String temp = super.toStringAttrib();
		temp+=this.getStringCoef();
		temp+=this.getStringSell();
		return temp;
	}
	@Override
	public String toXmlAttrib() {
		String temp = super.toXmlAttrib();
		temp+=this.getXmlCoef();
		temp+=this.getXmlSell();
		return temp;
	}
	
	// other object methods

	@Override
	public boolean allZero()  {
		boolean temp = super.allZero();
		if(this.coef!=null && this.coef!=0) temp= false;
		if(this.sell!=null && this.sell!=0) temp= false;
		return temp;
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		Price clone = (Price) super.clone();
		return clone;
	}
}
