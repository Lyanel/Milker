package modele.carac;

import java.util.Vector;

import org.w3c.dom.Element;

public class Agent extends NeededThing implements Cloneable {
	
	public static final String noeud = "agent";
	public String getNoeud() {return noeud;}

	@SuppressWarnings("rawtypes")
	public static Vector getMilkVarList(Element elementlist) {
		Vector<Agent> agents = new Vector<Agent>();
		Agent agent=new Agent();
		Element elements = agent.getMilkElementList(elementlist);
		int size = (elements!=null)? elements.getChildNodes().getLength():0;
		for (int i=0;i<size;i++){ 
			Element tempE=null;
			tempE=agent.getMilkElement(elements,i);
			if (tempE != null){
				agent=new Agent();
				agent.setValueFromNode(tempE);
				agents.add(agent);
			}
		}
		return agents;
	}
	@SuppressWarnings("rawtypes")
	public static Vector getMilkVarList(Vector<Element> elementlist) {
		Vector<Agent> agents = new Vector<Agent>();
		for (Element elementMilk: elementlist) {
			try {
				Agent agent = new Agent(elementMilk);
				agents.add(agent);
			} catch (Exception e) {e.printStackTrace();}
		}
		return agents;
	}
	/*
	@SuppressWarnings("rawtypes")
	public static Vector getNullMilkVarList(Element elementlist) {
		Vector<Agent> agents = new Vector<Agent>();
		Agent agent=new Agent();
		Element elements = agent.getMilkElementList(elementlist);
		int size = (elements!=null)? elements.getChildNodes().getLength():0;
		for (int i=0;i<size;i++){ 
			Element tempE=null;
			tempE=agent.getMilkElement(elements,i);
			if (tempE != null){
				agent=new Agent();
				agent.setNullValueFromNode(tempE);
				agents.add(agent);
			}
		}
		return agents;
	}
	@SuppressWarnings("rawtypes")
	public static Vector getNullMilkVarList(Vector<Element> elementlist) {
		Vector<Agent> agents = new Vector<Agent>();
		for (Element elementMilk: elementlist) {
			try {
				Agent agent = new Agent();
				agent.setNullValueFromNode(elementMilk);
				agents.add(agent);
			} catch (Exception e) {e.printStackTrace();}
		}
		return agents;
	}*/

	// Constructors
	
	public Agent() {
		super();
	}
	public Agent(Element milkElement) {
		super();
		this.setValueFromNode(milkElement);
	}
	
	// Set value from Element methods
	
	@Override
	public void setValueFromNode(Element milkElement) {
		Element thisElement = this.getThisElementFromParent(milkElement);
		if(milkElement != thisElement) super.setValueFromNode(thisElement);
	}
	/*
	@Override
	public void setNullValueFromNode(Element milkElement) {
		Element thisElement = this.getThisElementFromParent(milkElement);
		super.setNullValueFromNode(thisElement);
	}*/
	
	// other object methods
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		Agent clone = (Agent) super.clone();
		return clone;
	}
}
