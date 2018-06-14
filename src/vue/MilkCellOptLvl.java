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
import modele.toggle.ToggleOption;

/**
 * The controller for the Menu Bar.  
 * @author Lyanel Pheles
 */
public class MilkCellOptLvl extends ListCell<ToggleOption> {
    
    private Node renderer;
    private MilkCellOptLvlController rendererController;
    private final Milker application;
    
    public MilkCellOptLvl(Application application) {
        super();
        this.application = (Milker) application;
        try {
            final URL fxmlURL = getClass().getResource(MilkRs.milkCellOptLvl);
            final FXMLLoader fxmlLoader = new FXMLLoader(fxmlURL);
            renderer = (Node) fxmlLoader.load();
            rendererController = (MilkCellOptLvlController) fxmlLoader.getController();
            rendererController.setApplication(this.application);
            this.managedProperty().bind(this.visibleProperty());
        } catch (IOException ex) {
            Logger.getLogger(MilkCellOptLvl.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }
    }
    
    @Override
    protected void updateItem(ToggleOption value, boolean empty) {
        super.updateItem(value, empty);
        String text = null;
        Node graphic = null;
        if (!empty && renderer != null && value != null && ((Milker) application).getModel().isSwitchable(value)) {
        	this.setVisible(true);
            if (((Milker) application).getModel().isSwitchable(value)) {
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
