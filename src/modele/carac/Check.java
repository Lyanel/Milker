package modele.carac;

import java.util.Vector;

import org.w3c.dom.Element;

import controleur.ParseMilkFile;
import modele.MilkRs;
import modele.MilkVar;

public class Check extends MilkVar implements Cloneable {

	public static final String noeud = "check", xmlMod = "mod";
	public String getNoeud() {return noeud;}
	
	private Integer mod;
	private Vector<NeededThing> neededThings = null;
	
	// Constructors
	
	public Check() {
		this.mod = 1;
		this.neededThings = new Vector<NeededThing>();
	}
	public Check(Element milkElement) {
		super();
		this.mod = 1;
		this.neededThings = new Vector<NeededThing>();
		this.setValueFromNode(milkElement);
	}

	// Set value from Element methods
	
	@Override
	public void setValueFromNode(Element milkElement) {
		super.setValueFromNode(milkElement);
		this.setMod(milkElement);
		this.setNeededThings(milkElement);
		
	}
	@Override
	public void setNullValueFromNode(Element milkElement) {
		super.setNullValueFromNode(milkElement);
		this.setNullMod(milkElement);
		this.setNullNeededThings(milkElement);
	}
	
	// field methods

	public Integer getMod() {
		return this.mod;
	}
	public String getStringMod() {
		String temp = null;
		if (this.mod != null) temp = xmlMod+" : "+this.mod+". ";
		return temp;
	}
	public String getXmlMod() {
		String temp = null;
		if (this.mod != null) temp = " "+xmlMod+"=\""+mod+"\"";
		return temp;
	}
	public void setMod(Integer mod) {
		this.mod = mod;
	}
	public void setMod(Element milkElement) {
		Integer temp=null;
		temp=ParseMilkFile.getXmlIntAttribute(milkElement,xmlMod);
		if (temp != null) this.mod=temp;
	}
	public void setNullMod(Element milkElement) {
		mod = ParseMilkFile.getXmlIntAttribute(milkElement,xmlMod);
	}
	
	
	public Vector<NeededThing> getNeededThings() {
		return neededThings;
	}
	public void setNeededThings(Vector<NeededThing> neededThings) {
		this.neededThings = neededThings;
	}
	@SuppressWarnings("unchecked")
	public void setNeededThings(Element milkElement) {
		this.neededThings.addAll(NeededThing.getMilkVarList(milkElement));
	}
	@SuppressWarnings("unchecked")
	public void setNullNeededThings(Element milkElement) {
		this.neededThings.addAll(NeededThing.getNullMilkVarList(milkElement));
	}
	
	// toString & toXml methods
	
	@Override
	public String toStringAttrib() {
		String temp = super.toStringAttrib();
		temp+=this.getStringMod();
		return temp;
	}
	@Override
	public String toXmlAttrib() {
		String temp = super.toXmlAttrib();
		temp+=this.getXmlMod();
		return temp;
	}
	
	@Override
	public String toStringStatChild() {
		String temp = super.toStringStatChild();
		if(neededThings.size()>0){
			temp += MilkRs.LIGNE_TAB+NeededThing.noeud+"s : "+MilkRs.LIGNE_BREAK;
			for (NeededThing neededThing : neededThings) {
				temp += MilkRs.LIGNE_TAB+neededThing.toStringStat();
			}
		}
		return temp;
	}
	@Override
	public String toXmlStatChild() {
		String temp = super.toXmlStatChild();
		if(neededThings.size()>0){
			temp += "<"+NeededThing.noeud+"s>"+MilkRs.LIGNE_BREAK;
			for (NeededThing neededThing : neededThings) {
				temp += MilkRs.LIGNE_TAB+neededThing.toXmlStat();
			}
		temp += "</"+NeededThing.noeud+"s>"+MilkRs.LIGNE_BREAK;
		}
		return temp;
	}

	// other object methods
	
	public Vector<NeededThing> getCloneNeededThings() throws CloneNotSupportedException {
		Vector<NeededThing> clone = new Vector<NeededThing>();
		if (this.neededThings!=null) for (NeededThing neededThing:this.neededThings) clone.add((NeededThing) neededThing.clone());
		return clone;
	}

	@Override
	public boolean allZero()  {
		boolean temp = super.allZero();
		if(this.neededThings!=null && this.neededThings.size()!=0) temp= false;
		return temp;
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		Check clone = (Check) super.clone();
		clone.setNeededThings(getCloneNeededThings());
		return clone;
	}
}
