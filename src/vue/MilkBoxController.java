package vue;

import java.net.URL;
import java.util.ResourceBundle;

import application.Milker;
import javafx.application.Application;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.Initializable;
import modele.baseObject.MilkInfo;
import modele.baseObject.MilkXmlObj;

/**
 * The controller for the Menu Bar.  
 * @author Lyanel Pheles
 */
public class MilkBoxController implements Initializable {

	public MilkBoxController() {}

    /**
     * Initialisation du contrôleur.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    /**
    * Cet écouteur est appelé lorsque la propriété value change.
    *
    private final ChangeListener<MilkXmlObj> valueChangeListener = (ObservableValue<? extends MilkXmlObj> observableValue, MilkXmlObj oldValue, MilkXmlObj newValue) -> {
        updateUI(newValue);
    };*/

    protected void updateUI(MilkXmlObj milkXmlObj) {}

    public void showThing() {}

    public void buyThing() {}

    public void setInfoVisible(MilkInfo value, boolean visibility) {
    	((Milker) getApplication()).setInfoPanVisible(value,visibility);
    }
    
    /**
    * Contient une référence vers l'application parente.
    */
    protected final ObjectProperty<Application> application = new SimpleObjectProperty<>(this, "application");

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
