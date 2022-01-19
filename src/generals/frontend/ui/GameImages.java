package generals.frontend.ui;

import javax.swing.*;
import java.awt.image.BufferedImage;

public class GameImages extends JPanel {



    public GameImages() {
        super();
        try{
            onesuncolonelimg = ImageIO.read(new File("1-sun-colonel.png"));
        }catch(IOException e){
            System.out.println("ERROR");
        }
    }
}
