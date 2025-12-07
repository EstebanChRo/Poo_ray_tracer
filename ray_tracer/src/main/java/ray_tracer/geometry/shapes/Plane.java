package ray_tracer.geometry.shapes;

import ray_tracer.geometry.Vector;

import ray_tracer.geometry.Point;
import ray_tracer.imaging.Color;

public class Plane extends Shape {
    private Point point;
    private Vector normal;
    private Color diffuse;
    private Color specular;
    
    public Plane(Color diffuse, Color specular,Color shininess, Point point, Vector normal) {
        super(diffuse, specular, shininess);
        this.point = point;
        this.normal = normal;
    }

    public Point getPoint() {
        return point;
    }
    public void setPoint(Point point) {
        this.point = point;
    }
    public Vector getNormal() {
        return normal;
    }
    public void setNormal(Vector normal) {
        this.normal = normal;
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
