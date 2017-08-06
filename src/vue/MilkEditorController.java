package vue;

import java.io.IOException;

import application.Milker;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import modele.MilkInterface;
import modele.MilkRs;

/**
 * The controller for the Menu Bar.  
 * @author Lyanel Pheles
 */
public class MilkEditorController extends MilkTabControleur {

    // Reference to the main application
    private Milker milker;

    @FXML
    private TabPane editor;
    @FXML
    private Tab buildingTab;
    @FXML
    private Tab workerTab;
    @FXML
    private Tab slaveHTab;
    @FXML
    private Tab slaveATab;
    @FXML
    private Tab animalTab;
    @FXML
    private Tab researchTab;
    @FXML
    private Tab upgradeTab;
    @FXML
    private Tab synergyTab;
    @FXML
    private Tab ascensionTab;
    @FXML
    private Tab eventTab;
    
    @FXML
	public void initialize() {
    	setText();
    	editor.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> loadPanel(newValue));
    	loadPanel(editor.getSelectionModel().getSelectedItem());

	}
    
	public void loadPanel(Tab newValue) {
		if(newValue==buildingTab)initPanel(buildingTab,MilkRs.buildingPan);
		if(newValue==workerTab)initPanel(workerTab,MilkRs.workerPan);
		if(newValue==slaveHTab)initPanel(slaveHTab,MilkRs.slaveHPan);
		if(newValue==slaveATab)initPanel(slaveATab,MilkRs.slaveAPan);
		if(newValue==animalTab)initPanel(animalTab,MilkRs.animalPan);
		if(newValue==researchTab)initPanel(researchTab,MilkRs.researchPan);
		if(newValue==upgradeTab)initPanel(upgradeTab,MilkRs.upgradePan);
		if(newValue==synergyTab)initPanel(synergyTab,MilkRs.synergyPan);
	//	if(newValue==ascensionTab)initPanel(ascensionTab,MilkRs.ascensionPan);
	//	if(newValue==eventTab)initPanel(eventTab,MilkRs.eventPan);
    }

    /**
     * Initializes selected panel
     * @author Lyanel Pheles
     * @param fileTab 
     * @param selectedTab 
     */
    public void initPanel(Tab selectedTab, String fileTab) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Milker.class.getResource(fileTab));
            AnchorPane tabContent = (AnchorPane) loader.load();
            selectedTab.setContent(tabContent);
            
            MilkTabControleur controller = loader.getController();
            controller.setMainApp(milker);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
    
	public void setText() {
		buildingTab.setText(MilkInterface.getStringsFromId(501));
		workerTab.setText(MilkInterface.getStringsFromId(502));
		slaveHTab.setText(MilkInterface.getStringsFromId(503)+" - "+MilkInterface.getStringsFromId(504));
		slaveATab.setText(MilkInterface.getStringsFromId(503)+" - "+MilkInterface.getStringsFromId(505));
		animalTab.setText(MilkInterface.getStringsFromId(505));

		researchTab.setText(MilkInterface.getStringsFromId(551));
		upgradeTab.setText(MilkInterface.getStringsFromId(552));
		synergyTab.setText(MilkInterface.getStringsFromId(553));
		ascensionTab.setText(MilkInterface.getStringsFromId(554));
		eventTab.setText(MilkInterface.getStringsFromId(555));
    }
	
    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param milker
     */
    public void setMainApp(Milker milker) {
        this.milker = milker;
    }
}