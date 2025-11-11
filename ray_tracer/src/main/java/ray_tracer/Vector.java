package ray_tracer;

public class Vector extends AbstractVec3 {

    public Vector(double x, double y, double z){
        super(x,y,z);
    }

    public Vector add(AbstractVec3 other){
        if (!(other instanceof Vector)) {
            throw new IllegalArgumentException("A Vector can only be added to another Vector.");
        }
        Vector v = (Vector) other;
        return new Vector(this.x + v.x, this.y + v.y, this.z + v.z);
    }

    public Vector subtract(AbstractVec3 other){
        if (!(other instanceof Vector)) {
            throw new IllegalArgumentException("A Vector can only be subtracted from another Vector.");
        }
        Vector v = (Vector) other;
        return new Vector(this.x - v.x, this.y - v.y, this.z - v.z);
    }

    public Vector multiplyByScalar(double scalar){
        return new Vector(this.x * scalar, this.y * scalar, this.z * scalar);
    }

    public double dotProduct(Vector other){
        if (other == null) {
            throw new IllegalArgumentException("The argument cannot be null.");
        }
        return this.x * other.x + this.y * other.y + this.z * other.z;
    }

    public Vector crossProduct(Vector other){
        if (other == null) {
            throw new IllegalArgumentException("The argument cannot be null.");
        }
        return new Vector(this.y * other.z - this.z * other.y, this.z * other.x - this.x * other.z , this.x * other.y - this.y * other.x);
    }

    public double length(){
        return Math.sqrt(this.x * this.x + this.y * this.y + this.z * this.z);
    }

    public Vector normalize() {
        double len = this.length();
        if (len == 0.0) {
            throw new ArithmeticException("Cannot normalize a vector with zero length.");
        }
        return new Vector(this.x / len, this.y / len, this.z / len);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Vector other = (Vector) obj;
        return almostEqual(other.x, x) && almostEqual(other.y, y) && almostEqual(other.z, z);
    }
}
