package ray_tracer.raytracer.Lights;

import ray_tracer.geometry.Point;
import ray_tracer.imaging.Color;

public class PointLight extends AbstractLight {
    private Point origin;

    public PointLight(Color color, Point origin) {
        super(color);
        this.origin = origin;
    }

    public Point getOrigin() {
        return origin;
    }
    public void setOrigin(Point origin) {
        this.origin = origin;
    }

    public Color getColor() {
        return color;
    }
    public void setColor(Color color) {
        this.color = color;
    }
}
