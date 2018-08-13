package modele.intel;

import modele.baseObject.MilkVarList;

import java.util.ArrayList;

import org.w3c.dom.Element;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.util.Callback;


public class AscensionList extends MilkVarList {

	private static AscensionList INSTANCE = null;
	private static final String file	= "Ascension", noeud	= "ascension";
	public String getFile() {return file;}
	public String getNoeud() {return noeud;}

	public static AscensionList getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new AscensionList();
        }
        return INSTANCE;
    }

	public static Callback<Ascension, Observable[]> extractor() {
        return (Ascension p) -> new Observable[]{p.getInfo().getObrservableName()};
	}
	
	// Fields

	private static ArrayList<Ascension> ascensions;
	private static ObservableList<Ascension> modelListe;

	// Constructors
	
	private AscensionList() {
		super();
		setMilkVarFromFiles();
	}

	// field methods
	
	private ArrayList<Ascension> setMilkVarFromFiles() {
		if (ascensions==null) ascensions = new ArrayList<Ascension>();
		else ascensions.clear();
		ascensions = getMilkVarList(getElementStat());
		setInfo(ascensions, getElementInfo());
		return ascensions;
	}
	
	private ArrayList<Ascension> getMilkVarList(ArrayList<Element> elementlist) {
		ArrayList<Ascension> ascensions = new ArrayList<Ascension>();
		for (Element elementMilk: elementlist) {
			try {
				Ascension ascension = new Ascension(elementMilk);
				ascensions.add(ascension);
			} catch (Exception e) {e.printStackTrace();}
		}
		return ascensions;
	}
	
	public void updateInfoFromFiles() {
		if (modelListe==null) setFullListe();
		setInfo(modelListe, getElementInfo());
	}

	public ObservableList<Ascension> getAscensionListe() {
		if (modelListe==null) setFullListe();
		return modelListe;
	}
	
	public void setFullListe() {
		if (ascensions==null)setMilkVarFromFiles();
		modelListe = FXCollections.observableArrayList(extractor());
		if (ascensions!=null){
			for (Ascension ascension:ascensions){
				modelListe.add(new Ascension(ascension));
			}
		}
	}
}