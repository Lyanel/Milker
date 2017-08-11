package controleur;


import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
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
import javafx.util.Duration;

public class GameModele {
	
	private MilkDate plaYear;
	private String name;
	private DoubleProperty milkCoin = new SimpleDoubleProperty(0.0);
	
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
		
		tIncome+=Building.getIncomeFromList(buildingNeutral,buildProdBonus,buildQualBonus); 
		tIncome+=Building.getIncomeFromList(buildingScience,buildProdBonus,buildQualBonus); 
		tIncome+=Building.getIncomeFromList(buildingMagic,buildProdBonus,buildQualBonus); 
		
		tIncome+=Animal.getIncomeFromList(animalNeutral,toolProdBonus,toolQualBonus,cattleProdBonus,cattleQualBonus,buildProdBonus,buildQualBonus); 
		tIncome+=Animal.getIncomeFromList(animalScience,toolProdBonus,toolQualBonus,cattleProdBonus,cattleQualBonus,buildProdBonus,buildQualBonus); 
		tIncome+=Animal.getIncomeFromList(animalMagic,toolProdBonus,toolQualBonus,cattleProdBonus,cattleQualBonus,buildProdBonus,buildQualBonus); 
		
		tIncome+=Slave.getIncomeFromList(slaveNeutral,toolProdBonus,toolQualBonus,cattleProdBonus,cattleQualBonus,buildProdBonus,buildQualBonus); 
		tIncome+=Slave.getIncomeFromList(slaveScience,toolProdBonus,toolQualBonus,cattleProdBonus,cattleQualBonus,buildProdBonus,buildQualBonus); 
		tIncome+=Slave.getIncomeFromList(slaveMagic,toolProdBonus,toolQualBonus,cattleProdBonus,cattleQualBonus,buildProdBonus,buildQualBonus); 
		
		tIncome+=Worker.getIncomeFromList(workerNeutral,toolProdBonus,toolQualBonus,cattleProdBonus,cattleQualBonus,buildProdBonus,buildQualBonus); 
		tIncome+=Worker.getIncomeFromList(workerScience,toolProdBonus,toolQualBonus,cattleProdBonus,cattleQualBonus,buildProdBonus,buildQualBonus); 
		tIncome+=Worker.getIncomeFromList(workerMagic,toolProdBonus,toolQualBonus,cattleProdBonus,cattleQualBonus,buildProdBonus,buildQualBonus); 
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
		return (thing.getPriceValue()<=milkCoin.doubleValue());
	}

	public void buyThing(Thing thing) {
		if (thing.getPriceValue()<=milkCoin.doubleValue()){
			thing.buy();
			milkCoin.setValue(milkCoin.doubleValue() - thing.getPriceValue());
		}
	}

	public void statueClicked() {
		for(Building building: this.buildingNeutral) {
			if (building.getId().intValue()==10) milkCoin.setValue(milkCoin.doubleValue() + (building.getIncome().getProd()*(building.getAttrib().getQuant()+building.getStart())));
		}
		
	}
}
