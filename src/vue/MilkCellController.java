package vue;

import java.net.URL;
import java.util.ResourceBundle;

import application.Milker;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import modele.MilkInfo;
import modele.MilkXmlObj;

/**
 * The controller for the Menu Bar.  
 * @author Lyanel Pheles
 */
public class MilkCellController extends MilkBoxController implements Initializable {

    @FXML
    protected AnchorPane rootPane;
    @FXML
    protected GridPane textPan;

    @FXML
    protected GridPane varPan;
    @FXML
    protected Label iLabel;
    @FXML
    protected Label nameLabel;

	public MilkCellController() {}

    /**
     * Initialisation du contr�leur.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	iLabel.setText(null);
    	nameLabel.setText(null);
    	iLabel.setOnMousePressed(event -> showThing());
    	textPan.setOnMousePressed(event -> buyThing());
    	varPan.setOnMouseExited(event -> setInfoVisible (null,false));
    }

    /**
    * Cet �couteur est appel� lorsque la propri�t� value change.
    *
    private final ChangeListener<MilkXmlObj> valueChangeListener = (ObservableValue<? extends MilkXmlObj> observableValue, MilkXmlObj oldValue, MilkXmlObj newValue) -> {
        updateUI(newValue);
    };*/

    protected void updateUI(MilkXmlObj milkXmlObj) {
    	if(((Milker) getApplication()).getModel().isMilkObjVisible(milkXmlObj) ){
    		rootPane.setVisible(true);
    		if(milkXmlObj.getIcon().getName()=="") iLabel.setText(milkXmlObj.getStringId());
    		else {
    			milkXmlObj.getIcon().setAsIcon();
    			iLabel.setGraphic(milkXmlObj.getIcon().getImageView());
    		}
            nameLabel.setText(milkXmlObj.getInfo().getName());
    	} else rootPane.setVisible(false);
    }

    public void showThing() {}

    public void buyThing() {}

    public void setInfoVisible(MilkInfo value, boolean visibility) {
    	((Milker) getApplication()).setInfoPanVisible(value,visibility);
    }
}
