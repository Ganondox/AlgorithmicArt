import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: 36483
 * Date: 6/4/13
 * Time: 5:47 PM
 * To change this template use File | Settings | File Templates.
 */
public class Greycoil {
    public static void main( String args[]) {
        BufferedImage img = new BufferedImage(16, 16, BufferedImage.TYPE_INT_RGB);
        for(int i = 0; i < 16; i++ ){
            for(int k = 0; k < 16; k++) {
                int intensity = MagAlgv1.N2toN1(k,i,4, MagAlgv1.block2D.red);
                int red = intensity;
                int green =  intensity;
                int blue =  intensity;

                int rbg = (red * 65536) + (green * 256) + blue;
                img.setRGB(k, i, rbg);
                System.out.println(rbg);

            }
        }
        File f = new File("greyCoil.png");
        try {
            ImageIO.write(img, "PNG", f);
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}
