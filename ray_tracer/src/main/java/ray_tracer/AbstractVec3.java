package ray_tracer;

public abstract class AbstractVec3 {
    protected double x;
    protected double y;
    protected double z;
    
    public AbstractVec3(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public abstract AbstractVec3 Addition(AbstractVec3 other);

    public abstract AbstractVec3 Soustraction(AbstractVec3 other);

    public abstract AbstractVec3 MultiplicationParScalaire(double scalaire);

}
