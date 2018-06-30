package vue.editor;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import modele.baseObject.MilkInterface;
import modele.thing.Animal;

/**
 * The controller for the Menu Bar.  
 * @author Lyanel Pheles
 */
public class AnimalTabController extends MilkTabControleur {
	
    @FXML
    private ComboBox<Animal> selectVar;

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
	private Label price;
    @FXML
	private Label income;
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
	private Label priceActual;
    @FXML
	private Label incomeActual;
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
	private TextField priceInput;
    @FXML
	private TextField incomeInput;
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
    	selectVar.setItems(Animal.getFullListe());
		changeVar(selectVar.getValue());
	}
    
    
	public void setText() {
		super.setText();
		
		// description Label
		
	//	identifier.setText(MilkInterface.getStringsFromId(612));
		identifier.textProperty().bind( MilkInterface.getMilkStringsFromId(612).getText()  );
	//	path.setText(MilkInterface.getStringsFromId(613));
		path.textProperty().bind( MilkInterface.getMilkStringsFromId(612).getText()  );
	//	level.setText(MilkInterface.getStringsFromId(614));
		level.textProperty().bind( MilkInterface.getMilkStringsFromId(614).getText()  );
	//	start.setText(MilkInterface.getStringsFromId(615));
		start.textProperty().bind( MilkInterface.getMilkStringsFromId(615).getText()  );
		
	//	price.setText(MilkInterface.getStringsFromId(616));
		price.textProperty().bind( MilkInterface.getMilkStringsFromId(616).getText()  );
	//	income.setText(MilkInterface.getStringsFromId(618));
		income.textProperty().bind( MilkInterface.getMilkStringsFromId(618).getText()  );
	//	need.setText(MilkInterface.getStringsFromId(622));
		need.textProperty().bind( MilkInterface.getMilkStringsFromId(622).getText()  );

	//	name.setText(MilkInterface.getStringsFromId(651));
		name.textProperty().bind( MilkInterface.getMilkStringsFromId(651).getText()  );
	//	txEffect.setText(MilkInterface.getStringsFromId(652));
		txEffect.textProperty().bind( MilkInterface.getMilkStringsFromId(652).getText()  );
	//	description.setText(MilkInterface.getStringsFromId(653));
		description.textProperty().bind( MilkInterface.getMilkStringsFromId(653).getText()  );
	//	quote.setText(MilkInterface.getStringsFromId(654));
		quote.textProperty().bind( MilkInterface.getMilkStringsFromId(654).getText()  );
    }

	public void changeVar(Animal milkVar) {
		if(milkVar!=null){
			
			idValue.setText(""+milkVar.getId());
			lvlActual.setText(""+milkVar.getLvl());
			startActual.setText(""+milkVar.getStart());
			pathActual.setText(""+milkVar.getAttrib());
			
			priceActual.setText(""+milkVar.getPrice());
			incomeActual.setText(""+milkVar.getIncome());
			needActual.setText(""+milkVar.getNeed());
			
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
			needInput.setText(""+milkVar.getNeed());
			
			nameInput.setText(milkVar.getInfo().getName());
			txEfInput.setText(milkVar.getInfo().getTxEffect());
			descInput.setText(milkVar.getInfo().getDesc());
			quoteInput.setText(milkVar.getInfo().getQuote());
		}
	}
}