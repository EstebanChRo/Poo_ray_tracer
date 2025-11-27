package ray_tracer;

import ray_tracer.parsing.Scene;
import ray_tracer.parsing.SceneFileParser;

public class main {
    public static void main() {
        SceneFileParser SceneFileParser = new SceneFileParser();
        SceneFileParser.parse("ray_tracer/src/main/scenes/jalon2/test1.scene");
        Scene scene = SceneFileParser.getScene();
        System.out.println("Scene parsed with width: " + scene.getWidth() + " and height: " + scene.getHeight());
        System.out.println("Output file: " + scene.getOutput());
        System.out.println("Number of lights: " + scene.getLights().size());
        System.out.println("Number of shapes: " + scene.getShapes().size());
        System.out.println("Camera: " + scene.getCamera());
        System.out.println("Ambient color: " + scene.getAmbient());
    }
}
