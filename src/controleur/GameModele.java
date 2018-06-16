package controleur;

import java.util.ArrayList;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.ObservableList;
import modele.MilkDate;
import modele.MilkKind;
import modele.MilkXmlObj;
import modele.carac.Agent;
import modele.carac.NeededIntel;
import modele.carac.NeededThing;
import modele.carac.QuantityListener;
import modele.intel.Ascension;
import modele.intel.Intel;
import modele.intel.Research;
import modele.intel.Synergy;
import modele.intel.Upgrade;
import modele.thing.Animal;
import modele.thing.Building;
import modele.thing.Slave;
import modele.thing.Thing;
import modele.thing.Worker;
import modele.toggle.Toggle;
import modele.toggle.ToggleLevel;
import modele.toggle.ToggleOption;
import javafx.util.Duration;

public class GameModele {
	
	private MilkDate plaYear;
	private String name;
	private DoubleProperty milkCoin = new SimpleDoubleProperty(0.0), toolTogglePrice = new SimpleDoubleProperty(0.0), idolTogglePrice = new SimpleDoubleProperty(0.0), eventTogglePrice = new SimpleDoubleProperty(0.0);
	private float cattleProdBonus = 0, cattleQualBonus = 0, buildProdBonus = 0, buildQualBonus = 0;
	
