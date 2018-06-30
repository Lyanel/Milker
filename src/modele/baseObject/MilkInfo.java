package modele.baseObject;

import modele.MilkRs;
import modele.ParseMilkFile;

import org.w3c.dom.Element;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class MilkInfo extends MilkVar implements Cloneable {
	
	public static final String xmlName = "name", xmlTxEffect= "effect", xmlDesc= "desc", xmlQuote = "quote";
	
	// Fields
	
	private StringProperty name, txEffect, desc, quote;

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
	public void setName(Element milkElement) {
		this.setName(ParseMilkFile.getXmlStringValue(milkElement,xmlName));
	}
	public void setTxEffect(Element milkElement) {
		this.setTxEffect(ParseMilkFile.getXmlChildStringValue(milkElement,xmlTxEffect));
	}
	public void setDesc(Element milkElement) {
		this.setDesc(ParseMilkFile.getXmlStringValue(milkElement,xmlDesc));
	}
	public void setQuote(Element milkElement) {
		this.setQuote(ParseMilkFile.getXmlStringValue(milkElement,xmlQuote));
	}
	
	// field methods
	
	public void setInfo(MilkInfo info) {
		this.setName(info.getName());
		this.setTxEffect(info.getTxEffect());
		this.setDesc(info.getDesc());
		this.setQuote(info.getQuote());
	}
	
	public String getName() {
		return this.name.getValue();
	}
	public StringProperty getObrservableName() {
		return this.name;
	}
	public String getStringName() {
		String temp = null;
		if (this.name != null) temp = xmlName+" : "+getName()+". ";
		return temp;
	}
	public String getXmlName() {
		String temp = null;
		if (this.name != null) temp = "<"+xmlName+">"+getName()+"</"+xmlName+">"+MilkRs.LIGNE_BREAK;
		return temp;
	}
	public void setName(String name) {
		if (this.name == null) this.name = new SimpleStringProperty(name);
		else this.name.setValue(name);
	}

	public String getTxEffect() {
		return this.txEffect.getValue();
	}
	public StringProperty getObrservableTxEffect() {
		return this.txEffect;
	}
	public String getStringTxEffect() {
		String temp = null;
		if (this.txEffect != null) temp = xmlTxEffect+" : "+getTxEffect()+". ";
		return temp;
	}
	public String getXmlTxEffect() {
		String temp = null;
		if (this.txEffect != null) temp = "<"+xmlTxEffect+">"+getTxEffect()+"</"+xmlTxEffect+">"+MilkRs.LIGNE_BREAK;
		return temp;
	}
	public void setTxEffect(String txEffect) {
		if (this.txEffect == null) this.txEffect = new SimpleStringProperty(txEffect);
		else this.txEffect.setValue(txEffect);
	}

	public String getDesc() {
		return this.desc.getValue();
	}
	public StringProperty getObrservableDesc() {
		return this.desc;
	}
	public String getStringDesc() {
		String temp = null;
		if (this.desc != null) temp = xmlDesc+" : "+getDesc()+". ";
		return temp;
	}
	public String getXmlDesc() {
		String temp = null;
		if (this.desc != null) temp = "<"+xmlDesc+">"+getDesc()+"</"+xmlDesc+">"+MilkRs.LIGNE_BREAK;
		return temp;
	}
	public void setDesc(String desc) {
		if (this.desc == null) this.desc = new SimpleStringProperty(desc);
		else this.desc.setValue(desc);
	}

	public String getQuote() {
		return this.quote.getValue();
	}
	public StringProperty getObrservableQuote() {
		return this.quote;
	}
	public String getStringQuote() {
		String temp = null;
		if (this.quote != null) temp = xmlQuote+" : "+getQuote()+". ";
		return temp;
	}
	public String getXmlQuote() {
		String temp = null;
		if (this.quote != null) temp = "<"+xmlQuote+">"+getQuote()+"</"+xmlQuote+">"+MilkRs.LIGNE_BREAK;
		return temp;
	}
	public void setQuote(String quote) {
		if (this.quote == null) this.quote = new SimpleStringProperty(quote);
		else this.quote.setValue(quote);
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
		if (this.name!=null) clone.setName(this.name.getValue());
		if (this.txEffect!=null) clone.setTxEffect(this.txEffect.getValue());
		if (this.desc!=null) clone.setDesc(this.desc.getValue());
		if (this.quote!=null) clone.setQuote(this.quote.getValue());
		return clone;
	}
}
