package modele.thing;

import modele.carac.MilkTree;
import modele.ParseMilkFile;
import modele.baseObject.MilkKind;
import modele.carac.Income;
import modele.carac.MilkProd;
import modele.carac.NeededThing;
import modele.carac.Quantity;

import java.util.ArrayList;
import java.util.Vector;

import org.w3c.dom.Element;

import javafx.collections.ObservableList;

public class Thing extends NearThing {
	
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
				switch(thing.getKind().getKind()){
					case MilkKind.Building		:  thing = new Building(element); break;
					case MilkKind.Worker		:  thing = new Worker(element); break;
					case MilkKind.Slave_Worker	:  thing = new SlaveWorker(element); break;
					case MilkKind.Slave_Humanoid:  thing = new SlaveHuman(element); break;
					case MilkKind.Slave_Animal	:  thing = new SlaveAnimal(element); break;
					case MilkKind.Animal		:  thing = new Animal(element); break;
				}
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
	
	@SuppressWarnings({ "unchecked" }) @SafeVarargs
	protected static ObservableList<Thing> merge(ObservableList<? extends Thing> into, ObservableList<? extends Thing>... lists) {
        final ObservableList<Thing> list = (ObservableList<Thing>) into;
        for (ObservableList<? extends Thing> l : lists) {
            list.addAll(l);
            l.addListener((javafx.collections.ListChangeListener.Change<? extends Thing> c) -> {
                while (c.next()) {
                    if (c.wasAdded()) {
                        list.addAll(c.getAddedSubList());
                    }
                    if (c.wasRemoved()) {
                        list.removeAll(c.getRemoved());
                    }
                    if (c.wasUpdated()) {
                        list.removeAll(c.getRemoved());
                        list.addAll(c.getAddedSubList());
                    }
                }
            });
        }
        return list;
    }

	// Fields
	
	private Integer lvl, get;
	private MilkTree tree;
	private Quantity quant;
	private Income income;

	// Constructors
	
	public Thing() {
		super();
		this.setLvl(0);
		this.setGet(0);
		this.tree = new MilkTree();
		this.quant = new Quantity();
		this.income = new Income();
	}
	public Thing(Element milkElement) {
		super();
		this.setLvl(0);
		this.setGet(0);
		this.tree = new MilkTree();
		this.quant = new Quantity();
		this.income = new Income();
		this.setValueFromNode(milkElement);
	}
	public Thing(Thing original) {
		super(original);
		this.lvl = new Integer(original.getLvl());
		this.get = new Integer(original.getGet());
		this.tree = new MilkTree(original.getTree());
		this.quant = new Quantity(original.getQuantity());
		this.income = new Income(original.getIncome());
	}
	
	// Set value from Element methods

	@Override
	public void setValueFromNode(Element milkElement) {
		super.setValueFromNode(milkElement);
		this.setLvl(milkElement);
		this.setGet(milkElement);
		this.setTree(milkElement);
		this.setQuantity(milkElement);
		this.setIncome(milkElement);
		if(this.getStart().intValue()>0){
			this.quant.incrementeQuant(this.getStart().intValue());
			this.quant.incrementeActive(this.getStart().intValue());
		}
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
	public void setTree(Element milkElement) {
		this.tree.setValueFromNode(milkElement);
	}
	public void setQuantity(Element milkElement) {
		this.quant.setValueFromNode(milkElement);
	}
	public void setIncome(Element milkElement) {
		this.income.setValueFromNode(milkElement);
	}	
	
	// field methods

	@Override
	public Double getPriceValue() {
		return super.getPrice().getCoin()*(1+Math.pow(this.getQuantity().getQuant(),super.getPrice().getCoef())/50);
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
	
	public MilkTree getTree() {
		return this.tree;
	}
	public void setTree(MilkTree tree) {
		this.tree = tree;
	}
	
	public Quantity getQuantity() {
		return quant;
	}
	public void setQuantity(Quantity quant) {
		this.quant = quant;
	}
	
	public void incrementeQuant(Integer quantToAdd) {
		this.quant.incrementeQuant(quantToAdd);
	}

	public Income getIncome() {
		return this.income;
	}
	public double getIncome(double buildProdBonus, double buildQualBonus) {
		double tIncome = 0;
		Integer thingQuant = this.getQuantity().getActives();
		MilkProd attrib = this.getIncome().getAttrib();
		if(thingQuant>0){
			if(this.getIncome().canProdMilk()){
				double milkQuant = attrib.getQuant();
				double milkQual = attrib.getQual();
				tIncome += thingQuant *( milkQuant+milkQuant*buildProdBonus ) * (milkQual+milkQual*buildQualBonus) ;
			} else if(this.getIncome().canProdCoin()) tIncome += thingQuant * this.getIncome().getCoin() ;
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
		if (this.tree != null) temp = this.tree.toStringAttrib();
		return temp;
	}
	@Override
	public String toXmlAttrib() {
		String temp = super.toXmlAttrib();
		temp+=this.getXmlLvl();
		temp+=this.getXmlGet();
		if (this.tree != null) temp = this.tree.toXmlAttrib();
		return temp;
	}
	@Override
	public String toStringStatChild() {
		String temp = super.toStringStatChild();
		if (this.quant != null) temp = this.quant.toXmlStatChild();
		if (this.income != null) temp += this.income.toStringStat();
		return temp;
	}
	@Override
	public String toXmlStatChild() {
		String temp = super.toXmlStatChild();
		if (this.quant != null) temp = this.quant.toXmlStatChild();
		if (this.income != null) temp += this.income.toXmlStat();
		return temp;
	}
	
	// other object methods
	
	@Override
	public void buy() {
		this.getQuantity().setQuant(this.getQuantity().getQuant()+1);
	}
	
	public NeededThing toNeededThing() {
		NeededThing save = new NeededThing();
		save.setId(this.getId());
		save.setKind(this.getKind().getKind());
		save.getQuantity().setQuant(this.getQuantity().getQuant());
		return save;
	}

	@Override
	public boolean allZero()  {
		boolean temp = super.allZero();
		if(this.tree!=null && !this.tree.allZero()) temp= false;
		if(this.quant!=null && !this.quant.allZero()) temp= false;
		if(this.income!=null && !this.income.allZero()) temp= false;
		return temp;
	}
}