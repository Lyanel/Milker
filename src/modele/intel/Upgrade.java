package modele.intel;

import modele.MilkFile;
import modele.MilkInterface;
import modele.MilkKind;
import modele.carac.Effect;

import java.util.Vector;

import org.w3c.dom.Element;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Upgrade extends Research implements Cloneable {
	
	public static final String noeud = "upgrade";
	public String getNoeud() {return noeud;}

	private static Vector<Upgrade> upgrades;
	public static final String file		= "Upgrade";

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static Vector setMilkVarFromFiles() {
		if (upgrades==null) upgrades = new Vector<Upgrade>();
		else upgrades.removeAllElements();
		Vector<Element> elementlist = new Vector<Element>();
		elementlist = MilkFile.getMilkElementsFromFiles(MilkFile.getXmlFilePath(file)+file, noeud);
		upgrades = getMilkVarList(elementlist);
		Vector<Element> elementlInfos = new Vector<Element>();
		elementlInfos = MilkFile.getMilkElementsFromFiles(MilkInterface.getXmlLangPath()+file, noeud);
		setInfo(upgrades, elementlInfos);
		return upgrades;
	}

	private static void setInfo(Vector<Upgrade> upgrades, Vector<Element> elementlInfos) {
		for (Element elementlInfo: elementlInfos) {
			try {
				Upgrade upgradeInfo = new Upgrade(elementlInfo);
				upgradeInfo.setInfo(elementlInfo);
				for (Upgrade upgrade:upgrades){
					if (upgradeInfo.equals(upgrade)){
						upgrade.setInfo(upgradeInfo.getInfo());
						break;
					}
				}
			} catch (Exception e) {e.printStackTrace();}
		}
	}

	@SuppressWarnings("rawtypes")
	public static ObservableList getListes() {
		if (upgrades==null)setMilkVarFromFiles();
		ObservableList<Upgrade> clone = FXCollections.observableArrayList();
		if (upgrades!=null){
			for (Upgrade upgrade:upgrades){
				try {
					clone.add((Upgrade) upgrade.clone());
				} catch (CloneNotSupportedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return clone;
	}

	@SuppressWarnings("rawtypes")
	public static Vector getMilkVarList(Vector<Element> elementlist) {
		Vector<Upgrade> upgrades = new Vector<Upgrade>();
		for (Element elementMilk: elementlist) {
			try {
				Upgrade upgrade = new Upgrade(elementMilk);
				upgrades.add(upgrade);
			} catch (Exception e) {e.printStackTrace();}
		}
		return upgrades;
	}

	@SuppressWarnings("rawtypes")
	public static Vector getNullMilkVarList(Vector<Element> elementlist) {
		Vector<Upgrade> upgrades = new Vector<Upgrade>();
		for (Element elementMilk: elementlist) {
			try {
				Upgrade upgrade = new Upgrade();
				upgrade.setNullValueFromNode(elementMilk);
				upgrades.add(upgrade);
			} catch (Exception e) {e.printStackTrace();}
		}
		return upgrades;
	}
	
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
	@Override
	public void setNullValueFromNode(Element milkElement) {
		super.setNullValueFromNode(milkElement);
		this.setNullEffect(milkElement);
	}
	
	// field methods
	
	public Effect getEffect() {
		return this.effect;
	}
	public void setEffect(Effect effect) {
		this.effect = effect;
	}
	public void setEffect(Element milkElement) {
		this.effect.setValueFromNode(milkElement);
	}
	public void setNullEffect(Element milkElement) {
		this.effect.setValueFromNode(milkElement);
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