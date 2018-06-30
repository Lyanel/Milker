package modele.intel;

import modele.baseObject.MilkFile;
import modele.baseObject.MilkInterface;
import modele.baseObject.MilkKind;
import modele.carac.Effect;

import java.util.ArrayList;

import org.w3c.dom.Element;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.util.Callback;

public class Upgrade extends Research implements Cloneable {

	public static final String file="Upgrade", noeud="upgrade";
	public String getNoeud() {return noeud;}

	private static ArrayList<Upgrade> upgrades;
	private static ObservableList<Upgrade> modelUpgrades;
	

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static ArrayList setMilkVarFromFiles() {
		if (upgrades==null) upgrades = new ArrayList<Upgrade>();
		else upgrades.clear();
		ArrayList<Element> elementlist = new ArrayList<Element>();
		elementlist = MilkFile.getMilkElementsFromFiles(MilkFile.getXmlFilePath(file)+file, noeud);
		upgrades = getMilkVarList(elementlist);
		ArrayList<Element> elementlInfos = new ArrayList<Element>();
		elementlInfos = MilkFile.getMilkElementsFromFiles(MilkInterface.getXmlLangPath()+file, noeud);
		setInfo(upgrades, elementlInfos);
		return upgrades;
	}

	@SuppressWarnings("rawtypes")
	public static ArrayList getMilkVarList(ArrayList<Element> elementlist) {
		ArrayList<Upgrade> upgrades = new ArrayList<Upgrade>();
		for (Element elementMilk: elementlist) {
			try {
				Upgrade upgrade = new Upgrade(elementMilk);
				upgrades.add(upgrade);
			} catch (Exception e) {e.printStackTrace();}
		}
		return upgrades;
	}

	public static ObservableList<Upgrade> getUpgradeListe() {
		if (modelUpgrades==null){
			if (upgrades==null)setMilkVarFromFiles();
			modelUpgrades = FXCollections.observableArrayList(extractorA());
			if (upgrades!=null){
				for (Upgrade upgrade:upgrades){
					try {
						modelUpgrades.add((Upgrade) upgrade.clone());
					} catch (CloneNotSupportedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		return modelUpgrades;
	}
	
	public static void updateInfoFromFiles() {
		if (modelUpgrades==null) getResearchListe();
		ArrayList<Element> elementlInfos = new ArrayList<Element>();
		elementlInfos = MilkFile.getMilkElementsFromFiles(MilkInterface.getXmlLangPath()+file, noeud);
		setInfo(modelUpgrades, elementlInfos);
	}

	public static Callback<Upgrade, Observable[]> extractorA() {
        return (Upgrade p) -> new Observable[]{p.getInfo().getObrservableName()};
	}

	// Fields
	
	private Effect effect;

	// Constructors
	
	public Upgrade() {
		super();
		effect = new Effect();
		this.setKind(MilkKind.kind_Upgrade);
	}
	public Upgrade(Element milkElement) {
		super();
		this.effect = new Effect();
		this.setKind(MilkKind.kind_Upgrade);
		this.setValueFromNode(milkElement);
	}

	// Set value from Element methods
	
	@Override
	public void setValueFromNode(Element milkElement) {
		super.setValueFromNode(milkElement);
		this.setEffect(milkElement);
	}
	public void setEffect(Element milkElement) {
		this.effect.setValueFromNode(milkElement);
	}
/*	@Override
	public void setNullValueFromNode(Element milkElement) {
		super.setNullValueFromNode(milkElement);
		this.setNullEffect(milkElement);
	}
	public void setNullEffect(Element milkElement) {
		this.effect.setValueFromNode(milkElement);
	}*/
	
	// field methods
	
	public Effect getEffect() {
		return this.effect;
	}
	public void setEffect(Effect effect) {
		this.effect = effect;
	}
		
	// toString & toXml methods
	
	@Override
	public String toStringStatChild() {
		String temp = super.toStringStatChild();
		if (this.effect != null) temp = this.effect.toStringStat();
		return temp;
	}
	@Override
	public String toXmlStatChild() {
		String temp = super.toXmlStatChild();
		if (this.effect != null) temp = this.effect.toXmlStat();
		return temp;
	}
	
	// other object methods

	@Override
	public boolean allZero()  {
		boolean temp = super.allZero();
		if(this.effect!=null && !this.effect.allZero()) temp= false;
		return temp;
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		Upgrade clone = (Upgrade) super.clone();
		if (this.effect!=null) clone.setEffect((Effect) this.effect.clone());
		return clone;
	}
}