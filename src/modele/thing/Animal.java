package modele.thing;

import java.util.Vector;

import org.w3c.dom.Element;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import modele.MilkFile;
import modele.MilkImage;
import modele.MilkInterface;
import modele.MilkKind;
import modele.carac.ThingAttrib;

public class Animal extends Thing implements Cloneable {

	public static final String file	= "Animal", noeud	= "animal";
	public String getNoeud() {return noeud;}

	private static Vector<Animal> animals;
	private static ObservableList<Animal> modelListe;
	private static ObservableList<Animal> modelNeutralListe;
	private static ObservableList<Animal> modelScienceListe;
	private static ObservableList<Animal> modelMagicListe;

	@SafeVarargs
	private static ObservableList<Animal> merge(ObservableList<Animal> into, ObservableList<Animal>... lists) {
        final ObservableList<Animal> list = into;
        for (ObservableList<Animal> l : lists) {
            list.addAll(l);
            l.addListener((javafx.collections.ListChangeListener.Change<? extends Animal> c) -> {
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
	private static Vector<Animal> setMilkVarFromFiles() {
		if (animals==null) animals = new Vector<Animal>();
		else animals.removeAllElements();
		//Set stats
		Vector<Element> elementlist = new Vector<Element>();
		elementlist = MilkFile.getMilkElementsFromFiles(MilkFile.getXmlFilePath(file)+file, noeud);
		animals = getMilkVarList(elementlist);
		//Set info
		Vector<Element> elementlInfos = new Vector<Element>();
		elementlInfos = MilkFile.getMilkElementsFromFiles(MilkInterface.getXmlLangPath()+file, noeud);
		setInfo(animals, elementlInfos);
		//Set icon
		Vector<Element> elementlIcon = new Vector<Element>();
		elementlIcon = MilkFile.getMilkElementsFromFiles(MilkImage.getXmlIconsPath(file)+file, noeud);
		setIcon(animals, elementlIcon);
		//Set scene
		Vector<Element> elementlScene = new Vector<Element>();
		elementlScene = MilkFile.getMilkElementsFromFiles(MilkImage.getXmlScenesPath(file)+file, noeud);
		setScene(animals, elementlScene);
		
		return animals;
	}

	public static Vector<Animal> getMilkVarList(Vector<Element> elementlist) {
		Vector<Animal> animals = new Vector<Animal>();
		for (Element elementMilk: elementlist) {
			try {
				Animal animal = new Animal(elementMilk);
				animals.add(animal);
			} catch (Exception e) {e.printStackTrace();}
		}
		return animals;
	}
	/*
	public static Vector<Animal> getNullMilkVarList(Vector<Element> elementlist) {
		Vector<Animal> animals = new Vector<Animal>();
		for (Element elementMilk: elementlist) {
			try {
				Animal animal = new Animal();
				animal.setNullValueFromNode(elementMilk);
				animals.add(animal);
			} catch (Exception e) {e.printStackTrace();}
		}
		return animals;
	}*/
	
	public static ObservableList<Animal> getFullListe() {
		if (modelListe==null){
			if (animals==null)setMilkVarFromFiles();
			if (modelNeutralListe==null)getNeutralListe();
			if (modelScienceListe==null)getScienceListe();
			if (modelMagicListe==null)getMagicListe();
			modelListe = FXCollections.observableArrayList();
			merge(modelListe, modelNeutralListe, modelScienceListe, modelMagicListe);
		}
		return modelListe;
	}

	public static ObservableList<Animal> getNeutralListe() {
		if (modelNeutralListe==null){
			if (animals==null)setMilkVarFromFiles();
			modelNeutralListe = FXCollections.observableArrayList();
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
			modelScienceListe = FXCollections.observableArrayList();
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
			modelMagicListe = FXCollections.observableArrayList();
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
	
	
	// Constructors
	
	public Animal() {
		super();
		this.setKind(MilkKind.kind_Animal);
		this.setSellPrice((float)70);
		this.setProductivity(1);
	}
	public Animal(Element milkElement) {
		super();
		this.setKind(MilkKind.kind_Animal);
		this.setSellPrice((float)70);
		this.setProductivity(1);
		this.setValueFromNode(milkElement);
	}
	
	// other object methods
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		Animal clone = (Animal) super.clone();
		return clone;
	}
}
