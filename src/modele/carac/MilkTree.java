package modele.carac;

import modele.ParseMilkFile;
import modele.baseObject.MilkInterface;
import modele.baseObject.MilkVar;
import modele.thing.Thing;

import org.w3c.dom.Element;

	/**
	 * Tree :  Refer to the tree in which you can have the object : 
	 *	object : 1 (neutral), 2 (science), 3 (magic) or 4 (special, generally used by ascension).
	 *	need/check : 9 (all tree), 5 (neutral & science), 6 (neutral & magic) or 7 (science & magic).
	 *
	 * @author Slade Chaos
	 */
public class MilkTree extends MilkVar {

	public static final int Tree_None = 0;
	public static final int Tree_Neutral = 1;
	public static final int Tree_Science = 2;
	public static final int Tree_Magic = 3;
	public static final int Tree_Special = 4;
	public static final int Tree_NeutralScience = 5;
	public static final int Tree_NeutralMagic = 6;
	public static final int Tree_MagiScience = 7;
	public static final int tree_All = 9;
	public static final String xmlTree = "tree";
	
	/**
	 * Test if tree match : </br>
	 * Return true if </br>
	 * tested.tree=1 & wanted.tree=0,1,5,6or9 for Neutral ||</br> 
	 * tested.tree=2 & wanted.tree=0,2,5,7or9 for Science || </br>
	 * tested.tree=3 & wanted.tree=0,3,6,7or9 for Magic </br>
	 * tested.tree=4 & wanted.tree=0,4or9 for Special </br>
	 */
	public static boolean checkTree(MilkTree wanted, Thing tested) {
		boolean result = false;
		MilkTree test = tested.getTree();
		if (test.tree==1 && (wanted.tree==0 || wanted.tree==1 || wanted.tree==5 || wanted.tree==6 || wanted.tree==9)) result = true;
		else if (test.tree==2 && (wanted.tree==0 || wanted.tree==2 || wanted.tree==5 || wanted.tree==7 || wanted.tree==9)) result = true;
		else if (test.tree==3 && (wanted.tree==0 || wanted.tree==3 || wanted.tree==6 || wanted.tree==7 || wanted.tree==9)) result = true;
		else if (test.tree==4 && (wanted.tree==0 || wanted.tree==4 || wanted.tree==9)) result = true;
		return result;
	}

	// Fields
	private Integer tree;
	
	// Constructors
	
	public MilkTree() {
		this(0);
	}
	public MilkTree(Integer tree) {
		super();
		this.tree = tree;
	}
	public MilkTree(Element milkElement) {
		super();
		this.setValueFromNode(milkElement);
	}
	public MilkTree(MilkTree original) {
		super(original);
		this.tree = new Integer(original.getTree());
	}

	// Set value from Element methods
	
	@Override
	public void setValueFromNode(Element milkElement) {
		super.setValueFromNode(milkElement);
		this.setTree(milkElement);
	}
	public void setTree(Element milkElement) {
		Integer temp=null;
		temp=ParseMilkFile.getXmlIntAttribute(milkElement,xmlTree);
		if (temp != null) this.tree=temp;
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
		String temp = "";
		if (this.tree != null && this.tree.intValue() >0) temp = " "+xmlTree+"=\""+tree+"\"";
		return temp;
	}
	public void setTree(Integer tree) {
		this.tree = tree;
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
		
	// other object methods

	@Override
	public boolean allZero() {
		boolean temp = super.allZero();
		if(this.tree!=null && this.tree!=0) temp= false;
		return temp;
	}
	
}
