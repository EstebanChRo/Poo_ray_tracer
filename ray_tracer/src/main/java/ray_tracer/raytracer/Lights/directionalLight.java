package ray_tracer.raytracer.Lights;

import ray_tracer.geometry.Vector;
import ray_tracer.imaging.Color;

public class directionalLight extends AbstractLight {
    private Vector direction;

    public directionalLight(Color color, Vector direction) {
        super(color);
        this.direction = direction;
    }

    public Vector getDirection() {
        return direction;
    }
    public void setDirection(Vector direction) {
        this.direction = direction;
    }

    public Color getColor() {
        return color;
    }
    public void setColor(Color color) {
        this.color = color;
    }
}
