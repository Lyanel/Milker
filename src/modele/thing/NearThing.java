package modele.thing;

import modele.baseObject.MilkImage;
import modele.baseObject.MilkPricedObj;

import java.util.ArrayList;

import org.w3c.dom.Element;

public class NearThing extends MilkPricedObj {
	
	public static void setScene(ArrayList<? extends NearThing> things, ArrayList<Element> elementScenes) {
		for (Element elementScene: elementScenes) {
			try {
				NearThing test = new NearThing(elementScene);
				test.setScene(elementScene);
				for (NearThing thing:things){
					if (test.getId().intValue() == thing.getId().intValue()){
						thing.setScene(test.getScene());
						break;
					}
				}
			} catch (Exception e) {e.printStackTrace();}
		}
	}
	
	// field
	
	private MilkImage scene;

	// Constructors
	
	public NearThing() {
		super();
		this.scene = new MilkImage();
	}
	public NearThing(Element milkElement) {
		super();
		this.scene = new MilkImage();
		this.setValueFromNode(milkElement);
	}
	public NearThing(NearThing original) {
		super(original);
		this.scene = new MilkImage(original.getScene());
	}

	// Set value from Element methods

	public void setScene(Element milkElement) {
		this.scene.setValueFromNode(milkElement);
	}
	
	// field methods
	
	public void setScene(MilkImage scene) {
		this.scene = scene;
	}
	public MilkImage getScene() {
		return scene;
	}
	
	// other object methods

	@Override
	public boolean allZero()  {
		boolean temp = super.allZero();
		if(this.scene!=null && !this.scene.allZero()) temp= false;
		return temp;
	}
}