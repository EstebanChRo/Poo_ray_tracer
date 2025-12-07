package ray_tracer.parsing;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import ray_tracer.geometry.Point;
import ray_tracer.geometry.Vector;
import ray_tracer.geometry.shapes.Shape;
import ray_tracer.geometry.shapes.Sphere;
import ray_tracer.imaging.Color;
import ray_tracer.raytracer.Intersection;
import ray_tracer.raytracer.Ray;
import ray_tracer.raytracer.Lights.AbstractLight;
import ray_tracer.raytracer.Lights.PointLight;
import ray_tracer.raytracer.Lights.DirectionalLight;

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

    public Color calculatePhongIllumination(Intersection intersection, AbstractLight light, Vector eyeDir){
        Vector lightdir;
        if (light instanceof PointLight) {
            PointLight point_light = (PointLight) light;
            lightdir = point_light.getOrigin().subtract(intersection.getPoint()).normalize();
        } else if (light instanceof DirectionalLight) {
            DirectionalLight directional_light = (DirectionalLight) light;
            lightdir = directional_light.getDirection().normalize();
        } else {
            throw new IllegalArgumentException("Type de lumière non supporté : " + light.getClass());
        }

        Vector h = lightdir.add(eyeDir).normalize();
        double nDotH = intersection.getnormal().dotProduct(h);
        nDotH = Math.max(nDotH, 0);
        double shininess = intersection.getShape().getShininess();
        double specularFactor = Math.pow(nDotH, shininess);
        Color phongColor = light.getColor().multiply(intersection.getShape().getSpecular()).multiplyByScalar(specularFactor);
        
        return phongColor;
    }

    public Color calculateFinalColor(Intersection intersection, Vector eyeDir) {
        Color finalColor = this.ambient;

        for (AbstractLight light : this.lights) {
            Vector lightDir;
            if (light instanceof PointLight) {
                PointLight pointLight = (PointLight) light;
                lightDir = pointLight.getOrigin().subtract(intersection.getPoint()).normalize();
            } else if (light instanceof DirectionalLight) {
                DirectionalLight directionalLight = (DirectionalLight) light;
                lightDir = directionalLight.getDirection().normalize().multiplyByScalar(-1);
            } else {
                throw new IllegalArgumentException("Type de lumière non supporté : " + light.getClass());
            }

            Ray shadowRay = new Ray(intersection.getPoint(), lightDir);
            Optional<Intersection> shadowIntersection = findClosestIntersection(shadowRay);
            if (!shadowIntersection.isPresent() || shadowIntersection.get().getT() <= 1e-9) {
                Color diffuseColor = intersection.calculateDiffuseColor(light);
                Color specularColor = calculatePhongIllumination(intersection, light, eyeDir);
                finalColor = finalColor.add(diffuseColor).add(specularColor);
            }
        }
        return finalColor;
    }
}