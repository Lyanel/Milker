package vue;

import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import modele.baseObject.MilkInterface;

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
    	menuEdit.disableProperty().setValue(true);
	}
    
	public void setText() {
	//	menuFile.setText(MilkInterface.getStringsFromId(10));
		menuFile.textProperty().bind( MilkInterface.getMilkStringsFromId(10).getText()  );
	//	menuNew.setText(MilkInterface.getStringsFromId(11));
		menuNew.textProperty().bind( MilkInterface.getMilkStringsFromId(11).getText()  );
	//	menuLoad.setText(MilkInterface.getStringsFromId(12));
		menuLoad.textProperty().bind( MilkInterface.getMilkStringsFromId(12).getText()  );
	//	menuSave.setText(MilkInterface.getStringsFromId(13));
		menuSave.textProperty().bind( MilkInterface.getMilkStringsFromId(13).getText()  );
	//	menuSaveAs.setText(MilkInterface.getStringsFromId(13)+" "+MilkInterface.getStringsFromId(16));
		menuSaveAs.textProperty().bind( Bindings.concat(MilkInterface.getMilkStringsFromId(13).getText(), " ", MilkInterface.getMilkStringsFromId(16).getText() ) );
	//	menuEdit.setText(MilkInterface.getStringsFromId(4)+" - "+MilkInterface.getStringsFromId(5));
		menuEdit.textProperty().bind( Bindings.concat(MilkInterface.getMilkStringsFromId(4).getText(), " - ", MilkInterface.getMilkStringsFromId(5).getText() ) );
	//	menuExit.setText(MilkInterface.getStringsFromId(15));
		menuExit.textProperty().bind( MilkInterface.getMilkStringsFromId(15).getText()  );

	//	menuDisplay.setText(MilkInterface.getStringsFromId(20));
		menuDisplay.textProperty().bind( MilkInterface.getMilkStringsFromId(20).getText()  );
	//	menuFullScreen.setText(MilkInterface.getStringsFromId(21));
		menuFullScreen.textProperty().bind( MilkInterface.getMilkStringsFromId(21).getText()  );
	//	menuStatutBar.setText(MilkInterface.getStringsFromId(22));
		menuStatutBar.textProperty().bind( MilkInterface.getMilkStringsFromId(22).getText()  );

	//	menuHelp.setText(MilkInterface.getStringsFromId(30));
		menuHelp.textProperty().bind( MilkInterface.getMilkStringsFromId(30).getText()  );
	//	menuOption.setText(MilkInterface.getStringsFromId(31));
		menuOption.textProperty().bind( MilkInterface.getMilkStringsFromId(31).getText()  );
	//	menuAbout.setText(MilkInterface.getStringsFromId(32));
		menuAbout.textProperty().bind( MilkInterface.getMilkStringsFromId(32).getText()  );
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
    	//this.getMainApp().openEditor();
    }

	/**
     * Closes the application.
     */
    @FXML
    private void handleExit() {
    	this.getMainApp().close(null);
    }
    
    /**
     * Switch to full screen view.
     */
    @FXML
    private void handleFullScreen() {
    	if(this.getMainApp().getPrimaryStage().isFullScreen()){
    		this.getMainApp().getPrimaryStage().setFullScreen(false);
    		menuFullScreen.setSelected(false);;
    	} else {
    		this.getMainApp().getPrimaryStage().setFullScreen(true);
    		menuFullScreen.setSelected(true);;
    	}
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
    	this.getMainApp().openOption();
    }

    /**
     * Opens an about window.
     */
    @FXML
    private void handleAbout() {

    }
}