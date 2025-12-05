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
    private Vector normal;

    public Intersection(Point point, double t, Shape shape, Vector normal) {
        this.point = point;
        this.t = t;
        this.shape = shape;
        this.normal = normal;
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

    public Vector getnormal() {
        return normal;
    }

    public Color calculateDiffuseColor(AbstractLight light){
        Vector lightdir;
        if (light instanceof PointLight) {
            PointLight point_light = (PointLight) light;
            lightdir = point_light.getOrigin().subtract(this.point).normalize();
        } else if (light instanceof DirectionalLight) {
            DirectionalLight directional_light = (DirectionalLight) light;
            lightdir = directional_light.getDirection().normalize();
        } else {
            throw new IllegalArgumentException("Type de lumière non supporté : " + light.getClass());
        }
        return light.getColor().multiply(this.shape.getDiffuse()).multiplyByScalar(Math.max(normal.dotProduct(lightdir), 0));
    }
}
