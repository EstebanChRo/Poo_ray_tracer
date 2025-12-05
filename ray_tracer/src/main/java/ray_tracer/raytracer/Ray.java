package ray_tracer.raytracer;


import ray_tracer.geometry.Point;
import ray_tracer.geometry.Vector;

public class Ray {
    private Point origin;
    private Vector direction;
    public Ray(Point origin, Vector direction) {
        this.origin = origin;
        this.direction = direction;
    }

    public Point getOrigin() {
        return origin;
    }
    
    public Vector getDirection() {
        return direction;
    }

}
