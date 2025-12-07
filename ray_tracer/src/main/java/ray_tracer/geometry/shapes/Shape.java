package ray_tracer.geometry.shapes;

import ray_tracer.imaging.Color;

public abstract class Shape {
    protected Color diffuse;
    protected Color specular;
    protected Color shininess;

    public Shape(Color diffuse, Color specular, Color shininess) {
        this.diffuse = diffuse;
        this.specular = specular;
        this.shininess = shininess;
    }

    public Color getDiffuse() {
        return diffuse;
    }

    public Color getSpecular() {
        return specular;
    }

    public Color getShininess() {
        return shininess;
    }

    //TODO : Jalon-6 Implémenter les méthodes intersect (avec vecteur normal) à toutes les classes shapes.
}
