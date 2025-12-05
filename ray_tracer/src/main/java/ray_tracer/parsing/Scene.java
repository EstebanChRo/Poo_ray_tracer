package ray_tracer.parsing;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import ray_tracer.geometry.shapes.Shape;
import ray_tracer.geometry.shapes.Sphere;
import ray_tracer.imaging.Color;
import ray_tracer.raytracer.Intersection;
import ray_tracer.raytracer.Ray;
import ray_tracer.raytracer.Lights.AbstractLight;

public class Scene {
    private int width;
    private int height;
    private Camera camera;
    private String output = "output.png";
    private Color ambient = new Color();

    private List<AbstractLight> lights = new ArrayList<>();
    private List<Shape> shapes = new ArrayList<>();

    public Scene() {
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



    //TODO : Jalon-6 Ajouter les autres shapes


    // TODO : Jalon-4 : Étape 3 : S’assurer que l’Intersection retournée contient bien la normale.
    public Optional<Intersection> findClosestIntersection(Ray ray) {
        Optional<Intersection> closestIntersection = Optional.empty();
        double minT = Double.POSITIVE_INFINITY;

        for (Shape shape : shapes) {
            if (shape instanceof Sphere) {
                Sphere sphere = (Sphere) shape;
                Optional<Intersection> currentIntersection = sphere.intersect(ray);

                if (currentIntersection.isPresent()) {
                    Intersection intersection = currentIntersection.get();
                    if (intersection.getT() < minT && intersection.getT() > 0) {
                        minT = intersection.getT();
                        closestIntersection = currentIntersection;
                    }
                }
            }
        }
        return closestIntersection;
    }
}