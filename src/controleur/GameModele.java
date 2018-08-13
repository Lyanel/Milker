package controleur;

import java.util.ArrayList;
import java.util.Collections;
import java.util.GregorianCalendar;

import org.w3c.dom.Element;

import application.Milker;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.ObservableList;
import modele.Activity;
import modele.Utility;
import modele.baseObject.MilkDate;
import modele.baseObject.MilkInterface;
import modele.baseObject.MilkKind;
import modele.baseObject.MilkPricedObj;
import modele.baseObject.MilkString;
import modele.baseObject.MilkXmlObj;
import modele.carac.Agent;
import modele.carac.NeededIntel;
import modele.carac.NeededThing;
import modele.carac.QuantityListener;
import modele.carac.Sacrifice;
import modele.carac.Save;
import modele.intel.*;
import modele.thing.*;
import modele.toggle.Toggle;
import modele.toggle.ToggleLevel;
import modele.toggle.ToggleOption;
import javafx.util.Duration;

public class GameModele {

	public static void resetList() {
		BuildingList.getInstance().resetListes();
		WorkerList.getInstance().resetListes();
		SlaveList.getInstance().resetListes();
		AnimalList.getInstance().resetListes();
		
		ResearchList.getInstance().resetListes();
		UpgradeList.getInstance().resetListes();
		SynergyList.getInstance().resetListes();
	//	Ascencion.getInstance().resetListes();
		Toggle.setObservableListe();
	}
	
    private Milker milker;
	private MilkDate plaYear;
	private String name;
	private DoubleProperty milkCoin = new SimpleDoubleProperty(0.0), milkAgnel = new SimpleDoubleProperty(0.0),
			toolTogglePrice = new SimpleDoubleProperty(0.0), idolTogglePrice = new SimpleDoubleProperty(0.0), eventTogglePrice = new SimpleDoubleProperty(0.0);
	private float cattleProdBonus = 0, cattleQualBonus = 0, buildProdBonus = 0, buildQualBonus = 0;
	
