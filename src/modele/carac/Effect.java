package modele.carac;

import java.util.ArrayList;

import org.w3c.dom.Element;

import controleur.ParseMilkFile;
import modele.MilkRs;
import modele.MilkVar;
import modele.XmlHelper;
import modele.thing.Thing;

public class Effect extends MilkVar implements Cloneable {

	public static final String noeud = "effect", xmlMod = "mod", xmlCoef = "coef";
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
	
	private Float coef;
	private Integer mod;
	private ArrayList<Agent> agents = null;	
	private ArrayList<Thing> things = null;	
	
	// Constructors
	
	public Effect() {
		this.coef = (float)1;
		this.mod = 1;
		this.agents = new ArrayList<Agent>();
		this.things = new ArrayList<Thing>();
	}
	public Effect(Element milkElement) {
		super();
		this.coef = (float)1;
		this.mod = 1;
		this.agents = new ArrayList<Agent>();
		this.things = new ArrayList<Thing>();
		this.setValueFromNode(milkElement);
	}

	// Set value from Element methods
	
	@Override
	public void setValueFromNode(Element milkElement) {
		super.setValueFromNode(milkElement);
		this.setCoef(milkElement);
		this.setMod(milkElement);
		this.setAgents(milkElement);
		this.setThings(milkElement);
	}
	public void setCoef(Element milkElement) {
		Float temp=null;
		temp=ParseMilkFile.getXmlFloatAttribute(milkElement,xmlCoef);
		if (temp != null) this.coef=temp;
	}
	public void setMod(Element milkElement) {
		Integer temp=null;
		temp=ParseMilkFile.getXmlIntAttribute(milkElement,xmlMod);
		if (temp != null) this.mod=temp;
	}
	
	public void setAgents(Element milkElement) {
		this.agents.addAll(Agent.getMilkVarList(milkElement));
	}
	
	public void setThings(Element milkElement) {
		this.things.addAll(Thing.getMilkVarList(milkElement));
	}
	
	// field methods

	public Float getCoef() {
		return this.coef;
	}
	public String getStringCoef() {
		String temp = null;
		if (this.coef != null) temp = xmlCoef+" : "+this.coef+". ";
		return temp;
	}
	public String getXmlCoef() {
		String temp = null;
		if (this.coef != null) temp = " "+xmlCoef+"=\""+coef+"\"";
		return temp;
	}
	public void setCoef(Float coef) {
		this.coef = coef;
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
	
	public ArrayList<Agent> getAgents() {
		return agents;
	}
	public void setAgents(ArrayList<Agent> agents) {
		this.agents = agents;
	}
	
	public ArrayList<Thing> getThings() {
		return things;
	}
	public void setThings(ArrayList<Thing> things) {
		this.things = things;
	}
	
	// toString & toXml methods
	
	@Override
	public String toStringAttrib() {
		String temp = super.toStringAttrib();
		temp+=this.getStringCoef();
		temp+=this.getStringMod();
		return temp;
	}
	@Override
	public String toXmlAttrib() {
		String temp = super.toXmlAttrib();
		temp+=this.getXmlCoef();
		temp+=this.getXmlMod();
		return temp;
	}
	@Override
	public String toStringStatChild() {
		String temp = super.toStringStatChild();
		if(agents.size()>0){
			temp += MilkRs.LIGNE_TAB+Agent.noeud+"s : "+MilkRs.LIGNE_BREAK;
			for (Agent agent : agents) {
				temp += MilkRs.LIGNE_TAB+agent.toStringStat();
			}
		}
		if(things.size()>0){
			temp += MilkRs.LIGNE_TAB+Thing.noeud+"s : "+MilkRs.LIGNE_BREAK;
			for (Thing neededThing : things) {
				temp += MilkRs.LIGNE_TAB+neededThing.toStringStat();
			}
		}
		return temp;
	}
	@Override
	public String toXmlStatChild() {
		String temp = super.toXmlStatChild();
		if(agents.size()>0){
			temp += "<"+Agent.noeud+"s>"+MilkRs.LIGNE_BREAK;
			for (Agent agent : agents) {
				temp += MilkRs.LIGNE_TAB+agent.toXmlStat();
			}
		temp += "</"+Agent.noeud+"s>"+MilkRs.LIGNE_BREAK;
		}
		if(things.size()>0){
			temp += "<"+Thing.noeud+"s>"+MilkRs.LIGNE_BREAK;
			for (Thing neededThing : things) {
				temp += MilkRs.LIGNE_TAB+neededThing.toXmlStat();
			}
		temp += "</"+Thing.noeud+"s>"+MilkRs.LIGNE_BREAK;
		}
		return temp;
	}

	// other object methods
	
	public ArrayList<Agent> getCloneAgents() throws CloneNotSupportedException {
		ArrayList<Agent> clone = new ArrayList<Agent>();
		if (this.agents!=null) for (Agent agent:this.agents) clone.add((Agent) agent.clone());
		return clone;
	}
	public ArrayList<Thing> getCloneThings() throws CloneNotSupportedException {
		ArrayList<Thing> clone = new ArrayList<Thing>();
		if (this.things!=null) for (Thing neededThing:this.things) clone.add((Thing) neededThing.clone());
		return clone;
	}

	@Override
	public boolean allZero()  {
		boolean temp = super.allZero();
		if(this.agents!=null && this.agents.size()!=0) temp= false;
		if(this.things!=null && this.things.size()!=0) temp= false;
		return temp;
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		Effect clone = (Effect) super.clone();
		clone.setAgents(getCloneAgents());
		clone.setThings(getCloneThings());
		return clone;
	}
}
