package modele;

import java.util.Locale;
import java.util.ArrayList;

import org.w3c.dom.Element;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class MilkInterface extends MilkLanguage implements Cloneable {
	
	public static final String file			= "Interface";
	
	private static ArrayList<MilkLanguage> languages = setMilkLanguagesFromFiles();
	private static MilkLanguage language;
	private static ArrayList<MilkString> languagesString;

	
	private static ArrayList<MilkLanguage> setMilkLanguagesFromFiles() {
		if (languages==null) languages = new ArrayList<MilkLanguage>();
		else languages.clear();
		ArrayList<Element> elementlist = new ArrayList<Element>();
		elementlist = MilkFile.getMilkElementsFromFiles(getXmlFilePath(file)+file, noeud);
		languages = MilkLanguage.getMilkLanguageList(elementlist);
		changeLanguage(Locale.getDefault().getLanguage());
		if(language==null) changeLanguage(11);
		return languages;
	}
	
	private static void setLanguageStrings() {
		if (languagesString==null) languagesString = new ArrayList<MilkString>();
		else languagesString.clear();
		ArrayList<Element> elementlist = new ArrayList<Element>();
		try {
			elementlist = MilkFile.getMilkElementsFromFiles(getXmlFilePath(file)+language.getPath()+"/"+file,MilkString.noeud);
			languagesString = MilkString.getMilkVarList(elementlist);
		} catch (Exception e) {e.printStackTrace();}
	}

	public static ObservableList<MilkLanguage> getListes() {
		if (languages==null)setMilkLanguagesFromFiles();
		ObservableList<MilkLanguage> clone = FXCollections.observableArrayList();
		if (languages!=null){
			for (MilkLanguage temp:languages){
				try {
					clone.add((MilkLanguage) temp.clone());
				} catch (CloneNotSupportedException e) {}
			}
		}
		return clone;
	}

	
	public static void setLanguage(MilkLanguage newlanguage) {
		if(language != newlanguage){
			language = newlanguage;
			setLanguageStrings();
		}
	}
	
	public static void changeLanguage(int id) {
		if(language==null || language.getId() != id){
			for (MilkLanguage tlanguage : languages) {
				if(id == tlanguage.getId()) setLanguage(tlanguage);
			}
		}
	}
	
	public static void changeLanguage(String iso) {
		if(language==null || !language.getIso().matches(iso)){
			for (MilkLanguage tlanguage : languages) {
				if(iso.equalsIgnoreCase(tlanguage.getIso())) setLanguage(tlanguage);
			}
		}
	}
	
	public static String getStringsFromId(int id) {
		String temp ="";
		for (MilkString milkString : languagesString) {
			if(id == milkString.getId()) temp = milkString.getText();
		}
		return temp;
	}

	public static String getXmlLangPath() {
		return xmlBasePath+language.getPath()+"/";
	}

	public static MilkLanguage getMilkLanguage() {
		// TODO Auto-generated method stub
		return language;
	}
}