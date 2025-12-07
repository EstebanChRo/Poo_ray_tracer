package ray_tracer.raytracer;

import ray_tracer.geometry.Orthonormal;
import ray_tracer.geometry.Vector;
import ray_tracer.imaging.Color;
import ray_tracer.parsing.Scene;
import java.awt.image.BufferedImage;
import java.util.Optional;

public class ImageRenderer {
    public ImageRenderer(){
    }

    public BufferedImage render(Scene scene) {
        Orthonormal basis = new Orthonormal(scene.getCamera());
        BufferedImage image = new BufferedImage(scene.getWidth(), scene.getHeight(), BufferedImage.TYPE_INT_RGB);
        RayTracer raytracer = new RayTracer();

        for (int i = 0; i < scene.getWidth(); i++) {
            for (int j = 0; j < scene.getHeight(); j++) {
                Vector d = raytracer.directionCalcul(i, j, basis, scene); // calculer le vecteur unitaire d qui représente un rayon allant de l'oeil/camera au centre du pixel (i,j)
                Ray ray = new Ray(scene.getCamera().getLookFrom(), d);

                Optional<Intersection> intersection = scene.findClosestIntersection(ray); // rechercher le point d'intersection p le plus proche avec un objet de la scène

                Color pixelColor = raytracer.getColor(intersection, scene);
                image.setRGB(i, scene.getHeight() - 1 - j, pixelColor.toRGB());
            }
        }
        return image;
    }
}
