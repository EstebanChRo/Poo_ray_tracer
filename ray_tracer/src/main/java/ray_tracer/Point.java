package ray_tracer;

public class Point extends AbstractVec3 {

    public Point(double x, double y, double z){
        super(x,y,z);
    }

    public Point addition(AbstractVec3 other){
        Point v = (Point) other;
        return new Point(this.x + v.x, this.y + v.y, this.z + v.z);
    }

    public Point soustraction (AbstractVec3 other){
        Point v = (Point) other;
        return new Point(this.x - v.x, this.y - v.y, this.z - v.z);
    }

    public Point multiplicationParScalaire(double scalaire){
        return new Point(this.x * scalaire, this.y * scalaire, this.z * scalaire);
    }
}
