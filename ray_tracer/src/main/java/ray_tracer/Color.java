package ray_tracer;

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

    public Color produitDeSchur(Color other){
        if (other == null) {
            throw new IllegalArgumentException("The argument cannot be null.");
        }
        return new Color(this.x * other.x, this.y * other.y, this.z * other.z);
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
        return Double.compare(other.x, x) == 0 && Double.compare(other.y, y) == 0 && Double.compare(other.z, z) == 0;
    }
}
