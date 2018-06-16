package modele;

import java.util.ArrayList;

import org.w3c.dom.Element;

import modele.carac.Need;

public class MilkXmlObj extends MilkObj implements Cloneable {
	
	public static void setInfo(ArrayList<? extends MilkXmlObj> milkObjs, ArrayList<Element> elementlInfos) {
		for (Element elementlInfo: elementlInfos) {
			try {
				MilkXmlObj test = new MilkXmlObj(elementlInfo);
				test.setInfo(elementlInfo);
				for (MilkXmlObj milkObj:milkObjs){
					if (test.getId().intValue() == milkObj.getId().intValue()){
						milkObj.setInfo(test.getInfo());
						break;
					}
				}
			} catch (Exception e) {e.printStackTrace();}
		}
	}
	
	public static void setIcon(ArrayList<? extends MilkXmlObj> milkObjs, ArrayList<Element> elementIcons) {
		for (Element elementIcon: elementIcons) {
			try {
				MilkXmlObj test = new MilkXmlObj(elementIcon);
				test.setIcon(elementIcon);
				for (MilkXmlObj milkObj:milkObjs){
					if (test.getId().intValue() == milkObj.getId().intValue()){
						milkObj.setIcon(test.getIcon());
						break;
					}
				}
			} catch (Exception e) {e.printStackTrace();}
		}
	}

	// Fields
	
	private MilkInfo info;
	private Need need;
	private MilkImage icon;
	
	// Constructors

	public MilkXmlObj() {
		super();
		this.getKind().setMod(0);
		this.info = new MilkInfo();
		this.need = new Need();
		this.icon = new MilkImage();
	}
	public MilkXmlObj(Element milkElement) {
		super();
		this.getKind().setMod(0);
		this.info = new MilkInfo();
		this.need = new Need();
		this.icon = new MilkImage();
		this.setValueFromNode(milkElement);
	}

	// Set value from Element methods
	
	@Override
	public void setValueFromNode(Element milkElement) {
		super.setValueFromNode(milkElement);
		this.setNeed(milkElement);
	}
	public void setNeed(Element milkElement) {
		this.need.setValueFromNode(milkElement);
	}
	@Override
	public void setTextValueFromNode(Element milkElement) {
		super.setTextValueFromNode(milkElement);
		this.setInfo(milkElement);
	}
	public void setInfo(Element milkElement) {
		this.info.setTextValueFromNode(milkElement);
	}
	public void addIntel(Element milkElement) {
		this.need.addIntel(milkElement);
	}
	public void setIcon(Element milkElement) {
		this.icon.setValueFromNode(milkElement);
	}
	
	// field methods

	public Need getNeed() {
		return this.need;
	}
	public void setNeed(Need need) {
		this.need = need;
	}
	
	public MilkInfo getInfo() {
		return this.info;
	}
	public void setInfo(MilkInfo info) {
		this.info = info;
	}
	
	public MilkImage getIcon() {
		return icon;
	}
	public void setIcon(MilkImage icon) {
		this.icon = icon;
	}
	
	// toString & toXml methods

	@Override
	public String toStringStatChild() {
		String temp = super.toStringStatChild();
		if (this.need != null) temp = this.need.toStringStat();
		return temp;
	}
	@Override
	public String toXmlStatChild() {
		String temp = super.toXmlStatChild();
		if (this.need != null) temp = this.need.toXmlStat();
		return temp;
	}
	
	@Override
	public String toStringTextChild() {
		String temp = super.toStringTextChild();
		if (this.info != null) temp += this.info.toStringTextChild();
		return temp;
	}
	@Override
	public String toXmlTextChild() {
		String temp = super.toXmlTextChild();
		if (this.info != null) temp += this.info.toXmlTextChild();
		return temp;
	}
	
	/**
	 * @return an html String containing this object node, attibutes & childs, mostly not need to be overide.
	 */
	@Override
	public final String toString() {
		return this.getXmlId()+" : "+this.getInfo().getName();
	}
	
	// other object methods

	@Override
	public boolean allZero()  {
		boolean temp = super.allZero();
		if(this.info!=null && !this.info.allZero()) temp= false;
		if(this.need!=null && !this.need.allZero()) temp= false;
		if(this.icon!=null && !this.icon.allZero()) temp= false;
		return temp;
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		MilkXmlObj clone = (MilkXmlObj) super.clone();
		if (this.info!=null) clone.setInfo((MilkInfo) this.info.clone());
		if (this.need!=null) clone.setNeed((Need) this.need.clone());
		if (this.icon!=null) clone.setIcon((MilkImage) this.icon.clone());
		return clone;
	}
}
