package vue.editor;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import modele.baseObject.MilkInterface;
import vue.MilkerController;

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
		reset.setText(MilkInterface.getStringsFromId(113)+" "+MilkInterface.getStringsFromId(114));
		resetXml.setText(MilkInterface.getStringsFromId(113)+" "+MilkInterface.getStringsFromId(5));
		save.setText(MilkInterface.getStringsFromId(13)+" "+MilkInterface.getStringsFromId(114));
		saveXml.setText(MilkInterface.getStringsFromId(13)+" "+MilkInterface.getStringsFromId(5));
    }

    /**
     */
    @FXML
    private void handleAdd() {

    }

    /**
     */
    @FXML
    private void handleEdit() {
    }

    /**
     */
    @FXML
    private void handleDel() {
    }
    
    /**
     */
    @FXML
    private void handleResetModel() {

    }

    /**
     */
    @FXML
    private void handleResetXml() {

    }

    /**
     */
    @FXML
    private void handleSaveModel() {

    }

    /**
     */
    @FXML
    private void handleSaveXml() {

    }

}
