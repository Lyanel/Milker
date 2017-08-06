package vue;

import javafx.fxml.FXML;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import modele.MilkInterface;

/**
 * The controller for the Menu Bar.  
 * @author Lyanel Pheles
 */
public class MilkMenuController extends MilkerController {

    @FXML
    private Menu menuFile;
    @FXML
    private MenuItem menuNew;
    @FXML
    private MenuItem menuLoad;
    @FXML
    private MenuItem menuSave;
    @FXML
    private MenuItem menuSaveAs;
    @FXML
    private MenuItem menuEdit;
    @FXML
    private MenuItem menuExit;

    @FXML
    private Menu menuDisplay;
    @FXML
    private CheckMenuItem menuFullScreen;
    @FXML
    private CheckMenuItem menuStatutBar;

    @FXML
    private Menu menuHelp;
    @FXML
    private MenuItem menuOption;
    @FXML
    private MenuItem menuAbout;
    
    @FXML
	public void initialize() {
    	setText();
	}
    
	public void setText() {
		menuFile.setText(MilkInterface.getStringsFromId(10));
		menuNew.setText(MilkInterface.getStringsFromId(11));
		menuLoad.setText(MilkInterface.getStringsFromId(12));
		menuSave.setText(MilkInterface.getStringsFromId(13));
		menuSaveAs.setText(MilkInterface.getStringsFromId(14));
		menuEdit.setText(MilkInterface.getStringsFromId(2));
		menuExit.setText(MilkInterface.getStringsFromId(15));

		menuDisplay.setText(MilkInterface.getStringsFromId(20));
		menuFullScreen.setText(MilkInterface.getStringsFromId(21));
		menuStatutBar.setText(MilkInterface.getStringsFromId(22));

		menuHelp.setText(MilkInterface.getStringsFromId(30));
		menuOption.setText(MilkInterface.getStringsFromId(31));
		menuAbout.setText(MilkInterface.getStringsFromId(32));
    }

    /**
     * Launch a new game.
     */
    @FXML
    private void handleNew() {
    	this.getMainApp().openGame();
    }

    /**
     * Opens a FileChooser to let the user select an old saved game.
     */
    @FXML
    private void handleOpen() {

    }

    /**
     * Saves the game to the current save file. 
     * If there is no save file, the "save as" dialog is shown.
     */
    @FXML
    private void handleSave() {

    }

    /**
     * Opens a FileChooser to let the user select a file to save to.
     */
    @FXML
    private void handleSaveAs() {

    }
    
    /**
     * Opens the editor.
     */
    @FXML
    private void handleEdit() {
    	this.getMainApp().openEditor();
    }

	/**
     * Closes the application.
     */
    @FXML
    private void handleExit() {
        System.exit(0);
    }
    
    /**
     * Switch to full screen view.
     */
    @FXML
    private void handleFullScreen() {
    	
    }
    
    /**
     * Show the Statut bar.
     */
    @FXML
    private void handleShowStatut() {
    	
    }
    
    /**
     * Opens an option window.
     */
    @FXML
    private void handleOption() {
    	
    }

    /**
     * Opens an about window.
     */
    @FXML
    private void handleAbout() {

    }
}