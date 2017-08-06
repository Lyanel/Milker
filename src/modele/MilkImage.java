package modele;

import java.util.Vector;

import org.w3c.dom.Element;

import controleur.ParseMilkFile;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class MilkImage extends MilkFile {

	private static Vector<MilkImage> milkImages;
	public static final String file = "ImgFileListe";
	public static final String noeud = "image", xmlDesc= "desc";
	public String getNoeud() {return noeud;}
	
	public static int ICO_HEIGHT=100, ICO_WIDTH=100, SICO_HEIGHT = 20, SICO_WIDTH = 20;

	private static Vector<MilkImage> setMilkImagesFromFiles() {
		if (milkImages==null) milkImages = new Vector<MilkImage>();
		else milkImages.removeAllElements();
		Vector<Element> elementlist = new Vector<Element>();
		elementlist = getMilkElementsFromFiles(getXmlFilePath(file)+file, noeud);
		milkImages = getMilkImageList(elementlist);
		return milkImages;
	}
	public static Vector<MilkImage> getMilkImageList(Vector<Element> elementlist) {
		Vector<MilkImage> milkFiles = new Vector<MilkImage>();
		for (Element elementMilk: elementlist) {
			try {
				MilkImage milkFile = new MilkImage(elementMilk);
				milkFiles.add(milkFile);
			} catch (Exception e) {e.printStackTrace();}
		}
		return milkFiles;
	}
	
	public static MilkImage getImageFromID(int id) {
		if(milkImages==null)setMilkImagesFromFiles();
		MilkImage milkImage = new MilkImage();
		for (MilkImage tempImage: milkImages) {
			try {
				if (tempImage.getId()==id)milkImage=(MilkImage) tempImage.clone();
			} catch (Exception e) {e.printStackTrace();}
		}
		return milkImage;
	}
	public static MilkImage getIconFromID(int id) {
		MilkImage milkImage = getImageFromID(id);
		milkImage.setAsIcon();
		return milkImage;
	}
	public static MilkImage getSmallIconFromID(int id) {
		MilkImage milkImage = getImageFromID(id);
		milkImage.setAsSmallIcon();
		return milkImage;
	}
	
	private String desc;
	private Image img;
	private double IMG_HEIGHT, IMG_WIDTH;
	
	// Constructors
	
	public MilkImage() {
		this("");
	}
	public MilkImage(String desc) {
		super();
		this.setDesc(desc);
		this.setImage(null);
	}
	public MilkImage(Element milkElement) {
		super();
		this.setValueFromNode(milkElement);
	}

	// Set value from Element methods
	
	@Override
	public void setValueFromNode(Element milkElement) {
		super.setValueFromNode(milkElement);
		this.setDesc(milkElement);
		setImage();
	}
	@Override
	public void setNullValueFromNode(Element milkElement) {
		super.setNullValueFromNode(milkElement);
		this.setNullDesc(milkElement);
		setImage();
	}
	
	// field methods
	
	public String getDesc() {
		return desc;
	}
	public String getStringDesc() {
		String temp = null;
		if (this.desc != null) temp = xmlDesc+" : "+this.desc+". ";
		return temp;
	}
	public String getXmlDesc() {
		String temp = null;
		if (this.desc != null) temp = "<"+xmlDesc+">"+this.desc+"</"+xmlDesc+">"+MilkRs.LIGNE_BREAK;
		return temp;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public void setDesc(Element milkElement) {
		this.desc = ParseMilkFile.getXmlStringValue(milkElement,xmlDesc);
	}
	public void setNullDesc(Element milkElement) {
		this.desc = ParseMilkFile.getXmlStringValue(milkElement,xmlDesc);
	}
	
	public void setImage(Image image) {
		this.img = image;
	}
	
	private void setImage() {
		String path = this.getPath();
		if(path!=null && path.length()>0){
			path = "file:"+this.getPath()+this.getName()+".png";
			this.img = new Image(path);
			IMG_HEIGHT = img.getHeight();
			IMG_WIDTH = img.getWidth();
		}
	}
	public Image getImage() {
		return img;
	}
	public ImageView getImageView() {
            ImageView imageView = new ImageView();
            imageView.setFitHeight(IMG_HEIGHT);
            imageView.setFitWidth(IMG_WIDTH);
            imageView.setImage(img);
            return imageView;
     }
    
	public double getHeight() {
		// TODO Auto-generated method stub
		return IMG_HEIGHT;
	}
	public double getWidth() {
		// TODO Auto-generated method stub
		return IMG_WIDTH;
	}
	public void setAsIcon() {
		this.IMG_HEIGHT = ICO_HEIGHT;
		this.IMG_WIDTH = ICO_WIDTH;
	}
	public void setAsSmallIcon() {
		this.IMG_HEIGHT = SICO_HEIGHT;
		this.IMG_WIDTH = SICO_WIDTH;
	}
	
	
	// toString & toXml methods
	
	@Override
	public String toStringTextChild() {
		String temp = super.toStringTextChild();
		temp+=this.getStringDesc();
		return temp;
	}
	@Override
	public String toXmlTextChild() {
		String temp = super.toXmlTextChild();
		temp+=this.getXmlDesc();
		return temp;
	}
	
	// other object methods
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		MilkImage clone = (MilkImage) super.clone();
		if (this.img!=null) clone.setImage(this.img);
		return clone;
	}
} 
