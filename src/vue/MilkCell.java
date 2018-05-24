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
import modele.MilkXmlObj;

/**
 * The controller for the Menu Bar.  
 * @author Lyanel Pheles
 */
public class MilkCell extends ListCell<MilkXmlObj> {
    
    private Node renderer;
    private MilkCellController rendererController;
    private final Milker application;
    
    public MilkCell(Application application) {
        super();
        this.application = (Milker) application;
        try {
            final URL fxmlURL = getClass().getResource(MilkRs.milkCell);
            final FXMLLoader fxmlLoader = new FXMLLoader(fxmlURL);
            renderer = (Node) fxmlLoader.load();
            rendererController = (MilkCellController) fxmlLoader.getController();
            rendererController.setApplication(this.application);
        } catch (IOException ex) {
            Logger.getLogger(MilkCell.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }
    }
    
    @Override
    protected void updateItem(MilkXmlObj value, boolean empty) {
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
