package generals.frontend.ui;

import javax.swing.*;
import javax.imageio.ImageIO;
import java.awt.image.*;
import java.io.*;

public class GameImages extends JPanel {
    BufferedImage imgSpy;
    BufferedImage imgSergeant;
    BufferedImage imgPrivate;
    BufferedImage imgFlag;
    BufferedImage img5Star;
    BufferedImage img4Star;
    BufferedImage img3Star;
    BufferedImage img2Star;
    BufferedImage img1Star;
    BufferedImage img3Sun;
    BufferedImage img2Sun;
    BufferedImage img1Sun;
    BufferedImage img3Triangle;
    BufferedImage img2Triangle;
    BufferedImage img1Triangle;


    public GameImages() {
        super();
        try{
            img1Sun = ImageIO.read(new File("1-sun-colonel.png"));
        }catch(IOException e){
            System.out.println("ERROR");
        }
    }
}
