package modele.carac;

import controleur.ParseMilkFile;
import modele.MilkVar;

import org.w3c.dom.Element;

public class MilkAttrib extends MilkVar implements Cloneable {
	
	public static final String xmlQuant = "quan", xmlQual = "qual";
	private Float quant, qual; //Quantity & Quality of milk production.
		
	// Constructors
	
	public MilkAttrib() {
		super();
	}
	public MilkAttrib(Float quant,Float qual) {
		super();
		this.setQuant(quant);
		this.setQual(qual);
	}
	public MilkAttrib(Element milkElement) {
		super();
		this.setValueFromNode(milkElement);
	}
	
	// Set value from Element methods
	
	@Override
	public void setValueFromNode(Element milkElement) {
		Element thisElement = this.getThisElementFromParent(milkElement);
		super.setValueFromNode(thisElement);
		this.setQuant(thisElement);
		this.setQual(thisElement);
	}
	@Override
	public void setNullValueFromNode(Element milkElement) {
		Element thisElement = this.getThisElementFromParent(milkElement);
		super.setValueFromNode(thisElement);
		this.setNullQuant(thisElement);
		this.setNullQual(thisElement);
	}
	
	// field methods
	
	public Float getQuant() {
		return this.quant;
	}
	public String getStringQuant() {
		String temp = "";
		if (this.quant != null) temp = xmlQuant+" : "+this.quant+". ";
		return temp;
	}
	public String getXmlQuant() {
		String temp = "";
		if (this.quant != null) temp = " "+xmlQuant+"=\""+quant+"\"";
		return temp;
	}
	public void setQuant(Float quant) {
		this.quant = quant;
	}
	public void setQuant(Element milkElement) {
		Float temp=null;
		temp=ParseMilkFile.getXmlFloatAttribute(milkElement,xmlQuant);
		if (temp != null) this.setQuant(temp);
	}
	public void setNullQuant(Element milkElement) {
		this.setQuant(ParseMilkFile.getXmlFloatAttribute(milkElement,xmlQuant));
	}
	
	public Float getQual() {
		return this.qual;
	}
	public String getStringQual() {
		String temp = "";
		if (this.qual != null) temp = xmlQual+" : "+this.qual+". ";
		return temp;
	}
	public String getXmlQual() {
		String temp = "";
		if (this.qual != null) temp = " "+xmlQual+"=\""+qual+"\"";
		return temp;
	}
	public void setQual(Float qual) {
		this.qual = qual;
	}
	public void setQual(Element milkElement) {
		Float temp=null;
		temp=ParseMilkFile.getXmlFloatAttribute(milkElement,xmlQual);
		if (temp != null) this.qual=temp;
	}
	public void setNullQual(Element milkElement) {
		qual = ParseMilkFile.getXmlFloatAttribute(milkElement,xmlQual);
	}
	
	// toString & toXml methods
	
	@Override
	public String toStringAttrib() {
		String temp = super.toStringAttrib();
		temp+=this.getStringQuant();
		temp+=this.getStringQual();
		return temp;
	}
	@Override
	public String toXmlAttrib() {
		String temp = super.toXmlAttrib();
		temp+=this.getXmlQuant();
		temp+=this.getXmlQual();
		return temp;
	}
	
	// other object methods

	@Override
	public boolean allZero()  {
		boolean temp = super.allZero();
		if(this.quant!=null && this.quant!=0) temp= false;
		if(this.qual!=null && this.qual!=0) temp= false;
		return temp;
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		MilkAttrib clone = (MilkAttrib) super.clone();
		return clone;
	}
}
