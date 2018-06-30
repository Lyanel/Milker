package modele.thing;

import java.util.ArrayList;

import org.w3c.dom.Element;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.util.Callback;
import modele.baseObject.MilkFile;
import modele.baseObject.MilkImage;
import modele.baseObject.MilkInterface;
import modele.baseObject.MilkKind;
import modele.carac.ThingAttrib;

public class SlaveAnimal extends Slave implements Cloneable {

	public static final String file		= "SlaveAnimal";

	private static ArrayList<SlaveAnimal> slaveAnimals;
	private static ObservableList<SlaveAnimal> modelListe;
	private static ObservableList<SlaveAnimal> modelNeutralListe;
	private static ObservableList<SlaveAnimal> modelScienceListe;
	private static ObservableList<SlaveAnimal> modelMagicListe;
	
	private static ArrayList<SlaveAnimal> setMilkVarFromFiles() {
		if (slaveAnimals==null) slaveAnimals = new ArrayList<SlaveAnimal>();
		else slaveAnimals.clear();
		//Set stats
		ArrayList<Element> elementlist = new ArrayList<Element>();
		elementlist = MilkFile.getMilkElementsFromFiles(MilkFile.getXmlFilePath(file)+file, noeud);
		slaveAnimals = getMilkVarList(elementlist);
		//Set info
		ArrayList<Element> elementlInfos = new ArrayList<Element>();
		elementlInfos = MilkFile.getMilkElementsFromFiles(MilkInterface.getXmlLangPath()+file, noeud);
		setInfo(slaveAnimals, elementlInfos);
		//Set icon
		ArrayList<Element> elementlIcon = new ArrayList<Element>();
		elementlIcon = MilkFile.getMilkElementsFromFiles(MilkImage.getXmlIconsPath(file)+file, noeud);
		setIcon(slaveAnimals, elementlIcon);
		//Set scene
		ArrayList<Element> elementlScene = new ArrayList<Element>();
		elementlScene = MilkFile.getMilkElementsFromFiles(MilkImage.getXmlScenesPath(file)+file, noeud);
		setScene(slaveAnimals, elementlScene);
		
		return slaveAnimals;
	}

	public static ArrayList<SlaveAnimal> getMilkVarList(ArrayList<Element> elementlist) {
		ArrayList<SlaveAnimal> slaveAnimals = new ArrayList<SlaveAnimal>();
		for (Element elementMilk: elementlist) {
			try {
				SlaveAnimal slaveAnimal = new SlaveAnimal(elementMilk);
				slaveAnimals.add(slaveAnimal);
			} catch (Exception e) {e.printStackTrace();}
		}
		return slaveAnimals;
	}
	
	public static void updateInfoFromFiles() {
		if (modelListe==null) getSAFullListe();
		ArrayList<Element> elementlInfos = new ArrayList<Element>();
		elementlInfos = MilkFile.getMilkElementsFromFiles(MilkInterface.getXmlLangPath()+file, noeud);
		setInfo(modelListe, elementlInfos);
	}

	public static Callback<SlaveAnimal, Observable[]> extractor() {
        return (SlaveAnimal p) -> new Observable[]{p.getInfo().getObrservableName(), p.getAttrib().getObrservableQuant(), p.getAttrib().getObrservableActives()};
	}
	
	public static ObservableList<SlaveAnimal> getSAFullListe() {
		if (modelListe==null){
			if (slaveAnimals==null)setMilkVarFromFiles();
			if (modelNeutralListe==null)getNeutralListe();
			if (modelScienceListe==null)getScienceListe();
			if (modelMagicListe==null)getMagicListe();
			modelListe = FXCollections.observableArrayList(extractor());
			merge(modelListe, modelNeutralListe, modelScienceListe, modelMagicListe);
		}
		return modelListe;
	}

	public static ObservableList<SlaveAnimal> getSANeutralListe() {
		if (modelNeutralListe==null){
			if (slaveAnimals==null)setMilkVarFromFiles();
			modelNeutralListe = FXCollections.observableArrayList(extractor());
			if (slaveAnimals!=null){
				for (SlaveAnimal slaveAnimal:slaveAnimals){
					try {
						if(slaveAnimal.getAttrib().getTree()==ThingAttrib.Tree_Neutral)modelNeutralListe.add((SlaveAnimal) slaveAnimal.clone());
					} catch (CloneNotSupportedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		return modelNeutralListe;
	}

	public static ObservableList<SlaveAnimal> getSAScienceListe() {
		if (modelScienceListe==null){
			if (slaveAnimals==null)setMilkVarFromFiles();
			modelScienceListe = FXCollections.observableArrayList(extractor());
			if (slaveAnimals!=null){
				for (SlaveAnimal slaveAnimal:slaveAnimals){
					try {
						if(slaveAnimal.getAttrib().getTree()==ThingAttrib.Tree_Science)modelScienceListe.add((SlaveAnimal) slaveAnimal.clone());
					} catch (CloneNotSupportedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		return modelScienceListe;
	}

	public static ObservableList<SlaveAnimal> getSAMagicListe() {
		if (modelMagicListe==null){
			if (slaveAnimals==null)setMilkVarFromFiles();
			modelMagicListe = FXCollections.observableArrayList(extractor());
			if (slaveAnimals!=null){
				for (SlaveAnimal slaveAnimal:slaveAnimals){
					try {
						if(slaveAnimal.getAttrib().getTree()==ThingAttrib.Tree_Magic)modelMagicListe.add((SlaveAnimal) slaveAnimal.clone());
					} catch (CloneNotSupportedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		return modelMagicListe;
	}
	
	// Constructors
	
	public SlaveAnimal() {
		super();
		this.setKind(MilkKind.kind_Slave_Animal);
	}
	public SlaveAnimal(Element milkElement) {
		super();
		this.setKind(MilkKind.kind_Slave_Animal);
		this.setValueFromNode(milkElement);
	}
	
	// other object methods
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		SlaveAnimal clone = (SlaveAnimal) super.clone();
		return clone;
	}
}