	public GameModele() {
		plaYear = new MilkDate () ;
    	
		Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0),
				event -> milkCoin.setValue(milkCoin.doubleValue() + getIncome())),
				new KeyFrame(Duration.seconds(1)));
		timeline.setCycleCount(Animation.INDEFINITE);
		timeline.play();
		setDoublePropertyListener();
	}

	private void setDoublePropertyListener() {
		Utility.addQuantityListenerToThings(getToggles().get(0).getAgent(), new QuantityListener() {
			@Override
			public void quantityChanged(double oldQuantity, double newQuantity) {
				setToolTogglePrice();
			}
		});
		Utility.addQuantityListenerToThings(getToggles().get(1).getAgent(), new QuantityListener() {
			@Override
			public void quantityChanged(double oldQuantity, double newQuantity) {
				setIdolTogglePrice();
			}
		});
		Utility.addQuantityListenerToThings(getToggles().get(2).getAgent(), new QuantityListener() {
			@Override
			public void quantityChanged(double oldQuantity, double newQuantity) {
				setEventTogglePrice();
			}
		});
		Agent building = new Agent();
		building.setKind(MilkKind.kind_Building);
		Utility.addQuantityListenerToThings(building, new QuantityListener() {
			@Override
			public void quantityChanged(double oldQuantity, double newQuantity) {
				setIncomeBonus();
			}
		});
	}

	/*
	 * Method retournant le gain de jetonlait/seconde.
	 * Par encore programmé. 
	 * return 1
	 * */
	private double getIncome() {
		double tIncome = 0;
		double toolProdBonus = getToggles().get(0).getselectedOption().getLevel().getBonus().getAttrib().getQuant().doubleValue();
		double toolQualBonus = getToggles().get(0).getselectedOption().getLevel().getBonus().getAttrib().getQual().doubleValue();
		
		tIncome+=Building.getIncomeFromList(buildProdBonus,buildQualBonus); 
		tIncome+=Animal.getIncomeFromList(toolProdBonus,toolQualBonus,cattleProdBonus,cattleQualBonus,buildProdBonus,buildQualBonus); 
		tIncome+=Slave.getIncomeFromList(toolProdBonus,toolQualBonus,cattleProdBonus,cattleQualBonus,buildProdBonus,buildQualBonus); 
		tIncome+=Worker.getIncomeFromList(toolProdBonus,toolQualBonus,cattleProdBonus,cattleQualBonus,buildProdBonus,buildQualBonus);
		return tIncome;
	}

	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return this.name;
	}
	
	public Double getMilkCoinValue() {
		return this.milkCoin.doubleValue();
	}

	public DoubleProperty getMilkCoin() {
		return this.milkCoin;
	}

	
	protected void setIncomeBonus() {
		cattleProdBonus = 0;
		cattleQualBonus = 0; 
		buildProdBonus = 0;
		buildQualBonus = 0;
		for (Building building:Building.getFullListe()){
			if(building.getBonus().getKind().intValue()==MilkKind.kind_Living_Being){
				cattleProdBonus+=(building.getBonus().getAttrib().getQuant().doubleValue()*building.getAttrib().getQuant().intValue());
				cattleQualBonus+=(building.getBonus().getAttrib().getQual().doubleValue()*building.getAttrib().getQuant().intValue());
			}else{
				buildProdBonus+=(building.getBonus().getAttrib().getQuant().doubleValue()*building.getAttrib().getQuant().intValue());
				buildQualBonus+=(building.getBonus().getAttrib().getQual().doubleValue()*building.getAttrib().getQuant().intValue());
			}
		}
		cattleProdBonus = 1+cattleProdBonus/100;
		cattleQualBonus = 1+cattleQualBonus/100; 
		buildProdBonus = 1+buildProdBonus/100;
		buildQualBonus = 1+buildQualBonus/100;
	}

	protected void setToolTogglePrice() {
		int quant = Utility.getThingsQuantity(getToggles().get(0).getAgent());
		toolTogglePrice.setValue(quant*getToggles().get(0).getPriceValue());
	}
	
	protected void setIdolTogglePrice() {
		int quant = Utility.getThingsQuantity(getToggles().get(1).getAgent());
		idolTogglePrice.setValue(quant*getToggles().get(1).getPriceValue());
	}
	
	protected void setEventTogglePrice() {
		int quant = Utility.getThingsQuantity(getToggles().get(2).getAgent());
		eventTogglePrice.setValue(quant*getToggles().get(2).getPriceValue());
	}
	
	public DoubleProperty getToolTogglePrice() {
		return toolTogglePrice;
	}

	public DoubleProperty getIdolTogglePrice() {
		return idolTogglePrice;
	}

	public DoubleProperty getEventTogglePrice() {
		return eventTogglePrice;
	}
	
	
	public MilkDate getPlaYear() {
		return plaYear;
	}

	public void setPlaYear(MilkDate plaYear) {
		this.plaYear = plaYear;
	}

	public ObservableList<Research> getResearch() {return Research.getResearchListe();}
	public ObservableList<Upgrade> getUpgrade() {return Upgrade.getUpgradeListe();}
	public ObservableList<Synergy> getSynergy() {return Synergy.getSynergyListe();}
	public ObservableList<Ascension> getAscension() {return null;}
	//public ObservableList<Event> getEvent() {return null;}

	public ObservableList<Toggle> getToggles() {return Toggle.getToggleListe();}

	public ObservableList<Building> getBuildingNeutral() {return Building.getNeutralListe();}
	public ObservableList<Building> getBuildingScience() {return Building.getScienceListe();}
	public ObservableList<Building> getBuildingMagic() {return Building.getMagicListe();}

	public ObservableList<Worker> getWorkerNeutral() {return Worker.getNeutralListe();}
	public ObservableList<Worker> getWorkerScience() {return Worker.getScienceListe();}
	public ObservableList<Worker> getWorkerMagic() {return Worker.getMagicListe();}

	public ObservableList<Slave> getSlaveNeutral() {return Slave.getNeutralListe();}
	public ObservableList<Slave> getSlaveScience() {return Slave.getScienceListe();}
	public ObservableList<Slave> getSlaveMagic() {return Slave.getMagicListe();}

	public ObservableList<Animal> getAnimalNeutral() {return Animal.getNeutralListe();}
	public ObservableList<Animal> getAnimalScience() {return Animal.getScienceListe();}
	public ObservableList<Animal> getAnimalMagic() {return Animal.getMagicListe();}
	
	public void statueClicked() {
		for(Building building: Building.getNeutralListe()) {
			if (building.getId().intValue()==10) 
				milkCoin.setValue( milkCoin.doubleValue() + building.getIncome().getProd() * building.getAttrib().getQuant() );
		}
		
	}
	
	public boolean isMilkObjVisible(MilkXmlObj value) {
		boolean visible = true;
		if(value.getNeed().getNeededIntels().size()>0) visible = isMilkObjResearched(value);
		if(visible && value.getNeed().getNeededThings().size()>0) visible = isMilkObjOwned(value);
		if(visible && value instanceof Research){
			Research tempResearch = (Research)value;
			if(tempResearch.getCheck().getNeededThings().size()>0)visible = areMilkObjOwned(tempResearch);
		}
		return visible;
	}

	public int getToolLevel(ToggleOption thing) {
		ArrayList<ToggleLevel> lvls = thing.getLevels();
		int retlvl=1;
		for (ToggleLevel lvl:lvls){
			for (NeededIntel need: lvl.getNeed().getNeededIntels()) {
				if (Utility.checkIntel(need, getResearch()) && lvl.getLvl() > retlvl) retlvl = lvl.getLvl();
			}
		}
		return retlvl;
	}
	
	public boolean isMilkObjResearched(MilkXmlObj value) {
		boolean visible = true;
		for (NeededIntel need: value.getNeed().getNeededIntels()) {
			switch (need.getKind().getKind()) {
				case MilkKind.kind_Research: {
						visible = Utility.checkIntel(need, getResearch());
					}
					break;
				case MilkKind.kind_Upgrade: {
						visible = Utility.checkIntel(need, getUpgrade());
					}
					break;
				case MilkKind.kind_Synergy: {
						visible = Utility.checkIntel(need, getSynergy());
					}
					break;
				case MilkKind.kind_Ascension: {
					visible = false; //MilkCheck.checkIntel(need, getAscension());
				}
				break;
				case MilkKind.kind_Event: {
					visible = false; //MilkCheck.checkIntel(need, getEvent());
				}
				break;
				default:
					break;
			}
		}
		return visible;
	}
	
	public boolean isMilkObjOwned(MilkXmlObj value) {
		boolean visible = true;
		for (NeededThing need: value.getNeed().getNeededThings()) {
			visible = Utility.checkThing(need, Utility.getThingsListsFromKind(need.getKind()));
		}
		return visible;
	}
	
	public boolean areMilkObjOwned(Research value) {
		int count = 0;
		for (NeededThing need: value.getCheck().getNeededThings()) {
			if(Utility.checkThing(need, Utility.getThingsListsFromKind(need.getKind())))count+=1;
			if(count >= value.getCheck().getMod().intValue())return true;
		}
		return false;
	}


	public boolean isIntelbuyable(Intel value) {
		boolean result = false;
		if(value.getPriceValue()<=milkCoin.doubleValue()){
			if(value instanceof Thing)result = true;
			else if (!value.bought()) result = true;
		}
		return result;
	}

	public void buyIntel(Intel value) {
		if(isIntelbuyable(value)){
			value.buy();
			milkCoin.setValue(milkCoin.doubleValue() - value.getPriceValue());
		}
	}

	public boolean isToolToggleSwitchable() {
		return (toolTogglePrice.doubleValue()<milkCoin.doubleValue())?true:false;
	}
	public boolean isIdolToggleSwitchable() {
		return (idolTogglePrice.doubleValue()<milkCoin.doubleValue())?true:false;
	}
	public boolean isEventToggleSwitchable() {
		return (eventTogglePrice.doubleValue()<milkCoin.doubleValue())?true:false;
	}

	public void switchToolToggle(ToggleOption value) {
		if (isToolToggleSwitchable()){
			getToggles().get(0).setOptionSelected(value);
			milkCoin.setValue(milkCoin.doubleValue() - toolTogglePrice.doubleValue());
		}
	}
	public void switchIdolToggle(ToggleOption value) {
		if (isIdolToggleSwitchable()){
			getToggles().get(1).setOptionSelected(value);
			milkCoin.setValue(milkCoin.doubleValue() - idolTogglePrice.doubleValue());
		}
	}
	public void switchEventToggle(ToggleOption value) {
		if (isEventToggleSwitchable()){
			getToggles().get(2).setOptionSelected(value);
			milkCoin.setValue(milkCoin.doubleValue() - eventTogglePrice.doubleValue());
		}
	}
}
