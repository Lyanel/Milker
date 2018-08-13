package modele.thing;


import org.w3c.dom.Element;

import modele.baseObject.MilkKind;

public class Animal extends LivingBeing {
	public String getNoeud() {return AnimalList.getInstance().getNoeud();}
	/*
	public static final String file	= "Animal", noeud	= "animal";
	public String getNoeud() {return noeud;}

	private static ArrayList<Animal> animals;
	private static ObservableList<Animal> modelListe;
	private static ObservableList<Animal> modelNeutralListe;
	private static ObservableList<Animal> modelScienceListe;
	private static ObservableList<Animal> modelMagicListe;
	
	private static ArrayList<Animal> setMilkVarFromFiles() {
		if (animals==null) animals = new ArrayList<Animal>();
		else animals.clear();
		//Set stats
		ArrayList<Element> elementlist = new ArrayList<Element>();
		elementlist = MilkFile.getMilkElementsFromFiles(MilkFile.getXmlFilePath(file)+file, noeud);
		animals = getMilkVarList(elementlist);
		//Set info
		ArrayList<Element> elementlInfos = new ArrayList<Element>();
		elementlInfos = MilkFile.getMilkElementsFromFiles(MilkInterface.getXmlLangPath()+file, noeud);
		setInfo(animals, elementlInfos);
		//Set icon
		ArrayList<Element> elementlIcon = new ArrayList<Element>();
		elementlIcon = MilkFile.getMilkElementsFromFiles(MilkImage.getXmlIconsPath(file)+file, noeud);
		setIcon(animals, elementlIcon);
		//Set scene
		ArrayList<Element> elementlScene = new ArrayList<Element>();
		elementlScene = MilkFile.getMilkElementsFromFiles(MilkImage.getXmlScenesPath(file)+file, noeud);
		setScene(animals, elementlScene);
		
		return animals;
	}

	public static ArrayList<Animal> getMilkVarList(ArrayList<Element> elementlist) {
		ArrayList<Animal> animals = new ArrayList<Animal>();
		for (Element elementMilk: elementlist) {
			try {
				Animal animal = new Animal(elementMilk);
				animals.add(animal);
			} catch (Exception e) {e.printStackTrace();}
		}
		return animals;
	}
	
	public static void updateInfoFromFiles() {
		if (modelListe==null) getFullListe();
		ArrayList<Element> elementlInfos = new ArrayList<Element>();
		elementlInfos = MilkFile.getMilkElementsFromFiles(MilkInterface.getXmlLangPath()+file, noeud);
		setInfo(modelListe, elementlInfos);
	}

	public static Callback<Animal, Observable[]> extractor() {
        return (Animal p) -> new Observable[]{p.getInfo().getObrservableName(), p.getAttrib().getObrservableQuant(), p.getAttrib().getObrservableActives()};
	}
	
	public static ObservableList<Animal> getFullListe() {
		if (modelListe==null){
			if (animals==null)setMilkVarFromFiles();
			if (modelNeutralListe==null)getNeutralListe();
			if (modelScienceListe==null)getScienceListe();
			if (modelMagicListe==null)getMagicListe();
			modelListe = FXCollections.observableArrayList(extractor());
			merge(modelListe, modelNeutralListe, modelScienceListe, modelMagicListe);
		}
		return modelListe;
	}

	public static ObservableList<Animal> getNeutralListe() {
		if (modelNeutralListe==null){
			if (animals==null)setMilkVarFromFiles();
			modelNeutralListe = FXCollections.observableArrayList(extractor());
			if (animals!=null){
				for (Animal animal:animals){
					try {
						if(animal.getAttrib().getTree()==ThingAttrib.Tree_Neutral)modelNeutralListe.add((Animal) animal.clone());
					} catch (CloneNotSupportedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		return modelNeutralListe;
	}

	public static ObservableList<Animal> getScienceListe() {
		if (modelScienceListe==null){
			if (animals==null)setMilkVarFromFiles();
			modelScienceListe = FXCollections.observableArrayList(extractor());
			if (animals!=null){
				for (Animal animal:animals){
					try {
						if(animal.getAttrib().getTree()==ThingAttrib.Tree_Science)modelScienceListe.add((Animal) animal.clone());
					} catch (CloneNotSupportedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		return modelScienceListe;
	}

	public static ObservableList<Animal> getMagicListe() {
		if (modelMagicListe==null){
			if (animals==null)setMilkVarFromFiles();
			modelMagicListe = FXCollections.observableArrayList(extractor());
			if (animals!=null){
				for (Animal animal:animals){
					try {
						if(animal.getAttrib().getTree()==ThingAttrib.Tree_Magic)modelMagicListe.add((Animal) animal.clone());
					} catch (CloneNotSupportedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		return modelMagicListe;
	}

	public static double getIncomeFromList(double toolProdBonus, double toolQualBonus, double cattleProdBonus, double cattleQualBonus, double buildProdBonus, double buildQualBonus) {
		if (modelListe==null)Animal.getFullListe();
		double tIncome = 0;
		for (Animal thing:modelListe){
			tIncome += thing.getIncome(toolProdBonus,toolQualBonus,cattleProdBonus,cattleQualBonus,buildProdBonus,buildQualBonus) ;
		}
		return tIncome;
	}
	*/
	
	// Constructors
	
	public Animal() {
		super();
		this.setKind(MilkKind.Animal);
		this.setSellPrice((float)70);
		this.setProductivity(1);
	}
	public Animal(Element milkElement) {
		super();
		this.setKind(MilkKind.Animal);
		this.setSellPrice((float)70);
		this.setProductivity(1);
		this.setValueFromNode(milkElement);
	}
	public Animal(Animal original) {
		super(original);
	}
	
	// other object methods
	
}
