package modele.carac;

import java.util.ArrayList;

import org.w3c.dom.Element;

import modele.ParseMilkFile;
import modele.Utility;
import modele.XmlHelper;
import modele.baseObject.MilkKind;
import modele.baseObject.MilkVar;
import modele.thing.Building;
import modele.thing.Slave;
import modele.thing.Thing;

public class Effect extends MilkVar {

	public static final int PRI_BUY = 1, PRI_SELL = 2, PRI_SAC = 3, INC_PROD = 4, INC_QUANT = 5, INC_QUAL = 6, BUI_AGEN = 11, BUI_POP = 12,
					MOD_REM = 0, MOD_CHANG = 1, MOD_ADD = 2, MOD_PERCENT = 3, MOD_MULT = 4 ;
	
	public static final String noeud = "effect",  xmlNum = "num", xmlMod = "mod", xmlValue = "val";
	public String getNoeud() {return noeud;}

	
	public static ArrayList<? extends Effect> getMilkVarList(Element parent) {
		return getMilkVarList(XmlHelper.getChildrenListByTagName(parent,noeud));
	}
	
	public static ArrayList<? extends Effect> getMilkVarList(ArrayList<Element> elementlist) {
		ArrayList<Effect> effects = new ArrayList<Effect>();
		for (Element elementMilk: elementlist) {
			try {
				Effect effect = new Effect(elementMilk);
				effects.add(effect);
			} catch (Exception e) {e.printStackTrace();}
		}
		return effects;
	}

	// Fields
	
	private Integer num, mod;
	private Float value;
	private Target target = null;	
	private Agent agent = null;	
	
	// Constructors
	
	public Effect() {
		super();
		this.num = 1;
		this.mod = 1;
		this.value = (float)1;
		this.target = new Target();
		this.agent = new Agent();
	}
	public Effect(Element milkElement) {
		super();
		this.num = 1;
		this.mod = 1;
		this.value = (float)1;
		this.target = new Target();
		this.agent = new Agent();
		this.setValueFromNode(milkElement);
	}
	public Effect(Effect original) {
		super(original);
		this.num = new Integer(original.getNum());
		this.mod = new Integer(original.getMod());
		this.value = new Float(original.getValue());
		this.target = new Target(original.getTarget());
		this.agent = new Agent(original.getAgent());
	}

	// Set value from Element methods
	
	@Override
	public void setValueFromNode(Element milkElement) {
		super.setValueFromNode(milkElement);
		this.setNum(milkElement);
		this.setMod(milkElement);
		this.setValue(milkElement);
		this.setTarget(milkElement);
		this.setAgent(milkElement);
	}
	public void setNum(Element milkElement) {
		Integer temp=null;
		temp=ParseMilkFile.getXmlIntAttribute(milkElement,xmlNum);
		if (temp != null) this.num=temp;
	}
	public void setMod(Element milkElement) {
		Integer temp=null;
		temp=ParseMilkFile.getXmlIntAttribute(milkElement,xmlMod);
		if (temp != null) this.mod=temp;
	}
	public void setValue(Element milkElement) {
		Float temp=null;
		temp=ParseMilkFile.getXmlFloatAttribute(milkElement,xmlValue);
		if (temp != null) this.value=temp;
	}
	public void setTarget(Element milkElement) {
		this.target.setValueFromNode(milkElement);
	}
	public void setAgent(Element milkElement) {
		this.agent.setValueFromNode(milkElement);
	}
		
	// field methods
	
