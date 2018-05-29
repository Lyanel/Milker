package vue;


import java.io.IOException;

import application.Milker;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TitledPane;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.util.Callback;
import modele.MilkImage;
import modele.MilkInfo;
import modele.MilkInterface;
import modele.MilkRs;
import modele.MilkXmlObj;
import modele.intel.Research;
import modele.intel.Synergy;
import modele.intel.Upgrade;
import modele.thing.Animal;
import modele.thing.Building;
import modele.thing.Slave;
import modele.thing.Thing;
import modele.thing.Worker;
import modele.toggle.Toggle;
import modele.toggle.ToggleOption;

/**
 * The controller for the Game window.  
 * @author Lyanel Pheles
 */
public class MilkGameController extends MilkTabControleur {
	private MouseEvent event;
	
    @FXML
    private BorderPane gamePanel;
    @FXML
    private AnchorPane centerAnchor;
    
    private InfoCellController infocell;

    @FXML
    private Label coinLabel;
    @FXML
    private Label agnelLabel;
    @FXML
    private Label bgLab;
    @FXML
    private Label npcLab;
    @FXML
    private Label idolLab;
    @FXML
    private Pane infoPan;
    
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
    private Tab toggleTab;
    @FXML
    private TreeView<MilkXmlObj> toggleTree;
    @FXML
    private TitledPane toolTab;
    @FXML
    private TitledPane idolTab;
    @FXML
    private TitledPane eventTab;
    @FXML
    private Label toolLabel;
    @FXML
    private Label idolLabel;
    @FXML
    private Label eventLabel;
    @FXML
    private ListView<ToggleOption> toolPan;
    @FXML
    private ListView<ToggleOption> idolPan;
    @FXML
    private ListView<ToggleOption> eventPan;
    
    @FXML
    private Tab intelTab;
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
    @FXML
    private ListView<Synergy> synergyPan;

	@FXML
    private void initialize() {}
	
