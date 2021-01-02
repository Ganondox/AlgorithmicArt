import javafx.util.Pair;

import java.util.Map;

/**
 * Created by jotbills on 7/2/17.
 */
public class PowerFunction {

    int precision;
    int depth;

    Map< Pair<Pair<Double,Double>, Double>, Double> foundvalues;

    public double xtoy (double x, double y){

       return xtoy(x,y, depth);
    }

    private double xtoy (double x, double y, double depth){

        //base calculations
        if(y == 0) return 1;
        if(y == 1) return x;
        if(x == 0) return 0; //yeah, it's not accurate, but I'm not deal with the undefined
        if(x == 1) return 1;

        //check found values
        Pair key =  new Pair(new Pair(x,y),depth);
        if(foundvalues.containsKey( key )) return foundvalues.get(key);

        //negative powers
        if(y < 0){

            double result = 1/xtoy(x, -y);
            foundvalues.put(key, result);
            return result;
        }

        //integer powers
        if(y % 1 == 0){
            double result = x * xtoy(x, y -1);
            foundvalues.put(key, result);
            return result;
        }

        //fractional powers
        double fractionalPart = 1;
        if(depth > 1) {
            if (y < 1) {


            double lowerBound = 1;
            double upperBound = x;
            double guess = (lowerBound + upperBound)/2;

            for(int i = 0; i < precision; i++) {
                double aim = xtoy(x,1/y, depth);
                if(aim == x) {
                    i = precision;
                } else {
                    if (aim < x) {
                        lowerBound = guess;
                    } else {
                        upperBound = guess;
                    }
                    guess = (lowerBound + upperBound)/2;
                }
            }
            foundvalues.put(key, guess);
            return guess;

            } else {
                fractionalPart = xtoy(x, y % 1, depth);
            }
        }
        double result = fractionalPart * xtoy(x ,y - (y % 1), depth - 1);
        foundvalues.put(key, result);
        return result;


    }
}
