package modele.thing;

import modele.baseObject.MilkKind;
import modele.carac.Sacrifice;

import org.w3c.dom.Element;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.util.Callback;


public class Slave extends LivingBeing implements Cloneable {
	
	public static String noeud = "slave";
	public String getNoeud() {return noeud;}
	
	private static ObservableList<Slave> modelListe;
	private static ObservableList<Slave> modelNeutralListe;
	private static ObservableList<Slave> modelScienceListe;
	private static ObservableList<Slave> modelMagicListe;
	
	public static Callback<Slave, Observable[]> extractorSlave() {
        return (Slave p) -> new Observable[]{p.getInfo().getObrservableName()};
	}
	
	public static ObservableList<Slave> getFullListe() {
		if (modelListe==null){
			if (modelNeutralListe==null)getNeutralListe();
			if (modelScienceListe==null)getScienceListe();
			if (modelMagicListe==null)getMagicListe();
			modelListe = FXCollections.observableArrayList(extractorSlave());
			merge(modelListe, modelNeutralListe, modelScienceListe, modelMagicListe);
		}
		return modelListe;
	}
	
	public static ObservableList<Slave> getNeutralListe() {
		if (modelNeutralListe==null){
			modelNeutralListe = FXCollections.observableArrayList(extractorSlave());
			merge(modelNeutralListe, SlaveAnimal.getSANeutralListe(), SlaveHuman.getSHNeutralListe()/*, SlaveWorker.getSWNeutralListe()*/);
		}
		return modelNeutralListe;
	}
	
	public static ObservableList<Slave> getScienceListe() {
		if (modelScienceListe==null){
			modelScienceListe = FXCollections.observableArrayList(extractorSlave());
			merge(modelScienceListe, SlaveAnimal.getSAScienceListe(), SlaveHuman.getSHScienceListe()/*, SlaveWorker.getSWScienceListe()*/);
		}
		return modelScienceListe;
	}
	
	public static ObservableList<Slave> getMagicListe() {
		if (modelMagicListe==null){
			modelMagicListe = FXCollections.observableArrayList(extractorSlave());
			merge(modelMagicListe, SlaveAnimal.getSAMagicListe(), SlaveHuman.getSHMagicListe()/*, SlaveWorker.getSWMagicListe()*/);
		}
		return modelMagicListe;
	}

	public static double getIncomeFromList(double toolProdBonus, double toolQualBonus, double cattleProdBonus,
			double cattleQualBonus, double buildProdBonus, double buildQualBonus) {
		if (modelListe==null)getFullListe();
		double tIncome = 0;
		for (Slave thing:modelListe){
			tIncome += thing.getIncome(toolProdBonus,toolQualBonus,cattleProdBonus,cattleQualBonus,buildProdBonus,buildQualBonus) ;
		}
		return tIncome;
	}

	// Fields
	
	private Sacrifice sacrifice;
	
	// Constructors
	
	public Slave() {
		super();
		sacrifice = new Sacrifice();
		this.setKind(MilkKind.kind_Slaves);
		this.setProductivity(1);
	}
	public Slave(Element milkElement) {
		super();
		this.sacrifice = new Sacrifice();
		this.setKind(MilkKind.kind_Slaves);
		this.setProductivity(1);
		this.setValueFromNode(milkElement);
	}

	// Set value from Element methods
	
	@Override
	public void setValueFromNode(Element milkElement) {
		super.setValueFromNode(milkElement);
		this.setSacrifice(milkElement);
	}
	public void setSacrifice(Element milkElement) {
		this.sacrifice.setValueFromNode(milkElement);;
	}/*
	@Override
	public void setNullValueFromNode(Element milkElement) {
		super.setNullValueFromNode(milkElement);
		this.setNullSacrifice(milkElement);
	}
	public void setNullSacrifice(Element milkElement) {
		this.sacrifice.setValueFromNode(milkElement);
	}*/
	
	// field methods
	
	public Sacrifice getSacrifice() {
		return this.sacrifice;
	}
	public void setSacrifice(Sacrifice sacrifice) {
		this.sacrifice = sacrifice;
	}
		
	// toString & toXml methods
	
	@Override
	public String toStringStatChild() {
		String temp = super.toStringStatChild();
		if (this.sacrifice != null) temp = this.sacrifice.toStringStat();
		return temp;
	}
	@Override
	public String toXmlStatChild() {
		String temp = super.toXmlStatChild();
		if (this.sacrifice != null) temp = this.sacrifice.toXmlStat();
		return temp;
	}
	
	// other object methods

	@Override
	public boolean allZero()  {
		boolean temp = super.allZero();
		if(this.sacrifice!=null && !this.sacrifice.allZero()) temp= false;
		return temp;
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		Slave clone = (Slave) super.clone();
		if (this.sacrifice!=null) clone.setSacrifice((Sacrifice) this.sacrifice.clone());
		return clone;
	}
}
