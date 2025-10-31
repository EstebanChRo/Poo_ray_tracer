package imgcompare;

import java.awt.image.BufferedImage;

public class ImageComparator {
    
    public int compteur_pixel_diff(BufferedImage Img1, BufferedImage Img2){
        int compteur = 0;
        for (int x=0; x < Img1.getWidth(); x++){
            for (int y=0; y < Img1.getHeight(); y++){
                if (Img1.getRGB(x,y) == Img2.getRGB(x,y)){
                    compteur++;
                }
            }
        }
        return compteur;
    }

    


}
