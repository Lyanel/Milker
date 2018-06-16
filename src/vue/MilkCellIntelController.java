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
import modele.intel.Intel;
import modele.intel.Research;

/**
 * The controller for the Option Cell.  
 * @author Lyanel Pheles
 */
public class MilkCellIntelController extends MilkCellController implements Initializable {

    @FXML
    private Label priceLabel;
    @SuppressWarnings("unchecked")
	public MilkCellIntelController() {
        valueProperty().addListener(valueChangeListener);
    }

    /**
     * Initialisation du contrôleur.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	super.initialize(url, rb);
    	priceLabel.setText(null);
    	varPan.setOnMouseEntered(event -> setInfoVisible (((Intel) valueProperty().getValue()).getInfo(),true));
	}

    /**
    * Cet écouteur est appelé lorsque la propriété value change.
    **/
    private final ChangeListener<Intel> valueChangeListener = (ObservableValue<? extends Intel> observableValue, Intel oldValue, Intel newValue) -> {
        updateUI(newValue);
    };

    private void updateUI(Intel intel) {
    	super.updateUI(intel);
    	if(((Milker) getApplication()).getModel().isMilkObjVisible(intel) ){
            priceLabel.setText(intel.getPrice().getStringCoin());
            if(intel.bought()){
            	nameLabel.getStyleClass().add(MilkRs.cssBought);
            	priceLabel.setVisible(false);
            } else {
                if(!((Milker) getApplication()).getModel().isIntelbuyable(intel))nameLabel.getStyleClass().add(MilkRs.cssNotBuyable);
                else nameLabel.getStyleClass().remove(MilkRs.cssNotBuyable);
            }
    	} else rootPane.setVisible(false);
    }

    /**
    * Contient la valeur à afficher.
    */
    private final ObjectProperty<Intel> value = new SimpleObjectProperty<>(this, "value");

    public Intel getValue() {
        return value.get();
    }

    public void setValue(Intel value) {
        this.value.set(value);
    }

    @SuppressWarnings("rawtypes")
	public ObjectProperty valueProperty() {
        return value;
    }

    @Override
    public void buyThing() {
    	if(!getValue().bought()) {
    		((Milker) getApplication()).getModel().buyIntel(getValue());
			if(getValue().getClass().equals(Research.class)){
				if(getValue().getId().intValue()==44)((Milker) getApplication()).setSlavesTabVisible(getValue().bought());
				if(getValue().getId().intValue()==501)((Milker) getApplication()).setIdolTabVisible(getValue().bought());
			}
			updateUI(getValue());
    	}
    }
}
