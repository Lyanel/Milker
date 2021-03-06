package modele.carac;

import java.util.ArrayList;

import org.w3c.dom.Element;

import modele.ParseMilkFile;
import modele.XmlHelper;
import modele.thing.Thing;

public class NeededThing extends NeededIntel {
	
	public static final String noeud = "thing", xmlLvl = "lvl";
	public String getNoeud() {return noeud;}

	public static ArrayList<Element> getElementListfromParent(Element parent) {
		ArrayList<Element> temp = null;
		try {
			temp = XmlHelper.getChildrenListByTagName(XmlHelper.getOptionalChild(parent, noeud+"s"),noeud);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return (temp !=null)?temp:new ArrayList<Element>();
	}
	public static ArrayList<? extends NeededThing> getMilkVarList(Element parent) {
		return getMilkVarList(getElementListfromParent(parent));
	}

	public static ArrayList<? extends NeededThing>  getMilkVarList(ArrayList<Element> elementlist) {
		ArrayList<NeededThing> neededThings = new ArrayList<NeededThing>();
		if (elementlist !=null) for (Element elementMilk: elementlist) {
			try {
				NeededThing neededThing = new NeededThing(elementMilk);
				neededThings.add(neededThing);
			} catch (Exception e) {e.printStackTrace();}
		}
		return neededThings;
	}

	/**
	 * Level :	have 2 way of working : </br>
	 *		If wanted lvl<0 the Level of the tested thing must be = to the wanted lvl.</br>
	 *		if wanted lvl>0 the Level of the tested thing must be > to the wanted lvl. (if wanted lvl=0 no need to check it since that will alwais return true)</br>
	 * 		ex if wanted = 7, tested thing must also have a lvl = to 7 return true.</br>
	 * 			if =-7 then all tested thing with a lvl>=7 will return true.</br>
	 */
	public static boolean checkLevel(NeededThing wanted, Thing tested) {
		boolean result = true;
		int wlvl = wanted.getLvl();
		int tlvl = tested.getLvl();
		if (wlvl!=0){
			if(wlvl<0 && (wlvl*-1) > tlvl )result=false;
			if(wlvl>0 && wlvl != tlvl)result=false;
		}
		return result;
	}
	
	// Fields
	
	private Integer lvl;
	private MilkTree tree;
	private Quantity quant;

	// Constructors
	
	public NeededThing() {
		super();
		this.setLvl(0);
		this.tree = new MilkTree();
		this.quant = new Quantity();
		this.getKind().setMod(1);
	}
	public NeededThing(Element milkElement) {
		super();
		this.tree = new MilkTree();
		this.quant = new Quantity();
		this.setValueFromNode(milkElement);
		this.getKind().setMod(1);
	}
	public NeededThing(NeededThing original) {
		super(original);
		if (original.getLvl()!=null) this.lvl = new Integer(original.getLvl());
		this.tree = new MilkTree(original.getTree());
		this.quant = new Quantity(original.getQuantity());
	}

	// Set value from Element methods
	

	@Override
	public void setValueFromNode(Element milkElement) {
		super.setValueFromNode(milkElement);
		this.setLvl(milkElement);
		this.setTree(milkElement);
		this.setQuantity(milkElement);
	}
	public void setLvl(Element milkElement) {
		Integer temp=null;
		temp=ParseMilkFile.getXmlIntAttribute(milkElement,xmlLvl);
		if (temp != null) this.lvl=temp;
	}
	public void setTree(Element milkElement) {
		this.tree.setValueFromNode(milkElement);
	}
	public void setQuantity(Element milkElement) {
		this.quant.setValueFromNode(milkElement);
	}
	
	// field methods

	public Integer getLvl() {
		return this.lvl;
	}
	public String getStringLvl() {
		String temp = "";
		if (this.lvl != null) temp += xmlLvl+" : "+this.lvl+". ";
		return temp;
	}
	public String getXmlLvl() {
		String temp = "";
		if (this.lvl != null && this.lvl.intValue() >0) temp += " "+xmlLvl+"=\""+lvl+"\"";
		return temp;
	}
	public void setLvl(Integer lvl) {
		this.lvl = lvl;
	}

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
		String temp =super.toStringAttrib();
		temp+=this.getStringLvl();
		if (this.tree != null) temp = this.tree.toStringAttrib();
		return temp;
	}
	@Override
	public String toXmlAttrib() {
		String temp = super.toXmlAttrib();
		temp+=this.getXmlLvl();
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
		if(this.lvl!=null && this.lvl!=0) temp= false;
		if(this.tree!=null && !this.tree.allZero()) temp= false;
		if(this.quant!=null && !this.quant.allZero()) temp= false;
		return temp;
	}
	
}
