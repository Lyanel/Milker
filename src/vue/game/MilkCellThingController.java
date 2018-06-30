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
import modele.thing.Slave;
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
    @FXML
    private Label activeLabel;

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
    	activeLabel.setText(null);
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
         //   priceLabel.setText(thing.getPriceValue().toString());
    		if(Utility.thatNeedaSacrifice(thing)){
    			ArrayList<? extends Thing> sacrifices = Utility.getThingsListsFromAgent(((Slave)thing).getSacrifice());
    			String sacrifice = null;
    			if(sacrifices.size() == 1){
    				sacrifice = sacrifices.get(0).getInfo().getObrservableName().getValue();
    			} else sacrifice = MilkInterface.getMilkStringsFromId(617).getText().getValue();
    			priceLabel.textProperty().bind( Bindings.concat(MilkInterface.getMilkStringsFromId(616).getText()," : ",thing.getPriceValue()," ",
    					MilkInterface.getMilkStringsFromId(601).getText()," ", MilkInterface.getMilkStringsFromId(1100).getText()," ",
    					((Slave)thing).getSacrifice().getAttrib().getObrservableQuant().getValue()," ",sacrifice,"." ) );
    			
    		} else priceLabel.textProperty().bind( Bindings.concat(MilkInterface.getMilkStringsFromId(616).getText()," : ", 
    					thing.getPriceValue()," ",MilkInterface.getMilkStringsFromId(601).getText(),"." ) );
         //   quantLabel.setText(thing.getAttrib().getQuant().intValue());
            quantLabel.textProperty().bind( Bindings.concat(MilkInterface.getMilkStringsFromId(631).getText(), " : ", thing.getAttrib().getObrservableQuant().getValue(),"." ) );
            activeLabel.textProperty().bind( Bindings.concat(MilkInterface.getMilkStringsFromId(633).getText(), " : ", thing.getAttrib().getObrservableActives().getValue(),"." ) );
            if(!((Milker) getApplication()).getModel().isIntelbuyable(thing))priceLabel.getStyleClass().add(MilkRs.cssNotBuyable);
            else priceLabel.getStyleClass().remove(MilkRs.cssNotBuyable);
            if(thing.getAttrib().getQuant().intValue()!=thing.getAttrib().getActives().intValue())activeLabel.getStyleClass().add(MilkRs.cssTooMuch);
            else activeLabel.getStyleClass().remove(MilkRs.cssTooMuch);
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
    	((Milker) getApplication()).getModel().buyIntel( getValue());
    	updateUI(getValue());
    }
}
