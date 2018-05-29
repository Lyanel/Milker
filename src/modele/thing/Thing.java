package modele.thing;

import modele.carac.ThingAttrib;
import modele.carac.Income;
import modele.carac.MilkAttrib;

import java.util.Vector;

import org.w3c.dom.Element;

import controleur.ParseMilkFile;

public class Thing extends NearThing implements Cloneable {
	
	public static final String noeud = "thing", xmlLvl="lvl", xmlGet="get";
	public String getNoeud() {return noeud;}
	
	@SuppressWarnings("rawtypes")
	public static Vector getMilkVarList(Element elementlist) {
		Vector<Thing> things = new Vector<Thing>();
		Thing thing=new Thing();
		Element elements = thing.getMilkElementList(elementlist);
		int size = (elements!=null)? elements.getChildNodes().getLength():0;
		for (int i=0;i<size;i++){ 
			Element tempE=null;
			tempE=thing.getMilkElement(elements,i);
			if (tempE != null){
				thing.setValueFromNode(tempE);
				things.add(thing);
			}
		}
		return things;
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Vector getMilkVarList(Vector<Element> elementlist) {
		Vector<Thing> things = new Vector<Thing>();
		for (Element elementMilk: elementlist) {
			try {
				Thing thing = new Thing(elementMilk);
				things.add(thing);
			} catch (Exception e) {e.printStackTrace();}
		}
		return things;
	}

	// Fields
	
	private Integer lvl, get;
	private ThingAttrib attrib;
	private Income income;

	// Constructors
	
	public Thing() {
		super();
		this.setLvl(0);
		this.setGet(0);
		this.attrib = new ThingAttrib();
		this.income = new Income();
	}
	public Thing(Element milkElement) {
		super();
		this.setLvl(0);
		this.setGet(0);
		this.attrib = new ThingAttrib();
		this.income = new Income();
		this.setValueFromNode(milkElement);
	}
	
	// Set value from Element methods
	
	@Override
	public void setValueFromNode(Element milkElement) {
		super.setValueFromNode(milkElement);
		this.setLvl(milkElement);
		this.setGet(milkElement);
		this.setAttrib(milkElement);
		this.setIncome(milkElement);
	}
	public void setLvl(Element milkElement) {
		Integer temp=null;
		temp=ParseMilkFile.getXmlIntAttribute(milkElement,xmlLvl);
		if (temp != null) this.lvl=temp;
	}
	public void setGet(Element milkElement) {
		Integer temp=null;
		temp=ParseMilkFile.getXmlIntAttribute(milkElement,xmlLvl);
		if (temp != null) this.get=temp;
	}
	public void setAttrib(Element milkElement) {
		this.attrib.setValueFromNode(milkElement);;
	}
	public void setIncome(Element milkElement) {
		this.income.setValueFromNode(milkElement);
	}	
	
	// field methods

	@Override
	public Float getPriceValue() {
		return super.getPriceValue()*this.getAttrib().getQuant();
	}
	
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

	public Integer getGet() {
		return this.get;
	}
	public String getStringGet() {
		String temp = null;
		if (this.get != null) temp = xmlGet+" : "+this.get+". ";
		return temp;
	}
	public String getXmlGet() {
		String temp = null;
		if (this.get != null) temp = " "+xmlGet+"=\""+get+"\"";
		return temp;
	}
	public void setGet(Integer get) {
		this.get = get;
	}
	public ThingAttrib getAttrib() {
		return this.attrib;
	}
	public void setAttrib(ThingAttrib attrib) {
		this.attrib = attrib;
	}

	public Income getIncome() {
		return this.income;
	}
	public double getIncome(double buildProdBonus, double buildQualBonus) {
		double tIncome = 0;
		Integer thingQuant = this.getAttrib().getQuant();
		MilkAttrib attrib = this.getIncome().getAttrib();
		if(thingQuant>0 && this.getIncome().canProdMilk()){
			double milkQuant = attrib.getQuant();
			double milkQual = attrib.getQual();
			tIncome += thingQuant *( milkQuant+milkQuant*buildProdBonus/100 ) * (milkQual+milkQual*buildQualBonus/100) ;
		}
		if(thingQuant>0 && this.getIncome().canProdCoin()){
			tIncome += thingQuant * this.getIncome().getCoin() ;
		}
		return tIncome;
	}
	public double getIncome(double toolProdBonus, double toolQualBonus, double cattleProdBonus,double cattleQualBonus, double buildProdBonus, double buildQualBonus) {
		double tIncome = 0;
		Integer thingQuant = this.getAttrib().getQuant();
		MilkAttrib attrib = this.getIncome().getAttrib();
		if(thingQuant>0 && this.getIncome().canProdMilk()){
			double milkQuant = (attrib.getQuant()+attrib.getQuant()*cattleProdBonus/100)*toolProdBonus;
			double milkQual = (attrib.getQual()+attrib.getQual()*cattleQualBonus/100)*toolQualBonus;
			tIncome += thingQuant *( milkQuant+milkQuant*buildProdBonus/100 ) * (milkQual+milkQual*buildQualBonus/100) ;
		}
		return tIncome;
	}
	public void setProductivity(int prod) {
		this.income.setProd(prod);
	}
	public void setIncome(Income income) {
		this.income = income;
	}
	
	// toString & toXml methods

	@Override
	public String toStringAttrib() {
		String temp = super.toStringAttrib();
		temp+=this.getStringLvl();
		temp+=this.getStringGet();
		if (this.attrib != null) temp += this.attrib.toStringAttrib();
		return temp;
	}
	@Override
	public String toXmlAttrib() {
		String temp = super.toXmlAttrib();
		temp+=this.getXmlLvl();
		temp+=this.getXmlGet();
		if (this.attrib != null) temp += this.attrib.toXmlAttrib();
		return temp;
	}
	@Override
	public String toStringStatChild() {
		String temp = super.toStringStatChild();
		if (this.attrib != null) temp += this.attrib.toStringStatChild();
		if (this.income != null) temp += this.income.toStringStat();
		return temp;
	}
	@Override
	public String toXmlStatChild() {
		String temp = super.toXmlStatChild();
		if (this.attrib != null) temp += this.attrib.toXmlStatChild();
		if (this.income != null) temp += this.income.toXmlStat();
		return temp;
	}
	
	// other object methods
	
	@Override
	public void buy() {
		this.getAttrib().setQuant(this.getAttrib().getQuant()+1);
	}

	@Override
	public boolean allZero()  {
		boolean temp = super.allZero();
		if(this.attrib!=null && !this.attrib.allZero()) temp= false;
		if(this.income!=null && !this.income.allZero()) temp= false;
		return temp;
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		Thing clone = (Thing) super.clone();
		if (this.attrib!=null) clone.setAttrib((ThingAttrib) this.attrib.clone());
		if (this.income!=null) clone.setIncome((Income) this.income.clone());
		return clone;
	}
}