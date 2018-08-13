package vue;

import java.io.File;
import java.util.Optional;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.stage.FileChooser;
import modele.ParseMilkFile;
import modele.Securite;
import modele.baseObject.MilkInterface;

/**
 * The controller for the Menu Bar.  
 * @author Lyanel Pheles
 */
public class MilkMenuController extends MilkerController {

    private String saveFile;
    
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
		menuFile.textProperty().bind( MilkInterface.getMilkStringsFromId(10).getText()  );
		menuNew.textProperty().bind( MilkInterface.getMilkStringsFromId(11).getText()  );
		menuLoad.textProperty().bind( MilkInterface.getMilkStringsFromId(12).getText()  );
		menuSave.textProperty().bind( MilkInterface.getMilkStringsFromId(13).getText()  );
		menuSaveAs.textProperty().bind( Bindings.concat(MilkInterface.getMilkStringsFromId(13).getText(), " ", MilkInterface.getMilkStringsFromId(16).getText() ) );
		menuEdit.textProperty().bind( Bindings.concat(MilkInterface.getMilkStringsFromId(4).getText(), " - ", MilkInterface.getMilkStringsFromId(5).getText() ) );
		menuExit.textProperty().bind( MilkInterface.getMilkStringsFromId(15).getText()  );

		menuDisplay.textProperty().bind( MilkInterface.getMilkStringsFromId(20).getText()  );
		menuFullScreen.textProperty().bind( MilkInterface.getMilkStringsFromId(21).getText()  );
		menuStatutBar.textProperty().bind( MilkInterface.getMilkStringsFromId(22).getText()  );

		menuHelp.textProperty().bind( MilkInterface.getMilkStringsFromId(30).getText()  );
		menuOption.textProperty().bind( MilkInterface.getMilkStringsFromId(31).getText()  );
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
    	File file = getFileChooser().showOpenDialog(this.getMainApp().getPrimaryStage());
        if(file != null){
        	byte[] data = null;
	    	this.getMainApp().openGame();
			try {
				data = Securite.ouvrirFichier(file.getAbsolutePath());
	            data = Securite.decompress(data);
	            data = Securite.decrypter(data);
				Document dataxml = ParseMilkFile.getXmlDocumentFromByteArray(data);
				Element racine= dataxml.getDocumentElement();
				this.getMainApp().getModel().loadSave(racine);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
        }
    }

    /**
     * Saves the game to the current save file. 
     * If there is no save file, the "save as" dialog is shown.
     */
    @FXML
    private void handleSave() {
    	if(saveFile!=null){
    		Alert alert = new Alert(AlertType.CONFIRMATION);
	  //  	alert.setTitle(titleTxt);
	   // 	String s = "Confirm to clear text in text field !";
	   // 	alert.setContentText(s);
	
	    	Optional<ButtonType> result = alert.showAndWait();
	
	    	if ((result.isPresent()) && (result.get() == ButtonType.OK)) save();
    	}
    	else handleSaveAs();
    }

    /**
     * Opens a FileChooser to let the user select a file to save to.
     */
    @FXML
    private void handleSaveAs() {
    	File file = getFileChooser().showSaveDialog(this.getMainApp().getPrimaryStage());
        if(file != null){
            saveFile = file.getAbsolutePath();
        	save();
        }
    }

    private FileChooser getFileChooser() {
    	FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Milk Save files (*.MiSa)", "*.MiSa");
        fileChooser.getExtensionFilters().add(extFilter);
        return fileChooser;
	}

    private void save() {
        String save = this.getMainApp().getModel().getSave();
        byte[] data;
		try {
			Document dataxml = ParseMilkFile.getXmlDocumentFromString(save);
			data = ParseMilkFile.getByteArrayFromXmlDocument(dataxml);
            data = Securite.crypter(data) ;
            data = Securite.compress(data) ;
            Securite.sauverFichier(saveFile, data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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