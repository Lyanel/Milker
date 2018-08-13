package modele.intel;

import modele.baseObject.MilkVarList;

import java.util.ArrayList;

import org.w3c.dom.Element;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.util.Callback;


public class EventList extends MilkVarList {

	private static EventList INSTANCE = null;
	private static final String file	= "Event", noeud	= "event";
	public String getFile() {return file;}
	public String getNoeud() {return noeud;}

	public static EventList getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new EventList();
        }
        return INSTANCE;
    }

	public static Callback<Event, Observable[]> extractor() {
        return (Event p) -> new Observable[]{};
	}
	
	// Fields

	private static ArrayList<Event> events;
	private static ObservableList<Event> modelListe;

	// Constructors
	
	private EventList() {
		super();
		setMilkVarFromFiles();
	}

	// field methods
	
	private ArrayList<Event> setMilkVarFromFiles() {
		if (events==null) events = new ArrayList<Event>();
		else events.clear();
		events = getMilkVarList(getElementStat());
		return events;
	}
	
	private ArrayList<Event> getMilkVarList(ArrayList<Element> elementlist) {
		ArrayList<Event> events = new ArrayList<Event>();
		for (Element elementMilk: elementlist) {
			Event event = new Event(elementMilk);
			events.add(event);
		}
		return events;
	}
	
	public ObservableList<Event> getEventListe() {
		if (modelListe==null) setFullListe();
		return modelListe;
	}
	
	public void setFullListe() {
		if (events==null)setMilkVarFromFiles();
		modelListe = FXCollections.observableArrayList(extractor());
		if (events!=null){
			for (Event event:events){
				modelListe.add(new Event(event));
			}
		}
	}

	// other methods

	public void changeByID(int id) {
		if (modelListe==null) setFullListe();
		for (Event event: modelListe) {
			if (id==event.getId().intValue()) event.setActive(true);
			else event.setActive(false);
		}
	}
	
	public void changeByDate(int month) {
		if (modelListe==null) setFullListe();
		for (Event event: modelListe) {
			if (month==event.getMonth().intValue()) event.setActive(true);
			else event.setActive(false);
		}
	}
	public Event getActiveEvent() {
		Event result = null;
		if (modelListe==null) setFullListe();
		for (Event event: modelListe) {
			if (event.isActive()) result = event;
		}
		return result;
	}
}