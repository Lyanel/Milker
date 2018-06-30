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

public class SlaveHuman extends Slave implements Cloneable {
	
	public static final String file		= "SlaveHuman";

	private static ArrayList<SlaveHuman> slaveHumans;
	private static ObservableList<SlaveHuman> modelListe;
	private static ObservableList<SlaveHuman> modelNeutralListe;
	private static ObservableList<SlaveHuman> modelScienceListe;
	private static ObservableList<SlaveHuman> modelMagicListe;
	
	private static ArrayList<SlaveHuman> setMilkVarFromFiles() {
		if (slaveHumans==null) slaveHumans = new ArrayList<SlaveHuman>();
		else slaveHumans.clear();
		//Set stats
		ArrayList<Element> elementlist = new ArrayList<Element>();
		elementlist = MilkFile.getMilkElementsFromFiles(MilkFile.getXmlFilePath(file)+file, noeud);
		slaveHumans = getMilkVarList(elementlist);
		//Set info
		ArrayList<Element> elementlInfos = new ArrayList<Element>();
		elementlInfos = MilkFile.getMilkElementsFromFiles(MilkInterface.getXmlLangPath()+file, noeud);
		setInfo(slaveHumans, elementlInfos);
		//Set icon
		ArrayList<Element> elementlIcon = new ArrayList<Element>();
		elementlIcon = MilkFile.getMilkElementsFromFiles(MilkImage.getXmlIconsPath(file)+file, noeud);
		setIcon(slaveHumans, elementlIcon);
		//Set scene
		ArrayList<Element> elementlScene = new ArrayList<Element>();
		elementlScene = MilkFile.getMilkElementsFromFiles(MilkImage.getXmlScenesPath(file)+file, noeud);
		setScene(slaveHumans, elementlScene);
		
		return slaveHumans;
	}

	public static ArrayList<SlaveHuman> getMilkVarList(ArrayList<Element> elementlist) {
		ArrayList<SlaveHuman> slaveHumans = new ArrayList<SlaveHuman>();
		for (Element elementMilk: elementlist) {
			try {
				SlaveHuman slaveHuman = new SlaveHuman(elementMilk);
				slaveHumans.add(slaveHuman);
			} catch (Exception e) {e.printStackTrace();}
		}
		return slaveHumans;
	}
	
	public static void updateInfoFromFiles() {
		if (modelListe==null) getSHFullListe();
		ArrayList<Element> elementlInfos = new ArrayList<Element>();
		elementlInfos = MilkFile.getMilkElementsFromFiles(MilkInterface.getXmlLangPath()+file, noeud);
		setInfo(modelListe, elementlInfos);
	}

	public static Callback<SlaveHuman, Observable[]> extractor() {
        return (SlaveHuman p) -> new Observable[]{p.getInfo().getObrservableName(), p.getAttrib().getObrservableQuant(), p.getAttrib().getObrservableActives()};
	}

	public static ObservableList<SlaveHuman> getSHFullListe() {
		if (modelListe==null){
			if (slaveHumans==null)setMilkVarFromFiles();
			if (modelNeutralListe==null)getNeutralListe();
			if (modelScienceListe==null)getScienceListe();
			if (modelMagicListe==null)getMagicListe();
			modelListe = FXCollections.observableArrayList(extractor());
			merge(modelListe, modelNeutralListe, modelScienceListe, modelMagicListe);
		}
		return modelListe;
	}

	public static ObservableList<SlaveHuman> getSHNeutralListe() {
		if (modelNeutralListe==null){
			if (slaveHumans==null)setMilkVarFromFiles();
			modelNeutralListe = FXCollections.observableArrayList(extractor());
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
			modelScienceListe = FXCollections.observableArrayList(extractor());
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
			modelMagicListe = FXCollections.observableArrayList(extractor());
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
