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

public class SlaveAnimal extends Slave implements Cloneable {

	public static final String file		= "SlaveAnimal";

	private static Vector<SlaveAnimal> slaveAnimals;
	private static ObservableList<SlaveAnimal> modelListe;
	private static ObservableList<SlaveAnimal> modelNeutralListe;
	private static ObservableList<SlaveAnimal> modelScienceListe;
	private static ObservableList<SlaveAnimal> modelMagicListe;
	
	@SafeVarargs
	private static ObservableList<SlaveAnimal> merge(ObservableList<SlaveAnimal> into, ObservableList<SlaveAnimal>... lists) {
        final ObservableList<SlaveAnimal> list = into;
        for (ObservableList<SlaveAnimal> l : lists) {
            list.addAll(l);
            l.addListener((javafx.collections.ListChangeListener.Change<? extends SlaveAnimal> c) -> {
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
	
	private static Vector<SlaveAnimal> setMilkVarFromFiles() {
		if (slaveAnimals==null) slaveAnimals = new Vector<SlaveAnimal>();
		else slaveAnimals.removeAllElements();
		//Set stats
		Vector<Element> elementlist = new Vector<Element>();
		elementlist = MilkFile.getMilkElementsFromFiles(MilkFile.getXmlFilePath(file)+file, noeud);
		slaveAnimals = getMilkVarList(elementlist);
		//Set info
		Vector<Element> elementlInfos = new Vector<Element>();
		elementlInfos = MilkFile.getMilkElementsFromFiles(MilkInterface.getXmlLangPath()+file, noeud);
		setInfo(slaveAnimals, elementlInfos);
		//Set icon
		Vector<Element> elementlIcon = new Vector<Element>();
		elementlIcon = MilkFile.getMilkElementsFromFiles(MilkImage.getXmlIconsPath(file)+file, noeud);
		setIcon(slaveAnimals, elementlIcon);
		//Set scene
		Vector<Element> elementlScene = new Vector<Element>();
		elementlScene = MilkFile.getMilkElementsFromFiles(MilkImage.getXmlScenesPath(file)+file, noeud);
		setScene(slaveAnimals, elementlScene);
		
		return slaveAnimals;
	}

	public static Vector<SlaveAnimal> getMilkVarList(Vector<Element> elementlist) {
		Vector<SlaveAnimal> slaveAnimals = new Vector<SlaveAnimal>();
		for (Element elementMilk: elementlist) {
			try {
				SlaveAnimal slaveAnimal = new SlaveAnimal(elementMilk);
				slaveAnimals.add(slaveAnimal);
			} catch (Exception e) {e.printStackTrace();}
		}
		return slaveAnimals;
	}
	/*
	public static Vector<SlaveAnimal> getNullMilkVarList(Vector<Element> elementlist) {
		Vector<SlaveAnimal> slaveAnimals = new Vector<SlaveAnimal>();
		for (Element elementMilk: elementlist) {
			try {
				SlaveAnimal slaveAnimal = new SlaveAnimal();
				slaveAnimal.setNullValueFromNode(elementMilk);
				slaveAnimals.add(slaveAnimal);
			} catch (Exception e) {e.printStackTrace();}
		}
		return slaveAnimals;
	}*/
	
	public static ObservableList<SlaveAnimal> getSAFullListe() {
		if (modelListe==null){
			if (slaveAnimals==null)setMilkVarFromFiles();
			if (modelNeutralListe==null)getNeutralListe();
			if (modelScienceListe==null)getScienceListe();
			if (modelMagicListe==null)getMagicListe();
			modelListe = FXCollections.observableArrayList();
			merge(modelListe, modelNeutralListe, modelScienceListe, modelMagicListe);
		}
		return modelListe;
	}

	public static ObservableList<SlaveAnimal> getSANeutralListe() {
		if (modelNeutralListe==null){
			if (slaveAnimals==null)setMilkVarFromFiles();
			modelNeutralListe = FXCollections.observableArrayList();
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
			modelScienceListe = FXCollections.observableArrayList();
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
			modelMagicListe = FXCollections.observableArrayList();
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
