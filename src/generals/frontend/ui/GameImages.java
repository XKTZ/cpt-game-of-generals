package generals.frontend.ui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public interface GameImages {

    BufferedImage IMAGE_FLAG = imageOf("res/img/flag.png");

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
