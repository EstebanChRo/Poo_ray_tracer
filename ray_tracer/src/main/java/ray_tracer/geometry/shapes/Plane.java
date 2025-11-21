package ray_tracer.geometry.shapes;

import java.util.Vector;

import ray_tracer.geometry.Point;
import ray_tracer.imaging.Color;

public class Plane extends Shape {
    private Point point;
    private Vector normal;
    
    public Plane(Color diffuse, Color specular, Point point, Vector normal) {
        super(diffuse, specular);
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

    
}
