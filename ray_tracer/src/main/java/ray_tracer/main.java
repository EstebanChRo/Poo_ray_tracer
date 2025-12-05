package ray_tracer;

import java.awt.image.BufferedImage;
import java.io.IOException;

import ray_tracer.parsing.Scene;
import ray_tracer.parsing.SceneFileParser;
import ray_tracer.raytracer.ImageRenderer;
import ray_tracer.raytracer.ImageWriter;

public class main {
    public static void main(String[] args) throws IOException {
        Scene scene = new SceneFileParser().parse("ray_tracer/src/main/scenes/jalon3/tp3.test");
        ImageRenderer renderer = new ImageRenderer();
        BufferedImage image = renderer.render(scene);
        ImageWriter writer = new ImageWriter();
        writer.write(image, scene.getOutput());
    }
}