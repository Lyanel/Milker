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

public class SlaveHuman extends Slave implements Cloneable {
	
	public static final String file		= "SlaveHuman";

	private static Vector<SlaveHuman> slaveHumans;
	private static ObservableList<SlaveHuman> modelListe;
	private static ObservableList<SlaveHuman> modelNeutralListe;
	private static ObservableList<SlaveHuman> modelScienceListe;
	private static ObservableList<SlaveHuman> modelMagicListe;
	
	@SafeVarargs
	private static ObservableList<SlaveHuman> merge(ObservableList<SlaveHuman> into, ObservableList<SlaveHuman>... lists) {
        final ObservableList<SlaveHuman> list = into;
        for (ObservableList<SlaveHuman> l : lists) {
            list.addAll(l);
            l.addListener((javafx.collections.ListChangeListener.Change<? extends SlaveHuman> c) -> {
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
	
	private static Vector<SlaveHuman> setMilkVarFromFiles() {
		if (slaveHumans==null) slaveHumans = new Vector<SlaveHuman>();
		else slaveHumans.removeAllElements();
		//Set stats
		Vector<Element> elementlist = new Vector<Element>();
		elementlist = MilkFile.getMilkElementsFromFiles(MilkFile.getXmlFilePath(file)+file, noeud);
		slaveHumans = getMilkVarList(elementlist);
		//Set info
		Vector<Element> elementlInfos = new Vector<Element>();
		elementlInfos = MilkFile.getMilkElementsFromFiles(MilkInterface.getXmlLangPath()+file, noeud);
		setInfo(slaveHumans, elementlInfos);
		//Set icon
		Vector<Element> elementlIcon = new Vector<Element>();
		elementlIcon = MilkFile.getMilkElementsFromFiles(MilkImage.getXmlIconsPath(file)+file, noeud);
		setIcon(slaveHumans, elementlIcon);
		//Set scene
		Vector<Element> elementlScene = new Vector<Element>();
		elementlScene = MilkFile.getMilkElementsFromFiles(MilkImage.getXmlScenesPath(file)+file, noeud);
		setScene(slaveHumans, elementlScene);
		
		return slaveHumans;
	}

	public static Vector<SlaveHuman> getMilkVarList(Vector<Element> elementlist) {
		Vector<SlaveHuman> slaveHumans = new Vector<SlaveHuman>();
		for (Element elementMilk: elementlist) {
			try {
				SlaveHuman slaveHuman = new SlaveHuman(elementMilk);
				slaveHumans.add(slaveHuman);
			} catch (Exception e) {e.printStackTrace();}
		}
		return slaveHumans;
	}
	/*
	public static Vector<SlaveHuman> getNullMilkVarList(Vector<Element> elementlist) {
		Vector<SlaveHuman> slaveHumans = new Vector<SlaveHuman>();
		for (Element elementMilk: elementlist) {
			try {
				SlaveHuman slaveHuman = new SlaveHuman();
				slaveHuman.setNullValueFromNode(elementMilk);
				slaveHumans.add(slaveHuman);
			} catch (Exception e) {e.printStackTrace();}
		}
		return slaveHumans;
	}*/

	public static ObservableList<SlaveHuman> getSHFullListe() {
		if (modelListe==null){
			if (slaveHumans==null)setMilkVarFromFiles();
			if (modelNeutralListe==null)getNeutralListe();
			if (modelScienceListe==null)getScienceListe();
			if (modelMagicListe==null)getMagicListe();
			modelListe = FXCollections.observableArrayList();
			merge(modelListe, modelNeutralListe, modelScienceListe, modelMagicListe);
		}
		return modelListe;
	}

	public static ObservableList<SlaveHuman> getSHNeutralListe() {
		if (modelNeutralListe==null){
			if (slaveHumans==null)setMilkVarFromFiles();
			modelNeutralListe = FXCollections.observableArrayList();
			if (slaveHumans!=null){
				for (SlaveHuman slaveHuman:slaveHumans){
					try {
						if(slaveHuman.getAttrib().getTree()==ThingAttrib.Tree_Neutral)modelNeutralListe.add((SlaveHuman) slaveHuman.clone());
					} catch (CloneNotSupportedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		return modelNeutralListe;
	}

	public static ObservableList<SlaveHuman> getSHScienceListe() {
		if (modelScienceListe==null){
			if (slaveHumans==null)setMilkVarFromFiles();
			modelScienceListe = FXCollections.observableArrayList();
			if (slaveHumans!=null){
				for (SlaveHuman slaveHuman:slaveHumans){
					try {
						if(slaveHuman.getAttrib().getTree()==ThingAttrib.Tree_Science)modelScienceListe.add((SlaveHuman) slaveHuman.clone());
					} catch (CloneNotSupportedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		return modelScienceListe;
	}

	public static ObservableList<SlaveHuman> getSHMagicListe() {
		if (modelMagicListe==null){
			if (slaveHumans==null)setMilkVarFromFiles();
			modelMagicListe = FXCollections.observableArrayList();
			if (slaveHumans!=null){
				for (SlaveHuman slaveHuman:slaveHumans){
					try {
						if(slaveHuman.getAttrib().getTree()==ThingAttrib.Tree_Magic)modelMagicListe.add((SlaveHuman) slaveHuman.clone());
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
	
	public SlaveHuman() {
		super();
		this.setKind(MilkKind.kind_Slave_Human);
	}
	public SlaveHuman(Element milkElement) {
		super();
		this.setKind(MilkKind.kind_Slave_Human);
		this.setValueFromNode(milkElement);
	}
	
	// other object methods
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		SlaveHuman clone = (SlaveHuman) super.clone();
		return clone;
	}
}
