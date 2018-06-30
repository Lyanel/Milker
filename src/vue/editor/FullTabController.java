package vue.editor;

/*
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import modele.MilkInterface;
*/
/**
 * The controller for the Menu Bar.  
 * @author Lyanel Pheles
 */
public class FullTabController extends MilkTabControleur {
/*
    
    @FXML
    private ComboBox<MilkVar> selectVar;
    
	// description Label

    @FXML
	private Label identifier;
    @FXML
	private Label path;
    @FXML
	private Label level;
    @FXML
	private Label start;
    @FXML
	private Label agent;
    @FXML
	private Label price;
    @FXML
	private Label income;
    @FXML
	private Label bonus;
    @FXML
	private Label population;
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
	private Label lvlActual;
    @FXML
	private Label pathActual;
    @FXML
	private Label startActual;
    @FXML
	private Label agentActual;
    @FXML
	private Label priceActual;
    @FXML
	private Label incomeActual;
    @FXML
	private Label bonusActual;
    @FXML
	private Label popActual;
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
	private TextField lvlInput;
    @FXML
	private TextField pathInput;
    @FXML
	private TextField startInput;
    @FXML
	private TextField agentInput;
    @FXML
	private TextField priceInput;
    @FXML
	private TextField incomeInput;
    @FXML
	private TextField bonusInput;
    @FXML
	private TextField popInput;
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
    	selectVar.setItems(MilkVar.getListes());
		changeVar(selectVar.getValue());
	}
    
    
	public void setText() {
		super.setText();
		
		// description Label
		
		identifier.setText(MilkInterface.getStringsFromId(612));
		path.setText(MilkInterface.getStringsFromId(613));
		level.setText(MilkInterface.getStringsFromId(614));
		start.setText(MilkInterface.getStringsFromId(615));
		
		price.setText(MilkInterface.getStringsFromId(616));
		income.setText(MilkInterface.getStringsFromId(618));
		bonus.setText(MilkInterface.getStringsFromId(619));
		population.setText(MilkInterface.getStringsFromId(620));
		agent.setText(MilkInterface.getStringsFromId(621));
		need.setText(MilkInterface.getStringsFromId(622));
		check.setText(MilkInterface.getStringsFromId(623));
		effect.setText(MilkInterface.getStringsFromId(624));

		name.setText(MilkInterface.getStringsFromId(651));
		txEffect.setText(MilkInterface.getStringsFromId(652));
		description.setText(MilkInterface.getStringsFromId(653));
		quote.setText(MilkInterface.getStringsFromId(654));
    }

	public void changeVar(MilkVar milkVar) {
		if(milkVar!=null){
			
			idValue.setText(""+milkVar.getId());
			lvlActual.setText(""+milkVar.getLvl());
			startActual.setText(""+milkVar.getStart());
			pathActual.setText(""+milkVar.getAttrib());
			
			priceActual.setText(""+milkVar.getPrice());
			incomeActual.setText(""+milkVar.getIncome());
			bonusActual.setText(""+milkVar.getBonus());
			popActual.setText(""+milkVar.getPopulation());
			agentActual.setText(""+milkVar.getAgent());
			needActual.setText(""+milkVar.getNeed());
			checkActual.setText(""+milkVar.getCheck());
			effectActual.setText(""+milkVar.getEffect());
			
			nameActual.setText(milkVar.getInfo().getName());
			txEfActual.setText(milkVar.getInfo().getTxEffect());
			descActual.setText(milkVar.getInfo().getDesc());
			quoteActual.setText(milkVar.getInfo().getQuote());

			idInput.setText(""+milkVar.getId());
			lvlInput.setText(""+milkVar.getLvl());
			startInput.setText(""+milkVar.getStart());
			pathInput.setText(""+milkVar.getAttrib());
			
			priceInput.setText(""+milkVar.getPrice());
			incomeInput.setText(""+milkVar.getIncome());
			bonusInput.setText(""+milkVar.getBonus());
			popInput.setText(""+milkVar.getPopulation());
			agentInput.setText(""+milkVar.getAgent());
			needInput.setText(""+milkVar.getNeed());
			checkInput.setText(""+milkVar.getCheck());
			effectInput.setText(""+milkVar.getEffect());
			
			nameInput.setText(milkVar.getInfo().getName());
			txEfInput.setText(milkVar.getInfo().getTxEffect());
			descInput.setText(milkVar.getInfo().getDesc());
			quoteInput.setText(milkVar.getInfo().getQuote());
		}
	}*/
}