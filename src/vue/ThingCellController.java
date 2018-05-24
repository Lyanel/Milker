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
import modele.MilkRs;
import modele.thing.Thing;

/**
 * The controller for the Menu Bar.  
 * @author Lyanel Pheles
 */
public class ThingCellController implements Initializable {

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
    @FXML
    private Label priceLabel;
    @FXML
    private Label quantLabel;

    public ThingCellController() {
        valueProperty().addListener(valueChangeListener);
    }

    /**
     * Initialisation du contrôleur.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	iLabel.setText(null);
    	nameLabel.setText(null);
    	priceLabel.setText(null);
    	quantLabel.setText(null);

    	iLabel.setOnMousePressed(event -> showThing());
    	textPan.setOnMousePressed(event -> buyThing());
    	varPan.setOnMouseEntered(event -> ((Milker) getApplication()).setInfoPanVisible(valueProperty().getValue().getInfo(),true));
    	varPan.setOnMouseExited(event -> ((Milker) getApplication()).setInfoPanVisible(null,false));
    }

    /**
    * Cet écouteur est appelé lorsque la propriété value change.
    */
    private final ChangeListener<Thing> valueChangeListener = (ObservableValue<? extends Thing> observableValue, Thing oldValue, Thing newValue) -> {
        updateUI(newValue);
    };

    private void updateUI(Thing thing) {
    	if(((Milker) getApplication()).getModel().isThingVisible(thing) ){
    		rootPane.setVisible(true);
    		if(thing.getIcon().getName()=="") iLabel.setText(thing.getStringId());
    		else {
    			thing.getIcon().setAsIcon();
    			iLabel.setGraphic(thing.getIcon().getImageView());
    		}
            nameLabel.setText(thing.getInfo().getName());
            priceLabel.setText(thing.getPrice().getStringCoin());
            quantLabel.setText(thing.getAttrib().getStringQuant());
            if(!((Milker) getApplication()).getModel().isThingbuyable(thing))nameLabel.getStyleClass().add(MilkRs.cssNotBuyable);
            else nameLabel.getStyleClass().remove(MilkRs.cssNotBuyable);
    	} else rootPane.setVisible(false);
    }

    /**
    * Contient la valeur à afficher.
    */
    private final ObjectProperty<Thing> value = new SimpleObjectProperty<>(this, "value");

    public final Thing getValue() {
        return value.get();
    }

    public final void setValue(Thing value) {
        this.value.set(value);
    }

    public final ObjectProperty<Thing> valueProperty() {
        return value;
    }

    public final void showThing() {
    	((Milker) getApplication()).showThing(getValue());
    	updateUI(getValue());
    }

    public final void buyThing() {
    	((Milker) getApplication()).getModel().buyThing(getValue());
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
