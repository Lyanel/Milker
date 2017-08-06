package modele;

import controleur.ParseMilkFile;

import org.w3c.dom.Element;

/**
 * Kind :  Refer to the Kind of the thing you have/need :<br/>
 * object : 1 (buildings), 2 (workers), 3 (slaves A (human)), 4 (slaves B (animal)), 5 (animals), <br/>
 * 	need/check :  x=0 (all), 6 (Semi-Humans (workers+SA)), 7 (slaves (SA+SB)), 8 (Semi-animals (SB+animals)),<br/>
			09 (Earthling People (workers+SB)),			10 (natural Cattle (SA+animals)), <br/>
			11 (People (workers+SA+SB)),				12 (cattle (SA+SB+animals)),<br/>
			13 (Earthling being (workers+SB+animals)),	14 (natural being (workers+SA+animals)), <br/>
			15 (Livings being (workers+SA+SB+animals)).<br/>
	intel : 1 (research), 2 (upgrade), 3 (synergy), 4 (ascension), 5 (event).
	
 * @author Slade Chaos
 */
public class MilkKind extends MilkVar implements Cloneable {

	public static final int kind_None = 0;
	public static final int kind_All = 49;
	public static final int kind_Building = 1;
	public static final int kind_Worker = 2;
	public static final int kind_Slave_Human = 3;
	public static final int kind_Slave_Worker = 3;
	public static final int kind_Slave_Animal = 4;
	public static final int kind_Animal = 5;
	public static final int kind_Semi_Human = 6;
	public static final int kind_Slaves = 7;
	public static final int kind_Semi_Animal = 8;
	public static final int kind_Earthling_People = 9;
	public static final int kind_Natural_Cattle  = 10;
	public static final int kind_People = 11;
	public static final int kind_Cattle = 12;
	public static final int kind_Earthling_Being = 13;
	public static final int kind_Natural_Being = 14;
	public static final int kind_Living_Being = 15;

	public static final int kind_Research = 1;
	public static final int kind_Upgrade = 2;
	public static final int kind_Synergy =3;
	public static final int kind_Ascension = 4;
	public static final int kind_Event = 5;
	
	public static final String xmlKind = "kind";
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

	// Set value from Element methods
	
	@Override
	public void setValueFromNode(Element milkElement) {
		super.setValueFromNode(milkElement);
		this.setKind(milkElement);
	}
	@Override
	public void setNullValueFromNode(Element milkElement) {
		super.setNullValueFromNode(milkElement);
		this.setNullKind(milkElement);
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
				case kind_None			: temp += MilkInterface.getStringsFromId(500); break;
				case kind_None+50	: temp += MilkInterface.getStringsFromId(500); break;
				case kind_All				: temp += MilkInterface.getStringsFromId(599); break;
				case kind_All+50			: temp += MilkInterface.getStringsFromId(599); break;
				
				case kind_Building						: temp+= MilkInterface.getStringsFromId(501); break;
				case kind_Worker						: temp+= MilkInterface.getStringsFromId(502); break;
				case kind_Slave_Human			: temp+= MilkInterface.getStringsFromId(503)+" "+MilkInterface.getStringsFromId(504); break;
				case kind_Slave_Animal			: temp+= MilkInterface.getStringsFromId(503)+" "+MilkInterface.getStringsFromId(505); break;
				case kind_Animal						: temp+= MilkInterface.getStringsFromId(505); break;
				case kind_Semi_Human 			: temp+= MilkInterface.getStringsFromId(516)+" "+MilkInterface.getStringsFromId(504); break;
				case kind_Slaves						: temp+= MilkInterface.getStringsFromId(504); break;
				case kind_Semi_Animal 			: temp+= MilkInterface.getStringsFromId(516)+" "+MilkInterface.getStringsFromId(505); break;
				case kind_Earthling_People	: temp+= MilkInterface.getStringsFromId(511)+" "+MilkInterface.getStringsFromId(513); break;
				case kind_Natural_Cattle			: temp+= MilkInterface.getStringsFromId(512)+" "+MilkInterface.getStringsFromId(514); break;
				case kind_People						: temp+= MilkInterface.getStringsFromId(511); break;
				case kind_Cattle							: temp+= MilkInterface.getStringsFromId(512); break;
				case kind_Earthling_Being		: temp+= MilkInterface.getStringsFromId(510)+" "+MilkInterface.getStringsFromId(513); break;
				case kind_Natural_Being			: temp+= MilkInterface.getStringsFromId(510)+" "+MilkInterface.getStringsFromId(514); break;
				case kind_Living_Being			: temp+= MilkInterface.getStringsFromId(510)+" "+MilkInterface.getStringsFromId(515); break;

				case kind_Research+50			: temp+= MilkInterface.getStringsFromId(551); break;
				case kind_Upgrade+50				: temp+= MilkInterface.getStringsFromId(552); break;
				case kind_Synergy+50				: temp+= MilkInterface.getStringsFromId(553); break;
				case kind_Ascension+50			: temp+= MilkInterface.getStringsFromId(554); break;
				case kind_Event+50					: temp+= MilkInterface.getStringsFromId(555); break;
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
	public void setKind(Element milkElement) {
		Integer temp=null;
		temp=ParseMilkFile.getXmlIntAttribute(milkElement,xmlKind);
		if (temp != null) this.kind=temp;
	}
	public void setNullKind(Element milkElement) {
		kind = ParseMilkFile.getXmlIntAttribute(milkElement,xmlKind);
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
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		MilkKind clone = (MilkKind) super.clone();
		return clone;
	}
}
