package modele.intel;

import modele.MilkRs;
import modele.baseObject.MilkKind;
import modele.carac.Effect;

import java.util.ArrayList;

import org.w3c.dom.Element;

public class Upgrade extends Research {
	public String getNoeud() {return UpgradeList.getInstance().getNoeud();}

/*	public static final String file="Upgrade", noeud="upgrade";
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
	}*/

	// Fields

	private ArrayList<Effect> effects = null;

	// Constructors
	
	public Upgrade() {
		super();
		this.effects = new ArrayList<Effect>();
		this.setKind(MilkKind.Upgrade);
	}
	public Upgrade(Element milkElement) {
		super();
		this.effects = new ArrayList<Effect>();
		this.setKind(MilkKind.Upgrade);
		this.setValueFromNode(milkElement);
	}
	public Upgrade(Upgrade original) {
		super(original);
		this.setDeepEffects(original.getEffects());
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
	public void setDeepEffects(ArrayList<Effect> original) {
		this.effects = new ArrayList<Effect>();
		for (Effect effect:original) this.addEffect( new Effect (effect));
	}
	public void setEffects(ArrayList<Effect> effects) {
		this.effects = effects;
	}
	public void addEffect(Effect effect) {
		this.effects.add(effect);
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

	@Override
	public boolean allZero()  {
		boolean temp = super.allZero();
		if(this.effects!=null && this.effects.size()!=0) temp= false;
		return temp;
	}
	
	public void applyUpgrade() {
		for (Effect effect : effects){
			effect.applyEffect();
		}
	}
}