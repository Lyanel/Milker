package modele.intel;

import modele.baseObject.MilkVarList;

import java.util.ArrayList;

import org.w3c.dom.Element;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.util.Callback;


public class SynergyList extends MilkVarList {

	private static SynergyList INSTANCE = null;
	private static final String file	= "Synergy", noeud	= "synergy";
	public String getFile() {return file;}
	public String getNoeud() {return noeud;}

	public static SynergyList getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new SynergyList();
        }
        return INSTANCE;
    }

	public static Callback<Synergy, Observable[]> extractor() {
        return (Synergy p) -> new Observable[]{p.getInfo().getObrservableName()};
	}
	
	// Fields

	private static ArrayList<Synergy> synergys;
	private static ObservableList<Synergy> modelListe;

	// Constructors
	
	private SynergyList() {
		super();
		setMilkVarFromFiles();
	}

	// field methods
	
	private ArrayList<Synergy> setMilkVarFromFiles() {
		if (synergys==null) synergys = new ArrayList<Synergy>();
		else synergys.clear();
		synergys = getMilkVarList(getElementStat());
		setInfo(synergys, getElementInfo());
		return synergys;
	}
	
	private ArrayList<Synergy> getMilkVarList(ArrayList<Element> elementlist) {
		ArrayList<Synergy> synergys = new ArrayList<Synergy>();
		for (Element elementMilk: elementlist) {
			try {
				Synergy synergy = new Synergy(elementMilk);
				synergys.add(synergy);
			} catch (Exception e) {e.printStackTrace();}
		}
		return synergys;
	}
	
	public void updateInfoFromFiles() {
		if (modelListe==null) setFullListe();
		setInfo(modelListe, getElementInfo());
	}

	public ObservableList<Synergy> getSynergyListe() {
		if (modelListe==null) setFullListe();
		return modelListe;
	}
	
	public void setFullListe() {
		if (synergys==null)setMilkVarFromFiles();
		modelListe = FXCollections.observableArrayList(extractor());
		if (synergys!=null){
			for (Synergy synergy:synergys){
				modelListe.add(new Synergy(synergy));
			}
		}
	}
}