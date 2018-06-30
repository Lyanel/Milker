package modele;

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
import modele.baseObject.MilkXmlObj;
import modele.carac.Agent;
import modele.carac.NeededIntel;
import modele.carac.NeededThing;
import modele.carac.QuantityListener;
import modele.carac.ThingAttrib;
import modele.intel.Intel;
import modele.intel.Research;
import modele.thing.Animal;
import modele.thing.Building;
import modele.thing.Slave;
import modele.thing.SlaveAnimal;
import modele.thing.SlaveHuman;
import modele.thing.Thing;
import modele.thing.Worker;

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
		int val = value.getKind().intValue();
		if (val == MilkKind.kind_Building) lists.add(Building.getFullListe());
		
		if (contain(val, MilkKind.kind_Worker, MilkKind.kind_Semi_Human, MilkKind.kind_Earthling_People, 
				MilkKind.kind_People, MilkKind.kind_Earthling_Being, MilkKind.kind_Natural_Being, MilkKind.kind_Living_Being)) lists.add(Worker.getFullListe());
		
		if (contain(val, MilkKind.kind_Slave_Human, MilkKind.kind_Semi_Human, MilkKind.kind_Slaves, MilkKind.kind_Natural_Cattle, 
				MilkKind.kind_People, MilkKind.kind_Cattle, MilkKind.kind_Natural_Being, MilkKind.kind_Living_Being)) lists.add(SlaveHuman.getFullListe());
		
		if (contain(val, MilkKind.kind_Slave_Animal, MilkKind.kind_Slaves, MilkKind.kind_Semi_Animal, MilkKind.kind_Earthling_People, 
				MilkKind.kind_People, MilkKind.kind_Cattle, MilkKind.kind_Earthling_Being, MilkKind.kind_Living_Being)) lists.add(SlaveAnimal.getFullListe());
		
		if (contain(val, MilkKind.kind_Animal, MilkKind.kind_Semi_Animal, MilkKind.kind_Natural_Cattle, 
				MilkKind.kind_Cattle, MilkKind.kind_Earthling_Being, MilkKind.kind_Natural_Being, MilkKind.kind_Living_Being)) lists.add(Animal.getFullListe());
		
		return (ObservableList<? extends Thing>[]) lists.toArray(new ObservableList[lists.size()]);
	}

	/**
	 * return an Array of ObservableList depending of a Milkkind.
	 * */
	public static ArrayList<? extends Thing> getThingsListsFromAgent(NeededThing value) {
		
		ArrayList<Thing> lists =  new ArrayList<Thing>(); 
		
        for (ObservableList<? extends Thing> tests : getThingsListsFromKind(value.getKind())) {
			for (Thing test : tests) {
				if(value.getId()!=null && value.getId().intValue()>0){
					if(value.getId().intValue()==test.getId().intValue() ) lists.add(test);
				} else {
					if( (value.getLvl()==null || NeededThing.checkLevel(value, test) ) &&
							(value.getAttrib()==null || ThingAttrib.checkAttrib(value.getAttrib(), test) ) ) lists.add(test);
				}
			}
		}
		return lists;
	}
	
	public static boolean contain(int value, int... args) {
	    for (int arg : args) {
	        if (arg == value) {
	            return true;
	        }
	    }
	    return false;
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
				if(value.getId()!=null && value.getId().intValue()>0){
					if(value.getId().intValue()==test.getId().intValue() ) test.getAttrib().addQuantityListener(listener);
				} else {
					if( (value.getLvl()==null || NeededThing.checkLevel(value, test) ) &&
							(value.getAttrib()==null || ThingAttrib.checkAttrib(value.getAttrib(), test) ) ) test.getAttrib().addQuantityListener(listener);
				}
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
				if(value.getId()!=null && value.getId().intValue()>0){
					if(value.getId().intValue()==test.getId().intValue() ) result += test.getAttrib().getQuant().intValue();
				} else {
					if( (value.getLvl()==null || NeededThing.checkLevel(value, test) ) &&
							(value.getAttrib()==null || ThingAttrib.checkAttrib(value.getAttrib(), test) ) )  result += test.getAttrib().getQuant().intValue();
				}
			}
		}
        return result;
	}

	/*
	 * Check if the Intel related to the NeededIntel is bought.
	 * */
	public static boolean checkIntel(NeededIntel value, ObservableList<? extends Intel> intels) {
		boolean bought = true;
		for (Intel intel : intels) {
			if ( intel.getId().intValue()==value.getId().intValue() && !intel.bought() ) bought=false;
		}
		return bought;
	}

	/*
	 * Check if the things related to the NeededThing is bought and in enough quantity.
	 * */
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
	}

	public static boolean thatNeedaSacrifice(MilkXmlObj value) {
		boolean result = false;
		if ( (value instanceof Slave && ((Slave)value).getSacrifice().getKind().getKind().intValue()!=0 ) ||
				(value instanceof Research && ((Research)value).getSacrifice().getKind().getKind().intValue()!=0) ) result =true;
		return result;
	}
}