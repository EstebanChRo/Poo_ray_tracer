package ray_tracer;

public class Color extends AbstractVec3 {

    public Color(double x, double y, double z){
        super(x,y,z);
    }

    public Color addition(AbstractVec3 other){
        Color v = (Color) other;
        return new Color(this.x + v.x, this.y + v.y, this.z + v.z);
    }

    public Color subtraction (AbstractVec3 other){
        Color v = (Color) other;
        return new Color(this.x - v.x, this.y - v.y, this.z - v.z);
    }

    public Color multiplicationByScalar(double scalar){
        return new Color(this.x * scalar, this.y * scalar, this.z * scalar);
    }

    public Color produitDeSchur(Color other){
        return new Color(this.x * other.x, this.y * other.y, this.z * other.z);
    }

}
