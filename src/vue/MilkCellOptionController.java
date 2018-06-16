package vue;

import java.net.URL;
import java.util.ResourceBundle;

import application.Milker;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.Initializable;
import modele.toggle.ToggleOption;

/**
 * The controller for the Option Cell.  
 * @author Lyanel Pheles
 */
public class MilkCellOptionController extends MilkCellController implements Initializable {

    @SuppressWarnings("unchecked")
	public MilkCellOptionController() {
        valueProperty().addListener(valueChangeListener);
    }

    /**
     * Initialisation du contrôleur.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	super.initialize(url, rb);
    	varPan.setOnMouseEntered(event -> setInfoVisible (((ToggleOption) valueProperty().getValue()).getInfo(),true));
	}

    /**
    * Cet écouteur est appelé lorsque la propriété value change.
    **/
    private final ChangeListener<ToggleOption> valueChangeListener = (ObservableValue<? extends ToggleOption> observableValue, ToggleOption oldValue, ToggleOption newValue) -> {
        updateUI(newValue);
    };

    public void updateUI(ToggleOption thing) {
    	if(((Milker) getApplication()).getModel().isMilkObjVisible(thing) )super.updateUIVisible(thing);
    	else rootPane.setVisible(false);
    }

    /**
    * Contient la valeur à afficher.
    */
    private final ObjectProperty<ToggleOption> value = new SimpleObjectProperty<>(this, "value");

    public ToggleOption getValue() {
        return value.get();
    }

    public void setValue(ToggleOption value) {
        this.value.set(value);
    }

    @SuppressWarnings("rawtypes")
	public ObjectProperty valueProperty() {
        return value;
    }

    @Override
    public void showThing() {
    	((Milker) getApplication()).showMilkXmlObj(getValue());
    	updateUI(getValue());
    }

    @Override
    public void buyThing() {
    	((Milker) getApplication()).getModel().switchIdolToggle(getValue());
    	updateUI(getValue());
    }
}
