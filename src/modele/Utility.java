package modele;

import java.util.List;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Vector;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Element;

import javafx.collections.ObservableList;
import modele.baseObject.MilkKind;
import modele.baseObject.MilkPricedObj;
import modele.baseObject.MilkXmlObj;
import modele.carac.Agent;
import modele.carac.NeededIntel;
import modele.carac.NeededThing;
import modele.carac.QuantityListener;
import modele.carac.MilkTree;
import modele.intel.AscensionList;
import modele.intel.Intel;
import modele.intel.Research;
import modele.intel.ResearchList;
import modele.intel.SynergyList;
import modele.intel.UpgradeList;
import modele.thing.AnimalList;
import modele.thing.BuildingList;
import modele.thing.Slave;
import modele.thing.SlaveListA;
import modele.thing.SlaveListH;
import modele.thing.SlaveListW;
import modele.thing.Thing;
import modele.thing.WorkerList;

public class Utility{
//	private static final String NAME_FILE_STRING = "NameList";
//	private static final Vector<String> LAST_NAME_LIST_STRINGS = ParseMilkFile.getNamesLists(NAME_FILE_STRING);
	
	/**
	 * return a random Name.
	 * @throws IOException 
	 * @throws SAXException 
	 * @throws ParserConfigurationException 
	 * *
	public static String getRandomLastName() {
		int i = (int) Math.round(LAST_NAME_LIST_STRINGS.size()*Math.random()) ;
		if (i==LAST_NAME_LIST_STRINGS.size())i-=1;
		return LAST_NAME_LIST_STRINGS.elementAt(i);
	}*/
	
	/**
	 * return a string from a xml element.
	 * */
	public static String getStringFromElement(Element node) {
		DOMSource domSource = new DOMSource(node);
		StringWriter writer = new StringWriter();
		StreamResult result = new StreamResult(writer);
		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer transformer = null;
		try {
			transformer = tf.newTransformer();
			transformer.transform(domSource, result);
		} catch (Exception e) {e.printStackTrace();}
		return writer.toString();
	}

	/**
	 * return an Array of ObservableList depending of a Milkkind.
	 * */
	@SuppressWarnings("unchecked")
	public static ObservableList<? extends Thing>[] getThingsListsFromKind(MilkKind value) {
		Vector<ObservableList<? extends Thing>> lists = new Vector<ObservableList<? extends Thing>>();
		
		if (MilkKind.checkThingKind(MilkKind.Building, value)) lists.add(BuildingList.getInstance().getFullListe());
		if (MilkKind.checkThingKind(MilkKind.Worker, value)) lists.add(WorkerList.getInstance().getFullListe());
		if (MilkKind.checkThingKind(MilkKind.Slave_Worker, value)) lists.add(SlaveListW.getInstance().getSWFullListe());
		if (MilkKind.checkThingKind(MilkKind.Slave_Humanoid, value)) lists.add(SlaveListH.getInstance().getSHFullListe());
		if (MilkKind.checkThingKind(MilkKind.Slave_Animal, value)) lists.add(SlaveListA.getInstance().getSAFullListe());
		if (MilkKind.checkThingKind(MilkKind.Animal, value)) lists.add(AnimalList.getInstance().getFullListe());
		
		return (ObservableList<? extends Thing>[]) lists.toArray(new ObservableList[lists.size()]);
	}
	
	/**
	 * return an Array of ObservableList depending of a Milkkind.
	 * */
	public static ObservableList<? extends Intel> getIntelsListsFromKind(MilkKind value) {
		ObservableList<? extends Intel> list = null;
		switch (value.getKind()) {
			case MilkKind.Research: {
					list = ResearchList.getInstance().getResearchListe();
				}
				break;
			case MilkKind.Upgrade: {
					list = UpgradeList.getInstance().getUpgradeListe();
				}
				break;
			case MilkKind.Synergy: {
					list = SynergyList.getInstance().getSynergyListe();
				}
				break;
			case MilkKind.Ascension: {
					list = AscensionList.getInstance().getAscensionListe();
				}
				break;
			case MilkKind.Event: {
					//list = EventList.getInstance().getEventListe();
				}
				break;
			default:
				break;
		}
		return list;
	}

	/**
	 * return an Intel depending of a NeededIntel.
	 * */
	public static Intel getIntelFromAgent(NeededIntel value) {
		Intel result = null;
    	for (Intel test : getIntelsListsFromKind(value.getKind())) {
			if(value.getId()!=null && value.getId().intValue()>0){
				if(value.getId().intValue()==test.getId().intValue() ){
					result = test;
					break;
				}
			} 
		}
		return result;
	}
	
