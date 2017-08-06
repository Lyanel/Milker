package modele.carac;

import controleur.ParseMilkFile;

import org.w3c.dom.Element;

public class Income extends Bonus implements Cloneable {
	
	public static final String noeud = "income", xmlProd = "prod";
	public String getNoeud() {return noeud;}
	private Integer prod; //productivity (1/0 yes or no), sorry i was to leazy to convert a boolean from xml.
	private int mod; //Check prod->Xml : 0 no, 1 as attrib.

	// Constructors
	
	public Income() {
		super();
	}
	public Income(Integer prod,int mod) {
		super();
		this.setProd(prod);
		this.setMod(mod);
	}
	public Income(Element milkElement) {
		super();
		this.setValueFromNode(milkElement);
	}

	// Set value from Element methods
	
	@Override
	public void setValueFromNode(Element milkElement) {
		Element thisElement = this.getThisElementFromParent(milkElement);
		super.setValueFromNode(thisElement);
		this.setProd(thisElement);
	}
	@Override
	public void setNullValueFromNode(Element milkElement) {
		Element thisElement = this.getThisElementFromParent(milkElement);
		super.setNullValueFromNode(milkElement);
		this.setNullProd(thisElement);
	}
	
	// field methods
	
	public Integer getProd() {
		return this.prod;
	}
	public String getStringProd() {
		String temp = null;
		if (this.prod != null) temp = xmlProd+" : "+this.prod+". ";
		return temp;
	}
	public String getXmlProd() {
		System.out.println("Income.getXmlProd.prod : "+prod+".");
		String temp = null;
		if (this.prod != null) temp = " "+xmlProd+"=\""+prod+"\"";
		return temp;
	}
	public void setProd(Integer prod) {
		this.prod = prod;
	}
	public void setProd(Element milkElement) {
		Integer temp=null;
		temp=ParseMilkFile.getXmlIntAttribute(milkElement,xmlProd);
		if (temp != null) this.prod=temp;
	}
	public void setNullProd(Element milkElement) {
		prod = ParseMilkFile.getXmlIntAttribute(milkElement,xmlProd);
	}
	
	public int getMod() {
		return this.mod;
	}
	public void setMod(int mod) {
		this.mod = mod;
	}
	
	// toString & toXml methods
	
	@Override
	public String toStringAttrib() {
		String temp = super.toStringAttrib();
		if (this.mod == 1) temp+=this.getStringProd();
		return temp;
	}
	@Override
	public String toXmlAttrib() {
		String temp = super.toXmlAttrib();
		if (this.mod == 1) temp+=this.getXmlProd();
		return temp;
	}
	
	// other object methods

	@Override
	public boolean allZero()  {
		boolean temp = super.allZero();
		if(this.prod!=null && this.prod!=0) temp= false;
		return temp;
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		Income clone = (Income) super.clone();
		return clone;
	}
}
