package modele.carac;

import controleur.ParseMilkFile;
import modele.MilkVar;

import org.w3c.dom.Element;

public class MilkCoin extends MilkVar implements Cloneable {
	
	public static final String xmlCoin = "coin";
	private Float coin;
		
	// Constructors
	
	public MilkCoin() {
		this((float) 0);
	}
	public MilkCoin(Float coin) {
		super();
		this.setCoin(coin);
	}
	public MilkCoin(Element milkElement) {
		super();
		this.setValueFromNode(milkElement);
	}

	// Set value from Element methods
	
	@Override
	public void setValueFromNode(Element milkElement) {
		super.setValueFromNode(milkElement);
		this.setCoin(milkElement);
	}
	public void setCoin(Element milkElement) {
		Float temp=null;
		temp=ParseMilkFile.getXmlFloatAttribute(milkElement,xmlCoin);
		if (temp != null) this.coin=temp;
	}
	/*
	@Override
	public void setNullValueFromNode(Element milkElement) {
		super.setNullValueFromNode(milkElement);
		this.setNullCoin(milkElement);
	}
	public void setNullCoin(Element milkElement) {
		coin = ParseMilkFile.getXmlFloatAttribute(milkElement,xmlCoin);
	}*/
	
	// field methods
	
	public Float getCoin() {
		return this.coin;
	}
	public String getStringCoin() {
		String temp = null;
		if (this.coin != null) temp = " "+xmlCoin+" : "+this.coin+". ";
		return temp;
	}
	public String getXmlCoin() {
		String temp = null;
		if (this.coin != null) temp = " "+xmlCoin+"=\""+coin+"\"";
		return temp;
	}
	public void setCoin(Float coin) {
		this.coin = coin;
	}
	
	// toString & toXml methods
	
	@Override
	public String toStringAttrib() {
		String temp = super.toStringAttrib();
		temp+=this.getStringCoin();
		return temp;
	}
	@Override
	public String toXmlAttrib() {
		String temp = super.toXmlAttrib();
		temp+=this.getXmlCoin();
		return temp;
	}
	
	// other object methods

	@Override
	public boolean allZero()  {
		boolean temp = super.allZero();
		if(this.coin!=null && this.coin!=0) temp= false;
		return temp;
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		MilkCoin clone = (MilkCoin) super.clone();
		return clone;
	}
}
