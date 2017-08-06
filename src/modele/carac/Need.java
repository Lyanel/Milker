package modele.carac;

import java.util.Vector;

import org.w3c.dom.Element;

import modele.MilkRs;
import modele.MilkVar;

public class Need extends MilkVar implements Cloneable {
	
	public static final String noeud = "need";
	public String getNoeud() {return noeud;}

	private Vector<NeededIntel> neededIntels = null;	
	private Vector<NeededThing> neededThings = null;
	
	// Constructors
	
	public Need() {
		this.neededIntels = new Vector<NeededIntel>();
		this.neededThings = new Vector<NeededThing>();
	}
	public Need(Element milkElement) {
		super();
		this.neededIntels = new Vector<NeededIntel>();
		this.neededThings = new Vector<NeededThing>();
		this.setValueFromNode(milkElement);
	}

	// Set value from Element methods
	
	@Override
	public void setValueFromNode(Element milkElement) {
		Element thisElement = this.getThisElementFromParent(milkElement);
		super.setValueFromNode(thisElement);
		this.setNeededIntels(thisElement);
		this.setNeededThings(thisElement);
	}
	@Override
	public void setNullValueFromNode(Element milkElement) {
		Element thisElement = this.getThisElementFromParent(milkElement);
		super.setNullValueFromNode(thisElement);
		this.setNullNeededIntels(thisElement);
		this.setNullNeededThings(thisElement);
	}
	
	// field methods
	
	public void addIntel(Element milkElement) {
		NeededIntel newIntel = new NeededIntel(milkElement);
		newIntel.setValueFromNode(milkElement);
		neededIntels.add(newIntel);
	}
	public void addNullIntel(Element milkElement) {
		NeededIntel newIntel = new NeededIntel();
		newIntel.setNullValueFromNode(milkElement);
		neededIntels.add(newIntel);
	}
	public Vector<NeededIntel> getNeededIntels() {
		return neededIntels;
	}
	public void setNeededIntels(Vector<NeededIntel> neededIntels) {
		this.neededIntels = neededIntels;
	}
	@SuppressWarnings("unchecked")
	public void setNeededIntels(Element milkElement) {
		neededIntels.addAll(NeededIntel.getMilkVarList(milkElement));
	}
	@SuppressWarnings("unchecked")
	public void setNullNeededIntels(Element milkElement) {
		neededIntels.addAll(NeededIntel.getNullMilkVarList(milkElement));
	}
	
	public Vector<NeededThing> getNeededThings() {
		return neededThings;
	}
	public void setNeededThings(Vector<NeededThing> neededThings) {
		this.neededThings = neededThings;
	}
	@SuppressWarnings("unchecked")
	public void setNeededThings(Element milkElement) {
		neededThings.addAll(NeededThing.getMilkVarList(milkElement));
	}
	@SuppressWarnings("unchecked")
	public void setNullNeededThings(Element milkElement) {
		neededThings.addAll(NeededThing.getNullMilkVarList(milkElement));
	}
	
	// toString & toXml methods
	
	@Override
	public String toStringStatChild() {
		String temp = super.toStringStatChild();
		if(neededIntels.size()>0){
			temp += " "+NeededIntel.noeud+"s : "+MilkRs.LIGNE_BREAK;
			for (NeededIntel neededIntel : neededIntels) {
				temp += MilkRs.LIGNE_TAB+neededIntel.toStringStat();
			}
		}
		if(neededThings.size()>0){
			temp += " "+NeededThing.noeud+"s : "+MilkRs.LIGNE_BREAK;
			for (NeededThing neededThing : neededThings) {
				temp += MilkRs.LIGNE_TAB+neededThing.toStringStat();
			}
		}
		return temp;
	}
	@Override
	public String toXmlStatChild() {
		String temp = super.toXmlStatChild();
		if(neededIntels.size()>0){
			temp += "<"+NeededIntel.noeud+"s>"+MilkRs.LIGNE_BREAK;
			for (NeededIntel neededIntel : neededIntels) {
				temp += MilkRs.LIGNE_TAB+neededIntel.toXmlStat();
			}
		temp += "</"+NeededIntel.noeud+"s>"+MilkRs.LIGNE_BREAK;
		}
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

	public Vector<NeededIntel> getCloneNeededIntels() throws CloneNotSupportedException {
		Vector<NeededIntel> clone = new Vector<NeededIntel>();
		if (this.neededIntels!=null) for (NeededIntel neededIntel:this.neededIntels) clone.add((NeededIntel) neededIntel.clone());
		return clone;
	}
	public Vector<NeededThing> getCloneNeededThings() throws CloneNotSupportedException {
		Vector<NeededThing> clone = new Vector<NeededThing>();
		if (this.neededThings!=null) for (NeededThing neededThing:this.neededThings) clone.add((NeededThing) neededThing.clone());
		return clone;
	}

	@Override
	public boolean allZero()  {
		boolean temp = super.allZero();
		if(this.neededIntels!=null && this.neededIntels.size()!=0) temp= false;
		if(this.neededThings!=null && this.neededThings.size()!=0) temp= false;
		return temp;
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		Need clone = (Need) super.clone();
		clone.setNeededIntels(getCloneNeededIntels());
		clone.setNeededThings(getCloneNeededThings());
		return clone;
	}
}
