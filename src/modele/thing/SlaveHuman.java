package modele.thing;

import java.util.Vector;

import org.w3c.dom.Element;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import modele.MilkFile;
import modele.MilkInterface;
import modele.MilkKind;

public class SlaveHuman extends Slave implements Cloneable {
	
	private static Vector<SlaveHuman> slaveHumans;
	public static final String file		= "SlaveHuman";
	
	private static Vector<SlaveHuman> setMilkVarFromFiles() {
		if (slaveHumans==null) slaveHumans = new Vector<SlaveHuman>();
		else slaveHumans.removeAllElements();
		Vector<Element> elementlist = new Vector<Element>();
		elementlist = MilkFile.getMilkElementsFromFiles(MilkFile.getXmlFilePath(file)+file, noeud);
		slaveHumans = getMilkVarList(elementlist);
		Vector<Element> elementlInfos = new Vector<Element>();
		elementlInfos = MilkFile.getMilkElementsFromFiles(MilkInterface.getXmlLangPath()+file, noeud);
		setInfo(slaveHumans, elementlInfos);
		return slaveHumans;
	}

	private static void setInfo(Vector<SlaveHuman> slaveHumans, Vector<Element> elementlInfos) {
		for (Element elementlInfo: elementlInfos) {
			try {
				SlaveHuman slaveHumanInfo = new SlaveHuman(elementlInfo);
				slaveHumanInfo.setInfo(elementlInfo);
				for (SlaveHuman slaveHuman:slaveHumans){
					if (slaveHumanInfo.equals(slaveHuman)){
						slaveHuman.setInfo(slaveHumanInfo.getInfo());
						break;
					}
				}
			} catch (Exception e) {e.printStackTrace();}
		}
	}

	public static ObservableList<SlaveHuman> getListes() {
		if (slaveHumans==null)setMilkVarFromFiles();
		ObservableList<SlaveHuman> clone = FXCollections.observableArrayList();
		if (slaveHumans!=null){
			for (SlaveHuman slaveHuman:slaveHumans){
				try {
					clone.add((SlaveHuman) slaveHuman.clone());
				} catch (CloneNotSupportedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return clone;
	}

	public static Vector<SlaveHuman> getMilkVarList(Vector<Element> elementlist) {
		Vector<SlaveHuman> slaveHumans = new Vector<SlaveHuman>();
		for (Element elementMilk: elementlist) {
			try {
				SlaveHuman slaveHuman = new SlaveHuman(elementMilk);
				slaveHumans.add(slaveHuman);
			} catch (Exception e) {e.printStackTrace();}
		}
		return slaveHumans;
	}
	
	public static Vector<SlaveHuman> getNullMilkVarList(Vector<Element> elementlist) {
		Vector<SlaveHuman> slaveHumans = new Vector<SlaveHuman>();
		for (Element elementMilk: elementlist) {
			try {
				SlaveHuman slaveHuman = new SlaveHuman();
				slaveHuman.setNullValueFromNode(elementMilk);
				slaveHumans.add(slaveHuman);
			} catch (Exception e) {e.printStackTrace();}
		}
		return slaveHumans;
	}
	
	// Constructors
	
	public SlaveHuman() {
		super();
		this.setKind(MilkKind.kind_Slave_Human);
	}
	public SlaveHuman(Element milkElement) {
		super();
		this.setKind(MilkKind.kind_Slave_Human);
		this.setValueFromNode(milkElement);
	}
	
	// other object methods
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		SlaveHuman clone = (SlaveHuman) super.clone();
		return clone;
	}
}
