package ray_tracer;

import java.awt.image.BufferedImage;
import java.io.IOException;

import ray_tracer.parsing.Scene;
import ray_tracer.parsing.SceneFileParser;
import ray_tracer.raytracer.ImageRenderer;
import ray_tracer.raytracer.ImageWriter;

public class main {
    public static void main(String[] args) throws IOException {
        Scene scene = new SceneFileParser().parse("ray_tracer/src/main/scenes/jalon3/tp3.test");
        ImageRenderer renderer = new ImageRenderer();
        BufferedImage image = renderer.render(scene);
        ImageWriter writer = new ImageWriter();
        writer.write(image, scene.getOutput());
    }
/*
TODO : Jalon-4 : Étape 5 : Tester la réflexion diffuse
Objectif : Valider que le modèle de Lambert est correctement implémenté.
Tests suggérés :

Test unitaire :
    Vérifier que Intersection.calculateDiffuseColor retourne :
        Une couleur noire si la lumière est parallèle à la surface (produit scalaire = 0).
        Une couleur maximale si la lumière est perpendiculaire à la surface (produit scalaire = 1).

Test visuel :
    Générer une image avec une sphère et une lumière ponctuelle.
    Vérifier que :
        Le côté de la sphère face à la lumière est plus clair.
        Le côté opposé est plus sombre (seulement lumière ambiante).
*/

}