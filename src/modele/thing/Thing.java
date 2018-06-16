package modele.thing;

import modele.carac.ThingAttrib;
import modele.carac.Income;
import modele.carac.MilkAttrib;

import java.util.ArrayList;
import java.util.Vector;

import org.w3c.dom.Element;

import controleur.ParseMilkFile;

public class Thing extends NearThing implements Cloneable {
	
	public static final String noeud = "thing", xmlLvl="lvl", xmlGet="get";
	public String getNoeud() {return noeud;}
	
	public static ArrayList<? extends Thing> getMilkVarList(Element elementParent) {
		ArrayList<Thing> things = new ArrayList<Thing>();
		Thing thing=new Thing();
		Element elementlist = thing.getThisChildFromParentAsAContainer(elementParent);
		ArrayList<Element> elements = thing.getThisChildListFromParent(elementlist);
		if(elements !=null) for (Element element:elements){ 
			if (element != null){
				thing.setValueFromNode(element);
				things.add(thing);
			}
		}
		return things;
	}

	public static ArrayList<? extends Thing> getMilkVarList(Vector<Element> elementlist) {
		ArrayList<Thing> things = new ArrayList<Thing>();
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
		if(this.getStart().intValue()>0)this.attrib.incrementeQuant(this.getStart().intValue());
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
	public Double getPriceValue() {
		return super.getPrice().getCoin()*(1+Math.pow(this.getAttrib().getQuant()/100,super.getPrice().getCoef()));
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
	public void incrementeQuant(Integer quantToAdd) {
		this.attrib.incrementeQuant(quantToAdd);
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
			tIncome += thingQuant *( milkQuant+milkQuant*buildProdBonus ) * (milkQual+milkQual*buildQualBonus) ;
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
			double milkQuant = (attrib.getQuant()+attrib.getQuant()*cattleProdBonus)*toolProdBonus;
			double milkQual = (attrib.getQual()+attrib.getQual()*cattleQualBonus)*toolQualBonus;
			tIncome += thingQuant *( milkQuant+milkQuant*buildProdBonus ) * (milkQual+milkQual*buildQualBonus) ;
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