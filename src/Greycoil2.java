import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: 36483
 * Date: 1/5/14
 * Time: 12:10 AM
 * To change this template use File | Settings | File Templates.
 */
public class Greycoil2 {
    public static void main( String args[]) {
        BufferedImage img = new BufferedImage(27, 27, BufferedImage.TYPE_INT_RGB);
        for(int i = 0; i < 27; i++ ){
            for(int k = 0; k < 27; k++) {
                int red = (int) ((double)MagAlgv2.N2toN1(k,i,16, 1) / 729 * 256);
                int green = (int) ((double)MagAlgv2.N2toN1(k,i,16, 1) / 729 * 256);
                int blue = (int) ((double)MagAlgv2.N2toN1(k,i,16, 1) / 729 * 256);

                int rbg = (red * 65536) + (green * 256) + blue;
                img.setRGB(k, i, rbg);
                System.out.println(rbg);

            }
        }
        File f = new File("greyCoil2.png");
        try {
            ImageIO.write(img, "PNG", f);
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}
