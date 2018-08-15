package modele.baseObject;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Element;

import javafx.collections.ObservableList;
import modele.thing.LivingBeing;
import modele.thing.NearThing;
import modele.thing.Thing;

public class MilkVarList {
	
	private static MilkVarList INSTANCE = null;
	public static final String file	= "", noeud	= "";
	public String getFile() {return file;}
	public String getNoeud() {return noeud;}

	public static MilkVarList getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new MilkVarList();
        }
        return INSTANCE;
    }

	@SuppressWarnings({ "unchecked" }) @SafeVarargs
	protected static ObservableList<Thing> merge(ObservableList<? extends Thing> into, ObservableList<? extends Thing>... lists) {
        final ObservableList<Thing> list = (ObservableList<Thing>) into;
        for (ObservableList<? extends Thing> l : lists) {
            list.addAll(l);
            l.addListener((javafx.collections.ListChangeListener.Change<? extends Thing> c) -> {
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
	
	protected static void setInfo(List<? extends MilkXmlObj> milkObjs, ArrayList<Element> elementlInfos) {
		for (Element elementlInfo: elementlInfos) {
			try {
				MilkXmlObj test = new MilkXmlObj(elementlInfo);
				test.setInfo(elementlInfo);
				for (MilkXmlObj milkObj:milkObjs){
					if (test.getId().intValue() == milkObj.getId().intValue()){
						milkObj.setInfo(test.getInfo());
						break;
					}
				}
			} catch (Exception e) {e.printStackTrace();}
		}
	}
	
	public static void setIcon(ArrayList<? extends MilkXmlObj> milkObjs, ArrayList<Element> elementIcons) {
		for (Element elementIcon: elementIcons) {
			try {
				MilkXmlObj test = new MilkXmlObj(elementIcon);
				test.setIcon(elementIcon);
				for (MilkXmlObj milkObj:milkObjs){
					if (test.getId().intValue() == milkObj.getId().intValue()){
						milkObj.setIcon(test.getIcon());
						break;
					}
				}
			} catch (Exception e) {e.printStackTrace();}
		}
	}
	
	public static void setScene(ArrayList<? extends NearThing> things, ArrayList<Element> elementScenes) {
		for (Element elementScene: elementScenes) {
			try {
				NearThing test = new NearThing(elementScene);
				test.setScene(elementScene);
				for (NearThing thing:things){
					if (test.getId().intValue() == thing.getId().intValue()){
						thing.setScene(test.getScene());
						break;
					}
				}
			} catch (Exception e) {e.printStackTrace();}
		}
	}

	public static double getIncomeFromList(double toolProdBonus, double toolQualBonus, double cattleProdBonus, double cattleQualBonus, double buildProdBonus, double buildQualBonus, ObservableList<? extends LivingBeing> beingList) {
		double tIncome = 0;
		for (LivingBeing thing: beingList){
			tIncome += thing.getActiveIncome(toolProdBonus,toolQualBonus,cattleProdBonus,cattleQualBonus,buildProdBonus,buildQualBonus) ;
		}
		return tIncome;
	}	
	
	// Constructors
	
	protected MilkVarList() {}
	
	// other method : 
	
	public ArrayList<Element> getElementStat() {
		ArrayList<Element> elementlist = new ArrayList<Element>();
		elementlist = MilkFile.getMilkElementsFromFiles(MilkFile.getXmlFilePath(this.getFile())+this.getFile(), this.getNoeud());
		return elementlist;
	}
	
	public ArrayList<Element> getElementInfo() {
		ArrayList<Element> elementlist = new ArrayList<Element>();
		elementlist = MilkFile.getMilkElementsFromFiles(MilkInterface.getXmlLangPath()+this.getFile(), this.getNoeud());
		return elementlist;
	}
	
	public ArrayList<Element> getElementIcon() {
		ArrayList<Element> elementlist = new ArrayList<Element>();
		elementlist = MilkFile.getMilkElementsFromFiles(MilkImage.getXmlIconsPath(this.getFile())+this.getFile(), this.getNoeud());
		return elementlist;
	}
	
	public ArrayList<Element> getElementScene() {
		ArrayList<Element> elementlist = new ArrayList<Element>();
		elementlist = MilkFile.getMilkElementsFromFiles(MilkImage.getXmlScenesPath(this.getFile())+this.getFile(), this.getNoeud());
		return elementlist;
	}
	
	public void resetListes() {
		this.setNeutralListe();
		this.setScienceListe();
		this.setMagicListe();
		this.setFullListe();
	}
	
	/**
	 * meant to be @Override 
	 */
	public void setFullListe() {}
	
	/**
	 * meant to be @Override 
	 */
	public void setMagicListe() {}
	
	/**
	 * meant to be @Override 
	 */
	public void setScienceListe() {}
	
	/**
	 * meant to be @Override 
	 */
	public void setNeutralListe() {}
}
