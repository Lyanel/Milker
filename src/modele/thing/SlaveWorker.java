package modele.thing;

import modele.MilkFile;
import modele.MilkImage;
import modele.MilkInterface;
import modele.MilkKind;
import modele.carac.Agent;
import modele.carac.ThingAttrib;

import java.util.ArrayList;

import org.w3c.dom.Element;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class SlaveWorker extends Slave implements Cloneable {

	public static final String file		= "SlaveWorker";

	private static ArrayList<SlaveWorker> slaveWorkers;
	private static ObservableList<SlaveWorker> modelListe;
	private static ObservableList<SlaveWorker> modelNeutralListe;
	private static ObservableList<SlaveWorker> modelScienceListe;
	private static ObservableList<SlaveWorker> modelMagicListe;
	
	@SafeVarargs
	private static ObservableList<SlaveWorker> merge(ObservableList<SlaveWorker> into, ObservableList<SlaveWorker>... lists) {
        final ObservableList<SlaveWorker> list = into;
        for (ObservableList<SlaveWorker> l : lists) {
            list.addAll(l);
            l.addListener((javafx.collections.ListChangeListener.Change<? extends SlaveWorker> c) -> {
                while (c.next()) {
                    if (c.wasAdded()) {
                        list.addAll(c.getAddedSubList());
                    }
                    if (c.wasRemoved()) {
                        list.removeAll(c.getRemoved());
                    }
                    if (c.wasUpdated()) {
                        list.removeAll(c.getRemoved());
                        list.addAll(c.getAddedSubList());
                    }
                }
            });
        }
        return list;
    }
	
	private static ArrayList<SlaveWorker> setMilkVarFromFiles() {
		if (slaveWorkers==null) slaveWorkers = new ArrayList<SlaveWorker>();
		else slaveWorkers.clear();
		//Set stats
		ArrayList<Element> elementlist = new ArrayList<Element>();
		elementlist = MilkFile.getMilkElementsFromFiles(MilkFile.getXmlFilePath(file)+file, noeud);
		slaveWorkers = getMilkVarList(elementlist);
		//Set info
		ArrayList<Element> elementlInfos = new ArrayList<Element>();
		elementlInfos = MilkFile.getMilkElementsFromFiles(MilkInterface.getXmlLangPath()+file, noeud);
		setInfo(slaveWorkers, elementlInfos);
		//Set icon
		ArrayList<Element> elementlIcon = new ArrayList<Element>();
		elementlIcon = MilkFile.getMilkElementsFromFiles(MilkImage.getXmlIconsPath(file)+file, noeud);
		setIcon(slaveWorkers, elementlIcon);
		//Set scene
		ArrayList<Element> elementlScene = new ArrayList<Element>();
		elementlScene = MilkFile.getMilkElementsFromFiles(MilkImage.getXmlScenesPath(file)+file, noeud);
		setScene(slaveWorkers, elementlScene);
		
		return slaveWorkers;
	}

	public static ArrayList<SlaveWorker> getMilkVarList(ArrayList<Element> elementlist) {
		ArrayList<SlaveWorker> slaveWorkers = new ArrayList<SlaveWorker>();
		for (Element elementMilk: elementlist) {
			try {
				SlaveWorker slaveWorker = new SlaveWorker(elementMilk);
				slaveWorkers.add(slaveWorker);
			} catch (Exception e) {e.printStackTrace();}
		}
		return slaveWorkers;
	}
	/*
	public static ArrayList<SlaveWorker> getNullMilkVarList(ArrayList<Element> elementlist) {
		ArrayList<SlaveWorker> slaveWorkers = new ArrayList<SlaveWorker>();
		for (Element elementMilk: elementlist) {
			try {
				SlaveWorker slaveWorker = new SlaveWorker();
				slaveWorker.setNullValueFromNode(elementMilk);
				slaveWorkers.add(slaveWorker);
			} catch (Exception e) {e.printStackTrace();}
		}
		return slaveWorkers;
	}*/

	public static ObservableList<SlaveWorker> getSWFullListe() {
		if (modelListe==null){
			if (slaveWorkers==null)setMilkVarFromFiles();
			if (modelNeutralListe==null)getNeutralListe();
			if (modelScienceListe==null)getScienceListe();
			if (modelMagicListe==null)getMagicListe();
			modelListe = FXCollections.observableArrayList();
			merge(modelListe, modelNeutralListe, modelScienceListe, modelMagicListe);
		}
		return modelListe;
	}

	public static ObservableList<SlaveWorker> getSWNeutralListe() {
		if (modelNeutralListe==null){
			if (slaveWorkers==null)setMilkVarFromFiles();
			modelNeutralListe = FXCollections.observableArrayList();
			if (slaveWorkers!=null){
				for (SlaveWorker slaveWorker:slaveWorkers){
					try {
						if(slaveWorker.getAttrib().getTree()==ThingAttrib.Tree_Neutral)modelNeutralListe.add((SlaveWorker) slaveWorker.clone());
					} catch (CloneNotSupportedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		return modelNeutralListe;
	}

	public static ObservableList<SlaveWorker> getSWScienceListe() {
		if (modelScienceListe==null){
			if (slaveWorkers==null)setMilkVarFromFiles();
			modelScienceListe = FXCollections.observableArrayList();
			if (slaveWorkers!=null){
				for (SlaveWorker slaveWorker:slaveWorkers){
					try {
						if(slaveWorker.getAttrib().getTree()==ThingAttrib.Tree_Science)modelScienceListe.add((SlaveWorker) slaveWorker.clone());
					} catch (CloneNotSupportedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		return modelScienceListe;
	}

	public static ObservableList<SlaveWorker> getSWMagicListe() {
		if (modelMagicListe==null){
			if (slaveWorkers==null)setMilkVarFromFiles();
			modelMagicListe = FXCollections.observableArrayList();
			if (slaveWorkers!=null){
				for (SlaveWorker slaveWorker:slaveWorkers){
					try {
						if(slaveWorker.getAttrib().getTree()==ThingAttrib.Tree_Magic)modelMagicListe.add((SlaveWorker) slaveWorker.clone());
					} catch (CloneNotSupportedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		return modelMagicListe;
	}
	
	private Agent agent;

	// Constructors
	
	public SlaveWorker() {
		super();
		this.agent = new Agent();
		this.setKind(MilkKind.kind_Slave_Worker);
	}
	public SlaveWorker(Element milkElement) {
		super();
		this.agent = new Agent();
		this.setKind(MilkKind.kind_Slave_Worker);
		this.setValueFromNode(milkElement);
	}
	
	// Set value from Element methods
	
	@Override
	public void setValueFromNode(Element milkElement) {
		super.setValueFromNode(milkElement);
		this.setAgent(milkElement);
	}
	public void setAgent(Element milkElement) {
		this.agent.setValueFromNode(milkElement);;
	}
	/*@Override
	public void setNullValueFromNode(Element milkElement) {
		super.setNullValueFromNode(milkElement);
		this.setAgent(milkElement);
	}
	public void setNullAgent(Element milkElement) {
		this.agent.setNullValueFromNode(milkElement);
	}*/
	
	// field methods

	public Agent getAgent() {
		return this.agent;
	}
	public void setAgent(Agent agent) {
		this.agent = agent;
	}
	
	// toString & toXml methods
	
	@Override
	public String toStringStatChild() {
		String temp = super.toStringStatChild();
		if (this.agent != null) temp += this.agent.toXmlStat();
		return temp;
	}
	@Override
	public String toXmlStatChild() {
		String temp = super.toXmlStatChild();
		if (this.agent != null) temp += this.agent.toXmlStat();
		return temp;
	}
	
	// other object methods

	@Override
	public boolean allZero()  {
		boolean temp = super.allZero();
		if(this.agent!=null && !this.agent.allZero()) temp= false;
		return temp;
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		SlaveWorker clone = (SlaveWorker) super.clone();
		if (this.agent!=null) clone.setAgent((Agent) this.agent.clone());
		return clone;
	}
}