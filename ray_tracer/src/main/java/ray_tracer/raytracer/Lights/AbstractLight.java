
package ray_tracer.raytracer.Lights;

import ray_tracer.imaging.Color;

public abstract class AbstractLight {
    protected Color color;

    public AbstractLight(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    
}
