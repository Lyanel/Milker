package modele.toggle;

import org.w3c.dom.Element;

import modele.ParseMilkFile;
import modele.baseObject.MilkImage;
import modele.baseObject.MilkXmlObj;

public class ToggleScene extends MilkXmlObj {

	public static final String xmlLvl="lvl";
	
	// field
	
	private Integer lvl;
	private MilkImage scene;

	// Constructors
	
	public ToggleScene() {
		super();
		this.setLvl(0);
		this.scene = new MilkImage();
	}
	public ToggleScene(Element milkElement) {
		super();
		this.setLvl(0);
		this.scene = new MilkImage();
		this.setValueFromNode(milkElement);
	}
	public ToggleScene(ToggleScene original) {
		super(original);
		this.lvl = new Integer(original.getLvl());
		this.scene = new MilkImage(original.getScene());
	}
	
	// Set value from Element methods
	
	@Override
	public void setValueFromNode(Element milkElement) {
		super.setValueFromNode(milkElement);
		this.setLvl(milkElement);
	}
	public void setLvl(Element milkElement) {
		Integer temp=null;
		temp=ParseMilkFile.getXmlIntAttribute(milkElement,xmlLvl);
		if (temp != null) this.lvl=temp;
	}
	public void setScene(Element milkElement) {
		this.scene.setValueFromNode(milkElement);
	}
	
	// field methods

	public Integer getLvl() {
		return this.lvl;
	}
	public String getStringLvl() {
		String temp = null;
		if (this.lvl != null) temp = xmlLvl+" : "+this.lvl+". ";
		return temp;
	}
	public String getXmlLvl() {
		String temp = null;
		if (this.lvl != null) temp = " "+xmlLvl+"=\""+lvl+"\"";
		return temp;
	}
	public void setLvl(Integer lvl) {
		this.lvl = lvl;
	}
	
	public MilkImage getScene() {
		return scene;
	}
	public void setScene(MilkImage scene) {
		this.scene = scene;
	}
	
	// toString & toXml methods
	
	@Override
	public String toStringAttrib() {
		String temp = super.toStringAttrib();
		temp+=this.getStringLvl();
		return temp;
	}
	@Override
	public String toXmlAttrib() {
		String temp = super.toXmlAttrib();
		temp+=this.getXmlLvl();
		return temp;
	}
	
	// other object methods

	@Override
	public boolean allZero()  {
		boolean temp = super.allZero();
		if(this.scene!=null && !this.scene.allZero()) temp= false;
		return temp;
	}
	
}