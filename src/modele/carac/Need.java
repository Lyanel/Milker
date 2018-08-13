package modele.carac;

import java.util.ArrayList;

import org.w3c.dom.Element;

import modele.MilkRs;

public class Need extends Prerequisites {
	
	public static final String noeud = "need";
	public String getNoeud() {return noeud;}

	// Fields

	private ArrayList<NeededIntel> neededIntels = null;
	
	// Constructors
	
	public Need() {
		super();
		this.neededIntels = new ArrayList<NeededIntel>();
	}
	public Need(Element milkElement) {
		super();
		this.neededIntels = new ArrayList<NeededIntel>();
		this.setValueFromNode(milkElement);
	}
	public Need(Need original) {
		super(original);
		this.setDeepNeededIntels(original.getNeededIntels());
	}

	// Set value from Element methods
	
	@Override
	public void setValueFromNode(Element milkElement) {
		Element thisElement = this.getThisOptionalChildFromParent(milkElement);
		if(thisElement!=null){
			super.setValueFromNode(thisElement);
			this.setNeededIntels(thisElement);
		}
	}
	
	public void setNeededIntels(Element milkElement) {
		neededIntels.addAll(NeededIntel.getMilkVarList(milkElement));
	}
	public void addIntel(Element milkElement) {
		NeededIntel newIntel = new NeededIntel(milkElement);
		newIntel.setValueFromNode(milkElement);
		neededIntels.add(newIntel);
	}
	
	// field methods
	
	public ArrayList<NeededIntel> getNeededIntels() {
		return neededIntels;
	}
	public void setNeededIntels(ArrayList<NeededIntel> neededIntels) {
		this.neededIntels = neededIntels;
	}
	public void setDeepNeededIntels(ArrayList<NeededIntel> original) {
		this.neededIntels = new ArrayList<NeededIntel>();
		for (NeededIntel neededIntel:original) this.addNeededIntel( new NeededIntel (neededIntel));
	}
	public void addNeededIntel(NeededIntel intel) {
		this.neededIntels.add(intel);
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

	@Override
	public boolean allZero()  {
		boolean temp = super.allZero();
		if(this.neededIntels!=null && this.neededIntels.size()!=0) temp= false;
		return temp;
	}
}
