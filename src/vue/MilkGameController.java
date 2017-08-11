package vue;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.util.Callback;
import modele.intel.Research;
import modele.intel.Upgrade;
import modele.thing.Animal;
import modele.thing.Building;
import modele.thing.Slave;
import modele.thing.Worker;

/**
 * The controller for the Game window.  
 * @author Lyanel Pheles
 */
public class MilkGameController extends MilkTabControleur {

    @FXML
    private Label coinLabel;
    @FXML
    private Circle venusClick;
    @FXML
    private ListView<Research> researchPan;
    @FXML
    private ListView<Upgrade> upgradePan;
  //  @FXML
  //  private ListView<Tool> toolPan;
    @FXML
    private ListView<Building> neutralBuildingPan;
    @FXML
    private ListView<Worker> neutralWorkerPan;
    @FXML
    private ListView<Slave> neutralSlavePan;
    @FXML
    private ListView<Animal> neutralAnimalPan;
    @FXML
    private ListView<Building> scienceBuildingPan;
    @FXML
    private ListView<Worker> scienceWorkerPan;
    @FXML
    private ListView<Slave> scienceSlavePan;
    @FXML
    private ListView<Animal> scienceAnimalPan;
    @FXML
    private ListView<Building> magicBuildingPan;
    @FXML
    private ListView<Worker> magicWorkerPan;
    @FXML
    private ListView<Slave> magicSlavePan;
    @FXML
    private ListView<Animal> magicAnimalPan;

	@FXML
    private void initialize() {}
	
    public void initList() {
    	coinLabel.textProperty().bind(this.getMainApp().getModel().getMilkCoin().asString());
    	
    	venusClick.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
            	if (event.getButton()==MouseButton.PRIMARY) getMainApp().getModel().statueClicked();
            }
        });
    	
    	researchPan.setItems(this.getMainApp().getModel().getResearch());
    	upgradePan.setItems(this.getMainApp().getModel().getUpgrade());
    	
    	neutralBuildingPan.setItems(this.getMainApp().getModel().getBuildingNeutral());
    	neutralBuildingPan.setCellFactory(new Callback<ListView<Building>, ListCell<Building>>() {
    	    @SuppressWarnings("rawtypes")
			public ListCell call(ListView<Building> p) {
    	        return new ThingCell(getMainApp());
    	    }
    	});
    	scienceBuildingPan.setItems(this.getMainApp().getModel().getBuildingScience());
    	scienceBuildingPan.setCellFactory(new Callback<ListView<Building>, ListCell<Building>>() {
    	    @SuppressWarnings("rawtypes")
			public ListCell call(ListView<Building> p) {
    	        return new ThingCell(getMainApp());
    	    }
    	});
    	magicBuildingPan.setItems(this.getMainApp().getModel().getBuildingMagic());
    	magicBuildingPan.setCellFactory(new Callback<ListView<Building>, ListCell<Building>>() {
    	    @SuppressWarnings("rawtypes")
			public ListCell call(ListView<Building> p) {
    	        return new ThingCell(getMainApp());
    	    }
    	});

    	neutralWorkerPan.setItems(this.getMainApp().getModel().getWorkerNeutral());
    	neutralWorkerPan.setCellFactory(new Callback<ListView<Worker>, ListCell<Worker>>() {
    	    @SuppressWarnings("rawtypes")
			public ListCell call(ListView<Worker> p) {
    	        return new ThingCell(getMainApp());
    	    }
    	});
    	scienceWorkerPan.setItems(this.getMainApp().getModel().getWorkerScience());
    	scienceWorkerPan.setCellFactory(new Callback<ListView<Worker>, ListCell<Worker>>() {
    	    @SuppressWarnings("rawtypes")
			public ListCell call(ListView<Worker> p) {
    	        return new ThingCell(getMainApp());
    	    }
    	});
    	magicWorkerPan.setItems(this.getMainApp().getModel().getWorkerMagic());
    	magicWorkerPan.setCellFactory(new Callback<ListView<Worker>, ListCell<Worker>>() {
    	    @SuppressWarnings("rawtypes")
			public ListCell call(ListView<Worker> p) {
    	        return new ThingCell(getMainApp());
    	    }
    	});

    	neutralSlavePan.setItems(this.getMainApp().getModel().getSlaveNeutral());
    	neutralSlavePan.setCellFactory(new Callback<ListView<Slave>, ListCell<Slave>>() {
    	    @SuppressWarnings("rawtypes")
			public ListCell call(ListView<Slave> p) {
    	        return new ThingCell(getMainApp());
    	    }
    	});
    	scienceSlavePan.setItems(this.getMainApp().getModel().getSlaveScience());
    	scienceSlavePan.setCellFactory(new Callback<ListView<Slave>, ListCell<Slave>>() {
    	    @SuppressWarnings("rawtypes")
			public ListCell call(ListView<Slave> p) {
    	        return new ThingCell(getMainApp());
    	    }
    	});
    	magicSlavePan.setItems(this.getMainApp().getModel().getSlaveMagic());
    	magicSlavePan.setCellFactory(new Callback<ListView<Slave>, ListCell<Slave>>() {
    	    @SuppressWarnings("rawtypes")
			public ListCell call(ListView<Slave> p) {
    	        return new ThingCell(getMainApp());
    	    }
    	});

    	neutralAnimalPan.setItems(this.getMainApp().getModel().getAnimalNeutral());
    	neutralAnimalPan.setCellFactory(new Callback<ListView<Animal>, ListCell<Animal>>() {
    	    @SuppressWarnings("rawtypes")
			public ListCell call(ListView<Animal> p) {
    	        return new ThingCell(getMainApp());
    	    }
    	});
    	scienceAnimalPan.setItems(this.getMainApp().getModel().getAnimalScience());
    	scienceAnimalPan.setCellFactory(new Callback<ListView<Animal>, ListCell<Animal>>() {
    	    @SuppressWarnings("rawtypes")
			public ListCell call(ListView<Animal> p) {
    	        return new ThingCell(getMainApp());
    	    }
    	});
    	magicAnimalPan.setItems(this.getMainApp().getModel().getAnimalMagic());
    	magicAnimalPan.setCellFactory(new Callback<ListView<Animal>, ListCell<Animal>>() {
    	    @SuppressWarnings("rawtypes")
			public ListCell call(ListView<Animal> p) {
    	        return new ThingCell(getMainApp());
    	    }
    	});
    }
}