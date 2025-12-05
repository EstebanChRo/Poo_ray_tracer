package ray_tracer.raytracer;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class ImageWriter {
    public void write(BufferedImage image, String filename) throws IOException {
        File outputFile = new File(filename);
        File parentDir = outputFile.getParentFile();
        if (parentDir != null) {
            parentDir.mkdirs();
        }
        if (!ImageIO.write(image, "png", outputFile)) {
            throw new IOException("Échec de l'écriture de l'image : format non supporté.");
        }
    }
}
