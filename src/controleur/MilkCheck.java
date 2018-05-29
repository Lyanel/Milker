package controleur;

import javafx.collections.ObservableList;
import modele.carac.NeededIntel;
import modele.carac.NeededThing;
import modele.carac.ThingAttrib;
import modele.intel.Intel;
import modele.thing.Thing;

public class MilkCheck{

	public static boolean checkIntel(NeededIntel value, ObservableList<? extends Intel> intels) {
		boolean bought = true;
		for (Intel intel : intels) {
			if ( intel.getId().intValue()==value.getId().intValue() && !intel.bought() ) bought=false;
		}
		return bought;
	}
	
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
}