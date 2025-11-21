package ray_tracer.geometry.shapes;

import ray_tracer.geometry.Point;
import ray_tracer.imaging.Color;

public class Triangle extends Shape {
    private Point v0;
    private Point v1;
    private Point v2;

    public Triangle(Color diffuse, Color specular, Point v0, Point v1, Point v2) {
        super(diffuse, specular);
        this.v0 = v0;
        this.v1 = v1;
        this.v2 = v2;
    }
    
    public Point getV0() {
        return v0;
    }
    public void setV0(Point v0) {
        this.v0 = v0;
    }
    public Point getV1() {
        return v1;
    }
    public void setV1(Point v1) {
        this.v1 = v1;
    }
    public Point getV2() {
        return v2;
    }
    public void setV2(Point v2) {
        this.v2 = v2;
    }

    
}
