package modele;

import java.util.Vector;

import org.w3c.dom.Element;

import controleur.ParseMilkFile;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class MilkImage extends MilkFile {

	private static Vector<MilkImage> milkInterfaceIcon;
	private static Vector<MilkFile> milkIcon = null, milkScene = null;
	
	public static final String iconFile = "IconFileListe", sceneFile = "SceneFileListe";
	public static final String noeud = "image", xmlExt= "ext", xmlDesc= "desc";
	public String getNoeud() {return noeud;}
	
	public static int ICO_HEIGHT=100, ICO_WIDTH=100, SICO_HEIGHT = 20, SICO_WIDTH = 20;
	
	// Static method playing with files.Xml.

	/**
	 * set the milkFiles from the xml found in the file XmlFileListe.
	 * @param milkFiles 
	 * @param imageType 
	 */
	private static Vector<MilkFile> setMilkImagesListFromFiles(String imageType) {
		Vector<Element> elementlist = new Vector<Element>();
		elementlist = MilkFile.getMilkElementsFromFiles(MilkFile.getXmlFilePath(imageType)+imageType);
		Vector<MilkFile> milkFiles = getMilkVarList(elementlist);
		return milkFiles;
	}
	
	/**
	 * Return the path of an xml image list after searching the file name in an imageFileList.
	 */
	private static String getXmlImagePath(String fileName, Vector<MilkFile> milkFiles, String imageType) {
		String link = "";
		for (MilkFile gamefile: milkFiles) {
			if (fileName.equals(gamefile.getName())) link = xmlBasePath+gamefile.getPath();
		}
		return link;
	}
	/**
	 * Return the path of an xml scene list after searching the file name in a SceneFileListe.
	 */
	public static String getXmlScenesPath(String fileName) {
		if (milkScene==null){
			milkScene = new Vector<MilkFile>();
			milkScene = setMilkImagesListFromFiles(sceneFile);
		}
		return getXmlImagePath(fileName,milkScene,sceneFile);
	}
	/**
	 * Return the path of an xml icon list after searching the file name in an IconFileListe.
	 */
	public static String getXmlIconsPath(String fileName) {
		if (milkIcon==null){
			milkIcon = new Vector<MilkFile>();
			milkIcon = setMilkImagesListFromFiles(iconFile);
		}
		return getXmlImagePath(fileName,milkIcon,iconFile);
	}
	
	private static Vector<MilkImage> setMilkInterfaceIconFromFiles() {
		if (milkInterfaceIcon==null) milkInterfaceIcon = new Vector<MilkImage>();
		else milkInterfaceIcon.removeAllElements();
		Vector<Element> elementlist = new Vector<Element>();
		elementlist = getMilkElementsFromFiles(getXmlIconsPath(MilkInterface.file)+MilkInterface.file, noeud);
		milkInterfaceIcon = getMilkImageList(elementlist);
		return milkInterfaceIcon;
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
		if(milkInterfaceIcon==null)setMilkInterfaceIconFromFiles();
		MilkImage milkImage = new MilkImage();
		for (MilkImage tempImage: milkInterfaceIcon) {
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

	// Fields

	private String extension;
	private String desc;
	private Image img;
	private double IMG_HEIGHT, IMG_WIDTH;
	
	// Constructors
	
	public MilkImage() {
		this("");
	}
	public MilkImage(String desc) {
		super();
		this.setExt(".png");
		this.setDesc(desc);
		this.setImage(null);
	}
	public MilkImage(Element milkElement) {
		super();
		this.setExt(".png");
		this.setValueFromNode(milkElement);
	}

	// Set value from Element methods
	
	@Override
	public void setValueFromNode(Element milkElement) {
		super.setValueFromNode(milkElement);
		this.setExt(milkElement);
		this.setDesc(milkElement);
		setImage();
	}
	public void setDesc(Element milkElement) {
		this.desc = ParseMilkFile.getXmlStringValue(milkElement,xmlDesc);
	}
	/*
	@Override
	public void setNullValueFromNode(Element milkElement) {
		super.setNullValueFromNode(milkElement);
		this.setNullDesc(milkElement);
		setImage();
	}
	public void setNullDesc(Element milkElement) {
		this.desc = ParseMilkFile.getXmlStringValue(milkElement,xmlDesc);
	}*/
	
	// field methods
	
	public void setExt(String ext) {
		this.extension = ext;
	}
	private void setExt(Element milkElement) {
		String tempExt = ParseMilkFile.getXmlStringValue(milkElement,xmlExt);
		if(tempExt.length()>1&&tempExt.length()<10) this.extension=tempExt;
	}
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
	
	public void setImage(Image image) {
		this.img = image;
	}
	
	private void setImage() {
		String path = this.getPath();
		if(path!=null && path.length()>0){
			path = "file:"+this.getPath()+this.getName()+extension;
			this.img = new Image(path);
			IMG_HEIGHT = img.getHeight();
			IMG_WIDTH = img.getWidth();
		}
	}
	public Image getImage() {
		return img;
	}
	public ImageView getImageView() {
            return getImageView(IMG_WIDTH,IMG_HEIGHT);
     }

	public ImageView getImageView(double layoutY) {
		double width = layoutY*IMG_WIDTH/IMG_HEIGHT;
		return getImageView(width, layoutY) ;
	}
	
	public ImageView getImageView(double width, double height) {
        ImageView imageView = new ImageView();
        imageView.setFitHeight(height);
        imageView.setFitWidth(width);
        imageView.setImage(img);
        return imageView;
	}
    
	public double getHeight() {
		return IMG_HEIGHT;
	}
	public double getWidth() {
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
