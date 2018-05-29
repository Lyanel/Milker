package modele.carac;

import java.util.Vector;

import org.w3c.dom.Element;

import controleur.ParseMilkFile;
import modele.thing.Thing;

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

	/**
	 * Level :	have 2 way of working : </br>
	 *		If wanted lvl<0 the Level of the tested thing must be = to the wanted lvl.</br>
	 *		if wanted lvl>0 the Level of the tested thing must be > to the wanted lvl. (if wanted lvl=0 no need to check it since that will alwais return true)</br>
	 * 		ex if wanted = 7, tested thing must also have a lvl = to 7 return true.</br>
	 * 			if =-7 then all tested thing with a lvl>=7 will return true.</br>
	 */
	public static boolean checkLevel(NeededThing wanted, Thing tested) {
		boolean result = true;
		int wlvl = wanted.getLvl();
		int tlvl = tested.getLvl();
		if (wlvl!=0){
			if(wlvl<0 && (wlvl*-1) > tlvl )result=false;
			if(wlvl>0 && wlvl != tlvl)result=false;
		}
		return result;
	}
	
	// Fields
	
	private Integer lvl;
	private ThingAttrib attrib;

	// Constructors
	
	public NeededThing() {
		super();
		this.setLvl(0);
		this.attrib = new ThingAttrib();
		this.getKind().setMod(1);
	}
	public NeededThing(Element milkElement) {
		super();
		this.attrib = new ThingAttrib();
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
	public void setLvl(Element milkElement) {
		Integer temp=null;
		temp=ParseMilkFile.getXmlIntAttribute(milkElement,xmlLvl);
		if (temp != null) this.lvl=temp;
	}
	public void setAttrib(Element milkElement) {
		this.attrib.setValueFromNode(milkElement);;
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
	
	public ThingAttrib getAttrib() {
		return this.attrib;
	}
	public void setAttrib(ThingAttrib attrib) {
		this.attrib = attrib;
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
		if (this.attrib!=null) clone.setAttrib((ThingAttrib) this.attrib.clone());
		return clone;
	}
}
