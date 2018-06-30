package vue.game;

import java.net.URL;
import java.util.ResourceBundle;

import application.Milker;
import controleur.GameModele;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import modele.MilkRs;
import modele.baseObject.MilkInterface;
import modele.toggle.ToggleLevel;
import modele.toggle.ToggleOption;

/**
 * The controller for the Option Cell.  
 * @author Lyanel Pheles
 */
public class MilkCellOptLvlController extends MilkCellOptionController implements Initializable {

    @FXML
    protected Label lvlNameLabel;
    @FXML
    protected Label QntLabel;
    @FXML
    protected Label BonusQntLabel;
    @FXML
    protected Label QalLabel;
    @FXML
    protected Label BonusQalLabel;
    
	public MilkCellOptLvlController() {
        super();
    }

    /**
     * Initialisation du contrôleur.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	super.initialize(url, rb);
    	lvlNameLabel.setText(null);
  //  	QntLabel.setText(MilkInterface.getStringsFromId(557) + " " + MilkInterface.getStringsFromId(631) + " :");
    	QntLabel.textProperty().bind( Bindings.concat(MilkInterface.getMilkStringsFromId(557).getText(), " ", MilkInterface.getMilkStringsFromId(631).getText()," :" ) );
    	BonusQntLabel.setText(null);
   // 	QalLabel.setText(MilkInterface.getStringsFromId(557) + " " + MilkInterface.getStringsFromId(632) + " :");
    	QalLabel.textProperty().bind( Bindings.concat(MilkInterface.getMilkStringsFromId(557).getText(), " ", MilkInterface.getMilkStringsFromId(632).getText()," :" ) );
    	BonusQalLabel.setText(null);
    	varPan.setOnMouseEntered(event -> setInfoVisible (((ToggleOption) valueProperty().getValue()).getLevel().getInfo(),true));
	}
    
    @Override
    public void updateUI(ToggleOption thing) {
    	if(((Milker) getApplication()).getModel().isMilkObjVisible(thing) ){
    		rootPane.setVisible(true);
    		int lvl = ((Milker) getApplication()).getModel().getToolLevel(thing);
    		thing.setLvl(lvl);
    		super.updateUIVisible(thing);
    		ToggleLevel level = thing.getLevel();
        //	lvlNameLabel.setText(level.getInfo().getName());
        	lvlNameLabel.textProperty().bind( level.getInfo().getObrservableName()  );
	    	BonusQntLabel.setText("*"+level.getBonus().getAttrib().getQuant().toString()+"%");
	    	BonusQalLabel.setText("*"+level.getBonus().getAttrib().getQual().toString()+"%");
	    	level.getIcon().setAsIcon();
			iLabel.setGraphic(level.getIcon().getImageView());

            if(thing.isSelected())nameLabel.getStyleClass().add(MilkRs.cssBought);
            else{
                if(!((Milker) getApplication()).getModel().isToolToggleSwitchable())nameLabel.getStyleClass().add(MilkRs.cssNotBuyable);
                else nameLabel.getStyleClass().remove(MilkRs.cssNotBuyable);
            }
    	} else rootPane.setVisible(false);
    }
    
    @Override
    public void showThing() {
    //	((Milker) getApplication()).showMilkXmlObj( getValue());
    	updateUI(getValue());
    }

    @Override
    public void buyThing() {
    	GameModele model = ((Milker) getApplication()).getModel();
    	if (model.isToolToggleSwitchable())model.switchToolToggle(getValue());
    	updateUI(getValue());
    }
}
