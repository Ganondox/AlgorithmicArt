/**
 * Created with IntelliJ IDEA.
 * User: 36483
 * Date: 6/4/13
 * Time: 1:13 AM
 * To change this template use File | Settings | File Templates.
 */
public class MagAlgv1 {

    //one to one map between vectors in R1, R2, and R3 using iterated 2x2 partitions, made for ints, but may be extended to doubles in future

    public static int N2toN1(int x, int y, int magnitude, int color){
        //Magnitude, and color are all used to describe the area being partitioned,
        // magnitude is the number of partitions, color describes how it's partitioned

        int red = 1;
        //1 = red, 1-2 , green, red
        //         4-3   blue , red
        int green = 2;
        //2 = green, 1-4 , red  , yellow
        //           2-3   green, green
        int yellow = 3;
        //3 = yellow, 3-4 , yellow, green
        //            2-1   yellow, blue
        int blue = 4;
        //4 = blue, 3-2 , blue, blue
        //          4-1   red , yellow

        int scale1d = (int) Math.pow(2, magnitude - 1);
        int scale2d = (int) Math.pow(4, magnitude - 1);

        int sum = 0;

        if( x < 0 || x >= scale1d * 2){
            throw new IndexOutOfBoundsException();
        }

        if( y < 0 || y >= scale1d * 2){
            throw new IndexOutOfBoundsException();
        }

        if(magnitude < 1){
            throw new IndexOutOfBoundsException();
        }

        switch (color){
            case 1: //red
                   if(x < scale1d){
                       if (y < scale1d){
                           //1
                           sum = 0 * scale2d;
                           if(magnitude == 1){
                               return sum;
                           } else {
                               sum += MagAlgv1.N2toN1(x,y,magnitude - 1,green);
                               return sum;
                           }

                       } else {
                           //4
                           sum = 3 * scale2d;
                           if(magnitude == 1){
                               return sum;
                           } else {
                               sum += MagAlgv1.N2toN1(x,y - scale1d,magnitude - 1,blue);
                               return sum;
                           }
                       }
                   } else {
                       if (y < scale1d){
                           //2
                           sum = 1 * scale2d;
                           if(magnitude == 1){
                               return sum;
                           } else {
                               sum += MagAlgv1.N2toN1(x - scale1d,y,magnitude - 1,red);
                               return sum;
                           }
                       } else {
                           //3
                           sum = 2 * scale2d;
                           if(magnitude == 1){
                               return sum;
                           } else {
                               sum += MagAlgv1.N2toN1(x - scale1d,y - scale1d,magnitude - 1,red);
                              // return sum;
                           }
                       }
                   }
                break;
            case 2: //green
                if(x < scale1d){
                    if (y < scale1d){
                        //1
                        sum = 0 * scale2d;
                        if(magnitude == 1){
                            return sum;
                        } else {
                            sum += MagAlgv1.N2toN1(x,y,magnitude - 1,red);
                           // return sum;
                        }
                    } else {
                        //2
                        sum = 1 * scale2d;
                        if(magnitude == 1){
                            return sum;
                        } else {
                            sum += MagAlgv1.N2toN1(x,y - scale1d,magnitude - 1,green);
                            return sum;
                        }
                    }
                } else {
                    if (y < scale1d){
                        //4
                        sum = 3 * scale2d;
                        if(magnitude == 1){
                            return sum;
                        } else {
                            sum += MagAlgv1.N2toN1(x - scale1d,y,magnitude - 1,yellow);
                            return sum;
                        }
                    } else {
                        //3
                        sum = 2 * scale2d;
                        if(magnitude == 1){
                            return sum;
                        } else {
                            sum += MagAlgv1.N2toN1(x - scale1d,y - scale1d,magnitude - 1,green);
                            return sum;
                        }
                    }
                }
                break;
            case 3: //yellow
                if(x < scale1d){
                    if (y < scale1d){
                        //3
                        sum = 2 * scale2d;
                        if(magnitude == 1){
                           // return sum;
                        } else {
                            sum += MagAlgv1.N2toN1(x,y,magnitude - 1,yellow);
                            return sum;
                        }
                    } else {
                        //2
                        sum = 1 * scale2d;
                        if(magnitude == 1){
                            return sum;
                        }  else {
                            sum += MagAlgv1.N2toN1(x,y - scale1d,magnitude - 1,yellow);
                            return sum;
                        }
                    }
                } else {
                    if (y < scale1d){
                        //4
                        sum = 3 * scale2d;
                        if(magnitude == 1){
                            return sum;
                        } else {
                            sum += MagAlgv1.N2toN1(x - scale1d,y,magnitude - 1,green);
                            return sum;
                        }
                    } else {
                        //1
                        sum = 0 * scale2d;
                        if(magnitude == 1){
                            return sum;
                        } else {
                            sum += MagAlgv1.N2toN1(x - scale1d,y - scale1d,magnitude - 1,blue);
                            return sum;
                        }
                    }
                }
                break;
            case 4: //blue
                if(x < scale1d){
                    if (y < scale1d){
                        //3
                        sum = 2 * scale2d;
                        if(magnitude == 1){
                            return sum;
                        } else {
                            sum += MagAlgv1.N2toN1(x,y,magnitude - 1,blue);
                            return sum;
                        }
                    } else {
                        //4
                        sum = 3 * scale2d;
                        if(magnitude == 1){
                            return sum;
                        } else {
                            sum += MagAlgv1.N2toN1(x,y - scale1d,magnitude - 1,red);
                            return sum;
                        }
                    }
                } else {
                    if (y < scale1d){
                        //2
                        sum = 1 * scale2d;
                        if(magnitude == 1){
                            return sum;
                        } else {
                            sum += MagAlgv1.N2toN1(x - scale1d,y,magnitude - 1,blue);
                            return sum;
                        }
                    } else {
                        //1
                        sum = 0 * scale2d;
                        if(magnitude == 1){
                           // return sum;
                        } else {
                            sum += MagAlgv1.N2toN1(x - scale1d,y - scale1d,magnitude - 1,yellow);
                            return sum;
                        }
                    }
                }
                break;


        }
        return sum;
    }

