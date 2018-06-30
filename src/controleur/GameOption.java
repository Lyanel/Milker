package controleur;

import java.io.File;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import modele.MilkRs;
import modele.baseObject.MilkInterface;
import modele.baseObject.MilkLanguage;
import modele.intel.Research;
import modele.intel.Synergy;
import modele.intel.Upgrade;
import modele.thing.Animal;
import modele.thing.Building;
import modele.thing.SlaveAnimal;
import modele.thing.SlaveHuman;
import modele.thing.SlaveWorker;
import modele.thing.Worker;
import modele.toggle.ToggleEvent;
import modele.toggle.ToggleIdol;
import modele.toggle.ToggleTool;

public class GameOption {
	
	private static GameOption option = new GameOption();
	private static ObservableList<String> cssList;
	
	/** Point d'accès pour l'instance unique du singleton */
	public static GameOption getOption()
	{	return option;
	}
	
	public static ObservableList<String> getCssLists() {
		if(cssList==null||cssList.isEmpty()){
	    	File folder = new File(MilkRs.cssBasePath);
	    	File[] listOfFiles = folder.listFiles();
	    	cssList = FXCollections.observableArrayList();
		    for (int i = 0; i < listOfFiles.length; i++) {
	    		if (listOfFiles[i].isFile()) cssList.add(listOfFiles[i].getName());
	    	}
		}
		return cssList;
	}
	
	public static String getCssUrl(String cssName) {
		File cssfile = new File(MilkRs.cssBasePath+cssName);
		return "file:///" + cssfile.getAbsolutePath().replace("\\", "/");
	}
	
	private MilkLanguage langue;
	private String css;

	
	private GameOption(){}
	
	
	
	public MilkLanguage getLanguage() {
		return langue;
	}

	public void setLanguage(MilkLanguage langue) {
		this.langue = langue;
		MilkInterface.setLanguage(langue);
		//Intels
		Research.updateInfoFromFiles();
		Synergy.updateInfoFromFiles();
		Upgrade.updateInfoFromFiles();
		//Things
		Animal.updateInfoFromFiles();
		Building.updateInfoFromFiles();
		SlaveAnimal.updateInfoFromFiles();
		SlaveHuman.updateInfoFromFiles();
	//	SlaveWorker.updateInfoFromFiles();
		Worker.updateInfoFromFiles();
		//Toggles
		ToggleTool.updateInfoFromFiles();
		ToggleIdol.updateInfoFromFiles();
		ToggleEvent.updateInfoFromFiles();
	}

	public String getCss() {
		if(css==null) css = getCssLists().get(0);
		return css;
	}
	
	public void setCss(String css) {
		this.css = css;
	}	
}
