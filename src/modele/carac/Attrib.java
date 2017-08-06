package modele.carac;

import controleur.ParseMilkFile;
import modele.MilkInterface;
import modele.MilkVar;

import org.w3c.dom.Element;
	/**
	 * Path :  Refer to the path in which you can have the object : 
	 *	object : 0 (neutral), 2 (science) or 3 (magic).
	 *	need/check : 9 (all path), 5 (neutral & science), 6 (neutral & magic) or 7 (science & magic).
	 *
	 * @author Slade Chaos
	 */
public class Attrib extends MilkVar implements Cloneable {

	public static final int Path_None = 0;
	public static final int Path_Neutral = 1;
	public static final int Path_Science = 2;
	public static final int Path_Magic = 3;
	public static final int Path_NeutralScience = 5;
	public static final int Path_NeutralMagic = 6;
	public static final int Path_MagiScience = 7;
	public static final int path_All = 9;
	public static final String xmlPath = "path", xmlQuant = "quantity";
	private Integer path, quant;
	
	// Constructors
	
	public Attrib() {
		this(0,0);
	}
	public Attrib(Integer path,Integer quant) {
		super();
		this.path = path;
		this.quant = quant;
	}
	public Attrib(Element milkElement) {
		super();
		this.setValueFromNode(milkElement);
	}

	// Set value from Element methods
	
	@Override
	public void setValueFromNode(Element milkElement) {
		super.setValueFromNode(milkElement);
		this.setPath(milkElement);
		this.setQuant(milkElement);
	}
	@Override
	public void setNullValueFromNode(Element milkElement) {
		super.setNullValueFromNode(milkElement);
		this.setNullPath(milkElement);
		this.setNullQuant(milkElement);
	}
	
	// field methods
	
	public Integer getPath() {
		return this.path;
	}
	public String getStringPath() {
		String temp = "";
		if (this.path != null) {
			temp += " "+xmlPath+" : ";
			switch(this.path){
				case 0 : temp+= MilkInterface.getStringsFromId(500); break;
				case 1 : temp+= MilkInterface.getStringsFromId(561); break;
				case 2 : temp+= MilkInterface.getStringsFromId(562); break;
				case 3 : temp+= MilkInterface.getStringsFromId(563); break;
			}
			temp += ". ";
		}
		return temp;
	}
	public String getXmlPath() {
		String temp = null;
		if (this.path != null) temp = " "+xmlPath+"=\""+path+"\"";
		return temp;
	}
	public void setPath(Integer path) {
		this.path = path;
	}
	public void setPath(Element milkElement) {
		Integer temp=null;
		temp=ParseMilkFile.getXmlIntAttribute(milkElement,xmlPath);
		if (temp != null) this.path=temp;
	}
	public void setNullPath(Element milkElement) {
		this.path = ParseMilkFile.getXmlIntAttribute(milkElement,xmlPath);
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
		temp+=this.getStringPath();
		return temp;
	}
	@Override
	public String toXmlAttrib() {
		String temp = super.toXmlAttrib();
		temp+=this.getXmlPath();
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
		if(this.path!=null && this.path!=0) temp= false;
		if(this.quant!=null && this.quant!=0) temp= false;
		return temp;
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		Attrib clone = (Attrib) super.clone();
		return clone;
	}
}
