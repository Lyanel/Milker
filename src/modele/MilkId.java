package modele;

import org.w3c.dom.Element;

import controleur.ParseMilkFile;

public class MilkId extends MilkVar implements Cloneable {
	
	public static final String xmlId = "id";
	public static final String noeud = "";
	public String getNoeud() {return noeud;}
	
	// Fields
	
	private Integer id;

	// Constructors
		
	public MilkId() {
		this(0);
	}
	public MilkId(Integer id) {
		super();
		this.setId(id);
	}
	public MilkId(Element milkElement) {
		super();
		this.setValueFromNode(milkElement);
	}

	// Set value from Element methods
	
	@Override
	public void setValueFromNode(Element milkElement) {
		super.setValueFromNode(milkElement);
		this.setId(milkElement);
	}
	public void setId(Element milkElement) {
		Integer temp=null;
		temp=ParseMilkFile.getXmlIntAttribute(milkElement,xmlId);
		if (temp != null) this.id=temp;
	}
	/*
	@Override
	public void setNullValueFromNode(Element milkElement) {
		super.setNullValueFromNode(milkElement);
		this.setNullId(milkElement);
	}
	public void setNullId(Element milkElement) {
		id = ParseMilkFile.getXmlIntAttribute(milkElement,xmlId);
	}*/
	
	// field methods
	
	public Integer getId() {
		return this.id;
	}
	public String getStringId() {
		String temp = null;
		if (this.id != null) temp = " "+xmlId+" : "+this.id+". ";
		return temp;
	}
	public String getXmlId() {
		String temp = null;
		if (this.id != null) temp = " "+xmlId+"=\""+this.id+"\"";
		return temp;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	// toString & toXml methods
	
	@Override
	public String toStringAttrib() {
		String temp = super.toStringAttrib();
		temp+=this.getStringId();
		return temp;
	}
	@Override
	public String toXmlAttrib() {
		String temp = super.toXmlAttrib();
		temp+=this.getXmlId();
		return temp;
	}
	/**
	 * @return an xml String containing this object node, ID & childs for the Text file.
	 */
	public final String toXmlText() {
		String temp = "<"+this.getNoeud()+this.getXmlId();
		if (this.toXmlTextChild().length()>0){
			temp+= ">"+MilkRs.LIGNE_BREAK+this.toXmlTextChild()+MilkRs.LIGNE_BREAK
					+"</"+this.getNoeud()+">"+MilkRs.LIGNE_BREAK;
		}else temp+="/>"+MilkRs.LIGNE_BREAK;
		return temp;
	}
	
	// other object methods

	@Override
	public boolean allZero()  {
		boolean temp = super.allZero();
		if(this.id!=null && this.id!=0) temp= false;
		return temp;
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		MilkId clone = (MilkId) super.clone();
		return clone;
	}
	
	@Override
	public boolean equals(Object obj) {
		boolean equal = true;
		if (obj==null) equal = false;
		else if (obj.getClass() != this.getClass()) equal = false;
		else if (this.id.intValue() != ((MilkId)obj).id.intValue()) equal = false;
		return equal;
	}
}
