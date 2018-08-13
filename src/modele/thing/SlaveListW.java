package modele.thing;

import modele.carac.MilkTree;

import java.util.ArrayList;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.util.Callback;


public class SlaveListW extends SlaveList {

	private static SlaveListW INSTANCE = null;
	private static final String file	= "SlaveWorker";
	public String getFile() {return file;}

	public static SlaveListW getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new SlaveListW();
        }
        return INSTANCE;
    }

	public static Callback<SlaveWorker, Observable[]> extractorSW() {
        return (SlaveWorker p) -> new Observable[]{p.getInfo().getObrservableName(), p.getQuantity().getObrservableQuant(), p.getQuantity().getObrservableActives()};
	}
	
	// Fields

	private ArrayList<SlaveWorker> slaveWorkers;
	private ObservableList<SlaveWorker> modelListe;
	private ObservableList<SlaveWorker> modelNeutralListe;
	private ObservableList<SlaveWorker> modelScienceListe;
	private ObservableList<SlaveWorker> modelMagicListe;

	// Constructors
	
	private SlaveListW() {
		super();
		setMilkVarFromFiles();
	}

	// field methods
	
	private ArrayList<SlaveWorker> setMilkVarFromFiles() {
		if (slaveWorkers==null) slaveWorkers = new ArrayList<SlaveWorker>();
		else slaveWorkers.clear();
		/*slaveWorkers = getMilkVarList(getElementStat());
		setInfo(slaveWorkers, getElementInfo());
		setIcon(slaveWorkers, getElementIcon());
		setScene(slaveWorkers, getElementScene());*/
		ObservableList<Worker> workers = WorkerList.getInstance().getFullListe();
		for (Worker worker: workers) {
			try {
				SlaveWorker slaveWorker = new SlaveWorker(worker);
				slaveWorkers.add(slaveWorker);
			} catch (Exception e) {e.printStackTrace();}
		}
		
		return slaveWorkers;
	}
	/*
	private ArrayList<SlaveWorker> getMilkVarList(ArrayList<Element> elementlist) {
		ArrayList<SlaveWorker> slaveWorkers = new ArrayList<SlaveWorker>();
		for (Element elementMilk: elementlist) {
			try {
				SlaveWorker slaveWorker = new SlaveWorker(elementMilk);
				slaveWorkers.add(slaveWorker);
			} catch (Exception e) {e.printStackTrace();}
		}
		return slaveWorkers;
	}*/
	
	public void updateInfoFromFiles() {
		if (modelListe==null) getFullListe();
		setInfo(modelListe, getElementInfo());
	}

	public ObservableList<SlaveWorker> getSWFullListe() {
		if (modelListe==null) setFullListe();
		return modelListe;
	}

	public ObservableList<SlaveWorker> getSWNeutralListe() {
		if (modelNeutralListe==null) setNeutralListe();
		return modelNeutralListe;
	}

	public ObservableList<SlaveWorker> getSWScienceListe() {
		if (modelScienceListe==null) setScienceListe();
		return modelScienceListe;
	}

	public ObservableList<SlaveWorker> getSWMagicListe() {
		if (modelMagicListe==null) setMagicListe();
		return modelMagicListe;
	}

	@Override
	public void setFullListe() {
		if (modelNeutralListe==null)getNeutralListe();
		if (modelScienceListe==null)getScienceListe();
		if (modelMagicListe==null)getMagicListe();
		modelListe = FXCollections.observableArrayList(extractorSW());
		merge(modelListe, modelNeutralListe, modelScienceListe, modelMagicListe);
	}

	@Override
	public void setNeutralListe() {
		modelNeutralListe = FXCollections.observableArrayList(extractorSW());
		if (slaveWorkers==null)setMilkVarFromFiles();
		if (slaveWorkers!=null){
			for (SlaveWorker slaveWorker:slaveWorkers){
				if(slaveWorker.getTree().getTree()==MilkTree.Tree_Neutral)modelNeutralListe.add(new SlaveWorker(slaveWorker));
			}
		}
	}

	@Override
	public void setScienceListe() {
		modelScienceListe = FXCollections.observableArrayList(extractorSW());
		if (slaveWorkers==null)setMilkVarFromFiles();
		if (slaveWorkers!=null){
			for (SlaveWorker slaveWorker:slaveWorkers){
				if(slaveWorker.getTree().getTree()==MilkTree.Tree_Science)modelScienceListe.add(new SlaveWorker(slaveWorker));
			}
		}
	}

	@Override
	public void setMagicListe() {
		modelMagicListe = FXCollections.observableArrayList(extractorSW());
		if (slaveWorkers==null)setMilkVarFromFiles();
		if (slaveWorkers!=null){
			for (SlaveWorker slaveWorker:slaveWorkers){
				if(slaveWorker.getTree().getTree()==MilkTree.Tree_Magic)modelMagicListe.add(new SlaveWorker(slaveWorker));
			}
		}
	}
}