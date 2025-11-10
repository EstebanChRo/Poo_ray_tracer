package ray_tracer;

public class Vector extends AbstractVec3 {

    public Vector(double x, double y, double z){
        super(x,y,z);
    }

    public Vector Addition(AbstractVec3 other){
        Vector v = (Vector) other;
        return new Vector(this.x + v.x, this.y + v.y, this.z + v.z);
    }

    public Vector Soustraction (AbstractVec3 other){
        Vector v = (Vector) other;
        return new Vector(this.x - v.x, this.y - v.y, this.z - v.z);
    }

    public Vector MultiplicationParScalaire(double scalaire){
        return new Vector(this.x * scalaire, this.y * scalaire, this.z * scalaire);
    }
}
