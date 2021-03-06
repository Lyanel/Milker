package modele.baseObject;

import java.util.ArrayList;

import org.w3c.dom.Element;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import modele.MilkRs;
import modele.ParseMilkFile;

public class MilkImage extends MilkFile {

	private static ArrayList<MilkImage> milkInterfaceIcon;
	private static ArrayList<MilkFile> milkIcon = null, milkScene = null;
	
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
	private static ArrayList<MilkFile> setMilkImagesListFromFiles(String imageType) {
		ArrayList<Element> elementlist = new ArrayList<Element>();
		elementlist = MilkFile.getMilkElementsFromFiles(MilkFile.getXmlFilePath(imageType)+imageType);
		ArrayList<MilkFile> milkFiles = MilkFile.getMilkVarList(elementlist);
		return milkFiles;
	}
	
	/**
	 * Return the path of an xml image list after searching the file name in an imageFileList.
	 */
	private static String getXmlImagePath(String fileName, ArrayList<MilkFile> milkFiles, String imageType) {
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
			milkScene = new ArrayList<MilkFile>();
			milkScene = setMilkImagesListFromFiles(sceneFile);
		}
		return getXmlImagePath(fileName,milkScene,sceneFile);
	}
	/**
	 * Return the path of an xml icon list after searching the file name in an IconFileListe.
	 */
	public static String getXmlIconsPath(String fileName) {
		if (milkIcon==null){
			milkIcon = new ArrayList<MilkFile>();
			milkIcon = setMilkImagesListFromFiles(iconFile);
		}
		return getXmlImagePath(fileName,milkIcon,iconFile);
	}
	
	private static ArrayList<MilkImage> setMilkInterfaceIconFromFiles() {
		if (milkInterfaceIcon==null) milkInterfaceIcon = new ArrayList<MilkImage>();
		else milkInterfaceIcon.clear();
		ArrayList<Element> elementlist = new ArrayList<Element>();
		elementlist = getMilkElementsFromFiles(getXmlIconsPath(MilkInterface.file)+MilkInterface.file, noeud);
		milkInterfaceIcon = getMilkImageList(elementlist);
		return milkInterfaceIcon;
	}
	
	public static ArrayList<MilkImage> getMilkImageList(ArrayList<Element> elementlist) {
		ArrayList<MilkImage> milkFiles = new ArrayList<MilkImage>();
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
			if (tempImage.getId()==id)milkImage=new MilkImage(tempImage);
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
	/*
	private static BufferedImage getImageFromData(String image) throws IOException{
		File newImage = new File("Img/"+image+".jpg");
		if (!newImage.exists()){
			FileInputStream fistream = new FileInputStream("Data/Picture/"+image+".jpg.dat");
			Vector<Byte> b = new Vector<Byte>();
			Integer c;
			while ((c = fistream.read()) != -1){
				b.add(c.byteValue());
			}
			fistream.close();
			if(!newImage.getParentFile().exists())newImage.mkdir();
			FileOutputStream fostream = new FileOutputStream("Img/"+image+".jpg"); 
			while (!b.isEmpty()) {
				fostream.write((b.remove(b.size()-1)));
			}
			fostream.flush();
			fostream.close();
		}
		return ImageIO.read(newImage);
	}*/

	/*public void convertAllfromDat()  { 
	    	File dirData = new File("Data");
	    	File[] listImage = dirData.listFiles();
	    	for (int i = 0; i < listImage.length; i++) {
	    		if (listImage[i].getName().indexOf("dat")!=-1){
	    			try {
						getBufferedImage(listImage[i].getName().substring(0, listImage[i].getName().lastIndexOf(".jpg")),"");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	    		}
			}
	    }*/

	// Fields

	private String extension, desc;
	private double IMG_HEIGHT, IMG_WIDTH;
	//private Image img;
	
	// Constructors
	
	public MilkImage() {
		this("");
	}
	public MilkImage(String desc) {
		super();
		this.setExt(".png");
		this.setDesc(desc);
	//	this.setImage(null);
	}
	public MilkImage(Element milkElement) {
		super();
		this.setExt(".png");
		this.setValueFromNode(milkElement);
	}
	public MilkImage(MilkImage original) {
		super(original);
		this.extension = new String(original.getExt());
		this.desc = new String(original.getDesc());
		this.IMG_HEIGHT = original.IMG_HEIGHT;
		this.IMG_WIDTH = original.IMG_WIDTH;
	}

	// Set value from Element methods
	
	@Override
	public void setValueFromNode(Element milkElement) {
		super.setValueFromNode(milkElement);
		this.setExt(milkElement);
		this.setDesc(milkElement);
	}
	public void setDesc(Element milkElement) {
		this.desc = ParseMilkFile.getXmlStringValue(milkElement,xmlDesc);
	}
	
	// field methods

	public String getExt() {
		return this.extension;
	}
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
	/*
	public void setImage(Image image) {
		this.img = image;
	}*/
	
	private Image setImage() {
		/*String path = this.getPath();
		if(path!=null && path.length()>0){
			path = "file:"+this.getPath()+this.getName()+extension;
			this.img = new Image(path);
		}*/
		Image img = ParseMilkFile.getMilkImage(this.getPath()+this.getName(), extension);
		if(img!=null){
			IMG_HEIGHT = img.getHeight();
			IMG_WIDTH = img.getWidth();
		} else {System.out.println("MilkImage.setImage.img : "+img+". Fuuuuuuuuckkkkk!)");}
		return img;
	}
	public Image getImage() {
		return setImage();
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
        imageView.setImage(setImage());
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
	
	
} 
