package vue;

import java.net.URL;
import java.util.ResourceBundle;

import application.Milker;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import modele.MilkRs;
import modele.thing.Thing;

/**
 * The controller for the Menu Bar.  
 * @author Lyanel Pheles
 */
public class MilkCellThingController extends MilkCellController implements Initializable {

    @FXML
    private Label priceLabel;
    @FXML
    private Label quantLabel;

    @SuppressWarnings("unchecked")
	public MilkCellThingController() {
        valueProperty().addListener(valueChangeListener);
    }

    /**
     * Initialisation du contrôleur.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	super.initialize(url, rb);
    	priceLabel.setText(null);
    	quantLabel.setText(null);
    	varPan.setOnMouseEntered(event -> setInfoVisible (((Thing) valueProperty().getValue()).getInfo(),true));
    }

    /**
    * Cet écouteur est appelé lorsque la propriété value change.
    **/
    private final ChangeListener<Thing> valueChangeListener = (ObservableValue<? extends Thing> observableValue, Thing oldValue, Thing newValue) -> {
        updateUI(newValue);
    };

    private void updateUI(Thing thing) {
    	super.updateUI(thing);
    	if(((Milker) getApplication()).getModel().isMilkObjVisible(thing) ){
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

    public Thing getValue() {
        return value.get();
    }

    public void setValue(Thing value) {
        this.value.set(value);
    }

    @SuppressWarnings("rawtypes")
	public ObjectProperty valueProperty() {
        return value;
    }

    @Override
    public void showThing() {
    	((Milker) getApplication()).showMilkXmlObj( getValue());
    	updateUI(getValue());
    }

    @Override
    public void buyThing() {
    	((Milker) getApplication()).getModel().buyThing( getValue());
    	updateUI(getValue());
    }
}
