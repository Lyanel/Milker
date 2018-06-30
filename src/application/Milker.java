package application;

import java.io.IOException;

import controleur.GameModele;
import controleur.GameOption;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import modele.MilkRs;
import modele.baseObject.MilkInfo;
import modele.baseObject.MilkInterface;
import modele.baseObject.MilkXmlObj;
import modele.thing.Animal;
import modele.thing.Building;
import modele.thing.Slave;
import modele.thing.Thing;
import modele.thing.Worker;
import modele.toggle.ToggleOption;
import vue.MilkMenuController;
import vue.MilkOptionController;
import vue.MilkStatutController;
import vue.MilkerController;
import vue.editor.MilkEditorController;
import vue.game.MilkGameController;
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
	private MilkGameController gameController;
	
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
       // this.primaryStage.setTitle(MilkInterface.getStringsFromId(1));
       //this.primaryStage.titleProperty().bind( Bindings.selectString( MilkInterface.getMilkStringsFromId(1),"text" ) );
        this.primaryStage.titleProperty().bind( MilkInterface.getMilkStringsFromId(1).getText()  );
        
        this.primaryStage.setOnCloseRequest(e -> close(e));
      //  this.model = new GameModele ();
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

	        setStageCss(rootLayout.getScene());
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
        model = new GameModele(this);
        try {
            FXMLLoader loader = new FXMLLoader();
          //  loader.setLocation(Milker.class.getResource(MilkRs.milkGame));
            loader.setLocation(Milker.class.getResource(MilkRs.milkGame));
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
	}
	
	public void showMilkXmlObj(MilkXmlObj value) {
		if (value instanceof Building) gameController.setBGScene((Thing) value);
		if (value instanceof Slave || value instanceof Worker || value instanceof Animal) gameController.setNPCScene((Thing) value);
		if (value instanceof ToggleOption) gameController.setIdolScene((ToggleOption) value);
	}

	public void setSlavesTabVisible(boolean visible) {
		gameController.setSlavesTabVisible(visible);
	}

	public void setIdolTabVisible(boolean visible) {
		gameController.setIdolTabVisible(visible);
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
	        AnchorPane optionAnchor = (AnchorPane) loader.load();
	        Stage stage = new Stage();
	  //      stage.setTitle(MilkInterface.getStringsFromId(31));
	        stage.titleProperty().bind( MilkInterface.getMilkStringsFromId(31).getText()  );
	        Scene scene = new Scene(optionAnchor);
	        stage.setScene(scene);
	        ((MilkOptionController) loader.getController()).setScene(scene);
	        ((MilkOptionController) loader.getController()).setMainApp(this);
	        stage.show();
	        setStageCss(scene);
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
	
    /**
     * Apply Css found in the option to the given scene.
     * @param e 
     */
	public void setStageCss(Scene scene) {
		scene.getStylesheets().clear();
		scene.getStylesheets().add(GameOption.getCssUrl(GameOption.getOption().getCss()));
	}

	public Scene getScene() {
		// TODO Auto-generated method stub
		return rootLayout.getScene();
	}
}
