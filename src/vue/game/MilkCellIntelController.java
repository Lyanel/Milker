package vue.game;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import application.Milker;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import modele.MilkRs;
import modele.Utility;
import modele.baseObject.MilkInterface;
import modele.intel.Intel;
import modele.intel.Research;
import modele.intel.Upgrade;
import modele.thing.Thing;

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
    	if(((Milker) getApplication()).getModel().isIntelVisible(intel) ){
         //   priceLabel.setText(intel.getPrice().getStringCoin());
            if(intel.bought()){
            	nameLabel.getStyleClass().add(MilkRs.cssBought);
            	priceLabel.setVisible(false);
            } else {
            	if(Utility.thatNeedaSacrifice(intel)){
        			ArrayList<? extends Thing> sacrifices = Utility.getThingsListsFromAgent(((Research)intel).getSacrifice());
        			String sacrifice = null;
        			if(sacrifices.size() == 1){
        				sacrifice = sacrifices.get(0).getInfo().getObrservableName().getValue();
        			} else sacrifice = MilkInterface.getMilkStringsFromId(617).getText().getValue();
        			priceLabel.textProperty().bind( Bindings.concat(MilkInterface.getMilkStringsFromId(616).getText()," : ",intel.getPriceValue()," ",
        					MilkInterface.getMilkStringsFromId(601).getText()," ", MilkInterface.getMilkStringsFromId(1100).getText()," ",
        					((Research)intel).getSacrifice().getQuantity().getObrservableQuant().getValue()," ",sacrifice,"." ) );
        			
        		} else priceLabel.textProperty().bind( Bindings.concat(MilkInterface.getMilkStringsFromId(616).getText()," : ", 
        					intel.getPriceValue()," ",MilkInterface.getMilkStringsFromId(601).getText(),"." ) );
        		if(!((Milker) getApplication()).getModel().isMilkPricedObjbuyable(intel))nameLabel.getStyleClass().add(MilkRs.cssNotBuyable);
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
    		((Milker) getApplication()).getModel().buyMilkPricedObj(getValue());
			if(getValue().bought()){
				((Milker) getApplication()).unlockView(getValue());
				if(getValue().getClass().equals(Upgrade.class) ) ((Upgrade)getValue()).applyUpgrade();
			}
			updateUI(getValue());
    	}
    }
}
