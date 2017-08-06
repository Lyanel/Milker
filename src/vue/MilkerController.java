package vue;

import application.Milker;

/**
 * The controller for the Main Milker window.  
 * @author Lyanel Pheles
 */
public class MilkerController {

    // Reference to the main application
    private Milker milker;
    
    public void setMainApp(Milker milker) {
        this.milker = milker;
    }

    public Milker getMainApp() {
		return milker;
	}
}