package modele.thing;

import java.util.Vector;

import org.w3c.dom.Element;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import modele.MilkFile;
import modele.MilkImage;
import modele.MilkInterface;
import modele.MilkKind;
import modele.carac.ThingAttrib;

public class Worker extends Thing implements Cloneable {

	private static Vector<Worker> workers;
	public static final String file		= "Worker";
	public static final String noeud = "worker";
	public String getNoeud() {return noeud;}
	
	private static Vector<Worker> setMilkVarFromFiles() {
		if (workers==null) workers = new Vector<Worker>();
		else workers.removeAllElements();
		//Set stats
		Vector<Element> elementlist = new Vector<Element>();
		elementlist = MilkFile.getMilkElementsFromFiles(MilkFile.getXmlFilePath(file)+file, noeud);
		workers = getMilkVarList(elementlist);
		//Set info
		Vector<Element> elementlInfos = new Vector<Element>();
		elementlInfos = MilkFile.getMilkElementsFromFiles(MilkInterface.getXmlLangPath()+file, noeud);
		setInfo(workers, elementlInfos);
		//Set icon
		Vector<Element> elementlIcon = new Vector<Element>();
		elementlIcon = MilkFile.getMilkElementsFromFiles(MilkImage.getXmlIconsPath(file)+file, noeud);
		setIcon(workers, elementlIcon);
		//Set scene
		Vector<Element> elementlScene = new Vector<Element>();
		elementlScene = MilkFile.getMilkElementsFromFiles(MilkImage.getXmlScenesPath(file)+file, noeud);
		setScene(workers, elementlScene);
		
		return workers;
	}

	private static void setInfo(Vector<Worker> workers, Vector<Element> elementlInfos) {
		for (Element elementlInfo: elementlInfos) {
			try {
				Worker workerInfo = new Worker(elementlInfo);
				workerInfo.setInfo(elementlInfo);
				for (Worker worker:workers){
					if (workerInfo.equals(worker)){
						worker.setInfo(workerInfo.getInfo());
						break;
					}
				}
			} catch (Exception e) {e.printStackTrace();}
		}
	}

	private static void setIcon(Vector<Worker> workers, Vector<Element> elementIcons) {
		for (Element elementIcon: elementIcons) {
			try {
				Worker workerIcon = new Worker(elementIcon);
				workerIcon.setIcon(elementIcon);
				for (Worker worker:workers){
					if (workerIcon.equals(worker)){
						worker.setIcon(workerIcon.getIcon());
						break;
					}
				}
			} catch (Exception e) {e.printStackTrace();}
		}
	}

	private static void setScene(Vector<Worker> workers, Vector<Element> elementScenes) {
		for (Element elementScene: elementScenes) {
			try {
				Worker workerScene = new Worker(elementScene);
				workerScene.setScene(elementScene);
				for (Worker worker:workers){
					if (workerScene.equals(worker)){
						worker.setScene(workerScene.getScene());
						break;
					}
				}
			} catch (Exception e) {e.printStackTrace();}
		}
	}

	public static ObservableList<Worker> getListes() {
		if (workers==null)setMilkVarFromFiles();
		ObservableList<Worker> clone = FXCollections.observableArrayList();
		if (workers!=null){
			for (Worker worker:workers){
				try {
					clone.add((Worker) worker.clone());
				} catch (CloneNotSupportedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return clone;
	}

	public static ObservableList<Worker> getNeutralListes() {
		if (workers==null)setMilkVarFromFiles();
		ObservableList<Worker> clone = FXCollections.observableArrayList();
		if (workers!=null){
			for (Worker worker:workers){
				try {
					if(worker.getAttrib().getTree()==ThingAttrib.Tree_Neutral)clone.add((Worker) worker.clone());
				} catch (CloneNotSupportedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return clone;
	}


	public static ObservableList<Worker> getScienceListes() {
		if (workers==null)setMilkVarFromFiles();
		ObservableList<Worker> clone = FXCollections.observableArrayList();
		if (workers!=null){
			for (Worker worker:workers){
				try {
					if(worker.getAttrib().getTree()==ThingAttrib.Tree_Science)clone.add((Worker) worker.clone());
				} catch (CloneNotSupportedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return clone;
	}


	public static ObservableList<Worker> getMagicListes() {
		if (workers==null)setMilkVarFromFiles();
		ObservableList<Worker> clone = FXCollections.observableArrayList();
		if (workers!=null){
			for (Worker worker:workers){
				try {
					if(worker.getAttrib().getTree()==ThingAttrib.Tree_Magic)clone.add((Worker) worker.clone());
				} catch (CloneNotSupportedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return clone;
	}


	public static Vector<Worker> getMilkVarList(Vector<Element> elementlist) {
		Vector<Worker> workers = new Vector<Worker>();
		for (Element elementMilk: elementlist) {
			try {
				Worker worker = new Worker(elementMilk);
				workers.add(worker);
			} catch (Exception e) {e.printStackTrace();}
		}
		return workers;
	}
	
	public static Vector<Worker> getNullMilkVarList(Vector<Element> elementlist) {
		Vector<Worker> workers = new Vector<Worker>();
		for (Element elementMilk: elementlist) {
			try {
				Worker worker = new Worker();
				worker.setNullValueFromNode(elementMilk);
				workers.add(worker);
			} catch (Exception e) {e.printStackTrace();}
		}
		return workers;
	}

	public static double getIncomeFromList(ObservableList<Worker> thingList, double toolProdBonus, double toolQualBonus, double cattleProdBonus,
			double cattleQualBonus, double buildProdBonus, double buildQualBonus) {
		double tIncome = 0;
		for (Thing thing:thingList){
			tIncome += thing.getIncome(toolProdBonus,toolQualBonus,cattleProdBonus,cattleQualBonus,buildProdBonus,buildQualBonus) ;
		}
		return tIncome;
	}
	
	// Constructors
	
	public Worker() {
		super();
		this.setKind(MilkKind.kind_Ascension);
		this.setSellPrice((float)-40);
	}
	public Worker(Element milkElement) {
		super();
		this.setKind(MilkKind.kind_Worker);
		this.setSellPrice((float)-40);
		this.setValueFromNode(milkElement);
	}
	
	// other object methods
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		Worker clone = (Worker) super.clone();
		return clone;
	}
}
