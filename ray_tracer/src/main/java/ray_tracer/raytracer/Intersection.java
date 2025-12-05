package ray_tracer.raytracer;



import ray_tracer.geometry.Point;
import ray_tracer.geometry.Vector;
import ray_tracer.geometry.shapes.Shape;
import ray_tracer.imaging.Color;
import ray_tracer.raytracer.Lights.AbstractLight;
import ray_tracer.raytracer.Lights.PointLight;
import ray_tracer.raytracer.Lights.DirectionalLight;

public class Intersection {
    private Point point;
    private double t;
    private Shape shape;
    private Vector normale;

    public Intersection(Point point, double t, Shape shape, Vector normale) {
        this.point = point;
        this.t = t;
        this.shape = shape;
        this.normale = normale;
    }

    public Point getPoint() {
        return point;
    }

    public double getT() {
        return t;
    }

    public Shape getShape() {
        return shape;
    }

    public Vector getNormale() {
        return normale;
    }

    public Color calculateDiffuseColor(AbstractLight light){
        Color diffuse_color = new Color();
        if (light instanceof PointLight) {
        
        } else if (light instanceof DirectionalLight) {
        // Calcul pour DirectionalLight
        }

        return diffuse_color;
    }
/*
TODO : Jalon-4 Étape 2 :
Calculer la diffusion de Lambert ld = max(n ⋅ lightdir, 0) ∗ lightcolor ∗ colordiffuse
Ajouter une méthode :
    - calculateDiffuseColor(AbstractLight light) :
        - Calculer la direction de la lumière depuis le point d’intersection (lightDir).
        - Calculer le produit scalaire entre la normale et lightDir : max(n · lightDir, 0).
        - Multiplier ce scalaire par la couleur de la lumière et la couleur diffuse de l’objet :
        - couleur_diffuse = max(n · lightDir, 0) × couleur_lumière × couleur_diffuse_objet.
        - Retourner couleur_diffuse.
*/ 
}
