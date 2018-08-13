package modele;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javafx.collections.ObservableList;
import modele.baseObject.MilkKind;
import modele.carac.Agent;
import modele.carac.NeededThing;
import modele.carac.QuantityListener;
import modele.carac.MilkTree;
import modele.thing.Building;
import modele.thing.Thing;
import modele.thing.Worker;

public class Activity{

	/*
	 * Add a Active listener to things related to the agent.
	 * */
	public static void addActiveListenerToThings(Agent value, QuantityListener listener) {
		addActiveListenerToThingsLists(value, listener, Utility.getThingsListsFromKind(value.getKind()));
	}

	/*
	 * Add a Active listener to things related to the agent and contained in a array of ObservableList.
	 * */
	@SafeVarargs
	public static void addActiveListenerToThingsLists(Agent value, QuantityListener listener, ObservableList<? extends Thing>... testLists) {
        for (ObservableList<? extends Thing> tests : testLists) {
			for (Thing test : tests) {
				if(Utility.multiCheck(value, test)) test.getQuantity().addActiveListener(listener);
			}
		}
	}

	/*
	 * Get the quantity of active things related to the agent.
	 * */
	public static int getActiveThingsQuantity(Agent value) {
		return getActiveThingsQuantityFromLists(value, Utility.getThingsListsFromKind(value.getKind()));
	}
	
	/*
	 * Get the quantity of active things related to the agent and contained in a array of ObservableList.
	 * */
	@SafeVarargs
	public static int getActiveThingsQuantityFromLists(Agent value, ObservableList<? extends Thing>... testLists) {
		int result = 0;
        for (ObservableList<? extends Thing> tests : testLists) {
			for (Thing test : tests) {
				if(Utility.multiCheck(value, test))  result += test.getQuantity().getActives().intValue();
			}
		}
        return result;
	}

	/*
	 * Check if the things related to the NeededThing is bought with enough active.
	 * */
	@SafeVarargs
	public static boolean checkActiveThing(NeededThing wanted, ObservableList<? extends Thing>... testLists) {
		int unit = 0;
        for (ObservableList<? extends Thing> tests : testLists) {
			for (Thing test : tests) {
				if(Utility.multiCheck(wanted, test)) unit += test.getQuantity().getQuant().intValue();
			}
		}
		return ( unit > wanted.getQuantity().getQuant().intValue() ) ? true : false;
	}

	
	/**
	 * Makes active the building and workers according to their respective links.
	 * */
	public static void setActivesWandB() {
		ArrayList<Thing> inactiveWorker = new ArrayList<Thing>();
		inactiveWorker.addAll(setActiveWorker(MilkTree.Tree_Neutral));
		inactiveWorker.addAll(setActiveWorker(MilkTree.Tree_Science));
		inactiveWorker.addAll(setActiveWorker(MilkTree.Tree_Magic));
		Collections.shuffle(inactiveWorker);
		setActiveWorker(inactiveWorker);
		
		setActiveBuilding(MilkTree.Tree_Neutral);
		setActiveBuilding(MilkTree.Tree_Science);
		setActiveBuilding(MilkTree.Tree_Magic);
	}

