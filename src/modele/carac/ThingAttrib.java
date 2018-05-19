package modele.carac;

import controleur.ParseMilkFile;
import modele.MilkInterface;
import modele.MilkVar;

import org.w3c.dom.Element;
	/**
	 * Tree :  Refer to the tree in which you can have the object : 
	 *	object : 0 (neutral), 2 (science) or 3 (magic).
	 *	need/check : 9 (all tree), 5 (neutral & science), 6 (neutral & magic) or 7 (science & magic).
	 *
	 * @author Slade Chaos
	 */
public class ThingAttrib extends MilkVar implements Cloneable {

	public static final int Tree_None = 0;
	public static final int Tree_Neutral = 1;
	public static final int Tree_Science = 2;
	public static final int Tree_Magic = 3;
	public static final int Tree_NeutralScience = 5;
	public static final int Tree_NeutralMagic = 6;
	public static final int Tree_MagiScience = 7;
	public static final int tree_All = 9;
	public static final String xmlTree = "tree", xmlQuant = "quant";
	private Integer tree, quant;
	
	// Constructors
	
	public ThingAttrib() {
		this(0,0);
	}
	public ThingAttrib(Integer tree,Integer quant) {
		super();
		this.tree = tree;
		this.quant = quant;
	}
	public ThingAttrib(Element milkElement) {
		super();
		this.setValueFromNode(milkElement);
	}

	// Set value from Element methods
	
	@Override
	public void setValueFromNode(Element milkElement) {
		super.setValueFromNode(milkElement);
		this.setTree(milkElement);
		this.setQuant(milkElement);
	}
	@Override
	public void setNullValueFromNode(Element milkElement) {
		super.setNullValueFromNode(milkElement);
		this.setNullTree(milkElement);
		this.setNullQuant(milkElement);
	}
	
	// field methods
	
	public Integer getTree() {
		return this.tree;
	}
	public String getStringTree() {
		String temp = "";
		if (this.tree != null) {
			temp += " "+xmlTree+" : ";
			switch(this.tree){
				case 0 : temp+= MilkInterface.getStringsFromId(500); break;
				case 1 : temp+= MilkInterface.getStringsFromId(561); break;
				case 2 : temp+= MilkInterface.getStringsFromId(562); break;
				case 3 : temp+= MilkInterface.getStringsFromId(563); break;
			}
			temp += ". ";
		}
		return temp;
	}
	public String getXmlTree() {
		String temp = null;
		if (this.tree != null) temp = " "+xmlTree+"=\""+tree+"\"";
		return temp;
	}
	public void setTree(Integer tree) {
		this.tree = tree;
	}
	public void setTree(Element milkElement) {
		Integer temp=null;
		temp=ParseMilkFile.getXmlIntAttribute(milkElement,xmlTree);
		if (temp != null) this.tree=temp;
	}
	public void setNullTree(Element milkElement) {
		this.tree = ParseMilkFile.getXmlIntAttribute(milkElement,xmlTree);
	}

	public Integer getQuant() {
		return this.quant;
	}
	public String getStringQuant() {
		String temp = null;
		if (this.quant != null) temp = " "+xmlQuant+" = "+this.quant+". ";
		return temp;
	}
	public String getXmlQuant() {
		String temp = null;
		if (this.quant != null) temp = ""+quant+"";
		return temp;
	}
	public void setQuant(Integer quant) {
		this.quant = quant;
	}
	public void setQuant(Element milkElement) {
		Integer temp=null;
		temp=ParseMilkFile.getXmlIntValue(milkElement);
		if (temp != null) this.quant=temp;
	}
	public void setNullQuant(Element milkElement) {
		this.quant = ParseMilkFile.getXmlIntValue(milkElement);
	}
	
	// toString & toXml methods
	
	@Override
	public String toStringAttrib() {
		String temp = super.toXmlAttrib();
		temp+=this.getStringTree();
		return temp;
	}
	@Override
	public String toXmlAttrib() {
		String temp = super.toXmlAttrib();
		temp+=this.getXmlTree();
		return temp;
	}
	
	@Override
	public String toStringStatChild() {
		String temp = super.toXmlStatChild();
		temp+=this.getStringQuant();
		return temp;
	}
	@Override
	public String toXmlStatChild() {
		String temp = super.toXmlStatChild();
		 temp+=this.getXmlQuant();
		return temp;
	}
	
	// other object methods

	@Override
	public boolean allZero() {
		boolean temp = super.allZero();
		if(this.tree!=null && this.tree!=0) temp= false;
		if(this.quant!=null && this.quant!=0) temp= false;
		return temp;
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		ThingAttrib clone = (ThingAttrib) super.clone();
		return clone;
	}
}
