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

    public Scene() {
        // Valeurs pas défaut
        this.width = 0;
        this.height = 0;
        this.output = "output.png";
        this.ambient = new Color(0, 0, 0);
        this.lights = new ArrayList<>();
        this.shapes = new ArrayList<>();
        this.camera = null;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Camera getCamera() {
        return camera;
    }

    public void setCamera(Camera camera) {
        this.camera = camera;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public Color getAmbient() {
        return ambient;
    }

    public void setAmbient(Color ambient) {
        this.ambient = ambient;
    }

    public List<AbstractLight> getLights() {
        return lights;
    }

    public List<Shape> getShapes() {
        return shapes;
    }

    public void setSize(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void addLight(AbstractLight light) {
        this.lights.add(light);
    }

    public void addShape(Shape shape) {
        this.shapes.add(shape);
    }
}