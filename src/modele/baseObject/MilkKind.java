package modele.baseObject;

import org.w3c.dom.Element;

import modele.ParseMilkFile;

/**
 * Kind :  Refer to the Kind of the thing you have/need :<br/>
 * object : 1 (buildings), 2 (workers), 3 (animals), 4 (slaves A (animal)), 5 (slaves H (human)), 6 (slaves W (Worker)) <br/>
 * intel : 1 (research), 2 (upgrade), 3 (synergy), 4 (ascension), 5 (event).
 * 
 * @author Slade Chaos
 */
public class MilkKind extends MilkVar {
	
	public static final String xmlKind = "kind";

	public static final int None = 0, All = 49,

	// Things
	
	Building = 1,
	Worker = 2,
	Slave_Worker = 3,
	Slave_Humanoid = 4,
	Slave_Animal = 5,
	Animal = 6,

	Human = 7,				// workers + slaves worker
	MasterAndCaptive = 8,	// workers + slaves humanoids
	MasterAndCreature = 9,	// workers + slaves animals
	Original_Farm = 10,		// workers + animals
	Natural_Slave = 11,		// slaves worker + slaves humanoids
	Earthling_Slave = 12,	// slaves worker + slaves animals
	NatEarthCattle = 13,	// slaves worker + animals
	Forced_Slave = 14,		// slaves humanoids + slaves animals
	Familiar = 15,			// slaves humanoids + animals
	Pet = 16,				// slaves animals + animals

	Semi_Human = 17,		// workers + slaves worker + slaves humanoids
	Earthling_People = 18,	// workers + slaves worker + slaves animals
	Natural_Earthling = 19,	// workers + slaves worker + animals
	MasterAndSlave = 20,	// workers + slaves humanoids + slaves animals
	MasterAndFamiliar = 21,	// workers + slaves humanoids + animals
	MasterAndPet = 22,		// workers + slaves animals + animals
	Slaves = 23,			// slaves worker + slaves humanoids + slaves animals
	Natural_Cattle = 24,	// slaves worker + slaves humanoids + animals
	Earthling_Cattle = 25,	// slaves worker + slaves animals + animals
	Forced_Cattle = 26,		// slaves humanoids + slaves animals + animals			
	
	People = 27,			// workers + slaves worker + slaves humanoids + slaves animals
	Natural_Being = 28,		// workers + slaves worker + slaves humanoids + animals
	Earthling_Being = 29,	// workers + slaves worker + slaves animals + animals
	MasterAndCattle = 30,	// workers + slaves humanoids + slaves animals + animals
	Cattle = 31,			// slaves worker + slaves humanoids + slaves animals + animals
	
	Living_Being = 32,		// workers + slaves worker + slaves humanoids + slaves animals + animals	
	Economic_Agent = 33,	// Building + workers
	

	// Intels
	
