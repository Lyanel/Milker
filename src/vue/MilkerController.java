package vue;

import application.Milker;
import javafx.scene.Scene;

/**
 * The controller for the Main Milker window.  
 * @author Lyanel Pheles
 */
public class MilkerController {

    // Reference to the main application
    private Milker milker;
    private Scene scene;
    
    public void setMainApp(Milker milker) {
        this.milker = milker;
    }

    public Milker getMainApp() {
		return milker;
	}

    public Scene getScene() {
		return scene;
	}
	public void setScene(Scene scene) {
		this.scene=scene;
	}
}