package modele.thing;

import modele.carac.MilkTree;

import java.util.ArrayList;

import org.w3c.dom.Element;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.util.Callback;


public class SlaveListH extends SlaveList {

	private static SlaveListH INSTANCE = null;
	private static final String file	= "SlaveHuman";
	public String getFile() {return file;}

	public static SlaveListH getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new SlaveListH();
        }
        return INSTANCE;
    }

	public static Callback<SlaveHuman, Observable[]> extractorSH() {
        return (SlaveHuman p) -> new Observable[]{p.getInfo().getObrservableName(), p.getQuantity().getObrservableQuant(), p.getQuantity().getObrservableActives()};
	}
	
	// Fields

	private ArrayList<SlaveHuman> slaveHumans;
	private ObservableList<SlaveHuman> modelListe;
	private ObservableList<SlaveHuman> modelNeutralListe;
	private ObservableList<SlaveHuman> modelScienceListe;
	private ObservableList<SlaveHuman> modelMagicListe;

	// Constructors
	
	private SlaveListH() {
		super();
		setMilkVarFromFiles();
	}

	// field methods
	
	private ArrayList<SlaveHuman> setMilkVarFromFiles() {
		if (slaveHumans==null) slaveHumans = new ArrayList<SlaveHuman>();
		else slaveHumans.clear();
		slaveHumans = getMilkVarList(getElementStat());
		setInfo(slaveHumans, getElementInfo());
		setIcon(slaveHumans, getElementIcon());
		setScene(slaveHumans, getElementScene());
		return slaveHumans;
	}
	
	private ArrayList<SlaveHuman> getMilkVarList(ArrayList<Element> elementlist) {
		ArrayList<SlaveHuman> slaveHumans = new ArrayList<SlaveHuman>();
		for (Element elementMilk: elementlist) {
			try {
				SlaveHuman slaveHuman = new SlaveHuman(elementMilk);
				slaveHumans.add(slaveHuman);
			} catch (Exception e) {e.printStackTrace();}
		}
		return slaveHumans;
	}
	
	public void updateInfoFromFiles() {
		if (modelListe==null) getFullListe();
		setInfo(modelListe, getElementInfo());
	}

	public ObservableList<SlaveHuman> getSHFullListe() {
		if (modelListe==null) setFullListe();
		return modelListe;
	}

	public ObservableList<SlaveHuman> getSHNeutralListe() {
		if (modelNeutralListe==null) setNeutralListe();
		return modelNeutralListe;
	}

	public ObservableList<SlaveHuman> getSHScienceListe() {
		if (modelScienceListe==null) setScienceListe();
		return modelScienceListe;
	}

	public ObservableList<SlaveHuman> getSHMagicListe() {
		if (modelMagicListe==null) setMagicListe();
		return modelMagicListe;
	}

	@Override
	public void setFullListe() {
		if (modelNeutralListe==null)getNeutralListe();
		if (modelScienceListe==null)getScienceListe();
		if (modelMagicListe==null)getMagicListe();
		modelListe = FXCollections.observableArrayList(extractorSH());
		merge(modelListe, modelNeutralListe, modelScienceListe, modelMagicListe);
	}

	@Override
	public void setNeutralListe() {
		modelNeutralListe = FXCollections.observableArrayList(extractorSH());
		if (slaveHumans==null)setMilkVarFromFiles();
		if (slaveHumans!=null){
			for (SlaveHuman slaveHuman:slaveHumans){
				if(slaveHuman.getTree().getTree()==MilkTree.Tree_Neutral)modelNeutralListe.add(new SlaveHuman(slaveHuman));
			}
		}
	}

	@Override
	public void setScienceListe() {
		modelScienceListe = FXCollections.observableArrayList(extractorSH());
		if (slaveHumans==null)setMilkVarFromFiles();
		if (slaveHumans!=null){
			for (SlaveHuman slaveHuman:slaveHumans){
				if(slaveHuman.getTree().getTree()==MilkTree.Tree_Science)modelScienceListe.add(new SlaveHuman(slaveHuman));
			}
		}
	}

	@Override
	public void setMagicListe() {
		modelMagicListe = FXCollections.observableArrayList(extractorSH());
		if (slaveHumans==null)setMilkVarFromFiles();
		if (slaveHumans!=null){
			for (SlaveHuman slaveHuman:slaveHumans){
				if(slaveHuman.getTree().getTree()==MilkTree.Tree_Magic)modelMagicListe.add(new SlaveHuman(slaveHuman));
			}
		}
	}
}