	Research = 1,
	Upgrade = 2,
	Synergy =3,
	Ascension = 4,
	Event = 5;
	
	
	/**
	 * Test if kind match : </br>
	 * returns true if the value tested is equal, is included in or includes the wanted value, ex :</br>
	 * Worker : if(wanted==All			|| wanted==Living_Being		|| wanted==Worker			|| wanted==Economic_Agent	 </br>
				|| wanted==Human		|| wanted==MasterAndCaptive	|| wanted==MasterAndCreature|| wanted==Original_Farm	 </br>
				|| wanted==Semi_Human	|| wanted==Earthling_People	|| wanted==Natural_Earthling|| wanted==MasterAndSlave	|| wanted==MasterAndFamiliar|| wanted==MasterAndPet </br>
				|| wanted==People		|| wanted==Natural_Being	|| wanted==Earthling_Being	|| wanted==MasterAndCattle	) </br>
	 */
	public static boolean checkThingKind(int wanted, MilkKind tested) {
		boolean result = false;
		switch (tested.kind.intValue()) {
			case None : break;
			case Building			: if(wanted==All|| wanted==Building			|| wanted==Economic_Agent	) result = true ; break;
			
			// Living - Solo
			case Worker 			: if(wanted==All|| wanted==Living_Being		|| wanted==Worker			|| wanted==Economic_Agent		
							|| wanted==Human		|| wanted==MasterAndCaptive	|| wanted==MasterAndCreature|| wanted==Original_Farm	
							|| wanted==Semi_Human	|| wanted==Earthling_People	|| wanted==Natural_Earthling|| wanted==MasterAndSlave	|| wanted==MasterAndFamiliar|| wanted==MasterAndPet
							|| wanted==People		|| wanted==Natural_Being	|| wanted==Earthling_Being	|| wanted==MasterAndCattle	) result = true ; break;
			
			case Slave_Worker 		: if(wanted==All|| wanted==Living_Being		|| wanted==Slave_Worker			
							|| wanted==Human		|| wanted==Natural_Slave	|| wanted==Earthling_Slave	|| wanted==NatEarthCattle	
							|| wanted==Semi_Human	|| wanted==Earthling_People	|| wanted==Natural_Earthling|| wanted==Slaves			|| wanted==Natural_Cattle	|| wanted==Earthling_Cattle
							|| wanted==People		|| wanted==Natural_Being	|| wanted==Earthling_Being	|| wanted==Cattle		) result = true ; break;

			case Slave_Humanoid 		: if(wanted==All|| wanted==Living_Being		|| wanted==Slave_Humanoid			
						|| wanted==MasterAndCaptive	|| wanted==Natural_Slave	|| wanted==Forced_Slave		|| wanted==Familiar	
						|| wanted==Semi_Human		|| wanted==MasterAndSlave	|| wanted==MasterAndFamiliar|| wanted==Slaves			|| wanted==Natural_Cattle	|| wanted==Forced_Cattle
						|| wanted==People			|| wanted==Natural_Being	|| wanted==MasterAndCattle	|| wanted==Cattle		) result = true ; break;
			
			case Slave_Animal 		: if(wanted==All|| wanted==Living_Being		|| wanted==Slave_Animal			
						|| wanted==MasterAndCreature|| wanted==Earthling_Slave	|| wanted==Forced_Slave		|| wanted==Pet	
						|| wanted==Earthling_People	|| wanted==MasterAndSlave	|| wanted==MasterAndPet		|| wanted==Slaves			|| wanted==Earthling_Cattle	|| wanted==Forced_Cattle
						|| wanted==People			|| wanted==Earthling_Being	|| wanted==MasterAndCattle	|| wanted==Cattle		) result = true ; break;
			
			case Animal		 		: if(wanted==All|| wanted==Living_Being		|| wanted==Animal			
						|| wanted==Original_Farm	|| wanted==NatEarthCattle	|| wanted==Familiar			|| wanted==Pet	
						|| wanted==Natural_Earthling|| wanted==MasterAndFamiliar|| wanted==MasterAndPet		|| wanted==Natural_Cattle	|| wanted==Earthling_Cattle	|| wanted==Forced_Cattle
						|| wanted==Natural_Being	|| wanted==Earthling_Being	|| wanted==MasterAndCattle	|| wanted==Cattle		) result = true ; break;
						
			// Living - Duo
			case Human 				: if(wanted==All|| wanted==Living_Being		|| wanted==Worker			|| wanted==Slave_Worker		|| wanted==Human	
						|| wanted==Semi_Human		|| wanted==Earthling_People	|| wanted==Natural_Earthling|| wanted==People			|| wanted==Natural_Being	|| wanted==Earthling_Being	) result = true ; break;

			case MasterAndCaptive	: if(wanted==All|| wanted==Living_Being		|| wanted==Worker			|| wanted==Slave_Humanoid		|| wanted==MasterAndCaptive	
						|| wanted==Semi_Human		|| wanted==MasterAndSlave	|| wanted==MasterAndFamiliar|| wanted==People			|| wanted==Natural_Being	|| wanted==MasterAndCattle	) result = true ; break;

			case MasterAndCreature	: if(wanted==All|| wanted==Living_Being		|| wanted==Worker			|| wanted==Slave_Animal		|| wanted==MasterAndCreature	
						|| wanted==Earthling_People	|| wanted==MasterAndSlave	|| wanted==MasterAndPet		|| wanted==People			|| wanted==Earthling_Being	|| wanted==MasterAndCattle	) result = true ; break;

			case Original_Farm		: if(wanted==All|| wanted==Living_Being		|| wanted==Worker			|| wanted==Animal			|| wanted==Original_Farm	
						|| wanted==Natural_Earthling|| wanted==MasterAndFamiliar|| wanted==MasterAndPet		|| wanted==Natural_Being	|| wanted==Earthling_Being	|| wanted==MasterAndCattle	) result = true ; break;

			case Natural_Slave		: if(wanted==All|| wanted==Living_Being		|| wanted==Slave_Worker		|| wanted==Slave_Humanoid		|| wanted==Natural_Slave	
						|| wanted==Semi_Human		|| wanted==Slaves			|| wanted==Natural_Cattle	|| wanted==People			|| wanted==Natural_Being	|| wanted==Cattle			) result = true ; break;

			case NatEarthCattle		: if(wanted==All|| wanted==Living_Being		|| wanted==Slave_Worker		|| wanted==Animal			|| wanted==NatEarthCattle	
						|| wanted==Natural_Earthling|| wanted==Natural_Cattle	|| wanted==Earthling_Cattle	|| wanted==Natural_Being	|| wanted==Earthling_Being	|| wanted==Cattle			) result = true ; break;

			case Forced_Slave		: if(wanted==All|| wanted==Living_Being		|| wanted==Slave_Humanoid		|| wanted==Slave_Animal		|| wanted==Forced_Slave	
						|| wanted==MasterAndSlave	|| wanted==Slaves			|| wanted==Forced_Cattle	|| wanted==People			|| wanted==MasterAndCattle	|| wanted==Cattle			) result = true ; break;

			case Familiar			: if(wanted==All|| wanted==Living_Being		|| wanted==Slave_Humanoid		|| wanted==Animal			|| wanted==Familiar	
						|| wanted==MasterAndFamiliar|| wanted==Natural_Cattle	|| wanted==Forced_Cattle	|| wanted==Natural_Being	|| wanted==MasterAndCattle	|| wanted==Cattle			) result = true ; break;

			case Pet				: if(wanted==All|| wanted==Living_Being		|| wanted==Slave_Animal		|| wanted==Animal			|| wanted==Pet	
						|| wanted==MasterAndPet		|| wanted==Earthling_Cattle	|| wanted==Forced_Cattle	|| wanted==Earthling_Being	|| wanted==MasterAndCattle	|| wanted==Cattle			) result = true ; break;

			// Living - Trio
			case Semi_Human			: if(wanted==All|| wanted==Living_Being		|| wanted==Worker			|| wanted==Slave_Worker		|| wanted==Slave_Humanoid	
						|| wanted==Human			|| wanted==MasterAndCaptive	|| wanted==Natural_Slave	|| wanted==Semi_Human		|| wanted==People			|| wanted==Natural_Being	) result = true ; break;
			
			case Earthling_People	: if(wanted==All|| wanted==Living_Being		|| wanted==Worker			|| wanted==Slave_Worker		|| wanted==Slave_Animal	
						|| wanted==Human			|| wanted==MasterAndCreature|| wanted==Earthling_Slave	|| wanted==Earthling_People	|| wanted==People			|| wanted==Earthling_Being	) result = true ; break;

			case Natural_Earthling	: if(wanted==All|| wanted==Living_Being		|| wanted==Worker			|| wanted==Slave_Worker		|| wanted==Animal	
						|| wanted==Human			|| wanted==Original_Farm	|| wanted==NatEarthCattle	|| wanted==Natural_Earthling|| wanted==Natural_Being	|| wanted==Earthling_Being	) result = true ; break;

			case MasterAndSlave		: if(wanted==All|| wanted==Living_Being		|| wanted==Worker			|| wanted==Slave_Humanoid		|| wanted==Slave_Animal	
						|| wanted==MasterAndCaptive	|| wanted==MasterAndCreature|| wanted==Forced_Slave		|| wanted==MasterAndSlave	|| wanted==People			|| wanted==MasterAndCattle	) result = true ; break;

			case MasterAndFamiliar	: if(wanted==All|| wanted==Living_Being		|| wanted==Worker			|| wanted==Slave_Humanoid		|| wanted==Animal	
						|| wanted==MasterAndCaptive	|| wanted==Original_Farm	|| wanted==Familiar			|| wanted==MasterAndFamiliar|| wanted==Natural_Being	|| wanted==MasterAndCattle	) result = true ; break;

			case MasterAndPet		: if(wanted==All|| wanted==Living_Being		|| wanted==Worker			|| wanted==Slave_Animal		|| wanted==Animal	
						|| wanted==MasterAndCreature|| wanted==Original_Farm	|| wanted==Pet				|| wanted==MasterAndPet		|| wanted==Earthling_Being	|| wanted==MasterAndCattle	) result = true ; break;

			case Slaves				: if(wanted==All|| wanted==Living_Being		|| wanted==Slave_Worker		|| wanted==Slave_Humanoid		|| wanted==Slave_Animal	
						|| wanted==Natural_Slave	|| wanted==Earthling_Slave	|| wanted==Forced_Slave		|| wanted==Slaves			|| wanted==People			|| wanted==Cattle			) result = true ; break;

			case Natural_Cattle		: if(wanted==All|| wanted==Living_Being		|| wanted==Slave_Worker		|| wanted==Slave_Humanoid		|| wanted==Animal	
						|| wanted==Natural_Slave	|| wanted==NatEarthCattle	|| wanted==Familiar			|| wanted==Natural_Cattle	|| wanted==Natural_Being	|| wanted==Cattle			) result = true ; break;

			case Earthling_Cattle	: if(wanted==All|| wanted==Living_Being		|| wanted==Slave_Worker		|| wanted==Slave_Animal		|| wanted==Animal	
						|| wanted==Earthling_Slave	|| wanted==NatEarthCattle	|| wanted==Pet				|| wanted==Earthling_Cattle	|| wanted==Earthling_Being	|| wanted==Cattle			) result = true ; break;

			case Forced_Cattle		: if(wanted==All|| wanted==Living_Being		|| wanted==Slave_Humanoid		|| wanted==Slave_Animal		|| wanted==Animal	
						|| wanted==Forced_Slave		|| wanted==Familiar			|| wanted==Pet				|| wanted==Forced_Cattle	|| wanted==MasterAndCattle	|| wanted==Cattle			) result = true ; break;

			// Living - Trio
			case People		 		: if(wanted==All|| wanted==Living_Being		|| wanted==People			
							|| wanted==Worker		|| wanted==Slave_Worker		|| wanted==Slave_Humanoid		|| wanted==Slave_Animal	
							|| wanted==Human		|| wanted==MasterAndCaptive	|| wanted==MasterAndCreature|| wanted==Natural_Slave	|| wanted==Earthling_Slave	|| wanted==Forced_Slave
							|| wanted==Semi_Human	|| wanted==Earthling_People	|| wanted==MasterAndSlave	|| wanted==Slaves			) result = true ; break;
			
			case Natural_Being		: if(wanted==All|| wanted==Living_Being		|| wanted==Natural_Being			
							|| wanted==Worker		|| wanted==Slave_Worker		|| wanted==Slave_Humanoid		|| wanted==Animal	
							|| wanted==Human		|| wanted==MasterAndCaptive	|| wanted==Original_Farm	|| wanted==Natural_Slave	|| wanted==NatEarthCattle	|| wanted==Familiar
							|| wanted==Semi_Human	|| wanted==Natural_Earthling|| wanted==MasterAndFamiliar|| wanted==Natural_Cattle	) result = true ; break;
			
			case Earthling_Being	: if(wanted==All|| wanted==Living_Being		|| wanted==Earthling_Being			
						|| wanted==Worker			|| wanted==Slave_Worker		|| wanted==Slave_Animal		|| wanted==Animal	
						|| wanted==Human			|| wanted==MasterAndCreature|| wanted==Original_Farm	|| wanted==Earthling_Slave	|| wanted==NatEarthCattle	|| wanted==Pet
						|| wanted==Earthling_People	|| wanted==Natural_Earthling|| wanted==MasterAndPet		|| wanted==Earthling_Cattle	) result = true ; break;

			case MasterAndCattle	: if(wanted==All|| wanted==Living_Being		|| wanted==MasterAndCattle			
						|| wanted==Worker			|| wanted==Slave_Humanoid		|| wanted==Slave_Animal		|| wanted==Animal	
						|| wanted==MasterAndCaptive	|| wanted==MasterAndCreature|| wanted==Original_Farm	|| wanted==Forced_Slave		|| wanted==Familiar			|| wanted==Pet
						|| wanted==MasterAndSlave	|| wanted==MasterAndFamiliar|| wanted==MasterAndPet		|| wanted==Forced_Cattle	) result = true ; break;

			case Cattle				: if(wanted==All|| wanted==Living_Being		|| wanted==MasterAndCattle			
						|| wanted==Slave_Worker		|| wanted==Slave_Humanoid		|| wanted==Slave_Animal		|| wanted==Animal	
						|| wanted==Natural_Slave	|| wanted==Earthling_Slave	|| wanted==NatEarthCattle	|| wanted==Forced_Slave		|| wanted==Familiar			|| wanted==Pet
						|| wanted==Slaves			|| wanted==Natural_Cattle	|| wanted==Earthling_Cattle	|| wanted==Forced_Cattle	) result = true ; break;
			
					

			case Living_Being	 : if(wanted!=None	&& wanted!=Building			&& wanted!=Economic_Agent	) result = true ; break;
			case Economic_Agent	 : if(wanted==All	|| wanted==Building			|| wanted==Worker			|| wanted!=Economic_Agent	) result = true ; break;
			case All : if(wanted!=None) result = true ; break;
			
		}
		return result;
	}
	
