package ray_tracer.parsing;

import java.util.ArrayList;
import java.util.List;

import ray_tracer.geometry.shapes.Shape;
import ray_tracer.imaging.Color;
import ray_tracer.raytracer.AbstractLight;

public class Scene {
    private int width;
    private int height;
    private Camera camera;
    private String output = "output.png";
    private Color ambient = new Color();

    private List<AbstractLight> lights = new ArrayList<>();
    private List<Shape> shapes = new ArrayList<>();
}