package ray_tracer;

public class Point extends AbstractVec3 {

    public Point(double x, double y, double z){
        super(x,y,z);
    }

    public Point add(AbstractVec3 other){
        if (!(other instanceof Vector)) {
            throw new IllegalArgumentException("A Point can only be added to a Vector (translation).");
        }
        Vector v = (Vector) other;
        return new Point(this.x + v.x, this.y + v.y, this.z + v.z);
    }

    public Point subtract (AbstractVec3 other){
        if (!(other instanceof Point)) {
            throw new IllegalArgumentException("A Point can only be subtracted from another Point.");
        }
        Point p = (Point) other;
        return new Point(this.x - p.x, this.y - p.y, this.z - p.z);
    }

    public Point multiplyByScalar(double scalar){
        return new Point(this.x * scalar, this.y * scalar, this.z * scalar);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Point other = (Point) obj;
        return almostEqual(other.x, x) && almostEqual(other.y, y) && almostEqual(other.z, z);
    }
}
