package vue;

import java.net.URL;
import java.util.ResourceBundle;

import application.Milker;
import javafx.application.Application;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import modele.MilkXmlObj;

/**
 * The controller for the Menu Bar.  
 * @author Lyanel Pheles
 */
public class MilkCellController implements Initializable {

    @FXML
    private AnchorPane rootPane;

    @FXML
    private GridPane varPan;
    @FXML
    private Label iLabel;
    @FXML
    private GridPane textPan;
    @FXML
    private Label nameLabel;

    public MilkCellController() {
        valueProperty().addListener(valueChangeListener);
    }

    /**
     * Initialisation du contrôleur.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	iLabel.setText(null);
    	nameLabel.setText(null);
    }

    /**
    * Cet écouteur est appelé lorsque la propriété value change.
    */
    private final ChangeListener<MilkXmlObj> valueChangeListener = (ObservableValue<? extends MilkXmlObj> observableValue, MilkXmlObj oldValue, MilkXmlObj newValue) -> {
        updateUI(newValue);
    };

    private void updateUI(MilkXmlObj milkXmlObj) {
    	if(((Milker) getApplication()).getModel().isThingVisible(milkXmlObj) ){
    		rootPane.setVisible(true);
    		if(milkXmlObj.getIcon().getName()=="") iLabel.setText(milkXmlObj.getStringId());
    		else {
    			milkXmlObj.getIcon().setAsIcon();
    			iLabel.setGraphic(milkXmlObj.getIcon().getImageView());
    		}
            nameLabel.setText(milkXmlObj.getInfo().getName());
    	} else rootPane.setVisible(false);
    }

    /**
    * Contient la valeur à afficher.
    */
    private final ObjectProperty<MilkXmlObj> value = new SimpleObjectProperty<>(this, "value");

    public final MilkXmlObj getValue() {
        return value.get();
    }

	public void setValue(MilkXmlObj value) {
        this.value.set(value);
    }


    public final ObjectProperty<MilkXmlObj> valueProperty() {
        return value;
    }

    public final void showMilkXmlObj() {
    	((Milker) getApplication()).showMilkObject(getValue());
    	updateUI(getValue());
    }
    
    /**
    * Contient une référence vers l'application parente.
    */
    private final ObjectProperty<Application> application = new SimpleObjectProperty<>(this, "application");

    public final Application getApplication() {
        return application.get();
    }

    public final void setApplication(Application value) {
        this.application.set(value);
    }

    public final ObjectProperty<Application> applicationProperty() {
        return application;
    }
}
