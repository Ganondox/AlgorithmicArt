/**
 * Created by jotbills on 7/2/17.
 */
public abstract class RaisingFunction implements Iterable {



    //input bounds
    abstract public double getMax();
    abstract  public double getMin();

    //iteration accuracy parameters, higher values increase accuracy, but increase computational time
    // in effect, precision increases the range, while depth increases the domain of iteration
    // precision is more important than depth, but a depth of at least 1 is required for non-integer results
    abstract public int getPrecision();
    abstract  public int getDepth();

    //output must be greater than or equal input unless lowering, in which it must be less or equal
    abstract boolean lowering();

    //executes faster than the map can be referenced
    abstract boolean fast();

    @Override
    public Iterable iterate(double power) {
        return new Iterated(this, power, getPrecision(), getDepth());
    }


}
