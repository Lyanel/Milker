package vue;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TitledPane;
import javafx.util.Callback;
import modele.MilkInterface;
import modele.MilkKind;
import modele.MilkLanguage;

/**
 * The controller for the Game window.  
 * @author Lyanel Pheles
 */
public class MilkOptionController extends MilkTabControleur {

    @FXML
    private TitledPane optionTitle;
    @FXML
    private Label optionLab1;
    @FXML
    private Label optionLab2;
    @FXML
    private Label optionLab3;
    @FXML
    private ComboBox<MilkLanguage> optionSel1;
  /*  @FXML
    private ChoiceBox optionSel2;
    @FXML
    private ChoiceBox optionSel3;*/
    @FXML
    private Button saveBT;
    @FXML
    private Button CancelBT;

	@FXML
    private void initialize() {initList();}
	
    public void initList() {
    	optionLab1.setText(MilkInterface.getStringsFromId(33));
    	optionSel1.setItems(MilkInterface.getListes());
    	optionSel1.setValue(MilkInterface.getMilkLanguage());
    	
    	optionSel1.setButtonCell(
    		    new ListCell<MilkLanguage>() {
    		        @Override
    		        protected void updateItem(MilkLanguage t, boolean bln) {
    		            super.updateItem(t, bln);
	    		            if (bln) {
	    		                setText("");
	    		            } else {
	    		                setText(t.getName());
    		            }
    		        }
    		    });
    	
    	optionSel1.setCellFactory(new Callback<ListView<MilkLanguage>, ListCell<MilkLanguage>>() {
    	    @SuppressWarnings("rawtypes")
			public ListCell call(ListView<MilkLanguage> p) {
                ListCell cell = new ListCell<MilkLanguage >() {
                    @Override
                    protected void updateItem(MilkLanguage item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setText("");
                        } else {
                            setText(item.getName());
                        }
                    }
                };
    	        return cell;
    	    }
    	});
    	
    	
    	optionSel1.setButtonCell(
    		    new ListCell<MilkLanguage>() {
    		        @Override
    		        protected void updateItem(MilkLanguage t, boolean bln) {
    		            super.updateItem(t, bln);
                        System.out.println("MilkOptionController.initList() - empty = "+bln);
    		            if (bln) {
    		                setText("");
    		            } else {
    		                setText(t.getName());
    		            }
    		        }
    		    });
    }
}