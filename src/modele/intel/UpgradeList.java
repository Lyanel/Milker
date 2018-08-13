package modele.intel;

import modele.baseObject.MilkVarList;

import java.util.ArrayList;

import org.w3c.dom.Element;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.util.Callback;


public class UpgradeList extends MilkVarList {

	private static UpgradeList INSTANCE = null;
	private static final String file	= "Upgrade", noeud	= "upgrade";
	public String getFile() {return file;}
	public String getNoeud() {return noeud;}

	public static UpgradeList getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new UpgradeList();
        }
        return INSTANCE;
    }

	public static Callback<Upgrade, Observable[]> extractor() {
        return (Upgrade p) -> new Observable[]{p.getInfo().getObrservableName()};
	}
	
	// Fields

	private static ArrayList<Upgrade> upgrades;
	private static ObservableList<Upgrade> modelListe;

	// Constructors
	
	private UpgradeList() {
		super();
		setMilkVarFromFiles();
	}

	// field methods
	
	private ArrayList<Upgrade> setMilkVarFromFiles() {
		if (upgrades==null) upgrades = new ArrayList<Upgrade>();
		else upgrades.clear();
		upgrades = getMilkVarList(getElementStat());
		setInfo(upgrades, getElementInfo());
		return upgrades;
	}
	
	private ArrayList<Upgrade> getMilkVarList(ArrayList<Element> elementlist) {
		ArrayList<Upgrade> upgrades = new ArrayList<Upgrade>();
		for (Element elementMilk: elementlist) {
			try {
				Upgrade upgrade = new Upgrade(elementMilk);
				upgrades.add(upgrade);
			} catch (Exception e) {e.printStackTrace();}
		}
		return upgrades;
	}
	
	public void updateInfoFromFiles() {
		if (modelListe==null) setFullListe();
		setInfo(modelListe, getElementInfo());
	}

	public ObservableList<Upgrade> getUpgradeListe() {
		if (modelListe==null) setFullListe();
		return modelListe;
	}
	
	public void setFullListe() {
		if (upgrades==null)setMilkVarFromFiles();
		modelListe = FXCollections.observableArrayList(extractor());
		if (upgrades!=null){
			for (Upgrade upgrade:upgrades){
				modelListe.add(new Upgrade(upgrade));
			}
		}
	}
}