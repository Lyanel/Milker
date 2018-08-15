package modele.thing;

import org.w3c.dom.Element;

import modele.carac.MilkProd;

public class LivingBeing extends Thing {
	
	
	// field
	
	private Integer scene;

	// Constructors
	
	public LivingBeing() {
		super();
		this.setSceneLvl(1);
	}
	public LivingBeing(Element milkElement) {
		super();
		this.setSceneLvl(1);
		this.setValueFromNode(milkElement);
	}
	public LivingBeing(LivingBeing original) {
		super(original);
		this.scene = new Integer(original.getSceneLvl());
	}

	// Set value from Element methods

	public void setScene(Element milkElement) {
		super.setScene(milkElement);
	}
	
	// field methods
	
	public Integer getSceneLvl() {
		return this.scene;
	}
	public String getStringSceneLvl() {
		String temp = null;
		if (this.scene != null) temp = xmlLvl+" : "+this.scene+". ";
		return temp;
	}
	public String getXmlSceneLvl() {
		String temp = null;
		if (this.scene != null) temp = " "+xmlLvl+"=\""+scene+"\"";
		return temp;
	}
	public void setSceneLvl(Integer scene) {
		this.scene = scene;
	}
	
	// other object methods

	public double getIncome(double toolProdBonus, double toolQualBonus, double cattleProdBonus,double cattleQualBonus, double buildProdBonus, double buildQualBonus) {
		double tIncome = 0;
		Integer thingQuant = this.getQuantity().getActives();
		MilkProd attrib = this.getIncome().getAttrib();
		if(thingQuant>0 && this.getIncome().canProdMilk()){
			double milkQuant = (attrib.getQuant()+attrib.getQuant()*cattleProdBonus)*toolProdBonus/100;
			double milkQual = (attrib.getQual()+attrib.getQual()*cattleQualBonus)*toolQualBonus/100;
			tIncome += thingQuant *( milkQuant+milkQuant*buildProdBonus ) * (milkQual+milkQual*buildQualBonus) ;
		}
		return tIncome;
	}

	public double getActiveIncome(double toolProdBonus, double toolQualBonus, double cattleProdBonus,double cattleQualBonus, double buildProdBonus, double buildQualBonus) {
		double tIncome = 0;
		if(this.getIncome().canProdMilk()){
			MilkProd attrib = this.getIncome().getAttrib();
			Integer fullyActive = this.getQuantity().getActives();
			if(fullyActive>0){
				double milkQuant = (attrib.getQuant()+attrib.getQuant()*cattleProdBonus)*toolProdBonus/100;
				double milkQual = (attrib.getQual()+attrib.getQual()*cattleQualBonus)*toolQualBonus/100;
				tIncome += fullyActive *( milkQuant+milkQuant*buildProdBonus ) * (milkQual+milkQual*buildQualBonus) ;
			}
			Integer semiActive = this.getQuantity().getSemiActives();
			if(semiActive>0){
				double milkQuant = (attrib.getQuant()+attrib.getQuant()*cattleProdBonus)*0.8*toolProdBonus/100;
				double milkQual = (attrib.getQual()+attrib.getQual()*cattleQualBonus)*0.7*toolQualBonus/100;
				tIncome += semiActive * ( milkQuant+milkQuant*buildProdBonus ) * (milkQual+milkQual*buildQualBonus) ;
			}
		}
		return tIncome;
	}
	
	@Override
	public boolean allZero()  {
		boolean temp = super.allZero();
		return temp;
	}
	
}