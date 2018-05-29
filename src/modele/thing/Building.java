package modele.thing;

import modele.MilkFile;
import modele.MilkImage;
import modele.MilkInterface;
import modele.MilkKind;
import modele.carac.Agent;
import modele.carac.ThingAttrib;
import modele.carac.Bonus;
import modele.carac.Population;

import java.util.Vector;

import org.w3c.dom.Element;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class Building extends Thing implements Cloneable {

	public static final String file	= "Building", noeud	= "building";
	public String getNoeud() {return noeud;}

	private static Vector<Building> buildings;
	private static ObservableList<Building> modelListe;
	private static ObservableList<Building> modelNeutralListe;
	private static ObservableList<Building> modelScienceListe;
	private static ObservableList<Building> modelMagicListe;
	
	@SafeVarargs
	private static ObservableList<Building> merge(ObservableList<Building> into, ObservableList<Building>... lists) {
        final ObservableList<Building> list = into;
        for (ObservableList<Building> l : lists) {
            list.addAll(l);
            l.addListener((javafx.collections.ListChangeListener.Change<? extends Building> c) -> {
                while (c.next()) {
                    if (c.wasAdded()) {
                        list.addAll(c.getAddedSubList());
                    }
                    if (c.wasRemoved()) {
                        list.removeAll(c.getRemoved());
                    }
                    if (c.wasUpdated()) {
                        list.removeAll(c.getRemoved());
                        list.addAll(c.getAddedSubList());
                    }
                }
            });
        }
        return list;
    }
	
	private static Vector<Building> setMilkVarFromFiles() {
		if (buildings==null) buildings = new Vector<Building>();
		else buildings.removeAllElements();
		//Set stats
		Vector<Element> elementlist = new Vector<Element>();
		elementlist = MilkFile.getMilkElementsFromFiles(MilkFile.getXmlFilePath(file)+file, noeud);
		buildings = getMilkVarList(elementlist);
		//Set info
		Vector<Element> elementlInfos = new Vector<Element>();
		elementlInfos = MilkFile.getMilkElementsFromFiles(MilkInterface.getXmlLangPath()+file, noeud);
		setInfo(buildings, elementlInfos);
		//Set icon
		Vector<Element> elementlIcon = new Vector<Element>();
		elementlIcon = MilkFile.getMilkElementsFromFiles(MilkImage.getXmlIconsPath(file)+file, noeud);
		setIcon(buildings, elementlIcon);
		//Set scene
		Vector<Element> elementlScene = new Vector<Element>();
		elementlScene = MilkFile.getMilkElementsFromFiles(MilkImage.getXmlScenesPath(file)+file, noeud);
		setScene(buildings, elementlScene);
		
		return buildings;
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
	/*
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
	}*/
	
	public static ObservableList<Building> getFullListe() {
		if (modelListe==null){
			if (buildings==null)setMilkVarFromFiles();
			if (modelNeutralListe==null)getNeutralListe();
			if (modelScienceListe==null)getScienceListe();
			if (modelMagicListe==null)getMagicListe();
			modelListe = FXCollections.observableArrayList();
			merge(modelListe, modelNeutralListe, modelScienceListe, modelMagicListe);
		}
		return modelListe;
	}

	public static ObservableList<Building> getNeutralListe() {
		if (modelNeutralListe==null){
			if (buildings==null)setMilkVarFromFiles();
			modelNeutralListe = FXCollections.observableArrayList();
			if (buildings!=null){
				for (Building building:buildings){
					try {
						if(building.getAttrib().getTree()==ThingAttrib.Tree_Neutral)modelNeutralListe.add((Building) building.clone());
					} catch (CloneNotSupportedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		return modelNeutralListe;
	}

	public static ObservableList<Building> getScienceListe() {
		if (modelScienceListe==null){
			if (buildings==null)setMilkVarFromFiles();
			modelScienceListe = FXCollections.observableArrayList();
			if (buildings!=null){
				for (Building building:buildings){
					try {
						if(building.getAttrib().getTree()==ThingAttrib.Tree_Science)modelScienceListe.add((Building) building.clone());
					} catch (CloneNotSupportedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		return modelScienceListe;
	}

	public static ObservableList<Building> getMagicListe() {
		if (modelMagicListe==null){
			if (buildings==null)setMilkVarFromFiles();
			modelMagicListe = FXCollections.observableArrayList();
			if (buildings!=null){
				for (Building building:buildings){
					try {
						if(building.getAttrib().getTree()==ThingAttrib.Tree_Magic)modelMagicListe.add((Building) building.clone());
					} catch (CloneNotSupportedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		return modelMagicListe;
	}

	public static double getIncomeFromList(double buildProdBonus, double buildQualBonus) {
		if (modelListe==null)getFullListe();
		double tIncome = 0;
		for (Building thing:modelListe){
			tIncome += thing.getIncome(buildProdBonus,buildQualBonus) ;
		}
		return tIncome;
	}

	// Fields

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
	public void setAgent(Element milkElement) {
		this.agent.setValueFromNode(milkElement);;
	}
	public void setBonus(Element milkElement) {
		this.bonus.setValueFromNode(milkElement);;
	}
	public void setPopulation(Element milkElement) {
		this.population.setValueFromNode(milkElement);;
	}
	/*
	@Override
	public void setNullValueFromNode(Element milkElement) {
		super.setNullValueFromNode(milkElement);
		this.setNullAgent(milkElement);
		this.setNullBonus(milkElement);
		this.setNullPopulation(milkElement);
	}
	public void setNullAgent(Element milkElement) {
		this.agent.setValueFromNode(milkElement);
	}
	public void setNullBonus(Element milkElement) {
		this.bonus.setNullValueFromNode(milkElement);
	}
	public void setNullPopulation(Element milkElement) {
		this.population.setNullValueFromNode(milkElement);
	}*/
	
	// field methods
	
	public Agent getAgent() {
		return this.agent;
	}
	public void setAgent(Agent agent) {
		this.agent = agent;
	}
	
	public Bonus getBonus() {
		return this.bonus;
	}
	public void setBonus(Bonus bonus) {
		this.bonus = bonus;
	}
	
	public Population getPopulation() {
		return this.population;
	}
	public void setPopulation(Population population) {
		this.population = population;
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