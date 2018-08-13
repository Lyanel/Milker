package modele.thing;

import modele.baseObject.MilkVarList;
import modele.carac.MilkTree;

import java.util.ArrayList;

import org.w3c.dom.Element;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.util.Callback;


public class BuildingList extends MilkVarList {

	private static BuildingList INSTANCE = null;
	private static final String file	= "Building", noeud	= "building";
	public String getFile() {return file;}
	public String getNoeud() {return noeud;}

	public static BuildingList getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new BuildingList();
        }
        return INSTANCE;
    }

	public static Callback<Building, Observable[]> extractor() {
        return (Building p) -> new Observable[]{p.getInfo().getObrservableName(), p.getQuantity().getObrservableQuant(), p.getQuantity().getObrservableActives()};
	}
	
	// Fields

	private ArrayList<Building> buildings;
	private ObservableList<Building> modelListe;
	private ObservableList<Building> modelNeutralListe;
	private ObservableList<Building> modelScienceListe;
	private ObservableList<Building> modelMagicListe;

	// Constructors
	
	private BuildingList() {
		super();
		setMilkVarFromFiles();
	}

	// field methods
	
	private ArrayList<Building> setMilkVarFromFiles() {
		if (buildings==null) buildings = new ArrayList<Building>();
		else buildings.clear();
		buildings = getMilkVarList(getElementStat());
		setInfo(buildings, getElementInfo());
		setIcon(buildings, getElementIcon());
		setScene(buildings, getElementScene());
		return buildings;
	}
	
	public ArrayList<Building> getMilkVarList(ArrayList<Element> elementlist) {
		ArrayList<Building> buildings = new ArrayList<Building>();
		for (Element elementMilk: elementlist) {
			try {
				Building building = new Building(elementMilk);
				buildings.add(building);
			} catch (Exception e) {e.printStackTrace();}
		}
		return buildings;
	}
	
	public void updateInfoFromFiles() {
		if (modelListe==null) getFullListe();
		setInfo(modelListe, getElementInfo());
	}

	public ObservableList<Building> getFullListe() {
		if (modelListe==null) setFullListe();
		return modelListe;
	}

	public ObservableList<Building> getNeutralListe() {
		if (modelNeutralListe==null) setNeutralListe();
		return modelNeutralListe;
	}

	public ObservableList<Building> getScienceListe() {
		if (modelScienceListe==null) setScienceListe();
		return modelScienceListe;
	}

	public ObservableList<Building> getMagicListe() {
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
		if (buildings==null)setMilkVarFromFiles();
		if (buildings!=null){
			for (Building building:buildings){
				if(building.getTree().getTree()==MilkTree.Tree_Neutral)modelNeutralListe.add(new Building(building));
			}
		}
	}

	@Override
	public void setScienceListe() {
		modelScienceListe = FXCollections.observableArrayList(extractor());
		if (buildings==null)setMilkVarFromFiles();
		if (buildings!=null){
			for (Building building:buildings){
				if(building.getTree().getTree()==MilkTree.Tree_Science)modelScienceListe.add(new Building(building));
			}
		}
	}

	@Override
	public void setMagicListe() {
		modelMagicListe = FXCollections.observableArrayList(extractor());
		if (buildings==null)setMilkVarFromFiles();
		if (buildings!=null){
			for (Building building:buildings){
				if(building.getTree().getTree()==MilkTree.Tree_Magic)modelMagicListe.add(new Building(building));
			}
		}
	}
	
	// other method : 
	
	public double getIncome(double buildProdBonus, double buildQualBonus) {
		if (modelListe==null)getFullListe();
		double tIncome = 0;
		for (Building building:modelListe){
			tIncome += building.getIncome(buildProdBonus,buildQualBonus) ;
		}
		return tIncome;
	}	
}