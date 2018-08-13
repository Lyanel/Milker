package modele.carac;

import org.w3c.dom.Element;

import modele.baseObject.MilkDate;
import modele.baseObject.MilkString;
import modele.intel.Research;
import modele.intel.ResearchList;
import modele.intel.Synergy;
import modele.intel.SynergyList;
import modele.intel.Upgrade;
import modele.intel.UpgradeList;
import modele.thing.Animal;
import modele.thing.AnimalList;
import modele.thing.Building;
import modele.thing.BuildingList;
import modele.thing.Slave;
import modele.thing.SlaveList;
import modele.thing.Worker;
import modele.thing.WorkerList;

public class Save extends Need {

	public static final String noeud = "save", xmlMC="mc", xmlMA="ma" ;
	public String getNoeud() {return noeud;}
	
	// Fields
	
	private Double mc,ma;
	private MilkString name;
	private MilkDate plaYear;

	// Constructors
	
	public Save() {
		this(0.0,0.0);
	}
	public Save(Double mc,Double ma) {
		super();
		this.setMilkCoin(mc);
		this.setMilkAgnel(ma);
	}
	public Save(Element milkElement) {
		super();
		this.setValueFromNode(milkElement);
	}
	public Save(Save original) {
		super(original);
		this.mc = new Double(original.getMilkCoin());
		this.ma = new Double(original.getMilkAgnel());
		this.name = new MilkString(original.getName());
		this.plaYear = new MilkDate(original.getPlaYear());
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

	// field methods
	
	public Double getMilkCoin() {
		return mc;
	}
	public void setMilkCoin(Double mc) {
		this.mc = mc;
	}
	public String getXmlMilkCoin() {
		String temp = null;
		if (this.mc != null) temp = " "+xmlMC+"=\""+mc+"\"";
		return temp;
	}
	
	public Double getMilkAgnel() {
		return ma;
	}
	public void setMilkAgnel(Double ma) {
		this.ma = ma;
	}
	public String getXmlMilkAgnel() {
		String temp = null;
		if (this.ma != null) temp = " "+xmlMA+"=\""+ma+"\"";
		return temp;
	}
	
	
	public MilkDate getPlaYear() {
		return plaYear;
	}
	public void setPlaYear(MilkDate plaYear) {
		this.plaYear = plaYear;
	}
	
	
	
	public MilkString getName() {
		return name;
	}
	public void setName(MilkString name) {
		this.name = name;
	}
	

	// toString & toXml methods

	@Override
	public String toXmlAttrib() {
		String temp = super.toXmlAttrib();
		temp+=this.getXmlMilkCoin();
		temp+=this.getXmlMilkAgnel();
		return temp;
	}
	@Override
	public String toXmlStatChild() {
		String temp = super.toXmlStatChild();
		if (this.name != null) temp += this.name.toXmlStat();
	//	if (this.plaYear != null) temp += this.plaYear.toXmlStatChild();
		return temp;
	}
	
	
	
	// other object methods
	public void setSave() {
		for (Research research : ResearchList.getInstance().getResearchListe()){
			if(research.bought())this.addNeededIntel(research.toNeededIntel());
		}
		for (Upgrade upgrade : UpgradeList.getInstance().getUpgradeListe()){
			if(upgrade.bought())this.addNeededIntel(upgrade.toNeededIntel());
		}
		for (Synergy synergy : SynergyList.getInstance().getSynergyListe()){
			if(synergy.bought())this.addNeededIntel(synergy.toNeededIntel());
		}/*
		for (Ascension ascension : Ascension.getAscensionListe()){
			if(ascension.bought())save.addNeededIntel(ascension.toNeededIntel());
		}*/
		for (Building building : BuildingList.getInstance().getFullListe()){
			if(building.getQuantity().getQuant().intValue()>0)this.addNeededThing(building.toNeededThing());
		}
		for (Worker worker : WorkerList.getInstance().getFullListe()){
			if(worker.getQuantity().getQuant().intValue()>0)this.addNeededThing(worker.toNeededThing());
		}
		for (Slave slave : SlaveList.getInstance().getFullListe()){
			if(slave.getQuantity().getQuant().intValue()>0)this.addNeededThing(slave.toNeededThing());
		}
		for (Animal animal : AnimalList.getInstance().getFullListe()){
			if(animal.getQuantity().getQuant().intValue()>0)this.addNeededThing(animal.toNeededThing());
		}
	}
	
	@Override
	public boolean allZero()  {
		boolean temp = super.allZero();
		return temp;
	}
	
}
