package modele;

import java.util.Vector;

import org.w3c.dom.Element;

import controleur.ParseMilkFile;

public class MilkVar implements Cloneable {
	
	public static final String noeud="";
	public String getNoeud() {return noeud;}
	
	/**
	 * meant to be @Override. Set a list of milk object from an Xml element
	 * note that you can't override a static method in java so this is just a mask that you can copy past.
	 */
	public static Vector<? extends MilkVar> getMilkVarList(Vector<Element> elementlist) {
		Vector<MilkVar> milkVars = new Vector<MilkVar>();
		for (Element elementMilk: elementlist) {
			try {
				MilkVar milkVar = new MilkVar(elementMilk);
				milkVars.add(milkVar);
			} catch (Exception e) {e.printStackTrace();}
		}
		return milkVars;
	}
	
	// Constructors
		
	public MilkVar() {
	}
	/**
	 * create an instance by using the method setValueFromNode(Element xml). 
	 * if you want null value don't use this.
	 * @param milkElement
	 */
	public MilkVar(Element milkElement) {
		this.setValueFromNode(milkElement);
	}

	// Set value from Element methods

	/**
	 * get an element with a list of this object. ("node"+"s")
	 * @param element
	 * @return
	 */
	public Element getMilkElementList(Element element) {
		return ParseMilkFile.getMilkElement(element,this.getNoeud()+"s",0);
	}
	/**
	 * get an element of this object at index 0. ("node")
	 * @param element
	 * @return
	 */
	public Element getMilkElement(Element element) {
		return getMilkElement(element,0);
	}
	/**
	 * get an element of this object at index i. ("node")
	 * @param element
	 * @return
	 */
	public Element getMilkElement(Element element, int i) {
		return ParseMilkFile.getMilkElement(element,this.getNoeud(),i);
	}
	
	/**
	 * meant to be @Override. Set the various object from an Xml element, 
	 * generally call getThisElementFromParent, so it return the element obtained, null (if that method returned null), or the element received if the method was not call.
	 */
	public void setValueFromNode(Element milkElement) {}
	/**
	 * meant to be @Override. Set the various text object from an Xml element, if an element don't exist the object is set to "".
	 */
	public void setTextValueFromNode(Element milkElement){}
	
	/**
	 * Check if this element exist as a child and return it, else return milkElement.
	 */
	public Element getThisElementFromParent(Element milkElement) {
		Element child = (getMilkElement(milkElement,0)!=null)? getMilkElement(milkElement,0):milkElement;
		return child;
	}
	/**
	 * Check if this element exist as a child and return it, else return null.
	 */
	public Element getOptionalChild(Element milkElement) {
		Element child = null;
		try {
			child = XmlHelper.getOptionalChild(milkElement, this.getNoeud());
		} catch (Exception e) {e.printStackTrace();}
		return child;
	}
		
	// toString & toXml methods
	
	/**
	 * meant to be @Override.
	 * @return a String containing this object attibutes.
	 */
	public String toStringAttrib() {
		return "";
	}
	/**
	 * meant to be @Override.
	 * @return a String containing this Stat object childs.
	 */
	public String toStringStatChild() {
		return "";
	}
	/**
	 * meant to be @Override.
	 * @return a String containing this text object childs.
	 */
	public String toStringTextChild() {
		return "";
	}
	
	/**
	 * @return a String containing this object node, attibutes & childs (Stat).
	 */
	public final String toStringStat() {
		String temp = "";
		if (!allZero()){
			temp+=this.getNoeud()+MilkRs.LIGNE_BREAK;
			if (this.toStringAttrib().length()>0)temp+=MilkRs.LIGNE_TAB+this.toStringAttrib()+MilkRs.LIGNE_BREAK;
			if (this.toStringStatChild().length()>0)temp+=MilkRs.LIGNE_TAB+this.toStringStatChild()+MilkRs.LIGNE_BREAK;
		}
		return temp;
	}
	
	@Override
	public String toString() {
		return toStringStat();
	}
	
	/**
	 * meant to be @Override.
	 * @return an xml String containing this object attibutes.
	 */
	public String toXmlAttrib() {
		return "";
	}
	/**
	 * meant to be @Override.
	 * @return an xml String containing this Stat object childs.
	 */
	public String toXmlStatChild() {
		return "";
	}
	/**
	 * meant to be @Override.
	 * @return an xml String containing this text object childs.
	 */
	public String toXmlTextChild() {
		return "";
	}
	/**
	 * @return an xml String containing this object node, attibutes & childs (Stat).
	 */
	public final String toXmlStat() {
		String temp = "";
		if (!allZero()){
			temp += "<"+this.getNoeud();
			if (this.toXmlAttrib().length()>0) temp += this.toXmlAttrib();
			if (this.toXmlStatChild().length()>0){
				temp += ">"+MilkRs.LIGNE_BREAK;
				temp += this.toXmlStatChild()+MilkRs.LIGNE_BREAK;
				temp += "</"+this.getNoeud()+">"+MilkRs.LIGNE_BREAK;
			}
			else temp+="/>"+MilkRs.LIGNE_BREAK;
		}
		return temp;
	}
	
	// other object methods

	/**
	 * meant to be @Override.
	 * @return true if ALL the attributes are null or =0.
	 */
	public boolean allZero() {
		return true;
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		MilkVar clone = (MilkVar) super.clone();
		return clone;
	}
	@Override
	public boolean equals(Object obj) {
		boolean equal = super.equals(obj);
		if (obj==null) equal = false;
		else if (obj.getClass() != this.getClass()) equal = false;
		return equal;
	}
}
