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
import modele.intel.Intel;

/**
 * The controller for the Menu Bar.  
 * @author Lyanel Pheles
 */
public class MilkCellIntel extends ListCell<Intel> {
    
    private Node renderer;
    private MilkCellIntelController rendererController;
    private final Milker application;
    
    public MilkCellIntel(Application application) {
        super();
        this.application = (Milker) application;
        try {
            final URL fxmlURL = getClass().getResource(MilkRs.milkCellIntel);
            final FXMLLoader fxmlLoader = new FXMLLoader(fxmlURL);
            renderer = (Node) fxmlLoader.load();
            rendererController = (MilkCellIntelController) fxmlLoader.getController();
            rendererController.setApplication(this.application);
            this.managedProperty().bind(this.visibleProperty());
        } catch (IOException ex) {
            Logger.getLogger(MilkCellIntel.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }
    }
    
    @Override
    protected void updateItem(Intel value, boolean empty) {
        super.updateItem(value, empty);
        String text = null;
        Node graphic = null;
        if (!empty && renderer != null && value != null && ((Milker) application).getModel().isMilkObjVisible(value)) {
        	this.setVisible(true);
            graphic = renderer;
            rendererController.setValue(value);
            setText(text);
            setGraphic(graphic);
        }else this.setVisible(false);
    }
}
