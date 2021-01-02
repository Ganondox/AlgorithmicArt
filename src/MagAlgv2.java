/**
 * Created with IntelliJ IDEA.
 * User: 36483
 * Date: 1/4/14
 * Time: 9:07 PM
 * To change this template use File | Settings | File Templates.
 */
public class MagAlgv2 {

    //one to one map between vectors in R1, R2, and R3 using iterated 3x3 partitions, made for ints, but may be extended to doubles in future

    public static int N2toN1(int x, int y, int magnitude, int color){
        //Magnitude, and color are all used to describe the area being partitioned,
        // magnitude is the number of partitions, color describes how it's partitioned

        //defining colors
        int red = 1;
        //1 = red, 1-2-3 , red   , blue , red
        //         6-5-4   yellow, green, yellow
        //         7-8-9   red   , blue , red
        int blue = 2;
        //2 = blue,  7-8-9 , blue , red   , blue
        //           6-5-4   green, yellow, green
        //           1-2-3   blue , red   , blue
        int green = 3;
        //3 = green, 9-8-7 ,  green, yellow, green
        //           4-5-6    blue , red   , blue
        //           3-2-1    green, yellow, green
        int yellow = 4;
        //4 = yellow, 3-2-1 ,  yellow, green, yellow
        //            4-5-6    red   , blue , red
        //            9-8-7    yellow, green, yellow

        //setting scale from magnitude
        int scale1d = (int) Math.pow(3, magnitude - 1);
        int scale2d = (int) Math.pow(9, magnitude - 1);


        int sum = 0;

        //making sure in bounds
        if( x < 0 || x >= scale1d * 3){
            throw new IndexOutOfBoundsException();
        }

        if( y < 0 || y >= scale1d * 3){
            throw new IndexOutOfBoundsException();
        }

        if(magnitude < 1){
            throw new IndexOutOfBoundsException();
        }

        //summarizing location of point in bounds
        int xband = 0;
        int yband = 0;
        if( x < scale1d){
            xband = 1;
        } else if( x < scale1d * 2){
            xband = 2;
        } else xband = 3;
        if( y < scale1d){
            yband = 1;
        } else if( y < scale1d * 2){
            yband = 2;
        } else yband = 3;

        int numberpad = 0;
        //1-2-3
        //4-5-6
        //7-8-9
        numberpad = (3 * (yband - 1)) + xband;

        //finding data from color
        int newColor = 0;
        int order = 0;

        //find new color
        if(yband == 2){
            if(xband == 2){
                newColor = ((color + 1) % 4) + 1;
            } else newColor = ((color + 2) % 4) + 1;
        } else if(xband == 2){
            newColor = (color % 4) + 1;
        } else newColor = color;

        //find order
        switch(color){
            case 1:
                switch(numberpad){
                    case 1:
                        order = 1;
                        break;
                    case 2:
                        order = 2;
                        break;
                    case 3:
                        order = 3;
                        break;
                    case 4:
                        order = 6;
                        break;
                    case 5:
                        order = 5;
                        break;
                    case 6:
                        order = 4;
                        break;
                    case 7:
                        order = 7;
                        break;
                    case 8:
                        order = 8;
                        break;
                    case 9:
                        order = 9;
                        break;

                }
                break;
            case 2:
                switch(numberpad){
                    case 1:
                        order = 7;
                        break;
                    case 2:
                        order = 8;
                        break;
                    case 3:
                        order = 9;
                        break;
                    case 4:
                        order = 6;
                        break;
                    case 5:
                        order = 5;
                        break;
                    case 6:
                        order = 4;
                        break;
                    case 7:
                        order = 1;
                        break;
                    case 8:
                        order = 2;
                        break;
                    case 9:
                        order = 3;
                        break;

                }
                break;
            case 3:
                switch(numberpad){
                    case 1:
                        order = 9;
                        break;
                    case 2:
                        order = 8;
                        break;
                    case 3:
                        order = 7;
                        break;
                    case 4:
                        order = 4;
                        break;
                    case 5:
                        order = 5;
                        break;
                    case 6:
                        order = 6;
                        break;
                    case 7:
                        order = 3;
                        break;
                    case 8:
                        order = 2;
                        break;
                    case 9:
                        order = 1;
                        break;

                }
                break;
            case 4:
                switch(numberpad){
                    case 1:
                        order = 3;
                        break;
                    case 2:
                        order = 2;
                        break;
                    case 3:
                        order = 1;
                        break;
                    case 4:
                        order = 4;
                        break;
                    case 5:
                        order = 5;
                        break;
                    case 6:
                        order = 6;
                        break;
                    case 7:
                        order = 9;
                        break;
                    case 8:
                        order = 8;
                        break;
                    case 9:
                        order = 7;
                        break;

                }
                break;


        }

        //now to find the other parameters for the recursive function
        int newX = 0;
        int newY = 0;

        switch(xband){
            case 1:
                newX = x;
                break;
            case 2:
                newX = x - scale1d;
                break;
            case 3:
                newX = x - ( 2 * scale1d);
        }
        switch(yband){
            case 1:
                newY = y;
                break;
            case 2:
                newY = y - scale1d;
                break;
            case 3:
                newY = y - ( 2 * scale1d);
        }

        //returning value
        sum = (order - 1) * scale2d;
        if(magnitude == 1) return sum;
        sum += N2toN1(newX, newY, magnitude - 1, newColor);
        return sum;



    }

}
