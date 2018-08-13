package vue.editor;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import modele.baseObject.MilkInterface;
import modele.intel.Upgrade;
import modele.intel.UpgradeList;

/**
 * The controller for the Menu Bar.  
 * @author Lyanel Pheles
 */
public class UpgradeTabController extends MilkTabControleur {
    
    @FXML
    private ComboBox<Upgrade> selectVar;
    
	// description Label

    @FXML
	private Label identifier;
    @FXML
	private Label start;
    @FXML
	private Label sacrifice;
    @FXML
	private Label price;
    @FXML
	private Label need;
    @FXML
	private Label check;
    @FXML
	private Label effect;

    @FXML
	private Label name;
    @FXML
	private Label txEffect;
    @FXML
	private Label description;
    @FXML
	private Label quote;

	// Actual value Label

    @FXML
	private Label idValue;
    @FXML
	private Label startActual;
    @FXML
	private Label sacrificeActual;
    @FXML
	private Label priceActual;
    @FXML
	private Label needActual;
    @FXML
	private Label checkActual;
    @FXML
	private Label effectActual;

    @FXML
	private Label nameActual;
    @FXML
	private Label txEfActual;
    @FXML
	private Label descActual;
    @FXML
	private Label quoteActual;

	// Input value Field

    @FXML
	private TextField idInput;
    @FXML
	private TextField startInput;
    @FXML
	private TextField sacrificeInput;
    @FXML
	private TextField priceInput;
    @FXML
	private TextField needInput;
    @FXML
	private TextField checkInput;
    @FXML
	private TextField effectInput;

    @FXML
	private TextField nameInput;
    @FXML
	private TextField txEfInput;
    @FXML
	private TextField descInput;
    @FXML
	private TextField quoteInput;
	
	@FXML
	public void initialize() {
    	setText();
    	selectVar.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> changeVar(newValue));
    	selectVar.setItems(UpgradeList.getInstance().getUpgradeListe());
		changeVar(selectVar.getValue());
	}
    
    
	public void setText() {
		super.setText();
		
		// description Label
		
		identifier.setText(MilkInterface.getStringsFromId(612));
		start.setText(MilkInterface.getStringsFromId(615));
		
		price.setText(MilkInterface.getStringsFromId(616));
		sacrifice.setText(MilkInterface.getStringsFromId(617));
		need.setText(MilkInterface.getStringsFromId(622));
		check.setText(MilkInterface.getStringsFromId(623));
		effect.setText(MilkInterface.getStringsFromId(652));

		name.setText(MilkInterface.getStringsFromId(651));
		txEffect.setText(MilkInterface.getStringsFromId(652));
		description.setText(MilkInterface.getStringsFromId(653));
		quote.setText(MilkInterface.getStringsFromId(654));
    }

	public void changeVar(Upgrade milkVar) {
		if(milkVar!=null){
			
			idValue.setText(""+milkVar.getId());
			startActual.setText(""+milkVar.getStart());
			
			priceActual.setText(""+milkVar.getPrice());
			sacrificeActual.setText(""+milkVar.getSacrifice());
			needActual.setText(""+milkVar.getNeed());
			checkActual.setText(""+milkVar.getCheck());
			effectActual.setText(""+milkVar.getEffects());
			
			nameActual.setText(milkVar.getInfo().getName());
			txEfActual.setText(milkVar.getInfo().getTxEffect());
			descActual.setText(milkVar.getInfo().getDesc());
			quoteActual.setText(milkVar.getInfo().getQuote());

			idInput.setText(""+milkVar.getId());
			startInput.setText(""+milkVar.getStart());
			
			priceInput.setText(""+milkVar.getPrice());
			sacrificeInput.setText(""+milkVar.getSacrifice());
			needInput.setText(""+milkVar.getNeed());
			checkInput.setText(""+milkVar.getCheck());
			effectInput.setText(""+milkVar.getEffects());
			
			nameInput.setText(milkVar.getInfo().getName());
			txEfInput.setText(milkVar.getInfo().getTxEffect());
			descInput.setText(milkVar.getInfo().getDesc());
			quoteInput.setText(milkVar.getInfo().getQuote());
		}
	}
}