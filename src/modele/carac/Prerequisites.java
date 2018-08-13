package modele.carac;

import java.util.ArrayList;

import org.w3c.dom.Element;

import modele.MilkRs;
import modele.baseObject.MilkVar;

public class Prerequisites extends MilkVar {

	// Fields
	private ArrayList<NeededThing> neededThings = null;
	
	// Constructors
	
	public Prerequisites() {
		super();
		this.neededThings = new ArrayList<NeededThing>();
	}
	public Prerequisites(Element milkElement) {
		super();
		this.neededThings = new ArrayList<NeededThing>();
		this.setValueFromNode(milkElement);
	}
	public Prerequisites(Prerequisites original) {
		super(original);
		this.setDeepNeededThings(original.getNeededThings());
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
			if (test.getKind().getKind().intValue()!=0) this.addNeededThing(test);
		}
	}
	
	// field methods	
	
	public ArrayList<NeededThing> getNeededThings() {
		return neededThings;
	}
	public void setDeepNeededThings(ArrayList<NeededThing> original) {
		this.neededThings = new ArrayList<NeededThing>();
		for (NeededThing neededThing:original) this.addNeededThing(new NeededThing (neededThing));
	}
	public void setNeededThings(ArrayList<NeededThing> neededThings) {
		this.neededThings = neededThings;
	}
	public void addNeededThing(NeededThing thing) {
		this.neededThings.add(thing);
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
	
	@Override
	public boolean allZero()  {
		boolean temp = super.allZero();
		if(this.neededThings!=null && this.neededThings.size()!=0) temp= false;
		return temp;
	}
}
