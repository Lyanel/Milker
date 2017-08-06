package vue;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.util.Callback;
import modele.MilkImage;
import modele.MilkInterface;
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
public class MilkGameSController extends MilkTabControleur {

    @FXML
    private Label coinLabel;
    @FXML
    private Circle venusClick;
    
    @FXML
    private Tab neutralTab;
    @FXML
    private TitledPane neutralBuildingTab;
    @FXML
    private ListView<Building> neutralBuildingPan;
    @FXML
    private TitledPane neutralWorkerTab;
    @FXML
    private ListView<Worker> neutralWorkerPan;
    @FXML
    private TitledPane neutralSlaveTab;
    @FXML
    private ListView<Slave> neutralSlavePan;
    @FXML
    private TitledPane neutralAnimalTab;
    @FXML
    private ListView<Animal> neutralAnimalPan;
    
    @FXML
    private Tab scienceTab;
    @FXML
    private TitledPane scienceBuildingTab;
    @FXML
    private ListView<Building> scienceBuildingPan;
    @FXML
    private TitledPane scienceWorkerTab;
    @FXML
    private ListView<Worker> scienceWorkerPan;
    @FXML
    private TitledPane scienceSlaveTab;
    @FXML
    private ListView<Slave> scienceSlavePan;
    @FXML
    private TitledPane scienceAnimalTab;
    @FXML
    private ListView<Animal> scienceAnimalPan;
    
    @FXML
    private Tab magicTab;
    @FXML
    private TitledPane magicBuildingTab;
    @FXML
    private ListView<Building> magicBuildingPan;
    @FXML
    private TitledPane magicWorkerTab;
    @FXML
    private ListView<Worker> magicWorkerPan;
    @FXML
    private TitledPane magicSlaveTab;
    @FXML
    private ListView<Slave> magicSlavePan;
    @FXML
    private TitledPane magicAnimalTab;
    @FXML
    private ListView<Animal> magicAnimalPan;
    
    @FXML
    private Tab intelTab;
    @FXML
    private TitledPane toolTab;
    //  @FXML
    //  private ListView<Tool> toolPan;
    @FXML
    private TitledPane researchTab;
    @FXML
    private ListView<Research> researchPan;
    @FXML
    private TitledPane upgradeTab;
    @FXML
    private ListView<Upgrade> upgradePan;
    @FXML
    private TitledPane synergyTab;
    //  @FXML
    //  private ListView<Tool> synergyPan;

	@FXML
    private void initialize() {}
	
