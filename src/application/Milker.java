package application;

import java.io.IOException;

import controleur.GameModele;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import modele.MilkInfo;
import modele.MilkInterface;
import modele.MilkRs;
import modele.MilkXmlObj;
import modele.thing.Animal;
import modele.thing.Building;
import modele.thing.Slave;
import modele.thing.Thing;
import modele.thing.Worker;
import vue.MilkEditorController;
import vue.MilkGameSController;
import vue.MilkMenuController;
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
	private MilkGameSController gameController;
	
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle(MilkInterface.getStringsFromId(1));
        this.primaryStage.setOnCloseRequest(e -> close(e));
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
            gameController = loader.getController();
            gameController.setMainApp(this);
            gameController.initList();
            setStatutMessage(MilkInterface.getStringsFromId(1001));
        //    initInfoPan(game);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /*
     * show the info of the OnMoussed cell.
     */
	public void setInfoPanVisible(MilkInfo info, boolean visible) {
		gameController.setInfoVisible(info, visible);
		if(info!=null && visible){
			setStatutMessage(info.toStringTextChild());
		} else{
			setStatutMessage("");
		}
	}
	public void showThing(Thing value) {
		if (value instanceof Building) gameController.setBGScene(value);
		if (value instanceof Slave || value instanceof Worker || value instanceof Animal) gameController.setNPCScene(value);
	}

	public void showMilkObject(MilkXmlObj value) {
		// TODO Auto-generated method stub
		
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
  /*  public void openOption() {
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
    }*/
    public void openOption() {
        try {
	        FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(Milker.class.getResource(MilkRs.milkOption));
	        AnchorPane editor = (AnchorPane) loader.load();
	        Stage stage = new Stage();
	        stage.setTitle(MilkInterface.getStringsFromId(31));
	        Scene scene = new Scene(editor);
	        stage.setScene(scene);
	        stage.show();
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
    
    /**
     * Close the game.
     * @param e 
     */
	public void close(WindowEvent e) {
		Platform.exit();
        System.exit(0);
	}
}
