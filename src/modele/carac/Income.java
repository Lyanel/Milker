package modele.carac;

import org.w3c.dom.Element;

import modele.ParseMilkFile;

public class Income extends MilkCoin {
	
	public static final String noeud = "income", xmlProd = "prod";
	public String getNoeud() {return noeud;}
	
	// Fields
	
	private MilkProd attrib;
	private Integer prod; //productivity (1/0 yes or no), sorry i was to leazy to convert a boolean from xml.
	private int mod; //Check prod->Xml : 0 no, 1 as attrib.

	// Constructors
	
	public Income() {
		super();
		this.attrib = new MilkProd();
	}
	public Income(Integer prod,int mod) {
		super();
		this.attrib = new MilkProd();
		this.setProd(prod);
		this.setMod(mod);
	}
	public Income(Element milkElement) {
		super();
		this.attrib = new MilkProd();
		this.setValueFromNode(milkElement);
	}
	public Income(Income original) {
		super(original);
		this.mod = original.getMod();
		if (original.getProd()!=null)this.prod = new Integer(original.getProd());
		this.attrib = new MilkProd(original.getAttrib());
	}

	// Set value from Element methods
	
	@Override
	public void setValueFromNode(Element milkElement) {
		Element thisElement = this.getThisOptionalChildFromParent(milkElement);
		super.setValueFromNode(thisElement);
		this.setAttrib(thisElement);
		this.setProd(thisElement);
	}
	public void setAttrib(Element milkElement) {
		this.attrib.setValueFromNode(milkElement);
	}
	public void setProd(Element milkElement) {
		Integer temp=0;
		temp=ParseMilkFile.getXmlIntAttribute(milkElement,xmlProd);
		if (temp != null) this.prod=temp;
	}
	
	// field methods
	
	public MilkProd getAttrib() {
		return this.attrib;
	}
	public void setAttrib(MilkProd attrib) {
		this.attrib = attrib;
	}
	
	public Integer getProd() {
		return this.prod;
	}
	public boolean canProdMilk() {
		return (prod!=null && prod==1);
	}
	public boolean canProdCoin() {
		return (prod!=null && prod==2);
	}
	
	public String getStringProd() {
		String temp = null;
		if (this.prod != null) temp = xmlProd+" : "+this.prod+". ";
		return temp;
	}
	public String getXmlProd() {
		String temp = null;
		if (this.prod != null) temp = " "+xmlProd+"=\""+prod+"\"";
		return temp;
	}
	public void setProd(Integer prod) {
		this.prod = prod;
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
		if (this.attrib != null) temp = this.attrib.toStringAttrib();
		if (this.mod == 1) temp+=this.getStringProd();
		return temp;
	}
	@Override
	public String toXmlAttrib() {
		String temp = super.toXmlAttrib();
		if (this.attrib != null) temp = this.attrib.toXmlAttrib();
		if (this.mod == 1) temp+=this.getXmlProd();
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
		if(this.prod!=null && this.prod!=0) temp= false;
		return temp;
	}
}
