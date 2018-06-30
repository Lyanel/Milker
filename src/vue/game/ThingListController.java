package vue.game;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;
import modele.baseObject.MilkInterface;
import modele.thing.Animal;
import modele.thing.Building;
import modele.thing.Slave;
import modele.thing.Worker;
import vue.MilkerController;

/**
 * The controller for the Menu Bar.  
 * @author Lyanel Pheles
 */
public class ThingListController extends MilkerController {

    @FXML
    private AnchorPane rootPane;

    @FXML
    private GridPane varPan;
    @FXML
    private TitledPane listBuildingTab;
    @FXML
    private ListView<Building> listBuildingPan;
    @FXML
    private TitledPane listWorkerTab;
    @FXML
    private ListView<Worker> listWorkerPan;
    @FXML
    private TitledPane listSlaveTab;
    @FXML
    private ListView<Slave> listSlavePan;
    @FXML
    private TitledPane listAnimalTab;
    @FXML
    private ListView<Animal> listAnimalPan;


    /**
     * Initialisation du contrôleur.
     */
    @FXML
    public void initialize() {
    	listBuildingTab.setText(MilkInterface.getStringsFromId(501));
    	listWorkerTab.setText(MilkInterface.getStringsFromId(502));
    	listSlaveTab.setText(MilkInterface.getStringsFromId(503));
    	listAnimalTab.setText(MilkInterface.getStringsFromId(505));
    }
    public void initBuilding(ObservableList<Building> buildings) {
    	listBuildingPan.setItems(buildings);
    	listBuildingPan.setCellFactory(new Callback<ListView<Building>, ListCell<Building>>() {
    	    @SuppressWarnings("rawtypes")
			public ListCell call(ListView<Building> p) {
    	        return new MilkCellThing(getMainApp());
    	    }
    	});
    }
    public void initWorker(ObservableList<Worker> workers) {
    	listWorkerPan.setItems(workers);
    	listWorkerPan.setCellFactory(new Callback<ListView<Worker>, ListCell<Worker>>() {
    	    @SuppressWarnings("rawtypes")
			public ListCell call(ListView<Worker> p) {
    	        return new MilkCellThing(getMainApp());
    	    }
    	});
    }
    public void initSlave(ObservableList<Slave> slaves) {
    	listSlavePan.setItems(slaves);
    	listSlavePan.setCellFactory(new Callback<ListView<Slave>, ListCell<Slave>>() {
    	    @SuppressWarnings("rawtypes")
			public ListCell call(ListView<Slave> p) {
    	        return new MilkCellThing(getMainApp());
    	    }
    	});
    }
    public void initAnimal(ObservableList<Animal> Animals) {
    	listAnimalPan.setItems(Animals);
    	listAnimalPan.setCellFactory(new Callback<ListView<Animal>, ListCell<Animal>>() {
    	    @SuppressWarnings("rawtypes")
			public ListCell call(ListView<Animal> p) {
    	        return new MilkCellThing(getMainApp());
    	    }
    	});
    }
    
}
