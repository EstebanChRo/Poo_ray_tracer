package ray_tracer.imaging;

import ray_tracer.geometry.AbstractVec3;

public class Color extends AbstractVec3 {

    public Color(double x, double y, double z){
        super(
            Math.max(0.0, Math.min(1.0, x)),
            Math.max(0.0, Math.min(1.0, y)),
            Math.max(0.0, Math.min(1.0, z))
        );
    }

    public Color(){
        super(0.0,0.0,0.0);
    }

    public Color add(AbstractVec3 other){
        if (!(other instanceof Color)) {
            throw new IllegalArgumentException("A Color can only be added to another Color.");
        }
        Color v = (Color) other;
        return new Color(
            Math.min(1.0, this.x + v.x),
            Math.min(1.0, this.y + v.y),
            Math.min(1.0, this.z + v.z)
        );
    }

    public Color subtract (AbstractVec3 other){
        if (!(other instanceof Color)) {
            throw new IllegalArgumentException("A Color can only be subtracted from another Color.");
        }
        Color v = (Color) other;
        return new Color(
            Math.max(0.0, this.x - v.x),
            Math.max(0.0, this.y - v.y),
            Math.max(0.0, this.z - v.z)
        );
    }

    public Color multiplyByScalar(double scalar){
        return new Color(
            Math.max(0.0, Math.min(1.0, this.x * scalar)),
            Math.max(0.0, Math.min(1.0, this.y * scalar)),
            Math.max(0.0, Math.min(1.0, this.z * scalar))
        );
    }

    public Color schurProduct(Color other){
        if (other == null) {
            throw new IllegalArgumentException("The argument cannot be null.");
        }
        return new Color(this.x * other.x, this.y * other.y, this.z * other.z);
    }

        public Color multiply(Color other) {
        double newR = this.x * other.x;
        double newG = this.y * other.y;
        double newB = this.z * other.z;
        return new Color(newR, newG, newB);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Color other = (Color) obj;
        return almostEqual(other.x, x) && almostEqual(other.y, y) && almostEqual(other.z, z);
    }

    public int toRGB(){
        int red = (int) Math.round(x * 255);
        int green = (int) Math.round(y * 255);
        int blue = (int) Math.round(z * 255);
        return (
            ((red & 0xff) << 16)
            + ((green & 0xff) << 8)
            + (blue & 0xff));
    }
}
