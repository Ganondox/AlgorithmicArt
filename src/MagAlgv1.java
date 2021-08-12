/**
 * Created with IntelliJ IDEA.
 * User: 36483
 * Date: 6/4/13
 * Time: 1:13 AM
 * To change this template use File | Settings | File Templates.
 */



public class MagAlgv1 {

    //one to one map between vectors in R1, R2, and R3 using iterated 2x2 partitions, made for ints, but may be extended to doubles in future

    enum block2D{red, green, yellow, blue};

    enum block3D{red, orange, yellow, green, blue, purple,
                grey, cyan, brown, black, teal, pink }

                /*
    white, azure, mauve, maroon, tan, indigo,
    violet, olive, beige, amber, gold, silver */ // thankfully I only need half the rotational group, each edge is ordered

    public static int N2toN1(int x, int y, int magnitude, block2D color){
        //Magnitude, and color are all used to describe the area being partitioned,
        // magnitude is the number of partitions, color describes how it's partitioned

        /*int red = 1;
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

         */

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
            case red: //red
                   if(x < scale1d){
                       if (y < scale1d){
                           //1
                           sum = 0 * scale2d;
                           if(magnitude == 1){
                               return sum;
                           } else {
                               sum += MagAlgv1.N2toN1(x,y,magnitude - 1,block2D.green );
                               return sum;
                           }

                       } else {
                           //4
                           sum = 3 * scale2d;
                           if(magnitude == 1){
                               return sum;
                           } else {
                               sum += MagAlgv1.N2toN1(x,y - scale1d,magnitude - 1, block2D.blue);
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
                               sum += MagAlgv1.N2toN1(x - scale1d,y,magnitude - 1,block2D.red);
                               return sum;
                           }
                       } else {
                           //3
                           sum = 2 * scale2d;
                           if(magnitude == 1){
                               return sum;
                           } else {
                               sum += MagAlgv1.N2toN1(x - scale1d,y - scale1d,magnitude - 1,block2D.red);
                              // return sum;
                           }
                       }
                   }
                break;
            case green: //green
                if(x < scale1d){
                    if (y < scale1d){
                        //1
                        sum = 0 * scale2d;
                        if(magnitude == 1){
                            return sum;
                        } else {
                            sum += MagAlgv1.N2toN1(x,y,magnitude - 1,block2D.red);
                           // return sum;
                        }
                    } else {
                        //2
                        sum = 1 * scale2d;
                        if(magnitude == 1){
                            return sum;
                        } else {
                            sum += MagAlgv1.N2toN1(x,y - scale1d,magnitude - 1,block2D.green);
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
                            sum += MagAlgv1.N2toN1(x - scale1d,y,magnitude - 1,block2D.yellow);
                            return sum;
                        }
                    } else {
                        //3
                        sum = 2 * scale2d;
                        if(magnitude == 1){
                            return sum;
                        } else {
                            sum += MagAlgv1.N2toN1(x - scale1d,y - scale1d,magnitude - 1,block2D.green);
                            return sum;
                        }
                    }
                }
                break;
            case yellow: //yellow
                if(x < scale1d){
                    if (y < scale1d){
                        //3
                        sum = 2 * scale2d;
                        if(magnitude == 1){
                           // return sum;
                        } else {
                            sum += MagAlgv1.N2toN1(x,y,magnitude - 1,block2D.yellow);
                            return sum;
                        }
                    } else {
                        //2
                        sum = 1 * scale2d;
                        if(magnitude == 1){
                            return sum;
                        }  else {
                            sum += MagAlgv1.N2toN1(x,y - scale1d,magnitude - 1,block2D.yellow);
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
                            sum += MagAlgv1.N2toN1(x - scale1d,y,magnitude - 1,block2D.green);
                            return sum;
                        }
                    } else {
                        //1
                        sum = 0 * scale2d;
                        if(magnitude == 1){
                            return sum;
                        } else {
                            sum += MagAlgv1.N2toN1(x - scale1d,y - scale1d,magnitude - 1, block2D.blue);
                            return sum;
                        }
                    }
                }
                break;
            case blue: //blue
                if(x < scale1d){
                    if (y < scale1d){
                        //3
                        sum = 2 * scale2d;
                        if(magnitude == 1){
                            return sum;
                        } else {
                            sum += MagAlgv1.N2toN1(x,y,magnitude - 1,block2D.blue);
                            return sum;
                        }
                    } else {
                        //4
                        sum = 3 * scale2d;
                        if(magnitude == 1){
                            return sum;
                        } else {
                            sum += MagAlgv1.N2toN1(x,y - scale1d,magnitude - 1, block2D.red);
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
                            sum += MagAlgv1.N2toN1(x - scale1d,y,magnitude - 1,block2D.blue);
                            return sum;
                        }
                    } else {
                        //1
                        sum = 0 * scale2d;
                        if(magnitude == 1){
                           // return sum;
                        } else {
                            sum += MagAlgv1.N2toN1(x - scale1d,y - scale1d,magnitude - 1,block2D.yellow);
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

    public static int N3toN1(int x, int y, int z, int magnitude, block3D color){
        //for different functions colors are different
        /*int red = 1; (1-8)
        //1 = red, 1-4|2-3 ,  purple, grey, green, green
        //         8-5|7-6    orange, grey, blue , blue

        int purple = 2; (1-2)
        //2 = purple, 1-2|8-7 ,  green, red, brown, yellow
        //            4-3|5-6    cyan , red, cyan , yellow

        int green = 3; (1-4)
        //3 = green, 1-8|4-5 ,  red   , teal , pink  , pink
        //           2-7|3-6    purple, black, purple, black

        int yellow = 4; (7-2)
        //4 = yellow, 7-6|8-5 ,  brown, brown, purple, teal
        //            2-3|1-4    pink , pink , orange, teal

        int black = 5; (3-4)
        //5 = black,  7-8|2-1 ,  teal, green , grey , brown
        //            6-5|3-4    teal, orange, grey , orange

        int blue = 6; (5-8)
        //6 = blue,   7-2|6-3 ,  orange, cyan, orange, cyan
        //            8-1|5-4    red   , teal, brown , brown

        grey (3-6)
                      3-2|4-1 ,  brown, brown,   red, black
        //            6-7|5-8     pink,  pink,   red, cyan

        orange (7-8)
                      5-6|4-3 ,  black,  red, black, yellow
        //            8-7|1-2     blue,  red,  pink, yellow

        cyan (5-6)
                      3-4|6-5 ,   teal, purple, grey, purple
        //            2-1|7-8     teal,   blue, grey, pink

        brown (3-2)
                      5-4|8-1 ,  blue,    blue, yellow, grey
        //            6-3|7-2    purple, black, purple, black

        pink (7-6)
                      3-6|2-7 ,  orange,  cyan, orange, cyan
        //            4-5|1-8     green, green, yellow, grey

        Teal (5-4)
                      5-8|6-7 ,   yellow, black, green, green
        //            4-1|3-2     yellow,  cyan,  blue, blue

         (-)
                      -|- ,  , ,   ,
        //            -|-     ,  , ,


        Symmetry check

        Each color should start and end one other color
        Be paired with another color where both have doubles that appear in the same place twice, together
        Have one other double, in a different place

        Passed the check, so now it's time to code up the actual algorith

         */

        int scale1d = (int) Math.pow(2, magnitude - 1);
        int scale3d = (int) Math.pow(8, magnitude - 1);

        int sum = 0;

        if( x < 0 || x >= scale1d * 2){
            throw new IndexOutOfBoundsException();
        }

        if( y < 0 || y >= scale1d * 2){
            throw new IndexOutOfBoundsException();
        }

        if( z < 0 || z >= scale1d * 2){
            throw new IndexOutOfBoundsException();
        }

        if(magnitude < 1){
            throw new IndexOutOfBoundsException();
        }

        switch (color){
            case red: //red
                if(x < scale1d) {
                    if (y < scale1d) {
                        if (z < scale1d) {
                            //1
                            sum = 0 * scale3d;
                            if (magnitude == 1) {
                                return sum;
                            } else {
                                sum += MagAlgv1.N3toN1(x, y, z, magnitude - 1, block3D.purple);
                                return sum;
                            }
                        } else {
                            //2
                            sum = 1 * scale3d;
                            if (magnitude == 1) {
                                return sum;
                            } else {
                                sum += MagAlgv1.N3toN1(x, y, z - scale1d, magnitude - 1, block3D.green);
                                return sum;
                            }
                        }
                    } else {
                        if (z < scale1d) {
                            //8
                            sum = 7 * scale3d;
                            if (magnitude == 1) {
                                return sum;
                            } else {
                                sum += MagAlgv1.N3toN1(x, y - scale1d, z, magnitude - 1, block3D.orange);
                                return sum;
                            }
                        } else {
                            //7
                            sum = 6 * scale3d;
                            if (magnitude == 1) {
                                return sum;
                            } else {
                                sum += MagAlgv1.N3toN1(x, y - scale1d, z - scale1d, magnitude - 1, block3D.blue);
                                return sum;
                            }
                        }
                    }
                } else {
                    if (y < scale1d) {
                        if (z < scale1d) {
                            //4
                            sum = 3 * scale3d;
                            if (magnitude == 1) {
                                return sum;
                            } else {
                                sum += MagAlgv1.N3toN1(x - scale1d, y, z, magnitude - 1, block3D.grey);
                                return sum;
                            }
                        } else {
                            //3
                            sum = 2 * scale3d;
                            if (magnitude == 1) {
                                return sum;
                            } else {
                                sum += MagAlgv1.N3toN1(x - scale1d, y, z - scale1d, magnitude - 1, block3D.green);
                                return sum;
                            }
                        }
                    } else {
                        if (z < scale1d) {
                            //5
                            sum = 4 * scale3d;
                            if (magnitude == 1) {
                                return sum;
                            } else {
                                sum += MagAlgv1.N3toN1(x - scale1d, y - scale1d, z, magnitude - 1, block3D.grey);
                                return sum;
                            }
                        } else {
                            //6
                            sum = 5 * scale3d;
                            if (magnitude == 1) {
                                //return sum;
                            } else {
                                sum += MagAlgv1.N3toN1(x - scale1d, y - scale1d, z - scale1d, magnitude - 1, block3D.blue);
                                return sum;
                            }
                        }
                    }
                }
                break;
            case purple: //purple
                if(x < scale1d) {
                    if (y < scale1d) {
                        if (z < scale1d) {
                            //1
                            sum = 0 * scale3d;
                            if (magnitude == 1) {
                                return sum;
                            } else {
                                sum += MagAlgv1.N3toN1(x, y, z, magnitude - 1, block3D.green);
                                return sum;
                            }
                        } else {
                            //8
                            sum = 7 * scale3d;
                            if (magnitude == 1) {
                                return sum;
                            } else {
                                sum += MagAlgv1.N3toN1(x, y, z - scale1d, magnitude - 1, block3D.brown);
                                return sum;
                            }
                        }
                    } else {
                        if (z < scale1d) {
                            //4
                            sum = 3 * scale3d;
                            if (magnitude == 1) {
                                return sum;
                            } else {
                                sum += MagAlgv1.N3toN1(x, y - scale1d, z, magnitude - 1, block3D.cyan);
                                return sum;
                            }
                        } else {
                            //5
                            sum = 4 * scale3d;
                            if (magnitude == 1) {
                                return sum;
                            } else {
                                sum += MagAlgv1.N3toN1(x, y - scale1d, z - scale1d, magnitude - 1, block3D.cyan);
                                return sum;
                            }
                        }
                    }
                } else {
                    if (y < scale1d) {
                        if (z < scale1d) {
                            //2
                            sum = 1 * scale3d;
                            if (magnitude == 1) {
                                return sum;
                            } else {
                                sum += MagAlgv1.N3toN1(x - scale1d, y, z, magnitude - 1, block3D.red);
                                return sum;
                            }
                        } else {
                            //7
                            sum = 6 * scale3d;
                            if (magnitude == 1) {
                                return sum;
                            } else {
                                sum += MagAlgv1.N3toN1(x - scale1d, y, z - scale1d, magnitude - 1, block3D.yellow);
                                return sum;
                            }
                        }
                    } else {
                        if (z < scale1d) {
                            //3
                            sum = 2 * scale3d;
                            if (magnitude == 1) {
                                return sum;
                            } else {
                                sum += MagAlgv1.N3toN1(x - scale1d, y - scale1d, z, magnitude - 1, block3D.red);
                                return sum;
                            }
                        } else {
                            //6
                            sum = 5 * scale3d;
                            if (magnitude == 1) {
                                //return sum;
                            } else {
                                sum += MagAlgv1.N3toN1(x - scale1d, y - scale1d, z - scale1d, magnitude - 1, block3D.yellow);
                                return sum;
                            }
                        }
                    }
                }
                break;
            case green: //green
                if(x < scale1d) {
                    if (y < scale1d) {
                        if (z < scale1d) {
                            //1
                            sum = 0 * scale3d;
                            if (magnitude == 1) {
                                return sum;
                            } else {
                                sum += MagAlgv1.N3toN1(x, y, z, magnitude - 1, block3D.red);
                                return sum;
                            }
                        } else {
                            //4
                            sum = 3 * scale3d;
                            if (magnitude == 1) {
                                return sum;
                            } else {
                                sum += MagAlgv1.N3toN1(x, y, z - scale1d, magnitude - 1, block3D.pink);
                                return sum;
                            }
                        }
                    } else {
                        if (z < scale1d) {
                            //2
                            sum = 1 * scale3d;
                            if (magnitude == 1) {
                                return sum;
                            } else {
                                sum += MagAlgv1.N3toN1(x, y - scale1d, z, magnitude - 1, block3D.purple);
                                return sum;
                            }
                        } else {
                            //3
                            sum = 2 * scale3d;
                            if (magnitude == 1) {
                                return sum;
                            } else {
                                sum += MagAlgv1.N3toN1(x, y - scale1d, z - scale1d, magnitude - 1, block3D.purple);
                                return sum;
                            }
                        }
                    }
                } else {
                    if (y < scale1d) {
                        if (z < scale1d) {
                            //8
                            sum = 7 * scale3d;
                            if (magnitude == 1) {
                                return sum;
                            } else {
                                sum += MagAlgv1.N3toN1(x - scale1d, y, z, magnitude - 1, block3D.teal);
                                return sum;
                            }
                        } else {
                            //5
                            sum = 4 * scale3d;
                            if (magnitude == 1) {
                                return sum;
                            } else {
                                sum += MagAlgv1.N3toN1(x - scale1d, y, z - scale1d, magnitude - 1, block3D.pink);
                                return sum;
                            }
                        }
                    } else {
                        if (z < scale1d) {
                            //7
                            sum = 6 * scale3d;
                            if (magnitude == 1) {
                                return sum;
                            } else {
                                sum += MagAlgv1.N3toN1(x - scale1d, y - scale1d, z, magnitude - 1, block3D.black);
                                return sum;
                            }
                        } else {
                            //6
                            sum = 5 * scale3d;
                            if (magnitude == 1) {
                                //return sum;
                            } else {
                                sum += MagAlgv1.N3toN1(x - scale1d, y - scale1d, z - scale1d, magnitude - 1, block3D.black);
                                return sum;
                            }
                        }
                    }
                }
                break;
            case yellow: //yellow
                if(x < scale1d) {
                    if (y < scale1d) {
                        if (z < scale1d) {
                            //7
                            sum = 6 * scale3d;
                            if (magnitude == 1) {
                                return sum;
                            } else {
                                sum += MagAlgv1.N3toN1(x, y, z, magnitude - 1, block3D.brown);
                                return sum;
                            }
                        } else {
                            //8
                            sum = 7 * scale3d;
                            if (magnitude == 1) {
                                return sum;
                            } else {
                                sum += MagAlgv1.N3toN1(x, y, z - scale1d, magnitude - 1, block3D.purple);
                                return sum;
                            }
                        }
                    } else {
                        if (z < scale1d) {
                            //2
                            sum = 1 * scale3d;
                            if (magnitude == 1) {
                                return sum;
                            } else {
                                sum += MagAlgv1.N3toN1(x, y - scale1d, z, magnitude - 1, block3D.pink);
                                return sum;
                            }
                        } else {
                            //1
                            sum = 0 * scale3d;
                            if (magnitude == 1) {
                                return sum;
                            } else {
                                sum += MagAlgv1.N3toN1(x, y - scale1d, z - scale1d, magnitude - 1, block3D.orange);
                                return sum;
                            }
                        }
                    }
                } else {
                    if (y < scale1d) {
                        if (z < scale1d) {
                            //6
                            sum = 5 * scale3d;
                            if (magnitude == 1) {
                                return sum;
                            } else {
                                sum += MagAlgv1.N3toN1(x - scale1d, y, z, magnitude - 1, block3D.brown);
                                return sum;
                            }
                        } else {
                            //5
                            sum = 4 * scale3d;
                            if (magnitude == 1) {
                                return sum;
                            } else {
                                sum += MagAlgv1.N3toN1(x - scale1d, y, z - scale1d, magnitude - 1, block3D.teal);
                                return sum;
                            }
                        }
                    } else {
                        if (z < scale1d) {
                            //3
                            sum = 2 * scale3d;
                            if (magnitude == 1) {
                                return sum;
                            } else {
                                sum += MagAlgv1.N3toN1(x - scale1d, y - scale1d, z, magnitude - 1, block3D.pink);
                                return sum;
                            }
                        } else {
                            //4
                            sum = 3 * scale3d;
                            if (magnitude == 1) {
                                //return sum;
                            } else {
                                sum += MagAlgv1.N3toN1(x - scale1d, y - scale1d, z - scale1d, magnitude - 1, block3D.teal);
                                return sum;
                            }
                        }
                    }
                }
                break;

            case black: //black
                if(x < scale1d) {
                    if (y < scale1d) {
                        if (z < scale1d) {
                            //7
                            sum = 6 * scale3d;
                            if (magnitude == 1) {
                                return sum;
                            } else {
                                sum += MagAlgv1.N3toN1(x, y, z, magnitude - 1, block3D.teal);
                                return sum;
                            }
                        } else {
                            //2
                            sum = 1 * scale3d;
                            if (magnitude == 1) {
                                return sum;
                            } else {
                                sum += MagAlgv1.N3toN1(x, y, z - scale1d, magnitude - 1, block3D.grey);
                                return sum;
                            }
                        }
                    } else {
                        if (z < scale1d) {
                            //6
                            sum = 5 * scale3d;
                            if (magnitude == 1) {
                                return sum;
                            } else {
                                sum += MagAlgv1.N3toN1(x, y - scale1d, z, magnitude - 1, block3D.teal);
                                return sum;
                            }
                        } else {
                            //3
                            sum = 2 * scale3d;
                            if (magnitude == 1) {
                                return sum;
                            } else {
                                sum += MagAlgv1.N3toN1(x, y - scale1d, z - scale1d, magnitude - 1, block3D.grey);
                                return sum;
                            }
                        }
                    }
                } else {
                    if (y < scale1d) {
                        if (z < scale1d) {
                            //8
                            sum = 7 * scale3d;
                            if (magnitude == 1) {
                                return sum;
                            } else {
                                sum += MagAlgv1.N3toN1(x - scale1d, y, z, magnitude - 1, block3D.green);
                                return sum;
                            }
                        } else {
                            //1
                            sum = 0 * scale3d;
                            if (magnitude == 1) {
                                return sum;
                            } else {
                                sum += MagAlgv1.N3toN1(x - scale1d, y, z - scale1d, magnitude - 1, block3D.brown);
                                return sum;
                            }
                        }
                    } else {
                        if (z < scale1d) {
                            //5
                            sum = 4 * scale3d;
                            if (magnitude == 1) {
                                return sum;
                            } else {
                                sum += MagAlgv1.N3toN1(x - scale1d, y - scale1d, z, magnitude - 1, block3D.orange);
                                return sum;
                            }
                        } else {
                            //4
                            sum = 3 * scale3d;
                            if (magnitude == 1) {
                                //return sum;
                            } else {
                                sum += MagAlgv1.N3toN1(x - scale1d, y - scale1d, z - scale1d, magnitude - 1, block3D.orange);
                                return sum;
                            }
                        }
                    }
                }
                break;

            case blue: //blue
                if(x < scale1d) {
                    if (y < scale1d) {
                        if (z < scale1d) {
                            //7
                            sum = 6 * scale3d;
                            if (magnitude == 1) {
                                return sum;
                            } else {
                                sum += MagAlgv1.N3toN1(x, y, z, magnitude - 1, block3D.orange);
                                return sum;
                            }
                        } else {
                            //6
                            sum = 5 * scale3d;
                            if (magnitude == 1) {
                                return sum;
                            } else {
                                sum += MagAlgv1.N3toN1(x, y, z - scale1d, magnitude - 1, block3D.orange);
                                return sum;
                            }
                        }
                    } else {
                        if (z < scale1d) {
                            //8
                            sum = 7 * scale3d;
                            if (magnitude == 1) {
                                return sum;
                            } else {
                                sum += MagAlgv1.N3toN1(x, y - scale1d, z, magnitude - 1, block3D.red);
                                return sum;
                            }
                        } else {
                            //5
                            sum = 4 * scale3d;
                            if (magnitude == 1) {
                                return sum;
                            } else {
                                sum += MagAlgv1.N3toN1(x, y - scale1d, z - scale1d, magnitude - 1, block3D.brown);
                                return sum;
                            }
                        }
                    }
                } else {
                    if (y < scale1d) {
                        if (z < scale1d) {
                            //2
                            sum = 1 * scale3d;
                            if (magnitude == 1) {
                                return sum;
                            } else {
                                sum += MagAlgv1.N3toN1(x - scale1d, y, z, magnitude - 1, block3D.cyan);
                                return sum;
                            }
                        } else {
                            //3
                            sum = 2 * scale3d;
                            if (magnitude == 1) {
                                return sum;
                            } else {
                                sum += MagAlgv1.N3toN1(x - scale1d, y, z - scale1d, magnitude - 1, block3D.cyan);
                                return sum;
                            }
                        }
                    } else {
                        if (z < scale1d) {
                            //1
                            sum = 0 * scale3d;
                            if (magnitude == 1) {
                                return sum;
                            } else {
                                sum += MagAlgv1.N3toN1(x - scale1d, y - scale1d, z, magnitude - 1, block3D.teal);
                                return sum;
                            }
                        } else {
                            //4
                            sum = 3 * scale3d;
                            if (magnitude == 1) {
                                //return sum;
                            } else {
                                sum += MagAlgv1.N3toN1(x - scale1d, y - scale1d, z - scale1d, magnitude - 1, block3D.brown);
                                return sum;
                            }
                        }
                    }
                }
                break;
            case grey: //grey
                if(x < scale1d) {
                    if (y < scale1d) {
                        if (z < scale1d) {
                            //3
                            sum = 2 * scale3d;
                            if (magnitude == 1) {
                                return sum;
                            } else {
                                sum += MagAlgv1.N3toN1(x, y, z, magnitude - 1, block3D.brown);
                                return sum;
                            }
                        } else {
                            //4
                            sum = 3 * scale3d;
                            if (magnitude == 1) {
                                return sum;
                            } else {
                                sum += MagAlgv1.N3toN1(x, y, z - scale1d, magnitude - 1, block3D.red);
                                return sum;
                            }
                        }
                    } else {
                        if (z < scale1d) {
                            //6
                            sum = 5 * scale3d;
                            if (magnitude == 1) {
                                return sum;
                            } else {
                                sum += MagAlgv1.N3toN1(x, y - scale1d, z, magnitude - 1, block3D.pink);
                                return sum;
                            }
                        } else {
                            //5
                            sum = 4 * scale3d;
                            if (magnitude == 1) {
                                return sum;
                            } else {
                                sum += MagAlgv1.N3toN1(x, y - scale1d, z - scale1d, magnitude - 1, block3D.red);
                                return sum;
                            }
                        }
                    }
                } else {
                    if (y < scale1d) {
                        if (z < scale1d) {
                            //2
                            sum = 1 * scale3d;
                            if (magnitude == 1) {
                                return sum;
                            } else {
                                sum += MagAlgv1.N3toN1(x - scale1d, y, z, magnitude - 1, block3D.brown);
                                return sum;
                            }
                        } else {
                            //1
                            sum = 0 * scale3d;
                            if (magnitude == 1) {
                                return sum;
                            } else {
                                sum += MagAlgv1.N3toN1(x - scale1d, y, z - scale1d, magnitude - 1, block3D.black);
                                return sum;
                            }
                        }
                    } else {
                        if (z < scale1d) {
                            //7
                            sum = 6 * scale3d;
                            if (magnitude == 1) {
                                return sum;
                            } else {
                                sum += MagAlgv1.N3toN1(x - scale1d, y - scale1d, z, magnitude - 1, block3D.pink);
                                return sum;
                            }
                        } else {
                            //8
                            sum = 7 * scale3d;
                            if (magnitude == 1) {
                                //return sum;
                            } else {
                                sum += MagAlgv1.N3toN1(x - scale1d, y - scale1d, z - scale1d, magnitude - 1, block3D.cyan);
                                return sum;
                            }
                        }
                    }
                }
                break;
            case orange: //orange
                if(x < scale1d) {
                    if (y < scale1d) {
                        if (z < scale1d) {
                            //5
                            sum = 4 * scale3d;
                            if (magnitude == 1) {
                                return sum;
                            } else {
                                sum += MagAlgv1.N3toN1(x, y, z, magnitude - 1, block3D.black);
                                return sum;
                            }
                        } else {
                            //4
                            sum = 3 * scale3d;
                            if (magnitude == 1) {
                                return sum;
                            } else {
                                sum += MagAlgv1.N3toN1(x, y, z - scale1d, magnitude - 1, block3D.black);
                                return sum;
                            }
                        }
                    } else {
                        if (z < scale1d) {
                            //8
                            sum = 7 * scale3d;
                            if (magnitude == 1) {
                                return sum;
                            } else {
                                sum += MagAlgv1.N3toN1(x, y - scale1d, z, magnitude - 1, block3D.blue);
                                return sum;
                            }
                        } else {
                            //1
                            sum = 0 * scale3d;
                            if (magnitude == 1) {
                                return sum;
                            } else {
                                sum += MagAlgv1.N3toN1(x, y - scale1d, z - scale1d, magnitude - 1, block3D.pink);
                                return sum;
                            }
                        }
                    }
                } else {
                    if (y < scale1d) {
                        if (z < scale1d) {
                            //6
                            sum = 5 * scale3d;
                            if (magnitude == 1) {
                                return sum;
                            } else {
                                sum += MagAlgv1.N3toN1(x - scale1d, y, z, magnitude - 1, block3D.red);
                                return sum;
                            }
                        } else {
                            //3
                            sum = 2 * scale3d;
                            if (magnitude == 1) {
                                return sum;
                            } else {
                                sum += MagAlgv1.N3toN1(x - scale1d, y, z - scale1d, magnitude - 1, block3D.yellow);
                                return sum;
                            }
                        }
                    } else {
                        if (z < scale1d) {
                            //7
                            sum = 6 * scale3d;
                            if (magnitude == 1) {
                                return sum;
                            } else {
                                sum += MagAlgv1.N3toN1(x - scale1d, y - scale1d, z, magnitude - 1, block3D.red);
                                return sum;
                            }
                        } else {
                            //2
                            sum = 1 * scale3d;
                            if (magnitude == 1) {
                                //return sum;
                            } else {
                                sum += MagAlgv1.N3toN1(x - scale1d, y - scale1d, z - scale1d, magnitude - 1, block3D.yellow);
                                return sum;
                            }
                        }
                    }
                }
                break;
            case cyan: //cyan
                if(x < scale1d) {
                    if (y < scale1d) {
                        if (z < scale1d) {
                            //3
                            sum = 2 * scale3d;
                            if (magnitude == 1) {
                                return sum;
                            } else {
                                sum += MagAlgv1.N3toN1(x, y, z, magnitude - 1, block3D.teal);
                                return sum;
                            }
                        } else {
                            //6
                            sum = 5 * scale3d;
                            if (magnitude == 1) {
                                return sum;
                            } else {
                                sum += MagAlgv1.N3toN1(x, y, z - scale1d, magnitude - 1, block3D.grey);
                                return sum;
                            }
                        }
                    } else {
                        if (z < scale1d) {
                            //2
                            sum = 1 * scale3d;
                            if (magnitude == 1) {
                                return sum;
                            } else {
                                sum += MagAlgv1.N3toN1(x, y - scale1d, z, magnitude - 1, block3D.teal);
                                return sum;
                            }
                        } else {
                            //7
                            sum = 6 * scale3d;
                            if (magnitude == 1) {
                                return sum;
                            } else {
                                sum += MagAlgv1.N3toN1(x, y - scale1d, z - scale1d, magnitude - 1, block3D.grey);
                                return sum;
                            }
                        }
                    }
                } else {
                    if (y < scale1d) {
                        if (z < scale1d) {
                            //4
                            sum = 3 * scale3d;
                            if (magnitude == 1) {
                                return sum;
                            } else {
                                sum += MagAlgv1.N3toN1(x - scale1d, y, z, magnitude - 1, block3D.purple);
                                return sum;
                            }
                        } else {
                            //5
                            sum = 4 * scale3d;
                            if (magnitude == 1) {
                                return sum;
                            } else {
                                sum += MagAlgv1.N3toN1(x - scale1d, y, z - scale1d, magnitude - 1, block3D.purple);
                                return sum;
                            }
                        }
                    } else {
                        if (z < scale1d) {
                            //1
                            sum = 0 * scale3d;
                            if (magnitude == 1) {
                                return sum;
                            } else {
                                sum += MagAlgv1.N3toN1(x - scale1d, y - scale1d, z, magnitude - 1, block3D.blue);
                                return sum;
                            }
                        } else {
                            //8
                            sum = 7 * scale3d;
                            if (magnitude == 1) {
                                //return sum;
                            } else {
                                sum += MagAlgv1.N3toN1(x - scale1d, y - scale1d, z - scale1d, magnitude - 1, block3D.pink);
                                return sum;
                            }
                        }
                    }
                }
                break;

            case brown: //brown
                if(x < scale1d) {
                    if (y < scale1d) {
                        if (z < scale1d) {
                            //5
                            sum = 4 * scale3d;
                            if (magnitude == 1) {
                                return sum;
                            } else {
                                sum += MagAlgv1.N3toN1(x, y, z, magnitude - 1, block3D.blue);
                                return sum;
                            }
                        } else {
                            //8
                            sum = 7 * scale3d;
                            if (magnitude == 1) {
                                return sum;
                            } else {
                                sum += MagAlgv1.N3toN1(x, y, z - scale1d, magnitude - 1, block3D.yellow);
                                return sum;
                            }
                        }
                    } else {
                        if (z < scale1d) {
                            //6
                            sum = 5 * scale3d;
                            if (magnitude == 1) {
                                return sum;
                            } else {
                                sum += MagAlgv1.N3toN1(x, y - scale1d, z, magnitude - 1, block3D.purple);
                                return sum;
                            }
                        } else {
                            //7
                            sum = 6 * scale3d;
                            if (magnitude == 1) {
                                return sum;
                            } else {
                                sum += MagAlgv1.N3toN1(x, y - scale1d, z - scale1d, magnitude - 1, block3D.purple);
                                return sum;
                            }
                        }
                    }
                } else {
                    if (y < scale1d) {
                        if (z < scale1d) {
                            //4
                            sum = 3 * scale3d;
                            if (magnitude == 1) {
                                return sum;
                            } else {
                                sum += MagAlgv1.N3toN1(x - scale1d, y, z, magnitude - 1, block3D.blue);
                                return sum;
                            }
                        } else {
                            //1
                            sum = 0 * scale3d;
                            if (magnitude == 1) {
                                return sum;
                            } else {
                                sum += MagAlgv1.N3toN1(x - scale1d, y, z - scale1d, magnitude - 1, block3D.grey);
                                return sum;
                            }
                        }
                    } else {
                        if (z < scale1d) {
                            //3
                            sum = 2 * scale3d;
                            if (magnitude == 1) {
                                return sum;
                            } else {
                                sum += MagAlgv1.N3toN1(x - scale1d, y - scale1d, z, magnitude - 1, block3D.black);
                                return sum;
                            }
                        } else {
                            //2
                            sum = 1 * scale3d;
                            if (magnitude == 1) {
                                //return sum;
                            } else {
                                sum += MagAlgv1.N3toN1(x - scale1d, y - scale1d, z - scale1d, magnitude - 1, block3D.black);
                                return sum;
                            }
                        }
                    }
                }
                break;
            case pink: //pink
                if(x < scale1d) {
                    if (y < scale1d) {
                        if (z < scale1d) {
                            //3
                            sum = 2 * scale3d;
                            if (magnitude == 1) {
                                return sum;
                            } else {
                                sum += MagAlgv1.N3toN1(x, y, z, magnitude - 1, block3D.orange);
                                return sum;
                            }
                        } else {
                            //2
                            sum = 1 * scale3d;
                            if (magnitude == 1) {
                                return sum;
                            } else {
                                sum += MagAlgv1.N3toN1(x, y, z - scale1d, magnitude - 1, block3D.orange);
                                return sum;
                            }
                        }
                    } else {
                        if (z < scale1d) {
                            //4
                            sum = 3 * scale3d;
                            if (magnitude == 1) {
                                return sum;
                            } else {
                                sum += MagAlgv1.N3toN1(x, y - scale1d, z, magnitude - 1, block3D.green);
                                return sum;
                            }
                        } else {
                            //1
                            sum = 0 * scale3d;
                            if (magnitude == 1) {
                                return sum;
                            } else {
                                sum += MagAlgv1.N3toN1(x, y - scale1d, z - scale1d, magnitude - 1, block3D.yellow);
                                return sum;
                            }
                        }
                    }
                } else {
                    if (y < scale1d) {
                        if (z < scale1d) {
                            //6
                            sum = 5 * scale3d;
                            if (magnitude == 1) {
                                return sum;
                            } else {
                                sum += MagAlgv1.N3toN1(x - scale1d, y, z, magnitude - 1, block3D.cyan);
                                return sum;
                            }
                        } else {
                            //7
                            sum = 6 * scale3d;
                            if (magnitude == 1) {
                                return sum;
                            } else {
                                sum += MagAlgv1.N3toN1(x - scale1d, y, z - scale1d, magnitude - 1, block3D.cyan);
                                return sum;
                            }
                        }
                    } else {
                        if (z < scale1d) {
                            //5
                            sum = 4 * scale3d;
                            if (magnitude == 1) {
                                return sum;
                            } else {
                                sum += MagAlgv1.N3toN1(x - scale1d, y - scale1d, z, magnitude - 1, block3D.green);
                                return sum;
                            }
                        } else {
                            //8
                            sum = 7 * scale3d;
                            if (magnitude == 1) {
                                //return sum;
                            } else {
                                sum += MagAlgv1.N3toN1(x - scale1d, y - scale1d, z - scale1d, magnitude - 1, block3D.grey);
                                return sum;
                            }
                        }
                    }
                }
                break;
            case teal: //teal
                if(x < scale1d) {
                    if (y < scale1d) {
                        if (z < scale1d) {
                            //5
                            sum = 4 * scale3d;
                            if (magnitude == 1) {
                                return sum;
                            } else {
                                sum += MagAlgv1.N3toN1(x, y, z, magnitude - 1, block3D.yellow);
                                return sum;
                            }
                        } else {
                            //6
                            sum = 5 * scale3d;
                            if (magnitude == 1) {
                                return sum;
                            } else {
                                sum += MagAlgv1.N3toN1(x, y, z - scale1d, magnitude - 1, block3D.green);
                                return sum;
                            }
                        }
                    } else {
                        if (z < scale1d) {
                            //4
                            sum = 3 * scale3d;
                            if (magnitude == 1) {
                                return sum;
                            } else {
                                sum += MagAlgv1.N3toN1(x, y - scale1d, z, magnitude - 1, block3D.yellow);
                                return sum;
                            }
                        } else {
                            //3
                            sum = 2 * scale3d;
                            if (magnitude == 1) {
                                return sum;
                            } else {
                                sum += MagAlgv1.N3toN1(x, y - scale1d, z - scale1d, magnitude - 1, block3D.blue);
                                return sum;
                            }
                        }
                    }
                } else {
                    if (y < scale1d) {
                        if (z < scale1d) {
                            //8
                            sum = 7 * scale3d;
                            if (magnitude == 1) {
                                return sum;
                            } else {
                                sum += MagAlgv1.N3toN1(x - scale1d, y, z, magnitude - 1, block3D.black);
                                return sum;
                            }
                        } else {
                            //7
                            sum = 6 * scale3d;
                            if (magnitude == 1) {
                                return sum;
                            } else {
                                sum += MagAlgv1.N3toN1(x - scale1d, y, z - scale1d, magnitude - 1, block3D.green);
                                return sum;
                            }
                        }
                    } else {
                        if (z < scale1d) {
                            //1
                            sum = 0 * scale3d;
                            if (magnitude == 1) {
                                return sum;
                            } else {
                                sum += MagAlgv1.N3toN1(x - scale1d, y - scale1d, z, magnitude - 1, block3D.cyan);
                                return sum;
                            }
                        } else {
                            //2
                            sum = 1 * scale3d;
                            if (magnitude == 1) {
                                //return sum;
                            } else {
                                sum += MagAlgv1.N3toN1(x - scale1d, y - scale1d, z - scale1d, magnitude - 1, block3D.blue);
                                return sum;
                            }
                        }
                    }
                }
                break;

        }



        //qblock/#/47716

        return sum;
    }

}
