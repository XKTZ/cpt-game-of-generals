package generals.ui;

import org.w3c.dom.css.RGBColor;

import javax.swing.*;
import java.awt.*;

/**
 * @author Yidi Chen
 * @date 2022-01-12
 */
public class ChatArea extends JPanel {

    String strPrevMsg = "";

    public void paintComponent(Graphics g){
        Color myred = new Color(230, 170, 150);
        g.setColor(myred);
        g.fillRect(900,0, 380,275);
    }

    public ChatArea() {
        super();
    }

    public void println(String strMsg, TextArea chat, String strUser) {
        if(!strMsg.equals(strPrevMsg)) {
            chat.append(strUser + ": " + strMsg + "\n");
        }
        strPrevMsg = strMsg;
    }
}
