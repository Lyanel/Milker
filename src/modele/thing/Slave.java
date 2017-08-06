package modele.thing;

import modele.MilkKind;
import modele.carac.Attrib;
import modele.carac.Sacrifice;

import org.w3c.dom.Element;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class Slave extends Thing implements Cloneable {
	
	public static String noeud = "slave";
	public String getNoeud() {return noeud;}

	public static ObservableList<Slave> getNeutralListes() {		
		ObservableList<Slave> clone = FXCollections.observableArrayList();
		for (SlaveHuman hs:SlaveHuman.getListes()){
			try {
				if(hs.getAttrib().getPath()==Attrib.Path_Neutral)clone.add((SlaveHuman) hs.clone());
			} catch (CloneNotSupportedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		for (SlaveAnimal as:SlaveAnimal.getListes()){
			try {
				if(as.getAttrib().getPath()==Attrib.Path_Neutral)clone.add((SlaveAnimal) as.clone());
			} catch (CloneNotSupportedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return clone;
	}

	public static ObservableList<Slave> getScienceListes() {
		ObservableList<Slave> clone = FXCollections.observableArrayList();
		for (SlaveHuman hs:SlaveHuman.getListes()){
			try {
				if(hs.getAttrib().getPath()==Attrib.Path_Science)clone.add((SlaveHuman) hs.clone());
			} catch (CloneNotSupportedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		for (SlaveAnimal as:SlaveAnimal.getListes()){
			try {
				if(as.getAttrib().getPath()==Attrib.Path_Science)clone.add((SlaveAnimal) as.clone());
			} catch (CloneNotSupportedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return clone;
		
	}

	public static ObservableList<Slave> getMagicListes() {
		ObservableList<Slave> clone = FXCollections.observableArrayList();
		for (SlaveHuman hs:SlaveHuman.getListes()){
			try {
				if(hs.getAttrib().getPath()==Attrib.Path_Magic)clone.add((SlaveHuman) hs.clone());
			} catch (CloneNotSupportedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		for (SlaveAnimal as:SlaveAnimal.getListes()){
			try {
				if(as.getAttrib().getPath()==Attrib.Path_Magic)clone.add((SlaveAnimal) as.clone());
			} catch (CloneNotSupportedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return clone;
		
	}
	
	private Sacrifice sacrifice;
	
	// Constructors
	
	public Slave() {
		super();
		sacrifice = new Sacrifice();
		this.setKind(MilkKind.kind_Slaves);
		this.setProductivity(1);
	}
	public Slave(Element milkElement) {
		super();
		this.sacrifice = new Sacrifice();
		this.setKind(MilkKind.kind_Slaves);
		this.setProductivity(1);
		this.setValueFromNode(milkElement);
	}

	// Set value from Element methods
	
	@Override
	public void setValueFromNode(Element milkElement) {
		super.setValueFromNode(milkElement);
		this.setSacrifice(milkElement);
	}
	@Override
	public void setNullValueFromNode(Element milkElement) {
		super.setNullValueFromNode(milkElement);
		this.setNullSacrifice(milkElement);
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
		
	// toString & toXml methods
	
	@Override
	public String toStringStatChild() {
		String temp = super.toStringStatChild();
		if (this.sacrifice != null) temp = this.sacrifice.toStringStat();
		return temp;
	}
	@Override
	public String toXmlStatChild() {
		String temp = super.toXmlStatChild();
		if (this.sacrifice != null) temp = this.sacrifice.toXmlStat();
		return temp;
	}
	
	// other object methods

	@Override
	public boolean allZero()  {
		boolean temp = super.allZero();
		if(this.sacrifice!=null && !this.sacrifice.allZero()) temp= false;
		return temp;
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		Slave clone = (Slave) super.clone();
		if (this.sacrifice!=null) clone.setSacrifice((Sacrifice) this.sacrifice.clone());
		return clone;
	}
}
