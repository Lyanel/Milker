package modele.thing;

import modele.MilkImage;
import modele.intel.Intel;

import java.util.Vector;

import org.w3c.dom.Element;

public class NearThing extends Intel implements Cloneable {
	
	public static void setScene(@SuppressWarnings("rawtypes") Vector things, Vector<Element> elementScenes) {
		for (Element elementScene: elementScenes) {
			try {
				NearThing test = new NearThing(elementScene);
				test.setScene(elementScene);
				for (Object object:things){
					NearThing thing = (NearThing) object;
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
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		NearThing clone = (NearThing) super.clone();
		if (this.scene!=null) clone.setScene((MilkImage) this.scene.clone());
		return clone;
	}
}