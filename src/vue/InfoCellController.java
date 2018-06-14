package vue;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import modele.MilkInfo;

/**
 * 
 * @author Lyanel Pheles
 */
public class InfoCellController {
	
    @FXML
    private AnchorPane rootPane;
    
    @FXML
    private GridPane infoPan;
    @FXML
    private Label effectPan;
    @FXML
    private Label descPan;
    @FXML
    private Label quotePan;

    public InfoCellController() {
        valueProperty().addListener(valueChangeListener);
    }

    public void initialize(URL url, ResourceBundle rb) {
    	effectPan.setText(getValue().getTxEffect());
    	descPan.setText(getValue().getDesc());
    	quotePan.setText(getValue().getQuote());
    	infoPan.managedProperty().bind(rootPane.visibleProperty());
    }

    /**
    * Cet écouteur est appelé lorsque la propriété value change.
    */
    private final ChangeListener<MilkInfo> valueChangeListener = (ObservableValue<? extends MilkInfo> observableValue, MilkInfo oldValue, MilkInfo newValue) -> {
        updateUI(newValue);
    };

    private void updateUI(MilkInfo newValue) {
        effectPan.setText(newValue.getTxEffect());
        descPan.setText(newValue.getDesc());
        quotePan.setText(newValue.getQuote());
    }
    
    /**
    * Contient la valeur à afficher.
    */
    private final ObjectProperty<MilkInfo> value = new SimpleObjectProperty<>(this, "value");

    public final MilkInfo getValue() {
        return value.get();
    }

    public final void setValue(MilkInfo value) {
        this.value.set(value);
    }

    public final ObjectProperty<MilkInfo> valueProperty() {
        return value;
    }

	public void setVisible(boolean visible) {
		infoPan.setVisible(visible);
		
	}

	public boolean isVisible() {
		return infoPan.isVisible();
		
	}
}