    public void initList() {
    	venusClick.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
            	if (event.getButton()==MouseButton.PRIMARY) getMainApp().getModel().statueClicked();
            }
        });
    	coinLabel.textProperty().bind(this.getMainApp().getModel().getMilkCoinString());
    	

    	neutralTab.setText(MilkInterface.getStringsFromId(613)+" "+MilkInterface.getStringsFromId(561));
    //  neutralTab.setText("");
    	neutralTab.setGraphic(MilkImage.getSmallIconFromID(301).getImageView());
    //	neutralTab.setGraphic(new Circle(0, 0, 10));
    	
    	
    	neutralBuildingTab.setText(MilkInterface.getStringsFromId(501));
    	neutralBuildingPan.setItems(this.getMainApp().getModel().getBuildingNeutral());
    	neutralBuildingPan.setCellFactory(new Callback<ListView<Building>, ListCell<Building>>() {
    	    @SuppressWarnings("rawtypes")
			public ListCell call(ListView<Building> p) {
    	        return new ThingCell(getMainApp());
    	    }
    	});
    	neutralWorkerTab.setText(MilkInterface.getStringsFromId(502));
    	neutralWorkerPan.setItems(this.getMainApp().getModel().getWorkerNeutral());
    	neutralWorkerPan.setCellFactory(new Callback<ListView<Worker>, ListCell<Worker>>() {
    	    @SuppressWarnings("rawtypes")
			public ListCell call(ListView<Worker> p) {
    	        return new ThingCell(getMainApp());
    	    }
    	});
    	neutralSlaveTab.setText(MilkInterface.getStringsFromId(503));
    	neutralSlavePan.setItems(this.getMainApp().getModel().getSlaveNeutral());
    	neutralSlavePan.setCellFactory(new Callback<ListView<Slave>, ListCell<Slave>>() {
    	    @SuppressWarnings("rawtypes")
			public ListCell call(ListView<Slave> p) {
    	        return new ThingCell(getMainApp());
    	    }
    	});
    	neutralAnimalTab.setText(MilkInterface.getStringsFromId(505));
    	neutralAnimalPan.setItems(this.getMainApp().getModel().getAnimalNeutral());
    	neutralAnimalPan.setCellFactory(new Callback<ListView<Animal>, ListCell<Animal>>() {
    	    @SuppressWarnings("rawtypes")
			public ListCell call(ListView<Animal> p) {
    	        return new ThingCell(getMainApp());
    	    }
    	});
        
    	scienceTab.setText(MilkInterface.getStringsFromId(613)+" "+MilkInterface.getStringsFromId(562));
    	scienceTab.setGraphic(MilkImage.getSmallIconFromID(302).getImageView());
    	scienceBuildingTab.setText(MilkInterface.getStringsFromId(501));
    	scienceBuildingPan.setItems(this.getMainApp().getModel().getBuildingScience());
    	scienceBuildingPan.setCellFactory(new Callback<ListView<Building>, ListCell<Building>>() {
    	    @SuppressWarnings("rawtypes")
			public ListCell call(ListView<Building> p) {
    	        return new ThingCell(getMainApp());
    	    }
    	});
    	scienceWorkerTab.setText(MilkInterface.getStringsFromId(502));
    	scienceWorkerPan.setItems(this.getMainApp().getModel().getWorkerScience());
    	scienceWorkerPan.setCellFactory(new Callback<ListView<Worker>, ListCell<Worker>>() {
    	    @SuppressWarnings("rawtypes")
			public ListCell call(ListView<Worker> p) {
    	        return new ThingCell(getMainApp());
    	    }
    	});
    	scienceSlaveTab.setText(MilkInterface.getStringsFromId(503));
    	scienceSlavePan.setItems(this.getMainApp().getModel().getSlaveScience());
    	scienceSlavePan.setCellFactory(new Callback<ListView<Slave>, ListCell<Slave>>() {
    	    @SuppressWarnings("rawtypes")
			public ListCell call(ListView<Slave> p) {
    	        return new ThingCell(getMainApp());
    	    }
    	});
    	scienceAnimalTab.setText(MilkInterface.getStringsFromId(505));
    	scienceAnimalPan.setItems(this.getMainApp().getModel().getAnimalScience());
    	scienceAnimalPan.setCellFactory(new Callback<ListView<Animal>, ListCell<Animal>>() {
    	    @SuppressWarnings("rawtypes")
			public ListCell call(ListView<Animal> p) {
    	        return new ThingCell(getMainApp());
    	    }
    	});
    	
    	magicTab.setText(MilkInterface.getStringsFromId(613)+" "+MilkInterface.getStringsFromId(563));
    	magicTab.setGraphic(MilkImage.getSmallIconFromID(303).getImageView());
    	magicBuildingTab.setText(MilkInterface.getStringsFromId(501));
    	magicBuildingPan.setItems(this.getMainApp().getModel().getBuildingMagic());
    	magicBuildingPan.setCellFactory(new Callback<ListView<Building>, ListCell<Building>>() {
    	    @SuppressWarnings("rawtypes")
			public ListCell call(ListView<Building> p) {
    	        return new ThingCell(getMainApp());
    	    }
    	});
    	magicWorkerTab.setText(MilkInterface.getStringsFromId(502));
    	magicWorkerPan.setItems(this.getMainApp().getModel().getWorkerMagic());
    	magicWorkerPan.setCellFactory(new Callback<ListView<Worker>, ListCell<Worker>>() {
    	    @SuppressWarnings("rawtypes")
			public ListCell call(ListView<Worker> p) {
    	        return new ThingCell(getMainApp());
    	    }
    	});
    	magicSlaveTab.setText(MilkInterface.getStringsFromId(503));
    	magicSlavePan.setItems(this.getMainApp().getModel().getSlaveMagic());
    	magicSlavePan.setCellFactory(new Callback<ListView<Slave>, ListCell<Slave>>() {
    	    @SuppressWarnings("rawtypes")
			public ListCell call(ListView<Slave> p) {
    	        return new ThingCell(getMainApp());
    	    }
    	});
    	magicAnimalTab.setText(MilkInterface.getStringsFromId(505));
    	magicAnimalPan.setItems(this.getMainApp().getModel().getAnimalMagic());
    	magicAnimalPan.setCellFactory(new Callback<ListView<Animal>, ListCell<Animal>>() {
    	    @SuppressWarnings("rawtypes")
			public ListCell call(ListView<Animal> p) {
    	        return new ThingCell(getMainApp());
    	    }
    	});
    	
    	intelTab.setText(MilkInterface.getStringsFromId(557));
    	intelTab.setGraphic(MilkImage.getSmallIconFromID(304).getImageView());
    	toolTab.setText(MilkInterface.getStringsFromId(556));
        researchTab.setText(MilkInterface.getStringsFromId(551));
    	researchPan.setItems(this.getMainApp().getModel().getResearch());
    	upgradeTab.setText(MilkInterface.getStringsFromId(552));
    	upgradePan.setItems(this.getMainApp().getModel().getUpgrade());
    	synergyTab.setText(MilkInterface.getStringsFromId(553));
    }
}