package modele.intel;

import modele.baseObject.MilkVarList;

import java.util.ArrayList;

import org.w3c.dom.Element;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.util.Callback;


public class ResearchList extends MilkVarList {

	private static ResearchList INSTANCE = null;
	private static final String file	= "Research", noeud	= "research";
	public String getFile() {return file;}
	public String getNoeud() {return noeud;}

	public static ResearchList getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ResearchList();
        }
        return INSTANCE;
    }

	public static Callback<Research, Observable[]> extractor() {
        return (Research p) -> new Observable[]{p.getInfo().getObrservableName()};
	}
	
	// Fields

	private static ArrayList<Research> researchs;
	private static ObservableList<Research> modelListe;

	// Constructors
	
	private ResearchList() {
		super();
		setMilkVarFromFiles();
	}

	// field methods
	
	private ArrayList<Research> setMilkVarFromFiles() {
		if (researchs==null) researchs = new ArrayList<Research>();
		else researchs.clear();
		researchs = getMilkVarList(getElementStat());
		setInfo(researchs, getElementInfo());
		return researchs;
	}
	
	private ArrayList<Research> getMilkVarList(ArrayList<Element> elementlist) {
		ArrayList<Research> researchs = new ArrayList<Research>();
		for (Element elementMilk: elementlist) {
			try {
				Research research = new Research(elementMilk);
				researchs.add(research);
			} catch (Exception e) {e.printStackTrace();}
		}
		return researchs;
	}
	
	public void updateInfoFromFiles() {
		if (modelListe==null) setFullListe();
		setInfo(modelListe, getElementInfo());
	}

	public ObservableList<Research> getResearchListe() {
		if (modelListe==null) setFullListe();
		return modelListe;
	}
	
	public void setFullListe() {
		if (researchs==null)setMilkVarFromFiles();
		modelListe = FXCollections.observableArrayList(extractor());
		if (researchs!=null){
			for (Research research:researchs){
				modelListe.add(new Research(research));
			}
		}
	}
}