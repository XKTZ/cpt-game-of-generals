package generals.frontend;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public interface GameImages {

    BufferedImage IMAGE_FLAG = imageOf("/img/1-flag.png");

    BufferedImage[] LEVEL_IMAGES = new BufferedImage[]{
            null,
            imageOf("/img/1-flag.png"),
            imageOf("/img/2-spy.png"),
            imageOf("/img/3-private.png"),
            imageOf("/img/4-sergent.png"),
            imageOf("/img/5-2nd-lieutenant.png"),
            imageOf("/img/6-1st-lieutenant.png"),
            imageOf("/img/7-captain.png"),
            imageOf("/img/8-major.png"),
            imageOf("/img/9-lieutenant-colonel.png"),
            imageOf("/img/10-colonel.png"),
            imageOf("/img/11-1-star-general.png"),
            imageOf("/img/12-2-star-general.png"),
            imageOf("/img/13-3-star-general.png"),
            imageOf("/img/14-4-star-general.png"),
            imageOf("/img/15-5-star-general.png")
    };

    static BufferedImage imageOf(String path) {
        try {
            BufferedImage image = ImageIO.read(GameImages.class.getResourceAsStream(path));
            return image;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
