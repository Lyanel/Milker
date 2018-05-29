package modele.intel;

import modele.MilkFile;
import modele.MilkInterface;
import modele.MilkKind;
import modele.carac.Check;
import modele.carac.Sacrifice;

import java.util.Vector;

import org.w3c.dom.Element;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Research extends Intel implements Cloneable {

	public static final String file = "Research", noeud = "research";
	public String getNoeud() {return noeud;}

	private static Vector<Research> researchs;
	private static ObservableList<Research> modelResearchs;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static Vector setMilkVarFromFiles() {
		if (researchs==null) researchs = new Vector<Research>();
		else researchs.removeAllElements();
		Vector<Element> elementlist = new Vector<Element>();
		elementlist = MilkFile.getMilkElementsFromFiles(MilkFile.getXmlFilePath(file)+file, noeud);
		researchs = getMilkVarList(elementlist);
		Vector<Element> elementlInfos = new Vector<Element>();
		elementlInfos = MilkFile.getMilkElementsFromFiles(MilkInterface.getXmlLangPath()+file, noeud);
		setInfo(researchs, elementlInfos);
		return researchs;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Vector getMilkVarList(Vector<Element> elementlist) {
		Vector<Research> researchs = new Vector<Research>();
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
			modelResearchs = FXCollections.observableArrayList();
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

	// Fields
	
	private Sacrifice sacrifice;
	private Check check;

	// Constructors
	
	public Research() {
		super();
		sacrifice = new Sacrifice();
		check = new Check();
		this.setKind(MilkKind.kind_Research);
	}
	public Research(Element milkElement) {
		super();
		this.sacrifice = new Sacrifice();
		this.check = new Check();
		this.setKind(MilkKind.kind_Research);
		this.setValueFromNode(milkElement);
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
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		Research clone = (Research) super.clone();
		if (this.sacrifice!=null) clone.setSacrifice((Sacrifice) this.sacrifice.clone());
		if (this.check!=null) clone.setCheck((Check) this.check.clone());
		return clone;
	}
}