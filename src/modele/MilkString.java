package modele;

import java.util.Vector;

import org.w3c.dom.Element;

import controleur.ParseMilkFile;

public class MilkString extends MilkId implements Cloneable {
	public static final String noeud = "string";
	public String getNoeud() {return noeud;}

	public static Vector<MilkString> getMilkVarList(Vector<Element> elementlist) {
		Vector<MilkString> milkStrings = new Vector<MilkString>();
		for (Element elementMilk: elementlist) {
			try {
				MilkString milkString = new MilkString(elementMilk);
				milkStrings.add(milkString);
			} catch (Exception e) {e.printStackTrace();}
		}
		return milkStrings;
	}
	
	public static Vector<MilkString> getNullMilkVarList(Vector<Element> elementlist) {
		Vector<MilkString> milkStrings = new Vector<MilkString>();
		for (Element elementMilk: elementlist) {
			try {
				MilkString milkString = new MilkString();
				milkString.setNullValueFromNode(elementMilk);
				milkStrings.add(milkString);
			} catch (Exception e) {e.printStackTrace();}
		}
		return milkStrings;
	}
	
	private String text;

	// Constructors
	
	public MilkString() {
		this("");
	}
	public MilkString(String text) {
		super();
		this.setText(text);
	}
	public MilkString(Element milkElement) {
		super(milkElement);
		this.setValueFromNode(milkElement);
	}

	// Set value from Element methods
	
	@Override
	public void setValueFromNode(Element milkElement) {
		super.setValueFromNode(milkElement);
		this.setText(milkElement);
	}
	@Override
	public void setNullValueFromNode(Element milkElement) {
		super.setNullValueFromNode(milkElement);
		this.setNullText(milkElement);
	}
	
	// field methods
	
	public String getText() {
		return text;
	}
	public String getStringText() {
		String temp = "";
		if (this.text != null) temp += noeud + " : "+this.text+". ";
		return temp;
	}
	public String getXmlText() {
		String temp = "";
		if (this.text != null) temp = this.text;
		return temp;
	}
	public void setText(String text) {
		this.text = text;
	}
	public void setText(Element milkElement) {
		this.text = ParseMilkFile.getXmlStringValue(milkElement, "");
	}
	public void setNullText(Element milkElement) {
		this.text = ParseMilkFile.getXmlStringValue(milkElement, "");
	}
	
	// toString & toXml methods
	
	@Override
	public String toStringTextChild() {
		String temp = super.toStringTextChild();
		temp+=this.getStringText();
		return temp;
	}
	@Override
	public String toXmlTextChild() {
		String temp = super.toXmlTextChild();
		temp+=this.getXmlText();
		return temp;
	}
	
	// other object methods
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		MilkString clone = (MilkString) super.clone();
		return clone;
	}
}
