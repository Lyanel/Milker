package vue;

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
public class ThingCell extends ListCell<Thing> {
    
    private Node renderer;
    private ThingCellController rendererController;
    private final Milker application;
    
    public ThingCell(Application application) {
        super();
        this.application = (Milker) application;
        try {
            final URL fxmlURL = getClass().getResource(MilkRs.thingCell);
            final FXMLLoader fxmlLoader = new FXMLLoader(fxmlURL);
            renderer = (Node) fxmlLoader.load();
            rendererController = (ThingCellController) fxmlLoader.getController();
            rendererController.setApplication(this.application);
        } catch (IOException ex) {
            Logger.getLogger(ThingCell.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }
    }
    
    @Override
    protected void updateItem(Thing value, boolean empty) {
        super.updateItem(value, empty);
        String text = null;
        Node graphic = null;
        if (!empty && renderer != null && value != null && ((Milker) application).getModel().isThingVisible(value)) {
        	this.setVisible(true);
            if (((Milker) application).getModel().isThingbuyable(value)) {
                graphic = renderer;
                rendererController.setValue(value);
            } else {
                text = String.valueOf(value);
            }
        }else this.setVisible(false);
        setText(text);
        setGraphic(graphic);
    }
}
