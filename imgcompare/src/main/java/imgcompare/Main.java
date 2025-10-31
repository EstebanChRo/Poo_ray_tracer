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

        //déclaration des images avant le try-catch pour qu'elles soient accessibles dans toute la méthode main
        BufferedImage image1 = null;
        BufferedImage image2 = null;

        try {
            image1 = ImageIO.read(new File(imagePath1));
            image2 = ImageIO.read(new File(imagePath2));
        } catch (IOException e) {
            System.err.println("Erreur lors de la lecture des images : " + e.getMessage());
            return;
        }

        ImageComparator comparator = new ImageComparator();

        int diffCount = comparator.compteur_pixel_diff(image1, image2);

        if (diffCount <= 1000) {
            System.out.println("OK");
        } else {
            System.out.println("KO");
        }
        System.out.printf("Les deux images diffèrent de %d pixels.\n", diffCount);

        BufferedImage diffImage = comparator.img_différentielle(image1, image2);

        File resultatDir = new File("imgcompare/src/main/resultat");
        if (!resultatDir.exists()) {
            resultatDir.mkdirs();
        }

        try {
            ImageIO.write(diffImage, "png", new File("imgcompare/src/main/resultat/difference.png"));
            System.out.println("Image de différence enregistrée sous 'imgcompare/src/main/resultat/difference.png'.");
        } catch (IOException e) {
            System.err.println("Erreur lors de l'enregistrement de l'image de différence : " + e.getMessage());
        }
    }
}