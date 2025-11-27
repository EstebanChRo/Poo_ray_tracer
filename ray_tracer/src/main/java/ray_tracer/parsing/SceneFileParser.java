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

    public SceneFileParser() {
        this.scene = new Scene();
        this.currentDiffuse = new Color(0, 0, 0);
        this.currentSpecular = new Color(0, 0, 0);
        this.vertices = new ArrayList<>();
    }

    public Scene getScene() {
        return scene;
    }

    public Color getCurrentDiffuse() {
        return currentDiffuse;
    }

    public Color getCurrentSpecular() {
        return currentSpecular;
    }

    public List<Point> getVertices() {
        return vertices;
    }

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
                String[] parts = line.split("\\s+");
                switch (parts[0].toLowerCase()) {
                    case "size":
                        parseSize(parts);
                        break;
                    case "output":
                        parseOutput(parts);
                        break;
                    case "camera":
                        parseCamera(parts);
                        break;
                    case "ambient":
                        parseAmbient(parts);
                        break;
                    case "directional":
                        parseDirectionalLight(parts);
                        break;
                    case "point":
                        parsePointLight(parts);
                        break;
                    case "diffuse":
                        parseDiffuse(parts);
                        break;
                    case "specular":
                        parseSpecular(parts);
                        break;
                    case "maxverts":
                        break;
                    case "vertex":
                        parseVertex(parts);
                        break;
                    case "tri":
                        parseTriangle(parts);
                        break;
                    case "sphere":
                        parseShpere(parts);
                        break;
                    default:
                        throw new IllegalArgumentException("Ligne inconnue dans le fichier de scène: " + parts[0]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return scene;
    }


    private void parseSize(String[] line){
        if (line.length < 3) {
            throw new IllegalArgumentException("Format invalide pour 'size'. Attendu : size <width> <height>");
        }
        int width = Integer.parseInt(line[1]);
        int height = Integer.parseInt(line[2]);
        if (width <= 0 || height <= 0) {
            throw new IllegalArgumentException("La largeur et la hauteur doivent être des entiers positifs.");
        }
        scene.setWidth(width);
        scene.setHeight(height);
    }

    private void parseOutput(String[] line){
        if (line.length < 2) {
            throw new IllegalArgumentException("Format invalide pour 'output'. Attendu : outpout <output.png>");
        }
        String output = line[1];
        scene.setOutput(output);
    }

    private void parseCamera(String[] line){
        if (line.length < 11) {
            throw new IllegalArgumentException("Format invalide pour 'camera'. Attendu : camera <x> <y> <z> <u> <v> <w> <m> <n> <o> <fov>");
        }
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
        if (fov <= 0 || fov >= 180) {
            throw new IllegalArgumentException("Le champ de vision (fov) doit être compris entre 0 et 180 degrés.");
        }
        Camera camera = new Camera(lookFrom, lookAt, up, fov);
        scene.setCamera(camera);
    }

    private void parseAmbient(String[] line){
        if (line.length < 4) {
            throw new IllegalArgumentException("Format invalide pour 'ambient'. Attendu : ambient <r> <g> <b>");
        }
        double ambientR = Double.parseDouble(line[1]);
        double ambientG = Double.parseDouble(line[2]);
        double ambientB = Double.parseDouble(line[3]);
        if (ambientR < 0 || ambientR > 1 || ambientG < 0 || ambientG > 1 || ambientB < 0 || ambientB > 1) {
            throw new IllegalArgumentException("Les composantes RGB de la couleur ambiante doivent être comprises entre 0 et 1.");
        }
        Color ambient = new Color(ambientR, ambientG, ambientB);
        scene.setAmbient(ambient);
    }

    private void parseDirectionalLight(String[] line){
        if (line.length < 7) {
            throw new IllegalArgumentException("Format invalide pour 'directional'. Attendu : directional <x> <y> <z> <r> <g> <b>");
        }
        double directionX = Double.parseDouble(line[1]);
        double directionY = Double.parseDouble(line[2]);
        double directionZ = Double.parseDouble(line[3]);
        Vector direction = new Vector(directionX, directionY, directionZ);
        double colorR = Double.parseDouble(line[4]);
        double colorG = Double.parseDouble(line[5]);
        double colorB = Double.parseDouble(line[6]);
        if (colorR < 0 || colorR > 1 || colorG < 0 || colorG > 1 || colorB < 0 || colorB > 1) {
            throw new IllegalArgumentException("Les composantes RGB de la lumière directionnelle doivent être comprises entre 0 et 1.");
        }
        Color color = new Color(colorR, colorG, colorB);
        directionalLight directionalLight =  new directionalLight(color, direction);
        scene.addLight(directionalLight);
    }

    private void parsePointLight(String[] line){
        if (line.length < 7) {
            throw new IllegalArgumentException("Format invalide pour 'point'. Attendu : point <x> <y> <z> <r> <g> <b>");
        }
        double originX = Double.parseDouble(line[1]);
        double originY = Double.parseDouble(line[2]);
        double originZ = Double.parseDouble(line[3]);
        Point origin = new Point(originX, originY, originZ);
        double colorR = Double.parseDouble(line[4]);
        double colorG = Double.parseDouble(line[5]);
        double colorB = Double.parseDouble(line[6]);
        if (colorR < 0 || colorR > 1 || colorG < 0 || colorG > 1 || colorB < 0 || colorB > 1) {
            throw new IllegalArgumentException("Les composantes RGB de la lumière ponctuelle doivent être comprises entre 0 et 1.");
        }
        Color color = new Color(colorR, colorG, colorB);
        PointLight pointLight =  new PointLight(color, origin);
        scene.addLight(pointLight);
    }

    private void parseDiffuse(String[] line){
        if (line.length < 4) {
            throw new IllegalArgumentException("Format invalide pour 'diffuse'. Attendu : diffuse <r> <g> <b>");
        }
        double diffuseR = Double.parseDouble(line[1]);
        double diffuseG = Double.parseDouble(line[2]);
        double diffuseB = Double.parseDouble(line[3]);
        if (diffuseR < 0 || diffuseR > 1 || diffuseG < 0 || diffuseG > 1 || diffuseB < 0 || diffuseB > 1) {
            throw new IllegalArgumentException("Les composantes RGB de la couleur diffuse doivent être comprises entre 0 et 1.");
        }
        Color diffuse = new Color(diffuseR, diffuseG, diffuseB);
        this.currentDiffuse = diffuse;
    }

    private void parseSpecular(String[] line){
        if (line.length < 4) {
            throw new IllegalArgumentException("Format invalide pour 'specular'. Attendu : specular <r> <g> <b>");
        }
        double specularR = Double.parseDouble(line[1]);
        double specularG = Double.parseDouble(line[2]);
        double specularB = Double.parseDouble(line[3]);
        if (specularR < 0 || specularR > 1 || specularG < 0 || specularG > 1 || specularB < 0 || specularB > 1) {
            throw new IllegalArgumentException("Les composantes RGB de la couleur spéculaire doivent être comprises entre 0 et 1.");
        }
        Color specular = new Color(specularR, specularG, specularB);
        this.currentSpecular = specular;
    } 

    private void parseMaxverts(String[] line){
        if (line.length < 2) {
            throw new IllegalArgumentException("Format invalide pour 'maxverts'. Attendu : maxverts <nombre>");
        }
        int maxverts = Integer.parseInt(line[1]);
        // à implémenter plus tard si besoin
    }

    private void parseVertex(String[] line){
        if (line.length < 4) {
            throw new IllegalArgumentException("Format invalide pour 'vertex'. Attendu : vertex <x> <y> <z>");
        }
        double vertexX = Double.parseDouble(line[1]);
        double vertexY = Double.parseDouble(line[2]);
        double vertexZ = Double.parseDouble(line[3]);
        Point vertex = new Point(vertexX, vertexY, vertexZ);
        this.vertices.add(vertex);
    }

    private void parseTriangle(String[] parts) {
        if (parts.length < 4) {
            throw new IllegalArgumentException("Format invalide pour 'tri'. Attendu : tri <a> <b> <c>");
        }
        int a = Integer.parseInt(parts[1]);
        int b = Integer.parseInt(parts[2]);
        int c = Integer.parseInt(parts[3]);
        Triangle triangle = new Triangle(currentDiffuse, currentSpecular, vertices.get(a), vertices.get(b), vertices.get(c));
        scene.addShape(triangle);
    }

    private void parseShpere(String[] line){
        if (line.length < 5) {
            throw new IllegalArgumentException("Format invalide pour 'sphere'. Attendu : sphere <x> <y> <z> <radius>");
        }
        double centerX = Double.parseDouble(line[1]);
        double centerY = Double.parseDouble(line[2]);
        double centerZ = Double.parseDouble(line[3]);
        Point center = new Point(centerX, centerY, centerZ);
        double radius = Double.parseDouble(line[4]);
        if (radius <= 0) {
            throw new IllegalArgumentException("Le rayon de la sphère doit être positif.");
        }
        Sphere sphere = new Sphere(currentDiffuse, currentSpecular, center, radius);
        scene.addShape(sphere);
    }
}