    public void initList() {
    	initInfoPan(centerAnchor);
    	gamePanel.addEventHandler(MouseEvent.MOUSE_MOVED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
            	event = e;
            	if (infocell.isVisible()) infoPan.relocate(event.getSceneX()+10, event.getSceneY()+10);
            }
        });

    	
    	coinLabel.textProperty().bind(this.getMainApp().getModel().getMilkCoin().asString());
    	agnelLabel.setText(MilkInterface.getStringsFromId(602));
    	
    	idolLab.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
            	if (event.getButton()==MouseButton.PRIMARY) getMainApp().getModel().statueClicked();
            }
        });
    	bgLab.setText("");
    	npcLab.setText("");
    	idolLab.setText("");
    	

    	neutralTab.setText(MilkInterface.getStringsFromId(613)+" "+MilkInterface.getStringsFromId(561));
    	neutralTab.setGraphic(MilkImage.getSmallIconFromID(301).getImageView());
    	
    	neutralBuildingTab.setText(MilkInterface.getStringsFromId(501));
    	neutralBuildingPan.setItems(this.getMainApp().getModel().getBuildingNeutral());
    	neutralBuildingPan.setCellFactory(new Callback<ListView<Building>, ListCell<Building>>() {
    	    @SuppressWarnings("rawtypes")
			public ListCell call(ListView<Building> p) {
    	        return new MilkCellThing(getMainApp());
    	    }
    	});
    	neutralWorkerTab.setText(MilkInterface.getStringsFromId(502));
    	neutralWorkerPan.setItems(this.getMainApp().getModel().getWorkerNeutral());
    	neutralWorkerPan.setCellFactory(new Callback<ListView<Worker>, ListCell<Worker>>() {
    	    @SuppressWarnings("rawtypes")
			public ListCell call(ListView<Worker> p) {
    	        return new MilkCellThing(getMainApp());
    	    }
    	});
    	neutralSlaveTab.setText(MilkInterface.getStringsFromId(503));
    	neutralSlavePan.setItems(this.getMainApp().getModel().getSlaveNeutral());
    	neutralSlavePan.setCellFactory(new Callback<ListView<Slave>, ListCell<Slave>>() {
    	    @SuppressWarnings("rawtypes")
			public ListCell call(ListView<Slave> p) {
    	        return new MilkCellThing(getMainApp());
    	    }
    	});
    	neutralAnimalTab.setText(MilkInterface.getStringsFromId(505));
    	neutralAnimalPan.setItems(this.getMainApp().getModel().getAnimalNeutral());
    	neutralAnimalPan.setCellFactory(new Callback<ListView<Animal>, ListCell<Animal>>() {
    	    @SuppressWarnings("rawtypes")
			public ListCell call(ListView<Animal> p) {
    	        return new MilkCellThing(getMainApp());
    	    }
    	});
        
    	scienceTab.setText(MilkInterface.getStringsFromId(613)+" "+MilkInterface.getStringsFromId(562));
    	scienceTab.setGraphic(MilkImage.getSmallIconFromID(302).getImageView());
    	scienceBuildingTab.setText(MilkInterface.getStringsFromId(501));
    	scienceBuildingPan.setItems(this.getMainApp().getModel().getBuildingScience());
    	scienceBuildingPan.setCellFactory(new Callback<ListView<Building>, ListCell<Building>>() {
    	    @SuppressWarnings("rawtypes")
			public ListCell call(ListView<Building> p) {
    	        return new MilkCellThing(getMainApp());
    	    }
    	});
    	scienceWorkerTab.setText(MilkInterface.getStringsFromId(502));
    	scienceWorkerPan.setItems(this.getMainApp().getModel().getWorkerScience());
    	scienceWorkerPan.setCellFactory(new Callback<ListView<Worker>, ListCell<Worker>>() {
    	    @SuppressWarnings("rawtypes")
			public ListCell call(ListView<Worker> p) {
    	        return new MilkCellThing(getMainApp());
    	    }
    	});
    	scienceSlaveTab.setText(MilkInterface.getStringsFromId(503));
    	scienceSlavePan.setItems(this.getMainApp().getModel().getSlaveScience());
    	scienceSlavePan.setCellFactory(new Callback<ListView<Slave>, ListCell<Slave>>() {
    	    @SuppressWarnings("rawtypes")
			public ListCell call(ListView<Slave> p) {
    	        return new MilkCellThing(getMainApp());
    	    }
    	});
    	scienceAnimalTab.setText(MilkInterface.getStringsFromId(505));
    	scienceAnimalPan.setItems(this.getMainApp().getModel().getAnimalScience());
    	scienceAnimalPan.setCellFactory(new Callback<ListView<Animal>, ListCell<Animal>>() {
    	    @SuppressWarnings("rawtypes")
			public ListCell call(ListView<Animal> p) {
    	        return new MilkCellThing(getMainApp());
    	    }
    	});
    	
    	magicTab.setText(MilkInterface.getStringsFromId(613)+" "+MilkInterface.getStringsFromId(563));
    	magicTab.setGraphic(MilkImage.getSmallIconFromID(303).getImageView());
    	magicBuildingTab.setText(MilkInterface.getStringsFromId(501));
    	magicBuildingPan.setItems(this.getMainApp().getModel().getBuildingMagic());
    	magicBuildingPan.setCellFactory(new Callback<ListView<Building>, ListCell<Building>>() {
    	    @SuppressWarnings("rawtypes")
			public ListCell call(ListView<Building> p) {
    	        return new MilkCellThing(getMainApp());
    	    }
    	});
    	magicWorkerTab.setText(MilkInterface.getStringsFromId(502));
    	magicWorkerPan.setItems(this.getMainApp().getModel().getWorkerMagic());
    	magicWorkerPan.setCellFactory(new Callback<ListView<Worker>, ListCell<Worker>>() {
    	    @SuppressWarnings("rawtypes")
			public ListCell call(ListView<Worker> p) {
    	        return new MilkCellThing(getMainApp());
    	    }
    	});
    	magicSlaveTab.setText(MilkInterface.getStringsFromId(503));
    	magicSlavePan.setItems(this.getMainApp().getModel().getSlaveMagic());
    	magicSlavePan.setCellFactory(new Callback<ListView<Slave>, ListCell<Slave>>() {
    	    @SuppressWarnings("rawtypes")
			public ListCell call(ListView<Slave> p) {
    	        return new MilkCellThing(getMainApp());
    	    }
    	});
    	magicAnimalTab.setText(MilkInterface.getStringsFromId(505));
    	magicAnimalPan.setItems(this.getMainApp().getModel().getAnimalMagic());
    	magicAnimalPan.setCellFactory(new Callback<ListView<Animal>, ListCell<Animal>>() {
    	    @SuppressWarnings("rawtypes")
			public ListCell call(ListView<Animal> p) {
    	        return new MilkCellThing(getMainApp());
    	    }
    	});

    	toggleTab.setText(MilkInterface.getStringsFromId(556));
    	toggleTab.setGraphic(MilkImage.getSmallIconFromID(304).getImageView());
    	initToogleAsAccordion();

    	intelTab.setText(MilkInterface.getStringsFromId(557));
    	intelTab.setGraphic(MilkImage.getSmallIconFromID(305).getImageView());
    	
        researchTab.setText(MilkInterface.getStringsFromId(551));
    	researchPan.setItems(this.getMainApp().getModel().getResearch());
    	researchPan.setCellFactory(new Callback<ListView<Research>, ListCell<Research>>() {
    	    @SuppressWarnings("rawtypes")
			public ListCell call(ListView<Research> p) {
    	        return new MilkCellIntel(getMainApp());
    	    }
    	});
    	upgradeTab.setText(MilkInterface.getStringsFromId(552));
    	upgradePan.setItems(this.getMainApp().getModel().getUpgrade());
    	upgradePan.setCellFactory(new Callback<ListView<Upgrade>, ListCell<Upgrade>>() {
    	    @SuppressWarnings("rawtypes")
			public ListCell call(ListView<Upgrade> p) {
    	        return new MilkCellIntel(getMainApp());
    	    }
    	});
    	synergyTab.setText(MilkInterface.getStringsFromId(553));
    	synergyPan.setItems(this.getMainApp().getModel().getSynergy());
    	synergyPan.setCellFactory(new Callback<ListView<Synergy>, ListCell<Synergy>>() {
    	    @SuppressWarnings("rawtypes")
			public ListCell call(ListView<Synergy> p) {
    	        return new MilkCellIntel(getMainApp());
    	    }
    	});
    	

    	setBGScene(this.getMainApp().getModel().getBuildingNeutral().get(0));
    }
    

    /*
     * Init the toggles as an Accordion with titled panel.
     */
	public void initToogleAsAccordion() {
		ObservableList<Toggle> toggles = this.getMainApp().getModel().getToggles();
		
    	toolTab.setText(null);
    	toolLabel.setText(toggles.get(0).getInfo().getName());
    	toolPan.setItems(toggles.get(0).getObservableOptions());
    	toolPan.setCellFactory(new Callback<ListView<ToggleOption>, ListCell<ToggleOption>>() {
    	    @SuppressWarnings("rawtypes")
			public ListCell call(ListView<ToggleOption> p) {
    	        return new MilkCellOption(getMainApp());
    	    }
    	});
    	
    	idolTab.setText(null);
    	idolLabel.setText(toggles.get(1).getInfo().getName());
    	idolPan.setItems(toggles.get(1).getObservableOptions());
    	idolPan.setCellFactory(new Callback<ListView<ToggleOption>, ListCell<ToggleOption>>() {
    	    @SuppressWarnings("rawtypes")
			public ListCell call(ListView<ToggleOption> p) {
    	        return new MilkCellOption(getMainApp());
    	    }
    	});

    	eventTab.setVisible(false);
    	eventTab.setText(null);
    	eventLabel.setText(toggles.get(2).getInfo().getName());
    	eventPan.setItems(toggles.get(2).getObservableOptions());
    	eventPan.setCellFactory(new Callback<ListView<ToggleOption>, ListCell<ToggleOption>>() {
    	    @SuppressWarnings("rawtypes")
			public ListCell call(ListView<ToggleOption> p) {
    	        return new MilkCellOption(getMainApp());
    	    }
    	});
    	
    	setIdolScene(toggles.get(1).getToggleOptions().get(0));
	}
    

    /*
     * Init the toggles as a tree view.
     */
	public void initToogleAsTree() {
    	TreeItem<MilkXmlObj> rootItem = new TreeItem<MilkXmlObj>();
    	toggleTree.setRoot(rootItem);
    	toggleTree.setShowRoot(false);
    	rootItem.setExpanded(true);
    	
    	ObservableList<TreeItem<MilkXmlObj>> togglePan = FXCollections.observableArrayList();
    	
    	for(Toggle toggle:this.getMainApp().getModel().getToggles()){
        	TreeItem<MilkXmlObj> treeToggles = new TreeItem<MilkXmlObj>(toggle);
        	ObservableList<TreeItem<MilkXmlObj>> optionPan = FXCollections.observableArrayList();
        	for(ToggleOption option:toggle.getObservableOptions()){
            	TreeItem<MilkXmlObj> treeOptions = new TreeItem<MilkXmlObj>(option);
            	optionPan.add(treeOptions);
        	}
    		treeToggles.getChildren().addAll(optionPan);
    		togglePan.add(treeToggles);
    	}
    	rootItem.getChildren().addAll(togglePan);
	}
    
    /*
     * Init a little panel displaying info and following mouse.
     */
	public void initInfoPan(AnchorPane game) {
		try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Milker.class.getResource(MilkRs.infoCell));
            AnchorPane infoPanAnch = (AnchorPane) loader.load();
            infoPan.getChildren().add(infoPanAnch);
            
           // infoPan.setPrefSize( ((AnchorPane)rootLayout.getCenter()).getWidth(), ((AnchorPane)rootLayout.getCenter()).getHeight());
            infocell = loader.getController();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

	public void setSlavesTabVisible(boolean visible) {
		neutralSlavePan.setVisible(visible);
		scienceSlaveTab.setVisible(visible);
		magicSlaveTab.setVisible(visible);
	}

	public void setInfoVisible(MilkInfo info, boolean visible) {
		if(info!=null && visible){
			infocell.setValue(info);
		}else{
			infocell.setValue(new MilkInfo());
		}
		infocell.setVisible(visible);
    	if (infocell.isVisible() && event!=null) infoPan.relocate(0, event.getSceneY());
	}

	public void setBGScene(Thing value) {		
		centerAnchor.setBackground(new Background(new BackgroundFill(new ImagePattern(value.getScene().getImage()), null, null)));
	}

	public void setNPCScene(Thing value) {
		ImageView img = value.getScene().getImageView();
		img.fitHeightProperty().bind(centerAnchor.heightProperty());
		img.setPreserveRatio(true);
		npcLab.setGraphic(img);
	}

	public void setIdolScene(ToggleOption value) {
		ImageView img = value.getScene().getImageView();
		img.fitHeightProperty().bind(centerAnchor.heightProperty());
		img.setPreserveRatio(true);
		idolLab.setGraphic(img);
	}
    
    
    /**
    * Cet �couteur est appel� lorsque la propri�t� value change.
    */
    /*
    private final ChangeListener<Double> valueChangeListener = (ObservableValue<? extends Double> observableValue, Double oldValue, Double newValue) -> {
        updateUI(newValue);
    };

    private void updateUI(Double value) {
    	if(((Milker) getApplication()).getModel().isThingVisible(thing) ){
    		rootPane.setVisible(true);
            iLabel.setText(thing.getStringId());
            nameLabel.setText(thing.getInfo().getName());
            priceLabel.setText(thing.getPrice().getStringCoin());
            quantLabel.setText(thing.getAttrib().getStringQuant());
            if(!((Milker) getApplication()).getModel().isThingbuyable(thing))nameLabel.getStyleClass().add(MilkRs.cssNotBuyable);
            else nameLabel.getStyleClass().remove(MilkRs.cssNotBuyable);
    	} else rootPane.setVisible(false);
    }*/
}