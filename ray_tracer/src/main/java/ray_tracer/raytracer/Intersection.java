package ray_tracer.raytracer;

import ray_tracer.geometry.Point;
import ray_tracer.geometry.shapes.Shape;

public class Intersection {
    private Point point;
    private double t;
    private Shape shape;
    public Intersection(Point point, double t, Shape shape) {
        this.point = point;
        this.t = t;
        this.shape = shape;
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
}
