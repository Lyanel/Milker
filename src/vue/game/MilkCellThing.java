package vue.game;

import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

import application.Milker;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ListCell;
import modele.MilkRs;
import modele.thing.Thing;

/**
 * The controller for the Menu Bar.  
 * @author Lyanel Pheles
 */
public class MilkCellThing extends ListCell<Thing> {
    
    private Node renderer;
    private MilkCellThingController rendererController;
    private final Milker application;
    
    public MilkCellThing(Application application) {
        super();
        this.application = (Milker) application;
        try {
            final URL fxmlURL = getClass().getResource(MilkRs.milkCellThing);
            final FXMLLoader fxmlLoader = new FXMLLoader(fxmlURL);
            renderer = (Node) fxmlLoader.load();
            rendererController = (MilkCellThingController) fxmlLoader.getController();
            rendererController.setApplication(this.application);
            this.managedProperty().bind(this.visibleProperty());
        } catch (IOException ex) {
            Logger.getLogger(MilkCellThing.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }
    }
    
    @Override
    protected void updateItem(Thing value, boolean empty) {
        super.updateItem(value, empty);
        if (!empty && value != null) {
            String text = null;
            Node graphic = null;
            if (!empty && renderer != null && value != null && ((Milker) application).getModel().isMilkObjVisible(value)) {
            	this.setVisible(true);
                graphic = renderer;
                rendererController.setValue(value);
            }else this.setVisible(false);
            setText(text);
            setGraphic(graphic);
        } else setGraphic(null);
    }
}
