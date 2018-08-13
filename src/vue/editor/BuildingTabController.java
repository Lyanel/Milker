package vue.editor;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import modele.baseObject.MilkInterface;
import modele.thing.Building;
import modele.thing.BuildingList;

/**
 * The controller for the Menu Bar.  
 * @author Lyanel Pheles
 */
public class BuildingTabController extends MilkTabControleur {

    @FXML
    private ComboBox<Building> selectVar;
    
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
    	selectVar.setItems(BuildingList.getInstance().getFullListe());
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
		agent.setText(MilkInterface.getStringsFromId(502));
		need.setText(MilkInterface.getStringsFromId(622));

		name.setText(MilkInterface.getStringsFromId(651));
		txEffect.setText(MilkInterface.getStringsFromId(652));
		description.setText(MilkInterface.getStringsFromId(653));
		quote.setText(MilkInterface.getStringsFromId(654));
    }

	public void changeVar(Building building) {
		if(building!=null){
			
			idValue.setText(""+building.getId());
			lvlActual.setText(""+building.getLvl());
			startActual.setText(""+building.getStart());
			pathActual.setText(""+building.getTree());
			
			priceActual.setText(""+building.getPrice());
			incomeActual.setText(""+building.getIncome());
			bonusActual.setText(""+building.getBonus());
			popActual.setText(""+building.getPopulation());
			agentActual.setText(""+building.getAgent());
			needActual.setText(""+building.getNeed());
			
			nameActual.setText(building.getInfo().getName());
			txEfActual.setText(building.getInfo().getTxEffect());
			descActual.setText(building.getInfo().getDesc());
			quoteActual.setText(building.getInfo().getQuote());

			idInput.setText(""+building.getId());
			lvlInput.setText(""+building.getLvl());
			startInput.setText(""+building.getStart());
			pathInput.setText(""+building.getTree());
			
			priceInput.setText(""+building.getPrice());
			incomeInput.setText(""+building.getIncome());
			bonusInput.setText(""+building.getBonus());
			popInput.setText(""+building.getPopulation());
			agentInput.setText(""+building.getAgent());
			needInput.setText(""+building.getNeed());
			
			nameInput.setText(building.getInfo().getName());
			txEfInput.setText(building.getInfo().getTxEffect());
			descInput.setText(building.getInfo().getDesc());
			quoteInput.setText(building.getInfo().getQuote());
		}
	}
}