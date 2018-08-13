package modele.thing;

import modele.baseObject.MilkKind;
import modele.carac.Sacrifice;

import org.w3c.dom.Element;


public class Slave extends LivingBeing {
	public String getNoeud() {return SlaveList.getInstance().getNoeud();}
	/*
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
			merge(modelNeutralListe, SlaveHuman.getSHNeutralListe(), SlaveWorker.getSWNeutralListe(), SlaveAnimal.getSANeutralListe());
		}
		return modelNeutralListe;
	}
	
	public static ObservableList<Slave> getScienceListe() {
		if (modelScienceListe==null){
			modelScienceListe = FXCollections.observableArrayList(extractorSlave());
			merge(modelScienceListe, SlaveHuman.getSHScienceListe(), SlaveWorker.getSWScienceListe(), SlaveAnimal.getSAScienceListe());
		}
		return modelScienceListe;
	}
	
	public static ObservableList<Slave> getMagicListe() {
		if (modelMagicListe==null){
			modelMagicListe = FXCollections.observableArrayList(extractorSlave());
			merge(modelMagicListe, SlaveHuman.getSHMagicListe(), SlaveWorker.getSWMagicListe(), SlaveAnimal.getSAMagicListe());
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
	}*/

	// Fields
	
	private Sacrifice sacrifice;
	
	// Constructors
	
	public Slave() {
		super();
		sacrifice = new Sacrifice();
		this.setKind(MilkKind.Slaves);
		this.setProductivity(1);
	}
	public Slave(Element milkElement) {
		super();
		this.sacrifice = new Sacrifice();
		this.setKind(MilkKind.Slaves);
		this.setProductivity(1);
		this.setValueFromNode(milkElement);
	}
	public Slave(Slave original) {
		super(original);
		this.sacrifice = new Sacrifice(original.getSacrifice());
	}

	// Set value from Element methods
	
	@Override
	public void setValueFromNode(Element milkElement) {
		super.setValueFromNode(milkElement);
		this.setSacrifice(milkElement);
	}
	public void setSacrifice(Element milkElement) {
		this.sacrifice.setValueFromNode(milkElement);;
	}
	
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
	
}
