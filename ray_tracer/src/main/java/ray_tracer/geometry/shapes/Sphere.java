package ray_tracer.geometry.shapes;

import ray_tracer.geometry.Point;
import ray_tracer.imaging.Color;

public class Sphere extends Shape {
    private Point center;
    private double radius;
    
    public Sphere(Color diffuse, Color specular, Point center, double radius) {
        super(diffuse, specular);
        this.center = center;
        this.radius = radius;
    }
    public Point getCenter() {
        return center;
    }
    public void setCenter(Point center) {
        this.center = center;
    }
    public double getRadius() {
        return radius;
    }
    public void setRadius(double radius) {
        this.radius = radius;
    }

    public Color getDiffuse() {
        return diffuse;
    }
    public void setDiffuse(Color diffuse) {
        this.diffuse = diffuse;
    }
    public Color getSpecular() {
        return specular;
    }
    public void setSpecular(Color specular) {
        this.specular = specular;
    }
}
