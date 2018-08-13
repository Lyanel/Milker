package modele.thing;

import modele.baseObject.MilkVarList;
import modele.carac.MilkTree;

import java.util.ArrayList;

import org.w3c.dom.Element;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.util.Callback;


public class WorkerList extends MilkVarList {

	private static WorkerList INSTANCE = null;
	private static final String file	= "Worker", noeud	= "worker";
	public String getFile() {return file;}
	public String getNoeud() {return noeud;}

	public static WorkerList getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new WorkerList();
        }
        return INSTANCE;
    }

	public static Callback<Worker, Observable[]> extractor() {
        return (Worker p) -> new Observable[]{p.getInfo().getObrservableName(), p.getQuantity().getObrservableQuant(), p.getQuantity().getObrservableActives()};
	}
	
	// Fields

	private ArrayList<Worker> workers;
	private ObservableList<Worker> modelListe;
	private ObservableList<Worker> modelNeutralListe;
	private ObservableList<Worker> modelScienceListe;
	private ObservableList<Worker> modelMagicListe;

	// Constructors
	
	private WorkerList() {
		super();
		setMilkVarFromFiles();
	}

	// field methods
	
	private ArrayList<Worker> setMilkVarFromFiles() {
		if (workers==null) workers = new ArrayList<Worker>();
		else workers.clear();
		workers = getMilkVarList(getElementStat());
		setInfo(workers, getElementInfo());
		setIcon(workers, getElementIcon());
		setScene(workers, getElementScene());
		return workers;
	}
	
	private ArrayList<Worker> getMilkVarList(ArrayList<Element> elementlist) {
		ArrayList<Worker> workers = new ArrayList<Worker>();
		for (Element elementMilk: elementlist) {
			try {
				Worker worker = new Worker(elementMilk);
				workers.add(worker);
			} catch (Exception e) {e.printStackTrace();}
		}
		return workers;
	}
	
	public void updateInfoFromFiles() {
		if (modelListe==null) getFullListe();
		setInfo(modelListe, getElementInfo());
	}

	public ObservableList<Worker> getFullListe() {
		if (modelListe==null) setFullListe();
		return modelListe;
	}

	public ObservableList<Worker> getNeutralListe() {
		if (modelNeutralListe==null) setNeutralListe();
		return modelNeutralListe;
	}

	public ObservableList<Worker> getScienceListe() {
		if (modelScienceListe==null) setScienceListe();
		return modelScienceListe;
	}

	public ObservableList<Worker> getMagicListe() {
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
		if (workers==null)setMilkVarFromFiles();
		if (workers!=null){
			for (Worker worker:workers){
				if(worker.getTree().getTree()==MilkTree.Tree_Neutral)modelNeutralListe.add(new Worker(worker));
			}
		}
	}

	@Override
	public void setScienceListe() {
		modelScienceListe = FXCollections.observableArrayList(extractor());
		if (workers==null)setMilkVarFromFiles();
		if (workers!=null){
			for (Worker worker:workers){
				if(worker.getTree().getTree()==MilkTree.Tree_Science)modelScienceListe.add(new Worker(worker));
			}
		}
	}

	@Override
	public void setMagicListe() {
		modelMagicListe = FXCollections.observableArrayList(extractor());
		if (workers==null)setMilkVarFromFiles();
		if (workers!=null){
			for (Worker worker:workers){
				if(worker.getTree().getTree()==MilkTree.Tree_Magic)modelMagicListe.add(new Worker(worker));
			}
		}
	}
	
	// other method : 
	
	public double getIncome(double toolProdBonus, double toolQualBonus, double cattleProdBonus, double cattleQualBonus, double buildProdBonus, double buildQualBonus) {
		if (modelListe==null)getFullListe();
		return getIncomeFromList(toolProdBonus,toolQualBonus,cattleProdBonus,cattleQualBonus,buildProdBonus,buildQualBonus,modelListe) ;
	}	
}