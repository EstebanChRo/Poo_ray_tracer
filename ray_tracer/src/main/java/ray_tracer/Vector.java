package ray_tracer;

public class Vector extends AbstractVec3 {

    public Vector(double x, double y, double z){
        super(x,y,z);
    }

    public Vector add(AbstractVec3 other){
        Vector v = (Vector) other;
        return new Vector(this.x + v.x, this.y + v.y, this.z + v.z);
    }

    public Vector subtract (AbstractVec3 other){
        Vector v = (Vector) other;
        return new Vector(this.x - v.x, this.y - v.y, this.z - v.z);
    }

    public Vector multiplyByScalar(double scalar){
        return new Vector(this.x * scalar, this.y * scalar, this.z * scalar);
    }

    public double produitScalaire(Vector other){
        return this.x * other.x + this.y * other.y + this.z * other.z;
    }

    public Vector produitVectoriel(Vector other){
        return new Vector(this.y * other.z - this.z * other.y, this.z * other.x - this.x * other.z , this.x + other.y - this.y * other.x);
    }

    public double length(){
        return Math.sqrt(this.x * this.x + this.y * this.y + this.z * this.z);
    }

    public Vector normalize(){
        return new Vector(this.x / length(), this.y / length(), this.z / length());
    }
}
