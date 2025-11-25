package ray_tracer.geometry.shapes;

import ray_tracer.imaging.Color;

public abstract class Shape {
    protected Color diffuse;
    protected Color specular;

    public Shape(Color diffuse, Color specular) {
        this.diffuse = diffuse;
        this.specular = specular;
    }
}
