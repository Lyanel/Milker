package modele;

public class MilkRs {

	public static final String LIGNE_BREAK	= System.getProperty("line.separator");
	public static final String LIGNE_TAB	= "\t";
	public static final String DATE_FORMAT	= "HH:mm:ss";
	public static final String ISO_PASS		= "UTF-8";
	public static final String ALGO			= "Blowfish";
	public static final String CRYPT_PASS	= "Milk@Paradis.Vns";

	// fxml file list.
	
	public static final String fxBasePath	= "/vue/fxml/";
	public static final String milker		= fxBasePath+"Milker.fxml";
	public static final String milkMenu		= fxBasePath+"MilkMenu.fxml";
	public static final String milkStatut	= fxBasePath+"MilkStatut.fxml";
	public static final String milkOption	= fxBasePath+"MilkOption.fxml";
	
	public static final String fxGamePath	= fxBasePath+"game/";
	public static final String milkGame		= fxGamePath+"MilkGame.fxml";
	public static final String infoCell		= fxGamePath+"InfoCell.fxml";
	public static final String milkCellIntel	= fxGamePath+"MilkCellIntel.fxml";
	public static final String milkCellThing	= fxGamePath+"MilkCellThing.fxml";
	public static final String milkCellOption	= fxGamePath+"MilkCellOption.fxml";
	public static final String milkCellOptLvl	= fxGamePath+"MilkCellOptLvl.fxml";
	public static final String milkTitledCell	= fxGamePath+"MilkTitledCell.fxml";
	
	public static final String fxEditorPath	= fxBasePath+"editor/";
	public static final String milkEditor	= fxEditorPath+"MilkEditor.fxml";
	public static final String buildingPan	= fxEditorPath+"BuildingTab.fxml";
	public static final String workerPan	= fxEditorPath+"WorkerTab.fxml";
	public static final String slaveHPan	= fxEditorPath+"SlaveHTab.fxml";
	public static final String slaveAPan	= fxEditorPath+"SlaveATab.fxml";
	public static final String animalPan	= fxEditorPath+"AnimalTab.fxml";
	public static final String researchPan	= fxEditorPath+"ResearchTab.fxml";
	public static final String upgradePan	= fxEditorPath+"UpgradeTab.fxml";
	public static final String synergyPan	= fxEditorPath+"SynergyTab.fxml";
	public static final String ascensionPan	= fxEditorPath+"AscensionTab.fxml";
	public static final String eventPan		= fxEditorPath+"EventTab.fxml";

	// CSS.

	public static final String cssBasePath	= "CSS/";
	public static final String cssNotBuyable= "NotBuyable";
	public static final String cssBought	= "Bought";
	public static final String cssTooMuch	= "TooMuch";
}
