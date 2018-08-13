package modele.intel;

import modele.ParseMilkFile;
import modele.baseObject.MilkPricedObj;
import modele.carac.NeededIntel;

import org.w3c.dom.Element;

public class Intel extends MilkPricedObj {
	
	public static final String noeud = "intel", xmlViewUnlock = "view";
	public String getNoeud() {return noeud;}
	

	// Fields
	
	private Integer viewUnlock;

	// Constructors
	
	public Intel() {
		super();
		this.setViewUnlock(0);
	}
	public Intel(Element milkElement) {
		super();
		this.setViewUnlock(0);
		this.setValueFromNode(milkElement);
	}
	public Intel(Intel original) {
		super(original);
		this.viewUnlock = new Integer(original.getViewUnlock());
	}

	// Set value from Element methods
	
	@Override
	public void setValueFromNode(Element milkElement) {
		super.setValueFromNode(milkElement);
		this.setViewUnlock(milkElement);
	}
	public void setViewUnlock(Element milkElement) {
		Integer temp=null;
		temp=ParseMilkFile.getXmlIntAttribute(milkElement,xmlViewUnlock);
		if (temp != null) this.viewUnlock=temp;
	}
	
	// field methods

	public Integer getViewUnlock() {
		return this.viewUnlock;
	}
	public String getStringViewUnlock() {
		String temp = null;
		if (this.viewUnlock != null) temp = xmlStart+" : "+this.viewUnlock+". ";
		return temp;
	}
	public String getXmlViewUnlock() {
		String temp = null;
		if (this.viewUnlock != null) temp = " "+xmlStart+"=\""+viewUnlock+"\"";
		return temp;
	}
	public void setViewUnlock(Integer viewUnlock) {
		this.viewUnlock = viewUnlock;
	}
	
	// toString & toXml methods
	
	@Override
	public String toStringAttrib() {
		String temp = super.toStringAttrib();
		temp+=this.getStringViewUnlock();
		return temp;
	}
	@Override
	public String toXmlAttrib() {
		String temp = super.toXmlAttrib();
		temp+=this.getXmlViewUnlock();
		return temp;
	}
	
	// other object methods

	public NeededIntel toNeededIntel() {
		NeededIntel save = new NeededIntel();
		save.setKind(this.getKind().getKind());
		save.setId(this.getId());
		return save;
	}

	@Override
	public boolean allZero()  {
		boolean temp = super.allZero();
		if(this.viewUnlock!=null && this.viewUnlock!=0) temp= false;
		return temp;
	}
	
}