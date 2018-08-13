package modele.thing;

import modele.carac.MilkTree;

import java.util.ArrayList;

import org.w3c.dom.Element;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.util.Callback;


public class SlaveListA extends SlaveList {

	private static SlaveListA INSTANCE = null;
	private static final String file	= "SlaveAnimal";
	public String getFile() {return file;}

	public static SlaveListA getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new SlaveListA();
        }
        return INSTANCE;
    }

	public static Callback<SlaveAnimal, Observable[]> extractorSA() {
        return (SlaveAnimal p) -> new Observable[]{p.getInfo().getObrservableName(), p.getQuantity().getObrservableQuant(), p.getQuantity().getObrservableActives()};
	}
	
	// Fields

	private ArrayList<SlaveAnimal> slaveAnimals;
	private ObservableList<SlaveAnimal> modelListe;
	private ObservableList<SlaveAnimal> modelNeutralListe;
	private ObservableList<SlaveAnimal> modelScienceListe;
	private ObservableList<SlaveAnimal> modelMagicListe;

	// Constructors
	
	private SlaveListA() {
		super();
		setMilkVarFromFiles();
	}

	// field methods
	
	private ArrayList<SlaveAnimal> setMilkVarFromFiles() {
		if (slaveAnimals==null) slaveAnimals = new ArrayList<SlaveAnimal>();
		else slaveAnimals.clear();
		slaveAnimals = getMilkVarList(getElementStat());
		setInfo(slaveAnimals, getElementInfo());
		setIcon(slaveAnimals, getElementIcon());
		setScene(slaveAnimals, getElementScene());
		return slaveAnimals;
	}
	
	private ArrayList<SlaveAnimal> getMilkVarList(ArrayList<Element> elementlist) {
		ArrayList<SlaveAnimal> slaveAnimals = new ArrayList<SlaveAnimal>();
		for (Element elementMilk: elementlist) {
			try {
				SlaveAnimal slaveAnimal = new SlaveAnimal(elementMilk);
				slaveAnimals.add(slaveAnimal);
			} catch (Exception e) {e.printStackTrace();}
		}
		return slaveAnimals;
	}
	
	public void updateInfoFromFiles() {
		if (modelListe==null) getFullListe();
		setInfo(modelListe, getElementInfo());
	}

	public ObservableList<SlaveAnimal> getSAFullListe() {
		if (modelListe==null) setFullListe();
		return modelListe;
	}

	public ObservableList<SlaveAnimal> getSANeutralListe() {
		if (modelNeutralListe==null) setNeutralListe();
		return modelNeutralListe;
	}

	public ObservableList<SlaveAnimal> getSAScienceListe() {
		if (modelScienceListe==null) setScienceListe();
		return modelScienceListe;
	}

	public ObservableList<SlaveAnimal> getSAMagicListe() {
		if (modelMagicListe==null) setMagicListe();
		return modelMagicListe;
	}

	@Override
	public void setFullListe() {
		if (modelNeutralListe==null)getNeutralListe();
		if (modelScienceListe==null)getScienceListe();
		if (modelMagicListe==null)getMagicListe();
		modelListe = FXCollections.observableArrayList(extractorSA());
		merge(modelListe, modelNeutralListe, modelScienceListe, modelMagicListe);
	}

	@Override
	public void setNeutralListe() {
		modelNeutralListe = FXCollections.observableArrayList(extractorSA());
		if (slaveAnimals==null)setMilkVarFromFiles();
		if (slaveAnimals!=null){
			for (SlaveAnimal slaveAnimal:slaveAnimals){
				if(slaveAnimal.getTree().getTree()==MilkTree.Tree_Neutral)modelNeutralListe.add(new SlaveAnimal(slaveAnimal));
			}
		}
	}

	@Override
	public void setScienceListe() {
		modelScienceListe = FXCollections.observableArrayList(extractorSA());
		if (slaveAnimals==null)setMilkVarFromFiles();
		if (slaveAnimals!=null){
			for (SlaveAnimal slaveAnimal:slaveAnimals){
				if(slaveAnimal.getTree().getTree()==MilkTree.Tree_Science)modelScienceListe.add(new SlaveAnimal(slaveAnimal));
			}
		}
	}

	@Override
	public void setMagicListe() {
		modelMagicListe = FXCollections.observableArrayList(extractorSA());
		if (slaveAnimals==null)setMilkVarFromFiles();
		if (slaveAnimals!=null){
			for (SlaveAnimal slaveAnimal:slaveAnimals){
				if(slaveAnimal.getTree().getTree()==MilkTree.Tree_Magic)modelMagicListe.add(new SlaveAnimal(slaveAnimal));
			}
		}
	}
}