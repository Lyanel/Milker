package modele.thing;

import modele.MilkKind;
import modele.carac.Agent;

import org.w3c.dom.Element;

public class SlaveWorker extends SlaveHuman implements Cloneable {

	public static final String noeud = Slave.noeud;
	private Agent agent;

	// Constructors
	
	public SlaveWorker() {
		super();
		this.agent = new Agent();
		this.setKind(MilkKind.kind_Slave_Worker);
	}
	public SlaveWorker(Element milkElement) {
		super();
		this.agent = new Agent();
		this.setKind(MilkKind.kind_Slave_Worker);
		this.setValueFromNode(milkElement);
	}
	
	// Set value from Element methods
	
	@Override
	public void setValueFromNode(Element milkElement) {
		super.setValueFromNode(milkElement);
		this.setAgent(milkElement);
	}
	@Override
	public void setNullValueFromNode(Element milkElement) {
		super.setNullValueFromNode(milkElement);
		this.setAgent(milkElement);
	}
	
	// field methods

	public Agent getAgent() {
		return this.agent;
	}
	public void setAgent(Agent agent) {
		this.agent = agent;
	}
	public void setAgent(Element milkElement) {
		this.agent.setValueFromNode(milkElement);;
	}
	public void setNullAgent(Element milkElement) {
		this.agent.setNullValueFromNode(milkElement);
	}
	
	// toString & toXml methods
	@Override
	public String toStringStatChild() {
		String temp = super.toStringStatChild();
		if (this.agent != null) temp += this.agent.toXmlStat();
		return temp;
	}
	@Override
	public String toXmlStatChild() {
		String temp = super.toXmlStatChild();
		if (this.agent != null) temp += this.agent.toXmlStat();
		return temp;
	}
	
	// other object methods

	@Override
	public boolean allZero()  {
		boolean temp = super.allZero();
		if(this.agent!=null && !this.agent.allZero()) temp= false;
		return temp;
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		SlaveWorker clone = (SlaveWorker) super.clone();
		if (this.agent!=null) clone.setAgent((Agent) this.agent.clone());
		return clone;
	}
}