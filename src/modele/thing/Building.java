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

	private static Vector<Building> buildings;
	public static final String file		= "Building";
	public static final String noeud	= "building";
	public String getNoeud() {return noeud;}

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

	private static void setInfo(Vector<Building> buildings, Vector<Element> elementInfos) {
		for (Element elementInfo: elementInfos) {
			try {
				Building buildingInfo = new Building(elementInfo);
				buildingInfo.setInfo(elementInfo);
				for (Building building:buildings){
					if (buildingInfo.equals(building)){
						building.setInfo(buildingInfo.getInfo());
						break;
					}
				}
			} catch (Exception e) {e.printStackTrace();}
		}
	}

	private static void setIcon(Vector<Building> buildings, Vector<Element> elementIcons) {
		for (Element elementIcon: elementIcons) {
			try {
				Building buildingIcon = new Building(elementIcon);
				buildingIcon.setIcon(elementIcon);
				for (Building building:buildings){
					if (buildingIcon.equals(building)){
						building.setIcon(buildingIcon.getIcon());
						break;
					}
				}
			} catch (Exception e) {e.printStackTrace();}
		}
	}

	private static void setScene(Vector<Building> buildings, Vector<Element> elementScenes) {
		for (Element elementScene: elementScenes) {
			try {
				Building buildingScene = new Building(elementScene);
				buildingScene.setScene(elementScene);
				for (Building building:buildings){
					if (buildingScene.equals(building)){
						building.setScene(buildingScene.getScene());
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
					if(building.getAttrib().getTree()==ThingAttrib.Tree_Neutral)clone.add((Building) building.clone());
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
					if(building.getAttrib().getTree()==ThingAttrib.Tree_Science)clone.add((Building) building.clone());
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
					if(building.getAttrib().getTree()==ThingAttrib.Tree_Magic)clone.add((Building) building.clone());
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

	public static double getIncomeFromList(ObservableList<Building> thingList, double buildProdBonus,
			double buildQualBonus) {
		double tIncome = 0;
		for (Thing thing:thingList){
			tIncome += thing.getIncome(buildProdBonus,buildQualBonus) ;
		}
		return tIncome;
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