package ray_tracer.geometry.shapes;

import java.util.Optional;

import ray_tracer.geometry.Point;
import ray_tracer.geometry.Vector;
import ray_tracer.imaging.Color;
import ray_tracer.raytracer.Intersection;
import ray_tracer.raytracer.Ray;

public class Sphere extends Shape {
    private Point center;
    private double radius;
    
    public Sphere(Color diffuse, Color specular,Color shininess, Point center, double radius) {
        super(diffuse, specular, shininess);
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



    public Optional<Intersection> intersect(Ray ray){
        Point o = ray.getOrigin();
        Vector d = ray.getDirection();

        double a = d.dotProduct(d);
        double b = o.subtract(this.center).dotProduct(d)*2;
        double c = o.subtract(this.center).dotProduct(o.subtract(this.center)) - this.radius*this.radius;
        double delta = b * b - 4 * a * c;

        if (delta < 0) {
            return Optional.empty();
        }

        else if (delta == 0) {
            double t = -b / (2*a);
            Point intersection_point = o.add(d.multiplyByScalar(t));
            Vector normal = intersection_point.subtract(this.center).normalize();
            Intersection intersection = new Intersection(intersection_point, t, this, normal);
            return Optional.of(intersection);
        }

        else {
            double sqrtDiscriminant = Math.sqrt(delta);
            double t1 = (-b + sqrtDiscriminant) / (2 * a);
            double t2 = (-b - sqrtDiscriminant) / (2 * a);
            double t = Math.min(t1, t2);
            if (t < 0) {
                t = Math.max(t1, t2);
                if (t < 0) {
                    return Optional.empty();
                }
            }
            Point intersection_point = o.add(d.multiplyByScalar(t));
            Vector normal = intersection_point.subtract(this.center).normalize();
            Intersection intersection = new Intersection(intersection_point, t, this,normal);
            return Optional.of(intersection);
        }
    }
}