	public Integer getNum() {
		return this.num;
	}
	public String getStringNum() {
		String temp = null;
		if (this.num != null) temp = xmlNum+" : "+this.num+". ";
		return temp;
	}
	public String getXmlNum() {
		String temp = null;
		if (this.num != null) temp = " "+xmlNum+"=\""+num+"\"";
		return temp;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	
	public Integer getMod() {
		return this.mod;
	}
	public String getStringMod() {
		String temp = null;
		if (this.mod != null) temp = xmlMod+" : "+this.mod+". ";
		return temp;
	}
	public String getXmlMod() {
		String temp = null;
		if (this.mod != null) temp = " "+xmlMod+"=\""+mod+"\"";
		return temp;
	}
	public void setMod(Integer mod) {
		this.mod = mod;
	}

	public Float getValue() {
		return this.value;
	}
	public String getStringValue() {
		String temp = null;
		if (this.value != null) temp = xmlValue+" : "+this.value+". ";
		return temp;
	}
	public String getXmlValue() {
		String temp = null;
		if (this.value != null) temp = " "+xmlValue+"=\""+value+"\"";
		return temp;
	}
	public void setValue(Float value) {
		this.value = value;
	}

	public Target getTarget() {
		return this.target;
	}
	public void setTarget(Target target) {
		this.target = target;
	}
	
	public Agent getAgent() {
		return this.agent;
	}
	public void setAgent(Agent agent) {
		this.agent = agent;
	}
	
	// toString & toXml methods
	
	@Override
	public String toStringAttrib() {
		String temp = super.toStringAttrib();
		temp+=this.getStringNum();
		temp+=this.getStringMod();
		temp+=this.getStringValue();
		return temp;
	}
	@Override
	public String toXmlAttrib() {
		String temp = super.toXmlAttrib();
		temp+=this.getXmlNum();
		temp+=this.getXmlMod();
		temp+=this.getXmlValue();
		return temp;
	}
	@Override
	public String toStringStatChild() {
		String temp = super.toStringStatChild();
		if (this.target != null) temp += this.target.toStringStat();
		if (this.agent != null) temp += this.agent.toStringStat();
		return temp;
	}
	@Override
	public String toXmlStatChild() {
		String temp = super.toXmlStatChild();
		if (this.target != null) temp += this.target.toXmlStat();
		if (this.agent != null) temp += this.agent.toXmlStat();
		return temp;
	}

	// other object methods

	@Override
	public boolean allZero()  {
		boolean temp = super.allZero();
		if(this.target!=null && !this.target.allZero()) temp= false;
		if(this.agent!=null && !this.agent.allZero()) temp= false;
		return temp;
	}

	public void applyEffect() {
		ArrayList<? extends Thing> targets = Utility.getThingsListsFromAgent(target);
		for (Thing target : targets){
			switch (num) {
				case PRI_BUY : applyBuy(target);break;
				case PRI_SELL : applySell(target);break;
				case PRI_SAC : applySac(target);break;
				case INC_PROD : applyProd(target);break;
				case INC_QUANT : applyQuant(target);break;
				case INC_QUAL : applyQual(target);break;
				case BUI_AGEN : applyAgent(target);break;
				case BUI_POP : applyPop(target);break;
			}
		}
	}

	public void applyBuy(Thing target) {
		switch (mod) {
			case MOD_REM : target.getPrice().setCoin(new Float(0)); break;
			case MOD_CHANG : target.getPrice().setCoin(value); break;
			case MOD_ADD : target.getPrice().setCoin(target.getPrice().getCoin()+value); break;
			case MOD_PERCENT : target.getPrice().setCoin(target.getPrice().getCoin()+(target.getPrice().getCoin()*value/100)); break;
			case MOD_MULT : target.getPrice().setCoin(target.getPrice().getCoin()*value); break;
		}
	}

	public void applySell(Thing target) {
		switch (mod) {
			case MOD_REM : target.getPrice().setSell(new Float(0)); break;
			case MOD_CHANG : target.getPrice().setSell(value); break;
			case MOD_ADD : target.getPrice().setSell(target.getPrice().getCoin()+value); break;
			case MOD_PERCENT : target.getPrice().setSell(target.getPrice().getCoin()+(target.getPrice().getCoin()*value/100)); break;
			case MOD_MULT : target.getPrice().setSell(target.getPrice().getCoin()*value); break;
		}
	}

	public void applySac(Thing target) {
		if(MilkKind.checkThingKind(MilkKind.Slaves, target.getKind())){
			Slave realTarget = (Slave) target;
			switch (mod) {
				case MOD_REM : realTarget.getSacrifice().getQuantity().setQuant(0); break;
				case MOD_CHANG : realTarget.getSacrifice().getQuantity().setQuant(value.intValue()); break;
				case MOD_ADD : realTarget.getSacrifice().getQuantity().setQuant(realTarget.getSacrifice().getQuantity().getQuant()+value.intValue()); break;
				case MOD_PERCENT : realTarget.getSacrifice().getQuantity().setQuant(realTarget.getSacrifice().getQuantity().getQuant()+(realTarget.getSacrifice().getQuantity().getQuant()*value.intValue()/100)); break;
				case MOD_MULT : realTarget.getSacrifice().getQuantity().setQuant(realTarget.getSacrifice().getQuantity().getQuant()*value.intValue()); break;
			}
		}
	}

	public void applyProd(Thing target) {
		switch (mod) {
			case MOD_REM : target.setProductivity(0); break;
			case MOD_CHANG : target.setProductivity(value.intValue()); break;
		}
	}

	public void applyQuant(Thing target) {
		switch (mod) {
			case MOD_REM : target.getIncome().getAttrib().setQuant(new Float(0)); break;
			case MOD_CHANG : target.getIncome().getAttrib().setQuant(value); break;
			case MOD_ADD : target.getIncome().getAttrib().setQuant(target.getIncome().getAttrib().getQuant()+value); break;
			case MOD_PERCENT : target.getIncome().getAttrib().setQuant(target.getIncome().getAttrib().getQuant()+(target.getIncome().getAttrib().getQuant()*value/100)); break;
			case MOD_MULT : target.getIncome().getAttrib().setQuant(target.getIncome().getAttrib().getQuant()*value); break;
		}
	}

	public void applyQual(Thing target) {
		switch (mod) {
			case MOD_REM : target.getIncome().getAttrib().setQual(new Float(0)); break;
			case MOD_CHANG : target.getIncome().getAttrib().setQual(value); break;
			case MOD_ADD : target.getIncome().getAttrib().setQual(target.getIncome().getAttrib().getQual()+value); break;
			case MOD_PERCENT : target.getIncome().getAttrib().setQual(target.getIncome().getAttrib().getQual()+(target.getIncome().getAttrib().getQual()*value/100)); break;
			case MOD_MULT : target.getIncome().getAttrib().setQual(target.getIncome().getAttrib().getQual()*value); break;
		}
	}

	public void applyAgent(Thing target) {
		if(MilkKind.checkThingKind(MilkKind.Building, target.getKind())){
			Building realTarget = (Building) target;
			switch (mod) {
				case MOD_REM : realTarget.getAgent().getQuantity().setQuant(0); break;
				case MOD_CHANG : realTarget.getAgent().getQuantity().setQuant(value.intValue()); break;
				case MOD_ADD : realTarget.getAgent().getQuantity().setQuant(realTarget.getAgent().getQuantity().getQuant()+value.intValue()); break;
				case MOD_PERCENT : realTarget.getAgent().getQuantity().setQuant(realTarget.getAgent().getQuantity().getQuant()+(realTarget.getAgent().getQuantity().getQuant()*value.intValue()/100)); break;
				case MOD_MULT : realTarget.getAgent().getQuantity().setQuant(realTarget.getAgent().getQuantity().getQuant()*value.intValue()); break;
			}
		}
	}

	public void applyPop(Thing target) {
		if(MilkKind.checkThingKind(MilkKind.Building, target.getKind())){
			Building realTarget = (Building) target;
			switch (mod) {
				case MOD_REM : realTarget.getPopulation().getQuantity().setQuant(0); break;
				case MOD_CHANG : realTarget.getPopulation().getQuantity().setQuant(value.intValue()); break;
				case MOD_ADD : realTarget.getPopulation().getQuantity().setQuant(realTarget.getPopulation().getQuantity().getQuant()+value.intValue()); break;
				case MOD_PERCENT : realTarget.getPopulation().getQuantity().setQuant(realTarget.getPopulation().getQuantity().getQuant()+(realTarget.getPopulation().getQuantity().getQuant()*value.intValue()/100)); break;
				case MOD_MULT : realTarget.getPopulation().getQuantity().setQuant(realTarget.getPopulation().getQuantity().getQuant()*value.intValue()); break;
			}
		}
	}
}
