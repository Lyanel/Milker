package modele.baseObject;

import modele.ParseMilkFile;
import modele.carac.Price;
import modele.thing.SlaveHuman;

import org.w3c.dom.Element;

public class MilkPricedObj extends MilkXmlObj {
	
	public static final String noeud = "intel", xmlStart = "start";
	public String getNoeud() {return noeud;}
	
	// Fields
	
	private Integer start;
	private Price price;

	// Constructors
	
	public MilkPricedObj() {
		super();
		this.setStart(0);
		this.price = new Price();
	}
	public MilkPricedObj(Element milkElement) {
		super();
		this.setStart(0);
		this.price = new Price();
		this.setValueFromNode(milkElement);
	}
	public MilkPricedObj(MilkPricedObj original) {
		super(original);
		this.start = new Integer(original.getStart());
		this.price = new Price(original.getPrice());
	}

	// Set value from Element methods
	
	@Override
	public void setValueFromNode(Element milkElement) {
		super.setValueFromNode(milkElement);
		this.setStart(milkElement);
		this.setPrice(milkElement);
	}
	public void setStart(Element milkElement) {
		Integer temp=null;
		temp=ParseMilkFile.getXmlIntAttribute(milkElement,xmlStart);
		if (temp != null) this.start=temp;
	}
	public void setPrice(Element milkElement) {
		this.price.setValueFromNode(milkElement);;
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
	
	public Double getPriceValue() {
		return (double) (this.price.getCoin()*this.price.getCoef());
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

	public boolean bought()  {
		return (getStart().intValue()==1)?true:false;
	}

	public void buy() {
		setStart(1);
	}
	
	public boolean isVoluntarySlave() {
		return (this instanceof SlaveHuman && this.getId().intValue()==13);
	}
	
	@Override
	public boolean allZero()  {
		boolean temp = super.allZero();
		if(this.start!=null && this.start!=0) temp= false;
		if(this.price!=null && !this.price.allZero()) temp= false;
		return temp;
	}
}