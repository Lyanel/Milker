package controleur;

import java.util.HashMap;

import javafx.beans.property.SimpleBooleanProperty;

public class ViewLock {
	public static final Integer SCI_TAB	= 2, MAG_TAB = 3, SLA_PAN = 4, 
					IDOL_PAN = 5, VOL_SYS = 6, MET_SYS = 7, WORK_OWN = 8,
					WOR_to_CAP = 11, HYB_to_CAP = 12, ANI_to_CAP = 13, CAP_to_HYB = 14, WOR_to_HYB = 15 ;
	
	private static HashMap<Integer, SimpleBooleanProperty> viewslist;
	
	private static void fill(){
		viewslist = new HashMap<>();
		viewslist.put(SCI_TAB,new SimpleBooleanProperty(false));
		viewslist.put(MAG_TAB,new SimpleBooleanProperty(false));
		viewslist.put(SLA_PAN,new SimpleBooleanProperty(false));
		viewslist.put(IDOL_PAN,new SimpleBooleanProperty(false));
		
		viewslist.put(VOL_SYS,new SimpleBooleanProperty(false));
		viewslist.put(MET_SYS,new SimpleBooleanProperty(false));
		viewslist.put(WORK_OWN,new SimpleBooleanProperty(false));
		
		viewslist.put(WOR_to_CAP,new SimpleBooleanProperty(false));
		viewslist.put(HYB_to_CAP,new SimpleBooleanProperty(false));
		viewslist.put(ANI_to_CAP,new SimpleBooleanProperty(false));
		viewslist.put(CAP_to_HYB,new SimpleBooleanProperty(false));
		viewslist.put(WOR_to_HYB,new SimpleBooleanProperty(false));
	}
	
	public static void unlock(int key){
		if(viewslist==null)fill();
		viewslist.get(key).set(true);
	}

	public static void lock(int key){
		if(viewslist==null)fill();
		viewslist.get(key).set(false);
	}

	public static SimpleBooleanProperty getBind(int key){
		if(viewslist==null)fill();
		return viewslist.get(key);
	}
	
	public static boolean isUnlocked(int key){
		if(viewslist==null)fill();
		return viewslist.get(key).getValue();
	}
	
	
}
