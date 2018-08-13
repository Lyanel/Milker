package vue.game;


import java.io.IOException;

import application.Milker;
import controleur.ViewLock;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TitledPane;
import javafx.scene.control.TreeView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.util.Callback;
import modele.MilkRs;
import modele.baseObject.MilkImage;
import modele.baseObject.MilkInfo;
import modele.baseObject.MilkInterface;
import modele.baseObject.MilkXmlObj;
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
import vue.editor.MilkTabControleur;

/**
 * The controller for the Game window.  
 * @author Lyanel Pheles
 */
public class MilkGameController extends MilkTabControleur {
	private MouseEvent event;
	private EventHandler<MouseEvent> InfoPanMover;
	
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
    private ImageView npcPic;
    @FXML
    private ImageView idolPic;
    @FXML
    private Pane infoPan;
    
    @FXML
    private GridPane gameTab;
    
    @FXML
    private TabPane tabList;
    
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
    private ImageView toolIcon;
    @FXML
    private ImageView idolIcon;
    @FXML
    private ImageView eventIcon;
    @FXML
    private Label toolLabel;
    @FXML
    private Label idolLabel;
    @FXML
    private Label eventLabel;
    @FXML
    private Label priceLabel1;
    @FXML
    private Label priceLabel2;
    @FXML
    private Label priceLabel3;
    @FXML
    private Label toolPrice;
    @FXML
    private Label idolPrice;
    @FXML
    private Label eventPrice;
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
    	InfoPanMover = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
            	event = e;
            	Platform.runLater(new Runnable() {
					public void run() {
						infoPan.relocate(0, event.getSceneY()-(infoPan.getHeight()/2));
					}
				});
            }
        };
        
    	coinLabel.textProperty().bind( Bindings.concat(this.getMainApp().getModel().getMilkCoin().asString(), " ", MilkInterface.getMilkStringsFromId(601).getText()));
    	agnelLabel.textProperty().bind(Bindings.concat(this.getMainApp().getModel().getAgnelCoin().asString(), " ", MilkInterface.getMilkStringsFromId(603).getText()));
    	
    	idolPic.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
            	if (event.getButton()==MouseButton.PRIMARY) getMainApp().getModel().statueClicked();
            }
        });
    	
    	initNeutralTab();
    	initScienceTab();
    	initMagicTab();
    	
    	tabList.getTabs().remove(scienceTab);
    	tabList.getTabs().remove(magicTab);
    	
    	initToogleAsAccordion();
    	initIntelTab();
    	
		npcPic.fitHeightProperty().bind(centerAnchor.heightProperty());
		npcPic.setPreserveRatio(true);
		idolPic.fitHeightProperty().bind(centerAnchor.heightProperty());
		idolPic.setPreserveRatio(true);
    	setBGScene(this.getMainApp().getModel().getBuildingNeutral().get(0));
    }

    /*
     * Init the Neutral panel.
     */
    public void initNeutralTab() {
    	neutralTab.textProperty().bind( Bindings.concat(MilkInterface.getMilkStringsFromId(613).getText(), " ", MilkInterface.getMilkStringsFromId(561).getText() ) );
        neutralTab.setGraphic(MilkImage.getSmallIconFromID(301).getImageView());
        neutralBuildingTab.textProperty().bind( MilkInterface.getMilkStringsFromId(501).getText()  );
    	neutralBuildingPan.addEventHandler(MouseEvent.MOUSE_MOVED, InfoPanMover);
    	neutralBuildingPan.setItems(this.getMainApp().getModel().getBuildingNeutral());
    	neutralBuildingPan.setCellFactory(new Callback<ListView<Building>, ListCell<Building>>() {
    	    @SuppressWarnings("rawtypes")
			public ListCell call(ListView<Building> p) {
    	    	MilkCellThing cell = new MilkCellThing(getMainApp());
    	    	cell.addEventHandler(MouseEvent.MOUSE_CLICKED, InfoPanMover);
    	        return cell;
    	    }
    	});
    	neutralWorkerTab.textProperty().bind( MilkInterface.getMilkStringsFromId(502).getText()  );
    	neutralWorkerPan.addEventHandler(MouseEvent.MOUSE_MOVED, InfoPanMover);
    	neutralWorkerPan.setItems(this.getMainApp().getModel().getWorkerNeutral());
    	neutralWorkerPan.setCellFactory(new Callback<ListView<Worker>, ListCell<Worker>>() {
    	    @SuppressWarnings("rawtypes")
			public ListCell call(ListView<Worker> p) {
    	        return new MilkCellThing(getMainApp());
    	    }
    	});
    	neutralSlaveTab.textProperty().bind( MilkInterface.getMilkStringsFromId(503).getText()  );
    	neutralSlaveTab.visibleProperty().bind(ViewLock.getBind(ViewLock.SLA_PAN));
    	neutralSlavePan.addEventHandler(MouseEvent.MOUSE_MOVED, InfoPanMover);
    	neutralSlavePan.setItems(this.getMainApp().getModel().getSlaveNeutral());
    	neutralSlavePan.setCellFactory(new Callback<ListView<Slave>, ListCell<Slave>>() {
    	    @SuppressWarnings("rawtypes")
			public ListCell call(ListView<Slave> p) {
    	        return new MilkCellThing(getMainApp());
    	    }
    	});
    	neutralAnimalTab.textProperty().bind( MilkInterface.getMilkStringsFromId(505).getText()  );
    	neutralAnimalPan.addEventHandler(MouseEvent.MOUSE_MOVED, InfoPanMover);
    	neutralAnimalPan.setItems(this.getMainApp().getModel().getAnimalNeutral());
    	neutralAnimalPan.setCellFactory(new Callback<ListView<Animal>, ListCell<Animal>>() {
    	    @SuppressWarnings("rawtypes")
			public ListCell call(ListView<Animal> p) {
    	        return new MilkCellThing(getMainApp());
    	    }
    	});
    }

    /*
     * Init the Science panel.
     */
    public void initScienceTab() {
    	scienceTab.textProperty().bind( Bindings.concat(MilkInterface.getMilkStringsFromId(613).getText(), " ", MilkInterface.getMilkStringsFromId(562).getText() ) );
    //	scienceTab.disableProperty().bind(ViewLock.getBind(ViewLock.SCI_TAB));
    	scienceTab.setGraphic(MilkImage.getSmallIconFromID(302).getImageView());
    	scienceBuildingTab.textProperty().bind( MilkInterface.getMilkStringsFromId(501).getText()  );
    	scienceBuildingPan.addEventHandler(MouseEvent.MOUSE_MOVED, InfoPanMover);
    	scienceBuildingPan.setItems(this.getMainApp().getModel().getBuildingScience());
    	scienceBuildingPan.setCellFactory(new Callback<ListView<Building>, ListCell<Building>>() {
    	    @SuppressWarnings("rawtypes")
			public ListCell call(ListView<Building> p) {
    	        return new MilkCellThing(getMainApp());
    	    }
    	});
    	scienceWorkerTab.textProperty().bind( MilkInterface.getMilkStringsFromId(502).getText()  );
    	scienceWorkerPan.addEventHandler(MouseEvent.MOUSE_MOVED, InfoPanMover);
    	scienceWorkerPan.setItems(this.getMainApp().getModel().getWorkerScience());
    	scienceWorkerPan.setCellFactory(new Callback<ListView<Worker>, ListCell<Worker>>() {
    	    @SuppressWarnings("rawtypes")
			public ListCell call(ListView<Worker> p) {
    	        return new MilkCellThing(getMainApp());
    	    }
    	});
    	scienceSlaveTab.textProperty().bind( MilkInterface.getMilkStringsFromId(503).getText()  );
    	scienceSlaveTab.visibleProperty().bind(ViewLock.getBind(ViewLock.SLA_PAN));
    	scienceSlavePan.addEventHandler(MouseEvent.MOUSE_MOVED, InfoPanMover);
    	scienceSlavePan.setItems(this.getMainApp().getModel().getSlaveScience());
    	scienceSlavePan.setCellFactory(new Callback<ListView<Slave>, ListCell<Slave>>() {
    	    @SuppressWarnings("rawtypes")
			public ListCell call(ListView<Slave> p) {
    	        return new MilkCellThing(getMainApp());
    	    }
    	});
    	scienceAnimalTab.textProperty().bind( MilkInterface.getMilkStringsFromId(505).getText()  );
    	scienceAnimalPan.addEventHandler(MouseEvent.MOUSE_MOVED, InfoPanMover);
    	scienceAnimalPan.setItems(this.getMainApp().getModel().getAnimalScience());
    	scienceAnimalPan.setCellFactory(new Callback<ListView<Animal>, ListCell<Animal>>() {
    	    @SuppressWarnings("rawtypes")
			public ListCell call(ListView<Animal> p) {
    	        return new MilkCellThing(getMainApp());
    	    }
    	});
    }

    /*
     * Init the Magic panel.
     */
    public void initMagicTab() {
    	magicTab.textProperty().bind( Bindings.concat(MilkInterface.getMilkStringsFromId(613).getText(), " ", MilkInterface.getMilkStringsFromId(563).getText() ) );
    //	magicTab.disableProperty().bind(ViewLock.getBind(ViewLock.MAG_TAB));
    	magicTab.setGraphic(MilkImage.getSmallIconFromID(303).getImageView());
    	magicBuildingTab.textProperty().bind( MilkInterface.getMilkStringsFromId(501).getText()  );
    	magicBuildingPan.addEventHandler(MouseEvent.MOUSE_MOVED, InfoPanMover);
    	magicBuildingPan.setItems(this.getMainApp().getModel().getBuildingMagic());
    	magicBuildingPan.setCellFactory(new Callback<ListView<Building>, ListCell<Building>>() {
    	    @SuppressWarnings("rawtypes")
			public ListCell call(ListView<Building> p) {
    	        return new MilkCellThing(getMainApp());
    	    }
    	});
    	magicWorkerTab.textProperty().bind( MilkInterface.getMilkStringsFromId(502).getText()  );
    	magicWorkerPan.addEventHandler(MouseEvent.MOUSE_MOVED, InfoPanMover);
    	magicWorkerPan.setItems(this.getMainApp().getModel().getWorkerMagic());
    	magicWorkerPan.setCellFactory(new Callback<ListView<Worker>, ListCell<Worker>>() {
    	    @SuppressWarnings("rawtypes")
			public ListCell call(ListView<Worker> p) {
    	        return new MilkCellThing(getMainApp());
    	    }
    	});
    	magicSlaveTab.textProperty().bind( MilkInterface.getMilkStringsFromId(503).getText()  );
    	magicSlaveTab.visibleProperty().bind(ViewLock.getBind(ViewLock.SLA_PAN));
    	magicSlavePan.addEventHandler(MouseEvent.MOUSE_MOVED, InfoPanMover);
    	magicSlavePan.setItems(this.getMainApp().getModel().getSlaveMagic());
    	magicSlavePan.setCellFactory(new Callback<ListView<Slave>, ListCell<Slave>>() {
    	    @SuppressWarnings("rawtypes")
			public ListCell call(ListView<Slave> p) {
    	        return new MilkCellThing(getMainApp());
    	    }
    	});
    	magicAnimalTab.textProperty().bind( MilkInterface.getMilkStringsFromId(505).getText()  );
    	magicAnimalPan.addEventHandler(MouseEvent.MOUSE_MOVED, InfoPanMover);
    	magicAnimalPan.setItems(this.getMainApp().getModel().getAnimalMagic());
    	magicAnimalPan.setCellFactory(new Callback<ListView<Animal>, ListCell<Animal>>() {
    	    @SuppressWarnings("rawtypes")
			public ListCell call(ListView<Animal> p) {
    	        return new MilkCellThing(getMainApp());
    	    }
    	});
    }
    
    /*
     * Init the toggles as an Accordion with titled panel.
     */
	public void initToogleAsAccordion() {
    	toggleTab.textProperty().bind( MilkInterface.getMilkStringsFromId(556).getText()  );
    	toggleTab.setGraphic(MilkImage.getSmallIconFromID(304).getImageView());
    	
		ObservableList<Toggle> toggles = this.getMainApp().getModel().getToggles();
		
    	toolTab.setText(null);
    	toolLabel.textProperty().bind( toggles.get(0).getInfo().getObrservableName()  );
    	priceLabel1.textProperty().bind( Bindings.concat(MilkInterface.getMilkStringsFromId(616).getText(), ":" ) );
    	toolPrice.textProperty().bind( Bindings.concat(this.getMainApp().getModel().getToolTogglePrice().asString(), " ", MilkInterface.getMilkStringsFromId(601).getText() ) );
    	toolPan.setItems(toggles.get(0).getObservableOptions());
    	toolPan.addEventHandler(MouseEvent.MOUSE_MOVED, InfoPanMover);
    	toolPan.setCellFactory(new Callback<ListView<ToggleOption>, ListCell<ToggleOption>>() {
    	    @SuppressWarnings("rawtypes")
			public ListCell call(ListView<ToggleOption> p) {
    	        return new MilkCellOptLvl(getMainApp());
    	    }
    	});
    	toolIcon.setImage(toggles.get(0).getIcon().getImage());
    	
    	
    	idolTab.setText(null);
    	idolTab.visibleProperty().bind(ViewLock.getBind(ViewLock.IDOL_PAN));
    	idolLabel.setText(toggles.get(1).getInfo().getName());
    	priceLabel2.textProperty().bind( Bindings.concat(MilkInterface.getMilkStringsFromId(616).getText(), ":" ) );
    	idolPrice.textProperty().bind( Bindings.concat(this.getMainApp().getModel().getIdolTogglePrice().asString(), " ", MilkInterface.getMilkStringsFromId(601).getText() ) );
    	idolPan.setItems(toggles.get(1).getObservableOptions());
    	idolPan.addEventHandler(MouseEvent.MOUSE_MOVED, InfoPanMover);
    	idolPan.setCellFactory(new Callback<ListView<ToggleOption>, ListCell<ToggleOption>>() {
    	    @SuppressWarnings("rawtypes")
			public ListCell call(ListView<ToggleOption> p) {
    	        return new MilkCellOption(getMainApp());
    	    }
    	});
    	idolIcon.setImage(toggles.get(1).getIcon().getImage());

    	eventTab.setVisible(false);
    	eventTab.setText(null);
    	eventLabel.setText(toggles.get(2).getInfo().getName());
    	priceLabel3.textProperty().bind( Bindings.concat(MilkInterface.getMilkStringsFromId(616).getText(), ":" ) );
    	eventPrice.textProperty().bind( Bindings.concat(this.getMainApp().getModel().getEventTogglePrice().asString(), " ", MilkInterface.getMilkStringsFromId(601).getText() ) );
    	eventPan.setItems(toggles.get(2).getObservableOptions());
    	eventPan.addEventHandler(MouseEvent.MOUSE_MOVED, InfoPanMover);
    	eventPan.setCellFactory(new Callback<ListView<ToggleOption>, ListCell<ToggleOption>>() {
    	    @SuppressWarnings("rawtypes")
			public ListCell call(ListView<ToggleOption> p) {
    	        return new MilkCellOption(getMainApp());
    	    }
    	});
    	eventIcon.setImage(toggles.get(2).getIcon().getImage());
    	
    	setIdolScene(toggles.get(1).getToggleOptions().get(0));
	}

    /*
     * Init the Intel panel.
     */
    public void initIntelTab() {
    	intelTab.textProperty().bind( MilkInterface.getMilkStringsFromId(557).getText()  );
    	intelTab.setGraphic(MilkImage.getSmallIconFromID(305).getImageView());
    	
    	researchTab.textProperty().bind( MilkInterface.getMilkStringsFromId(551).getText()  );
        researchPan.addEventHandler(MouseEvent.MOUSE_MOVED, InfoPanMover);
    	researchPan.setItems(this.getMainApp().getModel().getResearch());
    	researchPan.setCellFactory(new Callback<ListView<Research>, ListCell<Research>>() {
    	    @SuppressWarnings("rawtypes")
			public ListCell call(ListView<Research> p) {
    	        return new MilkCellIntel(getMainApp());
    	    }
    	});
    	upgradeTab.textProperty().bind( MilkInterface.getMilkStringsFromId(552).getText()  );
    	upgradePan.addEventHandler(MouseEvent.MOUSE_MOVED, InfoPanMover);
    	upgradePan.setItems(this.getMainApp().getModel().getUpgrade());
    	upgradePan.setCellFactory(new Callback<ListView<Upgrade>, ListCell<Upgrade>>() {
    	    @SuppressWarnings("rawtypes")
			public ListCell call(ListView<Upgrade> p) {
    	        return new MilkCellIntel(getMainApp());
    	    }
    	});
    	synergyTab.textProperty().bind( MilkInterface.getMilkStringsFromId(553).getText()  );
    	synergyPan.setItems(this.getMainApp().getModel().getSynergy());
    	synergyPan.addEventHandler(MouseEvent.MOUSE_MOVED, InfoPanMover);
    	synergyPan.setCellFactory(new Callback<ListView<Synergy>, ListCell<Synergy>>() {
    	    @SuppressWarnings("rawtypes")
			public ListCell call(ListView<Synergy> p) {
    	        return new MilkCellIntel(getMainApp());
    	    }
    	});
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
            infocell = loader.getController();
            setInfoVisible(null,false);
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public void setScienceTabVisible() {
    	if(tabList.getTabs().contains(magicTab))tabList.getTabs().add(2, scienceTab);
    	else tabList.getTabs().add(1, scienceTab);
	}

	public void setMagicTabVisible() {
    	if(tabList.getTabs().contains(scienceTab))tabList.getTabs().add(2, magicTab);
    	else tabList.getTabs().add(1, magicTab);
	}
	
	public void setSlavesTabVisible(boolean visible) {
		neutralSlaveTab.setVisible(visible);
		scienceSlaveTab.setVisible(visible);
		magicSlaveTab.setVisible(visible);
	}

	public void setIdolTabVisible(boolean visible) {
		idolTab.setVisible(visible);
	}

	public void setInfoVisible(MilkInfo info, boolean visible) {
		if(info!=null && visible){
			infocell.setValue(info);
			infocell.setVisible(visible);
		}else{
			infocell.setValue(new MilkInfo());
			infocell.setVisible(visible);
        	Platform.runLater(new Runnable() {
				public void run() {
					infoPan.relocate(-1000, 0);
				}
			});
		}
		infocell.setVisible(visible);
	}

	public void setBGScene(Thing value) {		
		centerAnchor.setBackground(new Background(new BackgroundFill(new ImagePattern(value.getScene().getImage()), null, null)));
	}

	public void setNPCScene(Thing value) {
		npcPic.setImage(value.getScene().getImage());
	}

	public void setIdolScene(ToggleOption value) {
		idolPic.setImage(value.getScene().getImage());
	}
}