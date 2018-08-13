package modele.carac;

import modele.ParseMilkFile;

import modele.baseObject.MilkVar;

import java.util.ArrayList;
import java.util.Collection;

import org.w3c.dom.Element;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

	/**
	 * @author Slade Chaos
	 */
public class Quantity extends MilkVar {
	
	public static final String xmlQuant = "quant";
	
	// Fields
	private Collection<QuantityListener> quantlistener = new ArrayList<QuantityListener>(), activelistener = new ArrayList<QuantityListener>();
	private IntegerProperty quant, active, semiActive;
	
	// Constructors
	
	public Quantity() {
		this(0);
	}
	public Quantity(Integer quant) {
		super();
		this.setQuant(quant);
		this.setActive(0);
	}
	public Quantity(Element milkElement) {
		super();
		this.setValueFromNode(milkElement);
	}
	public Quantity(Quantity original) {
		super(original);
		this.setQuant(original.getQuant().intValue());
		this.setActive(original.getActives().intValue());
		this.setSemiActive(original.getSemiActives().intValue());
	}

	// Set value from Element methods
	
	@Override
	public void setValueFromNode(Element milkElement) {
		super.setValueFromNode(milkElement);
		this.setQuant(milkElement);
		this.setActive(0);
	}
	public void setQuant(Element milkElement) {
		Integer temp=null;
		temp=ParseMilkFile.getXmlIntValue(milkElement);
		if (temp != null) this.setQuant(temp);
	}
	
	// field methods
		
	public Integer getQuant() {
		return this.quant.getValue();
	}
	public IntegerProperty getObrservableQuant() {
		return this.quant;
	}
	public String getStringQuant() {
		String temp = null;
		if (this.quant != null) temp = " "+xmlQuant+" = "+getQuant()+". ";
		return temp;
	}
	public String getXmlQuant() {
		String temp = "";
		if (this.quant != null) temp = ""+getQuant()+"";
		return temp;
	}
	public void setQuant(Integer newQuant) {
		if (this.quant == null) this.quant = new SimpleIntegerProperty(newQuant);
		else {
			int oldValue = getQuant();
			this.quant.setValue(newQuant);
			this.fireQuantityChanged(oldValue, newQuant);
		}
	}
    public void addQuantityListener(QuantityListener listener) {
		this.quantlistener.add(listener);
    }
	public void incrementeQuant(Integer quantToAdd) {
		setQuant(getQuant() + quantToAdd);
	}
	
	
	public Integer getActives() {
		return this.active.getValue();
	}
	public IntegerProperty getObrservableActives() {
		return this.active;
	}
	public void setActive(Integer newQuant) {
		if (this.active == null) this.active = new SimpleIntegerProperty(newQuant);
		else {
			int oldValue = getActives();
			this.active.setValue(newQuant);
			this.fireActiveChanged(oldValue, newQuant);
		}
	}
    public void addActiveListener(QuantityListener listener) {
		this.activelistener.add(listener);
    }
	public void incrementeActive(int activeToAdd) {
		setActive(getActives() + activeToAdd);
	}
	
	public Integer getSemiActives() {
		if (this.semiActive == null) this.semiActive = new SimpleIntegerProperty(0);
		return this.semiActive.getValue();
	}
	public IntegerProperty getObrservableSemiActives() {
		return this.semiActive;
	}
	public void setSemiActive(Integer newQuant) {
		if (this.semiActive == null) this.semiActive = new SimpleIntegerProperty(newQuant);
		else {
			this.semiActive.setValue(newQuant);
		}
	}

	
    protected void fireQuantityChanged(double oldValue, double newValue) {
        for(QuantityListener listener : quantlistener) {
            listener.quantityChanged(oldValue, newValue);
        }
    }
    protected void fireActiveChanged(double oldValue, double newValue) {
        for(QuantityListener listener : activelistener) {
            listener.quantityChanged(oldValue, newValue);
        }
    }
    
	// toString & toXml methods
	
	@Override
	public String toStringStatChild() {
		String temp = super.toXmlStatChild();
		temp+=this.getStringQuant();
		return temp;
	}
	@Override
	public String toXmlStatChild() {
		String temp = super.toXmlStatChild();
		 temp+=this.getXmlQuant();
		return temp;
	}
	
	// other object methods

	@Override
	public boolean allZero() {
		boolean temp = super.allZero();
		if(this.quant!=null && getQuant()!=0) temp= false;
		return temp;
	}
	
}
