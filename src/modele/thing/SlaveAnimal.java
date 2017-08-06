package modele.thing;

import java.util.Vector;

import org.w3c.dom.Element;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import modele.MilkFile;
import modele.MilkInterface;
import modele.MilkKind;

public class SlaveAnimal extends Slave implements Cloneable {

	private static Vector<SlaveAnimal> slaveAnimals;
	public static final String file		= "SlaveAnimal";
	
	private static Vector<SlaveAnimal> setMilkVarFromFiles() {
		if (slaveAnimals==null) slaveAnimals = new Vector<SlaveAnimal>();
		else slaveAnimals.removeAllElements();
		Vector<Element> elementlist = new Vector<Element>();
		elementlist = MilkFile.getMilkElementsFromFiles(MilkFile.getXmlFilePath(file)+file, noeud);
		slaveAnimals = getMilkVarList(elementlist);
		Vector<Element> elementlInfos = new Vector<Element>();
		elementlInfos = MilkFile.getMilkElementsFromFiles(MilkInterface.getXmlLangPath()+file, noeud);
		setInfo(slaveAnimals, elementlInfos);
		return slaveAnimals;
	}

	private static void setInfo(Vector<SlaveAnimal> slaveAnimals, Vector<Element> elementlInfos) {
		for (Element elementlInfo: elementlInfos) {
			try {
				SlaveAnimal slaveAnimalInfo = new SlaveAnimal(elementlInfo);
				slaveAnimalInfo.setInfo(elementlInfo);
				for (SlaveAnimal slaveAnimal:slaveAnimals){
					if (slaveAnimalInfo.equals(slaveAnimal)){
						slaveAnimal.setInfo(slaveAnimalInfo.getInfo());
						break;
					}
				}
			} catch (Exception e) {e.printStackTrace();}
		}
	}

	public static ObservableList<SlaveAnimal> getListes() {
		if (slaveAnimals==null)setMilkVarFromFiles();
		ObservableList<SlaveAnimal> clone = FXCollections.observableArrayList();
		if (slaveAnimals!=null){
			for (SlaveAnimal slaveAnimal:slaveAnimals){
				try {
					clone.add((SlaveAnimal) slaveAnimal.clone());
				} catch (CloneNotSupportedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return clone;
	}

	public static Vector<SlaveAnimal> getMilkVarList(Vector<Element> elementlist) {
		Vector<SlaveAnimal> slaveAnimals = new Vector<SlaveAnimal>();
		for (Element elementMilk: elementlist) {
			try {
				SlaveAnimal slaveAnimal = new SlaveAnimal(elementMilk);
				slaveAnimals.add(slaveAnimal);
			} catch (Exception e) {e.printStackTrace();}
		}
		return slaveAnimals;
	}
	
	public static Vector<SlaveAnimal> getNullMilkVarList(Vector<Element> elementlist) {
		Vector<SlaveAnimal> slaveAnimals = new Vector<SlaveAnimal>();
		for (Element elementMilk: elementlist) {
			try {
				SlaveAnimal slaveAnimal = new SlaveAnimal();
				slaveAnimal.setNullValueFromNode(elementMilk);
				slaveAnimals.add(slaveAnimal);
			} catch (Exception e) {e.printStackTrace();}
		}
		return slaveAnimals;
	}
	
	// Constructors
	
	public SlaveAnimal() {
		super();
		this.setKind(MilkKind.kind_Slave_Animal);
	}
	public SlaveAnimal(Element milkElement) {
		super();
		this.setKind(MilkKind.kind_Slave_Animal);
		this.setValueFromNode(milkElement);
	}
	
	// other object methods
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		SlaveAnimal clone = (SlaveAnimal) super.clone();
		return clone;
	}
}
