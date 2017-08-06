package modele.carac;

import java.util.Vector;

import org.w3c.dom.Element;

import controleur.ParseMilkFile;

public class NeededThing extends NeededIntel implements Cloneable {
	
	public static final String noeud = "thing", xmlLvl = "lvl";
	public String getNoeud() {return noeud;}

	@SuppressWarnings("rawtypes")
	public static Vector getMilkVarList(Element elementlist) {
		Vector<NeededThing> neededThings = new Vector<NeededThing>();
		NeededThing neededThing=new NeededThing();
		Element elements = neededThing.getMilkElementList(elementlist);
		int size = (elements!=null)? elements.getChildNodes().getLength():0;
		for (int i=0;i<size;i++){ 
			Element tempE=null;
			tempE=neededThing.getMilkElement(elements,i);
			if (tempE != null){
				neededThing=new NeededThing();
				neededThing.setValueFromNode(tempE);
				neededThings.add(neededThing);
			}
		}
		return neededThings;
	}
	@SuppressWarnings("rawtypes")
	public static Vector getNullMilkVarList(Element elementlist) {
		Vector<NeededThing> neededThings = new Vector<NeededThing>();
		NeededThing neededThing=new NeededThing();
		Element elements = neededThing.getMilkElementList(elementlist);
		int size = (elements!=null)? elements.getChildNodes().getLength():0;
		for (int i=0;i<size;i++){ 
			Element tempE=null;
			tempE=neededThing.getMilkElement(elements,i);
			if (tempE != null){
				neededThing=new NeededThing();
				neededThing.setNullValueFromNode(tempE);
				neededThings.add(neededThing);
			}
		}
		return neededThings;
	}
	@SuppressWarnings("rawtypes")
	public static Vector getMilkVarList(Vector<Element> elementlist) {
		Vector<NeededThing> neededThings = new Vector<NeededThing>();
		for (Element elementMilk: elementlist) {
			try {
				NeededThing neededThing = new NeededThing(elementMilk);
				neededThings.add(neededThing);
			} catch (Exception e) {e.printStackTrace();}
		}
		return neededThings;
	}
	@SuppressWarnings("rawtypes")
	public static Vector getNullMilkVarList(Vector<Element> elementlist) {
		Vector<NeededThing> neededThings = new Vector<NeededThing>();
		for (Element elementMilk: elementlist) {
			try {
				NeededThing neededThing = new NeededThing();
				neededThing.setNullValueFromNode(elementMilk);
				neededThings.add(neededThing);
			} catch (Exception e) {e.printStackTrace();}
		}
		return neededThings;
	}
	
	private Integer lvl;
	private Attrib attrib;

	// Constructors
	
	public NeededThing() {
		super();
		this.setLvl(0);
		this.attrib = new Attrib();
		this.getKind().setMod(1);
	}
	public NeededThing(Element milkElement) {
		super();
		this.attrib = new Attrib();
		this.setValueFromNode(milkElement);
		this.getKind().setMod(1);
	}

	// Set value from Element methods
	
	@Override
	public void setValueFromNode(Element milkElement) {
		super.setValueFromNode(milkElement);
		this.setLvl(milkElement);
		this.setAttrib(milkElement);
	}
	@Override
	public void setNullValueFromNode(Element milkElement) {
		super.setNullValueFromNode(milkElement);
		this.setNullLvl(milkElement);
		this.setNullAttrib(milkElement);
	}
	
	// field methods

	public Integer getLvl() {
		return this.lvl;
	}
	public String getStringLvl() {
		String temp = "";
		if (this.lvl != null) temp += xmlLvl+" : "+this.lvl+". ";
		return temp;
	}
	public String getXmlLvl() {
		String temp = "";
		if (this.lvl != null) temp += " "+xmlLvl+"=\""+lvl+"\"";
		return temp;
	}
	public void setLvl(Integer lvl) {
		this.lvl = lvl;
	}
	public void setLvl(Element milkElement) {
		Integer temp=null;
		temp=ParseMilkFile.getXmlIntAttribute(milkElement,xmlLvl);
		if (temp != null) this.lvl=temp;
	}
	public void setNullLvl(Element milkElement) {
		lvl = ParseMilkFile.getXmlIntAttribute(milkElement,xmlLvl);
	}
	
	public Attrib getAttrib() {
		return this.attrib;
	}
	public void setAttrib(Attrib attrib) {
		this.attrib = attrib;
	}
	public void setAttrib(Element milkElement) {
		this.attrib.setValueFromNode(milkElement);;
	}
	public void setNullAttrib(Element milkElement) {
		this.attrib.setValueFromNode(milkElement);
	}
	
	// toString & toXml methods
	
	@Override
	public String toStringAttrib() {
		String temp =super.toStringAttrib();
		temp+=this.getStringLvl();
		if (this.attrib != null) temp += this.attrib.toStringAttrib();
		return temp;
	}
	@Override
	public String toXmlAttrib() {
		String temp = super.toXmlAttrib();
		temp+=this.getXmlLvl();
		if (this.attrib != null) temp += this.attrib.toXmlAttrib();
		return temp;
	}
	@Override
	public String toStringStatChild() {
		String temp = super.toXmlStatChild();
		if (this.attrib != null) temp += this.attrib.toStringStatChild();
		return temp;
	}
	@Override
	public String toXmlStatChild() {
		String temp = super.toXmlStatChild();
		if (this.attrib != null) temp += this.attrib.toXmlStatChild();
		return temp;
	}
	
	// other object methods
	
	@Override
	public boolean allZero()  {
		boolean temp = super.allZero();
		if(this.lvl!=null && this.lvl!=0) temp= false;
		if(this.attrib!=null && !this.attrib.allZero()) temp= false;
		return temp;
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		NeededThing clone = (NeededThing) super.clone();
		if (this.attrib!=null) clone.setAttrib((Attrib) this.attrib.clone());
		return clone;
	}
}
