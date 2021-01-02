import javafx.util.Pair;

import java.util.Map;

/**
 * Created by jotbills on 7/2/17.
 */
public class Iterated extends RaisingFunction {

    RaisingFunction base;
    double power;
    int precision;
    int depth;
    boolean lowering;

    double min;
    double max;
    boolean calculatedBounds;

    Map< Pair<Pair<Double,Double>, Double>, Double> foundvalues;

    @Override
    public int getPrecision() {
        return precision;
    }

    @Override
    public int getDepth() {
        return depth;
    }

    public Iterated(RaisingFunction base, double power, int precision, int depth) {
        this.base = base;
        this.power = power;
        this.precision = precision;
        this.depth = depth;

        if( power < 0){
            lowering = !base.lowering();
        } else lowering = base.lowering();
    }

    @Override
    boolean lowering() {
        return lowering;
    }

    @Override
    boolean fast() {
        return false;
    }

    @Override
    public double execute(double input) {
        return iterate(input, power, depth);
    }

    private double iterate(double value, double power, int depth){

        //easy calculations
        if(power == 0) return value;
       if(base.fast()) if(power == 1) return base.execute(value);

        //bounds
        if(lowering && value < getMin()) return iterate(getMin(), power , depth);
        if(!lowering && value > getMax()) return iterate(getMax(), power, depth);

        //found values
        Pair key =  new Pair(new Pair(value,power),depth);
        if(foundvalues.containsKey( key )) return foundvalues.get(key);

        //negative iteration
        if(power < 0 ){
            double lowerBound = base.getMin();
            double upperBound = base.getMax();
            if(lowering){
                lowerBound = value;
            } else {
                upperBound = value;
            }
            double guess = (lowerBound + upperBound)/2;
            for(int i = 0; i < precision; i++) {
                double aim = iterate(guess, -power, depth);
                if(aim == value) {
                    i = precision;
                } else {
                    if (aim < value) {
                        lowerBound = guess;
                    } else {
                        upperBound = guess;
                    }
                    guess = (lowerBound + upperBound)/2;
                }
            }
            foundvalues.put(key, guess);
            return guess;
        }

        //integer iteration
        if(power % 1 == 0){
            double result = base.execute(iterate(value, power - 1, depth));
            foundvalues.put(key, result);
            return result;
        }

        //fractional iteration
            double fractionalPart = 0;
        if(depth > 1) {
            if (value < 1) {

                //dammit, it's impossible, only way to do is by having it predefined for a given function
                //need to make power function calculation based on this algorithm as that's what it was originally for

           /* double lowerBound = base.getMin();
            double upperBound = base.getMax();
            if(lowering){
                lowerBound = value;
            } else {
                upperBound = value;
            }
            double guess = (lowerBound + upperBound)/2;
            Iterated op = (Iterated) base.iterate(1/power);
            for(int i = 0; i < precision; i++) {
                double aim = op.execute(value);
                if(aim == value) {
                    i = precision;
                } else {
                    if (aim < value) {
                        lowerBound = guess;
                    } else {
                        upperBound = guess;
                    }
                    guess = (lowerBound + upperBound)/2;
                }
            }
            foundvalues.put(key, guess);
            return guess;
            */
            } else {
                fractionalPart = iterate(value, power % 1, depth);
            }
        }
        double result = iterate(fractionalPart, power - (power % 1), depth - 1);
        foundvalues.put(key, result);
        return result;

    }


    @Override
    public double getMax() {

        if(!calculatedBounds) {
            double MAX = base.execute(base.getMax());
            double MIN = base.execute(base.getMin());

            max = iterate(MAX, -power, depth);
            min = iterate(MIN, -power, depth);
            calculatedBounds = true;
        }
        return max;

    }

    @Override
    public double getMin() {

        if(!calculatedBounds) {
            double MAX = base.execute(base.getMax());
            double MIN = base.execute(base.getMin());

            max = iterate(MAX, -power, depth);
            min = iterate(MIN, -power, depth);
            calculatedBounds = true;
        }
        return min;
    }

    @Override
    public Iterable iterate(double power){

        return new Iterated(base, this.power * power, precision, depth);

    }

    public Map<Pair<Pair<Double, Double>, Double>, Double> getFoundvalues() {
        return foundvalues;
    }
}
