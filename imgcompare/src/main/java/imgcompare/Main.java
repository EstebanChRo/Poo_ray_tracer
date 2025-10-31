package imgcompare;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Deux images nécessaires en arguments.");
            return;
        }

        String imagePath1 = args[0];
        String imagePath2 = args[1];

        try {
            BufferedImage image1 = ImageIO.read(new File(imagePath1));
            BufferedImage image2 = ImageIO.read(new File(imagePath2));

        } catch (IOException e) {
            System.err.println("Erreur lors de la lecture des images : " + e.getMessage());
        }
    }
}
