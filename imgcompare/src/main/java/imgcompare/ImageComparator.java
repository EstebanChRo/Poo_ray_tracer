package imgcompare;

import java.awt.image.BufferedImage;

public class ImageComparator {
    public int compteur_pixel_diff(BufferedImage Img1, BufferedImage Img2){
        int compteur = 0;
        for (int x=0; x < Img1.getWidth(); x++){
            for (int y=0; y < Img1.getHeight(); y++){
                if (Img1.getRGB(x,y) != Img2.getRGB(x,y)){
                    compteur++;
                }
            }
        }
        return compteur;
    }

    public BufferedImage img_différentielle(BufferedImage Img1, BufferedImage Img2){
        BufferedImage img_différentielle = new BufferedImage(Img1.getWidth(), Img1.getHeight(),BufferedImage.TYPE_INT_RGB);
        for (int x=0; x < Img1.getWidth(); x++){
            for (int y=0; y < Img1.getHeight(); y++){
                int pixel1=Img1.getRGB(x, y);
                int pixel2=Img2.getRGB(x, y);
                img_différentielle.setRGB(x, y, pixel1 ^ pixel2);
            }
        }
        return img_différentielle;
    }
}
