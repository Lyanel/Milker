package modele;

import java.util.Locale;
import java.util.Vector;

import org.w3c.dom.Element;

import controleur.ParseMilkFile;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import modele.thing.Animal;

public class MilkInterface extends MilkLanguage implements Cloneable {
	
	public static final String file			= "Interface";
	
	private static Vector<MilkLanguage> languages = setMilkLanguagesFromFiles();
	private static MilkLanguage language;
	private static Vector<MilkString> languagesString;

	
	private static Vector<MilkLanguage> setMilkLanguagesFromFiles() {
		if (languages==null) languages = new Vector<MilkLanguage>();
		else languages.removeAllElements();
		Vector<Element> elementlist = new Vector<Element>();
		elementlist = getMilkElementsFromFiles(getXmlFilePath(file)+file, noeud);
		languages = getMilkLanguageList(elementlist);
		changeLanguage(Locale.getDefault().getLanguage());
		if(language==null) changeLanguage(11);
		return languages;
	}
	
	private static void setLanguageStrings() {
		if (languagesString==null) languagesString = new Vector<MilkString>();
		else languagesString.removeAllElements();
		Vector<Element> elementlist = new Vector<Element>();
		try {
			elementlist = ParseMilkFile.getMilkElementLists(getXmlFilePath(file)+language.getPath()+"/"+file,MilkString.noeud);
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
				} catch (CloneNotSupportedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
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