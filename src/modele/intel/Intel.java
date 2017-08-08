package modele.intel;

import modele.MilkXmlObj;
import modele.carac.Price;

import org.w3c.dom.Element;

import controleur.ParseMilkFile;

public class Intel extends MilkXmlObj implements Cloneable {
	
	public static final String noeud = "intel", xmlStart = "start";
	public String getNoeud() {return noeud;}
	private Integer start;
	private Price price;

	// Constructors
	
	public Intel() {
		super();
		this.setStart(0);
		this.price = new Price();
	}
	public Intel(Element milkElement) {
		super();
		this.setStart(0);
		this.price = new Price();
		this.setValueFromNode(milkElement);
	}

	// Set value from Element methods
	
	@Override
	public void setValueFromNode(Element milkElement) {
		super.setValueFromNode(milkElement);
		this.setStart(milkElement);
		this.setPrice(milkElement);
	}
	@Override
	public void setNullValueFromNode(Element milkElement) {
		super.setNullValueFromNode(milkElement);
		this.setNullStart(milkElement);
		this.setNullPrice(milkElement);
	}
	
	// field methods

	public Integer getStart() {
		return this.start;
	}
	public String getStringStart() {
		String temp = null;
		if (this.start != null) temp = xmlStart+" : "+this.start+". ";
		return temp;
	}
	public String getXmlStart() {
		String temp = null;
		if (this.start != null) temp = " "+xmlStart+"=\""+start+"\"";
		return temp;
	}
	public void setStart(Integer start) {
		this.start = start;
	}
	public void setStart(Element milkElement) {
		Integer temp=null;
		temp=ParseMilkFile.getXmlIntAttribute(milkElement,xmlStart);
		if (temp != null) this.start=temp;
	}
	public void setNullStart(Element milkElement) {
		start = ParseMilkFile.getXmlIntAttribute(milkElement,xmlStart);
	}

	public Float getPriceValue() {
		return this.price.getCoin()*this.price.getCoef();
	}
	public Price getPrice() {
		return this.price;
	}
	public void setSellPrice(Float sell) {
		this.price.setSell(sell);
	}
	public void setPrice(Price price) {
		this.price = price;
	}
	public void setPrice(Element milkElement) {
		this.price.setValueFromNode(milkElement);;
	}
	public void setNullPrice(Element milkElement) {
		this.price.setValueFromNode(milkElement);
	}
	
	// toString & toXml methods
	
	@Override
	public String toStringAttrib() {
		String temp = super.toStringAttrib();
		temp+=this.getStringStart();
		return temp;
	}
	@Override
	public String toXmlAttrib() {
		String temp = super.toXmlAttrib();
		temp+=this.getXmlStart();
		return temp;
	}
	
	@Override
	public String toStringStatChild() {
		String temp = super.toStringStatChild();
		if (this.price != null) temp = this.price.toStringStat();
		return temp;
	}
	@Override
	public String toXmlStatChild() {
		String temp = super.toXmlStatChild();
		if (this.price != null) temp = this.price.toXmlStat();
		return temp;
	}
	
	// other object methods

	public boolean buyable()  {
		return true;
	}

	public boolean isBought() {
		// TODO Auto-generated method stub
		return false;
	}

	public void buy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean allZero()  {
		boolean temp = super.allZero();
		if(this.start!=null && this.start!=0) temp= false;
		if(this.price!=null && !this.price.allZero()) temp= false;
		return temp;
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		Intel clone = (Intel) super.clone();
		if (this.price!=null) clone.setPrice((Price) this.price.clone());
		return clone;
	}
}