package modele.intel;

import org.w3c.dom.Element;

import modele.baseObject.MilkKind;

public class Synergy extends Upgrade {
	public String getNoeud() {return ResearchList.getInstance().getNoeud();}
/*
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
			modelSynergys = FXCollections.observableArrayList(extractorS());
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
	
	public static void updateInfoFromFiles() {
		if (modelSynergys==null) getResearchListe();
		ArrayList<Element> elementlInfos = new ArrayList<Element>();
		elementlInfos = MilkFile.getMilkElementsFromFiles(MilkInterface.getXmlLangPath()+file, noeud);
		setInfo(modelSynergys, elementlInfos);
	}

	public static Callback<Synergy, Observable[]> extractorS() {
        return (Synergy p) -> new Observable[]{p.getInfo().getObrservableName()};
	}*/

	// Fields
	
	// Constructors
	
	public Synergy() {
		super();
		this.setKind(MilkKind.Synergy);
	}
	public Synergy(Element milkElement) {
		super();
		this.setValueFromNode(milkElement);
		this.setKind(MilkKind.Synergy);
	}
	public Synergy(Synergy original) {
		super(original);
	}

	// Set value from Element methods
	
	@Override
	public void setValueFromNode(Element milkElement) {
		super.setValueFromNode(milkElement);
		
	}
	
	// field methods
	
	// toString & toXml methods

	@Override
	public String toStringStatChild() {
		String temp = super.toStringStatChild();
		return temp;
	}
	@Override
	public String toXmlStatChild() {
		String temp = super.toXmlStatChild();
		return temp;
	}

	// other object methods

	@Override
	public boolean allZero()  {
		boolean temp = super.allZero();
		return temp;
	}
	
}
