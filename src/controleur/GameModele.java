package controleur;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import modele.MilkDate;
import modele.MilkKind;
import modele.carac.NeededIntel;
import modele.intel.Research;
import modele.intel.Upgrade;
import modele.thing.Animal;
import modele.thing.Building;
import modele.thing.Slave;
import modele.thing.Thing;
import modele.thing.Worker;

public class GameModele {

	private MilkDate plaYear;
	private String name;
	private Double milkCoin = 0.0;
	private SimpleStringProperty milkCoinString;
	
	private ObservableList<Research> researchs;
	private ObservableList<Upgrade> upgrades;
	//private ObservableList<Toggle> toggles;
	
	private ObservableList<Building> buildingNeutral;
	private ObservableList<Building> buildingScience;
	private ObservableList<Building> buildingMagic;
	
	private ObservableList<Worker> workerNeutral;
	private ObservableList<Worker> workerScience;
	private ObservableList<Worker> workerMagic;
	
	private ObservableList<Slave> slaveNeutral;
	private ObservableList<Slave> slaveScience;
	private ObservableList<Slave> slaveMagic;
	
	private ObservableList<Animal> animalNeutral;
	private ObservableList<Animal> animalScience;
	private ObservableList<Animal> animalMagic;
	
	@SuppressWarnings("unchecked")
	public GameModele() {
		plaYear = new MilkDate () ;
		milkCoinString = new SimpleStringProperty();

		researchs = Research.getListes();
		upgrades = Upgrade.getListes();
    	
		buildingNeutral=Building.getNeutralListes();
		buildingScience=Building.getScienceListes();
    	buildingMagic=Building.getMagicListes();

    	workerNeutral=Worker.getNeutralListes();
    	workerScience=Worker.getScienceListes();
    	workerMagic=Worker.getMagicListes();

    	slaveNeutral=Slave.getNeutralListes();
    	slaveScience=Slave.getScienceListes();
    	slaveMagic=Slave.getMagicListes();
    	
    	animalNeutral=Animal.getNeutralListes();
    	animalScience=Animal.getScienceListes();
    	animalMagic=Animal.getMagicListes();
	}

	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return this.name;
	}
	
	public Double getMilkCoin() {
		return this.milkCoin;
	}
	
	
	public MilkDate getPlaYear() {
		return plaYear;
	}

	public void setPlaYear(MilkDate plaYear) {
		this.plaYear = plaYear;
	}

	public ObservableList<Research> getResearch() {
		return researchs;
	}

	public ObservableList<Upgrade> getUpgrade() {
		return upgrades;
	}

	public ObservableList<Building> getBuildingNeutral() {
		return buildingNeutral;
	}

	public ObservableList<Building> getBuildingScience() {
		return buildingScience;
	}

	public ObservableList<Building> getBuildingMagic() {
		return buildingMagic;
	}

	public ObservableList<Worker> getWorkerNeutral() {
		return workerNeutral;
	}

	public ObservableList<Worker> getWorkerScience() {
		return workerScience;
	}

	public ObservableList<Worker> getWorkerMagic() {
		return workerMagic;
	}

	public ObservableList<Slave> getSlaveNeutral() {
		return slaveNeutral;
	}

	public ObservableList<Slave> getSlaveScience() {
		return slaveScience;
	}

	public ObservableList<Slave> getSlaveMagic() {
		return slaveMagic;
	}

	public ObservableList<Animal> getAnimalNeutral() {
		return animalNeutral;
	}

	public ObservableList<Animal> getAnimalScience() {
		return animalScience;
	}

	public ObservableList<Animal> getAnimalMagic() {
		return animalMagic;
	}

	public boolean isThingVisible(Thing thing) {
		boolean visible = true;
		for (NeededIntel neededIntel: thing.getNeed().getNeededIntels()) {
			switch (neededIntel.getKind().getKind()) {
			
			case MilkKind.kind_Upgrade: {
					boolean bought = false;
					for (Upgrade upgrade: this.upgrades) {
						if (upgrade.isBought() && upgrade.getId().intValue()==neededIntel.getId().intValue()) bought=true;
					}
					if(!bought)visible=false;
				}
				break;
			case MilkKind.kind_Research: {
					boolean bought = false;
					for (Research research: this.researchs) {
						if (research.isBought() && research.getId().intValue()==neededIntel.getId().intValue()) bought=true;
					}
					if(!bought)visible=false;
				}
				break;
				
			default:
				break;
			}
		}
		return visible;
	}

	public boolean isThingbuyable(Thing thing) {
		return (thing.getPriceValue()<=milkCoin);
	}

	public void buyThing(Thing thing) {
		if (thing.getPriceValue()<=milkCoin){
			thing.buy();
			milkCoin += -thing.getPriceValue();
			milkCoinString.setValue(milkCoin.toString());
		}
	}

	public void statueClicked() {
		for(Building building: this.buildingNeutral) {
			if (building.getId().intValue()==10) milkCoin += (building.getIncome().getProd()*(building.getAttrib().getQuant()+building.getStart()));
		}
		milkCoinString.setValue(milkCoin.toString());
		
	}

	public SimpleStringProperty getMilkCoinString() {
		return milkCoinString;
	}
}
