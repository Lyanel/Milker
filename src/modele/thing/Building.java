package modele.thing;

import modele.baseObject.MilkKind;
import modele.carac.Agent;
import modele.carac.Bonus;
import modele.carac.Population;

import org.w3c.dom.Element;


public class Building extends Thing {

	public String getNoeud() {return BuildingList.getInstance().getNoeud();}
	
	/*	
	private static final String file	= "Building", noeud	= "building";
	public String getNoeud() {return noeud;}

	private static ArrayList<Building> buildings;
	private static ObservableList<Building> modelListe;
	private static ObservableList<Building> modelNeutralListe;
	private static ObservableList<Building> modelScienceListe;
	private static ObservableList<Building> modelMagicListe;
	
	private static ArrayList<Building> setMilkVarFromFiles() {
		if (buildings==null) buildings = new ArrayList<Building>();
		else buildings.clear();
		//Set stats
		ArrayList<Element> elementlist = new ArrayList<Element>();
		elementlist = MilkFile.getMilkElementsFromFiles(MilkFile.getXmlFilePath(file)+file, noeud);
		buildings = getMilkVarList(elementlist);
		//Set info
		ArrayList<Element> elementlInfos = new ArrayList<Element>();
		elementlInfos = MilkFile.getMilkElementsFromFiles(MilkInterface.getXmlLangPath()+file, noeud);
		setInfo(buildings, elementlInfos);
		//Set icon
		ArrayList<Element> elementlIcon = new ArrayList<Element>();
		elementlIcon = MilkFile.getMilkElementsFromFiles(MilkImage.getXmlIconsPath(file)+file, noeud);
		setIcon(buildings, elementlIcon);
		//Set scene
		ArrayList<Element> elementlScene = new ArrayList<Element>();
		elementlScene = MilkFile.getMilkElementsFromFiles(MilkImage.getXmlScenesPath(file)+file, noeud);
		setScene(buildings, elementlScene);
		
		return buildings;
	}
	public static ArrayList<Building> getMilkVarList(ArrayList<Element> elementlist) {
		ArrayList<Building> buildings = new ArrayList<Building>();
		for (Element elementMilk: elementlist) {
			try {
				Building building = new Building(elementMilk);
				buildings.add(building);
			} catch (Exception e) {e.printStackTrace();}
		}
		return buildings;
	}
	
	public static void updateInfoFromFiles() {
		if (modelListe==null) getFullListe();
		//Set stats
		ArrayList<Element> elementlInfos = new ArrayList<Element>();
		elementlInfos = MilkFile.getMilkElementsFromFiles(MilkInterface.getXmlLangPath()+file, noeud);
		setInfo(modelListe, elementlInfos);
	}

	public static Callback<Building, Observable[]> extractor() {
        return (Building p) -> new Observable[]{p.getInfo().getObrservableName(), p.getAttrib().getObrservableQuant(), p.getAttrib().getObrservableActives()};
	}
	
	public static void resetListes() {
		setNeutralListe();
		setScienceListe();
		setMagicListe();
		setFullListe();
	}

	public static ObservableList<Building> getFullListe() {
		if (modelListe==null) setFullListe();
		return modelListe;
	}
	
	public static void setFullListe() {
		if (modelNeutralListe==null)getNeutralListe();
		if (modelScienceListe==null)getScienceListe();
		if (modelMagicListe==null)getMagicListe();
		modelListe = FXCollections.observableArrayList(extractor());
		merge(modelListe, modelNeutralListe, modelScienceListe, modelMagicListe);
	}

	public static ObservableList<Building> getNeutralListe() {
		if (modelNeutralListe==null) setNeutralListe();
		return modelNeutralListe;
	}

	public static void setNeutralListe() {
		modelNeutralListe = FXCollections.observableArrayList(extractor());
		if (buildings==null)setMilkVarFromFiles();
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

	public static ObservableList<Building> getScienceListe() {
		if (modelScienceListe==null) setScienceListe();
		return modelScienceListe;
	}

	public static void setScienceListe() {
		modelScienceListe = FXCollections.observableArrayList(extractor());
		if (buildings==null)setMilkVarFromFiles();
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

	public static ObservableList<Building> getMagicListe() {
		if (modelMagicListe==null) setMagicListe();
		return modelMagicListe;
	}
	
	public static void setMagicListe() {
		modelMagicListe = FXCollections.observableArrayList(extractor());
		if (buildings==null)setMilkVarFromFiles();
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

	public static double getIncomeFromList(double buildProdBonus, double buildQualBonus) {
		if (modelListe==null)getFullListe();
		double tIncome = 0;
		for (Building building:modelListe){
			tIncome += building.getIncome(buildProdBonus,buildQualBonus) ;
		}
		return tIncome;
	}
*/
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
		this.setKind(MilkKind.Building);
		this.setSellPrice((float)70);
	}
	public Building(Element milkElement) {
		super();
		this.agent = new Agent();
		this.bonus = new Bonus();
		this.population = new Population();
		this.setKind(MilkKind.Building);
		this.setSellPrice((float)70);
		this.setValueFromNode(milkElement);
	}
	
	public Building(Building original) {
		super(original);
		this.agent = new Agent(original.getAgent());
		this.bonus = new Bonus(original.getBonus());
		this.population = new Population(original.getPopulation());
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
		this.agent.setValueFromNode(milkElement);
	}
	public void setBonus(Element milkElement) {
		this.bonus.setValueFromNode(milkElement);
	}
	public void setPopulation(Element milkElement) {
		this.population.setValueFromNode(milkElement);
	}

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
}