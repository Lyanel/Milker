package modele.baseObject;

import java.util.ArrayList;

import org.w3c.dom.Element;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import modele.ParseMilkFile;

public class MilkString extends MilkId {
	public static final String noeud = "string";
	public String getNoeud() {return noeud;}

	public static ArrayList<MilkString> getMilkVarList(ArrayList<Element> elementlist) {
		ArrayList<MilkString> milkStrings = new ArrayList<MilkString>();
		for (Element elementMilk: elementlist) {
			try {
				MilkString milkString = new MilkString(elementMilk);
				milkStrings.add(milkString);
			} catch (Exception e) {e.printStackTrace();}
		}
		return milkStrings;
	}

	// Fields
	
	private StringProperty text;

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
	public MilkString(MilkString original) {
		super(original);
		this.setText(original.getText());
	}

	// Set value from Element methods
	
	@Override
	public void setValueFromNode(Element milkElement) {
		super.setValueFromNode(milkElement);
		this.setText(milkElement);
	}
	public void setText(Element milkElement) {
		this.setText(ParseMilkFile.getXmlStringValue(milkElement, ""));
	}
	
	// field methods

	public StringProperty getText() {
		return text;
	}
	
	public String asText() {
		return text.getValue();
	}
	public String getStringText() {
		String temp = "";
		if (this.text != null) temp += noeud + " : "+this.text+". ";
		return temp;
	}
	public String getXmlText() {
		String temp = "";
		if (this.text != null) this.text.setValue(temp);
		return temp;
	}
	public void setText(String text) {
		if(this.text==null) this.text = new SimpleStringProperty();
		this.text.setValue(text);
	}
	public void setText(StringProperty text) {
		this.text = text ;
	}

	public void setValue(MilkString newValue) {
		this.setText(newValue.getText().getValue());
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
	
	
}
