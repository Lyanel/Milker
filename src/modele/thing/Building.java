package modele.thing;

import modele.MilkFile;
import modele.MilkInterface;
import modele.MilkKind;
import modele.carac.Agent;
import modele.carac.Attrib;
import modele.carac.Bonus;
import modele.carac.Population;

import java.util.Vector;

import org.w3c.dom.Element;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class Building extends Thing implements Cloneable {

	private static Vector<Building> buildings;
	public static final String file		= "Building";
	public static final String noeud	= "building";
	public String getNoeud() {return noeud;}

	private static Vector<Building> setMilkVarFromFiles() {
		if (buildings==null) buildings = new Vector<Building>();
		else buildings.removeAllElements();
		Vector<Element> elementlist = new Vector<Element>();
		elementlist = MilkFile.getMilkElementsFromFiles(MilkFile.getXmlFilePath(file)+file, noeud);
		buildings = getMilkVarList(elementlist);
		Vector<Element> elementlInfos = new Vector<Element>();
		elementlInfos = MilkFile.getMilkElementsFromFiles(MilkInterface.getXmlLangPath()+file, noeud);
		setInfo(buildings, elementlInfos);
		return buildings;
	}

	private static void setInfo(Vector<Building> buildings, Vector<Element> elementlInfos) {
		for (Element elementlInfo: elementlInfos) {
			try {
				Building buildingInfo = new Building(elementlInfo);
				buildingInfo.setInfo(elementlInfo);
				for (Building building:buildings){
					if (buildingInfo.equals(building)){
						building.setInfo(buildingInfo.getInfo());
						break;
					}
				}
			} catch (Exception e) {e.printStackTrace();}
		}
	}

	public static ObservableList<Building> getListes() {
		if (buildings==null)setMilkVarFromFiles();
		ObservableList<Building> clone = FXCollections.observableArrayList();
		if (buildings!=null){
			for (Building building:buildings){
				try {
					clone.add((Building) building.clone());
				} catch (CloneNotSupportedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return clone;
	}

	public static ObservableList<Building> getNeutralListes() {
		if (buildings==null)setMilkVarFromFiles();
		ObservableList<Building> clone = FXCollections.observableArrayList();
		if (buildings!=null){
			for (Building building:buildings){
				try {
					if(building.getAttrib().getPath()==Attrib.Path_Neutral)clone.add((Building) building.clone());
				} catch (CloneNotSupportedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return clone;
	}

	public static ObservableList<Building> getScienceListes() {
		if (buildings==null)setMilkVarFromFiles();
		ObservableList<Building> clone = FXCollections.observableArrayList();
		if (buildings!=null){
			for (Building building:buildings){
				try {
					if(building.getAttrib().getPath()==Attrib.Path_Science)clone.add((Building) building.clone());
				} catch (CloneNotSupportedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return clone;
	}

	public static ObservableList<Building> getMagicListes() {
		if (buildings==null)setMilkVarFromFiles();
		ObservableList<Building> clone = FXCollections.observableArrayList();
		if (buildings!=null){
			for (Building building:buildings){
				try {
					if(building.getAttrib().getPath()==Attrib.Path_Magic)clone.add((Building) building.clone());
				} catch (CloneNotSupportedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return clone;
	}

	public static Vector<Building> getMilkVarList(Vector<Element> elementlist) {
		Vector<Building> buildings = new Vector<Building>();
		for (Element elementMilk: elementlist) {
			try {
				Building building = new Building(elementMilk);
				buildings.add(building);
			} catch (Exception e) {e.printStackTrace();}
		}
		return buildings;
	}
	
	public static Vector<Building> getNullMilkVarList(Vector<Element> elementlist) {
		Vector<Building> buildings = new Vector<Building>();
		for (Element elementMilk: elementlist) {
			try {
				Building building = new Building();
				building.setNullValueFromNode(elementMilk);
				buildings.add(building);
			} catch (Exception e) {e.printStackTrace();}
		}
		return buildings;
	}

	private Agent agent;
	private Bonus bonus;
	private Population population;

	// Constructors
	
	public Building() {
		super();
		this.agent = new Agent();
		this.bonus = new Bonus();
		this.population = new Population();
		this.setKind(MilkKind.kind_Building);
		this.setSellPrice((float)70);
	}
	public Building(Element milkElement) {
		super();
		this.agent = new Agent();
		this.bonus = new Bonus();
		this.population = new Population();
		this.setKind(MilkKind.kind_Building);
		this.setSellPrice((float)70);
		this.setValueFromNode(milkElement);
	}

	// Set value from Element methods
	
	@Override
	public void setValueFromNode(Element milkElement) {
		super.setValueFromNode(milkElement);
		this.setAgent(milkElement);
		this.setBonus(milkElement);
		this.setPopulation(milkElement);
	}
	@Override
	public void setNullValueFromNode(Element milkElement) {
		super.setNullValueFromNode(milkElement);
		this.setNullAgent(milkElement);
		this.setNullBonus(milkElement);
		this.setNullPopulation(milkElement);
	}
	
	// field methods
	
	public Agent getAgent() {
		return this.agent;
	}
	public void setAgent(Agent agent) {
		this.agent = agent;
	}
	public void setAgent(Element milkElement) {
		this.agent.setValueFromNode(milkElement);;
	}
	public void setNullAgent(Element milkElement) {
		this.agent.setValueFromNode(milkElement);
	}
	
	public Bonus getBonus() {
		return this.bonus;
	}
	public void setBonus(Bonus bonus) {
		this.bonus = bonus;
	}
	public void setBonus(Element milkElement) {
		this.bonus.setValueFromNode(milkElement);;
	}
	public void setNullBonus(Element milkElement) {
		this.bonus.setNullValueFromNode(milkElement);
	}
	
	public Population getPopulation() {
		return this.population;
	}
	public void setPopulation(Population population) {
		this.population = population;
	}
	public void setPopulation(Element milkElement) {
		this.population.setValueFromNode(milkElement);;
	}
	public void setNullPopulation(Element milkElement) {
		this.population.setNullValueFromNode(milkElement);
	}
	
	// toString & toXml methods
	
	@Override
	public String toStringStatChild() {
		String temp = super.toStringStatChild();
		if (this.agent != null) temp += this.agent.toStringStat();
		if (this.bonus != null) temp += this.bonus.toStringStat();
		if (this.population != null) temp += this.population.toStringStat();
		return temp;
	}
	@Override
	public String toXmlStatChild() {
		String temp = super.toXmlStatChild();
		if (this.agent != null) temp += this.agent.toXmlStat();
		if (this.bonus != null) temp += this.bonus.toXmlStat();
		if (this.population != null) temp += this.population.toXmlStat();
		return temp;
	}
	
	// other object methods

	@Override
	public boolean allZero()  {
		boolean temp = super.allZero();
		if(this.agent!=null && !this.agent.allZero()) temp= false;
		if(this.bonus!=null && !this.bonus.allZero()) temp= false;
		if(this.population!=null && !this.population.allZero()) temp= false;
		return temp;
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		Building clone = (Building) super.clone();
		if (this.agent!=null) clone.setAgent((Agent) this.agent.clone());
		if (this.bonus!=null) clone.setBonus((Bonus) this.bonus.clone());
		if (this.population!=null) clone.setPopulation((Population) this.population.clone());
		return clone;
	}
}