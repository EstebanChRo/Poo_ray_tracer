package ray_tracer.parsing;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ray_tracer.geometry.Point;
import ray_tracer.geometry.Vector;
import ray_tracer.geometry.shapes.Sphere;
import ray_tracer.geometry.shapes.Triangle;
import ray_tracer.imaging.Color;
import ray_tracer.raytracer.PointLight;
import ray_tracer.raytracer.directionalLight;

public class SceneFileParser {
    private Scene scene;
    private Color currentDiffuse;
    private Color currentSpecular;
    private List<Point> vertices = new ArrayList<>();

    public Scene parse(String filePath) {
        Scene scene = new Scene();
        try (FileReader file = new FileReader(filePath)){
            BufferedReader br = new BufferedReader(file);
            String line;
            while ((line = br.readLine())!= null){
                line.trim();
                if (line.isEmpty() || line.startsWith("#")){
                    continue;
                }
                line.split(" ");
                // TODO : implémenter switch case avec méthodes pour chaque type de ligne

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return scene;
    }


    // TODO : Méthodes de parsing pour chaque type de ligne, ajouter les vérifications nécessaires
    private void parseSize(String[] line){
        int width = Integer.parseInt(line[1]);
        int height = Integer.parseInt(line[2]);
        scene.setWidth(width);
        scene.setHeight(height);
    }

    private void parseOutput(String[] line){
        String output = line[1];
        scene.setOutput(output);
    }

    private void parseCamera(String[] line){
        double lookFromX = Double.parseDouble(line[1]);
        double lookFromY = Double.parseDouble(line[2]);
        double lookFromZ = Double.parseDouble(line[3]);
        Point lookFrom = new Point(lookFromX, lookFromY, lookFromZ);

        double lookAtU = Double.parseDouble(line[4]);
        double lookAtV = Double.parseDouble(line[5]);
        double lookAtW = Double.parseDouble(line[6]);
        Point lookAt = new Point(lookAtU, lookAtV, lookAtW);

        double upM = Double.parseDouble(line[7]);
        double upN = Double.parseDouble(line[8]);
        double upO = Double.parseDouble(line[9]);
        Vector up = new Vector(upM, upN, upO);

        double fov = Double.parseDouble(line[10]);

        Camera camera = new Camera(lookFrom, lookAt, up, fov);
        scene.setCamera(camera);
    }

    private void parseAmbient(String[] line){
        double ambientR = Double.parseDouble(line[1]);
        double ambientG = Double.parseDouble(line[2]);
        double ambientB = Double.parseDouble(line[3]);
        Color ambient = new Color(ambientR, ambientG, ambientB);
        
        scene.setAmbient(ambient);
    }

    private void parseDirectionalLight(String[] line){
        double directionX = Double.parseDouble(line[1]);
        double directionY = Double.parseDouble(line[2]);
        double directionZ = Double.parseDouble(line[3]);
        Vector direction = new Vector(directionX, directionY, directionZ);

        double colorR = Double.parseDouble(line[4]);
        double colorG = Double.parseDouble(line[5]);
        double colorB = Double.parseDouble(line[6]);
        Color color = new Color(colorR, colorG, colorB);
        directionalLight directionalLight =  new directionalLight(color, direction);

        scene.addLight(directionalLight);
    }

    private void parsePointLight(String[] line){
        double originX = Double.parseDouble(line[1]);
        double originY = Double.parseDouble(line[2]);
        double originZ = Double.parseDouble(line[3]);
        Point origin = new Point(originX, originY, originZ);

        double colorR = Double.parseDouble(line[4]);
        double colorG = Double.parseDouble(line[5]);
        double colorB = Double.parseDouble(line[6]);
        Color color = new Color(colorR, colorG, colorB);
        PointLight pointLight =  new PointLight(color, origin);

        scene.addLight(pointLight);
    }

    private void parseDiffuse(String[] line){
        double diffuseR = Double.parseDouble(line[1]);
        double diffuseG = Double.parseDouble(line[2]);
        double diffuseB = Double.parseDouble(line[3]);
        Color diffuse = new Color(diffuseR, diffuseG, diffuseB);

        this.currentDiffuse = diffuse;
    }

    private void parseSpecular(String[] line){
        double specularR = Double.parseDouble(line[1]);
        double specularG = Double.parseDouble(line[2]);
        double specularB = Double.parseDouble(line[3]);
        Color specular = new Color(specularR, specularG, specularB);

        this.currentSpecular = specular;
    } 

    private void parseMaxverts(String[] line){
        int maxverts = Integer.parseInt(line[1]);
        // à implémenter plus tard si besoin
    }

    private void parseVertex(String[] line){
        double vertexX = Double.parseDouble(line[1]);
        double vertexY = Double.parseDouble(line[2]);
        double vertexZ = Double.parseDouble(line[3]);
        Point vertex = new Point(vertexX, vertexY, vertexZ);

        this.vertices.add(vertex);
    }

    private void parseTriangle(String[] parts) {
        int a = Integer.parseInt(parts[1]);
        int b = Integer.parseInt(parts[2]);
        int c = Integer.parseInt(parts[3]);
        if (a >= vertices.size() || b >= vertices.size() || c >= vertices.size()) {
            throw new IllegalArgumentException("Indice de sommet invalide.");
        }
        Triangle triangle = new Triangle(currentDiffuse, currentSpecular, vertices.get(a), vertices.get(b), vertices.get(c));
        scene.addShape(triangle);
    }

    private void parseShpere(String[] line){
        double centerX = Double.parseDouble(line[1]);
        double centerY = Double.parseDouble(line[2]);
        double centerZ = Double.parseDouble(line[3]);
        Point center = new Point(centerX, centerY, centerZ);

        double radius = Double.parseDouble(line[4]);
        Sphere sphere = new Sphere(currentDiffuse, currentSpecular, center, radius);
        
        scene.addShape(sphere);
    }
}