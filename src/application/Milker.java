package application;

import java.io.IOException;

import controleur.GameModele;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import modele.MilkInfo;
import modele.MilkInterface;
import modele.MilkRs;
import vue.InfoCellController;
import vue.MilkEditorController;
//import vue.MilkGameController;
import vue.MilkGameSController;
import vue.MilkMenuController;
import vue.MilkOptionController;
import vue.MilkStatutController;
import vue.MilkerController;

import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;


/**
 * Main class of the game.
 * @author Lyanel Pheles
 */
public class Milker extends Application {
	
	public static void main(String[] args) {
		launch(args);
	}

 	private Stage primaryStage;
	private BorderPane rootLayout;
	private GameModele model;
	private MilkStatutController statutController;
	private InfoCellController infocell;
	
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle(MilkInterface.getStringsFromId(1));
        this.model = new GameModele ();
        initMilker();
        initMilkMenu();
        initMilkStatut();
    }

    /**
     * Initializes the root layout
     */
    public void initMilker() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Milker.class.getResource(MilkRs.milker));
            rootLayout = (BorderPane) loader.load();
            
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            
            MilkerController controller = loader.getController();
            controller.setMainApp(this);

            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }

    /*
     * Initialize the menu bar.
     */
    public void initMilkMenu() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Milker.class.getResource(MilkRs.milkMenu));
            AnchorPane menu = (AnchorPane) loader.load();
            rootLayout.setTop(menu);
            
            MilkMenuController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
     * Initialize the statut bar.
     */
    public void initMilkStatut() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Milker.class.getResource(MilkRs.milkStatut));
            AnchorPane statut = (AnchorPane) loader.load();
            rootLayout.setBottom(statut);
            
            statutController = loader.getController();
            statutController.setMainApp(this);
            setStatutMessage(MilkInterface.getStringsFromId(1000));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
     * change the message in the statut bar.
     */
	public void setStatutMessage(String message) {
		statutController.setMessage(message);
	}

    /*
     * Open the Game window.
     */
    public void openGame() {
        model = new GameModele();
        try {
            FXMLLoader loader = new FXMLLoader();
          //  loader.setLocation(Milker.class.getResource(MilkRs.milkGame));
            loader.setLocation(Milker.class.getResource(MilkRs.milkGameS));
            AnchorPane game = (AnchorPane) loader.load();
            rootLayout.setCenter(game);

         //   MilkGameController controller = loader.getController();
            MilkGameSController controller = loader.getController();
            controller.setMainApp(this);
            controller.initList();
            setStatutMessage(MilkInterface.getStringsFromId(1001));
            initInfoPan(game);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /*
     * Init a little panel displaying info and following mouse.
     */
	public void initInfoPan(AnchorPane game) {
		try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Milker.class.getResource(MilkRs.infoCell));
            AnchorPane infoPan = (AnchorPane) loader.load();
            game.getChildren().add(infoPan);
            
           // infoPan.setPrefSize( ((AnchorPane)rootLayout.getCenter()).getWidth(), ((AnchorPane)rootLayout.getCenter()).getHeight());
            infocell = loader.getController();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

    /*
     * show the info panel.
     */
	public void setInfoPanVisible(MilkInfo info, boolean visible) {
		if(info!=null && visible){
			infocell.setValue(info);
			setStatutMessage(info.toStringTextChild());
		}
		else{
			infocell.setValue(new MilkInfo());
			setStatutMessage("");
		}
		infocell.setVisible(visible);
	}

    /*
     * Open the editor window.
     */
    public void openEditor() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Milker.class.getResource(MilkRs.milkEditor));
            AnchorPane editor = (AnchorPane) loader.load();
            rootLayout.setCenter(editor);
            
            MilkEditorController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
     * Open the option window.
     */
    public void openOption() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Milker.class.getResource(MilkRs.milkOption));
            AnchorPane editor = (AnchorPane) loader.load();
            rootLayout.setCenter(editor);
            
            MilkOptionController controller = loader.getController();
            System.out.println(controller);
            System.out.println(this);
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Returns the main stage.
     * @return
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }
    
    /**
     * Returns the game modele.
     * @return
     */
	public GameModele getModel() {
		return model;
	}
}
