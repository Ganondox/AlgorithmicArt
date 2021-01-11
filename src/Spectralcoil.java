import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Spectralcoil {

    public static void main( String args[]) {


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


        BufferedImage img = new BufferedImage(4096, 4096, BufferedImage.TYPE_INT_RGB);
        for(int i = 0; i < 4096; i++ ){
            for(int k = 0; k < 4096; k++) {
                int index = MagAlgv1.N2toN1(k,i,12, MagAlgv1.block2D.red);
                int red = r_coil[index];
                int green = g_coil[index];
                int blue =  b_coil[index];

                int rbg = (red * 65536) + (green * 256) + blue;
                img.setRGB(k, i, rbg);
                System.out.println(rbg);

            }
        }
        File f = new File("spectralCoil.png");
        try {
            ImageIO.write(img, "PNG", f);
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

}
