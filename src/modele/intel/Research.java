package modele.intel;

import modele.baseObject.MilkKind;
import modele.carac.Check;
import modele.carac.Sacrifice;


import org.w3c.dom.Element;

public class Research extends Intel {
	public String getNoeud() {return ResearchList.getInstance().getNoeud();}

/*	public static final String file = "Research", noeud = "research";
	public String getNoeud() {return noeud;}

	private static ArrayList<Research> researchs;
	private static ObservableList<Research> modelResearchs;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static ArrayList setMilkVarFromFiles() {
		if (researchs==null) researchs = new ArrayList<Research>();
		else researchs.clear();
		ArrayList<Element> elementlist = new ArrayList<Element>();
		elementlist = MilkFile.getMilkElementsFromFiles(MilkFile.getXmlFilePath(file)+file, noeud);
		researchs = getMilkVarList(elementlist);
		ArrayList<Element> elementlInfos = new ArrayList<Element>();
		elementlInfos = MilkFile.getMilkElementsFromFiles(MilkInterface.getXmlLangPath()+file, noeud);
		setInfo(researchs, elementlInfos);
		return researchs;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static ArrayList getMilkVarList(ArrayList<Element> elementlist) {
		ArrayList<Research> researchs = new ArrayList<Research>();
		for (Element elementMilk: elementlist) {
			try {
				Research research = new Research(elementMilk);
				researchs.add(research);
			} catch (Exception e) {e.printStackTrace();}
		}
		return researchs;
	}
	
	public static ObservableList<Research> getResearchListe() {
		if (modelResearchs==null){
			if (researchs==null)setMilkVarFromFiles();
			modelResearchs = FXCollections.observableArrayList(extractor());
			if (researchs!=null){
				for (Research research:researchs){
					try {
						modelResearchs.add((Research) research.clone());
					} catch (CloneNotSupportedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		return modelResearchs;
	}
	
	public static void updateInfoFromFiles() {
		if (modelResearchs==null) getResearchListe();
		ArrayList<Element> elementlInfos = new ArrayList<Element>();
		elementlInfos = MilkFile.getMilkElementsFromFiles(MilkInterface.getXmlLangPath()+file, noeud);
		setInfo(modelResearchs, elementlInfos);
	}

	public static Callback<Research, Observable[]> extractor() {
        return (Research p) -> new Observable[]{p.getInfo().getObrservableName()};
	}
*/
	// Fields
	
	private Sacrifice sacrifice;
	private Check check;

	// Constructors
	
	public Research() {
		super();
		sacrifice = new Sacrifice();
		check = new Check();
		this.setKind(MilkKind.Research);
	}
	public Research(Element milkElement) {
		super();
		this.sacrifice = new Sacrifice();
		this.check = new Check();
		this.setKind(MilkKind.Research);
		this.setValueFromNode(milkElement);
	}
	public Research(Research original) {
		super(original);
		this.sacrifice = new Sacrifice(original.getSacrifice());
		this.check = new Check(original.getCheck());
	}

	// Set value from Element methods
	
	@Override
	public void setValueFromNode(Element milkElement) {
		super.setValueFromNode(milkElement);
		this.setSacrifice(milkElement);
		this.setCheck(milkElement);
	}
	public void setSacrifice(Element milkElement) {
		this.sacrifice.setValueFromNode(milkElement);;
	}
	public void setCheck(Element milkElement) {
		this.check.setValueFromNode(milkElement);;
	}
	
	// field methods
	
	public Sacrifice getSacrifice() {
		return this.sacrifice;
	}
	public void setSacrifice(Sacrifice sacrifice) {
		this.sacrifice = sacrifice;
	}
	
	public Check getCheck() {
		return this.check;
	}
	public void setCheck(Check check) {
		this.check = check;
	}
	
	// toString & toXml methods
	
	@Override
	public String toStringStatChild() {
		String temp = super.toStringStatChild();
		if (this.sacrifice != null) temp = this.sacrifice.toStringStat();
		if (this.check != null) temp = this.check.toStringStat();
		return temp;
	}
	@Override
	public String toXmlStatChild() {
		String temp = super.toXmlStatChild();
		if (this.sacrifice != null) temp = this.sacrifice.toXmlStat();
		if (this.check != null) temp = this.check.toXmlStat();
		return temp;
	}
	
	// other object methods

	@Override
	public boolean allZero()  {
		boolean temp = super.allZero();
		if(this.sacrifice!=null && !this.sacrifice.allZero()) temp= false;
		if(this.check!=null && !this.check.allZero()) temp= false;
		return temp;
	}
	
}