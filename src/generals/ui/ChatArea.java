package generals.ui;

import org.w3c.dom.css.RGBColor;

import javax.swing.*;
import java.awt.*;

/**
 * @author Yidi Chen
 * @date 2022-01-12
 */
public class ChatArea extends JPanel {

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
        super(new BorderLayout());
        setSize(380, 300);
        // set text
        text = new JTextArea();
        scrollPane = new JScrollPane(scrollPane);
        // set input
        input = new JTextField();
        add(scrollPane, BorderLayout.CENTER);
        add(input, BorderLayout.SOUTH);
    }

    /**
     * Print a line in chat area
     */
    public void println(String strMsg) {
        text.append(strMsg);
        text.append("\n");
    }
}
