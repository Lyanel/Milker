package modele.thing;

import org.w3c.dom.Element;

import modele.carac.MilkAttrib;

public class LivingBeing extends Thing implements Cloneable {
	
	
	// field
	

	// Constructors
	
	public LivingBeing() {
		super();
	}
	public LivingBeing(Element milkElement) {
		super();
		this.setValueFromNode(milkElement);
	}

	// Set value from Element methods

	public void setScene(Element milkElement) {
		super.setScene(milkElement);
	}
	
	// field methods
	
	// other object methods

	public double getIncome(double toolProdBonus, double toolQualBonus, double cattleProdBonus,double cattleQualBonus, double buildProdBonus, double buildQualBonus) {
		double tIncome = 0;
		Integer thingQuant = this.getAttrib().getActives();
		MilkAttrib attrib = this.getIncome().getAttrib();
		if(thingQuant>0 && this.getIncome().canProdMilk()){
			double milkQuant = (attrib.getQuant()+attrib.getQuant()*cattleProdBonus)*toolProdBonus;
			double milkQual = (attrib.getQual()+attrib.getQual()*cattleQualBonus)*toolQualBonus;
			tIncome += thingQuant *( milkQuant+milkQuant*buildProdBonus ) * (milkQual+milkQual*buildQualBonus) ;
		}
		return tIncome;
	}

	public double getActiveIncome(double toolProdBonus, double toolQualBonus, double cattleProdBonus,double cattleQualBonus, double buildProdBonus, double buildQualBonus) {
		double tIncome = 0;
		if(this.getIncome().canProdMilk()){
			MilkAttrib attrib = this.getIncome().getAttrib();
			Integer fullyActive = this.getAttrib().getActives();
			if(fullyActive>0){
				double milkQuant = (attrib.getQuant()+attrib.getQuant()*cattleProdBonus)*toolProdBonus;
				double milkQual = (attrib.getQual()+attrib.getQual()*cattleQualBonus)*toolQualBonus;
				tIncome += fullyActive *( milkQuant+milkQuant*buildProdBonus ) * (milkQual+milkQual*buildQualBonus) ;
			}
			Integer semiActive = this.getAttrib().getSemiActives();
			if(semiActive>0){
				double milkQuant = (attrib.getQuant()+attrib.getQuant()*cattleProdBonus)*0.8*toolProdBonus;
				double milkQual = (attrib.getQual()+attrib.getQual()*cattleQualBonus)*0.7*toolQualBonus;
				tIncome += semiActive *( milkQuant+milkQuant*buildProdBonus ) * (milkQual+milkQual*buildQualBonus) ;
			}
		}
		return tIncome;
	}
	
	@Override
	public boolean allZero()  {
		boolean temp = super.allZero();
		return temp;
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		LivingBeing clone = (LivingBeing) super.clone();
		return clone;
	}
}