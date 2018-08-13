package modele.thing;

import modele.baseObject.MilkVarList;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.util.Callback;


public class SlaveList extends MilkVarList {

	private static SlaveList INSTANCE = null;
	private static final String noeud	= "slave";
	public String getNoeud() {return noeud;}

	public static SlaveList getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new SlaveList();
        }
        return INSTANCE;
    }

	public static Callback<Slave, Observable[]> extractor() {
        return (Slave p) -> new Observable[]{p.getInfo().getObrservableName(), p.getQuantity().getObrservableQuant(), p.getQuantity().getObrservableActives()};
	}
	
	// Fields

	private ObservableList<Slave> modelListe;
	private ObservableList<Slave> modelNeutralListe;
	private ObservableList<Slave> modelScienceListe;
	private ObservableList<Slave> modelMagicListe;

	// Constructors
	
	protected SlaveList() {
		super();
	}

	// field methods
	
	public ObservableList<Slave> getFullListe() {
		if (modelListe==null) setFullListe();
		return modelListe;
	}

	public ObservableList<Slave> getNeutralListe() {
		if (modelNeutralListe==null) setNeutralListe();
		return modelNeutralListe;
	}

	public ObservableList<Slave> getScienceListe() {
		if (modelScienceListe==null) setScienceListe();
		return modelScienceListe;
	}

	public ObservableList<Slave> getMagicListe() {
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
		merge(modelNeutralListe, SlaveListH.getInstance().getSHNeutralListe(), SlaveListW.getInstance().getSWNeutralListe(), SlaveListA.getInstance().getSANeutralListe());
	}

	@Override
	public void setScienceListe() {
		modelScienceListe = FXCollections.observableArrayList(extractor());
		merge(modelScienceListe, SlaveListH.getInstance().getSHScienceListe(), SlaveListW.getInstance().getSWScienceListe(), SlaveListA.getInstance().getSAScienceListe());
	}

	@Override
	public void setMagicListe() {
		modelMagicListe = FXCollections.observableArrayList(extractor());
		merge(modelMagicListe, SlaveListH.getInstance().getSHMagicListe(), SlaveListW.getInstance().getSWMagicListe(), SlaveListA.getInstance().getSAMagicListe());
	}
	
	// other method : 
	
	public double getIncome(double toolProdBonus, double toolQualBonus, double cattleProdBonus, double cattleQualBonus, double buildProdBonus, double buildQualBonus) {
		if (modelListe==null)getFullListe();
		return getIncomeFromList(toolProdBonus,toolQualBonus,cattleProdBonus,cattleQualBonus,buildProdBonus,buildQualBonus,modelListe) ;
	}	

	@Override
	public void resetListes() {
		SlaveListW.getInstance().setNeutralListe();
		SlaveListW.getInstance().setScienceListe();
		SlaveListW.getInstance().setMagicListe();
		SlaveListW.getInstance().setFullListe();

		SlaveListH.getInstance().setNeutralListe();
		SlaveListH.getInstance().setScienceListe();
		SlaveListH.getInstance().setMagicListe();
		SlaveListH.getInstance().setFullListe();

		SlaveListA.getInstance().setNeutralListe();
		SlaveListA.getInstance().setScienceListe();
		SlaveListA.getInstance().setMagicListe();
		SlaveListA.getInstance().setFullListe();

		this.setNeutralListe();
		this.setScienceListe();
		this.setMagicListe();
		this.setFullListe();
	}
}