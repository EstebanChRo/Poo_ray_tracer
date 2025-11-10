package ray_tracer;

public class Point extends AbstractVec3 {

    public Point(double x, double y, double z){
        super(x,y,z);
    }

    public Point addition(AbstractVec3 other){
        Point v = (Point) other;
        return new Point(this.x + v.x, this.y + v.y, this.z + v.z);
    }

    public Point subtraction (AbstractVec3 other){
        Point v = (Point) other;
        return new Point(this.x - v.x, this.y - v.y, this.z - v.z);
    }

    public Point multiplicationByScalar(double scalar){
        return new Point(this.x * scalar, this.y * scalar, this.z * scalar);
    }
}
