package generals.ui;

import org.w3c.dom.css.RGBColor;

import javax.swing.*;
import java.awt.*;

/**
 * @author Yidi Chen
 * @date 2022-01-12
 */
public class ChatArea extends JPanel {

    private static final Font fnt = new Font("Times New Roman", Font.PLAIN, 12);

    /**
     * Scroll pane
     */
    JScrollPane scrollPane;

    /**
     * Text field
     */
    JTextArea text;

    /**
     * Input
     */
    JTextField input;

    /**
     * Create the chat area
     */
    public ChatArea() {
        super(null);

        setSize(380, 300);
        // set text
        text = new JTextArea();
        text.setSize(380, 260);
        text.setLocation(0, 0);
        text.setLineWrap(true);
        text.setFont(fnt);
        scrollPane = new JScrollPane(text);
        scrollPane.setSize(380, 260);
        scrollPane.setLocation(0, 0);

        // set input
        input = new JTextField();
        input.setSize(380, 40);
        input.setLocation(0, 260);
        input.setFont(fnt);

        this.add(scrollPane);
        this.add(input);
    }

    /**
     * Print a line in chat area
     */
    public void println(String strMsg) {
        text.append(strMsg);
        text.append("\n");
    }
}
