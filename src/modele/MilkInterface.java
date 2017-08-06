package modele;

import java.util.Vector;

import org.w3c.dom.Element;

import controleur.ParseMilkFile;

public class MilkInterface extends MilkFile implements Cloneable {
	
	public static final String file			= "Interface";
	public static final String noeud		= "language";
	public String getNoeud() {return noeud;}
	
	private static Vector<MilkFile> languages = setMilkLanguagesFromFiles();
	private static MilkFile language;
	private static Vector<MilkString> languagesString;

	
	private static Vector<MilkFile> setMilkLanguagesFromFiles() {
		if (languages==null) languages = new Vector<MilkFile>();
		else languages.removeAllElements();
		Vector<Element> elementlist = new Vector<Element>();
		elementlist = getMilkElementsFromFiles(getXmlFilePath(file)+file, noeud);
		languages = getMilkVarList(elementlist);
		language = languages.get(1);
		setLanguageStrings();
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
	
	public static void setLanguage(MilkFile newlanguage) {
		if(language != newlanguage){
			language = newlanguage;
			setLanguageStrings();
		}
	}
	
	public static void changeLanguage(int id) {
		if(language.getId() != id){
			for (MilkFile tlanguage : languages) {
				if(id == tlanguage.getId()) {
					language = tlanguage;
					setLanguageStrings();
				}
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
}