	// Fields
	
	private Integer kind;
	private int mod; //Check kind->Xml : 0 no, 0< as attrib (1 thing, 2 intel).

	// Constructors
	
	public MilkKind() {
		this(0,0);
	}
	public MilkKind(Integer kind,int mod) {
		super();
		this.setKind(kind);
		this.setMod(mod);
	}
	public MilkKind(Element milkElement) {
		super();
		this.setKind(0);
		this.setMod(0);
		this.setValueFromNode(milkElement);
	}
	public MilkKind(MilkKind original) {
		super(original);
		this.kind = new Integer(original.getKind());
		this.mod = original.getMod();
	}

	// Set value from Element methods
	
	@Override
	public void setValueFromNode(Element milkElement) {
		super.setValueFromNode(milkElement);
		this.setKind(milkElement);
	}
	public void setKind(Element milkElement) {
		Integer temp=null;
		temp=ParseMilkFile.getXmlIntAttribute(milkElement,xmlKind);
		if (temp != null) this.kind=temp;
	}
	
	// field methods
	
	public Integer getKind() {
		return this.kind;
	}
	public String getStringKind() {
		String temp = "";
		if (this.kind != null) {
			temp += " "+xmlKind+" : ";
			int tp = (mod==2)?this.kind+50:this.kind;
			switch(tp){
				case None				: temp += MilkInterface.getStringsFromId(500); break;
				case None+50			: temp += MilkInterface.getStringsFromId(500); break;
				case All				: temp += MilkInterface.getStringsFromId(599); break;
				case All+50				: temp += MilkInterface.getStringsFromId(599); break;
				
				case Building			: temp+= MilkInterface.getStringsFromId(501); break;
				case Worker				: temp+= MilkInterface.getStringsFromId(502); break;
				case Slave_Worker		: temp+= MilkInterface.getStringsFromId(503)+" "+MilkInterface.getStringsFromId(504); break;
				case Slave_Humanoid		: temp+= MilkInterface.getStringsFromId(503)+" "+MilkInterface.getStringsFromId(506); break;
				case Slave_Animal		: temp+= MilkInterface.getStringsFromId(503)+" "+MilkInterface.getStringsFromId(507); break;
				case Animal				: temp+= MilkInterface.getStringsFromId(505); break;
				
				case Human		 		: temp+= MilkInterface.getStringsFromId(504); break;
				case MasterAndCaptive	: temp+= MilkInterface.getStringsFromId(517)+" "+MilkInterface.getStringsFromId(1100)+" "+MilkInterface.getStringsFromId(518); break;
				case MasterAndCreature	: temp+= MilkInterface.getStringsFromId(517)+" "+MilkInterface.getStringsFromId(1100)+" "+MilkInterface.getStringsFromId(519); break;
				case Original_Farm		: temp+= MilkInterface.getStringsFromId(523)+" "+MilkInterface.getStringsFromId(524); break;
				case Natural_Slave		: temp+= MilkInterface.getStringsFromId(503)+" "+MilkInterface.getStringsFromId(514); break;
				case Earthling_Slave	: temp+= MilkInterface.getStringsFromId(503)+" "+MilkInterface.getStringsFromId(513); break;
				case NatEarthCattle		: temp+= MilkInterface.getStringsFromId(512)+" "+MilkInterface.getStringsFromId(513)+" "+MilkInterface.getStringsFromId(514); break;
				case Forced_Slave		: temp+= MilkInterface.getStringsFromId(503)+" "+MilkInterface.getStringsFromId(522); break;
				case Familiar			: temp+= MilkInterface.getStringsFromId(520); break;
				case Pet				: temp+= MilkInterface.getStringsFromId(521); break;

				case Semi_Human 		: temp+= MilkInterface.getStringsFromId(516)+" "+MilkInterface.getStringsFromId(504); break;
				case Earthling_People	: temp+= MilkInterface.getStringsFromId(511)+" "+MilkInterface.getStringsFromId(513); break;
				case Natural_Earthling	: temp+= MilkInterface.getStringsFromId(513)+" "+MilkInterface.getStringsFromId(514); break;
				case MasterAndSlave		: temp+= MilkInterface.getStringsFromId(517)+" "+MilkInterface.getStringsFromId(1100)+" "+MilkInterface.getStringsFromId(503); break;
				case MasterAndFamiliar	: temp+= MilkInterface.getStringsFromId(517)+" "+MilkInterface.getStringsFromId(1100)+" "+MilkInterface.getStringsFromId(520); break;
				case MasterAndPet		: temp+= MilkInterface.getStringsFromId(517)+" "+MilkInterface.getStringsFromId(1100)+" "+MilkInterface.getStringsFromId(521); break;
				case Slaves				: temp+= MilkInterface.getStringsFromId(503); break;
				case Natural_Cattle		: temp+= MilkInterface.getStringsFromId(512)+" "+MilkInterface.getStringsFromId(514); break;
				case Earthling_Cattle	: temp+= MilkInterface.getStringsFromId(512)+" "+MilkInterface.getStringsFromId(513); break;
				case Forced_Cattle		: temp+= MilkInterface.getStringsFromId(512)+" "+MilkInterface.getStringsFromId(522); break;

				case People				: temp+= MilkInterface.getStringsFromId(511); break;
				case Natural_Being		: temp+= MilkInterface.getStringsFromId(510)+" "+MilkInterface.getStringsFromId(514); break;
				case Earthling_Being	: temp+= MilkInterface.getStringsFromId(510)+" "+MilkInterface.getStringsFromId(513); break;
				case MasterAndCattle	: temp+= MilkInterface.getStringsFromId(517)+" "+MilkInterface.getStringsFromId(1100)+" "+MilkInterface.getStringsFromId(512); break;
				case Cattle				: temp+= MilkInterface.getStringsFromId(512); break;
				
				case Research+50		: temp+= MilkInterface.getStringsFromId(551); break;
				case Upgrade+50			: temp+= MilkInterface.getStringsFromId(552); break;
				case Synergy+50			: temp+= MilkInterface.getStringsFromId(553); break;
				case Ascension+50		: temp+= MilkInterface.getStringsFromId(554); break;
				case Event+50			: temp+= MilkInterface.getStringsFromId(555); break;
			}
			temp += ". ";
		}
		return temp;
	}
	public String getXmlKind() {
		String temp = null;
		if (this.kind != null) temp = " "+xmlKind+"=\""+kind+"\"";
		return temp;
	}
	public void setKind(Integer kind) {
		this.kind = kind;
	}
	
	public int getMod() {
		return this.mod;
	}
	public void setMod(int mod) {
		this.mod = mod;
	}
	
	// toString & toXml methods
	
	@Override
	public String toStringAttrib() {
		String temp = super.toStringAttrib();
		if (this.mod>0) temp+=this.getStringKind();
		return temp;
	}
	@Override
	public String toXmlAttrib() {
		String temp = super.toXmlAttrib();
		if (this.mod>0) temp+=this.getXmlKind();
		return temp;
	}
	
	// other object methods
	
	public boolean allZero() {
		boolean temp = super.allZero();
		if(this.kind!=null && this.kind!=0) temp= false;
		return temp;
	}
}