    public static int N1toN2X(int n, int magnitude, int color){

        int red = 1;
        //1 = red, 1-2 , green, red
        //         4-3   blue , red
        int green = 2;
        //2 = green, 1-4 , red  , yellow
        //           2-3   green, green
        int yellow = 3;
        //3 = yellow, 3-4 , yellow, green
        //            2-1   yellow, blue
        int blue = 4;
        //4 = blue, 3-2 , blue, blue
        //          4-1   red , yellow

        int scale1d = (int) Math.pow(2, magnitude - 1);
        int scale2d = (int) Math.pow(4, magnitude - 1);

        int sum = 0;

        if( n < 0 || n >= scale2d * 4){
            throw new IndexOutOfBoundsException();
        }

        if(magnitude < 1){
            throw new IndexOutOfBoundsException();
        }

        switch (color){

        }

        return sum;
    }

    public static int N3toN1(int x, int y, int z, int magnitude, int color){
        //for different functions colors are different
        int red = 1;
        //1 = red, 1-4|2-3 ,  purple, grey, green, green
        //         8-5|7-6    orange, grey, blue , blue

        int purple = 2;
        //2 = purple, 1-2|8-7 ,  green, red, brown, yellow
        //            4-3|5-6    cyan , red, cyan , yellow

        int green = 3;
        //3 = green, 1-8|4-5 ,  red   , teal , pink  , pink
        //           2-7|3-6    purple, black, purple, black

        int yellow = 4;
        //4 = yellow, 7-6|8-5 ,  brown, brown, cyan  , purple
        //            2-3|1-4    pink , pink , orange, purple

        int black = 5;
        //5 = black,  7-8|2-1 ,  teal, green , grey , brown
        //            6-5|3-4    teal, orange, grey , orange

        int blue = 6;
        //6 = blue,   7-2|6-3 ,  orange, cyan, orange, cyan
        //            8-1|5-4    red   , teal, brown , brown




        //qblock/#/47716

        return 0;
    }

}