	/**
	 * Makes active the house building and workers of the specified tree according to their respective links.
	 * Return a list of worker who are still inactive due to not have housing.
	 * */
	protected static ArrayList<? extends Thing> setActiveWorker(int tree) {
		Agent agent = new Agent();
		agent.getTree().setTree(tree);
		agent.setKind(MilkKind.Building);
		ArrayList<? extends Thing> houses = Utility.getThingsListsFromAgent(agent);
		agent.setKind(MilkKind.Worker);
		ArrayList<? extends Thing> workers = Utility.getThingsListsFromAgent(agent);
		
		int availablePop = 0;
		
		for (Thing houseThing : houses) {
			Building house = (Building) houseThing;
			if(house.getPopulation().getQuantity().getQuant().intValue()>0 && house.getPopulation().getKind().intValue() == MilkKind.Worker){
				int houseQuant = house.getQuantity().getQuant().intValue();
				int agentNeed = house.getAgent().getQuantity().getQuant().intValue();
				Worker houseWorker = null;
				for (Thing test : workers) {
					if(house.getAgent().getId()!=null && house.getAgent().getId().intValue() == test.getId().intValue() ) {
						houseWorker = (Worker) test;
						break;
					}
				}
				workers.remove(houseWorker);
				int houseWorkerQuant = houseWorker.getQuantity().getQuant().intValue();
				int houseWorkerActive =  ( houseQuant * agentNeed <= houseWorkerQuant ) ? houseQuant * agentNeed : (int)Math.floor( houseWorkerQuant / agentNeed ) * agentNeed  ;
				houseWorker.getQuantity().setActive(houseWorkerActive);
				int houseActive = houseWorkerActive / agentNeed ;
				house.getQuantity().setActive(houseActive);
				int houseMaxPop = house.getPopulation().getQuantity().getQuant() * houseActive;
				availablePop += ( houseMaxPop - houseWorkerActive );
				
			}
		}

		int[][] tempPop = new int[2][workers.size()];
		setHousing(availablePop, tempPop, workers);
		
		for (int i=workers.size()-1;i>-1;i--){
			workers.get(i).getQuantity().setActive(tempPop[0][i]);
			if(tempPop[0][i]==tempPop[1][i])workers.remove(i);
		}
		return workers ;
	}
	
	/**
	 * Makes active the remaining workers via ascension upgrade.
	 * Return nothing, this was their last chance.
	 * */
	protected static void setActiveWorker(ArrayList<Thing> inactiveWorker) {
		Agent agent = new Agent();
		agent.getTree().setTree(MilkTree.Tree_Special);
		agent.setKind(MilkKind.Building);
		ArrayList<? extends Thing> houses = Utility.getThingsListsFromAgent(agent);

		int availablePop=0;
		
		for (Thing houseThing : houses) {
			Building house = (Building) houseThing;
			if(house.bought()) availablePop += house.getPopulation().getQuantity().getQuant().intValue();
		}
		if(availablePop>0){
			int[][] tempPop = new int[2][inactiveWorker.size()];
			setHousing(availablePop, tempPop, inactiveWorker);
			for (int i=inactiveWorker.size()-1;i>-1;i--){
				inactiveWorker.get(i).getQuantity().setActive(tempPop[0][i]);
			}
		}
	}


	/**
	 * Makes other building of the specified tree active according to their respective worker.
	 * Return, nothing, no second chance for building :(.
	 * */
	private static void setActiveBuilding(int tree) {
		Agent agent = new Agent();
		agent.getTree().setTree(tree);
		agent.setKind(MilkKind.Building);
		ArrayList<? extends Thing> buildings = Utility.getThingsListsFromAgent(agent);
		for (Thing buildThing : buildings) {
			Building building = (Building) buildThing;
			int buildingQuant = building.getQuantity().getQuant().intValue();
			if(buildingQuant>0){
				ArrayList<? extends Thing> agents = Utility.getThingsListsFromAgent(building.getAgent());
				if(agents.size()>0){
					int agentNeed = building.getAgent().getQuantity().getQuant().intValue();
					Worker worker = (Worker) agents.get(0);
					int activeWorker = worker.getQuantity().getActives().intValue();
					int buildingActive =  ( buildingQuant * agentNeed <= activeWorker ) ? buildingQuant : (int)Math.floor( activeWorker / agentNeed ) ;
					building.getQuantity().setActive(buildingActive);
				} else building.getQuantity().setActive(buildingQuant);
			}
		}
	}
	
