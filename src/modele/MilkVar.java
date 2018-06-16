package modele;

import java.util.ArrayList;

import org.w3c.dom.Element;

public class MilkVar implements Cloneable {
	
	public static final String noeud="";
	public String getNoeud() {return noeud;}

	
	/**
	 * meant to be @Override (if not, noeud will be = to "", and you'll get nothing). Set a list of milk object from an Xml element,
	 * note that you can't override a static method in java so this is just a mask that you can copy past.
	 */
	public static ArrayList<? extends MilkVar> getMilkVarList(Element parent) {
		return getMilkVarList(XmlHelper.getChildrenListByTagName(parent,noeud));
	}
	
	/**
	 * meant to be @Override (if not, you'll get an ArrayList<MilkVar>). Set a list of milk object from an Xml ArrayList<Element>,
	 * note that you can't override a static method in java so this is just a mask that you can copy past.
	 */
	public static ArrayList<? extends MilkVar> getMilkVarList(ArrayList<Element> elementlist) {
		ArrayList<MilkVar> milkVars = new ArrayList<MilkVar>();
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
	 * meant to be @Override. Set the various object from an Xml element, 
	 * generally call getThisElementFromParent, so it return the element obtained, null (if that method returned null), or the element received if the method was not call.
	 */
	public void setValueFromNode(Element milkElement) {}
	
	/**
	 * meant to be @Override. Set the various text object from an Xml element, if an element don't exist the object is set to "".
	 */
	public void setTextValueFromNode(Element milkElement){}

	
	// Get this element from parent Element methods (Old version)
	
	
	/**
	 * get an element with a list of this object. ("node"+"s")
	 * @param element
	 * @return
	 *
	public Element getMilkElementList(Element element) {
		return ParseMilkFile.getMilkElement(element,this.getNoeud()+"s",0);
	}
	/**
	 * get an element of this object at index 0. ("node")
	 * @param element
	 * @return
	 *
	public Element getMilkElement(Element element) {
		return getMilkElement(element,0);
	}
	/**
	 * get an element of this object at index i. ("node")
	 * @param element
	 * @return
	 *
	public Element getMilkElement(Element element, int i) {
		return ParseMilkFile.getMilkElement(element,this.getNoeud(),i);
	}
	
	/**
	 * Check if this element exist as a child and return it, else return milkElement.
	 *
	public Element getThisElementFromParent(Element milkElement) {
		Element child = (getMilkElement(milkElement,0)!=null)? getMilkElement(milkElement,0):milkElement;
		return child;
	}*/

	
	// Get this element from parent Element methods (new version)
	
	
	/**
	 * Check if this element exist as a child and return it, else you'll get an error.
	 */
	public Element getThisChildFromParent(Element milkParent) {
		Element child = null;
		try {
			child = XmlHelper.getOptionalChild(milkParent, this.getNoeud());
		} catch (Exception e) {e.printStackTrace();}
		return child;
	}
	
	/**
	 * Check if this element exist as a child containing a list with child of the same name ("node"+"s"[node]) and return it, else you'll get an error.
	 */
	public Element getThisChildFromParentAsAContainer(Element milkParent) {
		Element child = null;
		try {
			child = XmlHelper.getOptionalChild(milkParent, this.getNoeud()+"s");
		} catch (Exception e) {e.printStackTrace();}
		return child;
	}
	
	/**
	 * Check if this element contain a list of child of the same name and return it, else return null.
	 */
	public ArrayList<Element> getThisChildListFromParent(Element milkParent) {
		ArrayList<Element> childs = null;
		try {
			childs = XmlHelper.getChildrenListByTagName(milkParent, this.getNoeud());
		} catch (Exception e) {e.printStackTrace();}
		return childs;
	}
	
	/**
	 * Check if this element exist as a child and return it, else return null.
	 */
	public Element getThisOptionalChildFromParent(Element milkParent) {
		Element child = null;
		try {
			child = XmlHelper.getOptionalChild(milkParent, this.getNoeud(),milkParent);
		} catch (Exception e) {e.printStackTrace();}
		return child;
	}
	
	/**
	 * Check if this element exist as a child containing a list with child of the same name ("node"+"s"[node]) and return it, else return null.
	 */
	public Element getThisOptionalChildFromParentAsAContainer(Element milkParent) {
		Element child = null;
		try {
			child = XmlHelper.getOptionalChild(milkParent, this.getNoeud()+"s");
		} catch (Exception e) {e.printStackTrace();}
		return child;
	}
	
	/**
	 * Check if this element contain a non empty list of child of the same name and return it, else return null.
	 */
	public ArrayList<Element> getThisOptionalChildListFromParent(Element milkParent) {
		ArrayList<Element> childs = null;
		try {
			childs = XmlHelper.getChildrenListByTagName(milkParent, this.getNoeud());
		} catch (Exception e) {e.printStackTrace();}
		return (childs.size()>0)?childs:null;
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
