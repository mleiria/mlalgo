package pt.mleiria.image;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import pt.mleiria.mlalgo.utils.Arrays1D;

public class Main {
    private static String pathToFile = "/media/manuel/Elements/Pessoal/Joana/";
    public static String imgFile = "/media/manuel/Elements/Pessoal/Joana/img2.jpg";

    public static void main(String[] args) {

	//ColorImage ci = new ColorImage(pathToFile + "teste.jpg");
	// System.out.println(VUtils.showArrayContents(ci.getData()));
	//Double[] d = Arrays1D.convertToVector(ci.getData());
	// System.out.println(new VUtils<Double>().showContents((d)));
	//System.out.println(d.length);
	
	try {
	    normalizeImage();
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }

    private static void normalizeImage() throws IOException {
	Image image = ImageUtil.readImage(pathToFile + "teste.jpg");
	Image image1 = ImageUtil.resize(image, 4128, 3096);
	ImageIO.write(toBufferedImage(image1), "jpg", new File(pathToFile + "teste2.jpg"));

    }

    public static void imageIoWrite() {
	BufferedImage bImage = null;
	try {
	    File initialImage = new File("C://Users/Rou/Desktop/image.jpg");
	    bImage = ImageIO.read(initialImage);

	    ImageIO.write(bImage, "gif", new File("C://Users/Rou/Desktop/image.gif"));
	    ImageIO.write(bImage, "jpg", new File("C://Users/Rou/Desktop/image.png"));
	    ImageIO.write(bImage, "bmp", new File("C://Users/Rou/Desktop/image.bmp"));

	} catch (IOException e) {
	    System.out.println("Exception occured :" + e.getMessage());
	}
	System.out.println("Images were written succesfully.");
    }

    public static BufferedImage toBufferedImage(Image img) {
	if (img instanceof BufferedImage) {
	    return (BufferedImage) img;
	}

	// Create a buffered image with transparency
	BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

	// Draw the image on to the buffered image
	Graphics2D bGr = bimage.createGraphics();
	bGr.drawImage(img, 0, 0, null);
	bGr.dispose();

	// Return the buffered image
	return bimage;
    }

}
