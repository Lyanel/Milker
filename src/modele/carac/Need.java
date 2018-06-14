package modele.carac;

import java.util.Vector;

import org.w3c.dom.Element;

import modele.MilkRs;

public class Need extends Prerequisites implements Cloneable {
	
	public static final String noeud = "need";
	public String getNoeud() {return noeud;}

	// Fields

	private Vector<NeededIntel> neededIntels = null;
	
	// Constructors
	
	public Need() {
		this.neededIntels = new Vector<NeededIntel>();
	}
	public Need(Element milkElement) {
		super();
		this.neededIntels = new Vector<NeededIntel>();
		this.setValueFromNode(milkElement);
	}

	// Set value from Element methods
	
	@Override
	public void setValueFromNode(Element milkElement) {
		Element thisElement = this.getOptionalChild(milkElement);
		if(thisElement!=null){
			super.setValueFromNode(thisElement);
			this.setNeededIntels(thisElement);
		}
	}
	@SuppressWarnings("unchecked")
	public void setNeededIntels(Element milkElement) {
		neededIntels.addAll(NeededIntel.getMilkVarList(milkElement));
	}
	public void addIntel(Element milkElement) {
		NeededIntel newIntel = new NeededIntel(milkElement);
		newIntel.setValueFromNode(milkElement);
		neededIntels.add(newIntel);
	}
	
	// field methods
	
	public Vector<NeededIntel> getNeededIntels() {
		return neededIntels;
	}
	public void setNeededIntels(Vector<NeededIntel> neededIntels) {
		this.neededIntels = neededIntels;
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
		return temp;
	}
	
	// other object methods

	public Vector<NeededIntel> getCloneNeededIntels() throws CloneNotSupportedException {
		Vector<NeededIntel> clone = new Vector<NeededIntel>();
		if (this.neededIntels!=null) for (NeededIntel neededIntel:this.neededIntels) clone.add((NeededIntel) neededIntel.clone());
		return clone;
	}

	@Override
	public boolean allZero()  {
		boolean temp = super.allZero();
		if(this.neededIntels!=null && this.neededIntels.size()!=0) temp= false;
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
