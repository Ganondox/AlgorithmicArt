import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Tester {



    public static void main( String args[]) {

        System.out.println("Testing 2d-1d");

        int[] x_coil = new int[16777216];
        int[] y_coil = new int[16777216];

        for(int i = 0; i < 4096; i++) {
            for (int j = 0; j < 4096; j++) {

                int index = MagAlgv1.N2toN1(i,j, 12, MagAlgv1.block2D.red);
                x_coil[index] = i;
                y_coil[index] = j;
            }
        }
        try {
            for (int i = 1; i < 16777216; i++) {
                int x_dif = x_coil[i - 1] - x_coil[i];
                int y_dif = y_coil[i - 1] - y_coil[i];
                int dist = x_dif * x_dif + y_dif * y_dif;
                if (dist != 1) throw new DiscontinuousException(i, x_coil[i - 1], x_coil[i],
                        y_coil[i - 1] , y_coil[i], 0 , 0);
                //if(i % 4096 == 0) System.out.println("Checkpoint");
            }
            System.out.println("Passed!");
        }catch(DiscontinuousException e){

            System.out.println("Failed at " + e.failPoint + ": " + e.r0 + "," + e.r1 + ","  + e.g0 + "," + e.g1);
        }


        System.out.println("Testing 3d-1d");

        int[] r_coil = new int[16777216];
        int[] g_coil = new int[16777216];
        int[] b_coil = new int[16777216];


        for(int i = 0; i < 256; i++){
            for(int j = 0; j < 256; j++){
                for(int k = 0; k < 256; k++){

                    int index  = MagAlgv1.N3toN1(i,j,k, 8, MagAlgv1.block3D.red);
                    r_coil[index] = i;
                    g_coil[index] = j;
                    b_coil[index] = k;

                }
            }
        }

        try {
            for (int i = 1; i < 16777216; i++) {
                int r_dif = r_coil[i - 1] - r_coil[i];
                int g_dif = g_coil[i - 1] - g_coil[i];
                int b_dif =  b_coil[i - 1] - b_coil[i];
                int dist = r_dif * r_dif + g_dif * g_dif + b_dif * b_dif;
                if (dist != 1) throw new DiscontinuousException(i,r_coil[i - 1], r_coil[i],
                        g_coil[i - 1] , g_coil[i],  b_coil[i - 1] , b_coil[i]);
               // if(i % 4096 == 0) System.out.println("Checkpoint");
            }
            System.out.println("Passed!");
        }catch(DiscontinuousException e){

            System.out.println("Failed at " + e.failPoint + ": " + e.r0 + "," + e.r1 + ","  + e.g0 + "," + e.g1 + ","  + e.b0 + "," + e.b1);
        }



    }

}