	/**
	 * Makes active the Cattle according to their respective building.
	 * */
	public static void setActivesCattle() {
		ArrayList<Thing> inactiveHSlaveN = new ArrayList<Thing>();
		ArrayList<Thing> inactiveHSlaveS = new ArrayList<Thing>();
		ArrayList<Thing> inactiveHSlaveM = new ArrayList<Thing>();
		
		ArrayList<Thing> inactiveASlaveN = new ArrayList<Thing>();
		ArrayList<Thing> inactiveASlaveS = new ArrayList<Thing>();
		ArrayList<Thing> inactiveASlaveM = new ArrayList<Thing>();

		ArrayList<Thing> inactiveAnimalN = new ArrayList<Thing>();
		ArrayList<Thing> inactiveAnimalS = new ArrayList<Thing>();
		ArrayList<Thing> inactiveAnimalM = new ArrayList<Thing>();

		int remainingHNPlace = setActiveHSlave(MilkTree.Tree_Neutral, inactiveHSlaveN, inactiveASlaveN);
		setActiveHSlave(MilkTree.Tree_Science, inactiveHSlaveS, inactiveASlaveS);
		setActiveHSlave(MilkTree.Tree_Magic, inactiveHSlaveM, inactiveASlaveM);

		int remainingANPlace = setActiveAnimal(MilkTree.Tree_Neutral, inactiveAnimalN);
		int remainingASPlace = setActiveAnimal(MilkTree.Tree_Science, inactiveAnimalS);
		int remainingAMPlace = setActiveAnimal(MilkTree.Tree_Magic, inactiveAnimalM);
		
		if(remainingHNPlace>0) putRemainingSandMWithNeutral(remainingHNPlace, inactiveHSlaveS, inactiveHSlaveM);
		if(remainingASPlace>0) putRemainingHybrideWithAnimal(remainingASPlace, inactiveASlaveS);
		if(remainingAMPlace>0) putRemainingHybrideWithAnimal(remainingAMPlace, inactiveASlaveM);
		if(remainingANPlace>0) putRemainingHybrideWithAnimal(remainingANPlace, inactiveASlaveN);
		if(remainingANPlace>0) putRemainingSandMWithNeutral(remainingANPlace, inactiveAnimalS, inactiveAnimalM);
	}

	/*
	 * return available pop.
	 * */
	private static int setActiveHSlave(int tree, ArrayList<Thing> inactiveHSlave, ArrayList<Thing> inactiveASlave) {
		Agent agent = new Agent();
		agent.getTree().setTree(tree);
		agent.setKind(MilkKind.Building);
		ArrayList<? extends Thing> houses = Utility.getThingsListsFromAgent(agent);
		agent.setKind(MilkKind.Slaves);
		ArrayList<? extends Thing> cattles = Utility.getThingsListsFromAgent(agent);
		
		int availablePop=0;
		for (Thing houseThing : houses) {
			Building house = (Building) houseThing;
			if(house.getPopulation().getQuantity().getQuant().intValue()>0 && MilkKind.checkThingKind(MilkKind.Slaves, house.getPopulation()) ){
				int houseActive = house.getQuantity().getActives().intValue();
				availablePop += ( house.getPopulation().getQuantity().getQuant() * houseActive );
			}
		}
		
		int[][] tempPop = new int[2][cattles.size()];
		setHousing(availablePop, tempPop, cattles);
		
		for (int i=cattles.size()-1;i>-1;i--){
			cattles.get(i).getQuantity().setActive(tempPop[0][i]);
			if(tempPop[0][i]!=tempPop[1][i]){
				switch(cattles.get(i).getKind().getKind()){
					case MilkKind.Slave_Animal			: inactiveASlave.add(cattles.get(i)); break;
					case MilkKind.Slave_Humanoid			: inactiveHSlave.add(cattles.get(i)); break;
					case MilkKind.Slave_Worker			: inactiveHSlave.add(cattles.get(i)); break;
				}
			}
		}
		return availablePop;
	}