	/**
	 * return an Array of ObservableList depending of a Milkkind.
	 * */
	public static MilkPricedObj getThingFromList(int id, List<? extends MilkPricedObj> list) {
		MilkPricedObj wanted = null;
		for (MilkPricedObj test : list) if(id==test.getId().intValue() ) wanted=test;
		return wanted;
	}

	/**
	 * return an Array of ObservableList depending of a Milkkind.
	 * */
	public static NeededThing getAgentFromThing(Thing value) {
		NeededThing thing = new NeededThing();
		thing.setKind(value.getKind().getKind());
		thing.setId(value.getId().intValue());
		return thing;
	}

	/**
	 * return an Array of Thing depending of a NeededThing.
	 * */
	public static ArrayList<? extends Thing> getThingsListsFromAgent(NeededThing value) {
		ArrayList<Thing> lists =  new ArrayList<Thing>(); 
        for (ObservableList<? extends Thing> tests : getThingsListsFromKind(value.getKind())) {
			for (Thing test : tests) {
				if(multiCheck(value, test)) lists.add(test);
			}
		}
		return lists;
	}
	
	/*
	 * Add a Quantity listener to things related to the agent.
	 * */
	public static void addQuantityListenerToThings(Agent value, QuantityListener listener) {
		addQuantityListenerToThingsLists(value, listener, getThingsListsFromKind(value.getKind()));
	}

	/*
	 * Add a Quantity listener to things related to the agent and contained in a array of ObservableList.
	 * */
	@SafeVarargs
	public static void addQuantityListenerToThingsLists(Agent value, QuantityListener listener, ObservableList<? extends Thing>... testLists) {
        for (ObservableList<? extends Thing> tests : testLists) {
			for (Thing test : tests) {
				if(multiCheck(value, test)) test.getQuantity().addQuantityListener(listener);
			}
		}
	}

	/*
	 * Get the quantity of things related to the agent.
	 * */
	public static int getThingsQuantity(Agent value) {
		return getThingsQuantityFromLists(value, getThingsListsFromKind(value.getKind()));
	}
	
	/*
	 * Get the quantity of things related to the agent and contained in a array of ObservableList.
	 * */
	@SafeVarargs
	public static int getThingsQuantityFromLists(Agent value, ObservableList<? extends Thing>... testLists) {
		int result = 0;
        for (ObservableList<? extends Thing> tests : testLists) {
			for (Thing test : tests) {
				if(multiCheck(value, test))  result += test.getQuantity().getQuant().intValue();
			}
		}
        return result;
	}

	/*
	 * Chaek if the tested thing have the desired value (id or Lvl/Tree).
	 * */
	public static boolean multiCheck(NeededThing value, Thing test) {
		boolean result = false;
		if(value.getId()!=null && value.getId().intValue()>0){
			if(value.getId().intValue()==test.getId().intValue() ) result = true;
		} else {
			if( (value.getLvl()==null || NeededThing.checkLevel(value, test) ) &&
					(value.getTree()==null || MilkTree.checkTree(value.getTree(), test) ) )  result = true;
		}
        return result;
	}

	/*
	 * Check if the Intel related to the NeededIntel is bought.
	 * *
	public static boolean checkIntel(NeededIntel value, ObservableList<? extends MilkPricedObj> milkPricedObjs) {
		return getIntelFromAgent(value).bought();
	}

	/*
	 * Check if the things related to the NeededThing is bought and in enough quantity.
	 * *
	@SafeVarargs
	public static boolean checkThing(NeededThing wanted, ObservableList<? extends Thing>... testLists) {
		int unit = 0;
        for (ObservableList<? extends Thing> tests : testLists) {
			for (Thing test : tests) {
				if(wanted.getId()!=null && wanted.getId().intValue()>0){
					if(wanted.getId().intValue()==test.getId().intValue() ) unit += test.getAttrib().getQuant().intValue();
				} else {
					if( (wanted.getLvl()==null || NeededThing.checkLevel(wanted, test) ) &&
							(wanted.getAttrib()==null || ThingAttrib.checkAttrib(wanted.getAttrib(), test) ) ) unit += test.getAttrib().getQuant().intValue();
				}
			}
		}
		return ( unit > wanted.getAttrib().getQuant().intValue() ) ? true : false;
	}*/

	public static boolean thatNeedaSacrifice(MilkXmlObj value) {
		boolean result = false;
		if ( (value instanceof Slave && ((Slave)value).getSacrifice().getQuantity().getQuant().intValue()>0 ) ||
				(value instanceof Research && ((Research)value).getSacrifice().getQuantity().getQuant().intValue()>0) ) result =true;
		return result;
	}
	
	public static boolean contain(int value, int... args) {
	    for (int arg : args) {
	        if (arg == value) {
	            return true;
	        }
	    }
	    return false;
	}
}