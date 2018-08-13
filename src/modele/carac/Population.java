package modele.carac;

import org.w3c.dom.Element;

import modele.baseObject.MilkKind;

public class Population extends MilkKind {
	
	public static final String noeud = "pop";
	public String getNoeud() {return noeud;}

	// Fields
	
	private MilkTree tree;
	private Quantity quant;

	// Constructors
	public Population() {
		super();
		this.tree = new MilkTree();
		this.quant = new Quantity();
	}
	public Population(Element milkElement) {
		super();
		this.tree = new MilkTree();
		this.quant = new Quantity();
		this.setValueFromNode(milkElement);
	}
	public Population(Population original) {
		super(original);
		this.tree = new MilkTree(original.getTree());
		this.quant = new Quantity(original.getQuantity());
	}

	// Set value from Element methods
	
	@Override
	public void setValueFromNode(Element milkElement) {
		Element thisElement = this.getThisOptionalChildFromParent(milkElement);
		super.setValueFromNode(thisElement);
		this.setTree(thisElement);
		this.setQuantity(thisElement);
		if(this.getQuantity().getQuant()>0)this.setMod(1);		
	}
	public void setTree(Element milkElement) {
		this.tree.setValueFromNode(milkElement);
	}
	public void setQuantity(Element milkElement) {
		this.quant.setValueFromNode(milkElement);
	}
	
	// field methods
	
	public MilkTree getTree() {
		return this.tree;
	}
	public void setTree(MilkTree tree) {
		this.tree = tree;
	}
	
	public Quantity getQuantity() {
		return quant;
	}
	public void setQuantity(Quantity quant) {
		this.quant = quant;
	}
	
	// toString & toXml methods
	
	@Override
	public String toStringAttrib() {
		String temp = super.toStringAttrib();
		if (this.tree != null) temp = this.tree.toStringAttrib();
		return temp;
	}
	@Override
	public String toXmlAttrib() {
		String temp = super.toXmlAttrib();
		if (this.tree != null) temp = this.tree.toXmlAttrib();
		return temp;
	}
	
	@Override
	public String toStringStatChild() {
		String temp = super.toXmlStatChild();
		if (this.quant != null) temp = this.quant.toXmlStatChild();
		return temp;
	}
	@Override
	public String toXmlStatChild() {
		String temp = super.toXmlStatChild();
		if (this.quant != null) temp = this.quant.toXmlStatChild();
		return temp;
	}
	
	// other object methods

	@Override
	public boolean allZero()  {
		boolean temp = super.allZero();
		if(this.tree!=null && !this.tree.allZero()) temp= false;
		if(this.quant!=null && !this.quant.allZero()) temp= false;
		return temp;
	}
}
