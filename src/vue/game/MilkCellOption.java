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
import modele.toggle.ToggleOption;

/**
 * The controller for the Menu Bar.  
 * @author Lyanel Pheles
 */
public class MilkCellOption extends ListCell<ToggleOption> {
    
    private Node renderer;
    private MilkCellOptionController rendererController;
    private final Milker application;
    
    public MilkCellOption(Application application) {
        super();
        this.application = (Milker) application;
        try {
            final URL fxmlURL = getClass().getResource(MilkRs.milkCellOption);
            final FXMLLoader fxmlLoader = new FXMLLoader(fxmlURL);
            renderer = (Node) fxmlLoader.load();
            rendererController = (MilkCellOptionController) fxmlLoader.getController();
            rendererController.setApplication(this.application);
            this.managedProperty().bind(this.visibleProperty());
        } catch (IOException ex) {
            Logger.getLogger(MilkCellOption.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }
    }
    
    @Override
    protected void updateItem(ToggleOption value, boolean empty) {
        super.updateItem(value, empty);
        String text = null;
        Node graphic = null;
        if (!empty && renderer != null && value != null && ((Milker) application).getModel().isOptionVisible(value)) {
        	this.setVisible(true);
            graphic = renderer;
            rendererController.setValue(value);
        }else this.setVisible(false);
        setText(text);
        setGraphic(graphic);
    }
}
