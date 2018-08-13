package modele.thing;

import modele.baseObject.MilkVarList;
import modele.carac.MilkTree;

import java.util.ArrayList;

import org.w3c.dom.Element;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.util.Callback;


public class AnimalList extends MilkVarList {

	private static AnimalList INSTANCE = null;
	private static final String file	= "Animal", noeud	= "animal";
	public String getFile() {return file;}
	public String getNoeud() {return noeud;}

	public static AnimalList getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new AnimalList();
        }
        return INSTANCE;
    }

	public static Callback<Animal, Observable[]> extractor() {
        return (Animal p) -> new Observable[]{p.getInfo().getObrservableName(), p.getQuantity().getObrservableQuant(), p.getQuantity().getObrservableActives()};
	}
	
	// Fields

	private ArrayList<Animal> animals;
	private ObservableList<Animal> modelListe;
	private ObservableList<Animal> modelNeutralListe;
	private ObservableList<Animal> modelScienceListe;
	private ObservableList<Animal> modelMagicListe;

	// Constructors
	
	private AnimalList() {
		super();
		setMilkVarFromFiles();
	}

	// field methods
	
	private ArrayList<Animal> setMilkVarFromFiles() {
		if (animals==null) animals = new ArrayList<Animal>();
		else animals.clear();
		animals = getMilkVarList(getElementStat());
		setInfo(animals, getElementInfo());
		setIcon(animals, getElementIcon());
		setScene(animals, getElementScene());
		return animals;
	}
	
	private ArrayList<Animal> getMilkVarList(ArrayList<Element> elementlist) {
		ArrayList<Animal> animals = new ArrayList<Animal>();
		for (Element elementMilk: elementlist) {
			try {
				Animal animal = new Animal(elementMilk);
				animals.add(animal);
			} catch (Exception e) {e.printStackTrace();}
		}
		return animals;
	}
	
	public void updateInfoFromFiles() {
		if (modelListe==null) getFullListe();
		setInfo(modelListe, getElementInfo());
	}

	public ObservableList<Animal> getFullListe() {
		if (modelListe==null) setFullListe();
		return modelListe;
	}

	public ObservableList<Animal> getNeutralListe() {
		if (modelNeutralListe==null) setNeutralListe();
		return modelNeutralListe;
	}

	public ObservableList<Animal> getScienceListe() {
		if (modelScienceListe==null) setScienceListe();
		return modelScienceListe;
	}

	public ObservableList<Animal> getMagicListe() {
		if (modelMagicListe==null) setMagicListe();
		return modelMagicListe;
	}

	@Override
	public void setFullListe() {
		if (modelNeutralListe==null)getNeutralListe();
		if (modelScienceListe==null)getScienceListe();
		if (modelMagicListe==null)getMagicListe();
		modelListe = FXCollections.observableArrayList(extractor());
		merge(modelListe, modelNeutralListe, modelScienceListe, modelMagicListe);
	}

	@Override
	public void setNeutralListe() {
		modelNeutralListe = FXCollections.observableArrayList(extractor());
		if (animals==null)setMilkVarFromFiles();
		if (animals!=null){
			for (Animal animal:animals){
				if(animal.getTree().getTree()==MilkTree.Tree_Neutral)modelNeutralListe.add(new Animal(animal));
			}
		}
	}

	@Override
	public void setScienceListe() {
		modelScienceListe = FXCollections.observableArrayList(extractor());
		if (animals==null)setMilkVarFromFiles();
		if (animals!=null){
			for (Animal animal:animals){
				if(animal.getTree().getTree()==MilkTree.Tree_Science)modelScienceListe.add(new Animal(animal));
			}
		}
	}

	@Override
	public void setMagicListe() {
		modelMagicListe = FXCollections.observableArrayList(extractor());
		if (animals==null)setMilkVarFromFiles();
		if (animals!=null){
			for (Animal animal:animals){
				if(animal.getTree().getTree()==MilkTree.Tree_Magic)modelMagicListe.add(new Animal(animal));
			}
		}
	}
	
	// other method : 
	
	public double getIncome(double toolProdBonus, double toolQualBonus, double cattleProdBonus, double cattleQualBonus, double buildProdBonus, double buildQualBonus) {
		if (modelListe==null)getFullListe();
		return getIncomeFromList(toolProdBonus,toolQualBonus,cattleProdBonus,cattleQualBonus,buildProdBonus,buildQualBonus,modelListe) ;
	}	
}