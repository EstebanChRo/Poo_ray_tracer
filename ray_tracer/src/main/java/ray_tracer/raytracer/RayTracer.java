package ray_tracer.raytracer;


import ray_tracer.geometry.Orthonormal;
import ray_tracer.geometry.Vector;
import ray_tracer.imaging.Color;
import ray_tracer.parsing.Scene;

public class RayTracer {
    public RayTracer() {
    }

    public Vector directionCalcul(int i, int j, Orthonormal basis, Scene scene){
        double fovr = scene.getCamera().getFov()*Math.PI/180;
        double pixelheight = Math.tan(fovr/2);

        double pixelwidth = pixelheight*scene.getWidth()/scene.getHeight();
        double a = (pixelwidth * (i - scene.getWidth()/2.0 + 0.5)) / (scene.getWidth()/2.0);
        double b = (pixelheight * (j - scene.getHeight()/2.0 + 0.5)) / (scene.getHeight()/2.0);

        Vector d = basis.getU().multiplyByScalar(a).add(basis.getV().multiplyByScalar(b)).subtract(basis.getW()).normalize();
        return d;
    }

    public Color getColor(Intersection intersection, Scene scene){
        return scene.getAmbient();
    }
}