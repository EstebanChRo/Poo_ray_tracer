
package ray_tracer.raytracer;

import ray_tracer.imaging.Color;

public abstract class AbstractLight {
    protected Color color;

    public AbstractLight(Color color) {
        this.color = color;
    }

}
