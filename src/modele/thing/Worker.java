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

public class Worker extends Thing implements Cloneable {

	public static final String file	= "Worker", noeud = "worker";
	public String getNoeud() {return noeud;}

	private static Vector<Worker> workers;
	private static ObservableList<Worker> modelListe;
	private static ObservableList<Worker> modelNeutralListe;
	private static ObservableList<Worker> modelScienceListe;
	private static ObservableList<Worker> modelMagicListe;

	@SafeVarargs
	private static ObservableList<Worker> merge(ObservableList<Worker> into, ObservableList<Worker>... lists) {
        final ObservableList<Worker> list = into;
        for (ObservableList<Worker> l : lists) {
            list.addAll(l);
            l.addListener((javafx.collections.ListChangeListener.Change<? extends Worker> c) -> {
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
	
	private static Vector<Worker> setMilkVarFromFiles() {
		if (workers==null) workers = new Vector<Worker>();
		else workers.removeAllElements();
		//Set stats
		Vector<Element> elementlist = new Vector<Element>();
		elementlist = MilkFile.getMilkElementsFromFiles(MilkFile.getXmlFilePath(file)+file, noeud);
		workers = getMilkVarList(elementlist);
		//Set info
		Vector<Element> elementlInfos = new Vector<Element>();
		elementlInfos = MilkFile.getMilkElementsFromFiles(MilkInterface.getXmlLangPath()+file, noeud);
		setInfo(workers, elementlInfos);
		//Set icon
		Vector<Element> elementlIcon = new Vector<Element>();
		elementlIcon = MilkFile.getMilkElementsFromFiles(MilkImage.getXmlIconsPath(file)+file, noeud);
		setIcon(workers, elementlIcon);
		//Set scene
		Vector<Element> elementlScene = new Vector<Element>();
		elementlScene = MilkFile.getMilkElementsFromFiles(MilkImage.getXmlScenesPath(file)+file, noeud);
		setScene(workers, elementlScene);
		
		return workers;
	}
	
	public static Vector<Worker> getMilkVarList(Vector<Element> elementlist) {
		Vector<Worker> workers = new Vector<Worker>();
		for (Element elementMilk: elementlist) {
			try {
				Worker worker = new Worker(elementMilk);
				workers.add(worker);
			} catch (Exception e) {e.printStackTrace();}
		}
		return workers;
	}
	/*
	public static Vector<Worker> getNullMilkVarList(Vector<Element> elementlist) {
		Vector<Worker> workers = new Vector<Worker>();
		for (Element elementMilk: elementlist) {
			try {
				Worker worker = new Worker();
				worker.setNullValueFromNode(elementMilk);
				workers.add(worker);
			} catch (Exception e) {e.printStackTrace();}
		}
		return workers;
	}*/
	
	public static ObservableList<Worker> getFullListe() {
		if (modelListe==null){
			if (workers==null)setMilkVarFromFiles();
			if (modelNeutralListe==null)getNeutralListe();
			if (modelScienceListe==null)getScienceListe();
			if (modelMagicListe==null)getMagicListe();
			modelListe = FXCollections.observableArrayList();
			merge(modelListe, modelNeutralListe, modelScienceListe, modelMagicListe);
		}
		return modelListe;
	}

	public static ObservableList<Worker> getNeutralListe() {
		if (modelNeutralListe==null){
			if (workers==null)setMilkVarFromFiles();
			modelNeutralListe = FXCollections.observableArrayList();
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
			modelScienceListe = FXCollections.observableArrayList();
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
			modelMagicListe = FXCollections.observableArrayList();
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
