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

	private static Vector<Research> researchs;
	public static final String file		= "Research";
	public static final String noeud = "research";
	public String getNoeud() {return noeud;}

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

	private static void setInfo(Vector<Research> researchs, Vector<Element> elementlInfos) {
		for (Element elementlInfo: elementlInfos) {
			try {
				Research researchInfo = new Research(elementlInfo);
				researchInfo.setInfo(elementlInfo);
				for (Research research:researchs){
					if (researchInfo.equals(research)){
						research.setInfo(researchInfo.getInfo());
						break;
					}
				}
			} catch (Exception e) {e.printStackTrace();}
		}
	}

	@SuppressWarnings("rawtypes")
	public static ObservableList getListes() {
		if (researchs==null)setMilkVarFromFiles();
		ObservableList<Research> clone = FXCollections.observableArrayList();
		if (researchs!=null){
			for (Research research:researchs){
				try {
					clone.add((Research) research.clone());
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
		Vector<Research> researchs = new Vector<Research>();
		for (Element elementMilk: elementlist) {
			try {
				Research research = new Research(elementMilk);
				researchs.add(research);
			} catch (Exception e) {e.printStackTrace();}
		}
		return researchs;
	}

	@SuppressWarnings("rawtypes")
	public static Vector getNullMilkVarList(Vector<Element> elementlist) {
		Vector<Research> researchs = new Vector<Research>();
		for (Element elementMilk: elementlist) {
			try {
				Research research = new Research();
				research.setNullValueFromNode(elementMilk);
				researchs.add(research);
			} catch (Exception e) {e.printStackTrace();}
		}
		return researchs;
	}
	
	
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
	@Override
	public void setNullValueFromNode(Element milkElement) {
		super.setNullValueFromNode(milkElement);
		this.setNullSacrifice(milkElement);
		this.setNullCheck(milkElement);
	}
	
	// field methods
	
	public Sacrifice getSacrifice() {
		return this.sacrifice;
	}
	public void setSacrifice(Sacrifice sacrifice) {
		this.sacrifice = sacrifice;
	}
	public void setSacrifice(Element milkElement) {
		this.sacrifice.setValueFromNode(milkElement);;
	}
	public void setNullSacrifice(Element milkElement) {
		this.sacrifice.setValueFromNode(milkElement);
	}
	
	public Check getCheck() {
		return this.check;
	}
	public void setCheck(Check check) {
		this.check = check;
	}
	public void setCheck(Element milkElement) {
		this.check.setValueFromNode(milkElement);;
	}
	public void setNullCheck(Element milkElement) {
		this.check.setValueFromNode(milkElement);
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