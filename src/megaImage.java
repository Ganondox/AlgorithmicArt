import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: 36483
 * Date: 11/22/12
 * Time: 8:18 AM
 * To change this template use File | Settings | File Templates.
 */
public class megaImage {
    public static void main( String args[]) {
        BufferedImage img = new BufferedImage(4096, 4096, BufferedImage.TYPE_INT_RGB);
        for(int i = 0; i < 4096; i++ ){
            for(int k = 0; k < 4096; k++) {
                img.setRGB(i, k, ((i * 4096) + k) / 1);
                System.out.println((i * 4096) + k);

            }
        }
        File f = new File("megaImage.png");
        try {
            ImageIO.write(img, "PNG", f);
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}
