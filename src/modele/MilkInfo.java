package modele;

import controleur.ParseMilkFile;

import org.w3c.dom.Element;

public class MilkInfo extends MilkVar implements Cloneable {
	
	public static final String xmlName = "name", xmlTxEffect= "effect", xmlDesc= "desc", xmlQuote = "quote";
	
	// Fields
	
	private String name, txEffect, desc, quote;

	// Constructors
	
	public MilkInfo() {
		this("","","","");
	}
	public MilkInfo(String name, String effect,String desc,String quote) {
		super();
		this.setName(name);
		this.setTxEffect(effect);
		this.setDesc(desc);
		this.setQuote(quote);
	}
	public MilkInfo(Element milkElement) {
		super();
		this.setValueFromNode(milkElement);
	}

	// Set value from Element methods
	
	@Override
	public void setTextValueFromNode(Element milkElement) {
		super.setTextValueFromNode(milkElement);
		this.setName(milkElement);
		this.setTxEffect(milkElement);
		this.setDesc(milkElement);
		this.setQuote(milkElement);
	}
	
	// field methods
	
	public String getName() {
		return name;
	}
	public String getStringName() {
		String temp = null;
		if (this.name != null) temp = xmlName+" : "+this.name+". ";
		return temp;
	}
	public String getXmlName() {
		String temp = null;
		if (this.name != null) temp = "<"+xmlName+">"+this.name+"</"+xmlName+">"+MilkRs.LIGNE_BREAK;
		return temp;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setName(Element milkElement) {
		this.name = ParseMilkFile.getXmlStringValue(milkElement,xmlName);
	}

	public String getTxEffect() {
		return this.txEffect;
	}
	public String getStringTxEffect() {
		String temp = null;
		if (this.txEffect != null) temp = xmlTxEffect+" : "+this.txEffect+". ";
		return temp;
	}
	public String getXmlTxEffect() {
		String temp = null;
		if (this.txEffect != null) temp = "<"+xmlTxEffect+">"+this.txEffect+"</"+xmlTxEffect+">"+MilkRs.LIGNE_BREAK;
		return temp;
	}
	public void setTxEffect(String txEffect) {
		this.txEffect = txEffect;
	}
	public void setTxEffect(Element milkElement) {
		this.txEffect = ParseMilkFile.getXmlStringValue(milkElement,xmlTxEffect);
	}

	public String getDesc() {
		return desc;
	}
	public String getStringDesc() {
		String temp = null;
		if (this.desc != null) temp = xmlDesc+" : "+this.desc+". ";
		return temp;
	}
	public String getXmlDesc() {
		String temp = null;
		if (this.desc != null) temp = "<"+xmlDesc+">"+this.desc+"</"+xmlDesc+">"+MilkRs.LIGNE_BREAK;
		return temp;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public void setDesc(Element milkElement) {
		this.desc = ParseMilkFile.getXmlStringValue(milkElement,xmlDesc);
	}

	public String getQuote() {
		return quote;
	}
	public String getStringQuote() {
		String temp = null;
		if (this.quote != null) temp = xmlQuote+" : "+this.quote+". ";
		return temp;
	}
	public String getXmlQuote() {
		String temp = null;
		if (this.quote != null) temp = "<"+xmlQuote+">"+this.quote+"</"+xmlQuote+">"+MilkRs.LIGNE_BREAK;
		return temp;
	}
	public void setQuote(String quote) {
		this.quote = quote;
	}
	public void setQuote(Element milkElement) {
		this.quote = ParseMilkFile.getXmlStringValue(milkElement,xmlQuote);
	}
	
	// toString & toXml methods
	
	@Override
	public String toStringTextChild() {
		String temp = super.toStringTextChild();
		temp+=this.getStringName();
		temp+=this.getStringTxEffect();
		temp+=this.getStringDesc();
		temp+=this.getStringQuote();
		return temp;
	}
	@Override
	public String toXmlTextChild() {
		String temp = super.toXmlTextChild();
		temp+=this.getXmlName();
		temp+=this.getXmlTxEffect();
		temp+=this.getXmlDesc();
		temp+=this.getXmlQuote();
		return temp;
	}
	
	// other object methods
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		MilkInfo clone = (MilkInfo) super.clone();
		return clone;
	}
}
