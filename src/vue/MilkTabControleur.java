package vue;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import modele.MilkInterface;

public class MilkTabControleur extends MilkerController {

    @FXML
    private Button add;
    @FXML
    private Button edit;
    @FXML
    private Button del;
    @FXML
    private Button reset;
    @FXML
    private Button resetXml;
    @FXML
    private Button save;
    @FXML
    private Button saveXml;
    
	public void setText() {
		// Button
		add.setText(MilkInterface.getStringsFromId(110));
		edit.setText(MilkInterface.getStringsFromId(111));
		del.setText(MilkInterface.getStringsFromId(112));
		reset.setText(MilkInterface.getStringsFromId(113));
		resetXml.setText(MilkInterface.getStringsFromId(114));
		save.setText(MilkInterface.getStringsFromId(115));
		saveXml.setText(MilkInterface.getStringsFromId(116));
    }

    /**
     * Launch a new game.
     */
    @FXML
    private void handleAdd() {

    }

    /**
     * Opens the editor.
     */
    @FXML
    private void handleEdit() {
    }

    /**
     * Opens the editor.
     */
    @FXML
    private void handleDel() {
    }
    
    /**
     * Saves the game to the current save file. 
     * If there is no save file, the "save as" dialog is shown.
     */
    @FXML
    private void handleResetModel() {

    }

    /**
     * Saves the game to the current save file. 
     * If there is no save file, the "save as" dialog is shown.
     */
    @FXML
    private void handleResetXml() {

    }

    /**
     * Saves the game to the current save file. 
     * If there is no save file, the "save as" dialog is shown.
     */
    @FXML
    private void handleSaveModel() {

    }

    /**
     * Saves the game to the current save file. 
     * If there is no save file, the "save as" dialog is shown.
     */
    @FXML
    private void handleSaveXml() {

    }

}
