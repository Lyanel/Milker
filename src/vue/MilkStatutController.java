package vue;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

/**
 * The controller for the Statut bar.  
 * @author Lyanel Pheles
 */
public class MilkStatutController extends MilkerController {
		
    @FXML
    private ProgressBar progress;
    @FXML
    private Label message;
    @FXML
    private DigitalClock timeLabel;
    
	
	public void setMessage(String text) {
		message.setText(text);
	}
    @FXML
	public void initialize() {
    	
    }
	/*
    @FXML
	public void initialize() {
	}*/
}