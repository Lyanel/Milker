package modele.carac;

import java.util.Vector;

import org.w3c.dom.Element;

import modele.MilkRs;
import modele.baseObject.MilkVar;

public class Prerequisites extends MilkVar implements Cloneable {

	// Fields
	private Vector<NeededThing> neededThings = null;
	
	// Constructors
	
	public Prerequisites() {
		this.neededThings = new Vector<NeededThing>();
	}
	public Prerequisites(Element milkElement) {
		super();
		this.neededThings = new Vector<NeededThing>();
		this.setValueFromNode(milkElement);
	}

	// Set value from Element methods
	
	@Override
	public void setValueFromNode(Element milkElement) {
		super.setValueFromNode(milkElement);
		this.setNeededThings(milkElement);
		
	}
	public void setNeededThings(Element milkElement) {
		this.neededThings.addAll(NeededThing.getMilkVarList(milkElement));
		if(neededThings.size()==0){
			NeededThing test = new NeededThing();
			test.setValueFromNode(milkElement);
			if (test.getKind().getKind().intValue()!=0)neededThings.addElement(test);
		}
	}
	
	// field methods	
	
	public Vector<NeededThing> getNeededThings() {
		return neededThings;
	}
	public void setNeededThings(Vector<NeededThing> neededThings) {
		this.neededThings = neededThings;
	}
	
	// toString & toXml methods
	
	@Override
	public String toStringAttrib() {
		String temp = super.toStringAttrib();
		return temp;
	}
	@Override
	public String toXmlAttrib() {
		String temp = super.toXmlAttrib();
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
		Prerequisites clone = (Prerequisites) super.clone();
		clone.setNeededThings(getCloneNeededThings());
		return clone;
	}
}
