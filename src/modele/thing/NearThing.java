package modele.thing;

import modele.MilkImage;
import modele.intel.Intel;

import org.w3c.dom.Element;

public class NearThing extends Intel implements Cloneable {
	
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
	
	// field methods

	public void setScene(Element milkElement) {
		this.scene.setValueFromNode(milkElement);
	}
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