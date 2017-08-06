package modele.intel;

import java.util.Vector;

import org.w3c.dom.Element;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import modele.MilkFile;
import modele.MilkInterface;
import modele.MilkKind;
import modele.MilkRs;
import modele.carac.Effect;

public class Synergy extends Research implements Cloneable {
	
	public static final String noeud = "synergy";
	public String getNoeud() {return noeud;}

	private static Vector<Synergy> synergys;
	public static final String file		= "Synergy";

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static Vector setMilkVarFromFiles() {
		if (synergys==null) synergys = new Vector<Synergy>();
		else synergys.removeAllElements();
		Vector<Element> elementlist = new Vector<Element>();
		elementlist = MilkFile.getMilkElementsFromFiles(MilkFile.getXmlFilePath(file)+file, noeud);
		synergys = getMilkVarList(elementlist);
		Vector<Element> elementlInfos = new Vector<Element>();
		elementlInfos = MilkFile.getMilkElementsFromFiles(MilkInterface.getXmlLangPath()+file, noeud);
		setInfo(synergys, elementlInfos);
		return synergys;
	}

	private static void setInfo(Vector<Synergy> synergys, Vector<Element> elementlInfos) {
		for (Element elementlInfo: elementlInfos) {
			try {
				Synergy synergyInfo = new Synergy(elementlInfo);
				synergyInfo.setInfo(elementlInfo);
				for (Synergy synergy:synergys){
					if (synergyInfo.equals(synergy)){
						synergy.setInfo(synergyInfo.getInfo());
						break;
					}
				}
			} catch (Exception e) {e.printStackTrace();}
		}
	}

	@SuppressWarnings("rawtypes")
	public static ObservableList getListes() {
		if (synergys==null)setMilkVarFromFiles();
		ObservableList<Synergy> clone = FXCollections.observableArrayList();
		if (synergys!=null){
			for (Synergy synergy:synergys){
				try {
					clone.add((Synergy) synergy.clone());
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
		Vector<Synergy> synergys = new Vector<Synergy>();
		for (Element elementMilk: elementlist) {
			try {
				Synergy synergy = new Synergy(elementMilk);
				synergys.add(synergy);
			} catch (Exception e) {e.printStackTrace();}
		}
		return synergys;
	}

	@SuppressWarnings("rawtypes")
	public static Vector getNullMilkVarList(Vector<Element> elementlist) {
		Vector<Synergy> synergys = new Vector<Synergy>();
		for (Element elementMilk: elementlist) {
			try {
				Synergy synergy = new Synergy();
				synergy.setNullValueFromNode(elementMilk);
				synergys.add(synergy);
			} catch (Exception e) {e.printStackTrace();}
		}
		return synergys;
	}

	private Vector<Effect> effects = null;

	// Constructors
	
	public Synergy() {
		super();
		this.effects = new Vector<Effect>();
		this.setKind(MilkKind.kind_Synergy);
	}
	public Synergy(Element milkElement) {
		super();
		this.effects = new Vector<Effect>();
		this.setValueFromNode(milkElement);
		this.setKind(MilkKind.kind_Synergy);
	}

	// Set value from Element methods
	
	@Override
	public void setValueFromNode(Element milkElement) {
		super.setValueFromNode(milkElement);
		this.setEffects(milkElement);
		
	}
	@Override
	public void setNullValueFromNode(Element milkElement) {
		super.setNullValueFromNode(milkElement);
		this.setNullEffects(milkElement);
	}
	
	// field methods
	
	public Vector<Effect> getEffects() {
		return effects;
	}
	public void setEffects(Vector<Effect> effects) {
		this.effects = effects;
	}
	@SuppressWarnings("unchecked")
	public void setEffects(Element milkElement) {
		effects.addAll(Effect.getMilkVarList(milkElement));
	}
	@SuppressWarnings("unchecked")
	public void setNullEffects(Element milkElement) {
		effects.addAll(Effect.getNullMilkVarList(milkElement));
	}
	
	// toString & toXml methods

	@Override
	public String toStringStatChild() {
		String temp = super.toStringStatChild();
		if(effects.size()>0){
			temp += MilkRs.LIGNE_TAB+Effect.noeud+"s : "+MilkRs.LIGNE_BREAK;
			for (Effect effect : effects) {
				temp += MilkRs.LIGNE_TAB+effect.toStringStat();
			}
		}
		return temp;
	}
	@Override
	public String toXmlStatChild() {
		String temp = super.toXmlStatChild();
		if(effects.size()>0){
			temp += "<"+Effect.noeud+"s>"+MilkRs.LIGNE_BREAK;
			for (Effect effect : effects) {
				temp += MilkRs.LIGNE_TAB+effect.toXmlStat();
			}
		temp += "</"+Effect.noeud+"s>"+MilkRs.LIGNE_BREAK;
		}
		return temp;
	}

	// other object methods
	
	public Vector<Effect> getCloneEffects() throws CloneNotSupportedException {
		Vector<Effect> clone = new Vector<Effect>();
		if (this.effects!=null) for (Effect effect:this.effects) clone.add((Effect) effect.clone());
		return clone;
	}

	@Override
	public boolean allZero()  {
		boolean temp = super.allZero();
		if(this.effects!=null && this.effects.size()!=0) temp= false;
		return temp;
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		Synergy clone = (Synergy) super.clone();
		clone.setEffects(getCloneEffects());
		return clone;
	}
}
