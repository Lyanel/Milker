package modele.intel;

import org.w3c.dom.Element;

import modele.ParseMilkFile;
import modele.baseObject.MilkKind;
import modele.baseObject.MilkObj;
import modele.carac.MilkTree;

public class Event extends MilkObj {
	
	public static final String noeud = "event", xmlMonth="month";
	public String getNoeud() {return noeud;}
	
	// Fields
	
	private MilkTree tree;
	private Integer month;
	private boolean active;
	
	// Constructors
	
	public Event() {
		super();
		this.active=false;
		this.tree = new MilkTree();
		this.setKind(MilkKind.Event);
	}
	public Event(Element milkElement) {
		super();
		this.active=false;
		this.tree = new MilkTree();
		this.setValueFromNode(milkElement);
		this.setKind(MilkKind.Event);
	}
	public Event(Event original) {
		super(original);
		this.active=false;
		this.month = new Integer(original.getMonth());
		this.tree = new MilkTree(original.getTree());
	}

	// Set value from Element methods
	
	@Override
	public void setValueFromNode(Element milkElement) {
		super.setValueFromNode(milkElement);
		this.setTree(milkElement);
		this.setMonth(milkElement);
	}
	public void setTree(Element milkElement) {
		this.tree.setValueFromNode(milkElement);
	}
	public void setMonth(Element milkElement) {
		Integer temp=null;
		temp=ParseMilkFile.getXmlIntAttribute(milkElement,xmlMonth);
		if (temp != null) this.month=temp;
	}
	// field methods

	public MilkTree getTree() {
		return this.tree;
	}
	public void setTree(MilkTree tree) {
		this.tree = tree;
	}
	
	public Integer getMonth() {
		return month;
	}
	public void setMonth(Integer month) {
		this.month = month;
	}
	
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	
	// other object methods

	@Override
	public boolean allZero()  {
		boolean temp = super.allZero();
		if(this.tree!=null && !this.tree.allZero()) temp= false;
		if(this.month!=null && this.month!=0) temp= false;
		return temp;
	}
	
}
