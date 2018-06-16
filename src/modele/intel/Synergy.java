package modele.intel;

import java.util.ArrayList;

import org.w3c.dom.Element;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import modele.MilkFile;
import modele.MilkInterface;
import modele.MilkKind;
import modele.MilkRs;
import modele.carac.Effect;

public class Synergy extends Research implements Cloneable {

	public static final String file	= "Synergy", noeud = "synergy";
	public String getNoeud() {return noeud;}

	private static ArrayList<Synergy> synergys;
	private static ObservableList<Synergy> modelSynergys;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static ArrayList setMilkVarFromFiles() {
		if (synergys==null) synergys = new ArrayList<Synergy>();
		else synergys.clear();
		ArrayList<Element> elementlist = new ArrayList<Element>();
		elementlist = MilkFile.getMilkElementsFromFiles(MilkFile.getXmlFilePath(file)+file, noeud);
		synergys = getMilkVarList(elementlist);
		ArrayList<Element> elementlInfos = new ArrayList<Element>();
		elementlInfos = MilkFile.getMilkElementsFromFiles(MilkInterface.getXmlLangPath()+file, noeud);
		setInfo(synergys, elementlInfos);
		return synergys;
	}

	@SuppressWarnings("rawtypes")
	public static ArrayList getMilkVarList(ArrayList<Element> elementlist) {
		ArrayList<Synergy> synergys = new ArrayList<Synergy>();
		for (Element elementMilk: elementlist) {
			try {
				Synergy synergy = new Synergy(elementMilk);
				synergys.add(synergy);
			} catch (Exception e) {e.printStackTrace();}
		}
		return synergys;
	}
	
	public static ObservableList<Synergy> getSynergyListe() {
		if (modelSynergys==null){
			if (synergys==null)setMilkVarFromFiles();
			modelSynergys = FXCollections.observableArrayList();
			if (synergys!=null){
				for (Synergy synergy:synergys){
					try {
						modelSynergys.add((Synergy) synergy.clone());
					} catch (CloneNotSupportedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		return modelSynergys;
	}

	// Fields
	
	private ArrayList<Effect> effects = null;

	// Constructors
	
	public Synergy() {
		super();
		this.effects = new ArrayList<Effect>();
		this.setKind(MilkKind.kind_Synergy);
	}
	public Synergy(Element milkElement) {
		super();
		this.effects = new ArrayList<Effect>();
		this.setValueFromNode(milkElement);
		this.setKind(MilkKind.kind_Synergy);
	}

	// Set value from Element methods
	
	@Override
	public void setValueFromNode(Element milkElement) {
		super.setValueFromNode(milkElement);
		this.setEffects(milkElement);
		
	}
	public void setEffects(Element milkElement) {
		effects.addAll(Effect.getMilkVarList(milkElement));
	}
	
	// field methods
	
	public ArrayList<Effect> getEffects() {
		return effects;
	}
	public void setEffects(ArrayList<Effect> effects) {
		this.effects = effects;
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
	
	public ArrayList<Effect> getCloneEffects() throws CloneNotSupportedException {
		ArrayList<Effect> clone = new ArrayList<Effect>();
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
