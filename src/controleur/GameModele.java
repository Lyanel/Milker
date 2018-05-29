package controleur;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.ObservableList;
import modele.MilkDate;
import modele.MilkKind;
import modele.MilkXmlObj;
import modele.carac.NeededIntel;
import modele.carac.NeededThing;
import modele.intel.Ascension;
import modele.intel.Intel;
import modele.intel.Research;
import modele.intel.Synergy;
import modele.intel.Upgrade;
import modele.thing.Animal;
import modele.thing.Building;
import modele.thing.Slave;
import modele.thing.SlaveAnimal;
import modele.thing.SlaveHuman;
import modele.thing.Thing;
import modele.thing.Worker;
import modele.toggle.Toggle;
import modele.toggle.ToggleOption;
import javafx.util.Duration;

public class GameModele {
	
	private MilkDate plaYear;
	private String name;
	private DoubleProperty milkCoin = new SimpleDoubleProperty(0.0);
	
	public GameModele() {
		plaYear = new MilkDate () ;
    	
		Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0),
				event -> milkCoin.setValue(milkCoin.doubleValue() + getIncome())),
				new KeyFrame(Duration.seconds(1)));
		timeline.setCycleCount(Animation.INDEFINITE);
		timeline.play();
		
	}
	
	/*
	 * Method retournant le gain de jetonlait/seconde.
	 * Par encore programmé. 
	 * return 1
	 * */
	private double getIncome() {
		double tIncome = 0.0;
		double toolProdBonus = 1.0;
		double toolQualBonus = 1.0;
		double cattleProdBonus = 0.0;
		double cattleQualBonus = 0.0;
		double buildProdBonus = 0.0;
		double buildQualBonus = 0.0;
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
	
	public boolean isMilkObjResearched(MilkXmlObj value) {
		boolean visible = true;
		for (NeededIntel need: value.getNeed().getNeededIntels()) {
			switch (need.getKind().getKind()) {
				case MilkKind.kind_Research: {
						visible = MilkCheck.checkIntel(need, getResearch());
					}
					break;
				case MilkKind.kind_Upgrade: {
						visible = MilkCheck.checkIntel(need, getUpgrade());
					}
					break;
				case MilkKind.kind_Synergy: {
						visible = MilkCheck.checkIntel(need, getSynergy());
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
			switch (need.getKind().getKind()) {
				case MilkKind.kind_Building: {
					visible = MilkCheck.checkThing(need, Building.getFullListe());
				}
				break;
				case MilkKind.kind_Worker: {
					visible = MilkCheck.checkThing(need, Worker.getFullListe());
				}
				break;
				case MilkKind.kind_Slave_Human: {
					visible = MilkCheck.checkThing(need, SlaveHuman.getFullListe());
				}
				break;
				case MilkKind.kind_Slave_Animal: {
					visible = MilkCheck.checkThing(need, SlaveAnimal.getFullListe());
				}
				break;
				case MilkKind.kind_Animal: {
					visible = MilkCheck.checkThing(need, Animal.getFullListe());
				}
				break;
				case MilkKind.kind_Semi_Human: {
					visible = MilkCheck.checkThing(need, Worker.getFullListe(), SlaveHuman.getFullListe());
				}
				break;
				case MilkKind.kind_Slaves: {
					visible = MilkCheck.checkThing(need, SlaveHuman.getFullListe(), SlaveAnimal.getFullListe());
				}
				break;
				case MilkKind.kind_Semi_Animal: {
					visible = MilkCheck.checkThing(need, SlaveAnimal.getFullListe(), Animal.getFullListe());
				}
				break;
				case MilkKind.kind_Earthling_People: {
					visible = MilkCheck.checkThing(need, Worker.getFullListe(), SlaveAnimal.getFullListe());
				}
				break;
				case MilkKind.kind_Natural_Cattle: {
					visible = MilkCheck.checkThing(need, SlaveHuman.getFullListe(), Animal.getFullListe());
				}
				break;
				case MilkKind.kind_People: {
					visible = MilkCheck.checkThing(need, Worker.getFullListe(), SlaveHuman.getFullListe(), SlaveAnimal.getFullListe());
				}
				break;
				case MilkKind.kind_Cattle: {
					visible = MilkCheck.checkThing(need, SlaveHuman.getFullListe(), SlaveAnimal.getFullListe(), Animal.getFullListe());
				}
				break;
				case MilkKind.kind_Earthling_Being: {
					visible = MilkCheck.checkThing(need, Worker.getFullListe(), SlaveAnimal.getFullListe(), Animal.getFullListe());
				}
				break;
				case MilkKind.kind_Natural_Being: {
					visible = MilkCheck.checkThing(need, Worker.getFullListe(), SlaveHuman.getFullListe(), Animal.getFullListe());
				}
				break;
				case MilkKind.kind_Living_Being: {
					visible = MilkCheck.checkThing(need, Worker.getFullListe(), SlaveHuman.getFullListe(), Animal.getFullListe(), Animal.getFullListe());
				}
				break;
				default:
					break;
			}
		}
		return visible;
	}
	
	public boolean areMilkObjOwned(Research value) {
		int count = 0;
		for (NeededThing need: value.getCheck().getNeededThings()) {
			switch (need.getKind().getKind()) {
				case MilkKind.kind_Building: {
					if(MilkCheck.checkThing(need, Building.getFullListe()))count+=1;
				}
				break;
				case MilkKind.kind_Worker: {
					if(MilkCheck.checkThing(need, Worker.getFullListe()))count+=1;
				}
				break;
				case MilkKind.kind_Slave_Human: {
					if(MilkCheck.checkThing(need, SlaveHuman.getFullListe()))count+=1;
				}
				break;
				case MilkKind.kind_Slave_Animal: {
					if(MilkCheck.checkThing(need, SlaveAnimal.getFullListe()))count+=1;
				}
				break;
				case MilkKind.kind_Animal: {
					if(MilkCheck.checkThing(need, Animal.getFullListe()))count+=1;
				}
				break;
				case MilkKind.kind_Semi_Human: {
					if(MilkCheck.checkThing(need, Worker.getFullListe(), SlaveHuman.getFullListe()))count+=1;
				}
				break;
				case MilkKind.kind_Slaves: {
					if(MilkCheck.checkThing(need, SlaveHuman.getFullListe(), SlaveAnimal.getFullListe()))count+=1;
				}
				break;
				case MilkKind.kind_Semi_Animal: {
					if(MilkCheck.checkThing(need, SlaveAnimal.getFullListe(), Animal.getFullListe()))count+=1;
				}
				break;
				case MilkKind.kind_Earthling_People: {
					if(MilkCheck.checkThing(need, Worker.getFullListe(), SlaveAnimal.getFullListe()))count+=1;
				}
				break;
				case MilkKind.kind_Natural_Cattle: {
					if(MilkCheck.checkThing(need, SlaveHuman.getFullListe(), Animal.getFullListe()))count+=1;
				}
				break;
				case MilkKind.kind_People: {
					if(MilkCheck.checkThing(need, Worker.getFullListe(), SlaveHuman.getFullListe(), SlaveAnimal.getFullListe()))count+=1;
				}
				break;
				case MilkKind.kind_Cattle: {
					if(MilkCheck.checkThing(need, SlaveHuman.getFullListe(), SlaveAnimal.getFullListe(), Animal.getFullListe()))count+=1;
				}
				break;
				case MilkKind.kind_Earthling_Being: {
					if(MilkCheck.checkThing(need, Worker.getFullListe(), SlaveAnimal.getFullListe(), Animal.getFullListe()))count+=1;
				}
				break;
				case MilkKind.kind_Natural_Being: {
					if(MilkCheck.checkThing(need, Worker.getFullListe(), SlaveHuman.getFullListe(), Animal.getFullListe()))count+=1;
				}
				break;
				case MilkKind.kind_Living_Being: {
					if(MilkCheck.checkThing(need, Worker.getFullListe(), SlaveHuman.getFullListe(), Animal.getFullListe(), Animal.getFullListe()))count+=1;
				}
				break;
				default:
					break;
			}
			if(count >= value.getCheck().getMod())return true;
				
		}
		return false;
	}

	public boolean isThingbuyable(Intel value) {
		boolean result = false;
		if(value.getPriceValue()<=milkCoin.doubleValue()){
			if(value instanceof Thing)result = true;
			else if (!value.bought()) result = true;
		}
		return result;
	}

	public void buyThing(Intel value) {
		if(isThingbuyable(value)){
			value.buy();
			milkCoin.setValue(milkCoin.doubleValue() - value.getPriceValue());
		}
	}

	public void statueClicked() {
		for(Building building: Building.getNeutralListe()) {
			if (building.getId().intValue()==10) milkCoin.setValue(milkCoin.doubleValue() + (building.getIncome().getProd()*(building.getAttrib().getQuant()+building.getStart())));
		}
		
	}

	public boolean isSwitchable(MilkXmlObj value) {
		// TODO Auto-generated method stub
		return true;
	}

	public boolean isToggleSwitchable(Toggle value) {
		// TODO Auto-generated method stub
		return false;
	}

	public void switchToggle(ToggleOption value) {
		// TODO Auto-generated method stub
		
	}
}
