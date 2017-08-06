package modele.thing;

import java.util.Vector;

import org.w3c.dom.Element;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import modele.MilkFile;
import modele.MilkInterface;
import modele.MilkKind;
import modele.carac.Attrib;

public class Animal extends Thing implements Cloneable {

	private static Vector<Animal> animals;
	public static final String file		= "Animal";
	public static final String noeud = "animal";
	public String getNoeud() {return noeud;}
	
	private static Vector<Animal> setMilkVarFromFiles() {
		if (animals==null) animals = new Vector<Animal>();
		else animals.removeAllElements();
		Vector<Element> elementlist = new Vector<Element>();
		elementlist = MilkFile.getMilkElementsFromFiles(MilkFile.getXmlFilePath(file)+file, noeud);
		animals = getMilkVarList(elementlist);
		Vector<Element> elementlInfos = new Vector<Element>();
		elementlInfos = MilkFile.getMilkElementsFromFiles(MilkInterface.getXmlLangPath()+file, noeud);
		setInfo(animals, elementlInfos);
		return animals;
	}

	private static void setInfo(Vector<Animal> animals, Vector<Element> elementlInfos) {
		for (Element elementlInfo: elementlInfos) {
			try {
				Animal animalInfo = new Animal(elementlInfo);
				animalInfo.setInfo(elementlInfo);
				for (Animal animal:animals){
					if (animalInfo.equals(animal)){
						animal.setInfo(animalInfo.getInfo());
						break;
					}
				}
			} catch (Exception e) {e.printStackTrace();}
		}
	}

	public static ObservableList<Animal> getListes() {
		if (animals==null)setMilkVarFromFiles();
		ObservableList<Animal> clone = FXCollections.observableArrayList();
		if (animals!=null){
			for (Animal animal:animals){
				try {
					clone.add((Animal) animal.clone());
				} catch (CloneNotSupportedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return clone;
	}

	public static ObservableList<Animal> getNeutralListes() {
		if (animals==null)setMilkVarFromFiles();
		ObservableList<Animal> clone = FXCollections.observableArrayList();
		if (animals!=null){
			for (Animal animal:animals){
				try {
					if(animal.getAttrib().getPath()==Attrib.Path_Neutral)clone.add((Animal) animal.clone());
				} catch (CloneNotSupportedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return clone;
	}

	public static ObservableList<Animal> getScienceListes() {
		if (animals==null)setMilkVarFromFiles();
		ObservableList<Animal> clone = FXCollections.observableArrayList();
		if (animals!=null){
			for (Animal animal:animals){
				try {
					if(animal.getAttrib().getPath()==Attrib.Path_Science)clone.add((Animal) animal.clone());
				} catch (CloneNotSupportedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return clone;
	}

	public static ObservableList<Animal> getMagicListes() {
		if (animals==null)setMilkVarFromFiles();
		ObservableList<Animal> clone = FXCollections.observableArrayList();
		if (animals!=null){
			for (Animal animal:animals){
				try {
					if(animal.getAttrib().getPath()==Attrib.Path_Magic)clone.add((Animal) animal.clone());
				} catch (CloneNotSupportedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return clone;
	}

	public static Vector<Animal> getMilkVarList(Vector<Element> elementlist) {
		Vector<Animal> animals = new Vector<Animal>();
		for (Element elementMilk: elementlist) {
			try {
				Animal animal = new Animal(elementMilk);
				animals.add(animal);
			} catch (Exception e) {e.printStackTrace();}
		}
		return animals;
	}
	
	public static Vector<Animal> getNullMilkVarList(Vector<Element> elementlist) {
		Vector<Animal> animals = new Vector<Animal>();
		for (Element elementMilk: elementlist) {
			try {
				Animal animal = new Animal();
				animal.setNullValueFromNode(elementMilk);
				animals.add(animal);
			} catch (Exception e) {e.printStackTrace();}
		}
		return animals;
	}
	
	// Constructors
	
	public Animal() {
		super();
		this.setKind(MilkKind.kind_Animal);
		this.setSellPrice((float)70);
		this.setProductivity(1);
	}
	public Animal(Element milkElement) {
		super();
		this.setKind(MilkKind.kind_Animal);
		this.setSellPrice((float)70);
		this.setProductivity(1);
		this.setValueFromNode(milkElement);
	}
	
	// other object methods
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		Animal clone = (Animal) super.clone();
		return clone;
	}
}
