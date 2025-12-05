package ray_tracer.geometry;

import ray_tracer.parsing.Camera;

public class Orthonormal {
    private Vector u;
    private Vector v;
    private Vector w;
    
    public Orthonormal(Camera camera) {
        Point lookFrom = camera.getLookFrom();
        Point lookAt = camera.getLookAt();
        Vector up = camera.getUp();

        if (lookFrom.equals(lookAt)) {
            throw new IllegalArgumentException("lookFrom and lookAt cannot be the same point.");
        }

        this.w = lookFrom.subtract(lookAt).normalize();
        this.u = up.crossProduct(this.w).normalize();
        this.v = w.crossProduct(this.u).normalize();
    }

    public Vector getU() {
        return u;
    }

    public Vector getV() {
        return v;
    }

    public Vector getW() {
        return w;
    }
    
}
