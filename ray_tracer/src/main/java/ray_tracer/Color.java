package ray_tracer;

public class Color extends AbstractVec3 {

    public Color(double x, double y, double z){
        super(x,y,z);
    }

    public Color addition(AbstractVec3 other){
        Color v = (Color) other;
        return new Color(this.x + v.x, this.y + v.y, this.z + v.z);
    }

    public Color soustraction (AbstractVec3 other){
        Color v = (Color) other;
        return new Color(this.x - v.x, this.y - v.y, this.z - v.z);
    }

    public Color multiplicationParScalaire(double scalaire){
        return new Color(this.x * scalaire, this.y * scalaire, this.z * scalaire);
    }
}