	/*
	 * return available pop.
	 * */
	private static int setActiveAnimal(int tree, ArrayList<Thing> inactiveAnimal) {
		Agent agent = new Agent();
		agent.getTree().setTree(tree);
		agent.setKind(MilkKind.Building);
		ArrayList<? extends Thing> houses = Utility.getThingsListsFromAgent(agent);
		agent.setKind(MilkKind.Animal);
		ArrayList<? extends Thing> cattles = Utility.getThingsListsFromAgent(agent);

		int availablePop=0;
		for (Thing houseThing : houses) {
			Building house = (Building) houseThing;
			if(house.getPopulation().getQuantity().getQuant().intValue()>0 && MilkKind.checkThingKind(MilkKind.Animal, house.getPopulation()) ){
				int houseActive = house.getQuantity().getActives().intValue();
				availablePop += ( house.getPopulation().getQuantity().getQuant() * houseActive );
			}
		}
		
		int[][] tempPop = new int[2][cattles.size()];
		setHousing(availablePop, tempPop, cattles);
		
		for (int i=cattles.size()-1;i>-1;i--){
			cattles.get(i).getQuantity().setActive(tempPop[0][i]);
			if(tempPop[0][i]!=tempPop[1][i]) inactiveAnimal.add(cattles.get(i));
		}
		return availablePop;
	}


	private static void putRemainingSandMWithNeutral(int remainingPop, ArrayList<Thing> inactiveS, ArrayList<Thing> inactiveM) {
		ArrayList<Thing> inactive = new ArrayList<Thing>();
		inactive.addAll(inactiveS);
		inactive.addAll(inactiveM);
		Collections.shuffle(inactive);

		int[][] tempPop = new int[2][inactive.size()];
		setSemiHousing(remainingPop, tempPop, inactive);
		
		for (int i=inactive.size()-1;i>-1;i--){
			inactive.get(i).getQuantity().setSemiActive(tempPop[0][i]);
		}
	}

	private static void putRemainingHybrideWithAnimal(int remainingPop, ArrayList<Thing> inactiveASlave) {
		Collections.shuffle(inactiveASlave);

		int[][] tempPop = new int[2][inactiveASlave.size()];
		setSemiHousing(remainingPop, tempPop, inactiveASlave);
		
		for (int i=inactiveASlave.size()-1;i>-1;i--){
			inactiveASlave.get(i).getQuantity().setSemiActive(tempPop[0][i]);
		}
	}
	
	private static void setHousing(int availablePop, int[][] tempPop, List<? extends Thing> inactiveBeing) {
		int beingtype = inactiveBeing.size();
		for (int i=0;i<beingtype;i++){
			tempPop[0][i] = 0;
			tempPop[1][i] = inactiveBeing.get(i).getQuantity().getQuant().intValue();
		}
		boolean allHoused = false;
		while(availablePop>0 && !allHoused){
			for (int i=0;i<beingtype;i++){
				if(tempPop[0][i]<tempPop[1][i]){
					availablePop -= 1;
					tempPop[0][i] += 1;
				}
			}
			allHoused = true;
			for (int i=0;i<beingtype;i++){
				if(tempPop[0][i]<tempPop[1][i]){
					allHoused = false;
					break;
				}
			}
		}
	}
	
	private static void setSemiHousing(int availablePop, int[][] tempPop, List<? extends Thing> inactiveBeing) {
		int beingtype = inactiveBeing.size();
		for (int i=0;i<beingtype;i++){
			tempPop[0][i] = 0;
			tempPop[1][i] = (inactiveBeing.get(i).getQuantity().getQuant().intValue() - inactiveBeing.get(i).getQuantity().getActives().intValue());
		}
		boolean allHoused = false;
		while(availablePop>0 && !allHoused){
			for (int i=0;i<beingtype;i++){
				if(tempPop[0][i]<tempPop[1][i]){
					availablePop -= 1;
					tempPop[0][i] += 1;
				}
			}
			allHoused = true;
			for (int i=0;i<beingtype;i++){
				if(tempPop[0][i]<tempPop[1][i]){
					allHoused = false;
					break;
				}
			}
		}
	}

}