package vue;

import controleur.GameOption;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import javafx.util.Callback;
import modele.baseObject.MilkInterface;
import modele.baseObject.MilkLanguage;

/**
 * The controller for the Game window.  
 * @author Lyanel Pheles
 */
public class MilkOptionController extends MilkerController {

    @FXML
    private Label optionLab1;
    @FXML
    private Label optionLab2;
    @FXML
    private Label optionLab3;
    @FXML
    private ComboBox<MilkLanguage> optionSel1;
    @FXML
    private ComboBox<String> optionSel2;
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
    	initOption1();
    	initOption2();
    	
    	saveBT.setOnAction(new EventHandler<ActionEvent>() {
    	    @Override public void handle(ActionEvent e) {
    	    	GameOption.getOption().setLanguage(optionSel1.getSelectionModel().getSelectedItem());
    	    	GameOption.getOption().setCss(optionSel2.getSelectionModel().getSelectedItem());
    	    	getMainApp().setStageCss(getMainApp().getScene());
    	    	getMainApp().setStatutMessage(MilkInterface.getMilkStringsFromId(140).asText());
    	    	Stage stage = (Stage) CancelBT.getScene().getWindow();
    	        stage.close();
    	    }
    	});
    	
    	CancelBT.setOnAction(new EventHandler<ActionEvent>() {
    	    @Override public void handle(ActionEvent e) {
    	    	Stage stage = (Stage) CancelBT.getScene().getWindow();
    	        stage.close();
    	    }
    	});

    }
    
    public void initOption1() {
    //	optionLab1.setText(MilkInterface.getStringsFromId(33));
    	optionLab1.textProperty().bind( MilkInterface.getMilkStringsFromId(33).getText()  );
    	optionSel1.setItems(MilkInterface.getListes());
    	optionSel1.setValue(MilkInterface.getMilkLanguage());
    	
    	optionSel1.setButtonCell(
    		    new ListCell<MilkLanguage>() {
    		        @Override
    		        protected void updateItem(MilkLanguage item, boolean empty) {
    		            super.updateItem(item, empty);
	    		            if (empty) {
	    		                setText("");
	    		            } else {
	    		                setText(item.getName());
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
    }
    
    public void initOption2() {
    //	optionLab2.setText(MilkInterface.getStringsFromId(33));
    	optionLab2.textProperty().bind( MilkInterface.getMilkStringsFromId(33).getText()  );
    	optionSel2.setItems(GameOption.getCssLists());
    	
    	optionSel2.setButtonCell(
    		    new ListCell<String>() {
    		        @Override
    		        protected void updateItem(String item, boolean empty) {
    		            super.updateItem(item, empty);
	    		            if (empty) {
	    		                setText("");
	    		            } else {
	    		                setText(item);
    		            }
    		        }
    		    });
    	
    	optionSel2.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
    	    @SuppressWarnings("rawtypes")
			public ListCell call(ListView<String> p) {
                ListCell cell = new ListCell<String >() {
                    @Override
                    protected void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setText("");
                        } else {
                            setText(item);
                        }
                    }
                };
    	        return cell;
    	    }
    	});
    	optionSel2.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> changeVar(newValue));
    	if(GameOption.getOption().getCss()!=null) optionSel2.setValue(GameOption.getOption().getCss());
    	else optionSel2.getSelectionModel().selectFirst();
    }

	public void changeVar(String css) {
		if (optionSel2.getScene()!=null){
			optionSel2.getScene().getStylesheets().clear();
			optionSel2.getScene().getStylesheets().add(GameOption.getCssUrl(css));
		}
	}
}