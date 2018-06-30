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

public class Worker extends LivingBeing implements Cloneable {

	public static final String file	= "Worker", noeud = "worker";
	public String getNoeud() {return noeud;}

	private static ArrayList<Worker> workers;
	private static ObservableList<Worker> modelListe;
	private static ObservableList<Worker> modelNeutralListe;
	private static ObservableList<Worker> modelScienceListe;
	private static ObservableList<Worker> modelMagicListe;
	
	private static ArrayList<Worker> setMilkVarFromFiles() {
		if (workers==null) workers = new ArrayList<Worker>();
		else workers.clear();
		//Set stats
		ArrayList<Element> elementlist = new ArrayList<Element>();
		elementlist = MilkFile.getMilkElementsFromFiles(MilkFile.getXmlFilePath(file)+file, noeud);
		workers = getMilkVarList(elementlist);
		//Set info
		ArrayList<Element> elementlInfos = new ArrayList<Element>();
		elementlInfos = MilkFile.getMilkElementsFromFiles(MilkInterface.getXmlLangPath()+file, noeud);
		setInfo(workers, elementlInfos);
		//Set icon
		ArrayList<Element> elementlIcon = new ArrayList<Element>();
		elementlIcon = MilkFile.getMilkElementsFromFiles(MilkImage.getXmlIconsPath(file)+file, noeud);
		setIcon(workers, elementlIcon);
		//Set scene
		ArrayList<Element> elementlScene = new ArrayList<Element>();
		elementlScene = MilkFile.getMilkElementsFromFiles(MilkImage.getXmlScenesPath(file)+file, noeud);
		setScene(workers, elementlScene);
		
		return workers;
	}
	
	public static ArrayList<Worker> getMilkVarList(ArrayList<Element> elementlist) {
		ArrayList<Worker> workers = new ArrayList<Worker>();
		for (Element elementMilk: elementlist) {
			try {
				Worker worker = new Worker(elementMilk);
				workers.add(worker);
			} catch (Exception e) {e.printStackTrace();}
		}
		return workers;
	}
	
	public static void updateInfoFromFiles() {
		if (modelListe==null) getFullListe();
		ArrayList<Element> elementlInfos = new ArrayList<Element>();
		elementlInfos = MilkFile.getMilkElementsFromFiles(MilkInterface.getXmlLangPath()+file, noeud);
		setInfo(modelListe, elementlInfos);
	}

	public static Callback<Worker, Observable[]> extractor() {
        return (Worker p) -> new Observable[]{p.getInfo().getObrservableName(), p.getAttrib().getObrservableQuant(), p.getAttrib().getObrservableActives()};
	}
	
	public static ObservableList<Worker> getFullListe() {
		if (modelListe==null){
			if (workers==null)setMilkVarFromFiles();
			if (modelNeutralListe==null)getNeutralListe();
			if (modelScienceListe==null)getScienceListe();
			if (modelMagicListe==null)getMagicListe();
			modelListe = FXCollections.observableArrayList(extractor());
			merge(modelListe, modelNeutralListe, modelScienceListe, modelMagicListe);
		}
		return modelListe;
	}

	public static ObservableList<Worker> getNeutralListe() {
		if (modelNeutralListe==null){
			if (workers==null)setMilkVarFromFiles();
			modelNeutralListe = FXCollections.observableArrayList(extractor());
			if (workers!=null){
				for (Worker worker:workers){
					try {
						if(worker.getAttrib().getTree()==ThingAttrib.Tree_Neutral)modelNeutralListe.add((Worker) worker.clone());
					} catch (CloneNotSupportedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		return modelNeutralListe;
	}

	public static ObservableList<Worker> getScienceListe() {
		if (modelScienceListe==null){
			if (workers==null)setMilkVarFromFiles();
			modelScienceListe = FXCollections.observableArrayList(extractor());
			if (workers!=null){
				for (Worker worker:workers){
					try {
						if(worker.getAttrib().getTree()==ThingAttrib.Tree_Science)modelScienceListe.add((Worker) worker.clone());
					} catch (CloneNotSupportedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		return modelScienceListe;
	}

	public static ObservableList<Worker> getMagicListe() {
		if (modelMagicListe==null){
			if (workers==null)setMilkVarFromFiles();
			modelMagicListe = FXCollections.observableArrayList(extractor());
			if (workers!=null){
				for (Worker worker:workers){
					try {
						if(worker.getAttrib().getTree()==ThingAttrib.Tree_Magic)modelMagicListe.add((Worker) worker.clone());
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
		if (modelListe==null)getFullListe();
		double tIncome = 0;
		for (Worker thing:modelListe){
			tIncome += thing.getIncome(toolProdBonus,toolQualBonus,cattleProdBonus,cattleQualBonus,buildProdBonus,buildQualBonus) ;
		}
		return tIncome;
	}
	
	// Constructors
	
	public Worker() {
		super();
		this.setKind(MilkKind.kind_Ascension);
		this.setSellPrice((float)-40);
	}
	public Worker(Element milkElement) {
		super();
		this.setKind(MilkKind.kind_Worker);
		this.setSellPrice((float)-40);
		this.setValueFromNode(milkElement);
	}
	
	// other object methods
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		Worker clone = (Worker) super.clone();
		return clone;
	}
}