	public GameModele(Milker milker) {
        this.milker = milker;
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
		
		Agent eco = new Agent();
		eco.setKind(MilkKind.Building);
		Utility.addQuantityListenerToThings(eco, new QuantityListener() {
			@Override
			public void quantityChanged(double oldQuantity, double newQuantity) {
				Activity.setActivesWandB();
				Activity.setActivesCattle();
			}
		});
		Activity.addActiveListenerToThings(eco, new QuantityListener() {
			@Override
			public void quantityChanged(double oldQuantity, double newQuantity) {
				setIncomeBonus();
			}
		});
		
		eco.setKind(MilkKind.Worker);
		Utility.addQuantityListenerToThings(eco, new QuantityListener() {
			@Override
			public void quantityChanged(double oldQuantity, double newQuantity) {
				Activity.setActivesWandB();
			}
		});
		Activity.addActiveListenerToThings(eco, new QuantityListener() {
			@Override
			public void quantityChanged(double oldQuantity, double newQuantity) {
				setIncomeBonus();
			}
		});
		
		eco.setKind(MilkKind.Cattle);
		Utility.addQuantityListenerToThings(eco, new QuantityListener() {
			@Override
			public void quantityChanged(double oldQuantity, double newQuantity) {
				Activity.setActivesCattle();
			}
		});
		Activity.addActiveListenerToThings(eco, new QuantityListener() {
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
		changeDate();
		double tIncome = 0;
		double toolProdBonus = getToggles().get(0).getselectedOption().getLevel().getBonus().getAttrib().getQuant().doubleValue();
		double toolQualBonus = getToggles().get(0).getselectedOption().getLevel().getBonus().getAttrib().getQual().doubleValue();
		
		tIncome+=BuildingList.getInstance().getIncome(buildProdBonus,buildQualBonus); 
		tIncome+=AnimalList.getInstance().getIncome(toolProdBonus,toolQualBonus,cattleProdBonus,cattleQualBonus,buildProdBonus,buildQualBonus); 
		tIncome+=SlaveList.getInstance().getIncome(toolProdBonus,toolQualBonus,cattleProdBonus,cattleQualBonus,buildProdBonus,buildQualBonus); 
		tIncome+=WorkerList.getInstance().getIncome(toolProdBonus,toolQualBonus,cattleProdBonus,cattleQualBonus,buildProdBonus,buildQualBonus);
		return tIncome;
	}

	public void changeDate() {
		this.plaYear.getMilkdate().add(GregorianCalendar.DAY_OF_MONTH, 1);
		int day = this.plaYear.getMilkdate().get(GregorianCalendar.DAY_OF_MONTH);
		if (getToggles().get(2).getselectedOption().getId().intValue()==getToggles().get(2).getStart().intValue() 
				&& day==1) EventList.getInstance().changeByDate(this.plaYear.getMilkdate().get(GregorianCalendar.MONTH));
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

	public DoubleProperty getAgnelCoin() {
		// TODO Auto-generated method stub
		return this.milkAgnel;
	}
	
	protected void setIncomeBonus() {
		cattleProdBonus = 0;
		cattleQualBonus = 0; 
		buildProdBonus = 0;
		buildQualBonus = 0;
		for (Building building:BuildingList.getInstance().getFullListe()){
			int active = building.getQuantity().getActives();
			if(building.getBonus().getKind().intValue()==MilkKind.Living_Being){
				cattleProdBonus+=(building.getBonus().getAttrib().getQuant().doubleValue()*active);
				cattleQualBonus+=(building.getBonus().getAttrib().getQual().doubleValue()*active);
			}else{
				buildProdBonus+=(building.getBonus().getAttrib().getQuant().doubleValue()*active);
				buildQualBonus+=(building.getBonus().getAttrib().getQual().doubleValue()*active);
			}
		}
		cattleProdBonus = 1+cattleProdBonus/100;
		cattleQualBonus = 1+cattleQualBonus/100; 
		buildProdBonus = 1+buildProdBonus/100;
		buildQualBonus = 1+buildQualBonus/100;
	}

	protected void setToolTogglePrice() {
		int quant = Activity.getActiveThingsQuantity(getToggles().get(0).getAgent());
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

	public ObservableList<Research> getResearch() {return ResearchList.getInstance().getResearchListe();}
	public ObservableList<Upgrade> getUpgrade() {return UpgradeList.getInstance().getUpgradeListe();}
	public ObservableList<Synergy> getSynergy() {return SynergyList.getInstance().getSynergyListe();}
	public ObservableList<Ascension> getAscension() {return null;}
	//public ObservableList<Event> getEvent() {return null;}

	public ObservableList<Toggle> getToggles() {return Toggle.getToggleListe();}

	public ObservableList<Building> getBuildingNeutral() {return BuildingList.getInstance().getNeutralListe();}
	public ObservableList<Building> getBuildingScience() {return BuildingList.getInstance().getScienceListe();}
	public ObservableList<Building> getBuildingMagic() {return BuildingList.getInstance().getMagicListe();}

	public ObservableList<Worker> getWorkerNeutral() {return WorkerList.getInstance().getNeutralListe();}
	public ObservableList<Worker> getWorkerScience() {return WorkerList.getInstance().getScienceListe();}
	public ObservableList<Worker> getWorkerMagic() {return WorkerList.getInstance().getMagicListe();}

	public ObservableList<Slave> getSlaveNeutral() {return SlaveList.getInstance().getNeutralListe();}
	public ObservableList<Slave> getSlaveScience() {return SlaveList.getInstance().getScienceListe();}
	public ObservableList<Slave> getSlaveMagic() {return SlaveList.getInstance().getMagicListe();}

	public ObservableList<Animal> getAnimalNeutral() {return AnimalList.getInstance().getNeutralListe();}
	public ObservableList<Animal> getAnimalScience() {return AnimalList.getInstance().getScienceListe();}
	public ObservableList<Animal> getAnimalMagic() {return AnimalList.getInstance().getMagicListe();}
	
	public void statueClicked() {
		for(Building building: getBuildingNeutral()) {
			if (building.getId().intValue()==10) 
				milkCoin.setValue( milkCoin.doubleValue() + building.getIncome().getProd() * building.getQuantity().getQuant() );
		}
		
	}

	public boolean isIntelVisible(Intel value) {
		boolean visible = true;
		if(!value.bought()){
			if(!isMilkObjVisible(value))visible = false;
			if(visible && value instanceof Research){
				Research tempResearch = (Research)value;
				if(tempResearch.getCheck().getNeededThings().size()>0)visible = areMilkObjOwned(tempResearch);
			}
		}
		return visible;
	}
	
	public boolean isThingVisible(Thing value) {
		boolean visible = true;
		if(value.getQuantity().getQuant().intValue()<1 && !isMilkObjVisible(value)) visible = false;
		return visible;
	}
	
	public boolean isOptionVisible(ToggleOption value) {
		boolean visible = true;
		if(!isMilkObjVisible(value)) visible = false;
		return visible;
	}

	private boolean isMilkObjVisible(MilkXmlObj value) {
		boolean visible = true;
		if(value.getNeed().getNeededIntels().size()>0) visible = isMilkObjResearched(value);
		if(visible && value.getNeed().getNeededThings().size()>0) visible = isMilkObjOwned(value);
		return visible;
	}

	public int getToolLevel(ToggleOption thing) {
		ArrayList<ToggleLevel> lvls = thing.getLevels();
		int retlvl=1;
		for (ToggleLevel lvl:lvls){
			for (NeededIntel need: lvl.getNeed().getNeededIntels()) {
				if (Utility.getIntelFromAgent(need).bought() && lvl.getLvl() > retlvl) retlvl = lvl.getLvl();
			}
		}
		return retlvl;
	}
	
	public boolean isMilkObjResearched(MilkXmlObj value) {
		boolean visible = true;
		for (NeededIntel need: value.getNeed().getNeededIntels()) {
			if(MilkKind.checkThingKind(MilkKind.Event, need.getKind())){
				Event test = EventList.getInstance().getActiveEvent();
				if(test!=null && need.getId().intValue()!=test.getId().intValue())visible=false;
			} else if(!Utility.getIntelFromAgent(need).bought())visible=false;
		}
		return visible;
	}
	
	public boolean isMilkObjOwned(MilkXmlObj value) {
		boolean visible = true;
		for (NeededThing need: value.getNeed().getNeededThings()) {
			visible = Activity.checkActiveThing(need, Utility.getThingsListsFromKind(need.getKind()));
		}
		return visible;
	}
	
	public boolean areMilkObjOwned(Research value) {
		int count = 0;
		for (NeededThing need: value.getCheck().getNeededThings()) {
			if(Activity.checkActiveThing(need, Utility.getThingsListsFromKind(need.getKind())))count+=1;
			if(count >= value.getCheck().getMod().intValue())return true;
		}
		return false;
	}


	public boolean isMilkPricedObjbuyable(MilkPricedObj value) {
		boolean result = false;
		if(value.getPriceValue()<=milkCoin.doubleValue()){
			if(value instanceof Thing)result = true;
			else if (!value.bought()) result = true;
		}
		return result;
	}

	public void buyMilkPricedObj(MilkPricedObj value) {
		if(isMilkPricedObjbuyable(value)){
			boolean sacrified = true;
			if(Utility.thatNeedaSacrifice(value)){
				ArrayList<Thing> selecteds = GetSacrificesList(value);
				//for now all sacrifice will be done at random.
				if(selecteds.size()>0){
					Collections.shuffle(selecteds);
					Sacrifice need = null;
					if(value instanceof Research) need = ((Research)value).getSacrifice();
					else need = ((Slave)value).getSacrifice();
					selecteds.get(0).getQuantity().incrementeQuant(-need.getQuantity().getQuant());
					if(value.isVoluntarySlave()){
						sacrified = false;
						SlaveWorker test = (SlaveWorker) Utility.getThingFromList(selecteds.get(0).getId().intValue(),SlaveListW.getInstance().getSWFullListe());
						test.getQuantity().incrementeQuant(1);
						milker.setStatutMessage(selecteds.get(0).getInfo().getName()+" "+MilkInterface.getMilkStringsFromId(1105).asText());
					}
				} else {
					sacrified = false;
					milker.setStatutMessage(value.getInfo().getName()+" "+MilkInterface.getMilkStringsFromId(1101).asText()
							+" "+MilkInterface.getMilkStringsFromId(1102).asText()+" "+MilkInterface.getMilkStringsFromId(1103).asText());
				}
			}
			if(sacrified){
				value.buy();
				milkCoin.setValue(milkCoin.doubleValue() - value.getPriceValue());
				milker.setStatutMessage(value.getInfo().getName()+" "+MilkInterface.getMilkStringsFromId(1102).asText());
			}
		} else milker.setStatutMessage(value.getInfo().getName()+" "+MilkInterface.getMilkStringsFromId(1104).asText());
	}

	public ArrayList<Thing> GetSacrificesList(MilkPricedObj value) {
		Sacrifice need = null;
		ArrayList<? extends Thing> candidates = null;
		ArrayList<Thing> selected = new ArrayList<Thing>();
		if(value instanceof Research){
			need = ((Research)value).getSacrifice();
			candidates = Utility.getThingsListsFromAgent(need);
			for(Thing candidate : candidates){
				if(need.getQuantity().getQuant().intValue()<candidate.getQuantity().getQuant().intValue())selected.add(candidate);
			}
		} else {
			need = ((Slave)value).getSacrifice();
			candidates = Utility.getThingsListsFromAgent(need);
			for(Thing candidate : candidates){
				if(MilkKind.checkThingKind(MilkKind.Worker, candidate.getKind())){
					if(need.getQuantity().getQuant().intValue()<
							(candidate.getQuantity().getQuant().intValue()-candidate.getQuantity().getActives().intValue()-candidate.getQuantity().getSemiActives().intValue()))selected.add(candidate);
				} else if(need.getQuantity().getQuant().intValue()<candidate.getQuantity().getQuant().intValue())selected.add(candidate);
			}
		}
		return selected;
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

	public String getSave() {
		Save save = new Save(milkCoin.getValue(),milkAgnel.getValue());
		save.setName(new MilkString(name));
		save.setPlaYear(plaYear);
		save.setSave();
		return save.toXmlStat();
	}
	
	public void loadSave(Element racine) {
		Save save = new Save(racine);
		milkCoin.setValue(save.getMilkCoin());
		milkAgnel.setValue(save.getMilkAgnel());
		//name=save.getName().asText();
		//plaYear.setMilkdate(save.getPlaYear().getMilkdate());
		
		for (NeededThing neededThing : save.getNeededThings()){
			Thing thing = Utility.getThingsListsFromAgent(neededThing).get(0);
			thing.getQuantity().setQuant(neededThing.getQuantity().getQuant());
		}
		
		for (NeededIntel neededIntel : save.getNeededIntels()){
			Intel intel = Utility.getIntelFromAgent(neededIntel);
			intel.buy();
			milker.unlockView(intel);
			if(intel.getClass().equals(Upgrade.class) ) ((Upgrade)intel).applyUpgrade();
		}
	}
}
